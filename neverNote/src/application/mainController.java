package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import javax.naming.spi.DirectoryManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystem;
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
	class user{
		private String name;
		private String password;
		private String image;
		private String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public user(String name, String password, String image , String path) {
			super();
			this.name = name;
			this.password = password;
			this.image = image;
			this.path = path;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public void genXML(){
			DataBase db = new DataBase(this.path);
			Document doc =  db.generateDoc();
			
			db.createDatabase(doc);
		}
	}
	private static String login_user;
	
	public static String getLogin_user() {
		return login_user;
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
			try {
				profileImagePath = db.getAttributes(db.getElementById(usr).getElementsByTagName("image").item(0)).getNamedItem("src").getNodeValue();
				}
				catch(NullPointerException ex) {
					ex.printStackTrace();
				} 
		}
		if(userLoggedIn) {
			changeScene("app.fxml");
			this.login_user = usr;
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
		boolean duplicate = userExists;
		boolean passWordMatches = re_pass.equals(ver_pass);

		if(re_pass.equals(ver_pass) && !re_pass.isEmpty() && !usr.isEmpty() && !duplicate) {
			if(!duplicate) {
				try {
				Document doc;
				doc = db.generateDoc();
				// set root element
				// create new elements
				Element user = doc.createElement("user");
				db.getRoot().appendChild(user);
				user.setAttribute("id", db.getUserName() );
				Element pass =  doc.createElement("password");
				pass.setTextContent(re_pass);
				db.getRoot().appendChild(user);
				Element image = doc.createElement("image");
				image.setAttribute("src",profileImagePath);
				user.appendChild(pass);
				user.appendChild(image);
				db.createDatabase(doc);
				}
				catch(NullPointerException ex) {
					ex.printStackTrace();
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
			else if(re_pass.trim().isEmpty()) {
				Anim.fadeIn(signup_alert , 400);
				signup_alert.setText("لطفا پسورد را وارد کنید");
				signup_alert.setVisible(true);
			}
			else if(ver_pass.trim().isEmpty()) {
				Anim.fadeIn(signup_alert , 400);
				signup_alert.setText("لطفا تکرار پسورد را وارد کنید");
				signup_alert.setVisible(true);
			}
			else if(duplicate){
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
 
	private static String profileImagePath = "file:default-admin.png";
	 
	@FXML
	private void browse () {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("choose profile image");
		ExtensionFilter extFilter = new  ExtensionFilter("image files", "*.GIF" , "*.BMP" , "*.JPEG" , "*.PNG" , "*.png" , "*.png" , "*.bmp" , "*.bmp" , "*.jpg" , "*.jpeg");
		fileChooser.getExtensionFilters().add(extFilter);
		File img = fileChooser.showOpenDialog(new Stage());
 
			try {
				setProfileImagePath(img.toURI().toURL().toExternalForm());
			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
		userImage.setImage(new Image(getProfileImagePath()));
	  
	 
	}
	@FXML
	private ImageView userImage;
	@FXML
	private void initialize() {
		for(Node node : main.getChildren()) {
			if(node instanceof Control)
			Anim.fadeIn(node , 1200);
			Anim.fadeIn(main, 500);
		}
	 
			new File("user").mkdirs();
		DataBase db = new DataBase("admin" , "admin" , "file.xml");

		Document doc   ;
		doc = db.generateDoc();
		Element user;
		Element pass ;
 

		user admin = new user("admin", "admin", "file:default-admin.png", "user/" + "admin.xml");
	db = new DataBase(admin.name , admin.password , admin.path);
	 doc = null ;
	doc = db.generateDoc();
	 user = doc.createElement("user");
	db.getRoot().appendChild(user);
	user.setAttribute("id", db.getUserName());
	 pass =  doc.createElement("password");
	pass.setTextContent(db.getPassWord());
    user.appendChild(pass);
    Element image = doc.createElement("image");
    user.appendChild(pass);
    user.appendChild(image);
     
    image.setAttribute("src",admin.path);
		db.createDatabase(doc);
}

	public static String getProfileImagePath() {
		return profileImagePath;
	}

	public static void setProfileImagePath(String path) {
		 profileImagePath = path;
	}





}