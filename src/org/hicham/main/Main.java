package org.hicham.main;

import org.hicham.Controller.Controller;
import org.hicham.View.MainFrame;
import org.hicham.View.Patient;

public class Main {

	public static void main(String[] args) {
        Patient p = new Patient();  
        MainFrame mf= new MainFrame(p);
        Controller c= new Controller(mf, p);
        mf.setVisible(true);
		
	}

}
