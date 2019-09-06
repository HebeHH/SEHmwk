
import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class AnswerBooklet {
	private Double total_marks;
	private Double ungraded_marks;
	private Double student_marks;
	private String student;
	private ArrayList<Answer> answers = new ArrayList<Answer>();

	// Make empty answer booklet for student 'name' based off of
	// quiz template 'qz'
	public AnswerBooklet(Quiz qz, String name) {
		this.student = name;
		this.total_marks = new Double(qz.getPoints());
		this.ungraded_marks = new Double(qz.getPoints());
		this.student_marks = 0.0;

		for (Question q : qz.getQuestions()) {
			this.answers.add(new Answer(q));
		}
	}

	// Build an answer booklet from it's XML representation
	public AnswerBooklet(Element e) {
		this.student = e.getAttribute("student");

		this.total_marks = Double.valueOf(e.getAttribute("total_marks"));
		this.ungraded_marks = Double.valueOf(e.getAttribute("ungraded_marks"));
		this.student_marks = Double.valueOf(e.getAttribute("student_marks"));
		
		NodeList ans = e.getElementsByTagName("Answer");
		for (int i = 0; i < ans.getLength(); i++) {
			Answer q = new Answer( (Element) ans.item(i));
			this.answers.add(q);
		}
	}

	// Return XML representation of answer booklet as string
	public String getXML() {
		String xml = "<AnswerBooklet student='" + this.student +"' total_marks='" + this.total_marks +
					"'  student_marks='" + this.student_marks +"' ungraded_marks='" + this.ungraded_marks +"'>\n";
		xml += "<Student>" + this.student + "</Student>\n";
		for (Answer a : this.answers ) {
			xml += a.getXML();
		}
		xml += "</AnswerBooklet>\n";
		return xml;
	}

	// Give the quiz to the student via the command line interface
	// Garner and save the answers
	public void giveQuiz() {
		Scanner input = new Scanner(System.in);
		for (Answer a: this.answers) {
			a.ask(input);
		}
		// input.close();
	}

	// Grade all questions that can be done automatically
	// Mimics what is done with canvas quizes when they're first submitted
	public void autoGrade() {
		this.ungraded_marks = 0.0;
		this.student_marks = 0.0;

		Optional<Double> tmp;
		for (Answer a : this.answers) {
			 tmp = a.autoGrade();
			 if (tmp.isPresent()) {
			 	student_marks += tmp.get();
			 } else {
			 	ungraded_marks += a.getPoints();
			 }
		}
	}

	// Have teacher grade the test via commandline
	// Anything that can be graded automatically is,
	// everything else is presented to teacher to give answer
	public void teacherGrade() {
		this.ungraded_marks = 0.0;
		this.student_marks = 0.0;

		Optional<Double> tmp;
		for (Answer a : this.answers) {
			 tmp = a.grade();
			 if (tmp.isPresent()) {
			 	student_marks += tmp.get();
			 } else {
			 	ungraded_marks += a.getPoints();
			 }
		}
	}

	// Returns current grade of student as a percentage
	public int currentGrade() {
		if (this.total_marks == 0.0) {
			return 0;
		}
		Double current_grade = (this.student_marks / this.total_marks * 100);
		return current_grade.intValue();
	}

	public String toString() {
		String s = "";
		for (Answer a : this.answers) {
			s += a.toString() + '\n';
		}
		return s;
	}
}


