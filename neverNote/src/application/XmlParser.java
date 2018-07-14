package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


abstract class XmlParser  {
	private String path;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPath(String path) {
		this.path = path;
	}
	XmlParser(String path , String id){
		this.path = path;
		this.id = id;
		this.root = null;
	}
	XmlParser(String path){
		this.path = path;
		this.root = null;
	}
	XmlParser(){};
	public String getPath() {
		return this.path;
	}
 Document removeChildById(Document doc ,String id) {
		try {
		for(int i = doc.getElementsByTagName("*").getLength()-1 ;  i > 0    ; i--) {
			Node node =  doc.getElementsByTagName("*").item(i);
				NamedNodeMap attrs = node.getAttributes();
				if(attrs.getNamedItem("id")!=null) {
					System.out.println("id:" + id + "elem id:" + attrs.getNamedItem("id"));
				if(attrs.getNamedItem("id").getNodeValue().equals(id)) {
					 node.getParentNode().removeChild(node);
					break;
				}
				 
				}
		}
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		return doc;
	}
 public NamedNodeMap getAttributes(Node node) {
	 NamedNodeMap attrs = node.getAttributes();
	 return attrs;
	 
 }
 public Element getElementByAttribute(Document doc , String attribute , String value) {
 try {
		for(int i = doc.getElementsByTagName("*").getLength()-1 ;  i > 0    ; i--) {
			Node node =  doc.getElementsByTagName("*").item(i);
				NamedNodeMap attrs = node.getAttributes();
				if(attrs.getNamedItem(attribute)!=null) {
				if(attrs.getNamedItem(attribute).getNodeValue().equals(value)) {
					return (Element) node;
				}
				 
				}
		}
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
return null;
 
 }
	public Element getElementById(String id) {
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder ;
	Document doc = null;
	Element element = null;
	try {
		docBuilder = docFactory.newDocumentBuilder();
		if(new File(getPath()).exists())
		doc = docBuilder.parse(getPath());
		else {
			element = null;
			return element;
		}
	} catch (SAXException | IOException | ParserConfigurationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	XPathFactory xpathFactory = XPathFactory.newInstance();
	XPath xpath = xpathFactory.newXPath();
	 

	try {
		element = (Element) xpath.evaluate("//*[@id='" + id + "']", doc, XPathConstants.NODE);
	} catch (XPathExpressionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return element;
	}
	private Element root;
	Document generateDoc()  {
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
				root = doc.createElement("root");
				doc.appendChild(root);
			} 
		}
		catch(  NullPointerException | IOException | ParserConfigurationException | SAXException ex) {
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
 
		
