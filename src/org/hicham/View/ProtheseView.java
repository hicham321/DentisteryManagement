package org.hicham.View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProtheseView extends JPanel{
    
	
	
	JLabel listProtheseComboLab= new JLabel("Prothese: ");
	final DefaultComboBoxModel<String> protheseModel = new DefaultComboBoxModel();
	JComboBox<String> listProtheseCombo= new JComboBox(protheseModel);
	private JScrollPane scroll = new JScrollPane(listProtheseCombo);
	
	JLabel titreProthese= new JLabel("");
	
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	public JPanel cards;
	
	public ProtheseView (ProtheseTotaleView protheseTotaleView
			,ProthesePartielleView prothesePartielleView
			,ProtheseFixeView protheseFixeView){
		
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		this.protheseModel.addElement("Prothese Totale");
		this.protheseModel.addElement("Prothese Partielle");
		this.protheseModel.addElement("Prothese Fixe");
		this.listProtheseCombo.setSelectedIndex(0);

		cards = new JPanel(new CardLayout());

		cards.add(protheseTotaleView, "Card 1");
		cards.add(prothesePartielleView, "Card 2");
		cards.add(protheseFixeView, "Card 3");
		
		this.add(cards);
		this.add(listProtheseComboLab);
		this.add(listProtheseCombo);
		this.add(titreProthese);
		titreProthese.setFont(titreProthese.getFont().deriveFont(36.0f));

   
		cards.setBounds(0, 50, 1500, 600);
        listProtheseComboLab.setBounds(40,25 , 120, 20);
        listProtheseCombo.setBounds(180, 25, 150, 20);
        titreProthese.setBounds(400,10 ,400 ,40 );

	}
	public void addProtheseActionListener(ActionListener listener){
		
		this.listProtheseCombo.addActionListener(listener);
	}
	public JComboBox<String> getListProtheseCombo() {
		return listProtheseCombo;
	}
	public JLabel getTitreProthese() {
		return titreProthese;
	}
	
}
