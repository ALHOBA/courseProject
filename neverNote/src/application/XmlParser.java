package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class XmlParser  {
	private String path;
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setPath(String path) {
		this.path = path;
	}
	XmlParser(String path , String token){
		this.path = path;
		this.token = token;
	}
	XmlParser(String path){
		this.path = path;
	}
	XmlParser(){};
	public String getPath() {
		return this.path;
	}
	public Element getElementById(String username) {
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder ;
	Document doc = null;
	try {
		docBuilder = docFactory.newDocumentBuilder();
		doc = docBuilder.parse(getPath());
	} catch (SAXException | IOException | ParserConfigurationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	XPathFactory xpathFactory = XPathFactory.newInstance();
	XPath xpath = xpathFactory.newXPath();
	Element element = null;

	try {
		element = (Element) xpath.evaluate("//*[@id='" + token + "']", doc, XPathConstants.NODE);
	} catch (XPathExpressionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return element;
	}
	private Element root;
	Document generateDoc(String path , Element root) throws FileNotFoundException, IOException, TransformerException, SAXException, XPathExpressionException  {
		Document doc = null;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
			if(new File(path).exists()) {
				doc = docBuilder.parse(path);
				root = doc.getDocumentElement();
			}
			else {
				doc = docBuilder.newDocument();
				root = doc.createElement("database");
				doc.appendChild(root);
			} 
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		this.setRoot(root);
		return doc;
}
	public Element getRoot() {
		return root;
	}
	public void setRoot(Element root) {
		this.root = root;
	}
}
 
		
