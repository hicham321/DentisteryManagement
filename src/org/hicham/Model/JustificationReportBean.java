package org.hicham.Model;

public class JustificationReportBean {
    String nomDentiste;
	String nomArab;
	String route;
	String city;
	String wilaya;
	String telephone;	
    String date;
    String temp;
    String gender;
    String sujet;
    String nom;
    public JustificationReportBean(String date, String temp,String gender,String sujet
    		,String nom,String nomDentiste
			,String nomArab,String route,String city,String wilaya, String telephone){
    	super();
    	this.date=date;
    	this. temp=temp;
    	this. gender=gender;
    	this. sujet=sujet;
    	this. nom=nom;
    	this.nomDentiste= nomDentiste;
		this.nomArab= nomArab;
		this.route= route;
		this.city= city;
		this.wilaya=wilaya;
		this.telephone= telephone;

    }
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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
