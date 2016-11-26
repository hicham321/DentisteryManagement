package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.View.ActPatientView;
import org.hicham.View.GestionStockView;
import org.hicham.View.InfoPatient;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.Patient;
import org.hicham.View.RecherchePatientView;


public class ControllerPatient {
	
	InfoPatient infoPatient= new InfoPatient();
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
	Ordonance ordonance= new Ordonance();
    RecherchePatientView recherchePatientView= new RecherchePatientView();
	
	Patient patient= new Patient(infoPatient,actPatient,odfPatient,ordonance,recherchePatientView);
    GestionStockView gestionStockView= new GestionStockView();
	MenuBar menuBar= new MenuBar();
	MainFrame mainFrame= new MainFrame(patient,gestionStockView,menuBar);

	public ControllerPatient(MainFrame mainFrame, Patient patient ,Ordonance ordonance){
		
		
		
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
