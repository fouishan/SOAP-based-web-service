package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Treatment
 *
 */
/*
 * TODO
 */
@Entity
@Table(name="Treatment")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TreatmentType")

public abstract class Treatment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * TODO
	 */
	@Id
	@GeneratedValue
	private long id;
	private String diagnosis;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	/*
	 * TODO
	 */
	@Column(name="TreatmentType", length=1)
	private String treatmentType;
	
	public String getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/*
	 * TODO
	 */
	@ManyToOne
	@JoinColumn(name="patient_fk", referencedColumnName="id")
	private Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
		if (!patient.getTreatments().contains(this))
			patient.addTreatment(this);
	}
	
	@ManyToOne
	@JoinColumn(name="provider_fk", referencedColumnName="npi")
	private Provider provider;

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
		// More logic in the domain model.
	}

	
	public abstract void export(ITreatmentExporter visitor);
	
	
	public Treatment() {
		super();
	}
   
}
