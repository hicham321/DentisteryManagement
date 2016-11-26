package org.hicham.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	public Act(String act, double payement){
		this.payement=payement;
		this.act= act;
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
	

	
	
}
