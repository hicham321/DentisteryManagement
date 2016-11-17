package org.hicham.View;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GestionStockView extends JPanel{
	
	JLabel nomProduitLab= new JLabel("Produit: ");
	
	final DefaultComboBoxModel produitModel = new DefaultComboBoxModel();
	final JComboBox produitCombo = new JComboBox(produitModel);    
	private JScrollPane produitListScrol = new JScrollPane(produitCombo);
	
	JLabel produitAjoutLab= new JLabel("Produit: ");
	JTextField textProduitAjout= new JTextField();
	
	JLabel prixProduitAjout= new JLabel("Prix: ");
	JTextField textPrixAjout= new JTextField();


	
	public GestionStockView(){
		this.setLayout(null);
		this.setBackground(Color.decode("#d2fdf9"));
		
		
	}

}
