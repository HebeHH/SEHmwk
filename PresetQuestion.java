
import java.util.*;

// Preset Questions are a type of question where the solution
// is constant: ie not essay based. These are different in that
// the solution can be stored so answers can be graded automatically.
public abstract class PresetQuestion extends Question {
	protected String solution = null;

	// Preset questions have solutions
	public abstract void addSolution(String sol);

	// Automatically verify whether a string answer matches the 
	// stored solution. Ignore case.
	public Optional<Double> verifyAnswer(String ans) {
		if (this.solution == null) {
			return Optional.empty();
		}
		else if (ans.toLowerCase().equals(this.solution.toLowerCase())) {
			return Optional.of(1.0);
		} else { 
			return Optional.of(0.0); 
		}
	}
}