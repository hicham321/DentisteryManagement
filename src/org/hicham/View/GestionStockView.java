package org.hicham.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class GestionStockView extends JDesktopPane{
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
	
	JLabel qteErrorLab= new JLabel("*");

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
	JLabel prixTotalLab=new JLabel("Prix Total: ");
	JLabel prixTotal=new JLabel("");

	
	
	//Label Info General
	
	public  DefaultTableModel dt;
	public  JTable produitsTab;
	private JScrollPane scrolPane;
	//
	JInternalFrame produitNomFrame= new JInternalFrame();
	public JInternalFrame getProduitNomFrame() {
		return produitNomFrame;
	}

	JLabel nomProduitTextLab= new JLabel("Nom du produit:");
    JTextField newNameText= new JTextField("");
	public JTextField getNewNameText() {
		return newNameText;
	}

	JButton okModifie= new JButton("Ok");
	public JButton getOkModifie() {
		return okModifie;
	}

	JButton annuleModifie= new JButton("Annuler");



	public JButton getAnnuleModifie() {
		return annuleModifie;
	}



	public GestionStockView(){
		
		this.setLayout(null);
		this.setBackground(Color.white);
		//panel for adding quatity
		JPanel panelAjoutqte= new JPanel();
		panelAjoutqte.setBackground(Color.white);
		panelAjoutqte.setLayout(null);
		panelAjoutqte.setBorder(BorderFactory.createTitledBorder("Ajouté une qte: "));
		//panel for adding new products		
		JPanel panelAjoutProduit= new JPanel();
		panelAjoutProduit.setBackground(Color.white);
		panelAjoutProduit.setLayout(null);
		panelAjoutProduit.setBorder(BorderFactory.createTitledBorder("Ajouté un produit: "));
		//panel for labels 
		JPanel panelProduitInfo= new JPanel();
		panelProduitInfo.setBackground(Color.white);
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
		panelAjoutqte.add(sousQte);
		panelAjoutqte.add(choixBtn);
		panelAjoutqte.add(qteErrorLab);
		((AbstractDocument) qteAjout.getDocument()).setDocumentFilter(new IntegerDocumentFilter());

		//put image icon
		try {
			Image img = ImageIO.read(getClass().getResource("/resources/61456.png"));
			Image newimg = img.getScaledInstance( 16, 16,  java.awt.Image.SCALE_SMOOTH ) ;  
			choixBtn.setIcon(new ImageIcon(newimg));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		nomProduitLab.setBounds(5, 20, 100, 20);
		produitCombo.setBounds(130, 20, 100, 20);
		qteAjoutLab.setBounds(5, 50, 100, 20);
		qteAjout.setBounds(130, 50, 100, 20);
		qteErrorLab.setBounds(250, 50, 50, 20);
		ajoutQte.setBounds(130, 80, 100, 20);
		sousQte.setBounds(250, 80, 100, 20);
		choixBtn.setBounds(240, 20, 30, 20);
		
		qteErrorLab.setForeground(Color.red);
		qteErrorLab.setVisible(false);
		
		//Ajout Produit
		panelAjoutProduit.add(produitAjoutLab);
		panelAjoutProduit.add(textProduitAjout);
		panelAjoutProduit.add(prixAjoutLab);
		panelAjoutProduit.add(textPrixAjout);
        panelAjoutProduit.add(ajoutProduit);
		((AbstractDocument) textPrixAjout.getDocument()).setDocumentFilter(new MyDocumentFilter());


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
        panelProduitInfo.add(prixTotalLab);
        panelProduitInfo.add(prixTotal);

        
        produitNomInfoLab.setBounds(5, 20, 200, 20);
        produitNomInfo.setBounds(230, 20, 100, 20);
        produitPrixInfoLab.setBounds(5, 50, 200, 20);
        produitPrixInfo.setBounds(230, 50, 100, 20);
        qteInfoLab.setBounds(5, 80, 200, 20);
        qteInfo.setBounds(230, 80, 100, 20);
        prixTotalLab.setBounds(5, 110, 200, 20);
        prixTotal.setBounds(230, 110, 200, 20);



 
		this.add(panelAjoutqte);
		this.add(panelAjoutProduit);
		this.add(panelProduitInfo);
		
		this.popmenu.add(modifieItem);
		this.popmenu.add(supItem);
		
		panelAjoutqte.setBounds(50, 50, 400, 200);
		panelAjoutProduit.setBounds(600,50,400, 200);
		panelProduitInfo.setBounds(50,300, 400, 200);
		
		
		//
		JPanel panelPay= new JPanel();
		panelPay.setLayout(null);
		panelPay.setBackground(Color.WHITE);
		
		nomProduitTextLab.setBounds(30,40 ,120 ,30 );;
		newNameText.setBounds(170,40 ,200 ,30 );
		okModifie.setBounds(100,85 ,100 ,30 );
		annuleModifie.setBounds(250,85 ,100 ,30 );

		panelPay.add(nomProduitTextLab);
		panelPay.add(newNameText);
		panelPay.add(okModifie);
		panelPay.add(annuleModifie);



		produitNomFrame.add(panelPay);
		produitNomFrame.setTitle("Modifie le nom du produit");
		produitNomFrame.setSize(450, 180);
		produitNomFrame.toFront();
		try {
			produitNomFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

		this.produitNomFrame.setLocation(490,180);
		produitNomFrame.setVisible(false);
		this.add(produitNomFrame);
	
	}
	

	
	public void addGestionStockActionListener(ActionListener Listener){
		
		this.sousQte.addActionListener(Listener);
		this.produitCombo.addActionListener(Listener);
		this.ajoutProduit.addActionListener(Listener);
		this.ajoutQte.addActionListener(Listener);
		this.choixBtn.addActionListener(Listener);
		this.modifieItem.addActionListener(Listener);
		this.supItem.addActionListener(Listener);
		this.annuleModifie.addActionListener(Listener);
		this.okModifie.addActionListener(Listener);

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

	public JLabel getPrixTotal() {
		return prixTotal;
	}

	public JLabel getQteErrorLab() {
		return qteErrorLab;
	}
	
	
	

}
