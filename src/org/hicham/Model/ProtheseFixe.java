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
@Entity
@Transactional
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
	@Column (name="entante" ,nullable=false)
	String entante;
	@Column (name="temp" ,nullable=false)
	String temp ;
	@Column (name="date" ,nullable=false)
	@Temporal(value=TemporalType.DATE)
	Date date ;
	@ManyToOne
    @JoinColumn(name="id_Patient")
    private Patient patient;
	@OneToMany(targetEntity=ImageProtheseFixe.class,orphanRemoval = true, mappedBy="protheseFixe"
			,cascade=CascadeType.DETACH,fetch= FetchType.EAGER)
	private List<ImageProtheseFixe> imageProtheseFixe;
	
    public ProtheseFixe(String numero,String entante, String typeProthese
    		,String temp, Date date) {
		this.numero = numero;
		this.typeProthese = typeProthese;
		this.temp = temp;
		this.date = date;
		this.entante= entante;
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

	public List<ImageProtheseFixe> getImageProtheseFixe() {
		return imageProtheseFixe;
	}

	public void setImageProtheseFixe(List<ImageProtheseFixe> imageProtheseFixe) {
		this.imageProtheseFixe = imageProtheseFixe;
	}

	public String getEntante() {
		return entante;
	}

	public void setEntante(String entante) {
		this.entante = entante;
	}
	


}
