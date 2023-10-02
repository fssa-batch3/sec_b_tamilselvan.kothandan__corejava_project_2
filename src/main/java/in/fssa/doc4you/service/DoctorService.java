package in.fssa.doc4you.service;

import java.util.List;
import java.util.Set;

import com.google.protobuf.ServiceException;

import in.fssa.doc4you.dao.DoctorDAO;
import in.fssa.doc4you.dto.DoctorDTO;
import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.model.Doctor;
import in.fssa.doc4you.model.User;
import in.fssa.doc4you.validator.DoctorValidator;

public class DoctorService {
	public void createDoctor(DoctorDTO newDoctor) throws ValidationException {
		UserService userService = new UserService();

		DoctorValidator.validate(newDoctor);
		User user = new User();
		user.setFirstName(newDoctor.getFirstName());
		user.setLastName(newDoctor.getLastName());
		user.setEmail(newDoctor.getEmailId());
		user.setPassword(newDoctor.getPassword());

		int userId = userService.createUser(user);

		DoctorDAO doctorDAO = new DoctorDAO();
		doctorDAO.create(newDoctor, userId);
	}
	
	public List<DoctorDTO> findAllByDoctors() throws ServiceException{
		DoctorDAO doctorDAO = new DoctorDAO();
		List<DoctorDTO>  doctors = null;
		try {
			doctors = doctorDAO.findAll();
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return doctors;
	}

	public List<DoctorDTO> listAllDoctor() throws ValidationException {
		DoctorDAO doctorDAO = new DoctorDAO();
		List<DoctorDTO> doctorList = doctorDAO.findAll();
		return doctorList;
	}

	public void updateDoctor(int id, Doctor newDoctor) throws ValidationException {
	    // Validate the newDoctor data using DoctorValidator.
	    DoctorValidator.validate1(newDoctor, id);

	    // Create a DoctorDAO instance to work with the database.
	    DoctorDAO doctorDAO = new DoctorDAO();

	    // Find the existing Doctor record by ID.
	    Doctor existingDoctor = doctorDAO.findById(id);

	    // Get the associated User ID.
	    int userId = existingDoctor.getId();

	    // Create a User object and populate it with newDoctor's data.
	    User user = new User();
	    user.setFirstName(newDoctor.getFirstName()); // Fix typo here (was setFirstName twice)
	    user.setLastName(newDoctor.getLastName());   // Set the last name
	    user.setEmail(newDoctor.getEmail());
	    user.setPassword(newDoctor.getPassword());

	    // Update the User information using a UserService (assuming such a service exists).
	    UserService.updateUser(userId, user);

	    // Create a new Doctor object and populate it with newDoctor's data.
	    Doctor doctor = new Doctor();
	    doctor.setQualifications(newDoctor.getQualifications());
	    doctor.setExperience(newDoctor.getExperience());
	    doctor.setDepartment(newDoctor.getDepartment());
	    doctor.setDoctorImage(newDoctor.getDoctorImage());

	    // Create a new DoctorDAO instance and update the Doctor record in the database.
	    
	    doctorDAO.update(id, doctor);
	}


	public void deleteDoctor(int id) throws ValidationException {
		DoctorValidator.validateForDoctorId(id);
		DoctorDAO doctorDAO = new DoctorDAO();
		doctorDAO.delete(id);
	}

	public static void reactivate(int id) {
		DoctorDAO doctorDAO = new DoctorDAO();
		doctorDAO.reactivate(id);
	}

	public static DoctorDTO getDoctorById(int id) throws ValidationException {
		DoctorValidator.validateForDoctorId(id);
		DoctorDAO doctorDAO = new DoctorDAO();
		return doctorDAO.findDoctorById(id);

	}

	public DoctorDTO getDoctorByEmail(String email) throws ValidationException {
		DoctorValidator.validateForEmail(email);
		DoctorDAO doctorDAO = new DoctorDAO();
		return DoctorDAO.findDoctorByEmail(email);
	}

	public static double convertYearToMonth(double year) {
		double month = year * 12;
		return month;
	}

	public static double convertMonthToYear(double month) {
		double year = month / 12;
		return year;
	}

	
	public DoctorDTO loginUser(String email, String password) throws ServiceException {
	    try {
	        DoctorValidator.validateForEmail(email);
	        DoctorValidator.validatePassword(password);

	        DoctorDTO doctor = DoctorDAO.findDoctorByEmail(email);

	        if (doctor != null) {
	            if (password.equals(doctor.getPassword())) {
	                return doctor;
	            } else {
	                throw new ServiceException("Incorrect password");
	            }
	        } else {
	            throw new ServiceException("Doctor not found");
	        }
	    } catch (ValidationException e) {
	        throw new ServiceException("Invalid email or password format");
	    }
	}


	
}
