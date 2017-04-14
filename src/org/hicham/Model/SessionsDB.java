package org.hicham.Model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

//this class should be used as a singleton class, as Factory objects are only to be created 
//once according to Hibernate guidelines.
public class SessionsDB {
	
	 static SessionFactory factory = null;

	
	
	static int singletonCounter=0;
	
    public SessionsDB(){
    	
    	if (singletonCounter==0) {
			//create singleton factory object
    		 Configuration configuration = new Configuration()
    				.configure("/resources/hibernate.cfg.xml")
    				.addAnnotatedClass(Patient.class)
    				.addAnnotatedClass(Produit.class)
    				.addAnnotatedClass(Medicament.class)
    				.addAnnotatedClass(Act.class)
    				.addAnnotatedClass(Register.class)
    				.addAnnotatedClass(Odf.class)
    				.addAnnotatedClass(ProtheseFixe.class)
    				.addAnnotatedClass(ImageProtheseFixe.class)
    				.addAnnotatedClass(ProthesePartielle.class)
    				.addAnnotatedClass(ImageProthesePartielle.class)
    				.addAnnotatedClass(ProtheseTotale.class)
    				.addAnnotatedClass(ImageProtheseTotale.class)
    				.addAnnotatedClass(ImageOdf.class)
    				.addAnnotatedClass(ImageAct.class)
    				.addAnnotatedClass(Dentiste.class);
    		 StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
    		 factory = configuration.buildSessionFactory(builder.build());
    		singletonCounter++;
    		
		}
    }

	public  static SessionFactory getFactory() {
		return factory;
	}
    
    
}
