package org.hicham.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.hibernate.annotations.Type;
@Entity
@Transactional
@Table(name="Odf")
public class Odf {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name="id" ,nullable=false)
	int id;
	@Column (name="entante" ,nullable=false)
	@Type(type="text")
	String entante;
	@Column (name="temp" ,nullable=false)
	String temp ;
	@Column (name="date" ,nullable=false)
	@Temporal(value=TemporalType.DATE)
	Date date ;
	@Column(name="payementActuel",nullable=false)
	double payementActuel;
	@Column(name="payementTotal",nullable=false)
	double payementTotal;
	@ManyToOne
    @JoinColumn(name="id_Patient")
    private Patient patient;
	@OneToMany(targetEntity=ImageOdf.class,orphanRemoval = true, mappedBy="odf"
			,cascade=CascadeType.DETACH,fetch= FetchType.EAGER)
	private List<ImageOdf> imageOdf;
	
    public Odf(String entante,String temp, Date date, double payTotal,double payActuel) {
		this.temp = temp;
		this.date = date;
		this.entante= entante;
		this.payementActuel=payActuel;
		this.payementTotal= payTotal;
	}

	public Odf(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	

	public List<ImageOdf> getImageOdf() {
		return imageOdf;
	}

	public void setImageOdf(List<ImageOdf> imageOdf) {
		this.imageOdf = imageOdf;
	}

	public String getEntante() {
		return entante;
	}

	public void setEntante(String entante) {
		this.entante = entante;
	}

	public double getPayementActuel() {
		return payementActuel;
	}

	public void setPayementActuel(double payementActuel) {
		this.payementActuel = payementActuel;
	}

	public double getPayementTotal() {
		return payementTotal;
	}

	public void setPayementTotal(double payementTotal) {
		this.payementTotal = payementTotal;
	}
	


}
