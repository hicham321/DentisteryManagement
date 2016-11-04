package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.View.ActPatient;
import org.hicham.View.InfoPatient;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.Patient;

public class ControllerMenuBar {

	MenuBar menuBar= new MenuBar();

	Ordonance ordonance = new Ordonance();
	InfoPatient infoPatient= new InfoPatient();
	ActPatient actPatient= new ActPatient();
	OdfPatient odfPatient= new OdfPatient();

	Patient patient= new Patient(infoPatient,actPatient,odfPatient,ordonance);


	MainFrame mainFrame= new MainFrame(patient,menuBar);

	public ControllerMenuBar(MainFrame mainFrame,MenuBar menuBar,Patient patient,Ordonance ordonance){

		this.patient= patient;
		this.ordonance= ordonance;
		this.menuBar= menuBar;
		this.mainFrame= mainFrame;
		this.menuBar.addMenuBarActionListener(new MenuBarActionListener() );

	}
	class MenuBarActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource()== menuBar.getOrdonance()) {
				//show ordonance card
			}
			if (e.getSource()== menuBar.getPatient()) {
				//show patient card

			}
			if (e.getSource()== menuBar.getLabo()) {
				//show labo card

			}
			if (e.getSource()== menuBar.getGestionStock()) {
				//show gestionStock card

			}
			if (e.getSource()== menuBar.getRetour()) {
				//show first card

			}
			if (e.getSource()== menuBar.getMotpasse()) {
				//show motpasse frame
				System.out.println("it works");

			}
			if (e.getSource()== menuBar.getMenuapropos()) {
				//show apropos frame

			}
			if (e.getSource()== menuBar.getAjoutbase()) {
				//show Jfileschooser card

			}
		}
	}


}
