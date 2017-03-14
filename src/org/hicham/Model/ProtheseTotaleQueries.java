package org.hicham.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

public class ProtheseTotaleQueries {
	       //insert update and delete queries
		public void addProtheseTotale(ProtheseTotale protheseTotale){
			SessionsDB FactoryObject= new SessionsDB();
			Session session= FactoryObject.getFactory().openSession();
			try {

				session.beginTransaction();
				session.saveOrUpdate(protheseTotale);
				session.getTransaction().commit();

			} finally {
				session.close();
			}
		}
		public void addProtheseTotaleImage(ImageProtheseTotale imageProtheseTotale){
			SessionsDB FactoryObject= new SessionsDB();
			Session session= FactoryObject.getFactory().openSession();
			try {

				session.beginTransaction();
				session.saveOrUpdate(imageProtheseTotale);
				session.getTransaction().commit();

			} finally {
				session.close();
			}
		}
		public void deleteProtheseTotale(ProtheseTotale protheseTotale){
			SessionsDB FactoryObject= new SessionsDB();
			Session session= FactoryObject.getFactory().openSession();
			try {

				session.beginTransaction();
				session.delete(protheseTotale);
				session.getTransaction().commit();

			} finally {
				session.close();
			}
		}
		
		public void deleteProtheseImages(String lien ){
			SessionsDB FactoryObject= new SessionsDB();
			Session session= FactoryObject.getFactory().openSession();
			ImageProtheseTotale imageProtheseTotale= new ImageProtheseTotale();
			try {
				List<ImageProtheseTotale> listImagesProtheseTotale= new ArrayList<>();
				Hibernate.initialize(listImagesProtheseTotale);
				listImagesProtheseTotale= session.createQuery("from ImageProtheseTotale").list();
	            for (int i = 0; i < listImagesProtheseTotale.size(); i++) {
					if (lien.equals(listImagesProtheseTotale.get(i).getLien())) {
						imageProtheseTotale= listImagesProtheseTotale.get(i);
					}
				}

				session.beginTransaction();
				session.delete(imageProtheseTotale);
				session.getTransaction().commit();

			} finally {
				session.close();
			}
		}
		
		public void addNewImages(List<String>newList,List<String>oldList,ProtheseTotale currentProtheseTotale){
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
				    ImageProtheseTotale imageProtheseTotale= new ImageProtheseTotale(newList.get(i));
				    imageProtheseTotale.setProtheseTotale(currentProtheseTotale);
				    addProtheseTotaleImage(imageProtheseTotale);
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
		public double updatePayement(ProtheseTotale protheseTotale, double payementAjouté, double payementTotal){
			if (new Double(protheseTotale.getPayementActuel())==null ) {
				protheseTotale.setPayementActuel(0);
			}
			double newPayement= protheseTotale.getPayementActuel()+payementAjouté;
			protheseTotale.setPayementActuel(newPayement);
			protheseTotale.setPayementTotal(payementTotal);
			return payementTotal-newPayement;
		}
		
	}


