package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.View.MainFrame;
import org.hicham.View.Ordonance;
import org.hicham.View.Patient;


public class ControllerPatient {

	Patient patient= new Patient();
	Ordonance ordonance= new Ordonance();
	MainFrame mainFrame= new MainFrame(patient,ordonance);

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
