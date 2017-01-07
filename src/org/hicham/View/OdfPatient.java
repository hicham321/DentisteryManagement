package org.hicham.View;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class OdfPatient extends JPanel{

	JLabel actLab= new JLabel("Act: ");
	JTextArea actText= new JTextArea();

	JLabel dateRendezVousLab= new JLabel("Rendez Vous: ");


	JLabel payementTotalLab= new JLabel("Payement: ");
    JTextField payementTotal = new JTextField();    

	JButton ok= new JButton("Ok");
	JButton modifie= new JButton("Annulé");


	JXDatePicker datePicker = new JXDatePicker();
	JSpinner timePicker= new JSpinner(new SpinnerDateModel());

	private JButton ouvrir= new JButton("Selectioné");
	private JButton okImage= new JButton("Ok");

	private JPanel panelImageAct= new JPanel();

	private JButton nouveauAct= new JButton("Nouveau");

	JLabel  actComboLab= new JLabel("Act: ");
	final DefaultComboBoxModel actModel = new DefaultComboBoxModel();
	private JComboBox listActCombo= new JComboBox(actModel);
	private JScrollPane actListScrol = new JScrollPane(listActCombo);
	//
	//
	JLabel nomPrenomLab= new JLabel("Nom et Prenom: ");
	JLabel nomPrenom= new JLabel("");
	
	JLabel payementOdfLab = new JLabel("Payement Total: ");
	JTextField payementOdfText= new JTextField();
	JLabel payementRestLab = new JLabel("Le Reste de Payement: ");
	JTextField payementRestText= new JTextField();

	
    JScrollPane scrol= new JScrollPane(actText);

	
	public OdfPatient(){

		this.setLayout(null);
		this.setBackground(Color.decode("#d2fdf9"));
		JPanel panelOdf= new JPanel();
		panelOdf.setBackground(Color.WHITE);
		panelOdf.setLayout(null);
		panelOdf.setBorder(BorderFactory.createTitledBorder("Info Act: "));

		panelImageAct= new JPanel();
		panelImageAct.setBackground(Color.decode("#d2fdf9"));
		panelImageAct.setLayout(null);
		panelImageAct.setBorder(BorderFactory.createTitledBorder("Radio: "));

		JPanel panelImagecontrol= new JPanel();
		panelImagecontrol.setBackground(Color.decode("#d2fdf9"));
		panelImagecontrol.setLayout(null);

		Border border = BorderFactory.createLineBorder(Color.BLACK);
        actText.setBorder(BorderFactory.createCompoundBorder(border
        		,BorderFactory.createEmptyBorder(0, 0, 10, 10)));

		actText.setLineWrap(true);
		actText.setWrapStyleWord(true);

	

		datePicker.setDate(Calendar.getInstance().getTime());
		datePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timePicker, "HH:mm");
		timePicker.setEditor(timeEditor);
		timePicker.setValue(new Date()); // will only show the current time

		AutoCompleteDecorator.decorate(listActCombo);
		this.actModel.addElement("");
		listActCombo.setSelectedIndex(0);


		panelOdf.add(datePicker);
		panelOdf.add(timePicker);
		panelOdf.add(ok);
		panelOdf.add(actLab);
		
		panelOdf.add(actText);
		panelOdf.add(dateRendezVousLab);
		panelOdf.add(payementTotalLab);
		panelOdf.add(payementTotal);
		panelOdf.add(modifie);

		panelOdf.add(actComboLab);
		panelOdf.add(listActCombo);
		panelOdf.add(nouveauAct);
		//
		//
		panelOdf.add(nomPrenomLab);
		panelOdf.add(nomPrenom);
		panelOdf.add(payementOdfLab);
		panelOdf.add(payementOdfText);
		panelOdf.add(payementRestLab);
		panelOdf.add(payementRestText);
		
		panelOdf.add(scrol);
		scrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);




		




		panelImagecontrol.add(ouvrir);
		panelImagecontrol.add(okImage);

        
		nouveauAct.setBounds(200,30,100,40);
		//
		//
		 nomPrenomLab.setBounds(30, 70, 150, 20);
		 nomPrenom.setBounds(220, 70, 150, 20);
		 payementOdfLab.setBounds(30,320,150,20); 
		 payementOdfText.setBounds(220, 320, 150, 20);
		 payementRestLab.setBounds(30,360 ,150 ,20 );
		 payementRestText.setBounds(220, 360, 150, 20);
		//
		//
		actLab.setBounds(30,190,150,20);
		actText.setBounds(220,150,350,100);
		dateRendezVousLab.setBounds(30,280,150,20);
		datePicker.setBounds(220,280,150,20);
		timePicker.setBounds(400,280,150,20);
		payementTotalLab.setBounds(30,400,150,20);
		payementTotal.setBounds(220,400,150,20);
		ok.setBounds(180,470,150,40);
		modifie.setBounds(350,470,150,40);

		actComboLab.setBounds(30,110,150,20);
		listActCombo.setBounds(220,110,150,20);

//		ouvrir.setBounds();
//		okImage.setBounds();




		panelOdf.setBounds(0, 0, 700, 600);
		panelImageAct.setBounds(600, 0, 300, 300);
		panelImagecontrol.setBounds(600, 300, 300, 50);
		this.add(panelOdf);
		this.add(panelImageAct);
		this.add(panelImagecontrol);
	}

	public void addActPatientViewActionListener(ActionListener listener){
		this.ok.addActionListener(listener);
		this.ouvrir.addActionListener(listener);
		this.okImage.addActionListener(listener);
		this.nouveauAct.addActionListener(listener);
		this.listActCombo.addActionListener(listener);


	}

	public JTextArea getActText() {
		return actText;
	}

	public JButton getOk() {
		return ok;
	}

	public JXDatePicker getDatePicker() {
		return datePicker;
	}

	public JSpinner getTimePicker() {
		return timePicker;
	}

	public JButton getOuvrir() {
		return ouvrir;
	}
	//
	public JPanel getPanelImageAct() {
		return panelImageAct;
	}

	public JButton getOkImage() {
		return okImage;
	}

	public JButton getNouveauAct() {
		return nouveauAct;
	}

	public JComboBox getListActCombo() {
		return listActCombo;
	}

	public JButton getModifie() {
		return modifie;
	}

	public JTextField getPayementTotal() {
		return payementTotal;
	}
	
	//
	//
	
	public JTextField getPayementOdfText() {
		return payementOdfText;
	}

	public JTextField getPayementRestText() {
		return payementRestText;
	}
	
	
}
