package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			 VBox root = (VBox)FXMLLoader.load(getClass().getResource("ComplexApplication/ComplexApplication.fxml"));
			Scene main = new Scene(root);
			primaryStage.setScene(main);
			primaryStage.show();
			
		 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
