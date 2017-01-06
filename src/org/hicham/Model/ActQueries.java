package org.hicham.Model;

import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.hibernate.Session;
import org.hicham.Model.PatientQueries.MyClass;

public class ActQueries extends UsefulMethods{
	
	public void addAct(Act act){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		//Session session = SessionsDB.getFactory().openSession();
		try {

			//save act object
			session.beginTransaction();
			session.save(act);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	
	public List<Act> findAllActs(){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		//Session session = SessionsDB.getFactory().openSession();
		try {

			List<Act> listAct= session.createQuery("from Act").list();

			return listAct;
		} finally {
			session.close();
		}	
	}
	
	public Act getAct(int id){
		List<Act> listOfallActs= findAllActs(); 
		return listOfallActs.get(id);
	}

}
