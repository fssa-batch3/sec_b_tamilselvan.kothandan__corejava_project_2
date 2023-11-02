package in.fssa.doc4you.service;

import java.util.Set;

import in.fssa.doc4you.dao.UserDAO;
import in.fssa.doc4you.exception.DAOException;
import in.fssa.doc4you.exception.ServiceException;
import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.model.User;
import in.fssa.doc4you.validator.UserValidator;

/**
 * The UserService class provides methods to manage user-related operations in
 * the system. It allows creating, updating, retrieving, and deleting user
 * information.
 */

public class UserService {
	/**
	 * Creates a new user in the system.
	 *
	 * @param newUser The User object containing the new user's information.
	 * @return The user_id assigned to the newly created user.
	 * @throws ValidationException If the provided user data is invalid.
	 */
	UserDAO userDAO = new UserDAO();

	public int createUser(User newUser) throws ValidationException {

		UserValidator.validateForCreate(newUser);

		UserDAO userObj = new UserDAO();
		int user_id = userObj.createUsers(newUser);
		return user_id;

	}

	/**
	 * Retrieves a set of all users in the system.
	 *
	 * @return A set containing User objects representing all users.
	 */

	public Set<User> getAllUsers() {
		UserDAO userObj = new UserDAO();
		Set<User> userArray = userObj.findAll();
		return userArray;
	}

	/**
	 * Updates user information in the system.
	 *
	 * @param id      The user_id of the user to be updated.
	 * @param newUser The updated User object with new information.
	 * @throws ValidationException If the provided user data is invalid or if the
	 *                             user_id is not found.
	 */
	public static void updateUser(int id, User newUser) throws ValidationException {
		UserValidator.validateForUpdate(id, newUser);
		UserDAO userObj = new UserDAO();

		userObj.updateUsers(id, newUser);
	}

	/**
	 * Deletes a user from the system.
	 *
	 * @param id The user_id of the user to be deleted.
	 * @throws ValidationException If the user_id is invalid or if the user is not
	 *                             found.
	 */
	public void deleteUser(int id) throws ValidationException {
		UserValidator.validateForId(id);
		UserDAO userObj = new UserDAO();
		userObj.delete(id);
	}

	/**
	 * Reactivates a previously deactivated user in the system.
	 *
	 * @param id The user_id of the user to be reactivated.
	 * @throws ValidationException If the user_id is invalid or if the user is not
	 *                             found.
	 */
	public static void reactivateUser(int id) throws ValidationException {
		UserDAO userObj = new UserDAO();
		userObj.reactivateUsers(id);
	}

	/**
	 * Retrieves a user by their user_id.
	 *
	 * @param userId The user_id of the user to be retrieved.
	 * @return The User object representing the requested user.
	 * @throws ValidationException If the provided user_id is invalid or if the user
	 *                             is not found.
	 */
	public static User getByUserId(int userId) throws ValidationException {
		UserValidator.validateForId(userId);
		UserDAO userObj = new UserDAO();
		return userObj.findById(userId);

	}

	/**
	 * Retrieves a user by their email address.
	 *
	 * @param email The email address of the user to be retrieved.
	 * @return The User object representing the requested user.
	 * @throws ValidationException If the provided email address is invalid or if
	 *                             the user is not found.
	 */
	public static User getByUserEmail(String email) throws ValidationException {
		UserValidator.validateForEmail(email);
		UserDAO ud = new UserDAO();
		return ud.findByEmail(email);

	}

	public User loginUser(String email, String password) throws ServiceException, ValidationException, DAOException {
		// Validate the phone number and password
		UserValidator.validateForEmail(email);
		UserValidator.validatePassword(password);

		// Check if a user with the provided phone number exists in the database
		User user = userDAO.findByEmail(email);
		System.out.println(user);

		if (user != null) {

			// If the user exists, check if the provided password matches the stored
			// password
			System.out.println(password);
			if (password.equals(user.getPassword())) {
				// Authentication successful, return the user
				return user;
			} else {
				// Password does not match
				throw new ServiceException("Incorrect Login Credentials");
			}
		} else {
			// User with the provided phone number does not exist
			throw new ServiceException("Incorrect Login Credentials");
		}
	}

}
