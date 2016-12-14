package org.hicham.View;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class RendezVousView extends JPanel{
	JLabel rechercheParPatientLab= new JLabel("Recherche Par Patient:");
	
	final DefaultComboBoxModel PatientModel = new DefaultComboBoxModel();
	final JComboBox patientCombo = new JComboBox(PatientModel);    
	private JScrollPane patientListScrol = new JScrollPane(patientCombo);
	
	JLabel rechercheParDateLab= new JLabel("Recherche Par Date:");
	JXDatePicker datePicker = new JXDatePicker();


	public RendezVousView(){
		
		this.setLayout( null);
		this.setBackground(Color.decode("#d2fdf9"));
		JPanel panelRechercheRendezVous= new JPanel();
		panelRechercheRendezVous.setBackground(Color.cyan);
		panelRechercheRendezVous.setLayout(null);
		panelRechercheRendezVous.setBorder(BorderFactory.createTitledBorder("Recherche Rendez Vous: "));
		
		this.PatientModel.addElement("");
		AutoCompleteDecorator.decorate(patientCombo);
		patientCombo.setSelectedIndex(0);
		
		panelRechercheRendezVous.add(rechercheParPatientLab);
		panelRechercheRendezVous.add(rechercheParDateLab);
		panelRechercheRendezVous.add(patientCombo);
		panelRechercheRendezVous.add(datePicker);
        
		rechercheParPatientLab.setBounds(70, 40, 150, 20);
		rechercheParDateLab.setBounds(70,80 , 150, 20);
		patientCombo.setBounds(250, 40, 200, 20);
		datePicker.setBounds(250, 80, 200, 20);

		

		
		
		this.add(panelRechercheRendezVous);
		panelRechercheRendezVous.setBounds(0, 0, 700, 300);
	}
	
	public void addRendezVousActionListenert(){
		
	}

	public JComboBox getPatientCombo() {
		return patientCombo;
	}

	public JXDatePicker getDatePicker() {
		return datePicker;
	}
	
}
