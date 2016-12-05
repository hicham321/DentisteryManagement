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
		this.setLayout( null);
		this.setBackground(Color.decode("#d2fdf9"));
		
		this.nomMedModel.addElement("");
		AutoCompleteDecorator.decorate(nomMed);
		nomMed.setSelectedIndex(0);
		
		this.add(nomMedlab);
		this.add(nomMed);
		this.add(situationLab);
		this.add(situation);
		this.add(ok);
		
		nomMedlab.setBounds(30, 50, 120, 20);
		nomMed.setBounds(150, 50, 200, 20);
		situationLab.setBounds(30, 90, 100, 20);
		situation.setBounds(150, 90, 200, 20);
        ok.setBounds(70,140, 200, 20);

		
		
		/*GridBagConstraints c= new GridBagConstraints();
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
        c.gridy=+2;
       */

	}

	public void addOrdonanceActionListener(ActionListener listener){

		this.ok.addActionListener(listener);
		this.nomMed.addActionListener(listener);
	}

	public JComboBox getNomMed() {
		return nomMed;
	}

	public JTextField getSituation() {
		return situation;
	}

	public JButton getOk() {
		return ok;
	}
	
}
