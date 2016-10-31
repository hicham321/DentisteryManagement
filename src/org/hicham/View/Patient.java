package org.hicham.View;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


public class Patient extends JPanel{
	
	

	JButton btn = new JButton("btn");
	JTabbedPane tabbedPane= new JTabbedPane();
	
	InfoPatient infoPatient= new InfoPatient();
	ActPatient actPatient= new ActPatient();
	OdfPatient odfPatient= new OdfPatient();


	public Patient (InfoPatient infoPatient,ActPatient actPatient, OdfPatient odfPatient){
		
		this.infoPatient= infoPatient;
		this.actPatient= actPatient;
		this.odfPatient= odfPatient;
		
		this.setLayout(null);
		this.setBackground(Color.white);
		
		
		
				
		this.add(tabbedPane);
		
		this.add(btn);
		
		
		tabbedPane.addTab("Info Panel       ", this.infoPatient);
		tabbedPane.addTab("act Panel      ", this.actPatient);
		tabbedPane.addTab("odf Panel      ", this.odfPatient);


		
		
		btn.setBounds(600,800 , 100,20 );
		tabbedPane.setBounds(0, 20, 700, 200);
		this.setVisible(true);

	}
	public void addPatientActionListener(ActionListener listener){
		this.btn.addActionListener(listener);
	}
	public JButton getBtn() {
		return btn;
	}


}
