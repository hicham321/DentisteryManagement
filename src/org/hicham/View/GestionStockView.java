package org.hicham.View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class GestionStockView extends JPanel{
	// ajout qte
	JLabel nomProduitLab= new JLabel("Produit: ");
	
	final DefaultComboBoxModel produitModel = new DefaultComboBoxModel();
	final JComboBox produitCombo = new JComboBox(produitModel);    
	private JScrollPane produitListScrol = new JScrollPane(produitCombo);
	
    private JButton choixBtn = new JButton();
	private JPopupMenu  popmenu= new JPopupMenu();
	JMenuItem modifieItem = new JMenuItem("Modifie");
	JMenuItem supItem = new JMenuItem("Supprimé");
	
	JLabel qteAjoutLab= new JLabel("Qte a Ajouté: ");
	JTextField qteAjout= new JTextField();
	
	JButton ajoutQte= new JButton("Ajouté qte");
	//soustraction de qte 
	
	//ajout Produit
	JLabel produitAjoutLab= new JLabel("Produit: ");
	JTextField textProduitAjout= new JTextField();
	
	JLabel prixProduitAjout= new JLabel("Prix: ");
	JTextField textPrixAjout= new JTextField();
	
	public  DefaultTableModel dt;
	public  JTable produitsTab;
	private JScrollPane scrolPane;


	public GestionStockView(){
		
		this.setLayout(null);
		this.setBackground(Color.decode("#d2fdf9"));
		
		this.produitModel.addElement("");
		AutoCompleteDecorator.decorate(produitCombo);
		produitCombo.setSelectedIndex(0);
		
		produitsTab =new JTable(dt);
		produitsTab.setPreferredScrollableViewportSize(new Dimension(500,50));
		produitsTab.setFillsViewportHeight(true);
		scrolPane= new JScrollPane(produitsTab);
		
		
		this.add(nomProduitLab);
		this.add(produitCombo);
		this.add(produitAjoutLab);
		this.add(textProduitAjout);
		this.add(prixProduitAjout);
		this.add(textPrixAjout);
		this.add(nomProduitLab);
		this.add(choixBtn);
		this.popmenu.add(modifieItem);
		this.popmenu.add(supItem);
	
	}
	
	

}
