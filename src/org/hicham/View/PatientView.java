package org.hicham.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


public class PatientView extends JPanel{
	
	

	JTabbedPane tabbedPane= new JTabbedPane();
	
	InfoPatient infoPatient= new InfoPatient();
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	ProtheseView protheseView= new ProtheseView(protheseTotaleView,prothesePartielleView
			,protheseFixeView);   
	Ordonance ordonance= new Ordonance();
    
	public PatientView (InfoPatient infoPatient,ActPatientView actPatient
			,OdfPatient odfPatient,ProtheseView protheseView, Ordonance ordonance
			){
		
		this.infoPatient= infoPatient;
		this.actPatient= actPatient;
		this.odfPatient= odfPatient;
		this.protheseView= protheseView;
		this.ordonance= ordonance;
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
				
		this.add(tabbedPane);
				
		tabbedPane.addTab("Info       ", this.infoPatient);
		tabbedPane.addTab("Act      ", this.actPatient);
		tabbedPane.addTab("ODF       ", this.odfPatient);
		tabbedPane.addTab("Prothese       ", this.protheseView);
		tabbedPane.addTab("Ordonance       ", this.ordonance);
	

	}
	public void addPatientActionListener(ActionListener listener){
	}
	

}
