package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.util.Duration;
 

 

public class appController extends Main {
	private int nbLayoutX = 14;
	private int nbLayoutY = 53;
	private List<noteBook> noteBooks = new ArrayList();
 
	@FXML
	private AnchorPane center , right , left;
	class noteBook{
		private String path;
		private String name;
		private String img_path;
		// TO-DO add notes memos and to-do lists to this class...
		public String getPath() {
			return path;
		}
		public noteBook(String path, String name, String img_path) {
			super();
			this.path = path;
			this.name = name;
			this.img_path = img_path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getImg_path() {
			return img_path;
		}
		public void setImg_path(String img_path) {
			this.img_path = img_path;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	@FXML
	private void newNoteBook(){
		if(nbLayoutY < center.getHeight() - 130) {
			ImageView bookImage = new ImageView("file:notebook.png");
			bookImage.setLayoutX(nbLayoutX);
			bookImage.setLayoutY(nbLayoutY);
			bookImage.setPreserveRatio(false);
			bookImage.setFitWidth(114);
			bookImage.setFitHeight(100);
		    bookImage.setPickOnBounds(true);
			bookImage.setPreserveRatio(true);
			bookImage.setVisible(true);
			center.getChildren().add(bookImage);
			noteBook book = new noteBook("/file","love story" , "file:trash.png");
			noteBooks.add(book);
			ImageView delete = new ImageView("file:trash.png");
			delete.setLayoutX(nbLayoutX+5);
			delete.setPreserveRatio(false);
			delete.setFitWidth(32);
			delete.setFitHeight(32);
			delete.setLayoutY(nbLayoutY+10 + bookImage.getFitHeight());
			center.getChildren().add(delete);
			delete.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					
				}
				
			});
			nbLayoutX+=120;
			}
			if(nbLayoutX >= center.getWidth() - 120) {
				nbLayoutX = 14;
				nbLayoutY += 148;
			}
	}
	@FXML
	private void logOut(MouseEvent event) {
		if(event.getButton()==MouseButton.PRIMARY){
			changeScene("main.fxml");
			mainController.setUserLoggedIn(false);
			mainController.setGuestLoggedIn(false);
		}
	}
	@FXML
	private Button btn_newNoteBook;
	@FXML
	private ImageView userImage;
	@FXML
	public void initialize() {
		  userImage.setFitWidth(150);
		  userImage.setFitHeight(150);
		  left.setStyle(" -fx-alignment: center ;\n" + 
		  		"    -fx-padding: 10 ;");
		  Rectangle clip = new Rectangle(
	                userImage.getFitWidth(), userImage.getFitHeight()
	            );
		  userImage.setPreserveRatio(false);
		 
	            clip.setArcWidth(150);
	            clip.setArcHeight(150);
	            userImage.setClip(clip);
 
 
	}
 
}
