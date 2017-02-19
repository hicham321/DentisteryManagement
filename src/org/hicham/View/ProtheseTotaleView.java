package org.hicham.View;

import java.awt.Color;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProtheseTotaleView extends JDesktopPane {
	
	  JLabel numLab= new JLabel("Numero de prothese: ");
	  JTextField NumProthese= new JTextField();
	  
	  JLabel nomEtPrenomLab= new JLabel("Nom Et Prenom: ");
	  JLabel nomEtPrenom= new JLabel("");
	  
	  JLabel typeLab= new JLabel("Numero de prothese: ");
	  JTextField type= new JTextField();
	  
	  JLabel entanteLab= new JLabel("Entante: ");
	  JTextField entante= new JTextField();
	  
	  JLabel payementProtheseLab = new JLabel("Payement Total: ");
	  JTextField payementProtheseText = new JTextField();
	  
	  JLabel payementRestLab = new JLabel("Le Reste de Payement: ");
	  JTextField payementRestText = new JTextField();
	  
	  JLabel payementActuelLab = new JLabel("Payement Actuel: ");
	  JTextField payementActuelText = new JTextField();
	
      public ProtheseTotaleView(){
    	this.setLayout(null);
  		this.setBackground(Color.green);
  		
  	
  		
      }
}
