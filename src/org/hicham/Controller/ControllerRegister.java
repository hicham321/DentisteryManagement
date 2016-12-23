package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.Model.PatientQueries;
import org.hicham.Model.RegisterQueries;
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

public class ControllerRegister {
	MenuBar menuBar= new MenuBar();

	RegisterQueries registerModel= new RegisterQueries();
	RegisterView registerView= new RegisterView();
    ChangeMotPassView changeMotPassView= new ChangeMotPassView();
	
	Ordonance ordonance = new Ordonance();
	InfoPatient infoPatient= new InfoPatient();
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
    RecherchePatientView recherchePatientView= new RecherchePatientView();
    GestionStockView gestionStockView= new GestionStockView();

	RendezVousView rendezVousView= new RendezVousView();
	PatientView patient= new PatientView(infoPatient,actPatient,odfPatient,ordonance,recherchePatientView);
	PatientQueries patientQueries= new PatientQueries();
	public HomePanel homePanel= new HomePanel(registerView,changeMotPassView);
		
	
    
	MainFrame mainFrame= new MainFrame(homePanel,patient,gestionStockView,rendezVousView,registerView,menuBar);

	public ControllerRegister(RegisterQueries registerModel,RegisterView registerView, MainFrame mainFrame ){

		this.mainFrame= mainFrame;
		this.registerModel= registerModel;
		this.registerView= registerView;
		this.registerView.addRegisterViewActionListener(new RegisterViewActionListener());
	}
	class RegisterViewActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if(arg0.getSource()== registerView.getOk()){
				mainFrame.setEnabled(true);
			} 
			if(arg0.getSource()== registerView.getAnnule()){
				//get out of the program
				System.exit(0);
			}
		}

	}
}
