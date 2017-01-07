package org.hicham.View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class InfoPatient extends JPanel{
	
    JLabel nomLab= new JLabel("Nom: ");
	JTextField nom= new JTextField();
    JLabel prenomLab= new JLabel("Prenom: ");
	JTextField prenom= new JTextField();
    JLabel ageLab= new JLabel("Age: ");
	JFormattedTextField age;
    JLabel addressLab= new JLabel("address: ");
	JTextField address= new JTextField();
    JLabel telLab= new JLabel("Tel: ");
	JFormattedTextField tel;

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

    JLabel errorLabNom= new JLabel("*");
    JLabel errorLabPrenom= new JLabel("*");
    JLabel errorLabAge= new JLabel("*");
    JLabel errorLabAddress= new JLabel("*");
    JLabel errorLabTel= new JLabel("*");
    JLabel errorLabTeinte= new JLabel("*");
    JLabel errorLabSex= new JLabel("*");
    JLabel errorLabAnticident= new JLabel("*");
    JLabel errorLabFonction= new JLabel("*");
    
    JLabel patientRechLab= new JLabel("Recherche Patient: ");
	final DefaultComboBoxModel rechModel = new DefaultComboBoxModel();
	final JComboBox rechCombo = new JComboBox(rechModel);    
	private JScrollPane rechListScrol = new JScrollPane(rechCombo);
	
	JButton nouveauPatient= new JButton("Nouveau");
	
	JButton suppPatient= new JButton("Supprimer");	
	

	public InfoPatient(){
        this.setLayout(null);
		this.setBackground(Color.decode("#d2fdf9"));

		this.TeintModel.addElement("");
		AutoCompleteDecorator.decorate(teinte);
		teinte.setSelectedIndex(0);

		this.sexmodel.addElement("");
		this.sexmodel.addElement("Male");
		this.sexmodel.addElement("Femele");

		AutoCompleteDecorator.decorate(sex);
		sex.setSelectedIndex(0);
		
		this.add(nomLab);
		this.add(nom);
		this.add(prenomLab);
		this.add(prenom);
		this.add(ageLab);
        MaskFormatter maskAge = null;
		
        try {
            //
            // Create a MaskFormatter for accepting phone number, the # symbol accept
            // only a number. We can also set the empty value with a place holder
            // character.
            //
        	maskAge = new MaskFormatter("##");
        	maskAge.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        age= new JFormattedTextField(maskAge);
		this.add(age);
		this.add(addressLab);
		this.add(address);
		this.add(telLab);
		MaskFormatter maskTelphone = null;
		
        try {
            //
            // Create a MaskFormatter for accepting phone number, the # symbol accept
            // only a number. We can also set the empty value with a place holder
            // character.
            //
            maskTelphone = new MaskFormatter("# ## ## ## ##");
            maskTelphone.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tel= new JFormattedTextField(maskTelphone);
		this.add(tel);
		this.add(teinteLab);
		this.add(teinte);
		this.add(sexLab);
		this.add(sex);
		this.add(anticidentLab);
		this.add(anticident);
		this.add(fonctionLab);
		this.add(fonction);
		this.add(ok);
		this.add(modifie);
		
		this.add(patientRechLab);
		this.add(rechCombo);
		this.add(nouveauPatient);
		
		this.add(suppPatient);
		
		
		this.add(errorLabNom);
		errorLabNom.setForeground(Color.red);
		errorLabNom.setVisible(false);
		
		this.add(errorLabPrenom);
		errorLabPrenom.setForeground(Color.red);
		errorLabPrenom.setVisible(false);
		
		this.add(errorLabAge);
		errorLabAge.setForeground(Color.red);
		errorLabAge.setVisible(false);
		
		this.add(errorLabAddress);
		errorLabAddress.setForeground(Color.red);
		errorLabAddress.setVisible(false);
		
		this.add(errorLabTel);
		errorLabTel.setForeground(Color.red);
		errorLabTel.setVisible(false);
		
		this.add(errorLabTeinte);
		errorLabTeinte.setForeground(Color.red);
		errorLabTeinte.setVisible(false);
		
		this.add(errorLabSex);
		errorLabSex.setForeground(Color.red);
		errorLabSex.setVisible(false);
		
		this.add(errorLabAnticident);
		errorLabAnticident.setForeground(Color.red);
		errorLabAnticident.setVisible(false);
		
		this.add(errorLabFonction);
		errorLabFonction.setForeground(Color.red);
		errorLabFonction.setVisible(false);
		
		

		//setting position of components:
        this.nomLab.setBounds(100,100 , 100, 20);
        
        
        this.errorLabNom.setBounds(450, 100, 250, 20);
        this.nom.setBounds(200, 100, 250, 20);
        this.prenomLab.setBounds(100, 140, 100, 20);
        
        
        this.errorLabPrenom.setBounds(450, 140, 250, 20);
        this.prenom.setBounds(200,140 ,250, 20);
        this.ageLab.setBounds(100,180 ,100, 20);
        
        this.errorLabAge.setBounds(450, 180, 250, 20);
        this.age.setBounds(200,180 ,250, 20);
        this.addressLab.setBounds(100,220 ,100, 20);
        
        this.errorLabAddress.setBounds(450, 220, 250, 20);
        this.address.setBounds(200,220 ,250, 20);
        this.telLab.setBounds(100,260 ,100, 20);
        
        this.errorLabTel.setBounds(450, 260, 250, 20);
        this.tel.setBounds(200,260 ,250, 20);
        this.teinteLab.setBounds(100,300 ,100, 20);
        
        this.errorLabTeinte.setBounds(450, 300, 250, 20);
        this.teinte.setBounds(200,300 ,250, 20);
        this.sexLab.setBounds(100, 340,100, 20);
        
        this.errorLabSex.setBounds(450, 340, 250, 20);
        this.sex.setBounds(200,340 ,250, 20);
        this.anticidentLab.setBounds(100,380 ,100, 20);
        
        this.errorLabAnticident.setBounds(450, 380, 250, 20);
        this.anticident.setBounds(200,380 ,250, 20);
        this.fonctionLab.setBounds(100,420 ,100, 20);
        
        
        this.fonction.setBounds(200,420 ,250, 20);
        this.ok.setBounds(100,540 ,120, 40);
        this.modifie.setBounds(260,540 ,120, 40);
        
        this.patientRechLab.setBounds(500,100 ,100, 20);
        this.rechCombo.setBounds(610,100 ,100, 20);
        this.nouveauPatient.setBounds(400,50,100, 20);
        
        this.suppPatient.setBounds(420, 540, 120, 40);
        
        this.rechModel.addElement("");
		AutoCompleteDecorator.decorate(rechCombo);
		rechCombo.setSelectedIndex(0);



       this.modifie.setEnabled(false);


		
	}

	public void addInfoPatientActionListener(ActionListener listener){

		this.ok.addActionListener(listener);
		this.modifie.addActionListener(listener);	
		this.nouveauPatient.addActionListener(listener);
		this.rechCombo.addActionListener(listener);
		this.suppPatient.addActionListener(listener);

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

	
	
	public JLabel getErrorLabNom() {
		return errorLabNom;
	}

	public JLabel getErrorLabPrenom() {
		return errorLabPrenom;
	}

	public JLabel getErrorLabAge() {
		return errorLabAge;
	}

	public JLabel getErrorLabAddress() {
		return errorLabAddress;
	}

	public JLabel getErrorLabTel() {
		return errorLabTel;
	}

	public JLabel getErrorLabTeinte() {
		return errorLabTeinte;
	}

	public JLabel getErrorLabSex() {
		return errorLabSex;
	}

	public JLabel getErrorLabAnticident() {
		return errorLabAnticident;
	}

	public JLabel getErrorLabFonction() {
		return errorLabFonction;
	}

	public JButton getNouveauPatient() {
		return nouveauPatient;
	}

	public JComboBox getRechCombo() {
		return rechCombo;
	}

	public JButton getSuppPatient() {
		return suppPatient;
	}
	
	
	
}
