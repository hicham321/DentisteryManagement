package org.hicham.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.annotations.Source;

public class RendezVousQueries {
	
	public List<Act> getActFromPatient(int selectedPosition){
		
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		
		List<Act> PatientActs= new ArrayList<>();		
        List<Patient> patientList= new ArrayList<>();
        List<Integer> patientIds= new ArrayList<>();
		try {
			patientList= session.createQuery("from Patient").list();
			for (int i = 0; i < patientList.size(); i++) {
				patientIds.add(patientList.get(i).getId());
				
			}
			
			Patient patient =(Patient) session.get(Patient.class, patientIds.get(selectedPosition));
			PatientActs= patient.getActList();

		} finally {
			session.close();
		}	
		return PatientActs;
	}
	public List<Integer> getPatientsIds(){
		List<Integer> listIds= new ArrayList<>();
		
		return listIds;
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
