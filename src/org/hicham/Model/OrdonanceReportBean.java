package org.hicham.Model;

import java.util.List;

public class OrdonanceReportBean {
	private String nomDentiste;
	private String nomArab;
	private String route;
	private String city;
	private String wilaya;
	private String telephone;	
	private String date;
	private String nomPatient;
	private String prenomPatient;
	private String agePatient;
	private int id;
	private List<String>meds;
	
	

	public OrdonanceReportBean(int id,String date, String nomPatient, String prenomPatient
			, String agePatient,List<String>meds,String nomDentiste
			,String nomArab,String route,String city,String wilaya, String telephone) {
		super();
		this.id= id;
		this.date = date;
		this.nomPatient = nomPatient;
		this.prenomPatient = prenomPatient;
		this.agePatient = agePatient;
		this.meds= meds;
		this.nomDentiste= nomDentiste;
		this.nomArab= nomArab;
		this.route= route;
		this.city= city;
		this.wilaya=wilaya;
		this.telephone= telephone;
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


	public String getNomDentiste() {
		return nomDentiste;
	}


	public void setNomDentiste(String nomDentiste) {
		this.nomDentiste = nomDentiste;
	}


	public String getNomArab() {
		return nomArab;
	}


	public void setNomArab(String nomArab) {
		this.nomArab = nomArab;
	}


	public String getRoute() {
		return route;
	}


	public void setRoute(String route) {
		this.route = route;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getWilaya() {
		return wilaya;
	}


	public void setWilaya(String wilaya) {
		this.wilaya = wilaya;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
    

	

}
