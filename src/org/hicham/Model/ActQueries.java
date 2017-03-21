package org.hicham.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ActQueries {
       //insert update and delete queries
	public void addAct(Act act){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {

			session.beginTransaction();
			session.saveOrUpdate(act);
			session.getTransaction().commit();
		} finally {
			session.close();

		}
	}
	public void addActImage(ImageAct imageAct){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {

			session.beginTransaction();
			session.saveOrUpdate(imageAct);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	public void deleteAct(Act act){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {

			session.beginTransaction();
			session.delete(act);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	
	public void deleteActImages(String lien ){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		ImageAct imageAct= new ImageAct();
		try {
			List<ImageAct> listImagesAct= new ArrayList<>();
			Hibernate.initialize(listImagesAct);
			listImagesAct= session.createQuery("from ImageAct").list();
            for (int i = 0; i < listImagesAct.size(); i++) {
				if (lien.equals(listImagesAct.get(i).getLien())) {
					imageAct= listImagesAct.get(i);
				}
			}

			session.beginTransaction();
			session.delete(imageAct);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	
	public void addNewImages(List<String>newList,List<String>oldList,Act currentAct){
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
			    ImageAct imageAct= new ImageAct(newList.get(i));
			    imageAct.setAct(currentAct);
			    addActImage(imageAct);
			    found=false;
			}
			
		}
	}
	public String CopyFileImage(String pathTodestinationImageDir, String sourceFileImagePath){
		String newImagePathFile="";
		try{
			File sourceFileImage = new File(sourceFileImagePath);
			//the path to the destination file should be changed when packaging the Jar file
			
			File destinationFile = new File(pathTodestinationImageDir);
			FileInputStream fileInputStream = new FileInputStream(sourceFileImage);
			newImagePathFile=destinationFile+"/"+sourceFileImage.getName();
			FileOutputStream fileOutputStream = new FileOutputStream( newImagePathFile);
			int bufferSize;
			byte[] bufffer = new byte[512];
			while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
				fileOutputStream.write(bufffer, 0, bufferSize);
			}
			fileInputStream.close();
			fileOutputStream.close();

		}catch(Exception ex){
			ex.printStackTrace();

		}
		return newImagePathFile;
	}
	//payement update
	public double updatePayement(Act act, double payementAjouté, double payementTotal){
		if (new Double(act.getPayementActuel())==null ) {
			act.setPayementActuel(0);
		}
		double newPayement= act.getPayementActuel()+payementAjouté;
		act.setPayementActuel(newPayement);
		act.setPayementTotal(payementTotal);
		return payementTotal-newPayement;
	}
	
}
