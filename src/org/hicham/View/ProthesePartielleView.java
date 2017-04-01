package org.hicham.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.text.AbstractDocument;

import org.jdesktop.swingx.JXDatePicker;

public class ProthesePartielleView extends JDesktopPane{

    //Gui part
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

	JLabel dateRendezVousLab = new JLabel("Rendez Vous le: ");
	JXDatePicker datePicker = new JXDatePicker();
	JLabel timeLab = new JLabel("A: ");
	JSpinner timePicker = new JSpinner(new SpinnerDateModel());



	JButton ajoute= new JButton("Ajouté");
	JButton modifie= new JButton("Modifie");
	JButton supp= new JButton("Supprimer");
	JButton nouveau= new JButton("Nouveau");

	
	//images
	JPanel imagePanel;

	JButton ajouteImage= new JButton("Image");
	JButton deleteImage= new JButton("Supprimé");
	JButton annuleImage= new JButton("Annulé");
	JLabel imageLabel= new JLabel();

	JInternalFrame showImage= new JInternalFrame();

	JPanel panelShowImage=new JPanel();

	//payement
	JLabel payementActuelLab= new JLabel("Versé:");
	JLabel payementTotalLab= new JLabel("Versement Total:");
	JLabel PayementRestLab= new JLabel("Reste:");
	JLabel payementActuel= new JLabel("");
	JLabel payementTotal= new JLabel("");
	JLabel PayementRest= new JLabel("");
	
	JButton addPay= new JButton("Versement");
	
	JInternalFrame payementFrame= new JInternalFrame();
	JLabel payementActuelTextLab= new JLabel("payement a ajouté:");
    JTextField payementActuelText= new JTextField("0");
	JLabel PayementTotalTextLab= new JLabel("Total: ");
    JTextField PayementTotalText= new JTextField("0");
	JButton okPay= new JButton("Ok");
	JButton annulePay= new JButton("Annuler");



	public ProthesePartielleView(){
        //specific gui stuff
		this.setLayout(null);
		this.setBackground(Color.WHITE);

		

		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timePicker, "HH:mm");
		timePicker.setEditor(timeEditor);
		timePicker.setValue(new Date()); // will only show the current time
		datePicker.setDate(Calendar.getInstance().getTime());
		datePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		
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
		this.add(timeLab);
		this.add(timePicker);
		this.add(ajoute);
		this.add(modifie);
		this.add(supp);
		this.add(nouveau);
		this.add(listRVCombo);
		this.add(protheseRvLab);
		
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
		datePicker.setBounds(170, 290, 130, 20);
		timeLab.setBounds(320,290 ,50 , 20);
		timePicker.setBounds(390, 290, 80, 20);
		ajoute.setBounds(70, 500, 100, 40);
		modifie.setBounds(200, 500, 100, 40);
		supp.setBounds(330, 500, 100, 40);
		nouveau.setBounds(400, 30, 100, 40);

		
		
		
		//images 
		imagePanel= new JPanel();
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setLayout(new GridLayout(4,3));
		imagePanel.setBorder(BorderFactory.createTitledBorder("Images:"));

		this.add(imagePanel);
		imagePanel.setBounds(650, 15 ,500 , 500);

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
		this.showImage.setLocation(0,0);
		showImage.setVisible(false);	

		panelShowImage.add(deleteImage);
		panelShowImage.add(annuleImage);
		panelShowImage.add(imageLabel);

		deleteImage.setFont(new Font("Arial", Font.PLAIN, 20));
		annuleImage.setFont(new Font("Arial", Font.PLAIN, 20));

		deleteImage.setBounds(0, 500, 500, 70);
		annuleImage.setBounds(500, 500,500, 70);
		imageLabel.setBounds(0, 0, 1000, 500);
		ajouteImage.setBounds(450, 500, 100, 40);

		this.add(ajouteImage);
		this.add(showImage);	

		//payement
		JPanel panelPay= new JPanel();
		panelPay.setLayout(null);
		panelPay.setBackground(Color.WHITE);
		
