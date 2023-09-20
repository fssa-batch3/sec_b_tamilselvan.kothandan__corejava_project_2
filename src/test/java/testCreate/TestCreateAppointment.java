package testCreate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.model.Appointment;
import in.fssa.doc4you.service.AppointmentService;

public class TestCreateAppointment {

	
	@Test
	public void testCreateAppointmentWithValidData() {
		Random random = new Random();
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(13);
		appointment.setDoctorId(4);
		appointment.setReasonForConsultation("head ache for 3 days");
		String date = AppointmentService.convertLocalDateToString(LocalDate.now().plusDays(random.nextLong(7)));
		appointment.setDateOfConsultation(date);
		LocalTime st = LocalTime.of(12, 00, 0);
		LocalTime et = st.plusHours(1);
		String startTime = AppointmentService.convertLocalTimeToString(st);
		String endTime = AppointmentService.convertLocalTimeToString(et);
		appointment.setStartTime(startTime);
		appointment.setEndTime(endTime);
		System.out.println(startTime+" "+endTime);
		
		assertDoesNotThrow(()->{
			appointmentService.create(appointment);
		});
	}
	
	@Test
	public void testCreateAppointmentWithInvalidData() {
		AppointmentService appointmentService = new AppointmentService();
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(null);
		});
		String expectedMessage = "appointment cannot be empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithNegativePatientId() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(-5);
		appointment.setDoctorId(10);
		appointment.setReasonForConsultation("head ache for 3 days");
		appointment.setDateOfConsultation("2023-09-06");
		appointment.setStartTime("12:00:00");
		appointment.setEndTime("13:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		String expectedMessage = "patient id cannot be negative";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithInvalidPatientId() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(30);
		appointment.setDoctorId(5);
		appointment.setReasonForConsultation("Stomach ache for 3 days");
		appointment.setDateOfConsultation("2023-09-06");
		appointment.setStartTime("12:00:00");
		appointment.setEndTime("13:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		String expectedMessage = "no user exists in the  given user id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithNegativeDoctorId() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(-5);
		appointment.setReasonForConsultation("Stomach ache for 3 days");
		appointment.setDateOfConsultation("2023-09-06");
		appointment.setStartTime("12:00:00");
		appointment.setEndTime("13:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		String expectedMessage = "doctor id cannot be negative";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
		
	}
	
	@Test
	public void testCreateAppointmentWithInvalidDoctorId() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(15);
		appointment.setReasonForConsultation("Stomach ache for 3 days");
		appointment.setDateOfConsultation("2023-09-06");
		appointment.setStartTime("12:00:00");
		appointment.setEndTime("13:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		String expectedMessage = "no doctor exists in the given doctor id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage)); 
	}
	
	@Test
	public void testCreateAppointmentWithReasonNull() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		
		appointment.setPatientId(3);
		appointment.setDoctorId(5);
		appointment.setReasonForConsultation(null);
		appointment.setDateOfConsultation("2023-09-10");
		appointment.setStartTime("09:00:00");
		appointment.setEndTime("10:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "reason for consultation cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithReasonEmpty() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		
		appointment.setPatientId(4);
		appointment.setDoctorId(6);
		appointment.setReasonForConsultation("");
		appointment.setDateOfConsultation("2023-09-10");
		appointment.setStartTime("09:00:00");
		appointment.setEndTime("10:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create (appointment);
		});
		
		String expectedMessage = "reason for consultation cannot be null or Empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithReasonWithIncorrectPattern() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		
		appointment.setPatientId(3);
		appointment.setDoctorId(3);
		appointment.setReasonForConsultation("asdfghjk12345678*&^%$#@");
		appointment.setDateOfConsultation("2023-09-10");
		appointment.setStartTime("09:00:00");
		appointment.setEndTime("10:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "reason should not include any special symbols except ',' and '.'";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithDateNull() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(2);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		appointment.setDateOfConsultation(null);
		appointment.setStartTime("09:00:00");
		appointment.setEndTime("10:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "date of consultation should not be empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
		
	}
	
	@Test
	public void testCreateAppointmentWithDateEmpty() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(3);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		appointment.setDateOfConsultation("");
		appointment.setStartTime("09:00:00");
		appointment.setEndTime("10:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "date of consultation should not be empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
		
	}
	
	@Test
	public void testCreateAppointmentWithDateWithIncorrectPattern() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(3);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		appointment.setDateOfConsultation("09-09-2023");
		appointment.setStartTime("09:00:00");
		appointment.setEndTime("10:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "date should be in the format of 'yyyy-MM-dd'";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
		
	}
	
	@Test
	public void testCreateAppointmentWithDateBeforeCurrentDate() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(3);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		String date = AppointmentService.convertLocalDateToString(LocalDate.now().minusDays(3));
		appointment.setDateOfConsultation(date);
		appointment.setStartTime("09:00:00");
		appointment.setEndTime("10:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "consultation date is before the today's date";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithNullStartTime() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(3);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		appointment.setDateOfConsultation("2023-09-09");
		appointment.setStartTime(null);
		appointment.setEndTime("10:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "start time should not be empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithEmptyStartTime() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(3);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		appointment.setDateOfConsultation("2023-09-09");
		appointment.setStartTime("");
		appointment.setEndTime("10:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "start time should not be empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithStartTimeBeforeDefaultTime() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(3);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		appointment.setDateOfConsultation("2023-09-09");
		appointment.setStartTime("07:30:00");
		appointment.setEndTime("08:30:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "you cannot book the appointment for before 9 am or after 8 pm";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithAlreadyExistsTiming() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(2);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		appointment.setDateOfConsultation("2023-09-08");
		appointment.setStartTime("11:00:00");
		appointment.setEndTime("12:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "appointment already exists in this timings";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithNullEndTime() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(3);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		appointment.setDateOfConsultation("2023-09-12");
		appointment.setStartTime("09:45:00");
		appointment.setEndTime(null);
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "end time should not be empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithEmptyEndTime() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(3);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		appointment.setDateOfConsultation("2023-09-13");
		appointment.setStartTime("09:45:00");
		appointment.setEndTime("");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "end time should not be empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithAlreadyEndTime() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(3);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		appointment.setDateOfConsultation("2023-09-10");
		appointment.setStartTime("09:30:00");
		appointment.setEndTime("10:30:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "appointment already exists in this timings";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAppointmentWithEndTimeMoreThanOneHour() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		appointment.setPatientId(3);
		appointment.setDoctorId(3);
		appointment.setReasonForConsultation("eye pain since day before yesterday");
		appointment.setStartTime("14:30:00");
		appointment.setEndTime("17:00:00");
		
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.create(appointment);
		});
		
		String expectedMessage = "you can only book appointment for 1 hr";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
}
