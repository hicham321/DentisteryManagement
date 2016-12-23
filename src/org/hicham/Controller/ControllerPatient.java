package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


public class ControllerPatient {
	
	InfoPatient infoPatient= new InfoPatient();
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
	Ordonance ordonance= new Ordonance();
    RecherchePatientView recherchePatientView= new RecherchePatientView();
	
	PatientView patient= new PatientView(infoPatient,actPatient,odfPatient,ordonance,recherchePatientView);
    GestionStockView gestionStockView= new GestionStockView();
    RegisterView registerView= new RegisterView();
    ChangeMotPassView changeMotPassView= new ChangeMotPassView();

    HomePanel homePanel= new HomePanel(registerView,changeMotPassView);
    RendezVousView rendezVousView= new RendezVousView();
	MenuBar menuBar= new MenuBar();
	MainFrame mainFrame= new MainFrame(homePanel,patient,gestionStockView,rendezVousView,registerView,menuBar);

	public ControllerPatient(MainFrame mainFrame, PatientView patient ,Ordonance ordonance){
		
		
		
		this.patient= patient;
		this.mainFrame= mainFrame;		
		this.patient.addPatientActionListener(new PatientActionListener() );

	}

	class PatientActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

//				showOrdonanceCard();
//				System.out.println("this works for non connected view panels");
			
		}

	}
	public void showOrdonanceCard(){
		CardLayout cardLayout = (CardLayout) this.mainFrame.cards.getLayout();
		cardLayout.show(this.mainFrame.cards, "Card 2");	
	}

}
