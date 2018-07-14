package application;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Screen;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;

//re-design with better design pattern afterward

public class appController extends Main {

	private List<noteBook> noteBooks = new ArrayList<noteBook>();
 
	@FXML
	private GridPane center , right , left;
	@FXML
	private ImageView previewNoteBookImage;
	@FXML
	private  Pane noteBookSetting;
	@FXML 
	private AnchorPane noteBookViewer;
	@FXML
	private Button btn_newNoteBook;
	@FXML
	private ImageView userImage;
	 

	public void setUserImage(Image userImage) {
		 this.userImage.setImage(userImage);
	}

	@FXML
	private Button saveButton;
	@FXML 
	private ChoiceBox<String> noteType;
	@FXML
	private TextField noteBookName;
	@FXML
	private AnchorPane leftAnchor;
	@FXML 
	private TextArea note;
	@FXML 
	private AnchorPane noteViewer;
 
	@FXML
	private TextField noteTitle;
	@FXML
	private ColorPicker colorPicker;
	class toDo{
		private String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public ArrayList<String> getList() {
			return list;
		}
		public void setList(ArrayList<String> list) {
			this.list = list;
		}
		public String getBackgroundColor() {
			return backgroundColor;
		}
		public void setBackgroundColor(String backgroundColor) {
			this.backgroundColor = backgroundColor;
		}
		private String title;
		private ArrayList<String> list;
		private String backgroundColor;
		public toDo(String path, String title, ArrayList<String> list, String backgroundColor) {
			super();
			this.path = path;
			this.title = title;
			this.list = list;
			this.backgroundColor = backgroundColor;
		}
	}
	class Memo{
		private String path;
		private String text;
		private String backgroundColor;
	
		public Memo(String path, String text, String backgroundColor) {
			super();
			this.path = path;
			this.text = text;
			this.backgroundColor = backgroundColor;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getBackgroundColor() {
			return backgroundColor;
		}
		public void setBackgroundColor(String backgroundColor) {
			this.backgroundColor = backgroundColor;
		}
	}
 
	class Note  implements java.lang.Comparable<Note>  {
		private String path;
		private String title;
		private String text;
		public Note(String path, String title, String text, String attachment, String backgroundColor) {
			super();
			this.path = path;
			this.title = title;
			this.text = text;
			this.attachment = attachment;
			this.backgroundColor = backgroundColor;
		}
		public Note(String path, String title, String text, String backgroundColor) {
			super();
			this.path = path;
			this.title = title;
			this.text = text;
			this.backgroundColor = backgroundColor;
		}
		private String attachment;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getAttachment() {
			return attachment;
		}
		public void setAttachment(String attachment) {
			this.attachment = attachment;
		}
		public String getBackgroundColor() {
			return backgroundColor;
		}
		public void setBackgroundColor(String backgroundColor) {
			this.backgroundColor = backgroundColor;
		}
		private String backgroundColor;
		 
		@Override
		public int compareTo(Note other) {
			return title.compareTo(other.title);
		}
	 
  
	}

	class noteBook implements Comparable<noteBook>{
		private String path;
		private String title;
		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAbout() {
			return about;
		}

		public void setAbout(String about) {
			this.about = about;
		}

		public String getImg_path() {
			return img_path;
		}

		public void setImg_path(String img_path) {
			this.img_path = img_path;
		}

		private String about;
		private String img_path;
	
		public noteBook(String path, String title, String about, String img_path) {
			super();
			this.path = path;
			this.title = title;
			this.about = about;
			this.img_path = img_path;
		}

		@Override
		public int compareTo(noteBook other) {
			// TODO Auto-generated method stub
			return title.compareTo(other.getTitle());
		}	
	}
	 

	private String browsedImagePath;
 
	private String noteBookBrowsedFolderPath = "notebook/";
	 
	private int nbLayoutX = 14;
	private int nbLayoutY = 25;
	private int nLayoutX = 22;
	private int nLayoutY = 67;

