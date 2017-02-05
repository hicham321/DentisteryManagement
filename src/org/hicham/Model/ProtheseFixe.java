package org.hicham.Model;

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
@Entity
@Table(name="ProtheseFixe")
public class ProtheseFixe {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name="id" ,nullable=false)
	int id;
	@Column (name="numero" ,nullable=false)
	String numero ;
	@Column (name="typeProthese" ,nullable=false)
	String typeProthese ;
	@Column (name="temp" ,nullable=false)
	String temp ;
	@Column (name="date" ,nullable=false)
	String date ;
	@ManyToOne
    @JoinColumn(name="id_Patient")
    private Patient patient;
	@OneToMany(targetEntity=ImageProtheseFixe.class, mappedBy="protheseFixe"
			,cascade=CascadeType.ALL,fetch= FetchType.EAGER)
	private List<ImageProtheseFixe> imageProtheseFixe;
	
    public ProtheseFixe(String numero, String typeProthese, String temp, String date) {
		this.numero = numero;
		this.typeProthese = typeProthese;
		this.temp = temp;
		this.date = date;
	}

	public ProtheseFixe(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTypeProthese() {
		return typeProthese;
	}

	public void setTypeProthese(String typeProthese) {
		this.typeProthese = typeProthese;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<ImageProtheseFixe> getImageProtheseFixe() {
		return imageProtheseFixe;
	}

	public void setImageProtheseFixe(List<ImageProtheseFixe> imageProtheseFixe) {
		this.imageProtheseFixe = imageProtheseFixe;
	}
	


}
