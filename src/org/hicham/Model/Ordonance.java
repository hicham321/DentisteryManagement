package org.hicham.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Ordonance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	int id;
	@Column(name="nomMed")
	String nomMed;
	@Column(name="situation")
	String situation;
	public Ordonance(String nomMed,String situation){
		 this.nomMed=nomMed;
		 this.situation= situation;
	}
	public Ordonance(){
		
	}
	public int getId() {
		return id;
	}
	public String getNomMed() {
		return nomMed;
	}
	public String getSituation() {
		return situation;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNomMed(String nomMed) {
		this.nomMed = nomMed;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	
}
