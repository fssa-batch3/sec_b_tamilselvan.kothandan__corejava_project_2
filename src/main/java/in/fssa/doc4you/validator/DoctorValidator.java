package in.fssa.doc4you.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.doc4you.dao.DoctorDAO;
import in.fssa.doc4you.dto.DoctorDTO;
import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.model.Doctor;

import in.fssa.doc4you.util.StringUtil;

public class DoctorValidator  extends UserValidator {

	public static void validate1(Doctor newdoctor, int id) throws ValidationException {
		if(newdoctor == null) {
			throw new ValidationException("Doctor cannot be null");
		}
		if(id<=0) {
			throw new ValidationException("id cannot be negative");
		}
		
		DoctorDAO doctorDAO = new DoctorDAO();
		Doctor doctor = doctorDAO.findById(id);
		if(doctor == null) {
			throw new ValidationException("Doctor doesn't exists");
		}
		StringUtil.rejectIfInvalidString(newdoctor.getQualifications(), "qualifications");
		
		if(newdoctor.getExperience()<=0) {
			throw new ValidationException("Invalid yrs of experience");
		}
		if(newdoctor.getExperience()<10) {
			throw new ValidationException("Doctor should have atleast 10 yrs of experience");
		}
		StringUtil.rejectIfInvalidString(newdoctor.getDepartment(), "department");
	}
	
	
	
	/**
	 * 
	 * @param newDoctor
	 * @throws ValidationException
	 */
	public static void validate(DoctorDTO newDoctor) throws ValidationException {
		if(newDoctor == null) {
			throw new ValidationException("Doctor cannot be null");
		}
		
		StringUtil.rejectIfInvalidString(newDoctor.getQualifications(), "qualifications");
		
		if(newDoctor.getExperience()<=0) {
			throw new ValidationException("Invalid yrs of experience");
		}
		if(newDoctor.getExperience()<10) {
			throw new ValidationException("Doctor should have atleast 10 yrs of experience");
		}
		StringUtil.rejectIfInvalidString(newDoctor.getDepartment(), "department");
	}
	
	
	
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void validateForDoctorId(int id) throws ValidationException {
		if(id<=0) {
			throw new ValidationException("id cannot be negative");
		}
		DoctorDAO doctorDAO = new DoctorDAO();
		Doctor doctor = doctorDAO.findById(id);
		if(doctor == null) {
			throw new ValidationException("Doctor doesn't exists");
		}
	}
	
	public static void validateForEmail(String email) throws ValidationException {
		StringUtil.rejectIfInvalidString(email, "email");

		Pattern pattern1 = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
		Matcher matcher1 = pattern1.matcher(email);
		if (!matcher1.matches()) {
			throw new ValidationException("email must contain lowercase letters followed by '@' and '.'");
		}
	}
	
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

	public static void validate1(DoctorDTO newUser) throws ValidationException {

		if (!validatePassword(newUser.getPassword())) {
			System.out.println(newUser.getPassword() + "password");
			throw new ValidationException("Password must be at least 8 characters ex: Njcat#10");
		}

		StringUtil.rejectIfInvalidString(newUser.getPassword(), "Password");
	}

	public static boolean validatePassword(String password) throws ValidationException {

		if (StringUtil.isInvalidString(password)) {
			throw new ValidationException("Invalid password");
		}

		if (!password.matches(PASSWORD_PATTERN)) {
			throw new ValidationException("Password must be at least 8 characters and meet specific criteria");
		}

		return true;
	}

}
