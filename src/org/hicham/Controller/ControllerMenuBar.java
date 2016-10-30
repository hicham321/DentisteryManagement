package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.Ordonance;
import org.hicham.View.Patient;

public class ControllerMenuBar {
	
	MenuBar menuBar= new MenuBar();
	
	Ordonance ordonance = new Ordonance();
	
	Patient patient = new Patient();
	
	MainFrame mainFrame= new MainFrame(patient, ordonance,menuBar);
	
	public ControllerMenuBar(MainFrame mainFrame,MenuBar menuBar,Patient patient,Ordonance ordonance){
		
		this.patient= patient;
		this.ordonance= ordonance;
		this.menuBar= menuBar;
		this.mainFrame= mainFrame;
		this.menuBar.addMenuBarActionListener(new MenuBarActionListener() );
		
	}
	class MenuBarActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()== menuBar.getOrdonance()) {
				//show ordonance card
			}
			if (e.getSource()== menuBar.getPatient()) {
				//show patient card

			}
			if (e.getSource()== menuBar.getLabo()) {
				//show labo card

			}
			if (e.getSource()== menuBar.getGestionStock()) {
				//show gestionStock card

			}
			if (e.getSource()== menuBar.getRetour()) {
				//show first card

			}
			if (e.getSource()== menuBar.getMotpasse()) {
				//show motpasse frame
				showOrdonanceCard();

			}
			if (e.getSource()== menuBar.getMenuapropos()) {
				//show apropos frame

			}
			if (e.getSource()== menuBar.getAjoutbase()) {
				//show Jfileschooser card

			}
			
		}

    }
    public void showOrdonanceCard(){
		CardLayout cardLayout = (CardLayout) this.mainFrame.cards.getLayout();
		cardLayout.show(this.mainFrame.cards, "Card 2");	
	}
}
