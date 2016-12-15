package org.hicham.Model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

//this class should be used as a singleton class, as Factory objects are only to be created 
//once according to Hibernate guidelines.
public class SessionsDB {
	
	/*static Configuration configuration = new Configuration().configure("/resources/hibernate.cfg.xml")
			.addAnnotatedClass(Patient.class)
			.addAnnotatedClass(Produit.class)
			.addAnnotatedClass(Medicament.class)
			.addAnnotatedClass(Act.class);
	static StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	static SessionFactory factory = configuration.buildSessionFactory(builder.build());*/
	 static SessionFactory factory = null;

	
	
	static int singletonCounter=0;
	
    public SessionsDB(){
    	
    	if (singletonCounter==0) {
			//create singleton factory object
    		 Configuration configuration = new Configuration().configure("/resources/hibernate.cfg.xml")
    				.addAnnotatedClass(Patient.class)
    				.addAnnotatedClass(Produit.class)
    				.addAnnotatedClass(Medicament.class)
    				.addAnnotatedClass(Act.class);
    		 StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
    		 factory = configuration.buildSessionFactory(builder.build());
     		 System.out.println("Creating a new Factory");
    		singletonCounter++;
		}
    }

	public  SessionFactory getFactory() {
		return factory;
	}
    
    
}
