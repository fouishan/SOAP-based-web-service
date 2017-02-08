package edu.stevens.cs548.clinic.domain;

import edu.stevens.cs548.clinic.domain.Treatment;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Surgery
 *
 */
@Entity
@DiscriminatorValue("S")
public class Surgery extends Treatment implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date surgerydate;
	public Date getsurgerydate() {
		return this.surgerydate;
	}

	public void setsurgerydate(Date surgerydate) {
		this.surgerydate = surgerydate;
	} 
	
	public void export(ITreatmentExporter visitor) {
		visitor.exportSurgery(this.getId(), 
								   		   this.getDiagnosis(),
								   		   this.getsurgerydate());
	}

	public Surgery() {
		super();
		this.setTreatmentType("S");
	}
   
}
