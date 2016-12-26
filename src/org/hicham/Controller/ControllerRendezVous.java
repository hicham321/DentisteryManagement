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
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hicham.Model.Act;
import org.hicham.Model.Patient;
import org.hicham.Model.RendezVousQueries;
import org.hicham.View.RendezVousView;

public class ControllerRendezVous {

	RendezVousView rendezVousView= new RendezVousView();
	RendezVousQueries rendezVousQueries= new RendezVousQueries();
	
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
				List<Patient> listPatient=rendezVousQueries.getPatientsFromDate(dateObject);
				
				
				for (int i = 0; i < listPatient.size(); i++) {
					
					System.out.println(listPatient.get(i).getNom());

				}
				showRendezVousInfoPanel();

			}
			if (e.getSource()==rendezVousView.getRechercheBtn() & rendezVousView.getModeRechercheList().getSelectedIndex()==0) {
				//show par patient  panel
				
                System.out.println("patient panel is executed");
                
				int selectedIndex=rendezVousView.getPatientCombo().getSelectedIndex();
				List<Act> acts=rendezVousQueries.getActFromPatient(selectedIndex);


				for (Iterator iterator = acts.iterator(); iterator.hasNext();) {
					Act act = (Act) iterator.next();
					System.out.println(act.getDateRendezVous() +" a " +act.getTempRendezVous() +"\n"
							+ "l'act est: "	+ act.getAct() );

					setPatientPanel(rendezVousView.getPatientCombo().getSelectedItem().toString()
							, act.getDateRendezVous().toString()
							,act.getAct(), act.getPayement());
					showPatientInfoPanel();

				}

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
	public void setPatientPanel(String nomPatient,String dateRendezVous, String actPatient, double Payement){
        
		rendezVousView.getNomPatient().setText(nomPatient);
		rendezVousView.getPayement().setText(new Double(Payement).toString());
		rendezVousView.getDateRendezVous().setText(dateRendezVous);
		rendezVousView.getActPatient().setText(actPatient);
		
		//rendezVousView.getPanelRechercheRendezVous().add(rendezVousView.getPanelPatient());
		rendezVousView.getPanelRechercheRendezVous().revalidate();
		rendezVousView.getPanelRechercheRendezVous().repaint();

	}
	public void setRendezVousPanel(){

		rendezVousView.getPanelRechercheRendezVous().add(rendezVousView.getPanelRendezVous());
        
		rendezVousView.getPanelRechercheRendezVous().revalidate();
		rendezVousView.getPanelRechercheRendezVous().repaint();

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