	@SuppressWarnings("unchecked")
	public <T > boolean    binarySearch(String token ,  List<T> param) {
		// returns true if token found otherwise false
		try {
			int low = 0;
			int high = ((List<T>)param).size() -1;
			int mid;

			if(param.getClass().isInstance(noteBooks)) {


				while (low <= high) {
					mid = (low + high) / 2;

					if (((List<noteBook>)param).get(mid).title.compareTo(token) < 0) {
						low = mid + 1;
					} else if (((List<noteBook>)param).get(mid).title.compareTo(token) > 0) {
						high = mid - 1;
					} else {
						return true;
					}
				}
			}
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();

		}
	 
		return false;
	}
	 
	private HashSet<String> hset  = new HashSet<>();
	
 
	@FXML
	private void browseFolder() {
		try {
		if( noteBookBrowsedFolderPath.equals("notebook/")) {
			new File( noteBookBrowsedFolderPath).mkdirs();
		}
		DirectoryChooser chooser = new DirectoryChooser();
		File selectedDirectory = new File( noteBookBrowsedFolderPath);
		chooser.setInitialDirectory(selectedDirectory);
		chooser.setTitle("Folder");
		try {
			selectedDirectory = chooser.showDialog(new Stage());
			this. noteBookBrowsedFolderPath =  selectedDirectory.getPath();
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML
	private void browseImage() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("choose profile image");
		ExtensionFilter extFilter = new  ExtensionFilter("image files", "*.GIF" , "*.BMP" , "*.JPEG" , "*.PNG" , "*.png" , "*.png" , "*.bmp" , "*.bmp" , "*.jpg" , "*.jpeg");
		fileChooser.getExtensionFilters().add(extFilter);
		File img = fileChooser.showOpenDialog(new Stage());
		this.browsedImagePath = img.getPath();
		previewNoteBookImage.setImage(new Image("file:" + img.getPath()));
	}
	@SuppressWarnings("unchecked")
	private String uniqueID = "";
	
	@FXML private TextArea notePaper;
	 
	@SuppressWarnings("unchecked")
	@FXML 
	private <T> void newNote() {
		// work later
		if(nLayoutY < left.getHeight() - 170) {
			String title = noteBookName.getText();
			final T note  ;
			boolean duplicate = false;
			if(!noteViewer.isVisible()) {
				Anim.fadeIn(noteViewer, 400);
				noteBookViewer.toBack();
				noteViewer.toFront();
			}
			if(!title.isEmpty())
			duplicate = binarySearch(title , noteBooks);
			final ImageView noteImage  ;
			final TextField label  ;
			
			final ImageView close =   new ImageView("file:close.png");
			final ImageView delete =   new ImageView("file:trash.png");
			close.setPreserveRatio(false);
			close.setFitWidth(32);
			close.setFitHeight(32);
			close.setPickOnBounds(true);
			close.setVisible(true);
			delete.setLayoutX(nLayoutX+34);
			delete.setLayoutY(nLayoutY+10 + 114);
			delete.setPreserveRatio(false);
			delete.setFitWidth(32);
			delete.setFitHeight(32);
			close.setLayoutY(nLayoutY+10 + 114);
			close.setLayoutX(nLayoutX+5);
			final DataBase db;
			final String uniqueId =  uniqueID;
			final TextArea memo;
			final ArrayList<String> list = new ArrayList<String>();
			label = new TextField();
			label.setLayoutX(nLayoutX+73);
			label.setMaxWidth(114 - 50);
			label.setPrefWidth(75);
			label.setLayoutY(nLayoutY+10 + 100);
			noteBookViewer.getChildren().add(close);
			 
			if(!duplicate) {
				switch(noteType.getSelectionModel().getSelectedItem().toString()) {
				case "memo":
					note = (T) new Memo(selectedNoteBook   , "Another Memo" , colorPicker.getValue().toString()  );
					memo = new TextArea();
					noteImage = new ImageView("file:note.png"); 
					memo.setPrefWidth(114);
					memo.setPrefHeight(100);
					memo.setMinHeight(100);
					memo.setMinWidth(114);
					memo.setWrapText(true);
					memo.setLayoutX(nLayoutX+5);
					memo.setLayoutY(nLayoutY+10);
					memo.setBackground(Background.EMPTY);
					memo.setStyle("-fx-background-color:" + colorPicker.getValue().toString().replace("0x", "#") + ";-fx-font-family:IRANSans;");
		            memo.applyCss();
					noteViewer.getChildren().add(memo);
					db = new DataBase(selectedNoteBook);
					Document doc = db.generateDoc();
					Element memoRoot = doc.createElement("memo");
					db.getRoot().appendChild(memoRoot);
					uniqueID = UUID.randomUUID().toString();
					memoRoot.setAttribute("id", uniqueID);
					Element nbText = doc.createElement("text");
					Element background = doc.createElement("background");
					nbText.setTextContent(((Memo) note).getText());
					memoRoot.appendChild(nbText);
					memoRoot.appendChild(background); 
					background.setAttribute("color", ((Memo) note).getBackgroundColor());
					
					db.createDatabase(doc);
					memo.setOnKeyPressed(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							// TODO Auto-generated method stub **
						
						}
						
					});
					break;
				case "note":
					note = (T) new Note(selectedNoteBook ,noteTitle.getText() , "Another note" , "" , colorPicker.getValue().toString() );
					noteImage = new ImageView("file:note.png"); 
					noteImage.setLayoutX(nLayoutX);
					noteImage.setLayoutY(nLayoutY);
					noteImage.setPreserveRatio(false);
					noteImage.setFitWidth(114); 
					noteImage.setFitHeight(100);
			 
					noteViewer.getChildren().add(label);
					noteImage.setOnMouseClicked(e->{notePaper.setVisible(true);saveButton.setVisible(true);});
					 
					notePaper.setOnKeyPressed(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							// TODO Auto-generated method stub
							if(event.getCode().equals(KeyCode.ESCAPE)) {
								notePaper.setVisible(false);
								 
							}
						}
						
					});
					noteImage.setPickOnBounds(true);
					noteImage.setVisible(true);
					 
					Element noteRoot;
					Element attachment;
					try {
					db = new DataBase(selectedNoteBook);
					noteViewer.getChildren().add(noteImage);
					doc = db.generateDoc();
					noteRoot = doc.createElement("note");
					noteRoot.setAttribute("title", noteTitle.getText());
					attachment = doc.createElement("attachment");
					attachment.setAttribute("src", "");
					nbText = doc.createElement("text");
					nbText.setTextContent(((Note) note).getText());
					background = doc.createElement("background");
					background.setAttribute("color", ((Note)note).getBackgroundColor());
					noteRoot.appendChild(nbText);
					noteRoot.appendChild(background);
					noteRoot.appendChild(attachment);
					db.getRoot().appendChild(noteRoot);
					db.createDatabase(doc);
					saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							// TODO Auto-generated method stub
							((Note)note).setText(notePaper.getText());
							DataBase db = new DataBase(selectedNoteBook);
							Document doc = null;
							try {
							doc = db.generateDoc();
						 
							for(int i = doc.getElementsByTagName("note").getLength() - 1 ; i >= 0 ; i--) {
								org.w3c.dom.Node node = doc.getElementsByTagName("note").item(i);
								if(db.getAttributes(node).getNamedItem("title").getNodeValue().equals(((Note) note).getTitle())) {
									((Element)(node)).setAttribute("title", noteTitle.getText());
									node.getPreviousSibling().setTextContent(label.getText());
								 
								break;
								}
							} 
							db.createDatabase(doc);
							}
							catch(NullPointerException ex) {
								ex.printStackTrace();
							}
						}
					});
					}
					catch(NullPointerException ex) {
						ex.printStackTrace();
					}
					
