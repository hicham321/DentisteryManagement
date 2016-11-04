package org.hicham.View;

import java.awt.Color;
import java.awt.GridBagConstraints;
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
		this.setBackground(Color.WHITE);
		
		this.nomMedModel.addElement("");
		AutoCompleteDecorator.decorate(nomMed);
		nomMed.setSelectedIndex(0);
		
		GridBagConstraints c= new GridBagConstraints();
		c.anchor= GridBagConstraints.LINE_END;
        c.gridx=0;
        c.gridy=0;
        this.add(nomMedlab,c);
        c.gridy=+2;
        this.add(situationLab, c);
        c.gridy=0;
        c.gridx=1;
        this.add(nomMed, c);
        c.gridy=+2;
        this.add(situation, c);
        c.gridy=+2;
        this.add(ok, c);
		this.setVisible(false);
	}

	public void addOrdonanceActionListener(ActionListener listener){

		this.ok.addActionListener(listener);
	}
	public JButton getBtn(){
		return this.ok;
	}
}
