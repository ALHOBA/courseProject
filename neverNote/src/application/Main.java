package application;
	
import java.lang.annotation.Annotation;
import java.util.Collection;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;


public class Main extends Application {
 

	private static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		changeScene("main.fxml");
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        Main.primaryStage.show();
        Main.primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        Main.primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
	}
	// need to work
	public void changeScene(String fxml){
		try {
			Parent root = FXMLLoader.load(getClass().getResource(fxml));
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.setResizable(false);
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	        Main.primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
	        Main.primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
			   
	 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}