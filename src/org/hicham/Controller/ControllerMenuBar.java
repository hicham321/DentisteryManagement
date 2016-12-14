package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import org.hicham.Model.PatientQueries;
import org.hicham.Model.ProduitQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.GestionStockView;
import org.hicham.View.HomePanel;
import org.hicham.View.InfoPatient;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.PatientView;
import org.hicham.View.RecherchePatientView;
import org.hicham.View.RendezVousView;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

public class ControllerMenuBar {

	MenuBar menuBar= new MenuBar();

	Ordonance ordonance = new Ordonance();
	InfoPatient infoPatient= new InfoPatient();
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
    RecherchePatientView recherchePatientView= new RecherchePatientView();
    GestionStockView gestionStockView= new GestionStockView();
   
    RendezVousView rendezVousView= new RendezVousView();
	PatientView patient= new PatientView(infoPatient,actPatient,odfPatient,ordonance,recherchePatientView);
	PatientQueries patientQueries= new PatientQueries();
	public HomePanel homePanel= new HomePanel();
		
	

	ProduitQueries produitQueries= new ProduitQueries();

	MainFrame mainFrame= new MainFrame(homePanel,patient,gestionStockView,rendezVousView,menuBar);

	public ControllerMenuBar(MainFrame mainFrame,HomePanel homePanel,MenuBar menuBar,PatientView patient,Ordonance ordonance,GestionStockView gestionStockView,RendezVousView rendezVousView,ProduitQueries produitQueries,PatientQueries patientQueries){

		this.patient= patient;
		this.ordonance= ordonance;
		this.gestionStockView=gestionStockView;
		this.menuBar= menuBar;
		this.homePanel= homePanel;
		this.mainFrame= mainFrame;
		this.produitQueries=produitQueries;
		this.patientQueries= patientQueries;
		this.rendezVousView= rendezVousView;
		this.menuBar.addMenuBarActionListener(new MenuBarActionListener() );

	}
	class MenuBarActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource()== menuBar.getOrdonance()) {
				//show ordonance card
			}
			if (e.getSource()== menuBar.getPatient()) {
				//show patient card

			}
			if (e.getSource()== menuBar.getInfoPatientItem()) {
				//show info patient card
				showInfoPatientCard();
			}
			if (e.getSource()== menuBar.getRendezVousPatient()) {
				//show rendez vous card
				DefaultComboBoxModel dftb=patientQueries.getComboModel();
				rendezVousView.getPatientCombo().setModel(dftb);
				showRendezVousCard();

			}
			if (e.getSource()== menuBar.getLabo()) {
				//show labo card

			}
			if (e.getSource()== menuBar.getGestionStockItem()){
				//prepare gestionStockViiew Combobox with data
				DefaultComboBoxModel dftb=produitQueries.getComboModel();
				gestionStockView.getProduitCombo().setModel(dftb);	

				//show gestionStock card
				showGestionStockCard();
			}
			if (e.getSource()== menuBar.getStat()){
				//show gestionStock card

			}
			
			if (e.getSource()== menuBar.getRetour()) {
				//show first card

			}
			if (e.getSource()== menuBar.getMotpasse()) {
				//show motpasse frame

			}
			if (e.getSource()== menuBar.getMenuapropos()) {
				//show apropos frame

			}
			if (e.getSource()== menuBar.getAjoutbase()) {
				//show Jfileschooser card

			}
		}
	}
	public void showGestionStockCard(){
		CardLayout cardLayout = (CardLayout) mainFrame.cards.getLayout();
		cardLayout.show(mainFrame.cards, "Card 3");	

	}
	public void showInfoPatientCard(){
		CardLayout cardLayout = (CardLayout) mainFrame.cards.getLayout();
		cardLayout.show(mainFrame.cards, "Card 2");	
	}
	public void showRendezVousCard(){
		CardLayout cardLayout = (CardLayout) mainFrame.cards.getLayout();
		cardLayout.show(mainFrame.cards, "Card 4");	

	}

}

