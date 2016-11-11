package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import org.hicham.Model.PatientQueries;
import org.hicham.View.InfoPatient;
import org.hicham.View.RecherchePatientView;

public class ControllerInfoPatient {
	
	InfoPatient infoPatient= new InfoPatient();
	PatientQueries patientQueries= new PatientQueries();
	RecherchePatientView recherchePatientView= new RecherchePatientView();
	
	public ControllerInfoPatient(InfoPatient infoPatient, PatientQueries patientQueries ,RecherchePatientView recherchePatientView){
		this.infoPatient= infoPatient;
		this.patientQueries=patientQueries;
		this.recherchePatientView=recherchePatientView;
		this.infoPatient.addInfoPatientActionListener(new InfoPatientActionListener());
	}
	class InfoPatientActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==infoPatient.getOk()) {
				
				DefaultComboBoxModel dftb=patientQueries.getComboModel();
				recherchePatientView.getRech().setModel(dftb);
				
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
			if (e.getSource()== infoPatient.getModifie()) {
				
			}
		}
		
	}

}
