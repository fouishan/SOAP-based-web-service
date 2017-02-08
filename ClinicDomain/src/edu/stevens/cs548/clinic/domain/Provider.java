package edu.stevens.cs548.clinic.domain;

import static javax.persistence.CascadeType.REMOVE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;

/**
 * Entity implementation class for Entity: Provider
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(
		name="SearchProviderByNPI",
		query="select prov from Provider prov where prov.npi = :npi"),
	@NamedQuery(
			name="SearchProviderById",
			query="select prov from Provider prov where prov.id = :proid"),
	@NamedQuery(
		name="CountProviderByNPI",
		query="select count(prov) from Provider prov where prov.npi = :npi"),
	/*@NamedQuery(
		name = "RemoveAllProviders", 
		query = "delete from Provider prov")*/
})
@Table(name="PROVIDER")
public class Provider implements Serializable {

	   
	@Id
	@GeneratedValue
	private long id;
	private long npi;
	private String name;
	private String Specialization;
	private static final long serialVersionUID = 1L;
	
	public long getId() {
		return this.id;
	}

	public void setId(long proid) {
		this.id = proid;
	}   
 	public long getNpi() {
		return this.npi;
	}

	public void setNpi(long npi) {
		this.npi = npi;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getSpecialization() {
		return this.Specialization;
	}

	public void setSpecialization(String Specialization) {
		this.Specialization = Specialization;
	}
	
	
	
	@OneToMany(cascade = REMOVE, mappedBy = "provider")
	@OrderBy 
	private List<Treatment> treatments;
	/*
	public void setTreatments(List<Treatment> treatments){
		this.treatments = treatments;
	}*/
	public List<Treatment> getTreatments(){
		return treatments;
	}
	@Transient
	private ITreatmentDAO treatmentDAO;
	
	public void setTreatmentDAO (ITreatmentDAO tdao) {
		this.treatmentDAO = tdao;
	}
	
	public long addTreatment (Treatment t) {
		this.treatmentDAO.addTreatment(t);
		this.getTreatments().add(t);
		if (t.getProvider() != this) {
			t.setProvider(this);
		}
		return t.getId();
	}
	
	public long addDrugTreatment(String diagnosis, String drug, float dosage, Patient p){
		//TODO: Add provider parameter when adding treatments to a patient.
		DrugTreatment treatment= new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDosage(dosage);
		treatment.setDrug(drug);
		treatment.setPatient(p);
		this.addTreatment(treatment);
		return p.getId();
	}
	
	public long addRadiology(String diagnosis, List<Date> dates, Patient p){
		Radiology treatment= new Radiology();
		treatment.setDiagnosis(diagnosis);
		treatment.setDate(dates);
		treatment.setPatient(p);
		this.addTreatment(treatment);
		return p.getId();
	}
	
	public long addSurgery(String diagnosis, Date date, Patient p){
		Surgery treatment= new Surgery();
		treatment.setDiagnosis(diagnosis);
		treatment.setsurgerydate(date);
		treatment.setPatient(p);
		this.addTreatment(treatment);
		return p.getId();
	}
	
	public List<Long> getTreatmentIds(){
		List<Long> tids = new ArrayList<Long>();
		for(Treatment t: this.getTreatments()){
			tids.add(t.getId());
		}
		return tids;
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
		if (t.getProvider() != this) {
			throw new TreatmentExn("Inappropriate treatment access: provider = " + npi + ", treatment = " + tid);
		}
		t.export(visitor);
	}
	
		
	public void deleteTreatment(long tid) throws TreatmentExn{
		Treatment t = treatmentDAO.getTreatmentByDbId(tid);
		if(t.getProvider()== this){
			throw new TreatmentExn("Inappropriate treatment access: provider = " + id + ", treatment = " + tid);
		}
		treatmentDAO.deleteTreatment(t);
	}
	
	
	public Provider() {
		super();
		treatments = new ArrayList<Treatment>();
	}
}
