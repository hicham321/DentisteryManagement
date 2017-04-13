package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


public class ControllerPatient {
	
	InfoPatient infoPatient= new InfoPatient();
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	ProtheseView protheseView= new ProtheseView(protheseTotaleView,prothesePartielleView
			,protheseFixeView);	Ordonance ordonance= new Ordonance();
	
	PatientView patient= new PatientView(infoPatient,actPatient
			,odfPatient,protheseView,ordonance);
    GestionStockView gestionStockView= new GestionStockView();
    RegisterView registerView= new RegisterView();
    ChangeMotPassView changeMotPassView= new ChangeMotPassView();

    HomePanel homePanel= new HomePanel(registerView,changeMotPassView);
    RendezVousView rendezVousView= new RendezVousView();
	MenuBar menuBar= new MenuBar();
	JustificationAbsenceView justificationAbsenceView= new JustificationAbsenceView();
    public OrdonanceMenuView ordonanceMenuView= new OrdonanceMenuView();
    ExamenComplimentaireView examenComplimentaireView= new ExamenComplimentaireView();
	InfoDentisteView infoDentisteView= new InfoDentisteView();
    StatisticsView statisticsView= new StatisticsView();


	MainFrame mainFrame= new MainFrame(homePanel,patient,gestionStockView,rendezVousView
			,justificationAbsenceView,registerView,ordonanceMenuView
			,examenComplimentaireView,infoDentisteView,statisticsView,menuBar);

	public ControllerPatient(MainFrame mainFrame, PatientView patient ,Ordonance ordonance){
		
		
		
		this.patient= patient;
		this.mainFrame= mainFrame;		
		this.patient.addPatientActionListener(new PatientActionListener() );

	}

	class PatientActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

//				showOrdonanceCard();
//				System.out.println("this works for non connected view panels");
			
		}

	}
	public void showOrdonanceCard(){
		CardLayout cardLayout = (CardLayout) this.mainFrame.cards.getLayout();
		cardLayout.show(this.mainFrame.cards, "Card 2");	
	}

}
