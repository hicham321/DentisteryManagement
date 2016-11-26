package org.hicham.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Act")
public class Act {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name="id" ,nullable=false)
	int id;
	
}
