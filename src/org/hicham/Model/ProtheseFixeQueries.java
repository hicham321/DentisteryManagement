package org.hicham.Model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
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
	
	public void deleteProtheseImages(String lien ){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		ImageProtheseFixe imageProtheseFix= new ImageProtheseFixe();
		try {
			List<ImageProtheseFixe> listImagesProtheseFixe= new ArrayList<>();
			Hibernate.initialize(listImagesProtheseFixe);
		    listImagesProtheseFixe= session.createQuery("from ImageProtheseFixe").list();
            for (int i = 0; i < listImagesProtheseFixe.size(); i++) {
				if (lien.equals(listImagesProtheseFixe.get(i).getLien())) {
					imageProtheseFix= listImagesProtheseFixe.get(i);
				}
			}

			session.beginTransaction();
			session.delete(imageProtheseFix);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	
	public void addNewImages(List<String>newList,List<String>oldList,ProtheseFixe currentProtheseFixe){
		//compare and insert the new image in prothese
		boolean found= false;
		for (int i = 0; i < newList.size(); i++) {
			for (int j = 0; j < oldList.size(); j++) {
				if (newList.get(i)==oldList.get(j)) {
					//do nothing 
					found=true;
				}
			
			}
			if (!found) {
				//add the new lien to the database
			    ImageProtheseFixe imageProtheseFixe= new ImageProtheseFixe(newList.get(i));
			    imageProtheseFixe.setProtheseFixe(currentProtheseFixe);
			    addProtheseFixeImage(imageProtheseFixe);
			    found=false;
			}
			
		}
	}
	
}
