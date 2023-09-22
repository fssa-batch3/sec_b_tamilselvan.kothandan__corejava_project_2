package in.fssa.doc4you.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.doc4you.dto.DoctorDTO;
import in.fssa.doc4you.exception.ValidationException;
import in.fssa.doc4you.model.Doctor;
import in.fssa.doc4you.service.DoctorService;
import in.fssa.doc4you.util.ConnectionUtil;

public class DoctorDAO {

	
	
	public void create (DoctorDTO newDoctor , int id) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConnectionUtil.getConnection();
			String query = "INSERT INTO doctors(id , qualifications , experience , department , doctor_image) VALUES (? , ? , ? , ? , ?)";
		    ps = con.prepareStatement(query);
		    ps.setInt(1, id);
		    ps.setString(2, newDoctor.getQualifications());
		    ps.setDouble(3, DoctorService.convertYearToMonth(newDoctor.getExperience()));
			ps.setString(4, newDoctor.getDepartment());
			ps.setString(5, newDoctor.getDoctorImage());
			
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Doctor had been created successfully");
			} else {
				throw new RuntimeException("Doctor had not been created successfully");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	public Set<DoctorDTO> findAll() throws ValidationException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Set<DoctorDTO> doctorList = null;
	    try {
	        con = ConnectionUtil.getConnection();
	        String query = "SELECT\r\n"
	            + "    d.doctor_id,\r\n"
	            + "    d.qualifications,\r\n"
	            + "    d.experience,\r\n"
	            + "    d.department,\r\n"
	            + "    d.doctor_image,\r\n"
	            + "    u.first_name,\r\n"
	            + "    u.last_name,\r\n"
	            + "    u.email\r\n"
	            + "FROM\r\n"
	            + "    doctors AS d\r\n"
	            + "INNER JOIN\r\n"
	            + "    users AS u\r\n"
	            + "ON\r\n"
	            + "    u.id = d.id\r\n"
	            + "WHERE\r\n"
	            + "    d.is_active = 1;\r\n";
	        ps = con.prepareStatement(query);
	        doctorList = new HashSet<DoctorDTO>();
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            DoctorDTO dto = new DoctorDTO();
	            dto.setId(rs.getInt("doctor_id")); // Use d.doctor_id here
	            dto.setQualifications(rs.getString("qualifications"));
	            dto.setExperience(DoctorService.convertMonthToYear(rs.getInt("experience")));
	            dto.setDepartment(rs.getString("department"));
	            dto.setFirstName(rs.getString("first_name"));
	            dto.setLastName(rs.getString("last_name"));
	            dto.setEmailId(rs.getString("email"));
	            dto.setDoctorImage(rs.getString("doctor_image"));
	            dto.setDoctorId(rs.getInt("doctor_id"));
	            doctorList.add(dto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new RuntimeException(e);
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	    return doctorList;
	}

	
	
	public void update(int id, Doctor  newDoctor) throws ValidationException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE doctors SET qualifications = ? , experience = ? , department = ? , doctor_image = ? WHERE doctor_id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, newDoctor.getQualifications());
			ps.setDouble(2, DoctorService.convertYearToMonth(newDoctor.getExperience()));
			ps.setString(3, newDoctor.getDepartment());
			ps.setString(4, newDoctor.getDoctorImage());
			ps.setInt(5, id);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Doctor had been updated successfully");
			} else {
				throw new RuntimeException("Doctor had not been updated successfully");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE doctors SET is_active = 0 WHERE doctor_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int rowsAffected  = ps.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Doctor had been deleted successfully");
			}else {
				throw new RuntimeException("Doctor had not been deleted successfully");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
		
	}

	/**
	 *  
	 * @param id
	 * @return doctor
	 */
	public Doctor findById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Doctor d = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT doctor_id,qualifications,experience,department,doctor_image,is_active,id FROM doctors WHERE is_active = 1 AND doctor_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				d = new Doctor();
				d.setId(rs.getInt("doctor_id"));
				d.setQualifications(rs.getString("qualifications"));
				d.setExperience(DoctorService.convertMonthToYear(rs.getInt("experience")));
				d.setDepartment(rs.getString("department"));
				d.setDocActive(rs.getBoolean("is_active"));
				d.setDoctorImage(rs.getString("doctor_image"));
				d.setId(rs.getInt("id"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return d;
	}
	
	/**
	 * 
	 * @param id
	 * @return doctor
	 */
	public DoctorDTO findDoctorById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DoctorDTO dd = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT doctor_id,d.id,qualifications,experience,department,doctor_image,first_name,last_name,email,d.is_active FROM users AS u INNER JOIN doctors AS d on u.id = d.id WHERE d.is_active = 1 AND doctor_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				dd = new DoctorDTO();
				dd.setDoctorId(rs.getInt("doctor_id"));
				dd.setId(rs.getInt("d.id"));
				dd.setFirstName(rs.getString("first_name"));
				dd.setLastName(rs.getString("last_name"));
				dd.setEmailId(rs.getString("email"));
				dd.setQualifications(rs.getString("qualifications"));
				dd.setExperience(DoctorService.convertMonthToYear(rs.getInt("experience")));
				dd.setDepartment(rs.getString("department"));
				dd.setDoctorImage(rs.getString("doctor_image"));
				dd.setDocActive(rs.getBoolean("d.is_active"));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return dd;
	}
	
	
	/**
	 * 
	 * @param email
	 * @return doctor
	 */
	public static DoctorDTO findDoctorByEmail(String email) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		DoctorDTO dd = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT d.doctor_id, d.id, d.qualifications, d.experience, d.department, d.doctor_image, u.first_name, u.last_name, u.email,\r\n"
					+ " u.password ,d.is_active FROM users AS u INNER JOIN doctors AS d ON u.id = d.id WHERE d.is_active = 1 AND u.email = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				dd = new DoctorDTO();
				dd.setDoctorId(rs.getInt("doctor_id"));
				dd.setId(rs.getInt("d.id"));
				dd.setFirstName(rs.getString("first_name"));
				dd.setLastName(rs.getString("last_name"));
				dd.setEmailId(rs.getString("email"));
				dd.setPassword(rs.getString("password"));
				dd.setQualifications(rs.getString("qualifications"));
				dd.setExperience(DoctorService.convertMonthToYear(rs.getInt("experience")));
				dd.setDepartment(rs.getString("department"));
				dd.setDoctorImage(rs.getString("doctor_image"));
				dd.setDocActive(rs.getBoolean("d.is_active"));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return dd;
		
	}

	public void reactivate(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE doctors SET is_active = 1 WHERE doctor_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected<=0) {
				throw new RuntimeException("Doctor does not exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
		
		
}
	


	












	
	
	


