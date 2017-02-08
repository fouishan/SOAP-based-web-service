package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

public interface ITreatmentFactory {
	
	public Treatment createDrugTreatment (String diagnosis, String drug, float dosage);
	public Surgery createSurgery(String diagnosis, Date date);
	public Radiology createRadiology(String diagnosis,List<Date> dates);
	/*
	 * TODO add methods for Radiology, Surgery
	 */

}
