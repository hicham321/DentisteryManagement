package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import org.hicham.View.ProtheseFixeView;
import org.hicham.View.ProthesePartielleView;
import org.hicham.View.ProtheseTotaleView;
import org.hicham.View.ProtheseView;

public class ControllerProthese {
	
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	ProtheseView protheseView= new ProtheseView(protheseTotaleView, prothesePartielleView
			, protheseFixeView);
	
	public ControllerProthese(ProtheseView protheseView, ProtheseTotaleView protheseTotaleView
			,ProthesePartielleView prothesePartielleView,ProtheseFixeView protheseFixeView){
		this.protheseView= protheseView;
		this.protheseTotaleView= protheseTotaleView;
		this.protheseFixeView= protheseFixeView;
		this.prothesePartielleView= prothesePartielleView;
		
		this.protheseView.addProtheseActionListener(new ProtheseActionListener());
		
	}
	class ProtheseActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
             if (e.getSource()== protheseView.getListProtheseCombo()) {
         		JComboBox<String> comboBox = (JComboBox) e.getSource();
 				String selectedItem= comboBox.getSelectedItem().toString();
 				System.out.println(selectedItem);
 				if ("Prothese Totale".equals(selectedItem)) {
 					showProtheseTotale();
 					//thread to make processing images faster
 					
 				}
 				if ("Prothese Partielle".equals(selectedItem)) {
 					showProthesePartielle();
 				}
 				if ("Prothese Fixe".equals(selectedItem)) {
 					showProtheseFixe();
 				}
			}			
		}		
	}
	
	public void showProtheseTotale(){
		CardLayout cardLayout = (CardLayout) protheseView.cards.getLayout();
		cardLayout.show(protheseView.cards, "Card 1");
	}
	public void showProthesePartielle(){
		CardLayout cardLayout = (CardLayout) protheseView.cards.getLayout();
		cardLayout.show(protheseView.cards, "Card 2");
	}
	public void showProtheseFixe(){
		CardLayout cardLayout = (CardLayout) protheseView.cards.getLayout();
		cardLayout.show(protheseView.cards, "Card 3");
	}
	
}
