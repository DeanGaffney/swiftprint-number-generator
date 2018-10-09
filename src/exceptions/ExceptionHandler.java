package exceptions;

import java.io.IOException;

import gui.GUI;

public class ExceptionHandler {

	public static void handleException(GUI gui, Exception e){
		if(e instanceof NumberFormatException){
            gui.getTextArea().appendText("Error occurred while reading inputs....\nMake sure your values are not empty and are valid numbers...\n");
		}else if(e instanceof IOException){
			gui.getTextArea().appendText("Something went wrong while writing to the file...\n");
		}
	}
}
