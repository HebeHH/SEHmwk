
import java.util.*;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class ShortAnswer extends PresetQuestion {

	// Initialize ShortAnswer with the question and (possibly) points
	public ShortAnswer(String q, int p) {
		this.query = q;
		this.points = p;
	}
	public ShortAnswer(String q) {
		this.query = q;
		this.points = 1;
	}

	// Initialize ShortAnswer from XML object
	public ShortAnswer(Element e) {
		this.query = e.getElementsByTagName("Query").item(0).getTextContent();
		if (e.getAttribute("points") != null) {
			this.points = Integer.parseInt(e.getAttribute("points"));
		}
		else {
			this.points = 1;
		} 
		if (e.getElementsByTagName("Solution").getLength() > 0) {
			this.solution = e.getElementsByTagName("Solution").item(0).getTextContent();
		}
	}

	// Add the solution of the short answer
	public void addSolution(String sol) {
		this.solution = sol;
	}

	// Return XML representation of ShortAnswer object 
	public String getXML() {
		String xml = "<ShortAnswer  points='" + Integer.toString(this.points) +"'>\n <Query>" + this.query + "</Query" ;
		if (this.solution != null) {
			xml += ">\n <Solution>" + this.solution + "</Solution";
		}
		xml += ">\n</ShortAnswer>\n";
		return xml;
	}

}