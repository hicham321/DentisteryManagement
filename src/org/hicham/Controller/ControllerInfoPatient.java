package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import org.hicham.Model.Patient;
import org.hicham.Model.PatientQueries;
import org.hicham.View.InfoPatient;
import org.hicham.View.RecherchePatientView;

public class ControllerInfoPatient {
	
	InfoPatient infoPatient= new InfoPatient();
	PatientQueries patientQueries= new PatientQueries();
	RecherchePatientView recherchePatientView= new RecherchePatientView();
	boolean errorFlag= false;
	
	//this field needs to be updated when adding a new patient or when selecting a new patient
	Patient currentPatient= new Patient();
	
	
	public ControllerInfoPatient(InfoPatient infoPatient, PatientQueries patientQueries ,RecherchePatientView recherchePatientView){
		
		this.infoPatient= infoPatient;
		this.patientQueries=patientQueries;
		this.recherchePatientView=recherchePatientView;
		this.infoPatient.addInfoPatientActionListener(new InfoPatientActionListener());
	}
	class InfoPatientActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==infoPatient.getOk()) {
				
				infoPatient.getModifie().setEnabled(true);
				DefaultComboBoxModel dftb=patientQueries.getComboModel();
				recherchePatientView.getRech().setModel(dftb);
				
				//a bunch of if statements to control what's in the text fields and than an else to 
				currentPatient= new Patient(infoPatient.getNom().getText()
						,infoPatient.getPrenom().getText()
						,Integer.parseInt(infoPatient.getAge().getText())
						,infoPatient.getAddress().getText()
						,Integer.parseInt(infoPatient.getTel().getText())
						,infoPatient.getTeinte().getSelectedItem().toString()
						,infoPatient.getSex().getSelectedItem().toString()
						,infoPatient.getAnticident().getText()
						,infoPatient.getFonction().getText()
						);
				patientQueries.addPatient(currentPatient);
				
				

				setFieldsEmpty();

			}
			if (e.getSource()== infoPatient.getModifie()) {

			}
		}

		//condition methods for patient:
		
		//setting the text fields to empty after clicking ok
		public void setFieldsEmpty(){
			infoPatient.getNom().setText("");
			infoPatient.getPrenom().setText("");
			infoPatient.getAge().setText("");
			infoPatient.getAddress().setText("");
			infoPatient.getTel().setText("");
			infoPatient.getTeinte().setSelectedIndex(0);
			infoPatient.getSex().setSelectedIndex(0);
			infoPatient.getAnticident().setText("");
			infoPatient.getFonction().setText("");
		}
		
		
		public void nomCondition(){
			if (infoPatient.getNom().getText().matches("[ \t]+") || "".equals(infoPatient.getNom().getText())) {
				infoPatient.getErrorLabNom().setVisible(true);
				errorFlag= true;
			}
		}
		public void prenomCondition(){
			if (infoPatient.getPrenom().getText().matches("[ \t]+") || "".equals(infoPatient.getPrenom().getText())) {
				infoPatient.getErrorLabPrenom().setVisible(true);
				errorFlag= true;

			}
		}
		public void ageCondition(){
			if (infoPatient.getAge().getText().matches("[ \t]+") ) {
				infoPatient.getErrorLabAge().setVisible(true);
				errorFlag= true;

			}
		}
		public void addressCondition(){
			if (infoPatient.getAddress().getText().matches("[ \t]+") ) {
				infoPatient.getErrorLabAddress().setVisible(true);
				errorFlag= true;

			}
		}
		public void anticidentCondition(){
			if (infoPatient.getAnticident().getText().matches("[ \t]+") ) {
				infoPatient.getErrorLabAnticident().setVisible(true);
				errorFlag= true;

			}
		}
		public void fonctionCondition(){
			if (infoPatient.getFonction().getText().matches("[ \t]+") ) {
				infoPatient.getErrorLabFonction().setVisible(true);
				errorFlag= true;
			}
		}
		
	}
	public Patient getCurrentPatient() {
		return currentPatient;
	}
	
}
