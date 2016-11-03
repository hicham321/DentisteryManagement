package org.hicham.View;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class Ordonance extends JPanel {
	
	JLabel nomMedlab= new JLabel("Nom De Medicament: ");
	final DefaultComboBoxModel nomMedModel = new DefaultComboBoxModel();
	final JComboBox nomMed = new JComboBox(nomMedModel);    
	private JScrollPane nomMedListScrol = new JScrollPane(nomMed);
	
	JLabel situationLab= new JLabel("situation: ");
    JTextField situation= new JTextField();
	
	
	JButton ok = new JButton("Ok");
	

	public Ordonance() {
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.blue);
		
		this.nomMedModel.addElement("");
		AutoCompleteDecorator.decorate(nomMed);
		nomMed.setSelectedIndex(0);
		
		this.add(ok);
		ok.setBounds(600,150 , 100,20 );
		this.setVisible(false);
	}

	public void addOrdonanceActionListener(ActionListener listener){

		this.ok.addActionListener(listener);
	}
	public JButton getBtn(){
		return this.ok;
	}
}
