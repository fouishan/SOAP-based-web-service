package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.*;
import static javax.persistence.CascadeType.REMOVE;

import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;

/**
 * Entity implementation class for Entity: Patient
 *
 */
/*
 * TODO
 */



/*
 * TODO
 */
@Entity
@NamedQueries({
	@NamedQuery(
		name="SearchPatientByPatientID",
		query="select p from Patient p where p.patientId = :pid"),
	@NamedQuery(
			name="SearchPatientByNameDOB",
			query="select p from Patient p where p.name = :name and p.dob = :dob"),
		
	@NamedQuery(
		name="CountPatientByPatientID",
		query="select count(p) from Patient p where p.patientId = :pid"),
	/*@NamedQuery(
		name = "RemoveAllPatients", 
		query = "delete from Patient p")*/
})
@Table(name="PATIENT")
public class Patient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	private long patientId; 
	@Temporal(TemporalType.DATE)
	private Date dob;
	private String name;
	
	
	public Long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public Long getpatientId() {
		return this.patientId;
	}

	public void setpatientId(long patientId) {
		this.patientId = patientId;
	}
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(cascade = REMOVE, mappedBy = "patient")
	@OrderBy 
	private List<Treatment> treatments;
	
	protected void setTreatments(List<Treatment> treatments){
		this.treatments = treatments;
	}
	protected List<Treatment> getTreatments(){
		return treatments;
	}
	@Transient
	private ITreatmentDAO treatmentDAO;
	
	public void setTreatmentDAO (ITreatmentDAO tdao) {
		this.treatmentDAO = tdao;
	}
	
	public long addTreatment (Treatment t) {
		// Persist treatment and set forward and backward links
		this.treatmentDAO.addTreatment(t);
		this.getTreatments().add(t);
		if (t.getPatient() != this) {
			t.setPatient(this);
		}
		return t.getId();
	}
	
	public long addDrugTreatment(String diagnosis, String drug, float dosage, Provider pro){
		//TODO: Add provider parameter when adding treatments to a patient.
		DrugTreatment treatment= new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDosage(dosage);
		treatment.setDrug(drug);
		treatment.setProvider(pro);
		treatment.setTreatmentType("Drug Treatment");
		this.addTreatment(treatment);
		return pro.getNpi();
	}
	
	public long addRadiology(String diagnosis, List<Date> dates, Provider pro){
		Radiology treatment= new Radiology();
		treatment.setDiagnosis(diagnosis);
		treatment.setDate(dates);
		treatment.setProvider(pro);
		treatment.setTreatmentType("Radiology");
		this.addTreatment(treatment);
		return pro.getNpi();
	}
	
	public long addSurgery(String diagnosis, Date date, Provider pro){
		Surgery treatment= new Surgery();
		treatment.setDiagnosis(diagnosis);
		treatment.setsurgerydate(date);
		treatment.setProvider(pro);
		treatment.setTreatmentType("Surgery");
		this.addTreatment(treatment);
		return pro.getNpi();
	}
	/*
	public void getTreatmentIds(List<Long> treatmentIds) {
		for (Treatment t : this.getTreatments()) {
			treatmentIds.add(t.getId());
		}
	}*/
	
	public List<Long> getTreatmentIds(){
		List<Long> tids = new ArrayList<Long>();
		for(Treatment t: this.getTreatments()){
			tids.add(t.getId());
		}
		return tids;
	}
	
	public void deleteTreatment(long tid) throws TreatmentExn{
		Treatment t = treatmentDAO.getTreatmentByDbId(tid);
		if(t.getPatient()== this){
			throw new TreatmentExn("Inappropriate treatment access: patient = " + id + ", treatment = " + tid);
		}
		treatmentDAO.deleteTreatment(t);
	}
/*	
	public void visitTreatment(long tid, ITreatmentVisitor visitor) throws TreatmentExn{
		Treatment t = treatmentDAO.getTreatmentByDbId(tid);
		if(t.getPatient()== this){
			throw new TreatmentExn("Inappropriate treatment access: patient = " + id + ", treatment = " + tid);
		}
		t.visit(visitor);
	}*/
	public void exportTreatment(long tid, ITreatmentExporter visitor) throws TreatmentExn {
		// Export a treatment without violated Aggregate pattern
		// Check that the exported treatment is a treatment for this patient.
		Treatment t = treatmentDAO.getTreatmentByDbId(tid);
		if (t.getPatient() != this) {
			throw new TreatmentExn("Inappropriate treatment access: patient = " + id + ", treatment = " + tid);
		}
		t.export(visitor);
	}
	
	public Patient() {
		super();
		treatments = new ArrayList<Treatment>();
	}
   
}
