package org.hicham.View;

import java.awt.CardLayout;
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
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ExamenComplimentaireView extends JPanel{

	
	JLabel nomMedlab= new JLabel("Nom De Medicament: ");
	final DefaultComboBoxModel nomMedModel = new DefaultComboBoxModel();
	final JComboBox nomMed = new JComboBox(nomMedModel);    
	private JScrollPane nomMedListScrol = new JScrollPane(nomMed);
	
	JLabel situationLab= new JLabel("situation: ");
    JTextArea situation= new JTextArea();
    
    JTextArea medList= new JTextArea();
	private JScrollPane MedListScrol;

    
	JButton add = new JButton("Ajouter");
		
	JButton ok = new JButton("Genérer");
	
	JPanel ordonancePanel;
	
	public JPanel cards;
	
	JLabel nomPrenomlab= new JLabel("Nom Du Patient: ");
	final DefaultComboBoxModel nomPrenomModel = new DefaultComboBoxModel();
	final JComboBox nomPrenomCombo = new JComboBox(nomPrenomModel);    
	private JScrollPane nomPrenomListScrol = new JScrollPane(nomPrenomCombo);
	
	JLabel ageLab= new JLabel("Age Du Patient: ");
    JTextField ageText= new JTextField();
    
    JLabel nomLab= new JLabel("Nom Du Patient: ");
    JTextField nomText= new JTextField();
    
    JLabel prenomLab= new JLabel("Prenom Du Patient: ");
    JTextField prenomText= new JTextField();
	

	public ExamenComplimentaireView() {
		this.setLayout( null);
		this.setBackground(Color.WHITE);
		
		ordonancePanel= new JPanel();
		ordonancePanel.setBackground(Color.WHITE);
		ordonancePanel.setLayout(null);
		ordonancePanel.setBorder(BorderFactory.createTitledBorder("L'ordonance:"));
		
		this.nomMedModel.addElement("");
		AutoCompleteDecorator.decorate(nomMed);
		nomMed.setSelectedIndex(0);
		
		this.nomPrenomModel.addElement("");
		AutoCompleteDecorator.decorate(nomPrenomCombo);
		nomPrenomCombo.setSelectedIndex(0);

		
		medList= new JTextArea(5, 20);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		medList.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(0, 0, 10, 10)));
		situation.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(0, 0, 10, 10)));

		medList.setLineWrap(true);
		medList.setWrapStyleWord(true);
		MedListScrol= new JScrollPane(medList);
		
		cards = new JPanel(new CardLayout());

		cards.add(ordonancePanel, "Card 1");

		this.add(cards);
		
		this.add(nomMedlab);
		this.add(nomMed);
		this.add(situationLab);
		this.add(situation);
		this.add(add);
		this.add(ok);
		this.add(MedListScrol);
		
		this.add(nomPrenomlab);
		this.add(nomPrenomCombo);
		this.add(nomLab);
		this.add(nomText);
		this.add(prenomLab);
		this.add(prenomText);
		this.add(ageLab);
		this.add(ageText);


		

		
		nomMedlab.setBounds(30,240,120,20);
		nomMed.setBounds(170,240,270,20);
		situationLab.setBounds(30,290,120,20);
		situation.setBounds(170,290,270,20);
		add.setBounds(450,290,80,20);
		MedListScrol.setBounds(30,340,500,200);
        ok.setBounds(70,600,130,40);
        cards.setBounds(650, 15 ,500 , 600);
        
        nomPrenomlab.setBounds(30, 50,120 ,20);
        nomPrenomCombo.setBounds(170,50, 270,20);
        nomLab.setBounds(30,90,120,20);
        nomText.setBounds(170,90,270,20);
        prenomLab.setBounds(30,140,120,20);
        prenomText.setBounds(170,140,270,20);
        ageLab.setBounds(30,190,120,20);
        ageText.setBounds(170,190,270,20);

	}

	public void addExamenActionListener(ActionListener listener){

		this.ok.addActionListener(listener);
		this.nomMed.addActionListener(listener);
		this.add.addActionListener(listener);
		this.nomPrenomCombo.addActionListener(listener);
	}
	public void setOrdonanceEmpty(){
		
		this.nomMed.setSelectedIndex(0);
		this.situation.setText("");
		this.medList.setText("");
		this.ageText.setText("");
		this.prenomText.setText("");
		this.nomText.setText("");

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

	public JPanel getOrdonancePanel() {
		return ordonancePanel;
	}

	public JPanel getCards() {
		return cards;
	}

	public JComboBox getNomPrenomCombo() {
		return nomPrenomCombo;
	}

	public JTextField getAgeText() {
		return ageText;
	}

	public JTextField getNomText() {
		return nomText;
	}

	public JTextField getPrenomText() {
		return prenomText;
	}
	
	
    

}
