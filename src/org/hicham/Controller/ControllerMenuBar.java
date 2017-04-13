package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingWorker;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.hicham.Model.Dentiste;
import org.hicham.Model.DentisteQueries;
import org.hicham.Model.MedicamentQueries;
import org.hicham.Model.PatientQueries;
import org.hicham.Model.ProduitQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.ChangeMotPassView;
import org.hicham.View.ExamenComplimentaireView;
import org.hicham.View.GestionStockView;
import org.hicham.View.HomePanel;
import org.hicham.View.InfoDentisteView;
import org.hicham.View.InfoPatient;
import org.hicham.View.JustificationAbsenceView;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.OrdonanceMenuView;
import org.hicham.View.PatientView;
import org.hicham.View.ProtheseFixeView;
import org.hicham.View.ProthesePartielleView;
import org.hicham.View.ProtheseTotaleView;
import org.hicham.View.ProtheseView;
import org.hicham.View.RegisterView;
import org.hicham.View.RendezVousView;
import org.hicham.View.StatisticsView;

public class ControllerMenuBar {

	MenuBar menuBar= new MenuBar();

	Ordonance ordonance = new Ordonance();
	InfoPatient infoPatient= new InfoPatient();
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	ProtheseView protheseView= new ProtheseView(protheseTotaleView,prothesePartielleView
			,protheseFixeView);
    GestionStockView gestionStockView= new GestionStockView();
   
    RendezVousView rendezVousView= new RendezVousView();
	PatientView patient= new PatientView(infoPatient,actPatient,odfPatient,protheseView,
			ordonance);
	PatientQueries patientQueries= new PatientQueries();
	RegisterView registerView= new RegisterView ();
    JustificationAbsenceView justificationAbsenceView= new JustificationAbsenceView();
    public OrdonanceMenuView ordonanceMenuView= new OrdonanceMenuView();
	ExamenComplimentaireView examenComplimentaireView= new ExamenComplimentaireView();

    ChangeMotPassView changeMotPassView= new ChangeMotPassView();
    ActPatientView actPatientView= new ActPatientView();

	public HomePanel homePanel= new HomePanel(registerView,changeMotPassView);
		
	

	ProduitQueries produitQueries= new ProduitQueries();
	InfoDentisteView infoDentisteView= new InfoDentisteView();
    StatisticsView statisticsView= new StatisticsView();

   
	MainFrame mainFrame= new MainFrame(homePanel,patient,gestionStockView,rendezVousView
			,justificationAbsenceView,registerView,ordonanceMenuView,examenComplimentaireView
			,infoDentisteView,statisticsView,menuBar);

