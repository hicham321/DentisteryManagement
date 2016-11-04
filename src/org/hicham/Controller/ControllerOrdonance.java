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

public class ControllerOrdonance {
	
	
	//If we don't need teh main frame or any of the other frames than we can just delete the corresponding fields
	InfoPatient infoPatient= new InfoPatient();
	ActPatient actPatient= new ActPatient();
	OdfPatient odfPatient= new OdfPatient();

	Ordonance ordonance= new Ordonance();
	Patient patient= new Patient(infoPatient,actPatient,odfPatient,ordonance);
	MenuBar menuBar= new MenuBar();
	MainFrame mainFrame= new MainFrame(patient,menuBar);
	public ControllerOrdonance(MainFrame mainFrame,Patient patient,Ordonance ordonance){
		this.patient= patient;
		this.ordonance= ordonance;
		this.mainFrame= mainFrame;
		this.ordonance.addOrdonanceActionListener(new OrdonanceActionListener());
	}

	class OrdonanceActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== ordonance.getBtn()){
				//showOrdonanceCard();
			}
		}

	}


}
