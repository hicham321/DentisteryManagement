package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.hicham.Model.PatientQueries;
import org.hicham.Model.ProduitQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.ChangeMotPassView;
import org.hicham.View.GestionStockView;
import org.hicham.View.HomePanel;
import org.hicham.View.InfoPatient;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.PatientView;
import org.hicham.View.RecherchePatientView;
import org.hicham.View.RegisterView;
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
	RegisterView registerView= new RegisterView ();
    ChangeMotPassView changeMotPassView= new ChangeMotPassView();

	public HomePanel homePanel= new HomePanel(registerView,changeMotPassView);
		
	

	ProduitQueries produitQueries= new ProduitQueries();

	MainFrame mainFrame= new MainFrame(homePanel,patient,gestionStockView,rendezVousView,registerView,menuBar);

	public ControllerMenuBar(MainFrame mainFrame,HomePanel homePanel,MenuBar menuBar
			,PatientView patient,InfoPatient infoPatient,Ordonance ordonance,GestionStockView gestionStockView
			,RendezVousView rendezVousView,RecherchePatientView recherchePatientView
			,ProduitQueries produitQueries,PatientQueries patientQueries,ChangeMotPassView changeMotPassView){

		this.patient= patient;
		this.infoPatient= infoPatient;
		this.ordonance= ordonance;
		this.gestionStockView=gestionStockView;
		this.menuBar= menuBar;
		this.homePanel= homePanel;
		this.mainFrame= mainFrame;
		this.produitQueries=produitQueries;
		this.patientQueries= patientQueries;
		this.rendezVousView= rendezVousView;
		this.recherchePatientView= recherchePatientView;
		this.changeMotPassView= changeMotPassView;
		this.menuBar.addMenuBarActionListener(new MenuBarActionListener() );
		this.menuBar.addMenuBarMenuListener(new MenuBarMenuListener() );

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
				DefaultComboBoxModel dftb=patientQueries.getComboModel();
				recherchePatientView.getRech().setModel(dftb);
				infoPatient.getRechCombo().setModel(dftb);
			}
			if (e.getSource()== menuBar.getRendezVousPatient()) {
				//show rendez vous card
				showRendezVousCard();
				DefaultComboBoxModel dftb=patientQueries.getComboModel();
				rendezVousView.getPatientCombo().setModel(dftb);
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
				showHomeCard();

			}
			if (e.getSource()== menuBar.getMotpasse()) {
				//show motpasse frame
				changeMotPassView.setVisible(true);
				showHomeCard();			

			}
			if (e.getSource()== menuBar.getMenuapropos()) {
				//show apropos frame

			}
			if (e.getSource()== menuBar.getAjoutbase()) {
				//show Jfileschooser card

			}
		}
	}
	class MenuBarMenuListener implements MenuListener{

		@Override
		public void menuCanceled(MenuEvent e) {
			
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void menuSelected(MenuEvent e) {
			showHomeCard();			
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
	public void showHomeCard(){
		CardLayout cardLayout = (CardLayout) mainFrame.cards.getLayout();
		cardLayout.show(mainFrame.cards, "Card 1");	
	}

}

