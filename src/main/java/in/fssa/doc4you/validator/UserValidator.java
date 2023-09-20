package in.fssa.doc4you.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.doc4you.dao.UserDAO;
import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.model.User;
import in.fssa.doc4you.util.StringUtil;

/**
 * The UserValidator class provides methods to validate user data for various
 * operations. It ensures that the user data is in the correct format and meets
 * the required criteria.
 */
public class UserValidator {

	/**
	 * Validates user data before creating a new user.
	 *
	 * @param user The User object containing user data to be validated.
	 * @throws ValidationException If the user data is invalid or doesn't meet the
	 *                             required criteria.
	 */
	public static void validateForCreate(User user) throws ValidationException {
		if (user == null) {
			throw new ValidationException("User cannot be null");
		}
		if (user.getId() < 0) {
			throw new ValidationException("id cannot be negative");
		}

		UserDAO userDAO = new UserDAO();
		User user1 = userDAO.findByEmail(user.getEmail());
		if (user1 != null) {
			throw new ValidationException("User already exists");
		}

		StringUtil.rejectIfInvalidString(user.getEmail(), "email");
		StringUtil.rejectIfInvalidString(user.getPassword(), "password");
		StringUtil.rejectIfInvalidString(user.getFirstName(), "first name");
		StringUtil.rejectIfInvalidString(user.getLastName(), "last name");

		Pattern pattern1 = Pattern.compile("^[A-Za-z\\s'-]+$");
		Matcher matcher1 = pattern1.matcher(user.getFirstName());
		if (matcher1.matches() == false) {
			throw new ValidationException("first name should contain only alphabets not numbers and symbols");
		}

		Pattern pattern2 = Pattern.compile("^[A-Za-z\\s'-]+$");
		Matcher matcher2 = pattern2.matcher(user.getLastName());
		if (matcher2.matches() == false) {
			throw new ValidationException("last name should contain only alphabets not numbers and symbols");
		}

		Pattern pattern3 = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
		Matcher matcher3 = pattern3.matcher(user.getEmail());
		if (matcher3.matches() == false) {
			throw new ValidationException("email must contain lowercase letters followed by '@' and '.'");
		}

		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^*&+=])(?=\\S+$).{8,}$";
		Pattern compiledPattern = Pattern.compile(pattern);
		Matcher matcher = compiledPattern.matcher(user.getPassword());

		if (!matcher.matches()) {
			throw new ValidationException(
					"Password should contain the combination of uppercase , lowercase , numbers and symbols");
		}

	}

	/**
	 * 
	 * @param id
	 * @param user
	 * @throws ValidationException
	 */
	public static void validateForUpdate(int id, User user) throws ValidationException {
		if (user == null) {
			throw new ValidationException("user cannot be null or empty");
		}
		if (user.getId() < 0) {
			throw new ValidationException("id cannot be negative");
		}

		UserDAO userDAO = new UserDAO();
		User user1 = userDAO.findById(id);
		if (user1 == null) {
			throw new ValidationException("User doesn't exists");
		}

		StringUtil.rejectIfInvalidString(user.getPassword(), "password");
		StringUtil.rejectIfInvalidString(user.getFirstName(), "first name");
		StringUtil.rejectIfInvalidString(user.getLastName(), "last name");

		Pattern pattern1 = Pattern.compile("^[A-Za-z\\s'-]+$");
		Matcher matcher1 = pattern1.matcher(user.getFirstName());
		if (matcher1.matches() == false) {
			throw new ValidationException("first name should contain only alphabets not numbers and symbols");
		}

		Pattern pattern2 = Pattern.compile("^[A-Za-z\\s'-]+$");
		Matcher matcher2 = pattern2.matcher(user.getLastName());
		if (matcher2.matches() == false) {
			throw new ValidationException("last name should contain only alphabets not numbers and symbols");
		}

		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^*&+=])(?=\\S+$).{8,}$";
		Pattern compiledPattern = Pattern.compile(pattern);
		Matcher matcher = compiledPattern.matcher(user.getPassword());

		if (!matcher.matches()) {
			throw new ValidationException(
					"Password should contain the combination of uppercase , lowercase , numbers and symbols");
		}
	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void validateForId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("id cannot be negative");
		}
		UserDAO userDAO = new UserDAO();
		User user1 = userDAO.findById(id);
		if (user1 == null) {
			throw new ValidationException("User doesn't exists");
		}

	}

	/**
	 * 
	 * @param email
	 * @throws ValidationException
	 */
	public static void validateForEmail(String email) throws ValidationException {
		StringUtil.rejectIfInvalidString(email, "email");

		Pattern pattern1 = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
		Matcher matcher1 = pattern1.matcher(email);
		if (matcher1.matches() == false) {
			throw new ValidationException("email must contain lowercase letters followed by '@' and '.'");
		}
	}

	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

	public static void validate(User newUser) throws ValidationException {

		if (!validatePassword(newUser.getPassword())) {
			System.out.println(newUser.getPassword() + "password");
			throw new ValidationException("Password must be at least 8 characters ex: Njcat#10");
		}

		StringUtil.rejectIfInvalidString(newUser.getPassword(), "Password");
	}

	public static boolean validatePassword(String password) throws ValidationException {

		if (StringUtil.isInvalidString(password)) {
			throw new ValidationException("Invalid password");
		}

		if (!password.matches(PASSWORD_PATTERN)) {
			throw new ValidationException("Password must be at least 8 characters and meet specific criteria");
		}

		return true;
	}

}