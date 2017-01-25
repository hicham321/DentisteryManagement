package org.hicham.Model;

import java.util.List;

public class OrdonanceReportBean {
	
	private String date;
	private String nomPatient;
	private String prenomPatient;
	private String agePatient;
	private int id;
	private List<String>meds;
	
	

	public OrdonanceReportBean(int id,String date, String nomPatient, String prenomPatient
			, String agePatient,List<String>meds) {
		super();
		this.id= id;
		this.date = date;
		this.nomPatient = nomPatient;
		this.prenomPatient = prenomPatient;
		this.agePatient = agePatient;
		this.meds= meds;
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<String> getMeds() {
		return meds;
	}


	public void setMeds(List<String> meds) {
		this.meds = meds;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNomPatient() {
		return nomPatient;
	}

	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}

	public String getPrenomPatient() {
		return prenomPatient;
	}

	public void setPrenomPatient(String prenomPatient) {
		this.prenomPatient = prenomPatient;
	}

	public String getAgePatient() {
		return agePatient;
	}

	public void setAgePatient(String agePatient) {
		this.agePatient = agePatient;
	}

	public List<String> getMed() {
		return meds;
	}

	public void setMed(List<String> med) {
		this.meds = med;
	}
	

}
