package org.hicham.Model;

import java.util.List;

import org.hibernate.Session;

public class RegisterQueries {
	
	public boolean checkPassIncorrect(String enteredPass, String selectedUser){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		//Session session = SessionsDB.getFactory().openSession();
		try {
			boolean passIsCorrect= false;
			List<Register> listUsers= session.createQuery("from Register").list();
			for (int i = 0; i < listUsers.size(); i++) {
				if(selectedUser.equals(listUsers.get(i).getTypeUtil())){
					if(enteredPass.equals(listUsers.get(i).getPassword())){
						passIsCorrect=true;
					}
				}
			}       
			return passIsCorrect;
		} finally {
			session.close();
		}	
	}
	//this method should be executed before the program begins
	public boolean CheckRegisterEmpty(){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		boolean isEmpty= false;
		try {
			List<Register> listUsers= session.createQuery("from Register").list();
			if (listUsers.isEmpty()) {
				isEmpty= true;
			}
            
		} finally {
			session.close();

		}
		return isEmpty;
	}
	
	//this method should only be executed when register table in database is empty
	public void putInitialPasswords(){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		Register registerDentisteObject= new Register();
		registerDentisteObject.setPassword("admin");
		registerDentisteObject.setTypeUtil("Dentiste");
		
		Register registerAssitantObject= new Register();
		registerAssitantObject.setPassword("admin");
		registerAssitantObject.setTypeUtil("Assistant");

		try {
			 
			session.beginTransaction();
			session.save(registerDentisteObject);
			session.save(registerAssitantObject);
			session.getTransaction().commit();
				
			
            
		} finally {
			session.close();
		}
	}
}
