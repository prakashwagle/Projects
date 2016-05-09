package edu.uncc.hspms.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PatientRowMapper implements RowMapper<Patient> {

	public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Patient p = new Patient();
		p.setName(rs.getString("name"));
		p.setAddress(rs.getString("address"));
		return p;
	}

}
