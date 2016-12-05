package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.hicham.Model.MedicamentQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.GestionStockView;
import org.hicham.View.InfoPatient;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.Patient;
import org.hicham.View.RecherchePatientView;

public class ControllerOrdonance {
	
	
	//If we don't need teh main frame or any of the other frames than we can just delete the corresponding fields
	InfoPatient infoPatient= new InfoPatient();
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
	Ordonance ordonance= new Ordonance();
    RecherchePatientView recherchePatientView= new RecherchePatientView();
	
	Patient patient= new Patient(infoPatient,actPatient,odfPatient,ordonance,recherchePatientView);
	MenuBar menuBar= new MenuBar();
    GestionStockView gestionStockView= new GestionStockView();
    MedicamentQueries medicamentQueries= new MedicamentQueries();

	MainFrame mainFrame= new MainFrame(patient,gestionStockView,menuBar);
	public ControllerOrdonance(MainFrame mainFrame,Patient patient,Ordonance ordonance ,MedicamentQueries medicamentQueries){
		this.patient= patient;
		this.ordonance= ordonance;
		this.mainFrame= mainFrame;
		this.medicamentQueries= medicamentQueries;
		this.ordonance.addOrdonanceActionListener(new OrdonanceActionListener());
	}

	class OrdonanceActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== ordonance.getOk()){
				//code for showing ordonance
				//temporarily for adding records to the database
				try{
					
				List<String>med =medicamentQueries.listOfMeds();
				medicamentQueries.addBatchMedicament(med);
				DefaultComboBoxModel dcm=medicamentQueries.comboBoxModel(med);
				ordonance.getNomMed().setModel(dcm);
				
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}

	}


}
