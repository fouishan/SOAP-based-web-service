package edu.stevens.cs548.clinic.service.web.soap;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


import edu.stevens.cs548.clinic.service.dto.provider.ProviderDto;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.TreatmentNotFoundExn;

@WebService(name="IProviderWebPort",targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap/provider")
@SOAPBinding(
		style=SOAPBinding.Style.DOCUMENT,
		use=SOAPBinding.Use.LITERAL,
		parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public interface IProviderWebService {
	
	@WebMethod(operationName="create")
	public long createProvider(long npi, String name, String spec) throws ProviderServiceExn;
	
	@WebMethod
	public ProviderDto getProviderByNpi(long npi) throws ProviderServiceExn;
	
	@WebMethod
	public ProviderDto getProvider(long id)throws ProviderServiceExn;
	
	@WebMethod
	public void addDrugTreatment(long id, String diagnosis, String drug, float dosage, long p)throws ProviderNotFoundExn,PatientNotFoundExn;
	
	@WebMethod
	public void addRadiology(long id,String diagnosis, List<Date> dates, long p) throws ProviderNotFoundExn,PatientNotFoundExn;
	
	@WebMethod
	public void addSurgery(long id,String diagnosis, Date date, long p) throws ProviderNotFoundExn,PatientNotFoundExn;
	
	@WebMethod
	public TreatmentDto[] getTreatments(long id, long[] tids) throws ProviderNotFoundExn, TreatmentNotFoundExn, ProviderServiceExn;
	
	@WebMethod
	public String siteInfo();
}
