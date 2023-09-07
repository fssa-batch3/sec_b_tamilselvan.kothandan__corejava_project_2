package testDelete;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.service.DoctorService;

public class TestDeleteDoctor {

	@Test
	public void testDeleteDoctorWithValidId() {
		DoctorService ds = new DoctorService();
		DoctorService.reactivate(8);
		assertDoesNotThrow(()->{
			ds.deleteDoctor(8);
		});
	}
	
	@Test
	public void testDeleteDoctorWithInvalidId() {
		DoctorService ds = new DoctorService();
		Exception excp = assertThrows(ValidationException.class , ()->{
			ds.deleteDoctor(-45);
		});
		String m1 = "id cannot be negative";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	
}
