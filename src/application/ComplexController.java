package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
 

 

public class ComplexController {
	@FXML
	private ImageView p_image;
	@FXML
	public void initialize() {
		p_image.setClip(new Circle(p_image.getFitWidth(),p_image.getFitHeight(),50));
 
	}
 
}
