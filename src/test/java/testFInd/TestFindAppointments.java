package testFInd;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.service.AppointmentService;

public class TestFindAppointments {

	
	@Test
	public void testFindAppointmentWithValidDoctorId() {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(()->{
			System.out.println(appointmentService.getAllAppointmentsByDoctorId(2));
		});
	}
	
	@Test 
	public void testFindAppointmentWithValidPatientId() {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(()->{
			System.out.println(appointmentService.getAllAppointmentsByUserId(3));
		});
	}
	
	@Test
	public void testFindAppointmentByValidAppointmentId() {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(()->{
			appointmentService.getAppointmentByAppointmentId(1);
		});
	}
	
	@Test
	public void testFindAppointmentByInvalidDoctorId() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.getAllAppointmentsByDoctorId(20);
		});
		String expectedMessage = "Doctor doesn't exists";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindAppointmentByInvalidUserId() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.getAllAppointmentsByUserId(80);
		});
		String expectedMessage = "User doesn't exists";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindAppointmentByInvalidAppointmentId() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.getAppointmentByAppointmentId(16);
		});
		String expectedMessage = "Appointment doesn't exists";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
}
