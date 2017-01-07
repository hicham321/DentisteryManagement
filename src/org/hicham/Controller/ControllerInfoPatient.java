package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import org.hicham.Model.Act;
import org.hicham.Model.Patient;
import org.hicham.Model.PatientQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.InfoPatient;
import org.hicham.View.RecherchePatientView;

public class ControllerInfoPatient {

	InfoPatient infoPatient= new InfoPatient();
	PatientQueries patientQueries= new PatientQueries();
	RecherchePatientView recherchePatientView= new RecherchePatientView();
	ActPatientView actPatientView= new ActPatientView();
	//this field needs to be updated when adding a new patient or when selecting a new patient
	Patient currentPatient= new Patient();




	public ControllerInfoPatient(InfoPatient infoPatient, PatientQueries patientQueries 
			,RecherchePatientView recherchePatientView,ActPatientView actPatientView){

		this.infoPatient= infoPatient;
		this.patientQueries=patientQueries;
		this.recherchePatientView=recherchePatientView;
		this.actPatientView= actPatientView;
		this.infoPatient.addInfoPatientActionListener(new InfoPatientActionListener());
	}
	class InfoPatientActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==infoPatient.getOk()) {

				infoPatient.getModifie().setEnabled(true);


				//a bunch of if statements to control what's in the text fields and than an else to 
				currentPatient= new Patient(infoPatient.getNom().getText()
						+ " "+infoPatient.getPrenom().getText()
						,infoPatient.getNom().getText()
						,infoPatient.getPrenom().getText()
						,infoPatient.getAge().getText()
						,infoPatient.getAddress().getText()
						,infoPatient.getTel().getText()
						,infoPatient.getTeinte().getSelectedItem().toString()
						,infoPatient.getSex().getSelectedItem().toString()
						,infoPatient.getAnticident().getText()
						,infoPatient.getFonction().getText()
						);
				patientQueries.addPatient(currentPatient);



				setFieldsEmpty();

			}
			if (e.getSource()== infoPatient.getRechCombo()) {
				infoPatient.getOk().setEnabled(false);
				infoPatient.getModifie().setEnabled(true);

				int selecteditem=infoPatient.getRechCombo().getSelectedIndex();
				List<Patient>patients=patientQueries.findAllPatients();
				Patient selectedPatient=patients.get(selecteditem);
				currentPatient=selectedPatient;
				//show selected patient info in text fields
				setFieldsPatientInfo(selectedPatient.getName(), selectedPatient.getPrenom(), 
						selectedPatient.getAge(), selectedPatient.getAddress(),
						selectedPatient.getTel(), selectedPatient.getTeinte(),
						selectedPatient.getSex(),selectedPatient.getAnticident(),
						selectedPatient.getFonction());
				
				//setfieldPatientDisabled();
				//show related patient acts
				setFieldsActInfo();
				//set info in act for the selected patient
				List<Act>acts=currentPatient.getActList();
				List<String> actsDates= new ArrayList<>();
				for (int i = 0; i < acts.size(); i++) {
					String date= acts.get(i).getDateRendezVous().toString();
					actsDates.add(date);
				}
				DefaultComboBoxModel dfcm=patientQueries.comboBoxModel(actsDates);
				actPatientView.getListActCombo().setModel(dfcm);

			}
			if (e.getSource()== infoPatient.getNouveauPatient()) {
				//set all fields empty and enable ok button 
                setfieldPatientenabled();
				setFieldsActDisabled();
				setFieldsEmpty();
				infoPatient.getOk().setEnabled(true);
				infoPatient.getModifie().setEnabled(false);

			}
			if (e.getSource()== infoPatient.getModifie()) {
				setfieldPatientenabled();

				modifyPatientFields(infoPatient.getNom().getText()
						, infoPatient.getPrenom().getText()
						, infoPatient.getNom().getText()+ " "+infoPatient.getPrenom().getText()
						, infoPatient.getAge().getText()
						, infoPatient.getTeinte().getSelectedItem().toString()
						, infoPatient.getFonction().getText()
						, infoPatient.getTel().getText()
						, infoPatient.getAnticident().getText()
						, infoPatient.getAddress().getText()
						, infoPatient.getSex().getSelectedItem().toString());
				patientQueries.addPatient(currentPatient);
			}
			if (e.getSource()== infoPatient.getSuppPatient()) {
				int input = JOptionPane.showOptionDialog(null, "est ce que vous ete sure de supprimé ce patient", "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION)
				{
				    // do something
					patientQueries.deletePatient(currentPatient);

				}
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
		public void setfieldPatientDisabled(){
			infoPatient.getNom().setEnabled(false);
			infoPatient.getPrenom().setEnabled(false);
			infoPatient.getAge().setEnabled(false);
			infoPatient.getAddress().setEnabled(false);
			infoPatient.getTel().setEnabled(false);
			infoPatient.getTeinte().setEnabled(false);
			infoPatient.getSex().setEnabled(false);
			infoPatient.getAnticident().setEnabled(false);
			infoPatient.getFonction().setEnabled(false);
		}
		public void setfieldPatientenabled(){
			infoPatient.getNom().setEnabled(true);
			infoPatient.getPrenom().setEnabled(true);
			infoPatient.getAge().setEnabled(true);
			infoPatient.getAddress().setEnabled(true);
			infoPatient.getTel().setEnabled(true);
			infoPatient.getTeinte().setEnabled(true);
			infoPatient.getSex().setEnabled(true);
			infoPatient.getAnticident().setEnabled(true);
			infoPatient.getFonction().setEnabled(true);
		}
		
		
		public void setFieldsPatientInfo(String nom,String prenom, String age,String address
				,String tel,String teinte,String sex,String anticident, String fonction){
			
			infoPatient.getNom().setText(nom);
			infoPatient.getPrenom().setText(prenom);
			infoPatient.getAge().setText(age);
			infoPatient.getAddress().setText(address);
			infoPatient.getTel().setText( tel);
			infoPatient.getTeinte().setSelectedItem(teinte);
			infoPatient.getSex().setSelectedItem(sex);;
			infoPatient.getAnticident().setText(anticident);
			infoPatient.getFonction().setText(fonction);
		}
		public void setFieldsActInfo(){
			actPatientView.getActText().setText("");
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			actPatientView.getTimePicker().setValue(cal.getTime());
			Date date= new Date();
			actPatientView.getDatePicker().setDate(date);
			actPatientView.getPayementCombo().setSelectedIndex(0);
		}
		public void setFieldsActDisabled(){
			setFieldsActInfo();
			actPatientView.getActText().setEnabled(false);
			actPatientView.getTimePicker().setEnabled(false);
			actPatientView.getDatePicker().setEnabled(false);
			actPatientView.getPayementCombo().setEnabled(false);
			actPatientView.getOkImage().setEnabled(false);
			actPatientView.getOuvrir().setEnabled(false);
			//actPatientView.getListActCombo().setEnabled(false);
			//actPatientView.getNouveauAct().setEnabled(false);
			actPatientView.getOk().setEnabled(false);
			//actPatientView.getModifie().setEnabled(false);
		}
		public void setFieldsActEnabled(){
			setFieldsActInfo();
			actPatientView.getActText().setEnabled(true);
			actPatientView.getTimePicker().setEnabled(true);
			actPatientView.getDatePicker().setEnabled(true);
			actPatientView.getPayementCombo().setEnabled(true);
			actPatientView.getOkImage().setEnabled(true);
			actPatientView.getOuvrir().setEnabled(true);
			actPatientView.getListActCombo().setEnabled(true);
			actPatientView.getNouveauAct().setEnabled(true);
			actPatientView.getOk().setEnabled(false);
			actPatientView.getModifie().setEnabled(true);
		}
		
		public void modifyPatientFields(String name, String prenom,String nomEtPrenom
				,String age, String teinte, String fonction,String tel, String anticident
				,String address ,String sex ){
			
			currentPatient.setAge(age);
			currentPatient.setAddress(address);
			currentPatient.setAnticident(anticident);
			currentPatient.setFonction(fonction);
			currentPatient.setName(name);
			currentPatient.setNomEtPrenom(nomEtPrenom);
			currentPatient.setPrenom(prenom);
			currentPatient.setSex(sex);
			currentPatient.setTel(tel);
			currentPatient.setTeinte(teinte);
			
		}



	}


	public Patient getCurrentPatient() {
		return currentPatient;
	}

}
