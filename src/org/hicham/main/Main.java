package org.hicham.Main;

import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.hicham.Controller.ControllerAct;
import org.hicham.Controller.ControllerChangePass;
import org.hicham.Controller.ControllerExamenComplimentaire;
import org.hicham.Controller.ControllerGestionStock;
import org.hicham.Controller.ControllerInfoPatient;
import org.hicham.Controller.ControllerJustification;
import org.hicham.Controller.ControllerMenuBar;
import org.hicham.Controller.ControllerOdf;
import org.hicham.Controller.ControllerOrdonance;
import org.hicham.Controller.ControllerOrdonanceMenu;
import org.hicham.Controller.ControllerPatient;
import org.hicham.Controller.ControllerProthese;
import org.hicham.Controller.ControllerProtheseFixe;
import org.hicham.Controller.ControllerProthesePartielle;
import org.hicham.Controller.ControllerProtheseTotale;
import org.hicham.Controller.ControllerRegister;
import org.hicham.Controller.ControllerRendezVous;
import org.hicham.Model.ActQueries;
import org.hicham.Model.MedicamentQueries;
import org.hicham.Model.OdfQueries;
import org.hicham.Model.PatientQueries;
import org.hicham.Model.ProduitQueries;
import org.hicham.Model.ProtheseFixeQueries;
import org.hicham.Model.ProthesePartielleQueries;
import org.hicham.Model.ProtheseTotaleQueries;
import org.hicham.Model.RegisterQueries;
import org.hicham.Model.RendezVousQueries;
import org.hicham.Model.SessionsDB;
import org.hicham.View.ActPatientView;
import org.hicham.View.ChangeMotPassView;
import org.hicham.View.ExamenComplimentaireView;
import org.hicham.View.GestionStockView;
import org.hicham.View.HomePanel;
import org.hicham.View.InfoPatient;
import org.hicham.View.JustificationAbsenceView;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.OrdonanceMenuView;
import org.hicham.View.OrdonnanceReport;
import org.hicham.View.PatientView;
import org.hicham.View.ProtheseFixeView;
import org.hicham.View.ProthesePartielleView;
import org.hicham.View.ProtheseTotaleView;
import org.hicham.View.ProtheseView;
import org.hicham.View.RegisterView;
import org.hicham.View.RendezVousView;

public class Main {
	
	public static void systemLookFeel(){
		try { 
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void showInterface(){
		//models
		PatientQueries pq= new PatientQueries();
		ProduitQueries prq= new ProduitQueries();
		ActQueries aq= new ActQueries();
		MedicamentQueries mq= new MedicamentQueries();
		RendezVousQueries rvq= new RendezVousQueries();
		RegisterQueries rq= new RegisterQueries();
		OdfQueries oq= new OdfQueries();
		ProtheseFixeQueries prfq= new ProtheseFixeQueries();
		ProthesePartielleQueries prprq= new ProthesePartielleQueries();
		ProtheseTotaleQueries prtoq= new ProtheseTotaleQueries();
		//views
		RegisterView rv= new RegisterView();
		ChangeMotPassView cmpv= new ChangeMotPassView();
		InfoPatient ip= new InfoPatient();
		ActPatientView ap= new ActPatientView();
		OdfPatient op= new OdfPatient();
		ProtheseTotaleView prtv= new ProtheseTotaleView();
		ProthesePartielleView prpv= new ProthesePartielleView();
		ProtheseFixeView prfv= new ProtheseFixeView();
		ProtheseView prv= new ProtheseView(prtv,prpv,prfv);
		Ordonance o= new Ordonance();
		PatientView p = new PatientView(ip,ap,op,prv,o);
		GestionStockView gs= new GestionStockView();
		MenuBar mb= new MenuBar();
		HomePanel hp= new HomePanel(rv,cmpv);
		RendezVousView rvv= new RendezVousView();
		JustificationAbsenceView jav= new JustificationAbsenceView();
		OrdonanceMenuView ormv= new OrdonanceMenuView();
		ExamenComplimentaireView excv= new ExamenComplimentaireView();
		MainFrame mf= new MainFrame(hp,p,gs,rvv,jav,rv,ormv,excv,mb);
		//controllers
		ControllerInfoPatient cip= new ControllerInfoPatient(ip, pq,ap,op,prfv,prpv,prtv,o);
		ControllerAct ca= new ControllerAct(ap, aq,ip, cip);
		ControllerOdf codf= new ControllerOdf(op, oq,ip,cip);
		ControllerPatient cp= new ControllerPatient(mf, p,o);
		ControllerOrdonance co= new ControllerOrdonance(mf,hp, p, o,mq,cip);
		ControllerMenuBar cmb= new ControllerMenuBar(mf,hp,mb, p,ip, o,gs,rvv
				,prq,pq,cmpv,jav,ormv,excv);
		ControllerGestionStock cgs= new ControllerGestionStock(gs, prq);
		ControllerRendezVous crv= new ControllerRendezVous(rvv,rvq);
		ControllerRegister cr= new ControllerRegister(rq, rv, mf);
		ControllerChangePass ccmp= new ControllerChangePass(cmpv, rq);
		ControllerProthese crpr= new ControllerProthese(prv, prtv, prpv, prfv);
		ControllerProtheseFixe crprf= new ControllerProtheseFixe(prfv, prfq,ip,cip);
		ControllerProthesePartielle crprp= new ControllerProthesePartielle(prpv, prprq,ip,cip);
		ControllerProtheseTotale crprto= new ControllerProtheseTotale(prtv, prtoq,ip,cip);
		ControllerJustification cj= new ControllerJustification(jav);
		ControllerOrdonanceMenu corm= new ControllerOrdonanceMenu(ormv, pq);
		ControllerExamenComplimentaire cexc= new ControllerExamenComplimentaire(excv, pq);
		mf.setVisible(true);
	}

	public static void main(String[] args) {
		//see if register table is empty and insert the passwords if it is
		

		new Thread(new Runnable() {
			public void run() {
				SessionsDB FactoryObject= new SessionsDB();
				RegisterQueries rq= new RegisterQueries();
				MedicamentQueries mq= new MedicamentQueries();
				if (rq.CheckRegisterEmpty()) {
					rq.putInitialPasswords();
				}
				if (mq.CheckMedicamentEmpty()) {
					// batch inserts here
					try{
						List<String>med =mq.listOfMeds();
						mq.addBatchMedicament(med);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				else{
				
				}
			}
		}).start();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				systemLookFeel();
				showInterface();
			}
		});

	}

}
