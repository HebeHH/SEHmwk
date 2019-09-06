
import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

// A LongAnswer is for questions that may have different answers
// It doesn't keep a solution, and must be graded manually
public class LongAnswer extends Question {

	// Initialize LongAnswer with the question and (possibly) points
	public LongAnswer(String q, int p) {
		this.query = q;
		this.points = p;
	}
	public LongAnswer(String q) {
		this.query = q;
		this.points = 1;
	}

	// Initialize LongAnswer from XML object
	public LongAnswer(Element e) {
		this.query = e.getElementsByTagName("Query").item(0).getTextContent();
		if (e.getAttribute("points") != null) {
			this.points = Integer.parseInt(e.getAttribute("points"));
		}
		else {
			this.points = 1;
		} 
	}

	// Cannot verify answer automatically
	public Optional<Double> verifyAnswer(String ans) {
		return Optional.empty();
	}

	// Return XML representation of LongAnswer
	public String getXML() {
		String xml = "<LongAnswer  points='" + Integer.toString(this.points) +"'>\n <Query>" + this.query + "</Query>\n</LongAnswer>\n";
		return xml;
	}
}