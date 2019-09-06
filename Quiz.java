import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

// Quiz template
// Holds questions; used to create individualized answer booklets
public class Quiz extends XMLizable {
	private int total_marks;
	private ArrayList<Question> questions = new ArrayList<Question>();

	// New Quiz
	public Quiz() {
		this.total_marks = 0;
	}

	// Build quiz, including all questions, from it's XML representation
	public Quiz(Element e) {
		this.total_marks = 0;
		
		NodeList mcqs = e.getElementsByTagName("MCQ");
		for (int i = 0; i < mcqs.getLength(); i++) {
			MCQ q = new MCQ( (Element) mcqs.item(i));
			this.questions.add(q);
		}
		NodeList sqs = e.getElementsByTagName("ShortAnswer");
		for (int i = 0; i < sqs.getLength(); i++) {
			ShortAnswer q = new ShortAnswer( (Element) sqs.item(i));
			this.questions.add(q);
		}
		NodeList lqs = e.getElementsByTagName("LongAnswer");
		for (int i = 0; i < lqs.getLength(); i++) {
			LongAnswer q = new LongAnswer( (Element) lqs.item(i));
			this.questions.add(q);
		}
	}

	// Add a question; both to list and it's marks to the total 
	// marks available on this quiz
	public void addQuestion(Question q) {
		this.questions.add(q);
		this.total_marks += q.points;
	}

	// Return all questions (primarily used to build AnswerBooklet)
	public ArrayList<Question> getQuestions() {
		return this.questions;
	}

	// Return the total marks available on the quiz
	public int getPoints() {
		return this.total_marks;
	}

	// Return XML representation of entire quiz
	public String getXML() {
		String xml = "<Quiz  points='" + Integer.toString(this.total_marks) +"'>\n";
		for (Question q : this.questions ) {
			xml += q.getXML();
		}
		xml += "</Quiz>\n";
		return xml;
	}

	public String toString() {
		String s = "";
		for (Question q : this.questions) {
			s += q.toString() + '\n';
		}
		return s;
	}
}