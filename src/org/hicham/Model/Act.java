package org.hicham.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="Act")
public class Act {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name="id" ,nullable=false)
	int id;
	@Column (name="act" ,nullable=false)
	String act;
	@Column (name="payement" ,nullable=false,length=1000)
    double payement;
	@Column (name="date")
	@Temporal(value=TemporalType.DATE)
    Date dateRendezVous;
	@Column (name="temp")
    String tempRendezVous;
	@Column (name="lienImageRadio",nullable= true)
	String lienImageRadio;
	@ManyToOne
    @JoinColumn(name="id_Patient")
    private Patient patient;
	
	public Act(String act, double payement, Date dateRendezVous
			,String tempRendezVous,String lienImageRadio){
		this.payement=payement;
		this.act= act;
		this.dateRendezVous= dateRendezVous;
		this.tempRendezVous=tempRendezVous;
		this.lienImageRadio=lienImageRadio;
	}
	
	public Act(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public double getPayement() {
		return payement;
	}

	public void setPayement(double payement) {
		this.payement = payement;
	}

	public Date getDateRendezVous() {
		return dateRendezVous;
	}

	public void setDateRendezVous(Date dateRendezVous) {
		this.dateRendezVous = dateRendezVous;
	}

	public String getTempRendezVous() {
		return tempRendezVous;
	}

	public void setTempRendezVous(String tempRendezVous) {
		this.tempRendezVous = tempRendezVous;
	}
	
	public String getLienImageRadio() {
		return lienImageRadio;
	}

	public void setLienImageRadio(String lienImageRadio) {
		this.lienImageRadio = lienImageRadio;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
}
