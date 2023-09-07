package testCreate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.model.User;
import in.fssa.doc4you.service.UserService;
import in.fssa.doc4you.util.EmailGenerator;




public class TestCreateUser {
	@Test
	public void testCreateUserWithValidData() {
		UserService userService = new UserService();
		User user = new User();

		user.setFirstName("Kothandan");
		user.setLastName("nagiaya");
		user.setEmail(EmailGenerator.generate());
		user.setPassword("Kothandan@#2002");

		assertDoesNotThrow(() -> {
			userService.createUser(user);
		});

	}

	@Test
	public void testCreateUserWithInvalidData() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(null);
		});
		String expectedMessage = "User cannot be null";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}

	@Test
	public void testCreateUserWithFirstNameNull() {
		UserService us = new UserService();
		User user = new User();

		user.setFirstName(null);
		user.setLastName("kothandan");
		user.setEmail(EmailGenerator.generate());
		user.setPassword("!@#$1234kothan");

		Exception excp = assertThrows(ValidationException.class, () -> {
			us.createUser(user);
		});

		String m1 = "first name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateUserWithFirstNameEmpty() {
		UserService us = new UserService();
		User user = new User();

		user.setFirstName("");
		user.setLastName("naiyaga");
		user.setEmail(EmailGenerator.generate());
		user.setPassword("!@#$1234selvan");

		Exception excp = assertThrows(ValidationException.class, () -> {
			us.createUser(user);
		});

		String m1 = "first name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateUserWithLastNameNull() {
		UserService us = new UserService();
		User user = new User();

		user.setFirstName("Kothandan");
		user.setLastName(null);
		user.setEmail(EmailGenerator.generate());
		user.setPassword("!@#$1234Tamil");

		Exception excp = assertThrows(ValidationException.class, () -> {
			us.createUser(user);
		});

		String m1 = "last name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateUserWithLastNameEmpty() {
		UserService us = new UserService();
		User user = new User();

		user.setFirstName("Ajun");
		user.setLastName("");
		user.setEmail(EmailGenerator.generate());
		user.setPassword("!@#$1234selvan");

		Exception excp = assertThrows(ValidationException.class, () -> {
			us.createUser(user);
		});

		String m1 = "last name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateEmailWithEmailNull() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Praveen");
		user.setLastName("Kumar");
		user.setEmail(null);
		user.setPassword("Tamil**1234");

		Exception excp = assertThrows(ValidationException.class, () -> {
			us.createUser(user);
		});
		String m1 = "email cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));

	}

	@Test
	public void testCreateUserWithEmailEmpty() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Kishore");
		user.setLastName("Sugmar");
		user.setEmail("");
		user.setPassword("selvan**1234");

		Exception excp = assertThrows(ValidationException.class, () -> {
			us.createUser(user);
		});
		String m1 = "email cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateUserWithIncorrectEmail() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Selvan");
		user.setLastName("tamil");
		user.setEmail("tamil27");
		user.setPassword("Deepu**1234");

		Exception excp = assertThrows(ValidationException.class, () -> {
			us.createUser(user);
		});
		String m1 = "email doesn't match the required format";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateUserWithExistingEmail() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Inba");
		user.setLastName("Lokesh");
		user.setEmail("michael@example.com");
		user.setPassword("Deepu**1234");

		Exception excp = assertThrows(ValidationException.class, () -> {
			us.createUser(user);
		});
		String m1 = "User already exists";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateUserWithPasswordNull() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Tamil");
		user.setLastName("selvan");
		user.setEmail(EmailGenerator.generate());
		user.setPassword(null);

		Exception excp = assertThrows(ValidationException.class, () -> {
			us.createUser(user);
		});
		String m1 = "password cannot be null or empty";
		String m2 = excp.getMessage();
		System.out.println(m2);
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateUserWithPasswordEmpty() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Tamil");
		user.setLastName("selvan");
		user.setEmail(EmailGenerator.generate());
		user.setPassword("");

		Exception excp = assertThrows(ValidationException.class, () -> {
			us.createUser(user);
		});
		String m1 = "password cannot be null or empty";
		String m2 = excp.getMessage();

		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateUserWithIncorrectPassword() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Tamil");
		user.setLastName("selvan");

		user.setEmail(EmailGenerator.generate());
		user.setPassword("tamil123");

		Exception excp = assertThrows(ValidationException.class, () -> {
			us.createUser(user);
		});
		String m1 = "Password doesn't match the required format";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
}