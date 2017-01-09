package org.hicham.Model;

import java.util.List;

import org.hibernate.Session;

public class OdfQueries {
	public void addOdf(Odf odf) {
		SessionsDB FactoryObject = new SessionsDB();
		Session session = FactoryObject.getFactory().openSession();
		// Session session = SessionsDB.getFactory().openSession();
		try {

			// save Odf object
			session.beginTransaction();
			session.saveOrUpdate(odf);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

	public void deleteOdf(Odf odf) {
		SessionsDB FactoryObject = new SessionsDB();
		Session session = FactoryObject.getFactory().openSession();
		try {

			// delete odf object
			session.beginTransaction();
			session.delete(odf);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

	public List<Act> findAllOdf() {
		SessionsDB FactoryObject = new SessionsDB();
		Session session = FactoryObject.getFactory().openSession();
		// Session session = SessionsDB.getFactory().openSession();
		try {

			List<Act> listOdfs = session.createQuery("from Odf").list();

			return listOdfs;
		} finally {
			session.close();
		}
	}

	public Act getOdf(int id) {
		List<Act> listOfallOdfs = findAllOdf();
		return listOfallOdfs.get(id);
	}

}