					break;
				case "to-do":
					noteViewer.getChildren().add(label); 
					 
					note = (T) new toDo(selectedNoteBook , noteTitle.getText() , (ArrayList<String>) list ,  colorPicker.getValue().toString() );
					 
					noteImage = new ImageView("file:to-do.png");
					noteImage.setLayoutX(nLayoutX);
					noteImage.setLayoutY(nLayoutY);
					noteImage.setPreserveRatio(false);
					noteImage.setFitWidth(114); 
					noteImage.setFitHeight(100);
					noteImage.setPickOnBounds(true);
					noteImage.setVisible(true);
		 
					db = new DataBase(selectedNoteBook);
					noteViewer.getChildren().add(noteImage);
					try {
					doc = db.generateDoc();
					Element toDoRoot = doc.createElement("todo");
					toDoRoot.setAttribute("title", noteTitle.getText());
					Element toDoList = doc.createElement("list");
					if(!list.isEmpty())
						for(String work : list) {
							// add date and time later
						 
							toDoList.setTextContent(toDoList.getTextContent() + "," + work);
						}
					background = doc.createElement("background");
					background.setAttribute("color", ((toDo)note).getBackgroundColor());
					db.getRoot().appendChild(toDoRoot);
					toDoRoot.appendChild(toDoList);
					toDoRoot.appendChild(background);
					db.createDatabase(doc);
				 
					}
					catch(NullPointerException ex) {
						ex.printStackTrace();
					}
					
					
					break;
				default:
					note = (T) new Note("","","","","");
					noteImage = new ImageView("file:note.png");
					break;
					
				}
			 
				noteViewer.getChildren().add(delete);
				noteViewer.getChildren().add(close);
				try {
 
   
				 
				delete.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						// TODO Auto-generated method stub
						String css = "-fx-background-color:lightgray;" ;
						Stage window = new Stage();

						window.initModality(Modality.APPLICATION_MODAL);
						window.setTitle("confirm delete");
						window.setWidth(114);
						window.setMinWidth(114);
						window.setResizable(false);
						window.setHeight(300);
						Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
						window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
						window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);
						Label lb = new Label();
						lb.setText("دفترچه حذف خواهد شد");
						Button deleteButton = new Button("تایید");
						Button cancelButton = new Button("منصرف شدم");
						deleteButton.setStyle("-fx-font-family:IRANSansMobile;-fx-background-color:lightskyblue;");
						cancelButton.setStyle("-fx-font-family:IRANSansMobile;");
						cancelButton.setCancelButton(true);
						cancelButton.setOnAction(e->window.close());
						VBox layout = new VBox(15);
						layout.setStyle(css);

						layout.getChildren().addAll(lb,deleteButton,cancelButton);
						layout.setAlignment(Pos.CENTER);
						Scene scene = new Scene(layout);
						deleteButton.setOnMouseClicked(event1 -> {
							// TODO Auto-generated method stub

							// TODO Auto-generated method stub
							noteViewer.getChildren().remove(label);
							noteViewer.getChildren().remove(noteImage);
							noteViewer.getChildren().remove(delete);
							noteViewer.getChildren().remove(close);
							DataBase db = new DataBase(selectedNoteBook);
							Document doc = db.generateDoc();
							doc = db.removeChildById(doc, uniqueId);
							db.createDatabase(doc);
							window.close();


						});
						window.setScene(scene);
						window.showAndWait();

					}

				});
				}
				catch(ClassCastException ex) {
					ex.printStackTrace();
				}
				 
		  
			}
			nLayoutX+=120;
			if(nLayoutX >= noteViewer.getWidth() - 150) {
				nLayoutX = 22;
				nLayoutY += 168;
			}
		}
	} 
	@FXML
	private TextField password;
	private String selectedNoteBook;
	@FXML
	private void newPass(KeyEvent event) {
		try {
		if(event.getCode().equals(KeyCode.ENTER)) {
		DataBase db = new DataBase("file.xml");
		Element elem = db.getElementById(mainController.getLogin_user());
		System.out.println(elem.getNextSibling().getTextContent());
		elem.getNextSibling().setTextContent(password.getText());
		Document doc = db.generateDoc();
		db.createDatabase(doc);
		}
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML private void loadNoteBooks() {
		browseFolder(); 
		File folder = new File( noteBookBrowsedFolderPath);
		File[] listOfFiles = folder.listFiles();
		nbLayoutX = 14;
		nbLayoutY = 25; 
		noteBookViewer.getChildren().clear();
		for(File f : listOfFiles) {
			if(f.getParentFile().getName().equals("notebook") && !f.isDirectory()) {
			DataBase db = new DataBase(f.getPath());
			try {
			Document doc = db.generateDoc();
			String name = doc.getElementsByTagName("name").item(0).getTextContent();
			String path = doc.getElementsByTagName("path").item(0).getAttributes().getNamedItem("src").getNodeValue();
			String imagePath = doc.getElementsByTagName("image").item(0).getAttributes().getNamedItem("src").getNodeValue();
			noteBook book = new noteBook(path,name , "" , imagePath);
			book.setImg_path(imagePath);
			book.setTitle(name);

			if(!hset.contains(book.getPath())) {
				hset.add(book.getPath());
				noteBooks.add(book);
				 
			}
			}
			catch(NullPointerException ex) {
				ex.printStackTrace();
			}
		}
		try {
			Collections.sort( noteBooks);
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		}
			for(noteBook b : noteBooks) {
				
				if(nbLayoutY < left.getHeight() - 130) {
					
					ImageView bookImage = new ImageView("file:notebook.png");
					bookImage.setLayoutX(nbLayoutX);
					bookImage.setLayoutY(nbLayoutY);
					bookImage.setPreserveRatio(false);
					bookImage.setFitWidth(114);
					bookImage.setFitHeight(100);
					bookImage.setPickOnBounds(true);
					bookImage.setPreserveRatio(false);
					bookImage.setVisible(true);
					bookImage.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							// TODO Auto-generated method stub
							noteViewer.setVisible(!noteViewer.isVisible());
							selectedNoteBook = b.getPath();
							
						}
					});
					ImageView close = new ImageView("file:close.png");
					close.setPreserveRatio(false);
					close.setFitWidth(32);
					close.setFitHeight(32);
					
			 
					close.setPickOnBounds(true);
					close.setPreserveRatio(false);
					close.setVisible(true);
					close.setLayoutY(nbLayoutY+10 + bookImage.getFitHeight());
					close.setLayoutX(nbLayoutX+34);
					TextField label = new TextField(b.title);
					label.setStyle("-fx-font-family:IRANSans; -fx-text-fill:black; -fx-background-color:transparent;");
					label.setLayoutX(nbLayoutX+73);
					label.setMaxWidth(bookImage.getFitWidth() - 50);
					label.setPrefWidth(75);
					label.setLayoutY(nbLayoutY+10 + bookImage.getFitHeight());
					noteBookViewer.getChildren().add(close);
					label.setOnKeyPressed(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							// TODO Auto-generated method stub
							if(event.getCode().equals(KeyCode.ENTER)) {
								b.setTitle(label.getText());

							}
						}
					});

					noteBookViewer.getChildren().add(label);
					noteBookViewer.getChildren().add(bookImage);

					ImageView delete = new ImageView("file:trash.png");
					delete.setLayoutX(nbLayoutX+5);
					delete.setPreserveRatio(false);
					delete.setFitWidth(32);
					delete.setVisible(true);
					delete.setFitHeight(32);
					delete.setLayoutY(nbLayoutY+10 + bookImage.getFitHeight());
					noteBookViewer.getChildren().add(delete);
					
					// set observable map 
					noteBookViewer.getProperties().put(  "noteBookClose",close );
					noteBookViewer.getProperties().put( "noteBookImage",bookImage );
					noteBookViewer.getProperties().put( "noteBookDelete", delete );
					noteBookViewer.getProperties().put("noteBookName",label);
					
					// end set observable map 
					
					close.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							// TODO Auto-generated method stub
							// TODO Auto-generated method stub
							noteBookViewer.getChildren().remove(label);
							noteBookViewer.getChildren().remove(bookImage);
							noteBookViewer.getChildren().remove(delete);				
							noteBookViewer.getChildren().remove(close);				

						}
					});
					delete.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							// TODO Auto-generated method stub
							String css = "-fx-background-color:lightgray;" ;
							Stage window = new Stage();

							window.initModality(Modality.APPLICATION_MODAL);
							window.setTitle("confirm delete");
							window.setMinWidth(250);
							Label   lb = new Label();

							lb.setText("دفترچه حذف خواهد شد ، ");
							Button deleteButton = new Button("تایید");
							Button cancelButton = new Button("منصرف شدم");
							deleteButton.setStyle("-fx-font-family:IRANSansMobile;-fx-background-color:lightskyblue;");
							cancelButton.setStyle("-fx-font-family:IRANSansMobile;");
							cancelButton.setCancelButton(true);
							cancelButton.setOnAction(e->window.close());
							VBox layout = new VBox(15);
							layout.setStyle(css);

							layout.getChildren().addAll(lb,deleteButton,cancelButton);
							layout.setAlignment(Pos.CENTER);
							deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

								@Override
								public void handle(MouseEvent event) {
									// TODO Auto-generated method stub
									// TODO Auto-generated method stub
									noteBookViewer.getChildren().remove(label);
									noteBookViewer.getChildren().remove(bookImage);
									noteBookViewer.getChildren().remove(delete);	
									noteBookViewer.getChildren().remove(close);

									new File(b.getPath()).delete();
									window.close();
								}
							});
							Scene scene = new Scene(layout);
							window.setScene(scene);
							window.setMinWidth(114);
							window.setWidth(114);
							window.setHeight(300);
							window.setResizable(false);
							Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
							window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
							window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);

							window.showAndWait();


						}

					});
					nbLayoutX+=205;
				}
				if(nbLayoutX >= noteBookViewer.getWidth() - 150) {
					nbLayoutX = 14;
					nbLayoutY += 168;
				}
			}
		}
	 
	

	@FXML
	private void newNoteBookConfirm() {
		Anim.fadeIn(noteBookViewer, 500);
		if(nbLayoutY < left.getHeight() - 130) {
			String name = noteBookName.getText();
			boolean duplicate = false;
			noteBook book = new noteBook(this.noteBookBrowsedFolderPath + "/" + name + ".xml" , name , "" ,  "file:" + this.browsedImagePath);
			try {
				Collections.sort(noteBooks);
			    duplicate = binarySearch(name , noteBooks);
			}
			catch (NullPointerException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
			if(!duplicate && !name.isEmpty()) {
if(nbLayoutY < left.getHeight() - 130) {
					ImageView bookImage = new ImageView("file:notebook.png");
					bookImage.setLayoutX(nbLayoutX);
					bookImage.setLayoutY(nbLayoutY);
					bookImage.setPreserveRatio(false);
					bookImage.setFitWidth(114);
					bookImage.setFitHeight(100);
					bookImage.setPickOnBounds(true);
					bookImage.setPreserveRatio(false);
					bookImage.setVisible(true);
					bookImage.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							// TODO Auto-generated method stub
							noteViewer.setVisible(!noteViewer.isVisible());
							selectedNoteBook = book.getPath();
							
						}
					});
					ImageView close = new ImageView("file:close.png");
					close.setPreserveRatio(false);
					close.setFitWidth(32);
					close.setFitHeight(32);
					
			 
					close.setPickOnBounds(true);
					close.setPreserveRatio(false);
					close.setVisible(true);
					close.setLayoutY(nbLayoutY+10 + bookImage.getFitHeight());
					close.setLayoutX(nbLayoutX+34);
					TextField label = new TextField(book.title);
					label.setStyle("-fx-font-family:IRANSans; -fx-text-fill:black; -fx-background-color:transparent;");
					label.setLayoutX(nbLayoutX+73);
					label.setMaxWidth(bookImage.getFitWidth() - 50);
					label.setPrefWidth(75);
					label.setLayoutY(nbLayoutY+10 + bookImage.getFitHeight());
					noteBookViewer.getChildren().add(close);
					label.setOnKeyPressed(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							// TODO Auto-generated method stub
							if(event.getCode().equals(KeyCode.ENTER)) {
								book.setTitle(label.getText());

							}
						}
					});

					noteBookViewer.getChildren().add(label);
					noteBookViewer.getChildren().add(bookImage);

					ImageView delete = new ImageView("file:trash.png");
					delete.setLayoutX(nbLayoutX+5);
					delete.setPreserveRatio(false);
					delete.setFitWidth(32);
					delete.setVisible(true);
					delete.setFitHeight(32);
					delete.setLayoutY(nbLayoutY+10 + bookImage.getFitHeight());
					noteBookViewer.getChildren().add(delete);
					
					// set observable map 
					noteBookViewer.getProperties().put(  "noteBookClose",close );
					noteBookViewer.getProperties().put( "noteBookImage",bookImage );
					noteBookViewer.getProperties().put( "noteBookDelete", delete );
					noteBookViewer.getProperties().put("noteBookName",label);
					
					// end set observable map 
					
					close.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							// TODO Auto-generated method stub
							// TODO Auto-generated method stub
							noteBookViewer.getChildren().remove(label);
							noteBookViewer.getChildren().remove(bookImage);
							noteBookViewer.getChildren().remove(delete);				
							noteBookViewer.getChildren().remove(close);				

						}
					});
					delete.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							// TODO Auto-generated method stub
							String css = "-fx-background-color:lightgray;" ;
							Stage window = new Stage();

							window.initModality(Modality.APPLICATION_MODAL);
							window.setTitle("confirm delete");
							window.setMinWidth(250);
							Label   lb = new Label();

							lb.setText("دفترچه حذف خواهد شد ، ");
							Button deleteButton = new Button("تایید");
							Button cancelButton = new Button("منصرف شدم");
							deleteButton.setStyle("-fx-font-family:IRANSansMobile;-fx-background-color:lightskyblue;");
							cancelButton.setStyle("-fx-font-family:IRANSansMobile;");
							cancelButton.setCancelButton(true);
							cancelButton.setOnAction(e->window.close());
							VBox layout = new VBox(15);
							layout.setStyle(css);

							layout.getChildren().addAll(lb,deleteButton,cancelButton);
							layout.setAlignment(Pos.CENTER);
							deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

								@Override
								public void handle(MouseEvent event) {
									// TODO Auto-generated method stub
									// TODO Auto-generated method stub
									noteBookViewer.getChildren().remove(label);
									noteBookViewer.getChildren().remove(bookImage);
									noteBookViewer.getChildren().remove(delete);	
									noteBookViewer.getChildren().remove(close);

									new File(book.getPath()).delete();
									window.close();
								}
							});
							Scene scene = new Scene(layout);
							window.setScene(scene);
							window.setMinWidth(114);
							window.setWidth(300);
							window.setHeight(200);
							window.setResizable(false);
							Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
							window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
							window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);

							window.showAndWait();


						}

					});
					nbLayoutX+=205;
				}
				if(nbLayoutX >= noteBookViewer.getWidth() - 150) {
					nbLayoutX = 14;
					nbLayoutY += 168;
				}
			
			}
		}
		Anim.fadeOut(noteBookSetting, 600);
	}
	@FXML
	private void newNoteBook(){
		if(noteBookSetting.isVisible()) {
			Anim.fadeOut(noteBookSetting,300);
		}
		else{
			Anim.fadeIn(noteBookSetting,300);
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
	public void initialize() {
		userImage.setFitWidth(150);
		userImage.setFitHeight(150);
		noteType.setItems(FXCollections.observableArrayList(FXCollections.observableArrayList(
				"note" , "memo" , "to-do")));

		left.setStyle(" -fx-alignment: center ;\n" + 
				"    -fx-padding: 10 ;");
		Rectangle clip = new Rectangle(
				userImage.getFitWidth(), userImage.getFitHeight()
				);
		userImage.setPreserveRatio(false);
		clip.setArcWidth(150);
		clip.setArcHeight(150);
		userImage.setClip(clip);
		try {
 
			String path = mainController.getProfileImagePath();
			FileInputStream input =  new FileInputStream(path);
			Image image = new Image(input);
			 
		setUserImage(image);
		}
		catch(IllegalArgumentException | FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	 
}
