package org.hicham.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ImageProtheseTotale")
public class ImageProtheseTotale {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name="id" ,nullable=false)
	int id;
	@Column(name="lien",nullable= false)
	String lien;
	@ManyToOne
    @JoinColumn(name="id_partielle")
    private ProtheseTotale protheseTotale;

	public ImageProtheseTotale(String lien) {
		this.lien = lien;
	}

	public ImageProtheseTotale() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public ProtheseTotale getProtheseFixe() {
		return protheseTotale;
	}

	public void setProtheseTotale(ProtheseTotale protheseTotale) {
		this.protheseTotale = protheseTotale;
	}
	
	
}


