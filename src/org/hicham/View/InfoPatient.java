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

public class InfoPatient extends JPanel{
	
    JLabel nomLab= new JLabel("Nom: ");
	JTextField nom= new JTextField(15);
    JLabel prenomLab= new JLabel("Prenom: ");
	JTextField prenom= new JTextField(15);
    JLabel ageLab= new JLabel("Age: ");
	JTextField age= new JTextField(15);
    JLabel addressLab= new JLabel("address: ");
	JTextField address= new JTextField(15);
    JLabel telLab= new JLabel("Tel: ");
	JTextField tel= new JTextField(15);

    JLabel teinteLab= new JLabel("Teinte: ");
	final DefaultComboBoxModel TeintModel = new DefaultComboBoxModel();
	final JComboBox teinte = new JComboBox(TeintModel);    
	private JScrollPane teinteListScrol = new JScrollPane(teinte);
	
	
    JLabel sexLab= new JLabel("Sex: ");
	final DefaultComboBoxModel sexmodel = new DefaultComboBoxModel();
	final JComboBox sex  = new JComboBox(sexmodel);    
	private JScrollPane sexListScrol = new JScrollPane(sex);

    JLabel anticidentLab= new JLabel("Anticident: ");
	JTextField anticident= new JTextField(15);
    JLabel fonctionLab= new JLabel("Fonction: ");
	JTextField fonction= new JTextField(15);

	JButton ok = new JButton("Ok");
	JButton modifie= new JButton("Modifie");


	public InfoPatient(){

		this.setLayout(new GridBagLayout());
		this.setBackground(Color.decode("#d2fdf9"));

		this.TeintModel.addElement("");
		AutoCompleteDecorator.decorate(teinte);
		teinte.setSelectedIndex(0);

		this.sexmodel.addElement("");
		this.sexmodel.addElement("Male");
		this.sexmodel.addElement("Femele");

		AutoCompleteDecorator.decorate(sex);
		sex.setSelectedIndex(0);
		
		
		GridBagConstraints c= new GridBagConstraints();
		c.anchor= GridBagConstraints.NORTHWEST;
		c.gridx=0;
		c.gridy=0;
	    this.add(nomLab,c);
	    c.gridy+=4;
	    this.add(prenomLab,c);
	    c.gridy+=4;
	    this.add(ageLab,c);
	    c.gridy+=4;
	    this.add(addressLab,c);
	    c.gridy+=4;
	    this.add(telLab,c);
	    c.gridy+=4;
	    this.add(sexLab,c);
	    c.gridy+=4;
	    this.add(teinteLab,c);
	    c.gridy+=4;
	    this.add(anticidentLab,c);
	    c.gridy+=4;
	    this.add(fonctionLab,c);
	    c.gridy+=4;
	    this.add(ok,c);
	    c.gridy+=4;

	    c.gridx=4;
		c.gridy=0;
		this.add(nom,c);
	    c.gridy+=4;
	    this.add(prenom,c);
	    c.gridy+=4;
	    this.add(age,c);
	    c.gridy+=4;
	    this.add(address,c);
	    c.gridy+=4;
	    this.add(tel,c);
	    c.gridy+=4;
	    this.add(sex,c);
	    c.gridy+=4;
	    this.add(teinte,c);
	    c.gridy+=4;
	    this.add(anticident,c);
	    c.gridy+=4;
	    this.add(fonction,c);
	    c.gridy+=4;
	    this.add(modifie,c);
	    c.gridy+=4;


		
	}

	public void addInfoPatientActionListener(ActionListener listener){

		this.ok.addActionListener(listener);
		this.modifie.addActionListener(listener);	

	}

	public JTextField getNom() {
		return nom;
	}

	public JTextField getPrenom() {
		return prenom;
	}

	public JTextField getAge() {
		return age;
	}

	public JTextField getAddress() {
		return address;
	}

	public JTextField getTel() {
		return tel;
	}

	public JComboBox getTeinte() {
		return teinte;
	}

	public JComboBox getSex() {
		return sex;
	}

	public JTextField getAnticident() {
		return anticident;
	}

	public JTextField getFonction() {
		return fonction;
	}

	public JButton getOk() {
		return ok;
	}

	public JButton getModifie() {
		return modifie;
	}
	
}
