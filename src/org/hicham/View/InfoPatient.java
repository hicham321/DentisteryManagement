package org.hicham.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class InfoPatient extends JPanel{
    
	JTextField nom= new JTextField();
	JTextField prenom= new JTextField();
	JTextField age= new JTextField();
	JTextField address= new JTextField();
	JTextField tel= new JTextField();
	
	final DefaultComboBoxModel TeintModel = new DefaultComboBoxModel();
	final JComboBox teinte = new JComboBox(TeintModel);    
	private JScrollPane teinteListScrol = new JScrollPane(teinte);
	
	final DefaultComboBoxModel sexmodel = new DefaultComboBoxModel();
	final JComboBox sex  = new JComboBox(sexmodel);    
	private JScrollPane sexListScrol = new JScrollPane(sex);
	
	JTextField anticident= new JTextField();
	JTextField fonction= new JTextField();
	
	JButton ok = new JButton("Ok");
	JButton annule= new JButton("Annuler");

	
	public InfoPatient(){
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode("#d2fdf9"));

		this.TeintModel.addElement("");
		AutoCompleteDecorator.decorate(teinte);
		teinte.setSelectedIndex(0);
		
		this.sexmodel.addElement("");
		AutoCompleteDecorator.decorate(sex);
		sex.setSelectedIndex(0);
		
	}
	
	public void addInfoPatientActionListener(ActionListener listener){
		
		this.ok.addActionListener(listener);
		this.annule.addActionListener(listener);	
			
	}
}
