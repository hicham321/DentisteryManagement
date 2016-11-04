package org.hicham.Main;

import javax.swing.UIManager;

import org.hicham.Controller.ControllerMenuBar;
import org.hicham.Controller.ControllerOrdonance;
import org.hicham.Controller.ControllerPatient;
import org.hicham.View.ActPatient;
import org.hicham.View.InfoPatient;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.Patient;

public class Main {

	public static void SystemLookFeel(){
		try { 
			//This line is for system look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void showInterface(){
		InfoPatient ip= new InfoPatient();
		ActPatient ap= new ActPatient();
		OdfPatient op= new OdfPatient();
		Ordonance o= new Ordonance();
		Patient p = new Patient(ip,ap,op,o); 
		MenuBar mb= new MenuBar();
		MainFrame mf= new MainFrame(p,mb);
		ControllerPatient cp= new ControllerPatient(mf, p,o);
		ControllerOrdonance co= new ControllerOrdonance(mf, p, o);
		ControllerMenuBar cmb= new ControllerMenuBar(mf,mb , p, o);
		mf.setVisible(true);
	}

	public static void main(String[] args) {
		SystemLookFeel();
		showInterface();

	}

}
