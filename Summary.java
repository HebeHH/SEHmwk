import java.util.*;

class Summary{  

	public static void printStatistics(int[] sample) {
		int len = sample.length;
		Arrays.sort(sample);

		System.out.println("The minimum is: " + sample[0]);  
		System.out.println("The maximum is: " + sample[len-1]);  

		int sum = 0;
		for (int num : sample) {
			sum += num;
		}
		System.out.println("The mean is: " + sum/Double.valueOf(len));

		double median;
		if (len % 2 == 1) {
			median = sample[(len-1)/2];
		} else {
			median = (sample[len/2] + sample[(len - 2)/2]) / 2.0;
		}

		System.out.println("The median is: " + median);
	}

	public static void main(String args[]) {  
		int len = args.length;

		if (len == 0) {
			System.out.println("No arguments given.");
		} else {
			int[] int_args = new int[len];
	
			for (int i = 0; i < len; i++) {
				int_args[i] = Integer.parseInt(args[i]);
			}
			printStatistics(int_args);
		}
	}  

}  