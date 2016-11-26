package org.hicham.Model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

public class ActQueries extends UsefulMethods{
	
	public void addAct(String act,double Payement,Date date){
		Session session = SessionsDB.getFactory().openSession();
		try {

			//save patient object
			session.beginTransaction();
			session.save( new Act(act,Payement,date) );
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	
	public List<Act> findAllActs(){
		Session session = SessionsDB.getFactory().openSession();
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
