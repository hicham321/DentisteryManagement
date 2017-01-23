package org.hicham.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Medicament")
public class Medicament { 
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="Id")
	int id;
	@Column(name="nomMed")
	String nomMed;
	public Medicament(String nomMed ){
		this.nomMed= nomMed;
	}
	public Medicament( ){
	}
	
	public int getId() {
		return id;
	}
	public String getNomMed() {
		return nomMed;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNomMed(String nomMed) {
		this.nomMed = nomMed;
	}
	
	
	
	

}
