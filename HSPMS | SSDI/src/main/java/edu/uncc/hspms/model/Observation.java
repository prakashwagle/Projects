package edu.uncc.hspms.model;
import java.util.*;

public class Observation {
	int obs_id; 
	int person_id;
	int concept_id=162169;
	int encounter_id;
	Date obs_datetime=new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	int location_id=9;
	String value_text="";
	int creator=1;
	Date date_created=new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	int voided=0;
	
	public int getObs_id() {
		return obs_id;
	}
	public void setObs_id(int obs_id) {
		this.obs_id = obs_id;
	}
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public int getConcept_id() {
		return concept_id;
	}
	public void setConcept_id(int concept_id) {
		this.concept_id = concept_id;
	}
	public int getEncounter_id() {
		return encounter_id;
	}
	public void setEncounter_id(int encounter_id) {
		this.encounter_id = encounter_id;
	}

	public Date getObs_datetime() {
		return obs_datetime;
	}
	public void setObs_datetime(Date obs_datetime) {
		this.obs_datetime = obs_datetime;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public String getValue_text() {
		return value_text;
	}
	public void setValue_text(String value_text) {
		this.value_text = value_text;
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
	
	
}
