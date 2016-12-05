package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.Model.RegisterQueries;
import org.hicham.View.RegisterView;

public class RegisterController {

	RegisterQueries registerModel= new RegisterQueries();
	RegisterView registerView= new RegisterView();

	public RegisterController(RegisterQueries registerModel,RegisterView registerView ){
		this.registerModel= registerModel;
		this.registerView= registerView;
		this.registerView.addRegisterViewActionListener(new RegisterViewActionListener());
	}
	class RegisterViewActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(arg0.getSource()== registerView.getOk()){

			} 
			if(arg0.getSource()== registerView.getAnnule()){

			}
		}

	}
}
