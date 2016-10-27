package org.hicham.View;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Patient extends JPanel{

	JButton btn = new JButton("btn");

	public Patient (){
		this.setLayout(null);
		this.setBackground(Color.white);
		this.add(btn);
		btn.setBounds(600,150 , 100,20 );
		this.setVisible(true);

	}
    public void addPatientActionListener(ActionListener listener){
    	this.btn.addActionListener(listener);
    }
	public JButton getBtn() {
		return btn;
	}
    

}
