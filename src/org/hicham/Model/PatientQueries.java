package org.hicham.Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.hibernate.Session;


public class PatientQueries {
	/*Configuration configuration = new Configuration().configure("/resources/hibernate.cfg.xml").addAnnotatedClass(Patient.class).addAnnotatedClass(Act.class);
	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	SessionFactory factory = configuration.buildSessionFactory(builder.build());*/
	
	//Patient patient;
	//queries to use factory field to instantiate sessions
	
	public void addPatient(Patient patientAdded){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		//Session session = SessionsDB.getFactory().openSession();
		try {

			//save patient object
			session.beginTransaction();
			session.saveOrUpdate(patientAdded );
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	public void  deletePatient(Patient patient){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		try {

			session.beginTransaction();
			session.delete(patient);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	public List<Patient> findAllPatients(){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
	    //Session session = SessionsDB.getFactory().openSession();
		try {

			List<Patient> listPatient= session.createQuery("from Patient").list();

			return listPatient;
		} finally {
			session.close();
		}	
	}
	public List<String> findPatientsNames(){
		List<String> listPatientNames= new ArrayList<>();
        List<Patient> listOfallPatients= findAllPatients();
        for (int i = 0; i < listOfallPatients.size(); i++) {
        	listPatientNames.add(listOfallPatients.get(i).getNomEtPrenom());
		}
		return listPatientNames ;
	}
	public List<Integer>findPatientsIds(){
		List<Integer> listPatientIds= new ArrayList<>();
        List<Patient> listOfallPatients= findAllPatients();
        for (int i = 0; i < listOfallPatients.size(); i++) {
        	listPatientIds.add(listOfallPatients.get(i).getId());
		}
		return listPatientIds ;
	}
	public DefaultComboBoxModel comboBoxModel(List<String> listPatientNames){
		
		DefaultComboBoxModel comboModel= new DefaultComboBoxModel<>();
		for(int i=0;i<listPatientNames.size();i++){
			comboModel.addElement(new MyClass(listPatientNames.get(i)));	
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
	
	public DefaultComboBoxModel getComboModel(){
		return comboBoxModel(findPatientsNames());
	}
	
	

}
