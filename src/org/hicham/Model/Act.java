package org.hicham.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Act")
public class Act {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name="id" ,nullable=false)
	int id;
	@Column (name="act" ,nullable=false)
	String act;
	@Column (name="payement" ,nullable=false)
    double payement;
	@Column (name="date")
    Date dateRendezVous;
	@OneToMany
	@JoinColumn(name="CUST_ID") 
	@Column (name="date")
    int gh;

	
	public Act(String act, double payement, Date dateRendezVous){
		this.payement=payement;
		this.act= act;
		this.dateRendezVous= dateRendezVous;
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
	

	
	
}
