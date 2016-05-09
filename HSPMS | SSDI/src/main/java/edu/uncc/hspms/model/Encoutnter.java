package edu.uncc.hspms.model;
import java.util.*;

public class Encoutnter {
	int encounter_id;
	int encounter_type=5;
	int patient_id;
	int location_id=9;
	int form_id=2;
	Date encounter_datetime=new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	int creator=1;
	Date date_created=new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	int voided=0;
	int visit_id;
	UUID uuid=UUID.randomUUID();
	
	public int getEncounter_id() {
		return encounter_id;
	}
	public void setEncounter_id(int encounter_id) {
		this.encounter_id = encounter_id;
	}
	public int getEncounter_type() {
		return encounter_type;
	}
	public void setEncounter_type(int encounter_type) {
		this.encounter_type = encounter_type;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public int getForm_id() {
		return form_id;
	}
	public void setForm_id(int form_id) {
		this.form_id = form_id;
	}
	public Date getEncounter_datetime() {
		return encounter_datetime;
	}
	public void setEncounter_datetime(Date encounter_datetime) {
		this.encounter_datetime = encounter_datetime;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	public int getVoided() {
		return voided;
	}
	public void setVoided(int voided) {
		this.voided = voided;
	}
	public int getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(int visit_id) {
		this.visit_id = visit_id;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	

}
