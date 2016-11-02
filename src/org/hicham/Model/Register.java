package org.hicham.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Register")
public class Register {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	int id;
	@Column(name="Type")
	String typeUtil;
	@Column(name="Password")
	String password;
	
	public Register(){
		
	}

	public int getId() {
		return id;
	}

	public String getTypeUtil() {
		return typeUtil;
	}

	public String getPassword() {
		return password;
	}
	

}
