package testCreate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doc4you.dto.DoctorDTO;
import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.service.DoctorService;
import in.fssa.doc4you.util.EmailGenerator;

public class TestCreateDoctor {

	@Test
	public void testCreateDoctorWithValidData() {
		DoctorService ds = new DoctorService();

		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Kishore");
		ddto.setLastName("Balachandran");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("PrEmA@#@#1234");
		ddto.setQualifications("MBBS , MS-Obstetrics and gynecologist");
		ddto.setExperience(10);
		ddto.setDepartment("Obstetrician and Gynecologist");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		assertDoesNotThrow(() -> {
			ds.createDoctor(ddto);
		});
	}

	@Test
	public void testCreateDoctorWithInvalidData() {
		DoctorService ds = new DoctorService();

		DoctorDTO ddto = null;
		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});
		String m1 = "Doctor cannot be null";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithFirstNameNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();

		ddto.setFirstName(null);
		ddto.setLastName("Ramamoorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("Kishore*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "first name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithFirstNameEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();

		ddto.setFirstName("");
		ddto.setLastName("Ramamoorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("Kishore*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "first name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithImproperFirstName() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("kishore$%$%123");
		ddto.setLastName("Ramamoorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("KisHore*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "first name should contain only alphabets not numbers and symbols";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));

	}

	@Test
	public void testCreateDoctorWithLastNameNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();

		ddto.setFirstName("Durga");
		ddto.setLastName(null);
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("DuRga*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "last name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithLastNameEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();

		ddto.setFirstName("Nihya");
		ddto.setLastName("");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("DuRGA*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "last name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithImproperLastName() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Rohini");
		ddto.setLastName("priya1212");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("RoHINi*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "last name should contain only alphabets not numbers and symbols";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));

	}

	

	@Test
	public void testCreateDoctorWithEmailNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();

		ddto.setFirstName("Prakesh");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId(null);
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "email cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithEmailEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();

		ddto.setFirstName("prakesh");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId("");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "email cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithIncorrectEmail() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();

		ddto.setFirstName("prakesh");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId("prakesh$%$.com");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "email must contain lowercase letters followed by '@' and '.'";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithPasswordNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("prakesh");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword(null);
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "password cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithPasswordEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nithya");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "password cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithIncorrectPassword() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nithya");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("1234567890");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "Password should contain the combination of uppercase , lowercase , numbers and symbols";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));

	}

	@Test
	public void testCreateDoctorWithQualificationNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nithya");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications(null);
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "qualifications cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithQualificationEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nithya");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications("");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "qualifications cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithDepartmentNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nithya");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment(null);
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "department cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithDepartmentEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");
		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "department cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithInvalidExperience() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nithya");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(0);
		ddto.setDepartment("General Medicine");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "Invalid yrs of experience";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}

	@Test
	public void testCreateDoctorWithIncorrectExperience() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nithya");
		ddto.setLastName("Ramamorthy");
		ddto.setEmailId(EmailGenerator.generate());
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(5);
		ddto.setDepartment("General Medicine");
		ddto.setDoctorImage("https://iili.io/HU0MDbV.jpg");

		Exception ex = assertThrows(ValidationException.class, () -> {
			ds.createDoctor(ddto);
		});

		String m1 = "Doctor should have atleast 10 yrs of experience";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
}
