package org.hicham.View;

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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

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
	
	
	//Rendez Vous Panel
	
	


	public RendezVousView(){
		
		this.setLayout( null);
		this.setBackground(Color.decode("#d2fdf9"));
		JPanel panelRechercheRendezVous= new JPanel();
		panelRechercheRendezVous.setBackground(Color.cyan);
		panelRechercheRendezVous.setLayout(null);
		panelRechercheRendezVous.setBorder(BorderFactory.createTitledBorder("Recherche Rendez Vous: "));
		
		JPanel panelPatient= new JPanel();
		panelPatient.setBackground(Color.cyan);
		panelPatient.setLayout(null);
		panelPatient.setBorder(BorderFactory.createTitledBorder("Recherche Par Patient: "));
		
		JPanel panelRendezVous= new JPanel();
		panelRendezVous.setBackground(Color.cyan);
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
        
		
		modeRechercheLab.setBounds(70, 40, 150, 20);
		modeRechercheList.setBounds(250, 40, 200, 40);
		rechercheParPatientLab.setBounds(70, 90, 150, 20);
		rechercheParDateLab.setBounds(70,130 , 150, 20);
		patientCombo.setBounds(250, 90, 200, 20);
		datePicker.setBounds(250, 130, 200, 20);
		rechercheBtn.setBounds(250, 170, 100, 30);
		

		
		
		this.add(panelRechercheRendezVous);
		this.add(panelPatient);
		this.add(panelRendezVous);

		panelRechercheRendezVous.setBounds(0, 0, 700, 300);
		panelPatient.setBounds(0, 300, 1000, 300);
		panelRendezVous.setBounds(0, 300, 1000, 300);

	}
	
	public void addRendezVousActionListener(ActionListener listener){
		this.rechercheBtn.addActionListener(listener);
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
	
	
	
}
