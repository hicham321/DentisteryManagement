package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.accessibility.AccessibleTableModelChange;

import org.hicham.Model.Act;
import org.hicham.Model.ActQueries;
import org.hicham.Model.Patient;
import org.hicham.Model.PatientQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.InfoPatient;
import org.hicham.View.RecherchePatientView;

public class ControllerAct {

	ActPatientView actPatientView= new ActPatientView();
	ActQueries actQueries        = new     ActQueries();
	PatientQueries patientQueries= new PatientQueries();
	
	
	InfoPatient infoPatient = new InfoPatient();
	RecherchePatientView recherchePatientView = new RecherchePatientView();
	
	ControllerInfoPatient controllerInfoPatient= new ControllerInfoPatient(infoPatient,patientQueries,recherchePatientView);
	
	Act currentAct= new Act();
    
	public ControllerAct(ActPatientView actPatientView,ActQueries actQueries,PatientQueries patientQueries ,ControllerInfoPatient controllerInfoPatient){
		
		this.actPatientView= actPatientView;
		this.actQueries= actQueries;
		this.patientQueries=patientQueries;
		this.controllerInfoPatient= controllerInfoPatient;
		this.actPatientView.addActPatientViewActionListener(new ActPatientViewActionListener() );

	}
	class ActPatientViewActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if(arg0.getSource()==actPatientView.getOk()){
				
				//put act information
				String actText=actPatientView.getActText().getText();
				Date actDate=actPatientView.getDatePicker().getDate();
				String actTemp= actPatientView.getTimePicker().getValue().toString();
			    double actPayement= Double.parseDouble(actPatientView.getPayementCombo().getSelectedItem().toString());
			    currentAct= new Act(actText,actPayement,actDate,actTemp);
			    //setting patient for oneToMany relationship between Patient and Act
			    currentAct.setPatient(controllerInfoPatient.getCurrentPatient());
			    actQueries.addAct(currentAct);
				
				
			}

		}

	}
}
