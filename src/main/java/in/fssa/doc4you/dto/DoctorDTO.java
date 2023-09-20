package in.fssa.doc4you.dto;

public class DoctorDTO {
	private int id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private boolean isActive = true;
	private String qualifications;
	private double experience;
	private String department;
	private String doctorImage;
	private boolean isDocActive = true;
	private int doctorId;
	 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDoctorImage() {
		return doctorImage;
	}

	public void setDoctorImage(String doctorImage) {
		this.doctorImage = doctorImage;
	}

	public boolean isDocActive() {
		return isDocActive;
	}

	public void setDocActive(boolean isActive) {
		this.isDocActive = isActive;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String fullName() {
		return firstName.concat(" ").concat(lastName);
	}

	@Override
	public String toString() {
		return "DoctorDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", password=" + password + ", isActive=" + isActive + ", qualifications=" + qualifications
				+ ", experience=" + experience + ", department=" + department + ", doctorImage=" + doctorImage
				+ ", isDocActive=" + isDocActive;
	}

}
