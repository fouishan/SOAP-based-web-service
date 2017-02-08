package edu.stevens.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IProviderDAO;
import edu.stevens.cs548.clinic.domain.ITreatmentExporter;
import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.ProviderDAO;
import edu.stevens.cs548.clinic.domain.ProviderFactory;
import edu.stevens.cs548.clinic.domain.Treatment;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.patient.PatientDto;
import edu.stevens.cs548.clinic.service.dto.provider.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.PatientService.TreatmentPDOtoDTO;

@Stateless(name="ProviderServiceBean")
public class ProviderService implements IProviderServiceLocal, IProviderServiceRemote {

	private ProviderFactory providerFactory;
	
	private IPatientDAO patientDAO;
	private IProviderDAO providerDAO;
	
	public ProviderService() {
        providerFactory = new ProviderFactory();
        //patientDAO = new PatientDAO(em); em is nill
        
    }
	
	@PersistenceContext(unitName="ClinicDomain")
	private EntityManager em;
	
	@PostConstruct
	private void initialize(){
		providerDAO = new ProviderDAO(em);
		patientDAO = new PatientDAO(em);
	}
	
	@Override
	public long createProvider(long npi, String name, String spec) throws ProviderServiceExn {
		try{
    		Provider provider= this.providerFactory.createProvider(npi, name, spec);
    		providerDAO.addProvider(provider);
    		return provider.getNpi();
		}catch(ProviderExn e){
    		throw new ProviderServiceExn(e.toString());
    	}		
	}

	@Override
	public ProviderDto getProviderByNpi(long npi) throws ProviderServiceExn {
		try{
    		Provider provider = providerDAO.getProviderByNPI(npi);//getting patient entity from db
    		return new ProviderDto(provider);//extract data from the patient entity, copied into the dto and then return the dto
    	}catch(ProviderExn e){
    		throw new ProviderServiceExn(e.toString());
    	}		
    }
	

	@Override
	public ProviderDto getProvider(long id) throws ProviderServiceExn {
		try{
    		Provider provider = providerDAO.getProvider(id);//getting patient entity from db
    		return new ProviderDto(provider);//extract data from the patient entity, copied into the dto and then return the dto
    	}catch(ProviderExn e){
    		throw new ProviderServiceExn(e.toString());
    	}	
	}

	@Override
	public long addDrugTreatment(long id, String diagnosis, String drug, float dosage, long p)
			throws ProviderNotFoundExn, PatientNotFoundExn {
		try{
			Patient pat = patientDAO.getPatientByPatientId(p);
			Provider provider = providerDAO.getProviderByNPI(id);
			long tid = provider.addDrugTreatment(diagnosis, drug, dosage, pat);
			return tid;
		}catch(ProviderExn e){
			throw new ProviderNotFoundExn(e.toString());
		}catch(PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		}

	}

	@Override
	public long addRadiology(long id, String diagnosis, List<Date> dates, long p) throws ProviderNotFoundExn,PatientNotFoundExn {
		try{
			Patient pat = patientDAO.getPatientByPatientId(p);
			Provider provider = providerDAO.getProviderByNPI(id);
			long tid=provider.addRadiology(diagnosis, dates, pat);
			return tid;
		}catch(ProviderExn e){
			throw new ProviderNotFoundExn(e.toString());
		}catch(PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		}

	}

	@Override
	public long addSurgery(long id, String diagnosis, Date date, long p) throws ProviderNotFoundExn,PatientNotFoundExn {
		try{
			Patient pat = patientDAO.getPatientByPatientId(id);
			Provider provider = providerDAO.getProviderByNPI(p);
			long tid=provider.addSurgery(diagnosis, date, pat);
			return tid;
		}catch(ProviderExn e){
			throw new ProviderNotFoundExn(e.toString());
		}catch(PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		}

	}
	
static class TreatmentPDOtoDTO implements ITreatmentExporter{
		
		private TreatmentDto dto;
		public TreatmentDto getDTO(){
			return dto;
		}
		@Override
		public void exportDrugTreatment(long tid, String diagnosis, String drug, float dosage) {
			dto=new TreatmentDto();
			dto.setDiagnosis(diagnosis);
			DrugTreatmentType drugInfo = new DrugTreatmentType();
			drugInfo.setDosage(dosage);
			drugInfo.setName(drug);
			dto.setDrugTreatment(drugInfo);
			
		}

		@Override
		public void exportRadiology(long tid, String diagnosis, List<Date> dates) {
			dto=new TreatmentDto();
			dto.setDiagnosis(diagnosis);
			RadiologyType radiology = new RadiologyType();
			radiology.setDate(dates);
			dto.setRadiology(radiology);
			
		}

		@Override
		public void exportSurgery(long tid, String diagnosis, Date date) {
			dto=new TreatmentDto();
			dto.setDiagnosis(diagnosis);
			SurgeryType surgery = new SurgeryType();
			surgery.setDate(date);
			dto.setSurgery(surgery);
			
		}
		
	}

	@Override
	public TreatmentDto[] getTreatments(long id, long[] tids)
			throws ProviderNotFoundExn, TreatmentNotFoundExn, ProviderServiceExn {
		try{
			Provider provider = providerDAO.getProvider(id);
			TreatmentDto[] treatments = new TreatmentDto[tids.length];
			for(int i=0; i<tids.length; i++){
				TreatmentPDOtoDTO visitor = new TreatmentPDOtoDTO();
				provider.exportTreatment(tids[i], visitor);
				treatments[i]= visitor.getDTO();
			}
			return treatments;
		}catch(ProviderExn e){
			throw new ProviderNotFoundExn(e.toString());
		}catch(TreatmentExn e){
			throw new ProviderServiceExn(e.toString());
		}
	}

	@Resource(name="SiteInfo")
	private String siteInformation;
	
	@Override
	public String siteInfo() {
		return siteInformation;
	}
	
}
