package edu.stevens.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs548.clinic.domain.ITreatmentExporter;
import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IProviderDAO;
import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.ProviderDAO;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.patient.PatientDto;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;

/**
 * Session Bean implementation class PatientService
 */
@Stateless(name="PatientServiceBean")
//@LocalBean
public class PatientService implements IPatientServiceRemote, IPatientServiceLocal {

private PatientFactory patientFactory;
	
	private IPatientDAO patientDAO;
	private IProviderDAO providerDAO;
	
    /**
     * Default constructor. 
     */
    public PatientService() {
    	patientFactory = new PatientFactory();
    }

    @PersistenceContext(unitName="ClinicDomain")
	private EntityManager em;
	
	@PostConstruct
	private void initialize(){
		patientDAO = new PatientDAO(em);
		providerDAO = new ProviderDAO(em);
	}
    /*
      First constructor is called, then dependency injection is done
     */
	/**
     * @see IPatientService#getPatientsByNameDob(String, Date)
     */
	@Override
    public PatientDto[] getPatientsByNameDob(String name, Date dob, int age) throws PatientServiceExn{
    	try{
        List<Patient> patients = patientDAO.getPatientByNameDob(name, dob);
        PatientDto[] dto = new PatientDto[patients.size()];
        for(int i=0; i<dto.length; i++){
        	dto[i]= new PatientDto(patients.get(i));
        }
        return dto;
    	}catch(PatientExn e){
    		throw new PatientServiceExn(e.toString());
    	}	
    }

        
	/**
     * @see IPatientService#getPatientByDbId(long)
     */
	@Override
	public PatientDto getPatientByDbId(long id) throws PatientServiceExn {
        // TODO Auto-generated method stub
    	try{
    		Patient patient = patientDAO.getPatient(id);//getting patient entity from db
    		return new PatientDto(patient);//extract data from the patient entity, copied into the dto and then return the dto
    	}catch(PatientExn e){
    		throw new PatientServiceExn(e.toString());
    	}		
    }

	/**
     * @see IPatientService#getPatientByPatId(long)
     */
	@Override
	public PatientDto getPatientByPatId(long pid) throws PatientServiceExn{
    	try{
    		Patient patient = patientDAO.getPatientByPatientId(pid);//getting patient entity from db
    		return new PatientDto(patient);//extract data from the patient entity, copied into the dto and then return the dto
    	}catch(PatientExn e){
    		throw new PatientServiceExn(e.toString());
    	}		
    }
    /**
     * @see IPatientService#createPatient(String, Date, long)
     */
	@Override
	public long createPatient(String name, Date dob, long patientid, int age) throws PatientServiceExn {
    	
    	try{
    		Patient patient= this.patientFactory.createPatient(patientid, name, dob, age);
    		patientDAO.addPatient(patient);
    		return patient.getId();
    	}catch(PatientExn e){
    		throw new PatientServiceExn(e.toString());
    	}		
    	
    }

	/**
     * @see IPatientService#deletePatient(String, long)
     */
   
	
	@Override
	public long addDrugTreatment(long id, String diagnosis, String drug, float dosage, long npi) throws PatientNotFoundExn, ProviderNotFoundExn {
		try{
			Provider pro= providerDAO.getProviderByNPI(npi);
			Patient patient= patientDAO.getPatient(id);
			long tid = patient.addDrugTreatment(diagnosis, drug, dosage, pro);
			return tid;
		}catch(PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		}catch(ProviderExn e2){
			throw new ProviderNotFoundExn(e2.toString());
		}
		
	}
	
	@Override
	public long addRadiology(long id,String diagnosis, List<Date> dates, long npi) throws PatientNotFoundExn, ProviderNotFoundExn {
		try{
			Provider pro= providerDAO.getProviderByNPI(npi);
			Patient patient= patientDAO.getPatient(id);
			long tid=patient.addRadiology(diagnosis, dates, pro);
			return tid;
		}catch(PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		}catch(ProviderExn e2){
			throw new ProviderNotFoundExn(e2.toString());
		}
		
	}
	
	@Override
	public long addSurgery(long id,String diagnosis, Date date, long npi) throws PatientNotFoundExn, ProviderNotFoundExn {
		try{
			Provider pro= providerDAO.getProviderByNPI(npi);
			Patient patient= patientDAO.getPatient(id);
			long tid=patient.addSurgery(diagnosis, date, pro);
			return tid;
		}catch(PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		}catch(ProviderExn e2){
			throw new ProviderNotFoundExn(e2.toString());
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
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn {
		try{
			Patient patient= patientDAO.getPatientByPatientId(id);
			TreatmentDto[] treatments = new TreatmentDto[tids.length];
			for(int i=0; i<tids.length; i++){
				TreatmentPDOtoDTO visitor = new TreatmentPDOtoDTO();
				patient.exportTreatment(tids[i], visitor);
				treatments[i]= visitor.getDTO();
			}
			return treatments;
		}catch(PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		}catch(TreatmentExn e){
			throw new PatientServiceExn(e.toString());
		}
		
	}
	@Override
	public void deleteTreatment(long id, long tid) 
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn, TreatmentExn {
		try{
			Patient patient= patientDAO.getPatient(id);
			patient.deleteTreatment(tid);
		}catch(PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		}
		
	}
	
	@Resource(name="SiteInfo")
	private String siteInformation;
	
	@Override
	public String siteInfo() {
		return siteInformation;
	}
}
