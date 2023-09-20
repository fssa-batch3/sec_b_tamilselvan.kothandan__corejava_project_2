package testUpdate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.doc4you.enumfiles.Status;
import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.model.Appointment;
import in.fssa.doc4you.service.AppointmentService;

public class TestUpdateAppointment {
	@Test
	public void testUpdateAppointmentWithValidData() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		
		appointment.setStatus(Status.Approved);
		
		
		assertDoesNotThrow(()->{
			appointmentService.updateAppointmentStatusByAppointmentId(1, appointment); 
		});
	}
	
	@Test 
	public void testUpdateAppointmentWithSameStatusInStatusField() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		
		appointment.setStatus(Status.Rejected);
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.updateAppointmentStatusByAppointmentId(2, appointment);
		});
		
		String expectedMessage = "status is already in Rejected";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
}
