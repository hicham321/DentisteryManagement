package org.hicham.View;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ActPatientView extends JPanel {
	
	JLabel actLab= new JLabel("Act: ");
	JTextArea actText= new JTextArea();
	
	JLabel dateRendezVousLab= new JLabel("Rendez Vous: ");
	
	
	JLabel payementLab= new JLabel("Payement: ");
	final DefaultComboBoxModel payementModel = new DefaultComboBoxModel();
	final JComboBox payementCombo = new JComboBox(payementModel);    
	private JScrollPane payementListScrol = new JScrollPane(payementCombo);
	
	JButton ok= new JButton("ok");
	
    
	public ActPatientView(){
		JPanel panelAct= new JPanel();
		panelAct.setBackground(Color.cyan);
		panelAct.setLayout(null);
		panelAct.setBorder(BorderFactory.createTitledBorder("Act: "));



		this.add(panelAct);
	}
	
	public void addActPatientViewActionListener(ActionListener listener){
		this.ok.addActionListener(listener);
	}

	public JTextArea getActText() {
		return actText;
	}

	public JComboBox getPayementCombo() {
		return payementCombo;
	}

	public JButton getOk() {
		return ok;
	}
	
}
