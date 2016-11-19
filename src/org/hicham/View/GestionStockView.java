package org.hicham.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
	JButton sousQte= new JButton("soustraction qte");
	
	//ajout Produit
	JLabel produitAjoutLab= new JLabel("Produit: ");
	JTextField textProduitAjout= new JTextField();
	
	JLabel prixAjoutLab= new JLabel("Prix: ");
	JTextField textPrixAjout= new JTextField();
	
	JButton ajoutProduit= new JButton("Ajouté");
	
	//label info produit
	JLabel produitNomInfoLab= new JLabel("Nom De Produit Selctioné");
	JLabel produitNomInfo = new JLabel("");
	JLabel produitPrixInfoLab= new JLabel("Prix Produit Selectioné: ");
	JLabel produitPrixInfo=new JLabel("");
	JLabel qteInfoLab=new JLabel("Quantité Du Produit selectioné: ");
	JLabel qteInfo=new JLabel("");
	
	//Label Info General




	
	public  DefaultTableModel dt;
	public  JTable produitsTab;
	private JScrollPane scrolPane;


	public GestionStockView(){
		
		this.setLayout(null);
		this.setBackground(Color.decode("#d2fdf9"));
		//panel for adding quatity
		JPanel panelAjoutqte= new JPanel();
		panelAjoutqte.setBackground(Color.cyan);
		panelAjoutqte.setLayout(null);
		panelAjoutqte.setBorder(BorderFactory.createTitledBorder("Ajouté une qte: "));
		//panel for adding new products		
		JPanel panelAjoutProduit= new JPanel();
		panelAjoutProduit.setBackground(Color.decode("#d2fdf9"));
		panelAjoutProduit.setLayout(null);
		panelAjoutProduit.setBorder(BorderFactory.createTitledBorder("Ajouté un produit: "));
		//panel for labels 
		JPanel panelProduitInfo= new JPanel();
		panelProduitInfo.setBackground(Color.decode("#d2fdf9"));
		panelProduitInfo.setLayout(null);
		panelProduitInfo.setBorder(BorderFactory.createTitledBorder("Info du Produit: "));
		


		this.produitModel.addElement("");
		AutoCompleteDecorator.decorate(produitCombo);
		produitCombo.setSelectedIndex(0);
		
		produitsTab =new JTable(dt);
		produitsTab.setPreferredScrollableViewportSize(new Dimension(500,50));
		produitsTab.setFillsViewportHeight(true);
		scrolPane= new JScrollPane(produitsTab);
		//Ajout QTE
		panelAjoutqte.add(produitCombo);
		panelAjoutqte.add(nomProduitLab);
		panelAjoutqte.add(qteAjoutLab);
		panelAjoutqte.add(qteAjout);
		panelAjoutqte.add(ajoutQte);
		panelAjoutqte.add(choixBtn);
		
		nomProduitLab.setBounds(5, 20, 100, 20);
		produitCombo.setBounds(130, 20, 100, 20);
		qteAjoutLab.setBounds(5, 50, 100, 20);
		qteAjout.setBounds(130, 50, 100, 20);
		ajoutQte.setBounds(130, 80, 100, 20);
		choixBtn.setBounds(240, 20, 100, 20);

		
		//Ajout Produit
		panelAjoutProduit.add(produitAjoutLab);
		panelAjoutProduit.add(textProduitAjout);
		panelAjoutProduit.add(prixAjoutLab);
		panelAjoutProduit.add(textPrixAjout);
        panelAjoutProduit.add(ajoutProduit);

        produitAjoutLab.setBounds(5, 20, 100, 20);
        textProduitAjout.setBounds(130, 20, 100, 20);
        prixAjoutLab.setBounds(5, 50, 100, 20);
        textPrixAjout.setBounds(130, 50, 100, 20);
        ajoutProduit.setBounds(130, 80, 100, 20);
        
        //info Produit 
        panelProduitInfo.add(produitNomInfoLab);
        panelProduitInfo.add(produitNomInfo);
        panelProduitInfo.add(produitPrixInfoLab);
        panelProduitInfo.add(produitPrixInfo);
        panelProduitInfo.add(qteInfoLab);
        panelProduitInfo.add(qteInfo);
        
        produitNomInfoLab.setBounds(5, 20, 200, 20);
        produitNomInfo.setBounds(130, 20, 100, 20);
        produitPrixInfoLab.setBounds(5, 50, 200, 20);
        produitPrixInfo.setBounds(130, 50, 100, 20);
        qteInfoLab.setBounds(5, 80, 200, 20);
        qteInfo.setBounds(130, 80, 100, 20);


 
		this.add(panelAjoutqte);
		this.add(panelAjoutProduit);
		this.add(panelProduitInfo);
		
		this.popmenu.add(modifieItem);
		this.popmenu.add(supItem);
		
		panelAjoutqte.setBounds(50, 50, 400, 200);
		panelAjoutProduit.setBounds(600,50,400, 200);
		panelProduitInfo.setBounds(50,300, 400, 200);
	
	}
	
	public void addGestionStockActionListener(ActionListener Listener){
		
		this.sousQte.addActionListener(Listener);
		this.produitCombo.addActionListener(Listener);
		this.ajoutProduit.addActionListener(Listener);
		this.ajoutQte.addActionListener(Listener);
		this.choixBtn.addActionListener(Listener);
		this.modifieItem.addActionListener(Listener);
		this.supItem.addActionListener(Listener);		
	}


	public JComboBox getProduitCombo() {
		return produitCombo;
	}


	public JButton getChoixBtn() {
		return choixBtn;
	}


	public JPopupMenu getPopmenu() {
		return popmenu;
	}


	public JMenuItem getModifieItem() {
		return modifieItem;
	}


	public JMenuItem getSupItem() {
		return supItem;
	}


	public JTextField getQteAjout() {
		return qteAjout;
	}


	public JButton getAjoutQte() {
		return ajoutQte;
	}


	public JButton getSousQte() {
		return sousQte;
	}


	public JTextField getTextProduitAjout() {
		return textProduitAjout;
	}


	public JTextField getTextPrixAjout() {
		return textPrixAjout;
	}


	public JButton getAjoutProduit() {
		return ajoutProduit;
	}


	public JLabel getProduitNomInfo() {
		return produitNomInfo;
	}


	public JLabel getProduitPrixInfo() {
		return produitPrixInfo;
	}


	public JLabel getQteInfo() {
		return qteInfo;
	}


	public JTable getProduitsTab() {
		return produitsTab;
	}
	
	
	

}
