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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ActPatientView extends JPanel {
	
	JLabel actLab= new JLabel("Act: ");
	JTextArea actText= new JTextArea();
	
	JLabel dateRendezVousLab= new JLabel("Rendez Vous: ");
	
	
	JLabel payementLab= new JLabel("Payement: ");
	final DefaultComboBoxModel payementModel = new DefaultComboBoxModel();
	final JComboBox payementCombo = new JComboBox(payementModel);    
	private JScrollPane payementListScrol = new JScrollPane(payementCombo);
	
	JButton ok= new JButton("Ok");
	JButton annule= new JButton("Annulé");

	
	JXDatePicker datePicker = new JXDatePicker();
    JSpinner timePicker= new JSpinner(new SpinnerDateModel());
    
	public ActPatientView(){
		
		this.setLayout(null);
		this.setBackground(Color.decode("#d2fdf9"));
		JPanel panelAct= new JPanel();
		panelAct.setBackground(Color.WHITE);
		panelAct.setLayout(null);
		panelAct.setBorder(BorderFactory.createTitledBorder("Act: "));
		
		this.payementModel.addElement("");
		this.payementModel.addElement("1000");
		this.payementModel.addElement("1500");
		this.payementModel.addElement("2000");
		this.payementModel.addElement("3000");
		this.payementModel.addElement("3500");
		this.payementModel.addElement("4000");


		
		AutoCompleteDecorator.decorate(payementCombo);
		payementCombo.setSelectedIndex(0);
		
        datePicker.setDate(Calendar.getInstance().getTime());
        datePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timePicker, "HH:mm");
        timePicker.setEditor(timeEditor);
        timePicker.setValue(new Date()); // will only show the current time
        
        
        panelAct.add(datePicker);
        panelAct.add(timePicker);
        panelAct.add(ok);
        panelAct.add(actLab);
        panelAct.add(actText);
        panelAct.add(dateRendezVousLab);
        panelAct.add(payementLab);
        panelAct.add(payementCombo);
        panelAct.add(annule);


        actLab.setBounds(30, 50, 100, 20);
        actText.setBounds(150, 50, 100, 20);
        dateRendezVousLab.setBounds(30, 90, 100, 20);
        datePicker.setBounds(150, 90, 100, 20);
        timePicker.setBounds(270, 90, 100, 20);
        payementLab.setBounds(30, 140, 100, 20);
        payementCombo.setBounds(150, 140, 100, 20);
        ok.setBounds(30, 190, 100, 20);
        annule.setBounds(150, 190, 100, 20);



        panelAct.setBounds(0, 0, 500, 500);

		this.add(panelAct);
	}
	
	public void addActPatientViewActionListener(ActionListener listener){
		this.ok.addActionListener(listener);
	}

	public JTextArea getActText() {
		return actText;
	}

	public JComboBox getPayementCombo() {
		return payementCombo;
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
	
	
}
