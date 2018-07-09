package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.event.*;


public class mainController {
 
	@FXML public void guestLogin(ActionEvent event) {
		System.out.println("hello world!");
	}
	@FXML
	private void userlogin() {
	 
			DataBase db = new DataBase("file.xml");
				if(loggedIn) {
					
				}
				
		 
	}
	@FXML
	public void signup() throws FileNotFoundException, IOException, TransformerException, SAXException {
		String re_pass = reg_password.getText();
		String ver_pass = verify_reg_password.getText();
		String usr = reg_username.getText();

		if(!usr.isEmpty())

		{
			if(re_pass.equals(ver_pass) && !re_pass.isEmpty()) {
			DataBase db = new DataBase(usr,re_pass,"file.xml");
			boolean newUser = !db.userExists(usr);
			if(newUser) {
				try {
					db.createDatabase();
				} catch (XPathExpressionException | ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				alert.setText("خوش آمدید");
				alert.setTextFill(Color.GREEN);
				fadeOutPane(signup);
			}
			else {
				alert.setText("این نام کاربری وجود دارد");
				alert.setTextFill(Color.RED);
			}
			fadeIn.play();
			alert.setVisible(true);
			 
		}
		else if(re_pass.isEmpty()) {
			fadeIn.play();
			alert.setText("لطفا پسورد وارد کنید");
			alert.setVisible(true);
		}
		else if(ver_pass.isEmpty()) {
			fadeIn.play();
			alert.setText("لطفا تکرار پسورد وارد کنید");
			alert.setVisible(true);
		}
		else if(re_pass.compareTo(ver_pass)!=0) {
			fadeIn.play();
			alert.setText("پسوردها همسان نیستند");
			alert.setVisible(true);
		}
		else {
			fadeIn.play();
			alert.setText("نام کاربری وارد کنید");
			alert.setVisible(true);
		}
		}
	}
	//animation
	private void fadeOutPane(AnchorPane pane) {
		FadeTransition fadeOut = new FadeTransition(Duration.millis(300), pane);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		fadeOut.play();
		fadeOut.setOnFinished(e -> signup.setVisible(false));
	}
	private void fadeInPane(AnchorPane pane) {
		FadeTransition fadeIn = new FadeTransition(Duration.millis(500), pane);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.play();
		signup.setVisible(true);
	}
	//end animation
	boolean loggedIn=false;
	
	private  FadeTransition fadeIn;
	
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
	private Label alert;
	@FXML
	private PasswordField log_pass;
	@FXML
	private TextField log_user;
	//FXML Methods
	@FXML
	private void showSignUpPanel() {
		fadeInPane(signup);
	}
	@FXML
	private void login() {
		
	}
	@FXML
	private void initialize() {
		fadeIn = new FadeTransition(Duration.millis(500), alert);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setAutoReverse(true);
	}




}