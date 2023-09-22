package in.fssa.doc4you.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import in.fssa.doc4you.dto.AppointmentDTO;
import in.fssa.doc4you.enumfiles.Status;
import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.interfaces.AppointmentInterface;
import in.fssa.doc4you.model.Appointment;
import in.fssa.doc4you.model.User;
import in.fssa.doc4you.service.AppointmentService;
import in.fssa.doc4you.service.DoctorService;
import in.fssa.doc4you.service.UserService;
import in.fssa.doc4you.util.ConnectionUtil;

public class AppointmentDAO implements AppointmentInterface {
	@Override
	public List<Map<String, LocalTime>> findAllBookedTimingsByDate(String date) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, LocalTime> timing = null;
		List<Map<String, LocalTime>> listOfTimings = new ArrayList<Map<String, LocalTime>>();
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT start_time , end_time FROM appointments WHERE date_of_consultation = ? AND (status = ? OR status = ?)";
			ps = con.prepareStatement(query);
			LocalDate consultationdate = AppointmentService.convertStringToDate(date);
			java.sql.Date sqlDate = Date.valueOf(consultationdate);
			ps.setDate(1, sqlDate);
			ps.setString(2, "On_process");
			ps.setString(3, "Approved");

			rs = ps.executeQuery();
			while (rs.next()) {
				timing = new HashMap<String, LocalTime>();
				LocalTime startTime = rs.getTimestamp("start_time").toLocalDateTime().toLocalTime();
				LocalTime endTime = rs.getTimestamp("end_time").toLocalDateTime().toLocalTime();
				timing.put("start time", startTime);
				timing.put("end time", endTime);

				listOfTimings.add(timing);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e);
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return listOfTimings;
	}

	@Override
	public void createAppointment(Appointment app) throws ValidationException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionUtil.getConnection();
			String query = "INSERT INTO appointments (doctor_id , patient_id , reason_for_consultation ,  date_of_consultation , start_time , end_time) VALUES(?,?,?,?,?,?)";
			ps = con.prepareStatement(query);

			LocalDate localDate = AppointmentService.convertStringToDate(app.getDateOfConsultation());

			java.sql.Date date = Date.valueOf(localDate);
			System.out.println(date);
			System.out.println(localDate);

			Timestamp startTime = Timestamp.valueOf((localDate + " " + app.getStartTime()));

			Timestamp endTime = Timestamp.valueOf(localDate + " " + app.getEndTime());

			ps.setInt(1, app.getDoctorId());
			ps.setInt(2, app.getPatientId());
			ps.setString(3, app.getReasonForConsultation());

			ps.setDate(4, date);
			ps.setTimestamp(5, startTime);
			ps.setTimestamp(6, endTime);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Appointment has been booked successfully");
			} else {
				throw new RuntimeException("Appointment has not been booked successfully");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	@Override
	public Set<AppointmentDTO> findAllAppointmentByDoctorId(int doctorId) {
	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement("SELECT a.id, u.first_name, u.last_name, a.* FROM appointments AS a INNER JOIN users AS u ON u.id = a.patient_id WHERE a.doctor_id = ?")) {
	        String doctorName = DoctorService.getDoctorById(doctorId).fullName();
	        ps.setInt(1, doctorId);

	        Set<AppointmentDTO> appointmentList = new HashSet<>();

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                AppointmentDTO appointment = new AppointmentDTO();
	                appointment.setId(rs.getInt("id"));
	                appointment.setUserId(rs.getInt("patient_id"));
	                appointment.setPatientName(rs.getString("first_name") + " " + rs.getString("last_name"));
	                appointment.setReasonForConsultation(rs.getString("reason_for_consultation"));
	                appointment.setDateOfConsultation(rs.getString("date_of_consultation"));
	                appointment.setStartTime(rs.getString("start_time"));
	                appointment.setEndTime(rs.getString("end_time"));
	                appointment.setDoctorId(doctorId);
	                appointment.setDoctorName(doctorName);
	                appointment.setStatus(Status.valueOf(rs.getString("status")));

	                appointmentList.add(appointment);
	            }
	        }

	        return appointmentList;
	    } catch (SQLException e) {
	        // Handle the exception properly, possibly logging it
	        throw new RuntimeException("Error finding appointments by doctor ID", e);
	    } catch (ValidationException e) {
	        // Handle the exception properly, possibly logging it
	        throw new RuntimeException("Validation error", e);
	    }
	}

	@Override
	public Set<AppointmentDTO> findAllAppointmentByUserId(int userId) {
	    try (Connection con = ConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement("SELECT a.id, u.first_name, u.last_name, a.* FROM appointments AS a INNER JOIN users AS u ON u.id = a.patient_id WHERE a.patient_id = ? ORDER BY a.date_of_consultation ASC")) {
	        ps.setInt(1, userId);

	        Set<AppointmentDTO> listOfAppointments = new HashSet<>();
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                AppointmentDTO appointment = new AppointmentDTO();
	                appointment.setDoctorId(rs.getInt("doctor_id"));
	                String doctorName = DoctorService.getDoctorById(appointment.getDoctorId()).fullName();
	                appointment.setDoctorName(doctorName);
	                appointment.setId(rs.getInt("id"));
	                appointment.setUserId(userId);
	                appointment.setPatientName(rs.getString("first_name") + " " + rs.getString("last_name"));
	                appointment.setReasonForConsultation(rs.getString("reason_for_consultation"));
	                appointment.setDateOfConsultation(rs.getString("date_of_consultation"));
	                appointment.setStartTime(rs.getString("start_time"));
	                appointment.setEndTime(rs.getString("end_time"));
	                appointment.setStatus(Status.valueOf(rs.getString("status")));

	                listOfAppointments.add(appointment);
	            }
	        }
	        
	        return listOfAppointments;
	    } catch (SQLException e) {
	        // Handle the exception properly, possibly logging it
	        throw new RuntimeException("Error finding appointments by user ID", e);
	    } catch (ValidationException e) {
	        // Handle the exception properly, possibly logging it
	        throw new RuntimeException("Validation error", e);
	    }
	}


	@Override
	public void updateAppointmentStatus(int appId, Appointment appointment) {
		System.out.println("appointmentDAO"+appId);
		System.out.println("appointmentId 2"+appointment);
	    Connection con = null;
	    PreparedStatement ps = null;
	    try {
	        con = ConnectionUtil.getConnection();
	        String query = "UPDATE appointments SET status = ? WHERE id = ? AND status = 'On_process'";
	        ps = con.prepareStatement(query);
	        ps.setString(1, appointment.getStatus().name());
	        ps.setInt(2, appId);  // Set the ID parameter here

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Appointment Status updated successfully");
	        } else {
	            throw new RuntimeException("Can't update the appointment status");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e);
	        throw new RuntimeException(e);
	    } finally {
	        ConnectionUtil.close(con, ps);
	    }
	}

	@Override
	public AppointmentDTO findAppointmentByAppointmentId(int appId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		AppointmentDTO appointment = null;
		User user = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT * FROM appointments WHERE id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, appId);
			rs = ps.executeQuery();
			if (rs.next()) {
				appointment = new AppointmentDTO();
				user = UserService.getByUserId(rs.getInt("patient_id"));
				appointment.setId(appId);
				appointment.setUserId(user.getId());
				appointment.setDoctorId(rs.getInt("doctor_id"));
				String doctorName = DoctorService.getDoctorById(appointment.getDoctorId()).fullName();
				appointment.setPatientName(user.getFirstName() + user.getLastName());
				appointment.setDoctorName(doctorName);
				appointment.setReasonForConsultation(rs.getString("reason_for_consultation"));
				appointment.setDateOfConsultation(rs.getString("date_of_consultation"));
				appointment.setStartTime(rs.getString("start_time"));
				appointment.setEndTime(rs.getString("end_time"));
				appointment.setStatus(Status.valueOf(rs.getString("status")));
			} else {
				throw new RuntimeException("Appointment doesn't exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			throw new RuntimeException(e);
		} catch (ValidationException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return appointment;
	}

}
