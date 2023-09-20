package in.fssa.doc4you.model;

import in.fssa.doc4you.enumfiles.Status;

public class AppointmentEntity {
	private int patientId;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
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

	private int doctorId;
	private String reasonForConsultation;
	private String dateOfConsultation;
	private String startTime;
	private String endTime;
	private Status status;
	private String reasonForRejection;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AppointmentEntity [id=" + id + ", patientId=" + patientId + ", doctorId=" + doctorId
				+ ", reasonForConsultation=" + reasonForConsultation
				+ ", dateOfConsultation=" + dateOfConsultation + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", status=" + status + ", reasonForRejection=" + reasonForRejection + "]";
	}
	
	
}
