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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	int id;
	@Column(name="Id")
	String nomMed;
	@Column(name="Id")
	String situation;
	public Medicament(){
		
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
	
	

}
