package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.View.ChangeMotPassView;

public class ControllerChangePass {
	
	ChangeMotPassView changeMotPassView= new ChangeMotPassView();
	
	public ControllerChangePass(ChangeMotPassView changeMotPassView){
		this.changeMotPassView= changeMotPassView;
		
		this.changeMotPassView.addChangePassActionListener(new ChangePassActionListener());
	}
	
	class ChangePassActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			 if (arg0.getSource()== changeMotPassView.getOk()) {
				//insert new password in correct database record
			}
			 if (arg0.getSource()== changeMotPassView.getAnnuler()) {
				//close changepassview
			}
		}

		
	}

}
