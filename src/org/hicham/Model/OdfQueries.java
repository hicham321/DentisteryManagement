package org.hicham.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

public class OdfQueries {
    //insert update and delete queries
	public void addOdf(Odf odf){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {

			session.beginTransaction();
			session.saveOrUpdate(odf);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	public void addImageOdf(ImageOdf imageOdf){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {

			session.beginTransaction();
			session.saveOrUpdate(imageOdf);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	public void deleteOdf(Odf odf){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {

			session.beginTransaction();
			session.delete(odf);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	
	public void deleteOdfImages(String lien ){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		ImageOdf imageOdf= new ImageOdf();
		try {
			List<ImageOdf> listImagesOdf= new ArrayList<>();
			Hibernate.initialize(listImagesOdf);
			listImagesOdf= session.createQuery("from ImageOdf").list();
            for (int i = 0; i < listImagesOdf.size(); i++) {
				if (lien.equals(listImagesOdf.get(i).getLien())) {
					imageOdf= listImagesOdf.get(i);
				}
			}

			session.beginTransaction();
			session.delete(imageOdf);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	
	public void addNewImages(List<String>newList,List<String>oldList,Odf currentOdf){
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
			    ImageOdf imageOdf= new ImageOdf(newList.get(i));
			    imageOdf.setOdf(currentOdf);
			    addImageOdf(imageOdf);
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
	public double updatePayement(Odf odf, double payementAjouté, double payementTotal){
		if (new Double(odf.getPayementActuel())==null ) {
			odf.setPayementActuel(0);
		}
		double newPayement= odf.getPayementActuel()+payementAjouté;
		odf.setPayementActuel(newPayement);
		odf.setPayementTotal(payementTotal);
		return payementTotal-newPayement;
	}
	

}
