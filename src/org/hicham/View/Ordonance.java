package org.hicham.View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class Ordonance extends JPanel {
	
	
	JLabel nomEtPrenomLab= new JLabel("Nom et Prenom: ");
	JLabel nomEtPrenom= new JLabel("");

	
	JLabel nomMedlab= new JLabel("Nom De Medicament: ");
	final DefaultComboBoxModel nomMedModel = new DefaultComboBoxModel();
	final JComboBox nomMed = new JComboBox(nomMedModel);    
	private JScrollPane nomMedListScrol = new JScrollPane(nomMed);
	
	JLabel situationLab= new JLabel("situation: ");
    JTextArea situation= new JTextArea();
    
    JTextArea medList= new JTextArea();
	private JScrollPane medListScrol;

    
	JButton add = new JButton("Ajouter");
		
	JButton ok = new JButton("Genérer");
	
	JPanel ordonancePanel;
	
	public JPanel cards;

    
	public Ordonance() {
		this.setLayout( null);
		this.setBackground(Color.WHITE);
		
		ordonancePanel= new JPanel();
		ordonancePanel.setBackground(Color.WHITE);
		ordonancePanel.setLayout(null);
		ordonancePanel.setBorder(BorderFactory.createTitledBorder("L'ordonance:"));
		
		this.nomMedModel.addElement("");
		AutoCompleteDecorator.decorate(nomMed);
		nomMed.setSelectedIndex(0);
		
		medList= new JTextArea(5, 20);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		medList.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(0, 0, 10, 10)));

		medList.setLineWrap(true);
		medList.setWrapStyleWord(true);
		medListScrol= new JScrollPane(medList);
		
		
		cards = new JPanel(new CardLayout());

		cards.add(ordonancePanel, "Card 1");

		this.add(cards);
		
		this.add(nomMedlab);
		this.add(nomMed);
		this.add(situationLab);
		this.add(situation);
		this.add(add);
		this.add(ok);
		this.add(medListScrol);
		this.add(nomEtPrenomLab);
		this.add(nomEtPrenom);
		
		situation.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(0, 0, 10, 10)));

		
		nomEtPrenomLab.setBounds(30, 50,120 ,20 );
		nomEtPrenom.setBounds(150,50 , 120,20 );
		nomMedlab.setBounds(30, 110, 120, 20);
		nomMed.setBounds(150, 110, 270, 20);
		situationLab.setBounds(30, 150, 100, 20);
		situation.setBounds(150, 150, 270, 20);
		add.setBounds(450, 150, 80, 20);
		medListScrol.setBounds(30, 190, 500, 200);
        ok.setBounds(70,450, 130, 40);
        cards.setBounds(650, 15 ,500 , 600);

		

	}

	public void addOrdonanceActionListener(ActionListener listener){

		this.ok.addActionListener(listener);
		this.nomMed.addActionListener(listener);
		this.add.addActionListener(listener);
	}

	public JComboBox getNomMed() {
		return nomMed;
	}

	public JTextArea getSituation() {
		return situation;
	}

	public JButton getOk() {
		return ok;
	}

	public JTextArea getMedList() {
		return medList;
	}

	public JButton getAdd() {
		return add;
	}

	public JLabel getNomEtPrenom() {
		return nomEtPrenom;
	}

	public JPanel getOrdonancePanel() {
		return ordonancePanel;
	}

	public JPanel getCards() {
		return cards;
	}
	
    
	
}
