package org.hicham.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="produit")
public class Produit {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name="Id",nullable = false)
	int id;
	@Column(name="produitNom", nullable=false)
	String produitNom;
	@Column(name="prix", nullable=false)
	double prix;
	@Column(name="qte", nullable=false)
	int qte;
	
	public Produit(String produitNom, double prix, int qte){
		this.produitNom=produitNom;
		this.prix=prix;
		this.qte= qte;
	}
	public Produit(){
	}
	public int getId() {
		return id;
	}
	public String getProduitNom() {
		return produitNom;
	}
	public double getPrix() {
		return prix;
	}
	public int getQte() {
		return qte;
	}
	
}
