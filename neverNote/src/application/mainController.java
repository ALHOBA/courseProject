package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.util.Duration;
import javafx.scene.input.*;

public class mainController  extends Main {
	@FXML public void guestLogin(Event event) {
		boolean kEvent = false , aEvent = false;
		if((event instanceof KeyEvent)) {
			if(((KeyEvent)event).getCode()==KeyCode.ENTER) {
				kEvent = true;
			}
		}
		if(event instanceof ActionEvent) {
				aEvent = true;
		}
			if(kEvent || aEvent) {
		changeScene("app.fxml");
		main_alert.setTextFill(Color.GREEN);
			}
	} 
			
	@FXML
	private void  login(Event event) {
		if((event instanceof KeyEvent && ((KeyEvent)event).getCode()==KeyCode.ENTER) || event instanceof ActionEvent){
			String usr = log_user.getText().trim().toLowerCase();
			String pass = log_pass.getText().trim();
		 
		DataBase db = new DataBase(usr , pass , "file.xml");
		boolean userExists = db.userExists(usr);
		boolean passWordMatches = db.passwordMatches(pass);
		if(userExists && passWordMatches) {
			userLoggedIn = true;
		}
		if(userLoggedIn) {
			changeScene("app.fxml");
			main_alert.setTextFill(Color.GREEN);
		}
		else {
			main_alert.setTextFill(Color.RED);
			if(usr.trim().isEmpty()) {
				Anim.fadeIn(main_alert , 400);
				main_alert.setText("نام کاربری وارد کنید");
				main_alert.setVisible(true);
			}
			else if(pass.isEmpty()) {
				Anim.fadeIn(main_alert , 400);
				main_alert.setText("لطفا پسورد را وارد کنید");
				main_alert.setVisible(true);
			}
			else if(!userExists){
				main_alert.setText("این نام کاربری وجود ندارد");
				Anim.fadeIn(main_alert , 400);
				main_alert.setVisible(true);
			}
			else if(! passWordMatches) {
				Anim.fadeIn(main_alert , 400);
				main_alert.setText("نام کاربری با رمز عبور داده شده تطابق ندارد");
				main_alert.setVisible(true);
			}
		}
		}
		}
 
