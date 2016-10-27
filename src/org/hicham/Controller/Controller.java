package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.View.MainFrame;
import org.hicham.View.Patient;


public class Controller {
	
	Patient patient= new Patient();
	
	public Controller(MainFrame mainFrame, Patient patient){
		
		this.patient= patient;
		
		this.patient.addPatientActionListener(new PatientActionListener() );
		
	}
	
	class PatientActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()== patient.getBtn()) {
                System.out.println("this works for non connected view panels");
			}			
		}

	}

}
