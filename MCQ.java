
import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

// Multi choice question class
public class MCQ extends PresetQuestion {
	private ArrayList<String> options = new ArrayList<String>();

	// Initialize MCQ with the question and (possibly) points
	public MCQ(String q, int p) {
		this.query = q;
		this.points = p;
	}
	public MCQ(String q) {
		this.query = q;
		this.points = 1;
	}

	// Initialize MCQ from XML object
	public MCQ(Element e) {
		this.query = e.getElementsByTagName("Query").item(0).getTextContent();
		if (e.getAttribute("points") != null) {
			this.points = Integer.parseInt(e.getAttribute("points"));
		}
		else {
			this.points = 1;
		} 
		for (int i = 0; i < e.getElementsByTagName("Option").getLength(); i++) {
			this.options.add(e.getElementsByTagName("Option").item(i).getTextContent());
		}
		if (e.getElementsByTagName("Solution").getLength() > 0) {
			this.solution = e.getElementsByTagName("Solution").item(0).getTextContent();
		}
	}

	// Add solution and other options to MCQ
	public void addSolution(String sol) {
		this.options.add(sol);
		this.solution = sol;
	}
	public void addOption(String option) {
		this.options.add(option);
	}

	// Return XML representation of MCQ object 
	public String getXML() {
		String xml = "<MCQ  points='" + Integer.toString(this.points) +"'>\n <Query>" + this.query + "</Query" ;
		if (this.solution != null) {
			xml += ">\n <Solution>" + this.solution + "</Solution";
		}
		for (String option : this.options ) {
			xml += ">\n <Option>" + option + "</Option";
		}
		xml += ">\n</MCQ>\n";
		return xml;
	}

	// Return string representation of MCQ (as presented when quiz is given)
	// Overrides the method given in the Question abstract class in order
	// to give the options as well
	public String toString() {
		String s = this.query;
		for (String option : this.options) {
			s += ("\n  - " + option);
		}
		return s;
	}

}