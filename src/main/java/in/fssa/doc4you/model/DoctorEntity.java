package in.fssa.doc4you.model;

public class DoctorEntity extends UserEntity {
	
	private String qualifications;
	private double experience;
	private String department;
	private String doctorImage;
	private boolean isDocActive;
	private int id;
	

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
	public boolean isActive() {
		return isDocActive;
	}
	public void setActive(boolean isActive) {
		this.isDocActive = isActive;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public boolean isDocActive() {
		return isDocActive;
	}
	public void setDocActive(boolean isDocActive) {
		this.isDocActive = isDocActive;
	}
	@Override
	public String toString() {
		return "DoctorEntity [id " + this.getId() + ", qualifications=" + qualifications + ", experience="
				+ experience + ", department=" + department + ", isActive=" + isDocActive + ", userId=" + id + "]";
	}
	
	
}