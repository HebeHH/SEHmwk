import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import org.xml.sax.SAXException;

public class GiveQuizExample {

	// Make a quiz and run tests
	public static void main(String [ ] args)  throws ParserConfigurationException, SAXException, IOException {

		System.out.println("\nThis Intro to Postgres quiz is given then automatically graded:\n");

		// Read in a quiz:
		Quiz dbs = Runnable.readQuizXML("QuizTemplates/IntroDBsQuiz.xml");
		
		// Setup an answer booklet and give exam
		AnswerBooklet tester_dbs = new AnswerBooklet(dbs, "Testing Booklet");
		tester_dbs.giveQuiz();
		tester_dbs.autoGrade();
		System.out.println("\nYou scored "+String.valueOf(tester_dbs.currentGrade()) +"% on this exam\n");

		// Save finished quiz (with answer et al) to folder
		Runnable.writeXML("AnswerBooklets/tester_dbs.xml", tester_dbs.getXML());

		System.out.println("\n\nThis Intro to Software Engineering quiz requires some grading from the Professor:\n");

		// Read in a quiz:
		Quiz intro = Runnable.readQuizXML("QuizTemplates/IntroQuiz.xml");
		
		// Setup an answer booklet and give exam
		AnswerBooklet tester_intro = new AnswerBooklet(intro, "Testing Booklet");
		tester_intro.giveQuiz();
		tester_intro.teacherGrade();
		System.out.println("\nYou scored "+String.valueOf(tester_intro.currentGrade()) +"% on this exam\n");

		// Save finished quiz (with answer et al) to folder
		Runnable.writeXML("AnswerBooklets/tester_intro.xml", tester_intro.getXML());
	}
}