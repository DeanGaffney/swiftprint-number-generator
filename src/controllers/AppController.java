package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import exceptions.ExceptionHandler;
import files.DelimitedFile;
import files.FileType;
import gui.GUI;
import javafx.stage.Stage;
import utils.MatrixUtils;

public class AppController {

	private GUI gui;
	private DelimitedFile delimitedFileModel;
	
	public AppController(Stage stage) {
		this.gui = new GUI(stage, this);
		this.delimitedFileModel = new DelimitedFile();
	}
	
	public void setFileType(FileType fileType){
		getDelimitedFileModel().setFileType(fileType);
	}
	
	public void onFolderButtonClicked(){
		File file = getGui().getDirectoryChooser().showDialog(getGui().getStage());
        if (file != null) {
            getDelimitedFileModel().setFilePath(file.getPath());
            getGui().getCurrentDirectoryField().setText(file.getPath());
        }else{
            getGui().getCurrentDirectoryField().setText("Not a valid folder...");
        }
        getGui().getGenerateButton().setDisable(file == null);
	}
	
	public void generateFile(){
		if(getGui().getFileNameField().getCharacters().toString().isEmpty()) {
    		getGui().getTextArea().appendText("You have not inputted a file name...");
    	}else {
    		getGui().getTextArea().clear();
            getGui().getTextArea().appendText("Generating file...\n");
            String filePath = getDelimitedFileModel().getFilePath() + File.separator + getGui().getFileNameField().getCharacters().toString().trim() + getDelimitedFileModel().getFileType().getFileExtension();
            int startNum = Integer.parseInt(getGui().getStartNumField().getCharacters().toString().replaceAll(",", "").trim());
            int endNum = Integer.parseInt(getGui().getEndNumField().getCharacters().toString().replaceAll(",", "").trim());
            int numOfCols = Integer.parseInt(getGui().getNumOfColsField().getCharacters().toString().replaceAll(",", "").trim());
            String[][] transposedMatrix;
            PrintWriter printWriter = null;
			try {
				transposedMatrix = MatrixUtils.createMatrix(startNum, endNum, numOfCols);
	            printWriter = new PrintWriter(new FileWriter(filePath));
	            getDelimitedFileModel().generateFile(printWriter, filePath, transposedMatrix);
	            getGui().getTextArea().appendText("Completed! Your file can be found at " + filePath + "\n");
			} catch (Exception e) {
				ExceptionHandler.handleException(getGui(), e);
			} finally{
	            if(printWriter != null){
	                printWriter.flush();
	                printWriter.close();
	            }
	        }
    	}
	}
	
	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public DelimitedFile getDelimitedFileModel() {
		return delimitedFileModel;
	}

	public void setDelimitedFileModel(DelimitedFile delimitedFileModel) {
		this.delimitedFileModel = delimitedFileModel;
	}
	
}
