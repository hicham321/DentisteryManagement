package org.hicham.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ODF")
public class Odf {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name="id" ,nullable=false)
	int id;
	@Column (name="odf" ,nullable=false)
	String odf;
	@Column (name="payement" ,nullable=false,length=1000)
    int payement;
	@Column (name="payementTotal" ,nullable=false,length=1000)
    int payementTotal;
	@Column (name="payementRest" ,nullable=false,length=1000)
    int payementRest;
	@Column (name="date")
	@Temporal(value=TemporalType.DATE)
    Date dateRendezVous;
	@Column (name="temp")
    String tempRendezVous;
	@ManyToOne
    @JoinColumn(name="id_Patient")
    private Patient patient;
	
	public Odf(String odf, int payement, int payementTotal, int payementRest
			, Date dateRendezVous,String tempRendezVous){
		this.payement=payement;
		this.payementTotal= payementTotal;
		this.payementRest= payementRest;
		this.odf= odf;
		this.dateRendezVous= dateRendezVous;
		this.tempRendezVous=tempRendezVous;
	}
	
	public Odf(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOdf() {
		return odf;
	}

	public void setOdf(String odf) {
		this.odf = odf;
	}

	public int getPayementTotal() {
		return payementTotal;
	}

	public void setPayementTotal(int payementTotal) {
		this.payementTotal = payementTotal;
	}

	public int getPayementRest() {
		return payementRest;
	}

	public void setPayementRest(int payementRest) {
		this.payementRest = payementRest;
	}


	
	public int getPayement() {
		return payement;
	}

	public void setPayement(int payement) {
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
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
}



