package in.fssa.doc4you.dto;

import in.fssa.doc4you.enumfiles.Status;

public class AppointmentDTO {
	private int id;
	private int userId;
	private String patientName;
	
	
	private long mobileNumber;
	private String doctorName;
	private int doctorId;
	private String reasonForConsultation;
	
	private String dateOfConsultation;
	private String startTime;
	private String endTime;
	private Status status;
	private String reasonForRejection;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getReasonForConsultation() {
		return reasonForConsultation;
	}
	public void setReasonForConsultation(String reasonForConsultation) {
		this.reasonForConsultation = reasonForConsultation;
	}
	
	public String getDateOfConsultation() {
		return dateOfConsultation;
	}
	public void setDateOfConsultation(String dateOfConsultation) {
		this.dateOfConsultation = dateOfConsultation;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getReasonForRejection() {
		return reasonForRejection;
	}
	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}
	
	
	@Override
	public String toString() {
		return "\n[id=" + id + ", userId=" + userId + ", patientName=" + patientName 
				 + ", mobileNumber=" + mobileNumber + ", doctorName=" + doctorName + ", doctorId="
				+ doctorId + ", reasonForConsultation=" + reasonForConsultation +  ", dateOfConsultation=" + dateOfConsultation + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", status=" + status + ", reasonForRejection=" + reasonForRejection + "]";
}
}
