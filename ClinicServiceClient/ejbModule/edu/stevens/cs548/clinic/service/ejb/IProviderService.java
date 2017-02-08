package edu.stevens.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;

import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.service.dto.provider.ProviderDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

public interface IProviderService {

public class ProviderServiceExn extends Exception{
		
		private static final long serialVersionUID = 1L;

		public ProviderServiceExn(String m){
			super(m);
		}
	}
	
	public class ProviderNotFoundExn extends ProviderServiceExn{
		private static final long serialVersionUID = 1L;
		public ProviderNotFoundExn(String m){
			super(m);
		}
	}
	
	public class TreatmentNotFoundExn extends ProviderServiceExn{
		private static final long serialVersionUID = 1L;
		public TreatmentNotFoundExn(String m){
			super(m);
		}
	}
	
	public long createProvider(long npi, String name, String spec) throws ProviderServiceExn;
	
	public ProviderDto getProviderByNpi(long npi) throws ProviderServiceExn;
	
	public ProviderDto getProvider(long id)throws ProviderServiceExn;
	
	public long addDrugTreatment(long id, String diagnosis, String drug, float dosage, long p)throws ProviderNotFoundExn,PatientNotFoundExn;
	
	public long addRadiology(long id,String diagnosis, List<Date> dates, long p) throws ProviderNotFoundExn,PatientNotFoundExn;
	
	public long addSurgery(long id,String diagnosis, Date date, long p) throws ProviderNotFoundExn,PatientNotFoundExn;
	
	public TreatmentDto[] getTreatments(long id, long[] tids) throws ProviderNotFoundExn, TreatmentNotFoundExn, ProviderServiceExn;
	
	public String siteInfo();
}
