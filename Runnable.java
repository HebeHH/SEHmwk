import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import org.xml.sax.SAXException;

public class Runnable {

	// Writes a string to a file
	public static void writeXML(String filename, String myxml) {
		try{    
           FileWriter fw = new FileWriter(filename);   
           fw.write(myxml);    
           fw.close();    
          } catch (Exception e) { System.out.println(e); }   
	}

	// Reads an XML file and returns the root element
	public static Element readXML(String filename)  throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(filename));
		document.getDocumentElement().normalize();

		Element root = document.getDocumentElement();
		return root;
	}

	// Gets root of an XML that represents a quiz, then passes that to the Quiz constructor
	// which builds a quiz object from the DOM
	public static Quiz readQuizXML(String filename)  throws ParserConfigurationException, SAXException, IOException {
		Quiz quiz = new Quiz(readXML(filename));
		return quiz;
	}

	// Gets root of an XML that represents an answer booklet, then passes that to the  constructor
	// which builds a AnswerBooklet object from the DOM
	public static AnswerBooklet readABXML(String filename)  throws ParserConfigurationException, SAXException, IOException {
		AnswerBooklet ansb = new AnswerBooklet(readXML(filename));
		return ansb;
	}

	// This function takes in a quiz and checks that the read/write xml functions are consistent by writing it 
	// into XML, reading it back, then writing it to XML again and testing for consistency.
	public static Boolean testXMLConsistency(Quiz q)  throws ParserConfigurationException, SAXException, IOException {
		String myxml = q.getXML();
		writeXML("QuizTemplates/Q1.xml", myxml);
		String newxml = readQuizXML("QuizTemplates/Q1.xml").getXML();

		return newxml.equals(myxml);
	}


	// This function takes in a quiz and checks that the read/write xml functions are consistent by writing it 
	// into XML, reading it back, then writing it to XML again and testing for consistency.
	public static Boolean testXMLConsistency(AnswerBooklet a)  throws ParserConfigurationException, SAXException, IOException {
		String myxml = a.getXML();
		writeXML("AnswerBooklets/A1.xml", myxml);
		String newxml = readABXML("AnswerBooklets/A1.xml").getXML();

		return newxml.equals(myxml);
	}

	// Make a quiz and run tests
	public static void main(String [ ] args)  throws ParserConfigurationException, SAXException, IOException {

		// Setup a quiz
		Quiz q = new Quiz();

		MCQ m1 = new MCQ("This course is taught in:");
		m1.addSolution("Java");
		m1.addOption("Python");
		m1.addOption("C");
		MCQ m2 = new MCQ("There is no iteration in:");
		m2.addOption("Scrum");
		m2.addSolution("Waterfall");
		m2.addOption("XP");

		
		ShortAnswer s1 = new ShortAnswer("The course code is:");
		s1.addSolution("YSC3232");
		ShortAnswer s2 = new ShortAnswer("Our current class project is a:");
		s2.addSolution("Planner");
		ShortAnswer s3 = new ShortAnswer("The Prof is:");
		s3.addSolution("Bruno Bodin");

		LongAnswer l1 = new LongAnswer("What is Software Engineering?");
		
		q.addQuestion(m1);
		q.addQuestion(m2);
		q.addQuestion(s1);
		q.addQuestion(s2);
		q.addQuestion(s3);
		q.addQuestion(l1);

		String myxml = q.getXML();
		writeXML("QuizTemplates/IntroQuiz.xml", myxml);

		// Setup an answer booklet
		AnswerBooklet john = new AnswerBooklet(q, "John Doe");

		// TESTS
		Boolean quiz_xml_works = testXMLConsistency(q);
		Boolean answer_xml_works = testXMLConsistency(john);
	}
}