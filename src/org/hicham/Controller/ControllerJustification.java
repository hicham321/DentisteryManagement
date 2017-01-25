package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.MarshalledObject;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationID;
import java.rmi.activation.ActivationInstantiator;
import java.util.List;

import org.hicham.Model.Patient;
import org.hicham.Model.PatientQueries;
import org.hicham.View.JustificationAbsenceView;

public class ControllerJustification {


	PatientQueries patientQueries= new PatientQueries();
	JustificationAbsenceView justificationAbsenceView= new JustificationAbsenceView();

	public ControllerJustification(JustificationAbsenceView justificationAbsenceView){


		this.justificationAbsenceView=justificationAbsenceView;
		this.justificationAbsenceView.addJustificationActionListener(
				new JustificationActionListener());
	}
	class JustificationActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource()== justificationAbsenceView.getGenerer()) {
				

			}
			if (e.getSource()== justificationAbsenceView.getNomPatient()) {
				int selecteditem=justificationAbsenceView.getNomPatient().getSelectedIndex();
				List<Patient>patients=patientQueries.findAllPatients();
				Patient selectedPatient=patients.get(selecteditem);
			}

		}

	}

}
