package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

public interface ITreatmentExporter {
	
	public void exportDrugTreatment (long tid,
									 String diagnosis,
							   		 String drug,
							   		 float dosage);
	
	public void exportRadiology (long tid,
								 String diagnosis,
								 List<Date> dates);
	
	public void exportSurgery (long tid,
						 	   String diagnosis,
			                   Date date);

}
