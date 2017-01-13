package org.hicham.View;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class ProtheseView extends JPanel{

	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	JPanel cards;
	public ProtheseView (ProtheseTotaleView protheseTotaleView
			,ProthesePartielleView prothesePartielleView
			,ProtheseFixeView protheseFixeView){
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		cards = new JPanel(new CardLayout());

		cards.add(protheseTotaleView, "Card 1");
		cards.add(prothesePartielleView, "Card 2");
		cards.add(protheseFixeView, "Card 3");

		cards.setBounds(0, 300, 1000, 300);

		this.add(cards);

	}
}