		payementActuelTextLab.setBounds(30,40 ,120 ,30 );;
		payementActuelText.setBounds(170,40 ,200 ,30 );
		PayementTotalTextLab.setBounds(30,90 ,120 ,30 );
		PayementTotalText.setBounds(170,90 ,200 ,30 );
		okPay.setBounds(100,140 ,100 ,30 );
		annulePay.setBounds(250,140 ,100 ,30 );
		((AbstractDocument) PayementTotalText.getDocument()).setDocumentFilter(new MyDocumentFilter());
		((AbstractDocument) payementActuelText.getDocument()).setDocumentFilter(new MyDocumentFilter());

		panelPay.add(payementActuelTextLab);
		panelPay.add(payementActuelText);
		panelPay.add(PayementTotalTextLab);
		panelPay.add(PayementTotalText);
		panelPay.add(okPay);
		panelPay.add(annulePay);



		payementFrame.add(panelPay);
		payementFrame.setTitle("Versement");
		payementFrame.setSize(450, 220);
		payementFrame.toFront();
		try {
			payementFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

		this.payementFrame.setLocation(490,180);
		payementFrame.setVisible(false);	
		
		this.add(payementActuelLab);
		this.add(payementActuel);
		this.add(payementTotalLab);
		this.add(payementTotal);
		this.add(PayementRestLab);
		this.add(PayementRest);
		this.add(addPay);
		this.add(payementFrame);


		payementActuelLab.setBounds(30, 340,120 ,20);
		payementActuel.setBounds(170,340 , 200,20);
		payementTotalLab.setBounds(30,380 ,120 ,20);
		payementTotal.setBounds(170, 380, 120,20);
		PayementRestLab.setBounds(30, 420,120 ,20);
		PayementRest.setBounds(170,420 ,120 , 20);
		addPay.setBounds(400,340 , 100, 20);

		
		
	}

	public void addProthesePartielleActionListener(ActionListener listener){
        //gui stuff
		this.ajoute.addActionListener(listener);
		this.modifie.addActionListener(listener);
		this.supp.addActionListener(listener);
		this.nouveau.addActionListener(listener);
		this.listRVCombo.addActionListener(listener);
		//image
		this.ajouteImage.addActionListener(listener);
		this.deleteImage.addActionListener(listener);
		this.annuleImage.addActionListener(listener);
		//payement
		this.addPay.addActionListener(listener);
		this.okPay.addActionListener(listener);
		this.annulePay.addActionListener(listener);

	}
	public void setEmptyFields(){
		this.getTimePicker().setValue(new Date());
		this.getEntente().setText("");
		this.getTypeProthese().setText("");
		this.getNumero().setText("");
		this.getDatePicker().setDate(new Date());
		this.payementActuel.setText("");
		this.payementTotal.setText("");
		this.PayementRest.setText("");
		this.payementActuelText.setText("0");
		this.PayementTotalText.setText("0");
	}


	public void addProthesePartielleMouseListener(MouseAdapter mouseAdapter,JLabel label){
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


	public JButton getAjoute() {
		return ajoute;
	}

	public JButton getModifie() {
		return modifie;
	}

	public JButton getSupp() {
		return supp;
	}

	public JButton getNouveau() {
		return nouveau;
	}

	public JComboBox getListRVCombo() {
		return listRVCombo;
	}
	
	
// image gui 
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
	
	public JPanel getImagePanel() {
		return imagePanel;
	}
	
	public JButton getAjouteImage() {
		return ajouteImage;
	}
	
	
    //payement
	public JButton getAddPay() {
		return addPay;
	}

	public JButton getAnnulePay() {
		return annulePay;
	}

	public JTextField getPayementActuelText() {
		return payementActuelText;
	}

	public JTextField getPayementTotalText() {
		return PayementTotalText;
	}

	public JButton getOkPay() {
		return okPay;
	}

	public JInternalFrame getPayementFrame() {
		return payementFrame;
	}

	public JLabel getPayementActuel() {
		return payementActuel;
	}

	public JLabel getPayementTotal() {
		return payementTotal;
	}

	public JLabel getPayementRest() {
		return PayementRest;
	}



}
