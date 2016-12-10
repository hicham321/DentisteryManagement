package org.hicham.Model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class SessionsDB {
	
	static Configuration configuration = new Configuration().configure("/resources/hibernate.cfg.xml")
			.addAnnotatedClass(Patient.class)
			.addAnnotatedClass(Produit.class)
			.addAnnotatedClass(Medicament.class)
			.addAnnotatedClass(Act.class);
	static StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	static SessionFactory factory = configuration.buildSessionFactory(builder.build());
	
    public SessionsDB(){
    	
    }

	public static SessionFactory getFactory() {
		return factory;
	}
    
    
}
