package org.hicham.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		
		rechTable =new JTable(dt);
		rechTable.setPreferredScrollableViewportSize(new Dimension(500,50));
		rechTable.setFillsViewportHeight(true);


		
		
	}
  
}
