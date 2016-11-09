package org.hicham.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class RecherchePatientView extends JPanel {
	
	JLabel rechLab= new JLabel("Patient: ");
	final DefaultComboBoxModel rechModel = new DefaultComboBoxModel();
	final JComboBox rech = new JComboBox(rechModel);    
	private JScrollPane rechListScrol = new JScrollPane(rech);
	
	public  DefaultTableModel dt;

	public  JTable rechTable;

	private JScrollPane scrolPane;


	public RecherchePatientView(){
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.decode("#d2fdf9"));
		this.setLayout(new FlowLayout());
		
		this.rechModel.addElement("");
		AutoCompleteDecorator.decorate(rech);
		rech.setSelectedIndex(0);
		
		rechTable =new JTable(dt);
		rechTable.setPreferredScrollableViewportSize(new Dimension(500,50));
		rechTable.setFillsViewportHeight(true);
		scrolPane= new JScrollPane(rechTable);
		
		
		
		GridBagConstraints c= new GridBagConstraints();
		c.anchor= GridBagConstraints.BASELINE_TRAILING;
		c.gridx=0;
		c.gridy=0;
		this.add(rechLab,c);
	    c.gridy+=2;
	    this.add(rech,c);
	    c.gridy+=2;
	    this.add(scrolPane,c);
	    c.gridy+=2;

		
		
	}
  
}
