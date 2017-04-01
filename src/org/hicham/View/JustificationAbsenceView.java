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

public class JustificationAbsenceView extends JPanel{
	
	JLabel nomPatientLab= new JLabel("Nom De Patient: ");
	final DefaultComboBoxModel nompatientModel = new DefaultComboBoxModel();
	final JComboBox nomPatient = new JComboBox(nompatientModel);    
	private JScrollPane nomPatientListScrol = new JScrollPane(nomPatient);
	
	JLabel entreNomLab= new JLabel("le nom: ");
	JTextField nomText= new JTextField();
	
	JLabel sexLab= new JLabel("sex: ");
	final DefaultComboBoxModel sexModel = new DefaultComboBoxModel();
	final JComboBox sex = new JComboBox(sexModel);    
	private JScrollPane sexscrol = new JScrollPane(sex);
	
	
	JLabel sujetLab= new JLabel("Le Sujet: ");
	JTextArea sujet;
	private JScrollPane sujetScrol;
	
    JPanel justificationPanel;
	
	public JPanel cards;

	JButton generer= new JButton("Generer");
	
	public JustificationAbsenceView(){
		
		this.setLayout( null);
		this.setBackground(Color.WHITE);
		
		justificationPanel= new JPanel();
		justificationPanel.setBackground(Color.WHITE);
		justificationPanel.setLayout(null);
		justificationPanel.setBorder(BorderFactory.createTitledBorder("L'ordonance:"));
		
		this.nompatientModel.addElement("");
		AutoCompleteDecorator.decorate(nomPatient);
		nomPatient.setSelectedIndex(0);
		
		this.sexModel.addElement("");
		this.sexModel.addElement("Male");
		this.sexModel.addElement("Femele");
		nomPatient.setSelectedIndex(0);
		
		sujet= new JTextArea(5, 20);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		sujet.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(0, 0, 10, 10)));

		sujet.setLineWrap(true);
		sujet.setWrapStyleWord(true);
		sujetScrol= new JScrollPane(sujet);

		
		cards = new JPanel(new CardLayout());

		cards.add(justificationPanel, "Card 1");

		this.add(cards);

		this.add(nomPatientLab);
		this.add(nomPatient);
		this.add(sex);
		this.add(entreNomLab);
		this.add(nomText);
		this.add(sexLab);
		this.add(sujetLab);
		this.add(sujetScrol);//
		this.add(generer);


		nomPatientLab.setBounds(30, 30, 120, 20);
		nomPatient.setBounds(170, 30, 400, 20);
		sex.setBounds(170, 110, 200, 20);
		entreNomLab.setBounds(30, 70, 120, 20);
		nomText.setBounds(170, 70, 400, 20);
		sexLab.setBounds(30, 110, 120, 20);
		sujetLab.setBounds(30, 150, 120, 20);
		sujetScrol.setBounds(170, 150, 400, 200);//
        cards.setBounds(650, 15,500 , 600);
        generer.setBounds(170,400 , 110, 40);
	}
	public void addJustificationActionListener(ActionListener listener){

		this.generer.addActionListener(listener);
		this.nomPatient.addActionListener(listener);
	}
	public void setFieldsEmpty(){
		this.sex.setSelectedIndex(0);
		this.nomText.setText("");
		this.sujet.setText("");
	}
	
	
	public JComboBox getNomPatient() {
		return nomPatient;
	}
	public JTextField getNomText() {
		return nomText;
	}
	public JComboBox getSex() {
		return sex;
	}
	public JTextArea getSujet() {
		return sujet;
	}
	public JPanel getJustificationPanel() {
		return justificationPanel;
	}
	public JPanel getCards() {
		return cards;
	}
	public JButton getGenerer() {
		return generer;
	}
	

     
}
