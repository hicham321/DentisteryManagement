package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.View.MainFrame;
import org.hicham.View.Ordonance;
import org.hicham.View.Patient;

public class ControllerOrdonance {

	Ordonance ordonance= new Ordonance();
	Patient patient= new Patient();
	MainFrame mainFrame= new MainFrame(patient,ordonance);
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
