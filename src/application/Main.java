package application;

import controllers.AppController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Main extends Application {

	private AppController appController;
	
    @Override
    public void start(final Stage stage) {
        stage.setTitle("SwiftPrint Number Generator");

        this.appController = new AppController(stage);

        stage.setScene(new Scene(this.appController.getGui().getRootGroup()));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}