	public ControllerMenuBar(MainFrame mainFrame,HomePanel homePanel,MenuBar menuBar
			,PatientView patient,InfoPatient infoPatient,Ordonance ordonance
			,GestionStockView gestionStockView,RendezVousView rendezVousView
			,ProduitQueries produitQueries
			,PatientQueries patientQueries,ChangeMotPassView changeMotPassView
			,JustificationAbsenceView justificationAbsenceView
			,OrdonanceMenuView ordonanceMenuView
			,ExamenComplimentaireView examenComplimentaireView
			,InfoDentisteView infoDentisteView
			,StatisticsView statisticsView){

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
		this.justificationAbsenceView =justificationAbsenceView;
		this.changeMotPassView= changeMotPassView;
		this.ordonanceMenuView= ordonanceMenuView;
		this.examenComplimentaireView=examenComplimentaireView;
		this.infoDentisteView= infoDentisteView;
		this.statisticsView= statisticsView;
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
			if (e.getSource()== menuBar.getQuiter()) {
				System.exit(0);
			}
			if (e.getSource()== menuBar.getInfoPatientItem()) {
				//show info patient card
				showInfoPatientCard();
				new SwingWorker() {

					@Override
					protected Object doInBackground() throws Exception {
						try{
							DefaultComboBoxModel dftb=patientQueries.getComboModel();
							infoPatient.getRechCombo().setModel(dftb);
						}catch(Exception ex){
							ex.printStackTrace();
						}
						return null;
					}
				}.execute();
				
				new SwingWorker() {

					@Override
					protected Object doInBackground() throws Exception {
						try{
							MedicamentQueries mq= new MedicamentQueries();
							List<String>med =mq.listOfMedsDb();
							DefaultComboBoxModel dcm=mq.comboBoxModel(med);
							ordonance.getNomMed().setModel(dcm);
						}catch(Exception ex){
							ex.printStackTrace();
						}						return null;
					}
				}.execute();
				
			}
			if (e.getSource()== menuBar.getRendezVousPatient()) {
				//show rendez vous card
				showRendezVousCard();
				new SwingWorker(){

					@Override
					protected Object doInBackground() throws Exception {
						DefaultComboBoxModel dftb=patientQueries.getComboModel();
						rendezVousView.getPatientCombo().setModel(dftb);
						return null;
					}
					
				}.execute();
				
			}
			
			if (e.getSource()== menuBar.getGestionStockItem()){
				//prepare gestionStockViiew Combobox with data
				new SwingWorker() {

					@Override
					protected Object doInBackground() throws Exception {
						DefaultComboBoxModel dftb=produitQueries.getComboModel();
						gestionStockView.getProduitCombo().setModel(dftb);	
						return null;
					}
				}.execute();;
				
				//show gestionStock card
				showGestionStockCard();
			}
			if (e.getSource()== menuBar.getDentisteInfoItem()) {
				//condition 
				DentisteQueries dentisteQueries= new DentisteQueries();
				if (!dentisteQueries.verifyEmpty()) {
					Dentiste dentiste = dentisteQueries.getdentiste();
					infoDentisteView.populateDentisteView(dentiste);
					infoDentisteView.setFieldsDisabled(false);
					infoDentisteView.getAjout().setEnabled(false);
				}
				showDentisteCard();
			}
			if (e.getSource()== menuBar.getStats()) {
				showStatsCard();
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
			
			if (e.getSource()== menuBar.getOrdonanceItem()){
				//show ordonance card
				showOrdonanceMenuViewCard();
				new SwingWorker() {

					@Override
					protected Object doInBackground() throws Exception {
						try{
							MedicamentQueries mq= new MedicamentQueries();
							List<String>med =mq.listOfMedsDb();
							DefaultComboBoxModel dcm=mq.comboBoxModel(med);
							ordonanceMenuView.getNomMed().setModel(dcm);
						}catch(Exception ex){
							ex.printStackTrace();
						}						return null;
					}
				}.execute();
				new SwingWorker() {

					@Override
					protected Object doInBackground() throws Exception {
						try{
							DefaultComboBoxModel dftb=patientQueries.getComboModel();
							ordonanceMenuView.getNomPrenomCombo().setModel(dftb);
						}catch(Exception ex){
							ex.printStackTrace();
						}						return null;
					}
				}.execute();
				


			}
			if (e.getSource()== menuBar.getExamenItem()) {
				//show examen card
				showExamenComplimentaireCard();
				new SwingWorker() {

					@Override
					protected Object doInBackground() throws Exception {
						try{
							DefaultComboBoxModel dftb=patientQueries.getComboModel();
							examenComplimentaireView.getNomPrenomCombo().setModel(dftb);
						}catch(Exception ex){
							ex.printStackTrace();
						}						
						return null;
					}
				}.execute();
				new SwingWorker() {

					@Override
					protected Object doInBackground() throws Exception {
						try{
							MedicamentQueries mq= new MedicamentQueries();
							List<String>med =mq.listOfMedsDb();
							DefaultComboBoxModel dcm=mq.comboBoxModel(med);
							examenComplimentaireView.getNomMed().setModel(dcm);
						}catch(Exception ex){
							ex.printStackTrace();
						}						
						return null;
					}
				}.execute();
				
				

			}
			if (e.getSource()== menuBar.getJustificationItem()) {
				//show justification card
				new SwingWorker() {

					@Override
					protected Object doInBackground() throws Exception {
						try{
							DefaultComboBoxModel dftb=patientQueries.getComboModel();
							justificationAbsenceView.getNomPatient().setModel(dftb);						
						}catch(Exception ex){
							ex.printStackTrace();
						}						return null;
					}
				}.execute();;
				
				showJustificationCard();
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
	public void showJustificationCard(){
		CardLayout cardLayout = (CardLayout) mainFrame.cards.getLayout();
		cardLayout.show(mainFrame.cards, "Card 5");	
	}
	public void showOrdonanceMenuViewCard(){
		CardLayout cardLayout = (CardLayout) mainFrame.cards.getLayout();
		cardLayout.show(mainFrame.cards, "Card 6");	
	}
	public void showExamenComplimentaireCard(){
		CardLayout cardLayout = (CardLayout) mainFrame.cards.getLayout();
		cardLayout.show(mainFrame.cards, "Card 7");	
	}
	public void showDentisteCard(){
		CardLayout cardLayout = (CardLayout) mainFrame.cards.getLayout();
		cardLayout.show(mainFrame.cards, "Card 8");	
	}
	public void showStatsCard(){
		CardLayout cardLayout = (CardLayout) mainFrame.cards.getLayout();
		cardLayout.show(mainFrame.cards, "Card 9");	
	}

}

