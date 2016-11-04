package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.View.InfoPatient;

public class ControllerInfoPatient {
	
	InfoPatient infoPatient= new InfoPatient();
	public ControllerInfoPatient(InfoPatient infoPatient){
		this.infoPatient= infoPatient;
		
		this.infoPatient.addInfoPatientActionListener(new InfoPatientActionListener());
	}
	class InfoPatientActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==infoPatient.getOk()) {
				
			}
			if (e.getSource()== infoPatient.getAnnule()) {
				
			}
		}
		
	}

}
