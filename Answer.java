
import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

enum Status {
  CREATED,
  ASKED,
  ANSWERED,
  GRADED
}

public class Answer  {
	private Question quest;
	private Status status;
	private String response;
	private double grade;

	// Initialize answer object with a question
	public Answer(Question q) {
		this.quest = q;
		this.status = Status.CREATED;
	}	

	// Initialize answer object from XML element
	public Answer(Element e) {
		if (e.getElementsByTagName("MCQ").getLength() > 0) {
			this.quest = new MCQ( (Element) e.getElementsByTagName("MCQ").item(0));
		} else if (e.getElementsByTagName("ShortAnswer").getLength() > 0) {
			this.quest = new ShortAnswer( (Element) e.getElementsByTagName("ShortAnswer").item(0));
		} else {
			this.quest = new LongAnswer( (Element) e.getElementsByTagName("LongAnswer").item(0));
		}
		this.grade = Double.valueOf(e.getElementsByTagName("Grade").item(0).getTextContent());
		if (e.getElementsByTagName("Response").getLength() > 0) {
			this.response = e.getElementsByTagName("Response").item(0).getTextContent();
		}
		this.status = Status.valueOf(e.getAttribute("status"));
		
	}

	// Return XML representation of answer as a string
	public String getXML() {
		String xml = "<Answer  status='" + this.status.name() +"'>\n";
		if (this.response != null) {
			xml += "<Response>" + this.response + "</Response>\n";
		}
		xml += "<Grade>" + this.grade + "</Grade>\n";
		xml += this.quest.getXML();
		xml += "</Answer>\n";
		return xml;
	}


	// Ask question and receive user input
	public void ask(Scanner input) {
		this.quest.askQuestion();
		this.status = Status.ASKED;

		while (!input.hasNext()) {}

		this.response = input.next();
		this.status = Status.ANSWERED;
	}

	// Get a grade from the teacher
	private void teacherGrade() {
		// Tell teacher the question/answer combo
		System.out.println("When asked");
		this.quest.askQuestion();
		if (this.status == Status.ANSWERED || this.status == Status.GRADED) {
			System.out.println("They answered:");
			System.out.println(this.response);
		} else {
			System.out.println("They failed to answer.");
		}
		// Get grade
		Scanner input = new Scanner(System.in);
		boolean success = false;
		Double g = 0.0;
		while (!success) {
			System.out.println("Please give them a decimal grade between 1 and 0 (inclusive):");
			g = input.nextDouble();
			success = (g >= 0.0 && g <= 1.0);
		}
		this.grade = g * this.quest.points;
		this.status = Status.GRADED;
		input.close();
	}


	// Return an optional with the marks received on this question,
	//  if unable to grade, return empty.
	public Optional<Double> autoGrade() {
		// If already graded, return set grade
		if (this.status == Status.GRADED) {
			return Optional.of(this.grade);
		} 
		// If not graded, try to grade automatically
		// If the question isn't set up to auto-grade, 
		// return empty
		else if (this.status == Status.ANSWERED) {
			Optional<Double> g = this.quest.verifyAnswer(this.response);
			if (g.isPresent()) {
				this.grade = g.get() * this.quest.points;
				this.status = Status.GRADED;
				return Optional.of(this.grade);
			} else {
				return Optional.empty();
			}
		}
		// if not answered yet, return empty
		else {
			return Optional.empty();
		}
	}



	// Return an optional with the marks received on this question,
	//  if unable to grade, return empty.
	public Optional<Double> grade() {
		if (this.status == Status.ANSWERED) {
			Optional<Double> g = this.quest.verifyAnswer(this.response);
			if (g.isPresent()) {
				this.grade = g.get() * this.quest.points;
				this.status = Status.GRADED;
				
			} else {
				this.teacherGrade();
			}
		}
		if (this.status == Status.GRADED) {
			return Optional.of(this.grade);
		} else {
			return Optional.empty();
		}
		
	}

	//  get number of points is worth
	public int getPoints() {
		return this.quest.points;
	}

	// toString method
	public String toString() {
		String s = "Q: " + this.quest.toString();
		if (this.response != null) {
			s += "A: " + this.response;
		}
		if (this.status == Status.GRADED) {
			s += "\nGrade: " + Double.toString(this.grade);
		}
		return s;
	}

}