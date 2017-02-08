package edu.stevens.cs548.clinic.service.dto.patient;

import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.service.dto.patient.PatientDto;
import edu.stevens.cs548.clinic.service.dto.patient.ObjectFactory;

public class PatientDtoFactory {
	
	ObjectFactory factory;
	
	public PatientDtoFactory() {
		factory = new ObjectFactory();
	}
	
	public PatientDto createPatientDto () {
		return factory.createPatientDto();
	}
	
	public PatientDto createPatientDto (Patient p) {
		PatientDto d = factory.createPatientDto();
		d.setId(p.getId());
		d.setDob(p.getDob());
		d.setName(p.getName());
		d.setPatientId(p.getpatientId());
		return d;
	}

}
