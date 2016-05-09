package edu.uncc.hspms.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.uncc.hspms.model.Chartreview;
import edu.uncc.hspms.model.Patient;
import edu.uncc.hspms.service.PatientService;


@Controller
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@RequestMapping(value="/getPatient/{patientId}", method=RequestMethod.GET)
	public @ResponseBody Patient getPatient(@PathVariable("patientId") int patientId)
	{
		System.out.println("rcvd patientId="+patientId);
		
		return patientService.getPatient(patientId);
	}
	
	@RequestMapping(value="/recommend", method=RequestMethod.POST)
	public @ResponseBody String submitPatient(@RequestBody Chartreview chartreview)
	{
		System.out.println("Patient ID: "+chartreview.getPatient_id()+" Vist Note:"+chartreview.getVisitnote());
		return "Ok";
	}
	
	@RequestMapping(value="/submitChartReview",method=RequestMethod.POST)
	public String submitChartReview(@RequestBody Chartreview chartreview) throws SQLException
	{
		patientService.addChartReview(chartreview.getPatient_id(),chartreview.getVisitnote());
		return  String.valueOf(HttpStatus.CREATED);
				
	}
}
