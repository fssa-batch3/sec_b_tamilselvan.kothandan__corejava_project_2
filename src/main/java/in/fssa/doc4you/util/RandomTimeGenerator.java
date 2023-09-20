package in.fssa.doc4you.util;

import java.time.LocalTime;
import java.util.Random;

public class RandomTimeGenerator {
	public static int rannumber() {
		int minValue = 0;
		int maxValue = 55;
		int range = ((maxValue-minValue)+1)/5;
		
		Random random = new Random();
		
		int number = random.nextInt(range)*5;
		return number;
		
	}
	
	public static LocalTime generateTime() {
		Random random = new Random();
		long hour = random.nextLong(11);
		long minute = random.nextLong(rannumber());
		LocalTime time = LocalTime.of(9, 0, 0).plusHours(hour).plusMinutes(minute);
		return time;
		
	}
	
	public static void main(String[] main) {
		System.out.println(generateTime());
	}
}
