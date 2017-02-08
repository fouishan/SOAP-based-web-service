package edu.stevens.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;

import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.service.dto.patient.PatientDto;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

public interface IPatientService {
	
	public class PatientServiceExn extends Exception{
		
		private static final long serialVersionUID = 1L;

		public PatientServiceExn(String m){
			super(m);
		}
	}
	
	public class PatientNotFoundExn extends PatientServiceExn{
		private static final long serialVersionUID = 1L;
		public PatientNotFoundExn(String m){
			super(m);
		}
	}
	
	public class TreatmentNotFoundExn extends PatientServiceExn{
		private static final long serialVersionUID = 1L;
		public TreatmentNotFoundExn(String m){
			super(m);
		}
	}
	public long createPatient(String name, Date dob, long patientid, int age) throws PatientServiceExn;
			
	public PatientDto getPatientByDbId(long id) throws PatientServiceExn;
	
	public PatientDto getPatientByPatId(long id) throws PatientServiceExn;
	
	public PatientDto[] getPatientsByNameDob(String name, Date dob, int age) throws PatientServiceExn;
	
	//public void deletePatient(String name, long id)throws PatientServiceExn;
	
	public long addDrugTreatment(long id, String diagnosis, String drug, float dosage, long npi)throws PatientNotFoundExn,ProviderNotFoundExn;
	
	long addRadiology(long id,String diagnosis, List<Date> dates, long npi) throws PatientNotFoundExn,ProviderNotFoundExn;
	
	long addSurgery(long id,String diagnosis, Date date, long npi) throws PatientNotFoundExn,ProviderNotFoundExn;
	
	public TreatmentDto[] getTreatments(long id, long[] tids) throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn;
	
	public void deleteTreatment(long id, long tid) throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn, TreatmentExn;

	public String siteInfo();
}
