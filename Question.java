
import java.util.*;


// The basic element of a quiz is a question.
// All questions must include the query - the actual
// question that is being asked. 
// They can also be worth varying amounts of points.
public abstract class Question extends XMLizable{
	protected String query;
	public int points;

	// String to ask the question; overridden in MCQ
	public String toString() {
		return query;
	}

	// Ask the question when giving the quiz
	public void askQuestion() {
		System.out.println(this.toString());
	};

	// Questions should include automatic verification. 
	public abstract Optional<Double> verifyAnswer(String ans);
}