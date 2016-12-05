package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.Model.ActQueries;
import org.hicham.View.ActPatientView;

public class ControllerAct {
	
	ActPatientView actPatientView= new ActPatientView();
	ActQueries actQueries= new ActQueries();
	
	public ControllerAct(ActPatientView actPatientView,ActQueries actQueries){
		this.actPatientView= actPatientView;
		this.actQueries= actQueries;
		this.actPatientView.addActPatientViewActionListener(new ActPatientViewActionListener() );
		
	}
   class ActPatientViewActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()== actPatientView.getOk()){
			String actText=actPatientView.getActText().toString();
			
		}
		
	}
	   
   }
}
