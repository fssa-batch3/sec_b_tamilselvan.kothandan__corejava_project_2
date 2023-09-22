package in.fssa.doc4you.util;

import in.fssa.doc4you.exception.ValidationException;

public class NumberUtil {
	public static void rejectIfInvalidInteger(int input , String inputName) throws ValidationException {
		if(input<0) {
			throw new ValidationException(inputName.concat(" cannot be negative"));
		}
	}
}
