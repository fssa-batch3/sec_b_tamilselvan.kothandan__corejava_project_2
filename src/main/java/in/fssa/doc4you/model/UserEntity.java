package in.fssa.doc4you.model;


/**
 * The abstract class UserEntity serves as a blueprint for representing user-related entities in the system.
 * It provides common properties and methods that user entities should have.
 */
public abstract class UserEntity implements Comparable<UserEntity> {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int id;
	private boolean isActive = true;

	
	 /**
     * Gets the first name of the user entity.
     *
     * @return The first name of the user.
     */
	
	public String getFirstName() {
		return firstName;
	}

	/**
     * Sets the first name of the user entity.
     *
     * @param firstName The first name to be set for the user.
     */
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
     * Gets the last name of the user entity.
     *
     * @return The last name of the user.
     */
	
	public String getLastName() {
		return lastName;
	}

	
	 /**
     * Sets the last name of the user entity.
     *
     * @param lastName The last name to be set for the user.
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	 /**
     * Gets the email address of the user entity.
     *
     * @return The email address of the user.
     */
	public String getEmail() {
		return email;
	}

	/**
     * Sets the email address of the user entity.
     *
     * @param email The email address to be set for the user.
     */
	public void setEmail(String email) {
		this.email = email;
	}

	  /**
     * Gets the password of the user entity.
     *
     * @return The password of the user.
     */
	public String getPassword() {
		return password;
	}

	 /**
     * Sets the password of the user entity.
     *
     * @param password The password to be set for the user.
     */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * Gets the ID of the user entity.
     *
     * @return The ID of the user.
     */
	public int getId() {
		return id;
	}

	 /**
     * Sets the ID of the user entity.
     *
     * @param id The ID to be set for the user.
     */
	public void setId(int id) {
		this.id = id;
	}

	/**
     * Checks if the user entity is active.
     *
     * @return True if the user is active, false otherwise.
     */
	public boolean isActive() {
		return isActive;
	}

	/**
     * Sets the active status of the user entity.
     *
     * @param isActive The active status to be set for the user.
     */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	  /**
     * Compares this user entity with another user entity based on their IDs.
     *
     * @param o The user entity to compare with.
     * @return 0 if IDs are equal, 1 if this ID is greater, -1 if this ID is smaller.
     */

	@Override
    public int compareTo(UserEntity o) {
    	if (this.getId() == o.getId()) {
			return 0;
		} else {
			if (this.getId() > o.getId()) {
				return 1;
			} else {
				return -1;
			}
		}
			
    }

	  /**
     * Provides a string representation of the user entity.
     *
     * @return A string containing user entity properties.
     */
	
	@Override
	public String toString() {
		return "UserEntity [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", id=" + id + ", isActive=" + isActive + "]";
	}

}