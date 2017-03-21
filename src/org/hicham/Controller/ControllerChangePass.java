package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.hicham.Model.RegisterQueries;
import org.hicham.View.ChangeMotPassView;

public class ControllerChangePass {
	
	ChangeMotPassView changeMotPassView= new ChangeMotPassView();
	RegisterQueries registerQueries= new RegisterQueries();
	
	public ControllerChangePass(ChangeMotPassView changeMotPassView, RegisterQueries registerQueries){
		this.changeMotPassView= changeMotPassView;
		this.registerQueries= registerQueries;		
		this.changeMotPassView.addChangePassActionListener(new ChangePassActionListener());
	}
	
	class ChangePassActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource()== changeMotPassView.getOk()) {
				//insert new password in correct database record
				char[] oldpass= changeMotPassView.getOldPass().getPassword();
				char[] newpass= changeMotPassView.getNewPass().getPassword();
				//get user type based on selected one
				String userType="Dentiste";
				if (registerQueries.checkPassCorrect(oldpass, userType)) {
					registerQueries.changePassword(userType, oldpass, newpass);
					changeMotPassView.setVisible(false);
					changeMotPassView.getErrorLab().setVisible(false);
					resetPasswordFields();
				}
				else{
					//error message old pass is wrong
					changeMotPassView.getErrorLab().setVisible(true);
					//reset password fields
					resetPasswordFields();
				}

			}
			if (arg0.getSource()== changeMotPassView.getAnnuler()) {
				//close changepassview
				resetPasswordFields();
				changeMotPassView.getErrorLab().setVisible(false);
				changeMotPassView.setVisible(false);
			}
		}

		
	}
	public void resetPasswordFields(){
		changeMotPassView.getOldPass().setText("");
		changeMotPassView.getNewPass().setText("");

	}

}
