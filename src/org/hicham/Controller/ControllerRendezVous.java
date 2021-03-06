package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hicham.Model.Act;
import org.hicham.Model.Patient;
import org.hicham.Model.RendezVousQueries;
import org.hicham.Model.ProduitQueries.MyClass;
import org.hicham.View.RendezVousView;

public class ControllerRendezVous {

	RendezVousView rendezVousView= new RendezVousView();
	RendezVousQueries rendezVousQueries= new RendezVousQueries();

	int selectedposition=0;
	List<String> datesRendezVous= new ArrayList<>();
	List<String> actsRendezVous= new ArrayList<>();
	List<String> payements= new ArrayList<>();
	List<String> versements= new ArrayList<>();
	List<String> rests= new ArrayList<>();
	List<String> temps= new ArrayList<>();

	List<Patient> patients= new ArrayList<>();

	public ControllerRendezVous(RendezVousView rendezVousView,RendezVousQueries rendezVousQueries){
		this.rendezVousView= rendezVousView;
		this.rendezVousQueries= rendezVousQueries;
		this.rendezVousView.addRendezVousActionListener(new RendezVousActionListener());
		this.rendezVousView.addRendezVousListSelectionListener(new RendezVousListSelectionListener());

	}

	class RendezVousActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource()==rendezVousView.getRechercheBtn() & rendezVousView.getModeRechercheList().getSelectedIndex()==1) {
				//show panel based on mode of search
				//int patientid=rendezVousView.getPatientCombo().getSelectedIndex();
				//show par rendez vous  panel
				try{
					Date dateObject= rendezVousView.getDatePicker().getDate();
					Date olddate= new Date();
					//olddate = new SimpleDateFormat("ddd MMM dd HH:mm:ss z yyyy").parse(dateObject.toString());
					String newDate= new SimpleDateFormat("dd-MM-yyyy").format(dateObject);
					patients=rendezVousQueries.getPatientsFromDate(dateObject);
					rendezVousView.getDateRV().setText(newDate);


					List<String>names = new ArrayList<>();
					for (int i = 0; i < patients.size(); i++) {
						//populate combobox with patient nom
						names.add(patients.get(i).getNomEtPrenom());
					}
					//populate patient combo with names
					DefaultComboBoxModel dcbm=addNamesToCombo(names);
					rendezVousView.getPatientComboRV().setModel(dcbm);


					showRendezVousInfoPanel();
				}catch(Exception ex){
					ex.printStackTrace();
					/*JOptionPane.showOptionDialog(null
							,ex.toString()
							, "Erreur"
							, JOptionPane.OK_CANCEL_OPTION
							, JOptionPane.INFORMATION_MESSAGE, null, null, null);*/
				}

			}
			if (e.getSource()==rendezVousView.getRechercheBtn() & rendezVousView.getModeRechercheList().getSelectedIndex()==0) {
				//show par patient  panel
				try{
					int selectedIndex=rendezVousView.getPatientCombo().getSelectedIndex();
					List<Act> acts=rendezVousQueries.getActFromPatient(selectedIndex);

					String nomPatient= rendezVousView.getPatientCombo().getSelectedItem().toString();
					datesRendezVous= new ArrayList<>();
					actsRendezVous= new ArrayList<>();
					payements= new ArrayList<>();
					temps= new ArrayList<>();

					for (int i=0; i<acts.size();i++) {

						Act act= acts.get(i);
						datesRendezVous.add(act.getDate().toString());
						actsRendezVous.add(act.getEntante());
						payements.add(new Double(act.getPayementTotal()).toString());
						versements.add(new Double(act.getPayementActuel()).toString());
						double rest= act.getPayementTotal()-act.getPayementActuel();
						rests.add(new Double(rest).toString());
						temps.add(act.getTemp());

					}
					//chargé dateCombo avec les dates 
					DefaultComboBoxModel dcbm=addDateToCombo(datesRendezVous);
					rendezVousView.getActCombo().setModel(dcbm);

					rendezVousView.getNomPatient().setText(nomPatient);

					showPatientInfoPanel();
				}catch(Exception ex){

					JOptionPane.showOptionDialog(null
							,"il n'ya pas de rendez vous ou des patients ?"
							, "Erreur"
							, JOptionPane.OK_CANCEL_OPTION
							, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				}

			}
			if (e.getSource()== rendezVousView.getActCombo()) {
				int selectedItem= selectedComboItem(e);
				try {
					String tempRV=temps.get(selectedItem);
					String actRV=actsRendezVous.get(selectedItem);
					String payRV=payements.get(selectedItem);
					String restRv=rests.get(selectedItem);
					String actuelPayRV=versements.get(selectedItem);

					setPatientPanel(actRV,payRV,actuelPayRV,restRv,tempRV);
				} catch (Exception e2) {
					e2.printStackTrace();			
				}

			}
			if (e.getSource()== rendezVousView.getPatientCombo()) {

				//resetting the rendez Vous labels when another patient is selected
				rendezVousView.getTempsRendezVous().setText("");
				rendezVousView.getActRendezVous().setText("");
				rendezVousView.getPayement().setText("");
				rendezVousView.getVerse().setText("");
				rendezVousView.getRestVersement().setText("");


			}
			if (e.getSource()== rendezVousView.getPatientComboRV()) {
				int selectedItem= selectedComboItem(e);
				Date dateObject= rendezVousView.getDatePicker().getDate();
				Patient patient=patients.get(selectedItem);

				List<Act> acts= patient.getActList();
				Act searchedAct= new Act();
				for (int i = 0; i < acts.size(); i++) {

					if (acts.get(i).getDate().equals(rendezVousView.getDatePicker().getDate())){
						searchedAct=acts.get(i);
					}
				}
				//set labels depending on selected act
				rendezVousView.getPayementRV().setText(new Double(searchedAct.getPayementActuel()).toString());
				rendezVousView.getVerseRV().setText(new Double(searchedAct.getPayementActuel()).toString());
				double rest= searchedAct.getPayementActuel()-searchedAct.getPayementActuel();
				rendezVousView.getRestVersementRV().setText(new Double(rest).toString());
				rendezVousView.getActPatientRV().setText(searchedAct.getEntante());
				rendezVousView.getTempRV().setText(searchedAct.getTemp());

			}




		}

	}
	class RendezVousListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getSource()==rendezVousView.getModeRechercheList()) {

				if (rendezVousView.getModeRechercheList().getSelectedIndex()==0) {
					//deactivate recherche par date
					rendezVousView.getDatePicker().setEnabled(false);
					rendezVousView.getPatientCombo().setEnabled(true);



				}
				if (rendezVousView.getModeRechercheList().getSelectedIndex()==1) {
					//deactivate recherche par patient
					rendezVousView.getPatientCombo().setEnabled(false);
					rendezVousView.getDatePicker().setEnabled(true);


				}
			}			
		}

	}

	public void setPatientPanel(String actPatient, String payementTotal,String verse,String restVersemement, String temp){

		rendezVousView.getActRendezVous().setText(actPatient);
		rendezVousView.getTempsRendezVous().setText(temp);
		rendezVousView.getPayement().setText(new Double(payementTotal).toString());
		rendezVousView.getVerse().setText(new Double(verse).toString());
		rendezVousView.getRestVersement().setText(new Double(restVersemement).toString());


		rendezVousView.getPanelRechercheRendezVous().revalidate();
		rendezVousView.getPanelRechercheRendezVous().repaint();

	}
	public void setRendezVousPanel(){

		rendezVousView.getPanelRechercheRendezVous().add(rendezVousView.getPanelRendezVous());

		rendezVousView.getPanelRechercheRendezVous().revalidate();
		rendezVousView.getPanelRechercheRendezVous().repaint();

	}
	public DefaultComboBoxModel addDateToCombo(List<String> datesRendezVous){
		DefaultComboBoxModel comboModel= new DefaultComboBoxModel<>();
		for(int i=0;i<datesRendezVous.size();i++){
			comboModel.addElement(new MyClass(datesRendezVous.get(i)));	
		}
		return comboModel;
	}
	public DefaultComboBoxModel addNamesToCombo(List<String> patientNames){
		DefaultComboBoxModel comboModel= new DefaultComboBoxModel<>();
		for(int i=0;i<patientNames.size();i++){
			comboModel.addElement(new MyClass(patientNames.get(i)));	
		}
		return comboModel;
	}

	public int selectedComboItem(ActionEvent e){
		//get action from product combobox
		JComboBox comboBox = (JComboBox) e.getSource();
		int selected = comboBox.getSelectedIndex();
		selectedposition=selected;
		return selected;
	}



	public void showPatientInfoPanel(){
		CardLayout cardLayout = (CardLayout) rendezVousView.cards.getLayout();
		cardLayout.show(rendezVousView.cards, "Card 1");
	}
	public void showRendezVousInfoPanel(){
		CardLayout cardLayout = (CardLayout) rendezVousView.cards.getLayout();
		cardLayout.show(rendezVousView.cards, "Card 2");
	}



}
