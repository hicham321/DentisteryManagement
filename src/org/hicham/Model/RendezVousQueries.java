package org.hicham.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

public class RendezVousQueries {
	public void getRendezVousAndAct(Patient patient){
		
	}
	public List<Patient> getPatientsFromDate(Date date){

		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		List<Patient> ListOfPatients= new ArrayList<>();
		try {
			List<Patient>ListOfAllPatients= session.createQuery("from Patient").list();
			for (int i = 0; i < ListOfAllPatients.size(); i++) {
				List<Act> actsOfPatient= ListOfAllPatients.get(i).getActList();
				for (int j = 0; j < actsOfPatient.size(); j++) {
					if(actsOfPatient.get(j).getDateRendezVous().equals(date)){
						ListOfPatients.add(actsOfPatient.get(j).getPatient());
					}
				}
					
				
  

			}
           //use the database way here by creating a pure hql to get the patients and compare time with previous way
			
		} finally {
			session.close();
		}	
		return ListOfPatients;
	}

}
