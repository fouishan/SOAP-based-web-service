package edu.stevens.cs548.clinic.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.patient.PatientDto;
import edu.stevens.cs548.clinic.service.dto.provider.ProviderDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;

/**
 * Session Bean implementation class InitBean
 */
@Singleton
@LocalBean
@Startup
public class InitBean {
	
	private static Logger logger = Logger.getLogger(InitBean.class.getCanonicalName());
	private static void info(String m) {
		logger.info(m);
	}
	/**
     * Default constructor. 
     */
    public InitBean() {
        // TODO Auto-generated constructor stub
    }
    

	@Inject
	// (beanName = "PatientServiceBean")
	private IPatientService localPatientService;
	@Inject
	// (beanName = "ProviderServiceBean")
	private IProviderService localProviderService;

	@PostConstruct
	public void init() {
		info("My name is Ishan Shah");
		info(" initializing the service..");

		// Add patient
		try {
			localPatientService.createPatient("John Doe", getDate("10/09/1993"), 1, 23);
			info("addPatient: patient1, name: John Doe, pid= 1");
		} catch (PatientServiceExn e) {
			e.printStackTrace();
			info("addPatient Error");
		}

		//Get Patients by name and dob
		PatientDto[] patients;
		try {
			patients = localPatientService.getPatientsByNameDob("John Doe", getDate("10/09/1993"), 23);
			for (PatientDto ps : patients) {
				info("Retrieve a list of patients by name and date of birth. Patient ID: "
						+ ps.getPatientId());
			}
		} catch (PatientServiceExn e2) {
			info("Error in Get Patients by name and dob");
			e2.printStackTrace();
		}
		

		// get patient by pat id
		try {
			PatientDto patient = localPatientService.getPatientByPatId(1);
			info("Retrieve a patient by patient identifier, patient name: "
					+ patient.getName());
		} catch (PatientServiceExn e) {
			info("Cannot find patient with pid=1");
			e.printStackTrace();
		}

		// get patient by db id
		try {
			PatientDto patient2 = localPatientService.getPatientByDbId(1);
			info("Retrieve patient by primary id, patient name =: "
					+ patient2.getName());
		} catch (PatientServiceExn e) {
			info("cannot find patient with primary_id = 1");
			e.printStackTrace();
		}


		
		// add a provider
		try {
			
			localProviderService.createProvider(1, "Joe", "Psychologist");
			info("add a new provider: 1, provider1");
		} catch (ProviderServiceExn e) {
			info("add a new provider error");
			e.printStackTrace();
		}

		//
		List<Long> tids = new ArrayList<Long>();
		try {
			long tid = localPatientService.addDrugTreatment(1, "Fever", "Crocin", (float) 5.0, 1);
			tids.add(tid);
			info("Primary key of the new treatment: " + tid);
		} catch (PatientNotFoundExn e) {
			info(" add Drug Treatment error PATIENT ");
			e.printStackTrace();
		}catch (ProviderNotFoundExn e) {
			info("add DrugTreatment error provider!");
			e.printStackTrace();
		}
		
		// get treatment by using patient ID and treatment id - tids.
		if (tids != null) {
			long[] ids = new long[tids.size()];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = tids.get(i);
			}
			try {
				localPatientService.getTreatments(1, ids);
				info("getTreatments succeed for patient!");
			} catch (PatientServiceExn e) {
				info("getTreatments error for patient!");
				e.printStackTrace();
			}
		}

	
		try{
			localProviderService.createProvider(2, "DrFarber", "Radiology");
			info("Provider added: NPI 2. Name: Dr.Farber. ");
			
		}catch(ProviderServiceExn e){
			e.printStackTrace();
			info("createProvider Error");
		}
		
		//create provider
				
		// get provider by npi
		try {
			ProviderDto provider = localProviderService.getProviderByNpi(2);
			info("Retrieve provider by NPI, provider name: "+provider.getName());
		} catch (ProviderServiceExn e1) {
			e1.printStackTrace();
			info("Retrieve provider by NPI error");
		}
		
		// get provider by db id 
		try {
			
			ProviderDto provider = localProviderService.getProvider(2);
			info("Retrieve provider by db id, provider name: "+provider.getName());
		} catch (ProviderServiceExn e1) {
			e1.printStackTrace();
			info("Retrieve provider by db id error");
		}
		
		//
		try {
			long id=localPatientService.createPatient("Peter", getDate("18/09/1993"), 2, 23);
			info("addPatient 2 succeed");
		} catch (PatientServiceExn e) {
			info("addPatient 2  error.");
			e.printStackTrace();
		}
		
		//
		long treatmentId2=0;
		try {
			treatmentId2 = localPatientService.addDrugTreatment(1, "Headache", "Disprin", (float) 8.0, 1);
			info("Added a drug treatment for a patient succesfully .  ");
		} catch (PatientNotFoundExn | ProviderNotFoundExn e) {
			info("Error while Adding a treatment for a patient");
			e.printStackTrace();
		}
		 
		
		long treatmentId3=0;
		try {
			treatmentId3 = localPatientService.addSurgery(1, "Fracture", getDate("18/09/2013"), 2);
			info("Error while Adding a treatment for a patient ");
		} catch (PatientNotFoundExn | ProviderNotFoundExn e) {
			info("Added a surgery treatment for a patient succesfully . ");
			e.printStackTrace();
		}
		
		
		List<Date> dateList =  new ArrayList<Date>();
    	dateList.add(getDate("10/09/2015"));
    	dateList.add(getDate("11/09/2015"));
    	dateList.add(getDate("12/09/2015"));
    	
		long treatmentId4=0;
		try {
			treatmentId4 = localPatientService.addRadiology(1, "Cancer", dateList, 1);
			info("Added a radiology treatment for a patient succesfully .  ");
		} catch (PatientNotFoundExn | ProviderNotFoundExn e) {
			info("Error while Adding a treatment for a patient. ");
			e.printStackTrace();
		}
		// get treatment by using provider ID and treatment id - tids.
				if (tids != null) {
					long[] ids = new long[tids.size()];
					for (int i = 0; i < ids.length; i++) {
						ids[i] = tids.get(i);
					}
					try {
						localProviderService.getTreatments(treatmentId2, ids);
						info("getTreatments succeed for provider!");
					}catch ( ProviderServiceExn e) {
						info("getTreatments by Provider Id error.");
						e.printStackTrace();
					}	
				}
	}

	 public Date getDate(String stringDate){
	    	Date date = null;
			try {
				String pattern = "MM/dd/yyyy";
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				 date = format.parse(stringDate);
			} catch (Exception e) {
				IllegalStateException ex = new IllegalStateException("Error while setting state");
				ex.initCause(e);
				throw ex;
			}
			return date;
	    }

}
