package edu.stevens.cs548.clinic.domain;

import edu.stevens.cs548.clinic.domain.Treatment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Radiology1
 *
 */
@Entity
@DiscriminatorValue("R")
public class Radiology extends Treatment implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@ElementCollection
	@Temporal(TemporalType.DATE)
	private List<Date> dates;
	public List<Date> getDate(){
		return dates;
	}
	public void setDate(List<Date> dates){
		this.dates = dates;
	}
	public Radiology() {
		super();
		dates = new ArrayList<Date>();
		this.setTreatmentType("R");
	}
  
	public void export(ITreatmentExporter visitor) {
		visitor.exportRadiology(this.getId(), 
								   		   this.getDiagnosis(),
								   		   this.getDate());
	}

}
