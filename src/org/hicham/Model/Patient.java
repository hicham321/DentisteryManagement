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
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column (name="Id")
	int id;
	@Column (name="Nom")
	String nom;
	@Column (name="Prenom")
	String prenom;
	@Column (name="Age")
	int age; 
	@Column (name="Address")
	String address; 
	@Column (name="Tel")
	int tel;
	@Column (name="Teinte")
	String teinte; 
	@Column (name="Sex")
	String sex; 
	@Column (name="Anticident")
	String anticident;
	@Column (name="Fonction")
	String fonction; 
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