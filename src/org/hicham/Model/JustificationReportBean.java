package org.hicham.Model;

public class JustificationReportBean {
    String date;
    String temp;
    String gender;
    String sujet;
    String nom;
    public JustificationReportBean(String date, String temp,String gender,String sujet
    		,String nom){
    	super();
    	this.date=date;
    	this. temp=temp;
    	this. gender=gender;
    	this. sujet=sujet;
    	this. nom=nom;

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
    


    
}
