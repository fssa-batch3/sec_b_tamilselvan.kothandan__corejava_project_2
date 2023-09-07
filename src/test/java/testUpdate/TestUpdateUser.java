package testUpdate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.model.User;
import in.fssa.doc4you.service.UserService;

public class TestUpdateUser {

	private UserService userService = new UserService();

	@Test
	public void testUpdateUserWithValidData() {
		User user = new User();
		user.setFirstName("Tamil");
		user.setLastName("Selvan");
		user.setPassword("Tamil@#2002");

		assertDoesNotThrow(() -> UserService.updateUser(2, user));
	}

	@Test
	public void testUpdateUserWithInvalidIdThrowsException() {
		User user = new User();
		user.setFirstName("rajj");
		user.setLastName("murgann");
		user.setPassword("raj@##1972");

		Exception exception = assertThrows(ValidationException.class, () -> UserService.updateUser(-23, user));

		String expectedMessage = "User doesn't exists";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testUpdateUserWithFirstNameNullThrowsException() {
		User user = new User();
		user.setFirstName(null);
		user.setLastName("Smith");
		user.setPassword("jane456");

		Exception exception = assertThrows(ValidationException.class, () -> UserService.updateUser(3, user));

		String expectedMessage = "first name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testUpdateUserWithFirstNameEmptyThrowsException() {
		User user = new User();
		user.setFirstName("");
		user.setLastName("Selvan");
		user.setPassword("!@#$1234Selvan");

		Exception exception = assertThrows(ValidationException.class, () -> UserService.updateUser(2, user));

		String expectedMessage = "first name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testUpdateUserWithLastNameNullThrowsException() {
		User user = new User();
		user.setFirstName("Tamil");
		user.setLastName(null);
		user.setPassword("!@#$1234Tamil");

		Exception exception = assertThrows(ValidationException.class, () -> userService.updateUser(3, user));

		String expectedMessage = "last name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testUpdateUserWithLastNameEmptyThrowsException() {
		User user = new User();
		user.setFirstName("Tamil");
		user.setLastName("");
		user.setPassword("!@#$1234Tamil");

		Exception exception = assertThrows(ValidationException.class, () -> UserService.updateUser(4, user));

		String expectedMessage = "last name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testUpdateUserWithPasswordNullThrowsException() {
		User user = new User();
		user.setFirstName("Tamil");
		user.setLastName("Selvan");
		user.setPassword(null);

		Exception exception = assertThrows(ValidationException.class, () -> UserService.updateUser(5, user));

		String expectedMessage = "password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testUpdateUserWithPasswordEmptyThrowsException() {
		User user = new User();
		user.setFirstName("Tamil");
		user.setLastName("Selvan");
		user.setPassword("");

		Exception exception = assertThrows(ValidationException.class, () -> UserService.updateUser(5, user));

		String expectedMessage = "password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testUpdateUserWithIncorrectPasswordThrowsException() {
		User user = new User();
		user.setFirstName("Jane");
		user.setLastName("Smith");
		user.setPassword("jane45");

		Exception exception = assertThrows(ValidationException.class, () -> UserService.updateUser(5, user));

		String expectedMessage = "Password doesn't match the required format";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

}
