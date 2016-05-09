package edu.uncc.hspms.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import edu.uncc.hspms.model.Encoutnter;
import edu.uncc.hspms.model.Observation;
import edu.uncc.hspms.model.Patient;
import edu.uncc.hspms.model.PatientRowMapper;
import edu.uncc.hspms.model.Visit;

@Service
public class PatientService {

	@Autowired
	private DataSource dataSource;
	
    
	public Patient getPatient(int patientId) {
		Patient p = new Patient();
		p.setName("Prakash from service");
		p.setAddress("UT from service");
	/*	UUID u =UUID.randomUUID();
        String randomUUIDString = u.toString();
		  System.out.println(randomUUIDString);
		try {
			if (dataSource.getConnection() != null)
				System.out.println("found db connection");
			else
				System.out.println("db connection is null");
		} catch (Exception e) {
			e.printStackTrace();
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {

			String sql = " select name,address from patient where patient_id=?";

			List<Patient> list = jdbcTemplate.query(sql,
					new Object[] { patientId }, new PatientRowMapper());

			Patient patient = jdbcTemplate.queryForObject(sql,
					new Object[] { patientId },
					new BeanPropertyRowMapper<Patient>(Patient.class));
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		} 
		*/
		return p;
	}

/*	private void addPatient(Patient patient) {
		String sql = "insert into patient(name,address) values(?,?)";
		//UUID sample_UUID= UUID.randomUUID();
		//System.out.println("Sample UUID: "+sample_UUID);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.update(sql, patient.getName(),
				patient.getAddress());
		System.out.println("addPost count=" + count);
	}
*/
	public void addChartReview(int patient_id,String chartReview) throws SQLException {
	 //	 int encounter_id=0;
	 //    int obs_id=0;
	 //    int visit_id=0;
		UUID u =UUID.randomUUID();
		String uuid=u.toString();
		Visit visit=new Visit();
	     Encoutnter encounter= new Encoutnter();
	     Observation observation = new Observation();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		java.sql.Connection c = jdbcTemplate.getDataSource().getConnection();
		String sql="Select max(visit_id) + 1 as visit_id from openmrs.visit";
		String sql1="Select max(encounter_id) + 1 as encounter_id from openmrs.encounter";
		String sql2="Select max(obs_id) + 1 as obs_id from openmrs.obs";
		Statement stmt = c.createStatement();
		ResultSet rset = stmt.executeQuery(sql);
		rset.first();
		visit.setVisit_id(rset.getInt("visit_id"));
		rset = stmt.executeQuery(sql1);
		rset.first();
		encounter.setEncounter_id(rset.getInt("encounter_id"));
		rset = stmt.executeQuery(sql2);
		rset.first();
		observation.setObs_id(rset.getInt("obs_id"));
		
		Patient patient = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Patient>(Patient.class));
		String sql_visit = "INSERT INTO openmrs.visit (visit_id, patient_id, visit_type_id, date_started, date_stopped, location_id, creator, date_created, changed_by, date_changed, voided, uuid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		int count_visit = jdbcTemplate.update(sql_visit,visit.getVisit_id(),patient_id,visit.getVisit_type_id(),visit.getDate_started(),visit.getDate_stopped(),visit.getLocation_id(),visit.getCreator(),visit.getDate_created(),visit.getChanged_by(),visit.getDate_changed(),visit.getVoided(),uuid);
		System.out.println("Visit count=" + count_visit);
		
		String sql_encounter="INSERT INTO openmrs.encounter (encounter_id, encounter_type, patient_id, location_id, form_id, encounter_datetime, creator, date_created, voided, visit_id, uuid) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		int count_encounter= jdbcTemplate.update(sql_encounter,encounter.getEncounter_id(),encounter.getEncounter_type(),patient_id,encounter.getLocation_id(),encounter.getForm_id(),encounter.getEncounter_datetime(),encounter.getCreator(),encounter.getDate_created(),encounter.getVoided(),visit.getVisit_id(),uuid);
		System.out.println("Encounter count"+ count_encounter);
		
		String sql_observation="INSERT INTO openmrs.obs (obs_id, person_id, concept_id, encounter_id, obs_datetime, location_id, value_text, creator, date_created) VALUES (?,?,?,?,?,?,?,?,?);";
		int count_observation=jdbcTemplate.update(sql_observation, observation.getObs_id(),patient_id,observation.getConcept_id(),encounter.getEncounter_id(),observation.getObs_datetime(),observation.getLocation_id(),chartReview,observation.getCreator(),observation.getDate_created());
		System.out.println("Observation count"+ count_observation);
		System.out.println("Chart Review Sucessfully created for PatientID : "+patient_id);
	}
}
