package org.hicham.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Transactional
@Table(name="Dentiste")
public class Dentiste {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name="id" ,nullable=false)
	int id;
	@Column (name="nom" ,nullable=false)
	String nom ;
	@Column (name="prenom" ,nullable=false)
	String prenom ;
	@Column (name="nomPrenomArab" ,nullable=false)
	String nomPrenomArab;
	@Column (name="route" ,nullable=false)
	String route ;
	@Column (name="city" ,nullable=false)
	String city;
	@Column (name="wilaya" ,nullable=false)
	String wilaya  ;
	@Column (name="tel" ,nullable=false)
	String tel;
	
    
	public Dentiste( String nom, String prenom, String nomPrenomArab, String route, String city, String wilaya,
			String tel) {
		this.nom = nom;
		this.prenom = prenom;
		this.nomPrenomArab = nomPrenomArab;
		this.route = route;
		this.city = city;
		this.wilaya = wilaya;
		this.tel = tel;
	}

	public Dentiste() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNomPrenomArab() {
		return nomPrenomArab;
	}

	public void setNomPrenomArab(String nomPrenomArab) {
		this.nomPrenomArab = nomPrenomArab;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWilaya() {
		return wilaya;
	}

	public void setWilaya(String wilaya) {
		this.wilaya = wilaya;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
