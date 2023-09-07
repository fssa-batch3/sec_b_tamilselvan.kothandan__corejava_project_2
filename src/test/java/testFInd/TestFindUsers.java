package testFInd;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.service.UserService;

public class TestFindUsers {

	@Test
	public void testFindUsers() {
		UserService us = new UserService();
		assertDoesNotThrow(() -> {
			System.out.print(us.getAllUsers());
		});
	}

	@Test
	public void testFindUserByValidId() {
		UserService us = new UserService();
		assertDoesNotThrow(() -> {
			us.getByUserId(2);
		});
	}

	@Test
	public void testFindUserByInvalidId() {
		UserService us = new UserService();
		Exception ex = assertThrows(ValidationException.class, () -> {
			us.getByUserId(-90);
		});
		String m1 = "id cannot be negative";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testFindUserWithValidEmail() {
		UserService us = new UserService();
		assertDoesNotThrow(() -> {
			us.getByUserEmail("tamil@gmail.com");
		});
	}

	@Test
	public void testFindUserWithNullEmail() {
		UserService us = new UserService();
		Exception ex = assertThrows(ValidationException.class, () -> {
			us.getByUserEmail(null);
		});
		String m1 = "email cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

}
