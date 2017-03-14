package org.hicham.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

public class ProthesePartielleQueries {
	       //insert update and delete queries
		public void addProthesePartielle(ProthesePartielle prothesePartielle){
			SessionsDB FactoryObject= new SessionsDB();
			Session session= FactoryObject.getFactory().openSession();
			try {

				session.beginTransaction();
				session.saveOrUpdate(prothesePartielle);
				session.getTransaction().commit();

			} finally {
				session.close();
			}
		}
		public void addProthesePartielleImage(ImageProthesePartielle imageProthesePartielle){
			SessionsDB FactoryObject= new SessionsDB();
			Session session= FactoryObject.getFactory().openSession();
			try {

				session.beginTransaction();
				session.saveOrUpdate(imageProthesePartielle);
				session.getTransaction().commit();

			} finally {
				session.close();
			}
		}
		public void deleteProthesePartielle(ProthesePartielle prothesePartielle){
			SessionsDB FactoryObject= new SessionsDB();
			Session session= FactoryObject.getFactory().openSession();
			try {

				session.beginTransaction();
				session.delete(prothesePartielle);
				session.getTransaction().commit();

			} finally {
				session.close();
			}
		}
		
		public void deleteProtheseImages(String lien ){
			SessionsDB FactoryObject= new SessionsDB();
			Session session= FactoryObject.getFactory().openSession();
			ImageProthesePartielle imageProthesePartielle= new ImageProthesePartielle();
			try {
				List<ImageProthesePartielle> listImagesProthesePartielle= new ArrayList<>();
				Hibernate.initialize(listImagesProthesePartielle);
				listImagesProthesePartielle= session.createQuery("from ImageProthesePartielle").list();
	            for (int i = 0; i < listImagesProthesePartielle.size(); i++) {
					if (lien.equals(listImagesProthesePartielle.get(i).getLien())) {
						imageProthesePartielle= listImagesProthesePartielle.get(i);
					}
				}

				session.beginTransaction();
				session.delete(imageProthesePartielle);
				session.getTransaction().commit();

			} finally {
				session.close();
			}
		}
		
		public void addNewImages(List<String>newList,List<String>oldList,ProthesePartielle currentProthesePartielle){
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
				    ImageProthesePartielle imageProthesePartielle= new ImageProthesePartielle(newList.get(i));
				    imageProthesePartielle.setProthesePartielle(currentProthesePartielle);
				    addProthesePartielleImage(imageProthesePartielle);
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
		public double updatePayement(ProthesePartielle prothesePartielle, double payementAjouté, double payementTotal){
			if (new Double(prothesePartielle.getPayementActuel())==null ) {
				prothesePartielle.setPayementActuel(0);
			}
			double newPayement= prothesePartielle.getPayementActuel()+payementAjouté;
			prothesePartielle.setPayementActuel(newPayement);
			prothesePartielle.setPayementTotal(payementTotal);
			return payementTotal-newPayement;
		}
		
	}


