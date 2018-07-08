package application;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.xpath.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
public final class DataBase {
	//constructor
	DataBase(String username , String password , String path){
		this.username = username;
		this.password = password;
		this.path = path;
	}
	DataBase(String path){
		this.path = path;
	}
	//setter
	private void setUserName(String username){
		this.username = username;
	}
	private void setPassWord(String password){
		this.password = password;

	}
	private void setPath(String path){
		this.path = path;
	}
	// getter
	private String getUserName(){
		return this.username;
	}
	private String getPassWord(){
		return this.password ;

	}
	private String getPath(){
		return	this.path ;
	}
	boolean userExists(String username , String password) throws ParserConfigurationException, InterruptedException, SAXException, IOException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document doc = docBuilder.parse(path);
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		Element element = null;

		try {
			element = (Element) xpath.evaluate("//*[@id='" + username + "']", doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (element!=null && element.getTextContent().equals(password));

	}
	private String username;
	private String password;;
	private String path;
	void createDatabase() throws FileNotFoundException, IOException, TransformerException, SAXException, XPathExpressionException  {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc;
			Element rootElement;
			File file;
			doc = docBuilder.newDocument();
			if(new File(path).exists()) {

				doc = docBuilder.parse(path);
				rootElement = doc.getDocumentElement();
			}
			else {
				doc = docBuilder.newDocument();
				// Build DOM tree for input XML
				rootElement = doc.createElement("database");
				doc.appendChild(rootElement);
			} 
			// set root element
			// create new elements
			Element user = doc.createElement("user");
			rootElement.appendChild(user);
			user.setAttribute("id", username);
			Element pass =  doc.createElement("password");
			pass.setTextContent(password);
			rootElement.appendChild(user);
			user.appendChild(pass);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			file = new File(path);

			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	}
}