	@FXML
	public void signup(Event event) throws FileNotFoundException, IOException, TransformerException, SAXException {
		if(((event instanceof KeyEvent && ((KeyEvent)event).getCode()==KeyCode.ENTER)) || (event instanceof ActionEvent)){
		String re_pass = reg_password.getText().trim();
		String ver_pass = verify_reg_password.getText().trim();
		String usr = reg_username.getText().toLowerCase().trim();
		DataBase db = new DataBase(usr , re_pass , "file.xml");
		boolean userExists = db.userExists(usr);
		boolean newUser = !userExists;
		boolean passWordMatches = re_pass.equals(ver_pass);

		if(re_pass.equals(ver_pass) && !re_pass.isEmpty() && !usr.isEmpty()) {

			if(newUser) {
				try {
					db.createDatabase();
				} catch (XPathExpressionException | ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			signup_alert.setText("خوش آمدید");
			signup_alert.setTextFill(Color.GREEN);
			Anim.fadeOut( signup , 400);
			Anim.fadeOut(signup_alert , 500);
			userLoggedIn = true;
		}
		else {
			signup_alert.setTextFill(Color.RED);
			if(usr.trim().isEmpty()) {
				Anim.fadeIn(signup_alert , 400);
				signup_alert.setText("نام کاربری وارد کنید");
				signup_alert.setVisible(true);
			}
			else if(re_pass.isEmpty()) {
				Anim.fadeIn(signup_alert , 400);
				signup_alert.setText("لطفا پسورد را وارد کنید");
				signup_alert.setVisible(true);
			}
			else if(ver_pass.isEmpty()) {
				Anim.fadeIn(signup_alert , 400);
				signup_alert.setText("لطفا تکرار پسورد را وارد کنید");
				signup_alert.setVisible(true);
			}
			else if(!newUser){
				signup_alert.setText("این نام کاربری وجود دارد");
				Anim.fadeIn(signup_alert , 400);
				signup_alert.setVisible(true);
			}
			else if(!passWordMatches) {
				Anim.fadeIn(signup_alert , 400);
				signup_alert.setText("نام کاربری با رمز عبور داده شده تطابق ندارد");
				signup_alert.setVisible(true);
			}
		}
	}
	}
	//animation
 

	//end animation
	private static boolean userLoggedIn=false;
	public static boolean isUserLoggedIn() {
		return userLoggedIn;
	}
	public static void setUserLoggedIn(boolean userLoggedIn) {
		mainController.userLoggedIn = userLoggedIn;
	}
	public static boolean isGuestLoggedIn() {
		return guestLoggedIn;
	}
	public static void setGuestLoggedIn(boolean guestLoggedIn) {
		mainController.guestLoggedIn = guestLoggedIn;
	}

	private static boolean guestLoggedIn = false;

	// FXML Elements
	@FXML
	private Button btn_guest;
	@FXML 
	private Button btn_user;
	@FXML
	private PasswordField password;
	@FXML
	private AnchorPane main;
	@FXML
	private Button btn_signup;
	@FXML
	private AnchorPane signup;
	@FXML 
	private Button btn_register;
	@FXML
	private TextField reg_username;
	@FXML 
	private PasswordField reg_password;
	@FXML
	private PasswordField verify_reg_password;
	@FXML
	private Label  main_alert;
	@FXML 
	private Label signup_alert;
	@FXML
	private PasswordField log_pass;
	@FXML
	private TextField log_user;
	//FXML Methods
	@FXML
	private void showSignUpPanel() {
		Anim.fadeIn( signup , 300);
		for(Node node :signup.getChildren()) {
			if(node instanceof Control && node.isVisible())
			Anim.fadeIn(node , 1200);
		}
	}
	  private TreeItem<File> createNode(final File f) {
		    return new TreeItem<File>(f) {
		      private boolean isLeaf;
		      private boolean isFirstTimeChildren = true;
		      private boolean isFirstTimeLeaf = true;

		      @Override
		      public ObservableList<TreeItem<File>> getChildren() {
		        if (isFirstTimeChildren) {
		          isFirstTimeChildren = false;
		          super.getChildren().setAll(buildChildren(this));
		        }
		        return super.getChildren();
		      }

		      @Override
		      public boolean isLeaf() {
		        if (isFirstTimeLeaf) {
		          isFirstTimeLeaf = false;
		          File f = (File) getValue();
		          isLeaf = f.isFile();
		        }
		        return isLeaf;
		      }

		      private ObservableList<TreeItem<File>> buildChildren(
		          TreeItem<File> TreeItem) {
		        File f = TreeItem.getValue();
		        if (f == null) {
		          return FXCollections.emptyObservableList();
		        }
		        if (f.isFile()) {
		          return FXCollections.emptyObservableList();
		        }
		        File[] files = f.listFiles();
		        if (files != null) {
		          ObservableList<TreeItem<File>> children = FXCollections
		              .observableArrayList();
		          for (File childFile : files) {
		            children.add(createNode(childFile));
		          }
		          return children;
		        }
		        return FXCollections.emptyObservableList();
		      }
		    };
		  }
	
	
	@FXML
	private TreeView<File>  fileTree;
	@FXML
	private void browse() {
	    TreeItem<File> root = createNode(new File("/"));
	    fileTree.setRoot(root);	  
	}
	@FXML
	private void initialize() {
		for(Node node : main.getChildren()) {
			if(node instanceof Control)
			Anim.fadeIn(node , 1200);
			Anim.fadeIn(main, 500);
		}
		DataBase db = new DataBase("admin" , "admin" , "file.xml");
		try {
			if(!db.userExists("admin"))
				db.createDatabase();
		} catch (XPathExpressionException | IOException | TransformerException | SAXException
				| ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





}