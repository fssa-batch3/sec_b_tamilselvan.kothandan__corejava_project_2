package in.fssa.doc4you.util;

import java.util.Random;

public class EmailGenerator {
	/**
	 * 
	 * @return email
	 */
	public static String generate() {
		Random r = new Random();
		
		StringBuilder sample = new StringBuilder();
		
		int length = 6+r.nextInt(5);
		
		for(int i=0;i<length;i++) {
			char ch = (char)(97+r.nextInt(26));
			sample.append(ch);
		}
		
		String domain = "gmail.com";
		
		String email = sample.toString()+"@"+domain;
		
		return email;
	}
	
	public static void main(String[] args) {
		System.out.println(EmailGenerator.generate());
	}

	
}
