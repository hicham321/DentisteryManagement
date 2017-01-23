package org.hicham.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.hibernate.Session;
import org.hicham.Model.ProduitQueries.MyClass;

public class MedicamentQueries {
	
	
	//for the real deal we need to get the data directly from the text file or perform 
	//a data transfer to the database once 
	
	public void addBatchMedicament(List<String> nomMedList){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {

			session.beginTransaction();
			
			for ( int i=0; i<nomMedList.size(); i++ ) {
				session.save( new Medicament(nomMedList.get(i)) );
				if ( i % 20 == 0 ) { 
					//20, same as the JDBC batch size
					//flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	
	//getting meds of csv file into a list
	public List<String> listOfMeds() throws FileNotFoundException, IOException{
		List<String> listOfMeds= new ArrayList<>();
		//read the file
		BufferedReader br = new BufferedReader(new InputStreamReader
				(new FileInputStream("src/resources/BaseMed.txt")));         

        String line;
        while ((line = br.readLine()) != null) {
            // process the line
        	listOfMeds.add(line);
        }
        br.close();
		//
		return listOfMeds;
	}
	public List<String> listOfMedsDb() throws FileNotFoundException, IOException{
		SessionsDB factoryObject= new SessionsDB();
		Session session= factoryObject.getFactory().openSession();
		List<Medicament>listOfMedObjects= new ArrayList<>();
		try {
			listOfMedObjects= session.createQuery("from Medicament").list();


		} finally {
			session.close();
		}
		List<String> meds= new ArrayList<>();
		for (int i = 0; i < listOfMedObjects.size(); i++) {
			meds.add(listOfMedObjects.get(i).getNomMed());
		}

		return meds;
	}
	public boolean CheckMedicamentEmpty(){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		boolean isEmpty= false;
		try {
			List<Register> listUsers= session.createQuery("from Medicament").list();
			if (listUsers.isEmpty()) {
				isEmpty= true;
			}
            
		} finally {
			session.close();

		}
		return isEmpty;
	}
    public DefaultComboBoxModel comboBoxModel(List<String> listMed){
		
		DefaultComboBoxModel comboModel= new DefaultComboBoxModel<>();
		for(int i=0;i<listMed.size();i++){
			comboModel.addElement(new MyClass(listMed.get(i)));	
		}
		return comboModel;
	}
    public class MyClass{

		private String myName;

		public MyClass(String name){
			this.myName = name;
		}
		@Override
		public String toString(){
			return myName;
		}

	}
}
