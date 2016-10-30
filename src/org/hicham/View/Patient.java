package org.hicham.View;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


public class Patient extends JPanel{

	JButton btn = new JButton("btn");
	
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



	public Patient (){
		this.setLayout(null);
		this.setBackground(Color.white);
		
		this.TeintModel.addElement("");
		AutoCompleteDecorator.decorate(teinte);
		teinte.setSelectedIndex(0);
		
		this.sexmodel.addElement("");
		AutoCompleteDecorator.decorate(sex);
		sex.setSelectedIndex(0);
		
		
		
		
		
		
		this.add(btn);
		btn.setBounds(600,150 , 100,20 );
		this.setVisible(true);

	}
	public void addPatientActionListener(ActionListener listener){
		this.btn.addActionListener(listener);
	}
	public JButton getBtn() {
		return btn;
	}


}
