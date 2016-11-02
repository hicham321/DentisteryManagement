package org.hicham.Model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ConnectionDB {
public void conn(){
	SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml").addAnnotatedClass(Patient.class).buildSessionFactory();

}
}
