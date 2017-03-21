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
import org.hicham.Model.Odf;
import org.hicham.Model.Patient;
import org.hicham.Model.PatientQueries;
import org.hicham.Model.ProtheseFixe;
import org.hicham.Model.ProthesePartielle;
import org.hicham.Model.ProtheseTotale;
import org.hicham.View.ActPatientView;
import org.hicham.View.InfoPatient;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.ProtheseFixeView;
import org.hicham.View.ProthesePartielleView;
import org.hicham.View.ProtheseTotaleView;
import org.hicham.View.RecherchePatientView;

public class ControllerInfoPatient {

	InfoPatient infoPatient= new InfoPatient();
	PatientQueries patientQueries= new PatientQueries();
	RecherchePatientView recherchePatientView= new RecherchePatientView();
	ActPatientView actPatientView= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	Ordonance ordonance= new Ordonance();
	//this field needs to be updated when adding a new patient or when selecting a new patient
	Patient currentPatient= new Patient();

    boolean patientSelected= false;


	public ControllerInfoPatient(InfoPatient infoPatient, PatientQueries patientQueries 
			,RecherchePatientView recherchePatientView
			,ActPatientView actPatientView,OdfPatient odfPatient
			,ProtheseFixeView protheseFixeView,ProthesePartielleView prothesePartielleView
			,ProtheseTotaleView protheseTotaleView,	Ordonance ordonance){

		this.infoPatient= infoPatient;
		this.patientQueries=patientQueries;
		this.recherchePatientView=recherchePatientView;
		this.actPatientView= actPatientView;
		this.odfPatient= odfPatient;
		this.protheseFixeView= protheseFixeView;
		this.prothesePartielleView= prothesePartielleView;
		this.protheseTotaleView= protheseTotaleView;
		this.ordonance= ordonance;
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
				patientSelected= true;
				setFieldsEmpty();
				setFieldsActEnabled();
				setAllActivityFieldsEmpty();
				setNameInActivities(currentPatient.getNomEtPrenom());

			}
			if (e.getSource()== infoPatient.getRechCombo()) {
				setAllActivityFieldsEmpty();
				setFieldsActEnabled();
				infoPatient.getOk().setEnabled(false);
				infoPatient.getModifie().setEnabled(true);
				

				int selecteditem=infoPatient.getRechCombo().getSelectedIndex();
				List<Patient>patients=patientQueries.findAllPatients();
				Patient selectedPatient=patients.get(selecteditem);
				currentPatient=selectedPatient;
				protheseFixeView.getNomPrenom().setText(currentPatient.getNomEtPrenom());
				prothesePartielleView.getNomPrenom().setText(currentPatient.getNomEtPrenom());
				protheseTotaleView.getNomPrenom().setText(currentPatient.getNomEtPrenom());
			    actPatientView.getNomPrenom().setText(currentPatient.getNomEtPrenom());
				odfPatient.getNomPrenom().setText(currentPatient.getNomEtPrenom());
				
				patientSelected= true;

				//show selected patient info in text fields
				setFieldsPatientInfo(selectedPatient.getName(), selectedPatient.getPrenom(), 
						selectedPatient.getAge(), selectedPatient.getAddress(),
						selectedPatient.getTel(), selectedPatient.getTeinte(),
						selectedPatient.getSex(),selectedPatient.getAnticident(),
						selectedPatient.getFonction());
				
				
				//this part can be multithreaded

				//show related patient acts
				setFieldsActInfo();
				//set info in act for the selected patient
				List<Act>acts=currentPatient.getActList();
				List<String> actsDates= new ArrayList<>();
				for (int i = 0; i < acts.size(); i++) {
					Date date= acts.get(i).getDate();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                String formatedDate = simpleDateFormat.format(date);
					actsDates.add(formatedDate);
				}
				DefaultComboBoxModel dfcmAct=patientQueries.comboBoxModel(actsDates);
				actPatientView.getListRVCombo().setModel(dfcmAct);
				//set info in act for the selected patient
				List<Odf>odfs=currentPatient.getOdfList();
				List<String> odfsDates= new ArrayList<>();
				for (int i = 0; i < odfs.size(); i++) {
					Date date= odfs.get(i).getDate();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                String formatedDate = simpleDateFormat.format(date);
					odfsDates.add(formatedDate);
				}
				DefaultComboBoxModel dfcmOdf=patientQueries.comboBoxModel(odfsDates);
				odfPatient.getListRVCombo().setModel(dfcmOdf);
				//prothese fixe
				List<ProtheseFixe>prothesesFixe=currentPatient.getProtheseFixes();
				List<String> protheseFixeDates= new ArrayList<>();
				for (int i = 0; i < prothesesFixe.size(); i++) {
					Date date= prothesesFixe.get(i).getDate();
					//format date
	                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                String formatedDate = simpleDateFormat.format(date);
	                protheseFixeDates.add(formatedDate);
				}
				DefaultComboBoxModel dfcmProthFixe=patientQueries
						.comboBoxModel(protheseFixeDates);
				protheseFixeView.getListRVCombo().setModel(dfcmProthFixe);
				//prothese Partielle
				List<ProthesePartielle>prothesesPartielle=currentPatient.getProthesePartielles();
				List<String> prothesePartielleDates= new ArrayList<>();
				for (int i = 0; i < prothesesPartielle.size(); i++) {
					Date date= prothesesPartielle.get(i).getDate();
					//format date
	                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                String formatedDate = simpleDateFormat.format(date);
	                prothesePartielleDates.add(formatedDate);
				}
				DefaultComboBoxModel dfcmProthPartielle=patientQueries
						.comboBoxModel(prothesePartielleDates);
				prothesePartielleView.getListRVCombo().setModel(dfcmProthPartielle);
				//prothese Totale
				List<ProtheseTotale>prothesesTotale=currentPatient.getProtheseTotales();
				List<String> protheseTotaleDates= new ArrayList<>();
				for (int i = 0; i < prothesesTotale.size(); i++) {
					Date date= prothesesTotale.get(i).getDate();
					//format date
	                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                String formatedDate = simpleDateFormat.format(date);
	                protheseTotaleDates.add(formatedDate);
				}
				DefaultComboBoxModel dfcmProthTotale=patientQueries
						.comboBoxModel(protheseTotaleDates);
				protheseTotaleView.getListRVCombo().setModel(dfcmProthTotale);

				setNameInActivities(currentPatient.getNomEtPrenom());
				}
			if (e.getSource()== infoPatient.getNouveauPatient()) {
				//set all fields empty and enable ok button 
				setNameInActivities("");
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
				int input = JOptionPane.showOptionDialog(null
						,"Etes vous sure de vouloir supprimer ce patient?"
						, "Supprimer Un Patient"
						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					// do something
					patientQueries.deletePatient(currentPatient);
					patientSelected= false;
					setFieldsEmpty();
					//refresh combobox

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
			/*actPatientView.getActText().setText("");
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			actPatientView.getTimePicker().setValue(cal.getTime());
			Date date= new Date();
			actPatientView.getDatePicker().setDate(date);
			actPatientView.getPayementCombo().setSelectedIndex(0);*/
		}
		public void setFieldsActDisabled(){
			/*setFieldsActInfo();
			actPatientView.getActText().setEnabled(false);
			actPatientView.getTimePicker().setEnabled(false);
			actPatientView.getDatePicker().setEnabled(false);
			actPatientView.getPayementCombo().setEnabled(false);
			actPatientView.getOkImage().setEnabled(false);
			actPatientView.getOuvrir().setEnabled(false);
			actPatientView.getOk().setEnabled(false);*/
		}
		public void setFieldsActEnabled(){
			/*setFieldsActInfo();
			actPatientView.getActText().setEnabled(true);
			actPatientView.getTimePicker().setEnabled(true);
			actPatientView.getDatePicker().setEnabled(true);
			actPatientView.getPayementCombo().setEnabled(true);
			actPatientView.getOkImage().setEnabled(true);
			actPatientView.getOuvrir().setEnabled(true);
			actPatientView.getListActCombo().setEnabled(true);
			actPatientView.getNouveauAct().setEnabled(true);
			actPatientView.getOk().setEnabled(false);
			actPatientView.getModifie().setEnabled(true);*/
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

		
		public void setNameInActivities(String nomEtPrenom){
			protheseFixeView.getNomPrenom().setText(nomEtPrenom);
			protheseTotaleView.getNomPrenom().setText(nomEtPrenom);
			prothesePartielleView.getNomPrenom().setText(nomEtPrenom);
			actPatientView.getNomPrenom().setText(nomEtPrenom);
			odfPatient.getNomPrenom().setText(nomEtPrenom);
			ordonance.getNomEtPrenom().setText(nomEtPrenom);
		}
		public void setAllActivityFieldsEmpty(){
			protheseFixeView.setEmptyFields();
			protheseTotaleView.setEmptyFields();
			prothesePartielleView.setEmptyFields();
			actPatientView.setEmptyFields();
			odfPatient.setEmptyFields();
		}
		



	}


	public Patient getCurrentPatient() {
		return currentPatient;
	}

}
