package org.hicham.View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class RendezVousView extends JPanel{
	
    JLabel modeRechercheLab= new JLabel("La Mode de Recherche:");	
	final JList modeRechercheList;    
	
	JLabel rechercheParPatientLab= new JLabel("Recherche Par Patient:");
	
	final DefaultComboBoxModel PatientModel = new DefaultComboBoxModel();
	final JComboBox patientCombo = new JComboBox(PatientModel);    
	private JScrollPane patientListScrol = new JScrollPane(patientCombo);
	
	JLabel rechercheParDateLab= new JLabel("Recherche Par Date:");
	JXDatePicker datePicker = new JXDatePicker();
	
	JButton rechercheBtn = new JButton("Recherche");
	
	//patient panel
	JLabel nomPatientLab= new JLabel("Nom du patient: ");
	JLabel dateRendezVousLab= new JLabel("Dates de rendez vous: ");
	JLabel actRendezVousLab= new JLabel("Act De Rendez Vous: ");
	JLabel payementLab= new JLabel("Le payement: ");
	JLabel tempsRendezVousLab= new JLabel("Le temps: ");

	

	
	JLabel nomPatient= new JLabel("");
	final DefaultComboBoxModel actModel = new DefaultComboBoxModel();
	final JComboBox actCombo = new JComboBox(actModel);    
	private JScrollPane produitListScrol = new JScrollPane(actCombo);
	JLabel actRendezVous= new JLabel("");
	JLabel payement= new JLabel("");
	JLabel tempsRendezVous= new JLabel("");


	//Rendez Vous Panel
	JLabel dateRVLab= new JLabel("La date: ");
	JLabel dateRV= new JLabel("");
	JLabel patientNomRVLab= new JLabel("Nom du patient");
	final DefaultComboBoxModel patientRVModel = new DefaultComboBoxModel();
	final JComboBox patientComboRV = new JComboBox(patientRVModel);    
	private JScrollPane patientListScrolRV = new JScrollPane(patientComboRV);
	
	JLabel actPatientRVLab= new JLabel("Act du patient: ");
	JLabel actPatientRV= new JLabel("");
	JLabel tempRVLab= new JLabel("Temp du rendez vous: ");
	JLabel tempRV= new JLabel("");
	JLabel payementRVLab= new JLabel("Payement: ");
	JLabel payementRV= new JLabel("");


	


	
	
	
	JPanel panelPatient;
    
	JPanel panelRendezVous;
	
    JPanel panelRechercheRendezVous;
    
	public JPanel cards ;


	public RendezVousView(){
		
		this.setLayout( null);
		this.setBackground(Color.WHITE);
		panelRechercheRendezVous= new JPanel();
		panelRechercheRendezVous.setBackground(Color.WHITE);
		panelRechercheRendezVous.setLayout(null);
		panelRechercheRendezVous.setBorder(BorderFactory.createTitledBorder("Info De Recherche: "));
		
		panelPatient= new JPanel();
		panelPatient.setBackground(Color.WHITE);
		panelPatient.setLayout(null);
		panelPatient.setBorder(BorderFactory.createTitledBorder("Recherche Par Patient: "));
		
	    panelRendezVous= new JPanel();
		panelRendezVous.setBackground(Color.WHITE);
		panelRendezVous.setLayout(null);
		panelRendezVous.setBorder(BorderFactory.createTitledBorder("Recherche Par Rendez Vous: "));
		
		
		String[] modeNames= {"Recherche Par Patient","Recherche Par Date"};
		modeRechercheList= new JList(modeNames);
		modeRechercheList.setVisibleRowCount(2);
		modeRechercheList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.PatientModel.addElement("");
		AutoCompleteDecorator.decorate(patientCombo);
		patientCombo.setSelectedIndex(0);
		
		
		
		panelRechercheRendezVous.add(modeRechercheLab);
		panelRechercheRendezVous.add(modeRechercheList);
		panelRechercheRendezVous.add(rechercheParPatientLab);
		panelRechercheRendezVous.add(rechercheParDateLab);
		panelRechercheRendezVous.add(patientCombo);
		panelRechercheRendezVous.add(datePicker);
		panelRechercheRendezVous.add(rechercheBtn);
		
        //panel rendezVous components
		
		panelRendezVous.add(dateRVLab);
		panelRendezVous.add(dateRV);
		panelRendezVous.add(patientNomRVLab);
		panelRendezVous.add(patientComboRV);
		panelRendezVous.add(actPatientRVLab);
		panelRendezVous.add(actPatientRV);
		panelRendezVous.add(tempRVLab);
		panelRendezVous.add(tempRV);
		panelRendezVous.add(payementRVLab);
		panelRendezVous.add(payementRV);
		
		dateRVLab.setBounds(30, 30, 120, 20);
		patientNomRVLab.setBounds(30, 90, 120, 20);
		actPatientRVLab.setBounds(30, 160, 120, 20);
		tempRVLab.setBounds(30, 210, 120, 20);
		payementRVLab.setBounds(30, 260, 120, 20);

		dateRV.setBounds(160,30,120,20);
		patientComboRV.setBounds(160, 90, 120, 20);
		actPatientRV.setBounds(160, 160, 120, 20);
		tempRV.setBounds(160, 210, 120, 20);
		payementRV.setBounds(160, 	260, 120, 20);


		
		//panel patient components
		
		this.actModel.addElement("");
		actCombo.setSelectedIndex(0);

		panelPatient.add(nomPatient);
		panelPatient.add(actCombo);
		panelPatient.add(actRendezVous);
		panelPatient.add(payement);
		panelPatient.add(nomPatientLab);
		panelPatient.add(dateRendezVousLab);
		panelPatient.add(actRendezVousLab);
		panelPatient.add(payementLab);
		panelPatient.add(tempsRendezVousLab);
		panelPatient.add(tempsRendezVous);
		

		
		
		nomPatientLab.setBounds(30, 30, 120, 20);
		dateRendezVousLab.setBounds(30, 90, 120, 20);
		actRendezVousLab.setBounds(30, 160, 120, 20);
		payementLab.setBounds(30, 210, 120, 20);
		tempsRendezVousLab.setBounds(30, 260, 120, 20);

		nomPatient.setBounds(160,30,120,20);
		actCombo.setBounds(160, 90, 120, 20);
		actRendezVous.setBounds(160, 160, 120, 20);
		payement.setBounds(160, 210, 120, 20);
		tempsRendezVous.setBounds(160, 	260, 120, 20);



        
		
		modeRechercheLab.setBounds(70, 40, 150, 20);
		modeRechercheList.setBounds(250, 40, 200, 40);
		rechercheParPatientLab.setBounds(70, 90, 150, 20);
		rechercheParDateLab.setBounds(70,130 , 150, 20);
		patientCombo.setBounds(250, 90, 200, 20);
		datePicker.setBounds(250, 130, 200, 20);
		rechercheBtn.setBounds(250, 170, 100, 30);
		
		
		//panelPatient.setBounds(0, 300, 1000, 300);
     	//panelRendezVous.setBounds(0, 300, 1000, 300);
		
        cards = new JPanel(new CardLayout());
		
		cards.add(panelPatient, "Card 1");
		cards.add(panelRendezVous, "Card 2");
		cards.setBounds(0, 300, 1000, 300);
		
		this.add(cards);

		
		
		this.add(panelRechercheRendezVous);
		//this.add(panelPatient);
		//this.add(panelRendezVous);

		panelRechercheRendezVous.setBounds(0, 0, 700, 300);
//		panelPatient.setBounds(0, 300, 1000, 300);
//		panelRendezVous.setBounds(0, 300, 1000, 300);
		
		

        
	}
	
	public void addRendezVousActionListener(ActionListener listener){
		this.rechercheBtn.addActionListener(listener);
		this.actCombo.addActionListener(listener);
		this.patientCombo.addActionListener(listener);
		this.patientComboRV.addActionListener(listener);

	}
	
	public void addRendezVousListSelectionListener(ListSelectionListener Listlistener){
		this.modeRechercheList.addListSelectionListener(Listlistener);
	}

	public JComboBox getPatientCombo() {
		return patientCombo;
	}

	public JXDatePicker getDatePicker() {
		return datePicker;
	}

	public JButton getRechercheBtn() {
		return rechercheBtn;
	}

	public JList getModeRechercheList() {
		return modeRechercheList;
	}

	public JLabel getNomPatient() {
		return nomPatient;
	}

	public JComboBox getActCombo() {
		return actCombo;
	}

	public DefaultComboBoxModel getActModel() {
		return actModel;
	}

	public JLabel getActRendezVous() {
		return actRendezVous;
	}

	public JLabel getPayement() {
		return payement;
	}

	public JPanel getPanelPatient() {
		return panelPatient;
	}

	public JPanel getPanelRendezVous() {
		return panelRendezVous;
	}

	public JPanel getPanelRechercheRendezVous() {
		return panelRechercheRendezVous;
	}

	public JLabel getTempsRendezVous() {
		return tempsRendezVous;
	}

	public JLabel getDateRV() {
		return dateRV;
	}

	public JComboBox getPatientComboRV() {
		return patientComboRV;
	}

	public JLabel getActPatientRV() {
		return actPatientRV;
	}

	public JLabel getTempRV() {
		return tempRV;
	}

	public JLabel getPayementRV() {
		return payementRV;
	}
	


	
	
}
