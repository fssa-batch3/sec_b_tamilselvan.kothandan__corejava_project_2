package in.fssa.doc4you.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.mysql.cj.xdevapi.Statement;

import in.fssa.doc4you.interfaces.UserInterface;
import in.fssa.doc4you.model.User;
import in.fssa.doc4you.util.ConnectionUtil;

public class UserDAO implements UserInterface {

	/**
	 * Retrieves a set of active User objects from the database.
	 *
	 * @return A Set of User objects representing active users.
	 * @throws RuntimeException If a database error occurs during execution.
	 */
	public Set<User> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<User> userList = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT * FROM users WHERE is_active = 1";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			userList = new HashSet<User>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setActive(rs.getBoolean("is_active"));

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return userList;
	}

	/**
	 * Creates a new user in the database with the provided user information.
	 *
	 * @param newUser The User object containing information for the new user.
	 * @return The generated ID of the newly created user.
	 * @throws RuntimeException If a database error occurs during execution.
	 */
	@Override
	public int createUsers(User newUser) throws RuntimeException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int generatedId = 0;
		try {
			con = ConnectionUtil.getConnection();
			String query = "INSERT INTO users (first_name , last_name ,  email , password)" + "VALUES(? , ? , ? , ? )";
			ps = con.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, newUser.getFirstName());
			ps.setString(2, newUser.getLastName());
			ps.setString(3, newUser.getEmail());
			ps.setString(4, newUser.getPassword());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					generatedId = rs.getInt(1);
				}
				System.out.println("User had been created successfully");
			} else {
				throw new RuntimeException("User had not been created successfully");
			}

			newUser.setId(generatedId);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
		return newUser.getId();
	}

	/**
	 * Updates the information of an existing user in the database.
	 *
	 * @param id      The ID of the user to be updated.
	 * @param newUser The updated User object containing new user information.
	 * @throws RuntimeException If the user is not found or a database error occurs.
	 */
	@Override
	public void updateUsers(int id, User newUser) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE users SET first_name = ?, last_name = ?, password = ? WHERE is_active = 1 AND id = ?";
			ps = con.prepareStatement(query);

			ps.setString(1, newUser.getFirstName());
			ps.setString(2, newUser.getLastName());
			ps.setString(3, newUser.getPassword());
			ps.setInt(4, id);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User has been updated successfully");
			} else {
				throw new RuntimeException("User was not updated successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Updates the information of an existing user in the database.
	 *
	 * @param id      The ID of the user to be updated.
	 * @param newUser The updated User object containing new user information.
	 * @throws RuntimeException If the user is not found or a database error occurs.
	 */
	@Override
	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE users SET is_active = 0 WHERE id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User had been deleted successfully");
			} else {
				throw new RuntimeException("User does not exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Reactivates a soft-deleted user by marking them as active in the database.
	 *
	 * @param id The ID of the user to be reactivated.
	 * @throws RuntimeException If the user is not found or a database error occurs.
	 */

	public void reactivateUsers(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE users SET is_active = 1 WHERE id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected <= 0) {
				throw new RuntimeException("User does not exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Retrieves user information by their ID from the database.
	 *
	 * @param id The ID of the user to be retrieved.
	 * @return A User object representing the retrieved user, or null if not found.
	 * @throws RuntimeException If a database error occurs during execution.
	 */
	@Override
	public User findById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT * FROM users WHERE is_active = 1 AND id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("Password"));
				user.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return user;
	}

	/**
	 * Retrieves user information by their email from the database.
	 *
	 * @param email The email of the user to be retrieved.
	 * @return A User object representing the retrieved user, or null if not found.
	 * @throws RuntimeException If a database error occurs during execution or if
	 *                          the email is already associated with another user.
	 */
	@Override
	public User findByEmail(String email) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT * FROM users WHERE is_active = 1 AND email = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("d"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("Duplicate entry")) {
				throw new RuntimeException("User already exists");
			}
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return user;
	}

	/**
	 * Counts the number of active users in the database.
	 *
	 * @return The count of active users.
	 * @throws RuntimeException If a database error occurs during execution.
	 */
	@Override
	public int count() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT COUNT(*) FROM users WHERE is_active = 1";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return count;
	}

	@Override
	public int create(User newT) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(int id, User newT) {
		// TODO Auto-generated method stub

	}

}