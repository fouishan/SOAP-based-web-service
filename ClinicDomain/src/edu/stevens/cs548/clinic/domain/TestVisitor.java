package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

public class TestVisitor implements ITreatmentExporter{

	@Override
	public void exportDrugTreatment(long tid, String diagnosis, String drug, float dosage) {
		DrugTreatment d =new DrugTreatment();
		d.setId(tid);
		d.setDiagnosis(diagnosis);
		d.setDosage(dosage);
		d.setDrug(drug);
		
	}

	@Override
	public void exportRadiology(long tid, String diagnosis, List<Date> dates) {
		Radiology treatment= new Radiology();
		treatment.setDiagnosis(diagnosis);
		treatment.setDate(dates);
		
	}

	@Override
	public void exportSurgery(long tid, String diagnosis, Date date) {
		Surgery treatment= new Surgery();
		treatment.setDiagnosis(diagnosis);
		treatment.setsurgerydate(date);
		
	}

}
