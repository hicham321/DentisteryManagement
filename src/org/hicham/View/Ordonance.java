package org.hicham.View;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Ordonance extends JPanel {
	
	JButton btn = new JButton("dhfhff");
	
	public Ordonance() {
		this.setLayout(null);
		this.setBackground(Color.blue);
		this.add(btn);
		btn.setBounds(600,150 , 100,20 );
		this.setVisible(false);
	}
	
	public void addOrdonanceActionListener(ActionListener listener){
		
		this.btn.addActionListener(listener);
	}
	public JButton getBtn(){
		return this.btn;
	}
}
