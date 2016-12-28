package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
				
				System.out.println("rendez vous panel is executed ");
				Date dateObject= rendezVousView.getDatePicker().getDate();
				patients=rendezVousQueries.getPatientsFromDate(dateObject);
				rendezVousView.getDateRV().setText(dateObject.toString());
				
				
				List<String>names = new ArrayList<>();
				for (int i = 0; i < patients.size(); i++) {
					//populate combobox with patient nom
					 names.add(patients.get(i).getNom());
					
					
					/*for (int j = 0; j < patients.size(); j++) {
						if (acts.get(j).getDateRendezVous()== rendezVousView.getDatePicker().getDate()){
							actPatients =acts.get(j);
						}
					}
					
					
					System.out.println(patients.get(i).getNom());*/

				}
				//populate patient combo with names
				DefaultComboBoxModel dcbm=addNamesToCombo(names);
				rendezVousView.getPatientComboRV().setModel(dcbm);

				
				showRendezVousInfoPanel();

			}
			if (e.getSource()==rendezVousView.getRechercheBtn() & rendezVousView.getModeRechercheList().getSelectedIndex()==0) {
				//show par patient  panel
				int selectedIndex=rendezVousView.getPatientCombo().getSelectedIndex();
				List<Act> acts=rendezVousQueries.getActFromPatient(selectedIndex);

				String nomPatient= rendezVousView.getPatientCombo().getSelectedItem().toString();
				datesRendezVous= new ArrayList<>();
				actsRendezVous= new ArrayList<>();
				payements= new ArrayList<>();
				temps= new ArrayList<>();
                
				for (int i=0; i<acts.size();i++) {
					
					Act act= acts.get(i);
					datesRendezVous.add(act.getDateRendezVous().toString());
					actsRendezVous.add(act.getAct());
					payements.add(new Double(act.getPayement()).toString());
					temps.add(act.getTempRendezVous());

				}
				//chargé dateCombo avec les dates 
				DefaultComboBoxModel dcbm=addDateToCombo(datesRendezVous);
				rendezVousView.getActCombo().setModel(dcbm);

				rendezVousView.getNomPatient().setText(nomPatient);

				showPatientInfoPanel();

			}
			if (e.getSource()== rendezVousView.getActCombo()) {
				int selectedItem= selectedComboItem(e);

				String tempRV=temps.get(selectedItem);
				String actRV=actsRendezVous.get(selectedItem);
				String payRV=payements.get(selectedItem);
				setPatientPanel(actRV,payRV,tempRV);
			}
			if (e.getSource()== rendezVousView.getPatientCombo()) {

				//resetting the rendez Vous labels when another patient is selected
				rendezVousView.getTempsRendezVous().setText("");
				rendezVousView.getActRendezVous().setText("");
				rendezVousView.getPayement().setText("");

			}
			if (e.getSource()== rendezVousView.getPatientComboRV()) {
				int selectedItem= selectedComboItem(e);
				Date dateObject= rendezVousView.getDatePicker().getDate();
                Patient patient=patients.get(selectedItem);
               
				List<Act> acts= patient.getActList();
                Act searchedAct= new Act();
				for (int i = 0; i < acts.size(); i++) {
					
					if (acts.get(i).getDateRendezVous().equals(rendezVousView.getDatePicker().getDate())){
						searchedAct=acts.get(i);
					}
				}
				//set labels depending on selected act
				rendezVousView.getPayementRV().setText(new Double(searchedAct.getPayement()).toString());
				rendezVousView.getActPatientRV().setText(searchedAct.getAct());
				rendezVousView.getTempRV().setText(searchedAct.getTempRendezVous());

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



	public void showInfoProduct(int selectedItem){
		//get product
		//		Produit produit=produitQueries.getProduct(selectedItem);
		//		String ProductName= produit.getProduitNom();
		//		Double prixProduct=produit.getPrix();
		//		Integer qte= produit.getQte();
		//		//set labels for selected product
		//		gestionStockView.getProduitNomInfo().setText(ProductName);
		//		gestionStockView.getProduitPrixInfo().setText(prixProduct.toString());
		//		gestionStockView.getQteInfo().setText(qte.toString());
		//	    //calculate prix total:
		//		Double prixTotal= prixProduct*qte;
		//		gestionStockView.getPrixTotal().setText(prixTotal.toString());
	}
	public void setPatientPanel(String actPatient, String Payement, String temp){
        
		rendezVousView.getActRendezVous().setText(actPatient);
		rendezVousView.getTempsRendezVous().setText(temp);
		rendezVousView.getPayement().setText(new Double(Payement).toString());
		
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
