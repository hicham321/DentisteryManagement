package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.hicham.Model.MedicamentQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.ChangeMotPassView;
import org.hicham.View.GestionStockView;
import org.hicham.View.HomePanel;
import org.hicham.View.InfoPatient;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.PatientView;
import org.hicham.View.RecherchePatientView;
import org.hicham.View.RegisterView;
import org.hicham.View.RendezVousView;

public class ControllerOrdonance {
	
	
	//If we don't need the main frame or any of the other frames than we can just delete the corresponding fields
	InfoPatient infoPatient= new InfoPatient();
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
	Ordonance ordonance= new Ordonance();
    RecherchePatientView recherchePatientView= new RecherchePatientView();
	
	PatientView patient= new PatientView(infoPatient,actPatient,odfPatient,ordonance,recherchePatientView);
	MenuBar menuBar= new MenuBar();
    GestionStockView gestionStockView= new GestionStockView();
    MedicamentQueries medicamentQueries= new MedicamentQueries();
    RegisterView registerView= new RegisterView();
    ChangeMotPassView changeMotPassView= new ChangeMotPassView();

    HomePanel homePanel= new HomePanel(registerView,changeMotPassView);
    RendezVousView rendezVousView= new RendezVousView();
	MainFrame mainFrame= new MainFrame( homePanel,patient,gestionStockView,rendezVousView,registerView,menuBar);
	public ControllerOrdonance(MainFrame mainFrame,HomePanel homePanel,PatientView patient,Ordonance ordonance ,MedicamentQueries medicamentQueries){
		this.patient= patient;
		this.ordonance= ordonance;
		this.homePanel=homePanel;
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
