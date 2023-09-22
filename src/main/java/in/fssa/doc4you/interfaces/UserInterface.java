package in.fssa.doc4you.interfaces;

import in.fssa.doc4you.model.User;
import in.fssa.doc4you.model.UserEntity;




/**
 * The UserInterface interface extends the Base interface and provides additional methods specific to user-related operations.
 * Classes implementing this interface are expected to provide concrete implementations for these methods.
 */
public interface UserInterface extends Base<User> {

	 /**
     * Retrieves a user entity by their email address.
     *
     * @param email The email address of the user to be retrieved.
     * @return The UserEntity object corresponding to the provided email address.
     */
	public abstract UserEntity findByEmail(String  email);
	
	 /**
     * Counts the number of user entities.
     *
     * @return The total count of user entities available in the system.
     */
	public abstract int count();

}