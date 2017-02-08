package edu.stevens.cs548.clinic.service.web.soap;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.service.dto.patient.PatientDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.TreatmentNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceRemote;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;

@WebService(endpointInterface="edu.stevens.cs548.clinic.service.web.soap.IPatientWebService",
serviceName="PatientWebService",targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap/patient",
portName="PatientWebPort")

public class PatientWebService {
	//implements IPatientWebService{
	
	@EJB(beanName="PatientServiceBean")
	IPatientServiceRemote service; 
	
	//@Override
	public long createPatient(String name, Date dob, long patientid, int age) throws PatientServiceExn {
		
		return service.createPatient(name, dob, patientid, age);
	}

	//@Override
	public PatientDto getPatientByDbId(long id) throws PatientServiceExn {
		
		return service.getPatientByDbId(id);
	}

	//@Override
	public PatientDto getPatientByPatId(long id) throws PatientServiceExn {
		// TODO Auto-generated method stub
		return service.getPatientByPatId(id);
	}

	//@Override
	public PatientDto[] getPatientsByNameDob(String name, Date dob, int age) throws PatientServiceExn {
		// TODO Auto-generated method stub
		return service.getPatientsByNameDob(name, dob, age);
	}

	//@Override
	/*public void deletePatient(String name, long id) throws PatientServiceExn {
		// TODO Auto-generated method stub
		service.deletePatient(name, id);
	}*/

	//@Override
	public long addDrugTreatment(long id, String diagnosis, String drug, float dosage, long npi) throws PatientNotFoundExn,ProviderNotFoundExn  {
		
		return service.addDrugTreatment(id, diagnosis, drug, dosage, npi);
	}
	
	public long addRadiology(long id,String diagnosis, List<Date> dates, long npi) throws PatientNotFoundExn,ProviderNotFoundExn{
		return service.addRadiology(id, diagnosis, dates, npi);
	}
	
	public long addSurgery(long id,String diagnosis, Date date, long npi) throws PatientNotFoundExn,ProviderNotFoundExn{
		return service.addSurgery(id, diagnosis, date, npi);
	}
	//@Override
	public TreatmentDto[] getTreatments(long id, long[] tids)
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn {
		// TODO Auto-generated method stub
		return service.getTreatments(id, tids);
	}

	//@Override
	public void deleteTreatment(long id, long tid)
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn, TreatmentExn {
		// TODO Auto-generated method stub
		service.deleteTreatment(id, tid);
	}

	//@Override
	public String siteInfo() {
		// TODO Auto-generated method stub
		return service.siteInfo();
	}

}
