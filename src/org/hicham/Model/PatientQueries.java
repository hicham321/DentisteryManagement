package org.hicham.Model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class PatientQueries {
	Configuration configuration = new Configuration().configure("/resources/hibernate.cfg.xml").addAnnotatedClass(Patient.class);
	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	SessionFactory factory = configuration.buildSessionFactory(builder.build());
	
	//queries to use factory field to instantiate sessions
	
	public void addPatient(String nom,String prenom,int age,String address,int tel,String teinte,String sex,String anticident,String fonction){
		Session session = factory.openSession();
		try {

			//save patient object
			session.beginTransaction();
			session.save( new Patient( nom, prenom, age, address, tel, teinte, sex, anticident, fonction) );
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

}
