import java.util.*;

class Summary{  

	// Print Summary statistics
	// Input: array of ints (len > 0)
	// Side effects: prints min, max, mean, median
	// Return: None
	public static void printStatistics(int[] sample) {
		int len = sample.length;

		// Sort array to easily print min and max based on position
		Arrays.sort(sample);
		System.out.println("The minimum is: " + sample[0]);  
		System.out.println("The maximum is: " + sample[len-1]);  

		// Add all elements and divide by n to get mean
		int sum = 0;
		for (int num : sample) {
			sum += num;
		}
		System.out.println("The mean is: " + sum/Double.valueOf(len));

		// Median is at the middle of the sorted array
		// Either the middle value for an odd array
		// or the average of the middle values for an even array
		double median;
		if (len % 2 == 1) {
			median = sample[(len-1)/2];
		} else {
			median = (sample[len/2] + sample[(len - 2)/2]) / 2.0;
		}
		System.out.println("The median is: " + median);
	}

	// main function
	// input: cmd line integer arguments
	// side effects: print summary statistics if ints given
	// return: none
	// issues: will throw error if non-int arguments given.
	public static void main(String args[]) {  
		int len = args.length;

		// must have some values to summarize
		if (len == 0) {
			System.out.println("No arguments given.");
		} else {
			// user inputs read as strings
			// convert to int and store in int array
			int[] int_args = new int[len];
			for (int i = 0; i < len; i++) {
				int_args[i] = Integer.parseInt(args[i]);
			}

			// print statistics
			printStatistics(int_args);
		}
	}  

}  