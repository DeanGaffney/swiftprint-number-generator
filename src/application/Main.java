package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public final class Main extends Application {

    private Label startNumLabel;
    private TextField startNumField;
    private Label endNumLabel;
    private TextField endNumField;
    private Label numOfColsLabel;
    private TextField numOfColsField;
    private File selectedDirectory;
    private TextArea textArea;
    private TextArea currentDirectoryField;
    private static final String FILE_NAME = "generated-numbers.tsv";

    @Override
    public void start(final Stage stage) {
        stage.setTitle("File Chooser Sample");

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a Folder");
        final Button folderButton = new Button("Select a Folder...");
        final Button generateButton = new Button("Generate!");
        generateButton.setDisable(true);
        final Button clearButton = new Button("Clear");

        folderButton.setOnAction( (e) ->{
            File file = directoryChooser.showDialog(stage);
            if (file != null) {
                setFileSaveDriectory(file);
                currentDirectoryField.setText(file.getPath());
            }else{
                currentDirectoryField.setText("Not a valid folder...");
            }
            generateButton.setDisable(file == null);
        });

        generateButton.setOnAction( (e) ->{
            textArea.appendText("==================================\n");
            String filePath = this.selectedDirectory.getPath() + File.separator + FILE_NAME;
            generateFile(filePath);
        });

        clearButton.setOnAction((e) -> textArea.clear());

        final GridPane inputGridPane = new GridPane();

        startNumLabel = new Label("Starting number: ");
        startNumField = new TextField();
        startNumField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        GridPane.setConstraints(startNumLabel, 0, 0);
        GridPane.setConstraints(startNumField, 1, 0);

        endNumLabel = new Label("Ending number: ");
        endNumField = new TextField();
        endNumField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        GridPane.setConstraints(endNumLabel, 0, 1);
        GridPane.setConstraints(endNumField, 1, 1);

        numOfColsLabel = new Label("Number of columns: ");
        numOfColsField = new TextField();
        endNumField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        GridPane.setConstraints(numOfColsLabel, 0, 2);
        GridPane.setConstraints(numOfColsField, 1, 2);

        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setMaxSize(200, 100);

        currentDirectoryField = new TextArea();
        currentDirectoryField.setEditable(false);
        currentDirectoryField.setMaxSize(200, 40);
        currentDirectoryField.setMinSize(200, 40);

        GridPane.setConstraints(folderButton, 0, 3);
        GridPane.setConstraints(currentDirectoryField, 1 , 3);
        GridPane.setConstraints(generateButton, 0, 4);
        GridPane.setConstraints(textArea, 0, 5);
        GridPane.setConstraints(clearButton, 1, 5);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(folderButton, generateButton, clearButton, currentDirectoryField, textArea, startNumLabel, startNumField, endNumLabel, endNumField, numOfColsLabel, numOfColsField);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        stage.setScene(new Scene(rootGroup));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void setFileSaveDriectory(File file){
        this.selectedDirectory = file;
    }

    private void generateFile(String filePath) {
        String[][] transposedMatrix = createMatrix();
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(filePath));
            for (int i = 0; i < transposedMatrix.length; i++) {
                String str = "";
                for (int j = 0; j < transposedMatrix[i].length; j++) {
                    str += transposedMatrix[i][j] + "\t";
                }
                printWriter.write(str + "\n");
            }
            textArea.appendText("Completed! Your file can be found at " + filePath + "\n");
        } catch (IOException e) {
            System.out.println(e);
            textArea.appendText("Something went wrong while writing to the file...\n");
        } catch (NullPointerException e){

        }finally{
            if(printWriter != null){
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    private String[][] createMatrix(){
        textArea.appendText("Creating matrix...\n");
        List<List<String>> matrix = new ArrayList<List<String>>();
        String[][] transposedMatrix = null;
        try{
            int startNum = Integer.parseInt(startNumField.getCharacters().toString().replaceAll(",", "").trim());
            int endNum = Integer.parseInt(endNumField.getCharacters().toString().replaceAll(",", "").trim());
            int numOfCols = Integer.parseInt(numOfColsField.getCharacters().toString().replaceAll(",", "").trim());
            int decreasingNum = endNum;
            int total = endNum - startNum;
            int currentColumnNumber = 1;
            double itemsPerColumn = Math.ceil((double)(total + 1) / (double)numOfCols);
            List<String> colValues = new ArrayList<String>();
            for(int i = 0; i <= total; i++){
                if(i % itemsPerColumn == 0){
                    if(i != 0){
                        List<String> temp = new ArrayList<String>(colValues);
                        matrix.add(temp);
                        colValues.clear();
                    }
                    colValues.add("Number " + currentColumnNumber++);
                }
                colValues.add(Integer.toString(decreasingNum--));
            }

            if(!colValues.isEmpty())matrix.add(colValues);
            textArea.appendText("Matrix created...\n");
            transposedMatrix = transpose(matrix);
        }catch(NumberFormatException e) {
            textArea.appendText("Error occurred while reading inputs....\nMake sure your values are not empty and are valid numbers...\n");
        }catch(Exception e){

        }
        return transposedMatrix;
    }

    private String [][] transpose(List<List<String>> matrix) throws Exception{
        textArea.appendText("Transposing matrix...\n");
        String [][] transposedMatrix = new String [matrix.get(0).size()][matrix.size()];
        for(int i = 0; i < matrix.size(); i++){
            for(int j = 0; j < matrix.get(0).size(); j++){
                try{
                    transposedMatrix[j][i] = matrix.get(i).get(j);
                }catch(IndexOutOfBoundsException e){
                    transposedMatrix[j][i] = "";
                }
            }
        }
        textArea.appendText("Transposing matrix complete...\n");
        return transposedMatrix;
    }
}