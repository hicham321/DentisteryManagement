package org.hicham.Model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

public class DentisteQueries {
	
	public void addorUpdateDentiste(Dentiste dentiste){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(dentiste);
			session.getTransaction().commit();
		} finally {
			session.close();

		}
	}
	//verify if dentiste table is not empty
	public boolean  verifyEmpty(){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		boolean verify= false;
		
		try {
			List<ImageAct> listDentiste= new ArrayList<>();
			listDentiste= session.createQuery("from Dentiste").list();
			if (listDentiste.isEmpty()) {
				verify= true;
			}
		} finally {
			session.close();
		}
		return verify;
	}

}
