package in.fssa.doc4you.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.doc4you.dao.UserDAO;
import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.model.User;
import in.fssa.doc4you.util.StringUtil;



/**
 * The UserValidator class provides methods to validate user data for various operations.
 * It ensures that the user data is in the correct format and meets the required criteria.
 */
public class UserValidator {


	 /**
     * Validates user data before creating a new user.
     *
     * @param user The User object containing user data to be validated.
     * @throws ValidationException If the user data is invalid or doesn't meet the required criteria.
     */
	public static void validateForCreate(User user) throws ValidationException {
		
		// Check if the user object is null
		if(user == null) {
			throw new ValidationException("User cannot be null");
		}
		
		// Check if user ID is negative
		if(user.getId()<0) {
			throw new ValidationException("id cannot be negative");
		}

		// Check if user with the same email already exists
		UserDAO userDao = new UserDAO();
		User user1 = userDao.findByEmail(user.getEmail());
		if(user1!=null) {
			throw new ValidationException("User already exists");
		}
        // Validate email, password, first name, and last name using StringUtil

		StringUtil.rejectIfInvalidString(user.getEmail(), "email");
		StringUtil.rejectIfInvalidString(user.getPassword(), "password");
		StringUtil.rejectIfInvalidString(user.getFirstName(), "first name");
		StringUtil.rejectIfInvalidString(user.getLastName(), "last name");
		
		
        // Validate email format using regex pattern

		Pattern ptn2 = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
		Matcher mtch2 = ptn2.matcher(user.getEmail());
		if(mtch2.matches()==false) {
			throw new ValidationException("email doesn't match the required format");
		}
        // Validate password format using regex pattern

		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^*&+=])(?=\\S+$).{8,}$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(user.getPassword());

        if (!matcher.matches()) {
            throw new ValidationException("Password doesn't match the required format");
        }
		
		
	}
	
	
	 /**
     * Validates user data before updating user information.
     *
     * @param id   The user_id of the user being updated.
     * @param user The updated User object with new information.
     * @throws ValidationException If the user data is invalid, doesn't meet the required criteria, or if the user doesn't exist.
     */
	public static void validateForUpdate(int id,User user) throws ValidationException{
		if(user == null) {
			throw new ValidationException("user cannot be null or empty");
		}
		if(user.getId()<0) {
			throw new ValidationException("id cannot be negative");
		}
		
		
		UserDAO userDao = new UserDAO();
		User user1 = userDao.findById(id);
		if(user1==null) {
			throw new ValidationException("User doesn't exists");
		}
		
		StringUtil.rejectIfInvalidString(user.getPassword(), "password");
		StringUtil.rejectIfInvalidString(user.getFirstName(), "first name");
		StringUtil.rejectIfInvalidString(user.getLastName(), "last name");
		

		
		
		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^*&+=])(?=\\S+$).{8,}$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(user.getPassword());

        if (!matcher.matches()) {
            throw new ValidationException("Password doesn't match the required format");
        }
	}
	
	
	 /**
     * Validates user ID before performing operations that require user identification.
     *
     * @param id The user_id to be validated.
     * @throws ValidationException If the user_id is negative or if the user doesn't exist.
     */
	public static void validateForId(int id) throws ValidationException {
		if(id <=0) {
			throw new ValidationException("id cannot be negative");
		}
		UserDAO userDao = new UserDAO();
		User user1 = userDao.findById(id);
		if(user1==null) {
			throw new ValidationException("User doesn't exists");
		}
		
	}
	
	
	/**
	 * 
	 * @param email
	 * @throws ValidationException
	 */
	public static void validateForEmail(String email) throws ValidationException{
		StringUtil.rejectIfInvalidString(email, "email");
		
		Pattern ptn2 = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
		Matcher mtch2 = ptn2.matcher(email);
		if(mtch2.matches()==false) {
			throw new ValidationException("email doesn't match the required format");
		}
	}
	
	

}