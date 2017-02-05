package org.hicham.Model;

import org.hibernate.Session;

public class ProtheseFixeQueries {
       //insert update and delete queries
	public void addProtheseFixe(ProtheseFixe protheseFixe){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {

			session.beginTransaction();
			session.saveOrUpdate(protheseFixe);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	public void addProtheseFixeImage(ImageProtheseFixe imageProtheseFixe){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {

			session.beginTransaction();
			session.saveOrUpdate(imageProtheseFixe);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	public void deleteProtheseFixe(ProtheseFixe protheseFixe){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {

			session.beginTransaction();
			session.delete(protheseFixe);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	
}
