package org.hicham.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
@Entity
@Table(name="RendezVous")
public class RendezVous {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RendezVous")
	int id;
	@Column(name="Date")
	Date date;
	@Column(name="RendezVous")
    String typeRendezVous;
	@ForeignKey(name="patientid")
    @Column(name="IdPatient")
    int idPatient;
    
	public RendezVous(){
		
	}
	public int getId() {
		return id;
	}
	public Date getDate() {
		return date;
	}
	public String getTypeRendezVous() {
		return typeRendezVous;
	}
	public int getIdPatient() {
		return idPatient;
	}
	

}
