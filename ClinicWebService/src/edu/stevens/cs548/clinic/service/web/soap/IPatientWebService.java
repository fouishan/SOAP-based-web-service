package edu.stevens.cs548.clinic.service.web.soap;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.service.dto.patient.PatientDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.TreatmentNotFoundExn;

@WebService(name="IPatientWebPort",targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap/patient")
//this specifies that this is the end point for this Patient web service

@SOAPBinding(
		style=SOAPBinding.Style.DOCUMENT,
		use=SOAPBinding.Use.LITERAL,
		parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)

public interface IPatientWebService {
	
	
	@WebMethod(operationName="create")
	public long createPatient(String name, Date dob, long patientid, int age) throws PatientServiceExn;
	
	@WebMethod
	public PatientDto getPatientByDbId(long id) throws PatientServiceExn;
	
	@WebMethod
	public PatientDto getPatientByPatId(long id) throws PatientServiceExn;
	
	@WebMethod
	public PatientDto[] getPatientsByNameDob(String name, Date dob, int age) throws PatientServiceExn;
	
	//@WebMethod
	//public void deletePatient(String name, long id)throws PatientServiceExn;
	
	@WebMethod
	long addDrugTreatment(long id, String diagnosis, String drug, float dosage, long npi)throws PatientNotFoundExn;
	
	@WebMethod
	long addRadiology(long id,String diagnosis, List<Date> dates, long npi) throws PatientNotFoundExn;
	
	@WebMethod
	long addSurgery(long id,String diagnosis, Date date, long npi) throws PatientNotFoundExn;
	
	@WebMethod
	public TreatmentDto[] getTreatments(long id, long[] tids) throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn;
	
	@WebMethod
	public void deleteTreatment(long id, long tid) throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn, TreatmentExn;

	@WebMethod
	public String siteInfo();
	
}
