package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.Model.PatientQueries;
import org.hicham.View.InfoPatient;

public class ControllerInfoPatient {
	
	InfoPatient infoPatient= new InfoPatient();
	PatientQueries patientQueries= new PatientQueries();
	
	public ControllerInfoPatient(InfoPatient infoPatient, PatientQueries patientQueries){
		this.infoPatient= infoPatient;
		this.patientQueries=patientQueries;
		this.infoPatient.addInfoPatientActionListener(new InfoPatientActionListener());
	}
	class InfoPatientActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==infoPatient.getOk()) {
				patientQueries.addPatient(infoPatient.getNom().getText()
						,infoPatient.getPrenom().getText()
						,Integer.parseInt(infoPatient.getAge().getText())
						,infoPatient.getAddress().getText()
						,Integer.parseInt(infoPatient.getTel().getText())
						,infoPatient.getTeinte().getSelectedItem().toString()
						,infoPatient.getSex().getSelectedItem().toString()
						,infoPatient.getAnticident().getText()
						,infoPatient.getFonction().getText());
			}
			if (e.getSource()== infoPatient.getAnnule()) {
				
			}
		}
		
	}

}
