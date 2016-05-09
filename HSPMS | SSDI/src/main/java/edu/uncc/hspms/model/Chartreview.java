package edu.uncc.hspms.model;

public class Chartreview {
	
	int patient_id;
	String visitnote="";
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public String getVisitnote() {
		return visitnote;
	}
	public void setVisitnote(String visitnote) {
		this.visitnote = visitnote;
	}
	@Override
	public String toString() {
		return "Chartreview [patient_id=" + patient_id + ", visitnote="
				+ visitnote + "]";
	}
	

}
