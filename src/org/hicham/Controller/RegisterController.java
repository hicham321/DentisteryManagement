package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.Model.RegisterModel;
import org.hicham.View.RegisterView;

public class RegisterController {

	RegisterModel registerModel= new RegisterModel();
	RegisterView registerView= new RegisterView();

	public RegisterController(RegisterModel registerModel,RegisterView registerView ){
		this.registerModel= registerModel;
		this.registerView= registerView;
		this.registerView.addRegisterViewActionListener(new RegisterViewActionListener());
	}
	class RegisterViewActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
            if(arg0.getSource()== registerView.){
            	
            }            
		}

	}
}
