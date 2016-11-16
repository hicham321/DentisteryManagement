package org.hicham.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
@Entity
@Table(name="Patient")
public class Patient {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name="Id",nullable = false)
	int id;
	@Column (name="Nom",nullable = false)
	String nom;
	@Column (name="Prenom",nullable = false)
	String prenom;
	@Column (name="Age",nullable = false)
	int age; 
	@Column (name="Address")
	String address; 
	@Column (name="Tel")
	int tel;
	@Column (name="Teinte")
	String teinte; 
	@Column (name="Sex",nullable = false)
	String sex; 
	@Column (name="Anticident")
	String anticident;
	@Column (name="Fonction")
	String fonction; 
	public Patient(String nom, String prenom, int age, String address, int tel, String teinte, String sex, String anticident,String fonction){
		this.nom= nom;
		this.prenom= prenom;
		this.age= age;
		this.address= address;
		this.tel= tel;
		this.teinte= teinte;
		this.sex= sex;
		this.anticident= anticident;
		this.fonction= fonction;
	}
	public Patient(){

	}
	public int getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public int getAge() {
		return age;
	}
	public String getAddress() {
		return address;
	}
	public int getTel() {
		return tel;
	}
	public String getTeinte() {
		return teinte;
	}
	public String getSex() {
		return sex;
	}
	public String getAnticident() {
		return anticident;
	}
	public String getFonction() {
		return fonction;
	}

}
