package org.hicham.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Register")
public class Register{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	int id;
	@Column(name="Type")
	String typeUtil;
	@Column(name="Password")
	String password;
	
	public Register(String typeUtil,String password){
		this.password= password;
		this.typeUtil= typeUtil;
	}
	
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

	public void setTypeUtil(String typeUtil) {
		this.typeUtil = typeUtil;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
