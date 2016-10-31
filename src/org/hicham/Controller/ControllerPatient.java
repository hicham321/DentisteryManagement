package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.View.ActPatient;
import org.hicham.View.InfoPatient;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.Patient;


public class ControllerPatient {
	InfoPatient infoPatient= new InfoPatient();
	ActPatient actPatient= new ActPatient();
	OdfPatient odfPatient= new OdfPatient();
	Patient patient= new Patient(infoPatient,actPatient,odfPatient);
	Ordonance ordonance= new Ordonance();
	MenuBar menuBar= new MenuBar();
	MainFrame mainFrame= new MainFrame(patient,ordonance,menuBar);

	public ControllerPatient(MainFrame mainFrame, Patient patient ,Ordonance ordonance){
		
		
		
		this.patient= patient;
		this.ordonance= ordonance;
		this.mainFrame= mainFrame;		
		this.patient.addPatientActionListener(new PatientActionListener() );

	}

	class PatientActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource()== patient.getBtn()) {
				showOrdonanceCard();
				System.out.println("this works for non connected view panels");
			}			
		}

	}
	public void showOrdonanceCard(){
		CardLayout cardLayout = (CardLayout) this.mainFrame.cards.getLayout();
		cardLayout.show(this.mainFrame.cards, "Card 2");	
	}

}
