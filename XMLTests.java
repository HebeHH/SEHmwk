import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import org.xml.sax.SAXException;

public class XMLTests {

	// This function takes in a quiz and checks that the read/write xml functions are consistent by writing it 
	// into XML, reading it back, then writing it to XML again and testing for consistency.
	public static Boolean testXMLConsistency(Quiz q)  throws ParserConfigurationException, SAXException, IOException {
		String myxml = q.getXML();
		Runnable.writeXML("QuizTemplates/Q1.xml", myxml);
		String newxml = Runnable.readQuizXML("QuizTemplates/Q1.xml").getXML();

		return newxml.equals(myxml);
	}


	// This function takes in a quiz and checks that the read/write xml functions are consistent by writing it 
	// into XML, reading it back, then writing it to XML again and testing for consistency.
	public static Boolean testXMLConsistency(AnswerBooklet a)  throws ParserConfigurationException, SAXException, IOException {
		String myxml = a.getXML();
		Runnable.writeXML("AnswerBooklets/A1.xml", myxml);
		String newxml = Runnable.readABXML("AnswerBooklets/A1.xml").getXML();

		return newxml.equals(myxml);
	}

	// Make a quiz and run tests
	public static void main(String [ ] args)  throws ParserConfigurationException, SAXException, IOException {

		// Test an existant answerbooklet (Jane Doe's attempt at the Intro DB quiz) reads in properly:
		AnswerBooklet jane = Runnable.readABXML("AnswerBooklets/JaneIntroDBs.xml");
		// This should correctly 1) form an AnswerBooklet object that 2) correctly gives her grade as 100%
		Boolean reads_correct = (jane.currentGrade() == 100);


		// Setup a quiz
		Quiz q = new Quiz();

		MCQ m1 = new MCQ("What type of database is Postgres?");
		m1.addOption("NoSQL");
		m1.addSolution("Relational");
		MCQ m2 = new MCQ("The classic call to add something to a SQL database is:");
		m2.addOption("INPUT");
		m2.addOption("PUT");
		m2.addSolution("INSERT");
		
		ShortAnswer s1 = new ShortAnswer("What character selects all columns?");
		s1.addSolution("*");
		ShortAnswer s2 = new ShortAnswer("We only want unique values; use SELECT ??????");
		s2.addSolution("DISTINCT");

		LongAnswer l1 = new LongAnswer("When should you use NoSQL databases?");
		
		q.addQuestion(m1);
		q.addQuestion(m2);
		q.addQuestion(s1);
		q.addQuestion(s2);
		q.addQuestion(l1);

		// Setup an answer booklet
		AnswerBooklet josh = new AnswerBooklet(q, "Josh Black");

		// TESTS
		Boolean quiz_xml_works = testXMLConsistency(q);
		Boolean answer_xml_works = testXMLConsistency(josh);
	}
}