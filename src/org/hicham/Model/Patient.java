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
import javax.persistence.OneToMany;
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
	
	@OneToMany(targetEntity=Act.class, mappedBy="patient",cascade=CascadeType.ALL,fetch= FetchType.EAGER)
	private List<Act> actList;

	
	
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
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public void setTeinte(String teinte) {
		this.teinte = teinte;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setAnticident(String anticident) {
		this.anticident = anticident;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public List<Act> getActList() {
		return actList;
	}
	public void setActList(List<Act> actList) {
		this.actList = actList;
	}
	
    
	
}
