package application;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.xpath.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
public  class DataBase  extends XmlParser{
	//constructor
	DataBase(String username , String password , String path){
		super(path , username);
		this.username = username;
		this.password = password;
	}
	DataBase(String path){
		super(path);
	}
	
	//setter
	public void setUserName(String username){
		
		this.username = username;
	}
	public void setPassWord(String password){
		this.password = password;

	}
	public void setPath(String path){
		setPath(path);
	}
	// getter
	public String getUserName(){
		return this.username;
	}
	public String getPassWord(){
		return this.password ;

	}
 
	
	boolean userExists(String username)  {
		Element elem = getElementById(username);
		return(elem!=null);
	}
	boolean passwordMatches(String password) {
		Element elem = getElementById(username);
		if(elem==null) return false;
		return (elem.getTextContent().equals(password));
	}
	private String username;
	private String password;

	boolean createDatabase(Document doc)   { 
		File file;
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
		 
		DOMSource source = new DOMSource(doc);
		file = new File(getPath());
		StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 return true;
	}
}
