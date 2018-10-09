package gui;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controllers.AppController;
import files.FileType;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

public class GUI {
	
	final Stage stage;
    final GridPane inputGridPane;
    final Pane rootGroup;
	
	private Label startNumLabel;
    private TextField startNumField;
    
    private Label endNumLabel;
    private TextField endNumField;
    
    private Label numOfColsLabel;
    private TextField numOfColsField;
    
    private Label fileNameLabel;
    private TextField fileNameField;
    
    final DirectoryChooser directoryChooser;
    
    private File selectedDirectory;
    private TextArea textArea;
    
    private TextArea currentDirectoryField;
    private String fileExt;
    
    private Label fileTypeChoiceBoxLabel;
    private ChoiceBox<String> fileTypeChoiceBox;
    
    private final Button generateButton;
    private final Button folderButton;
    final Button clearButton;

    private static final List<FileType> FILE_EXTENSIONS = Arrays.asList(FileType.CSV, FileType.TSV); 
    private final Map<String, FileType> fileExtMap;
    
    private final AppController controller;
    
	public GUI(Stage stage, AppController controller) {
		this.controller = controller;
		this.stage = stage;
		
		this.directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a Folder");
        
        this.folderButton = new Button("Select a Folder...");
        
        this.generateButton = new Button("Generate!");
        generateButton.setDisable(true);
        
        this.clearButton = new Button("Clear");

        folderButton.setOnAction( (e) -> this.controller.onFolderButtonClicked());

        generateButton.setOnAction( (e) -> this.controller.generateFile());

        clearButton.setOnAction((e) -> this.textArea.clear());

        inputGridPane = new GridPane();

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
        
        fileNameLabel = new Label("Filename: ");
        fileNameField = new TextField();
        
        GridPane.setConstraints(fileNameLabel, 0, 3);
        GridPane.setConstraints(fileNameField, 1, 3);
        
        this.fileExtMap = Arrays.asList(FileType.values())
        					    .stream()
        					    .collect(Collectors.toMap(FileType::getFileExtension, f -> f));

        this.fileTypeChoiceBoxLabel = new Label("File Type: ");
        
        this.fileTypeChoiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(this.fileExtMap.keySet()));
        this.fileTypeChoiceBox.getSelectionModel()
          .selectedItemProperty()
          .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> this.controller.setFileType(this.fileExtMap.get(newValue)));
        
        this.fileTypeChoiceBox.getSelectionModel().select(FILE_EXTENSIONS.get(0).getFileExtension());
        
        GridPane.setConstraints(fileTypeChoiceBoxLabel, 2, 3);
        GridPane.setConstraints(fileTypeChoiceBox, 3, 3);
        
        this.textArea = new TextArea();
        this.textArea.setWrapText(true);
        this.textArea.setMaxSize(200, 100);

        this.currentDirectoryField = new TextArea();
        this.currentDirectoryField.setEditable(false);
        this.currentDirectoryField.setMaxSize(200, 40);
        currentDirectoryField.setMinSize(200, 40);

        GridPane.setConstraints(folderButton, 0, 4);
        GridPane.setConstraints(currentDirectoryField, 1 , 4);
        GridPane.setConstraints(generateButton, 0, 5);
        GridPane.setConstraints(textArea, 0, 6);
        GridPane.setConstraints(clearButton, 1, 6);
        this.inputGridPane.setHgap(6);
        this.inputGridPane.setVgap(6);
        this.inputGridPane.getChildren().addAll(
        		this.folderButton, this.generateButton, this.clearButton, 
        		this.currentDirectoryField, this.textArea, this.startNumLabel, 
        		this.startNumField, this.endNumLabel, this.endNumField, 
        		this.numOfColsLabel, this.numOfColsField, this.fileNameField, 
        		this.fileNameLabel, this.fileTypeChoiceBox);

        this.rootGroup = new VBox(12);
        this.rootGroup.getChildren().addAll(inputGridPane);
        this.rootGroup.setPadding(new Insets(12, 12, 12, 12));
	}
	
	public Stage getStage() {
		return stage;
	}

	public DirectoryChooser getDirectoryChooser() {
		return directoryChooser;
	}

	public AppController getController() {
		return controller;
	}

	public Label getStartNumLabel() {
		return startNumLabel;
	}

	public void setStartNumLabel(Label startNumLabel) {
		this.startNumLabel = startNumLabel;
	}

	public TextField getStartNumField() {
		return startNumField;
	}

	public void setStartNumField(TextField startNumField) {
		this.startNumField = startNumField;
	}

	public Label getEndNumLabel() {
		return endNumLabel;
	}

	public void setEndNumLabel(Label endNumLabel) {
		this.endNumLabel = endNumLabel;
	}

	public TextField getEndNumField() {
		return endNumField;
	}

	public void setEndNumField(TextField endNumField) {
		this.endNumField = endNumField;
	}

	public Label getNumOfColsLabel() {
		return numOfColsLabel;
	}

	public void setNumOfColsLabel(Label numOfColsLabel) {
		this.numOfColsLabel = numOfColsLabel;
	}

	public TextField getNumOfColsField() {
		return numOfColsField;
	}

	public void setNumOfColsField(TextField numOfColsField) {
		this.numOfColsField = numOfColsField;
	}

	public Label getFileNameLabel() {
		return fileNameLabel;
	}

	public void setFileNameLabel(Label fileNameLabel) {
		this.fileNameLabel = fileNameLabel;
	}

	public TextField getFileNameField() {
		return fileNameField;
	}

	public void setFileNameField(TextField fileNameField) {
		this.fileNameField = fileNameField;
	}

	public File getSelectedDirectory() {
		return selectedDirectory;
	}

	public void setSelectedDirectory(File selectedDirectory) {
		this.selectedDirectory = selectedDirectory;
	}

	public TextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}

	public TextArea getCurrentDirectoryField() {
		return currentDirectoryField;
	}

	public void setCurrentDirectoryField(TextArea currentDirectoryField) {
		this.currentDirectoryField = currentDirectoryField;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public Label getFileTypeChoiceBoxLabel() {
		return fileTypeChoiceBoxLabel;
	}

	public void setFileTypeChoiceBoxLabel(Label fileTypeChoiceBoxLabel) {
		this.fileTypeChoiceBoxLabel = fileTypeChoiceBoxLabel;
	}

	public ChoiceBox<String> getFileTypeChoiceBox() {
		return fileTypeChoiceBox;
	}

	public void setFileTypeChoiceBox(ChoiceBox<String> fileTypeChoiceBox) {
		this.fileTypeChoiceBox = fileTypeChoiceBox;
	}

	public GridPane getInputGridPane() {
		return inputGridPane;
	}

	public Pane getRootGroup() {
		return rootGroup;
	}

	public static List<FileType> getFileExtensions() {
		return FILE_EXTENSIONS;
	}

	public Button getGenerateButton() {
		return generateButton;
	}

	public Button getFolderButton() {
		return folderButton;
	}

	public Button getClearButton() {
		return clearButton;
	}
	
}
