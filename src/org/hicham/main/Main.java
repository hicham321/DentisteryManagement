package org.hicham.Main;

import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.hicham.Controller.ControllerAct;
import org.hicham.Controller.ControllerChangePass;
import org.hicham.Controller.ControllerGestionStock;
import org.hicham.Controller.ControllerInfoPatient;
import org.hicham.Controller.ControllerMenuBar;
import org.hicham.Controller.ControllerOrdonance;
import org.hicham.Controller.ControllerPatient;
import org.hicham.Controller.ControllerRegister;
import org.hicham.Controller.ControllerRendezVous;
import org.hicham.Model.ActQueries;
import org.hicham.Model.MedicamentQueries;
import org.hicham.Model.PatientQueries;
import org.hicham.Model.ProduitQueries;
import org.hicham.Model.RegisterQueries;
import org.hicham.Model.RendezVousQueries;
import org.hicham.Model.SessionsDB;
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

public class Main {

	public static void SystemLookFeel(){
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
		//views
		RegisterView rv= new RegisterView();
		ChangeMotPassView cmpv= new ChangeMotPassView();
		InfoPatient ip= new InfoPatient();
		ActPatientView ap= new ActPatientView();
		OdfPatient op= new OdfPatient();
		Ordonance o= new Ordonance();
		RecherchePatientView rp= new RecherchePatientView();
		PatientView p = new PatientView(ip,ap,op,o,rp);
		GestionStockView gs= new GestionStockView();
		MenuBar mb= new MenuBar();
		HomePanel hp= new HomePanel(rv,cmpv);
		RendezVousView rvv= new RendezVousView();
		MainFrame mf= new MainFrame(hp,p,gs,rvv,rv,mb);
		
		//controllers
		ControllerInfoPatient cip= new ControllerInfoPatient(ip, pq,rp,ap);
		ControllerAct ca= new ControllerAct(ap, aq, pq,cip);
		ControllerPatient cp= new ControllerPatient(mf, p,o);
		ControllerOrdonance co= new ControllerOrdonance(mf,hp, p, o,mq);
		ControllerMenuBar cmb= new ControllerMenuBar(mf,hp,mb, p,ip, o,gs,rvv,rp,prq,pq,cmpv);
		ControllerGestionStock cgs= new ControllerGestionStock(gs, prq);
		ControllerRendezVous crv= new ControllerRendezVous(rvv,rvq);
		ControllerRegister cr= new ControllerRegister(rq, rv, mf);
		ControllerChangePass ccmp= new ControllerChangePass(cmpv, rq);
		
		mf.setVisible(true);
	}

	public static void main(String[] args) {
		//see if register table is empty and insert the passwords if it is


		new Thread(new Runnable() {
			public void run() {
				SessionsDB FactoryObject= new SessionsDB();
				RegisterQueries rq= new RegisterQueries();
				if (rq.CheckRegisterEmpty()) {
					rq.putInitialPasswords();
				}
			}
		}).start();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SystemLookFeel();
				showInterface();
			}
		});

	}

}
