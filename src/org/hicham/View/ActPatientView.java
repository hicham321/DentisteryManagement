package org.hicham.View;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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


    
	public ActPatientView(){
		
		this.setLayout(null);
		this.setBackground(Color.decode("#d2fdf9"));
		JPanel panelAct= new JPanel();
		panelAct.setBackground(Color.WHITE);
		panelAct.setLayout(null);
		panelAct.setBorder(BorderFactory.createTitledBorder("Info Act: "));
		
		panelImageAct= new JPanel();
		panelImageAct.setBackground(Color.decode("#d2fdf9"));
		panelImageAct.setLayout(null);
		panelImageAct.setBorder(BorderFactory.createTitledBorder("Radio: "));
		
		JPanel panelImagecontrol= new JPanel();
		panelImagecontrol.setBackground(Color.decode("#d2fdf9"));
		panelImagecontrol.setLayout(null);
		
		
		
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
        
        AutoCompleteDecorator.decorate(listActCombo);
		this.actModel.addElement("");
        listActCombo.setSelectedIndex(0);
        
        
        panelAct.add(datePicker);
        panelAct.add(timePicker);
        panelAct.add(ok);
        panelAct.add(actLab);
        panelAct.add(actText);
        panelAct.add(dateRendezVousLab);
        panelAct.add(payementLab);
        panelAct.add(payementCombo);
        panelAct.add(modifie);
        
        panelAct.add(actComboLab);
        panelAct.add(listActCombo);
        panelAct.add(nouveauAct);


        
        
        panelImagecontrol.add(ouvrir);
        panelImagecontrol.add(okImage);



        actLab.setBounds(30, 50, 100, 20);
        actText.setBounds(150, 50, 100, 20);
        dateRendezVousLab.setBounds(30, 90, 100, 20);
        datePicker.setBounds(150, 90, 100, 20);
        timePicker.setBounds(270, 90, 100, 20);
        payementLab.setBounds(30, 140, 100, 20);
        payementCombo.setBounds(150, 140, 100, 20);
        ok.setBounds(30, 190, 100, 20);
        modifie.setBounds(150, 190, 100, 20);
        
        actComboLab.setBounds(150, 20, 100, 20);
        listActCombo.setBounds(30, 20, 100, 20);
        nouveauAct.setBounds(300, 20,100 ,20 );

        ouvrir.setBounds(0, 0, 100, 20);
        okImage.setBounds(130, 0, 100, 20);
        
        
		
       
        panelAct.setBounds(0, 0, 500, 500);
        panelImageAct.setBounds(600, 0, 300, 300);
        panelImagecontrol.setBounds(600, 300, 300, 50);
		this.add(panelAct);
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
	
    
	
}
