package edu.stevens.cs548.clinic.service.web.soap;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.service.dto.provider.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceRemote;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.TreatmentNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceRemote;

@WebService(
		endpointInterface="edu.stevens.cs548.clinic.service.web.soap.IProviderWebService",
		serviceName="ProviderWebService",
		targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap/provider",
		portName="ProviderWebPort")
public class ProviderWebService implements IProviderWebService {
	
	@EJB(beanName="ProviderServiceBean")
	IProviderServiceRemote service; 
	

	//@Override
	public long createProvider(long npi, String name, String spec) throws ProviderServiceExn {
		return service.createProvider(npi, name, spec);
	}

	//@Override
	public ProviderDto getProviderByNpi(long npi) throws ProviderServiceExn {
		return service.getProviderByNpi(npi);
	}

	//@Override
	public ProviderDto getProvider(long id) throws ProviderServiceExn {
		return service.getProvider(id);
	}

	//@Override
	public void addDrugTreatment(long id, String diagnosis, String drug, float dosage, long p)
			throws ProviderNotFoundExn,PatientNotFoundExn {
		service.addDrugTreatment(id, diagnosis, drug, dosage, p);

	}

	//@Override
	public void addRadiology(long id, String diagnosis, List<Date> dates, long p) throws ProviderNotFoundExn,PatientNotFoundExn {
		service.addRadiology(id, diagnosis, dates, p);

	}

	//@Override
	public void addSurgery(long id, String diagnosis, Date date, long p) throws ProviderNotFoundExn,PatientNotFoundExn {
		service.addSurgery(id, diagnosis, date, p);

	}

	//@Override
	public TreatmentDto[] getTreatments(long id, long[] tids)
			throws ProviderNotFoundExn, TreatmentNotFoundExn, ProviderServiceExn {
		return service.getTreatments(id, tids);
	}

	//@Override
	public String siteInfo() {
		return service.siteInfo();
	}

}
