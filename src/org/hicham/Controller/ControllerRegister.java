package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.hicham.Model.PatientQueries;
import org.hicham.Model.RegisterQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.ChangeMotPassView;
import org.hicham.View.ExamenComplimentaireView;
import org.hicham.View.GestionStockView;
import org.hicham.View.HomePanel;
import org.hicham.View.InfoDentisteView;
import org.hicham.View.InfoPatient;
import org.hicham.View.JustificationAbsenceView;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.OrdonanceMenuView;
import org.hicham.View.PatientView;
import org.hicham.View.ProtheseFixeView;
import org.hicham.View.ProthesePartielleView;
import org.hicham.View.ProtheseTotaleView;
import org.hicham.View.ProtheseView;
import org.hicham.View.RegisterView;
import org.hicham.View.RendezVousView;
import org.hicham.View.StatisticsView;

public class ControllerRegister {
	MenuBar menuBar= new MenuBar();

	RegisterQueries registerQueries= new RegisterQueries();
	RegisterView registerView= new RegisterView();
	ChangeMotPassView changeMotPassView= new ChangeMotPassView();

	Ordonance ordonance = new Ordonance();
	InfoPatient infoPatient= new InfoPatient();
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	ProtheseView protheseView= new ProtheseView(protheseTotaleView,prothesePartielleView
			,protheseFixeView);
	GestionStockView gestionStockView= new GestionStockView();

	RendezVousView rendezVousView= new RendezVousView();
	PatientView patient= new PatientView(infoPatient,actPatient,odfPatient
			,protheseView,ordonance);
	PatientQueries patientQueries= new PatientQueries();
	public HomePanel homePanel= new HomePanel(registerView,changeMotPassView);
	JustificationAbsenceView justificationAbsenceView= new JustificationAbsenceView();
    public OrdonanceMenuView ordonanceMenuView= new OrdonanceMenuView();
    ExamenComplimentaireView examenComplimentaireView= new ExamenComplimentaireView();
	InfoDentisteView infoDentisteView= new InfoDentisteView();
    StatisticsView statisticsView= new StatisticsView();

	MainFrame mainFrame= new MainFrame(homePanel,patient,gestionStockView,rendezVousView
			,justificationAbsenceView,registerView,ordonanceMenuView
			,examenComplimentaireView,infoDentisteView,statisticsView,menuBar);

	public ControllerRegister(RegisterQueries registerModel,RegisterView registerView, MainFrame mainFrame ){

		this.mainFrame= mainFrame;
		this.registerQueries= registerModel;
		this.registerView= registerView;
		this.registerView.addRegisterViewActionListener(new RegisterViewActionListener());
	}
	class RegisterViewActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if(arg0.getSource()== registerView.getOk()){				
				//check if password is correct depending on user type:
				char[] password= registerView.getPassword().getPassword();
				String typeUser= registerView.getTypeUserCombo().getSelectedItem().toString();
				
				if (registerQueries.checkPassCorrect(password, typeUser)) {
					mainFrame.enableFrameComponents();
					registerView.dispose();
				}
				else{
					//error message 
					registerView.getErrorLab().setVisible(true);
				}

			} 
			if(arg0.getSource()== registerView.getAnnule()){
				System.exit(0);
			}
		}

	}
}
