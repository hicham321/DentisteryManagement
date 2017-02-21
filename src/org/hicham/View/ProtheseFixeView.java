package org.hicham.View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;

import org.hicham.Controller.ControllerProtheseFixe.ProtheseFixeMouseListener;
import org.jdesktop.swingx.JXDatePicker;

public class ProtheseFixeView extends JDesktopPane{
	

	JLabel  protheseRvLab= new JLabel("Rendez Vous Precedent: ");
    final DefaultComboBoxModel protheseRvModel = new DefaultComboBoxModel();
    private JComboBox listRVCombo= new JComboBox(protheseRvModel);
	private JScrollPane rvListScrol = new JScrollPane(listRVCombo);

	
	JLabel numLabel= new JLabel("Numero");
	JTextField numero= new JTextField();
	
	JLabel nomPrenomLab= new JLabel("Nom et Prenom");
	JLabel nomPrenom= new JLabel("");
	
	JLabel typeProtheseLab= new JLabel("Type de Prothese");
	JTextField typeProthese= new JTextField();
	
	JLabel ententeLab= new JLabel("Entente");
	JTextField entente= new JTextField();
	
	JLabel dateRendezVousLab = new JLabel("Rendez Vous: ");
	JXDatePicker datePicker = new JXDatePicker();
	JSpinner timePicker = new JSpinner(new SpinnerDateModel());
	
	JPanel imagePanel;


	JButton ajoute= new JButton("Ajouté");
	JButton modifie= new JButton("Modifie");
	JButton supp= new JButton("Supprimer");
	JButton nouveau= new JButton("Nouveau");

	
	JButton ajouteImage= new JButton("Image");
	JButton deleteImage= new JButton("Supprimé");
	JButton annuleImage= new JButton("Annulé");
	JLabel imageLabel= new JLabel();

	JInternalFrame showImage= new JInternalFrame();
	
    JPanel panelShowImage=new JPanel();

	public ProtheseFixeView(){

		this.setLayout(null);
		this.setBackground(Color.gray);
		
		imagePanel= new JPanel();
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setLayout(new GridLayout(4,3));
		imagePanel.setBorder(BorderFactory.createTitledBorder("Images:"));
		
		this.add(imagePanel);
		imagePanel.setBounds(650, 15 ,500 , 500);
		
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timePicker, "HH:mm");
        timePicker.setEditor(timeEditor);
        timePicker.setValue(new Date()); // will only show the current time
        datePicker.setDate(Calendar.getInstance().getTime());
        datePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        
        panelShowImage.setLayout(null);
        panelShowImage.setBackground(Color.WHITE);
      
        showImage.add(panelShowImage);
        showImage.setTitle("Image Selectioné");
        showImage.setSize(1100, 600);
        showImage.toFront();
		try {
			showImage.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		Dimension jInternalFrameSiz = this.showImage.getSize();
		
		this.showImage.setLocation((1400 - jInternalFrameSiz.width)/2,
		    (750- jInternalFrameSiz.height)/2);
		showImage.setVisible(false);	
		
		panelShowImage.add(deleteImage);
		panelShowImage.add(annuleImage);
		panelShowImage.add(imageLabel);

		deleteImage.setFont(new Font("Arial", Font.PLAIN, 20));
		annuleImage.setFont(new Font("Arial", Font.PLAIN, 20));

		deleteImage.setBounds(0, 500, 500, 70);
		annuleImage.setBounds(500, 500,500, 70);
		imageLabel.setBounds(0, 0, 1000, 500);
		
		this.add(numLabel);
		this.add(numero);
		this.add(nomPrenomLab);
		this.add(nomPrenom);
		this.add(typeProtheseLab);
		this.add(typeProthese);
		this.add(ententeLab);
		this.add(entente);
		this.add(dateRendezVousLab);
		this.add(datePicker);
		this.add(timePicker);
		this.add(ajoute);
		this.add(modifie);
		this.add(supp);
		this.add(ajouteImage);
		this.add(nouveau);
		this.add(listRVCombo);
		this.add(protheseRvLab);
		this.add(showImage);

		
		protheseRvLab.setBounds(30, 40, 1200, 20);
		listRVCombo.setBounds(170, 40, 200, 20);
		numLabel.setBounds(30, 140, 120, 20);
		numero.setBounds(170, 140, 200, 20);
		nomPrenomLab.setBounds(30,90 , 120, 20);
		nomPrenom.setBounds(170, 90, 200, 20);
		typeProtheseLab.setBounds(30, 190, 120, 20);
		typeProthese.setBounds(170, 190, 200, 20);
		ententeLab.setBounds(30, 240, 120, 20);
		entente.setBounds(170, 240, 200, 20);
		dateRendezVousLab.setBounds(30, 290, 120, 20);
		datePicker.setBounds(270, 290, 130, 20);
		timePicker.setBounds(170, 290, 80, 20);
		ajoute.setBounds(70, 500, 100, 40);
		modifie.setBounds(200, 500, 100, 40);
		supp.setBounds(330, 500, 100, 40);
		ajouteImage.setBounds(450, 500, 100, 40);
		nouveau.setBounds(400, 30, 100, 40);
	}

    public void addProtheseFixActionListener(ActionListener listener){
    	
    	this.ajoute.addActionListener(listener);
    	this.modifie.addActionListener(listener);
    	this.supp.addActionListener(listener);
    	this.ajouteImage.addActionListener(listener);
    	this.nouveau.addActionListener(listener);
    	this.listRVCombo.addActionListener(listener);
    	this.deleteImage.addActionListener(listener);
    	this.annuleImage.addActionListener(listener);

    }
    public void setEmptyFields(){
		this.getTimePicker().setValue(new Date());
		this.getEntente().setText("");
		this.getTypeProthese().setText("");
		this.getNumero().setText("");
		this.getDatePicker().setDate(new Date());
	}
    
	
    public void addProtheseFixeMouseListener(MouseAdapter mouseAdapter,JLabel label){
    	label.addMouseListener(mouseAdapter);
		
    }
    
    //utility methods
    public void clearImages(){
	    for (java.awt.Component label:this.getImagePanel().getComponents()){
			this.getImagePanel().remove(label);
		}
		this.getImagePanel().revalidate();
		this.getImagePanel().repaint();
	}



	public JTextField getNumero() {
		return numero;
	}

	public JLabel getNomPrenom() {
		return nomPrenom;
	}

	public JTextField getTypeProthese() {
		return typeProthese;
	}

	public JTextField getEntente() {
		return entente;
	}

	public JXDatePicker getDatePicker() {
		return datePicker;
	}

	public JSpinner getTimePicker() {
		return timePicker;
	}
	
	public JPanel getImagePanel() {
		return imagePanel;
	}

	public JButton getAjoute() {
		return ajoute;
	}

	public JButton getModifie() {
		return modifie;
	}

	public JButton getSupp() {
		return supp;
	}

	public JButton getAjouteImage() {
		return ajouteImage;
	}

	public JButton getNouveau() {
		return nouveau;
	}

	public JComboBox getListRVCombo() {
		return listRVCombo;
	}

	public JPanel getPanelShowImage() {
		return panelShowImage;
	}

	public JInternalFrame getShowImage() {
		return showImage;
	}

	public JButton getDeleteImage() {
		return deleteImage;
	}

	public JButton getAnnuleImage() {
		return annuleImage;
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}

	
	
}
