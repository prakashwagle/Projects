package edu.uncc.hspms.model;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Visit {
	int visit_id;
	int patient_id;
	int visit_type_id=1;
	Date date_started=new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	Date date_stopped=new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	int location_id=9;
	int creator=1;
	Date date_created=new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	int changed_by=1;
	int voided=0;
	Date date_changed=new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	UUID uuid=UUID.randomUUID();
	
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
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getVisit_type_id() {
		return visit_type_id;
	}
	public void setVisit_type_id(int visit_type_id) {
		this.visit_type_id = visit_type_id;
	}
	public Date getDate_started() {
		return date_started;
	}
	public void setDate_started(Date date_started) {
		this.date_started = date_started;
	}
	public Date getDate_stopped() {
		return date_stopped;
	}
	public void setDate_stopped(Date date_stopped) {
		this.date_stopped = date_stopped;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
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
	public int getChanged_by() {
		return changed_by;
	}
	public void setChanged_by(int changed_by) {
		this.changed_by = changed_by;
	}
	public Date getDate_changed() {
		return date_changed;
	}
	public void setDate_changed(Date date_changed) {
		this.date_changed = date_changed;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	

}
