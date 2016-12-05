package org.hicham.Model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

public class MedicamentQueries {
	
	public void addBatchMedicament(List<String> nomMedList){
		Session session = SessionsDB.getFactory().openSession();
		try {

			//save Medicament object
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
	public List<String> listOfMeds(){
		List<String> listOfMeds= new ArrayList<>();
		return listOfMeds;
	}
}
