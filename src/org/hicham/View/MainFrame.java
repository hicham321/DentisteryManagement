package org.hicham.View;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame{
	
	public JPanel cards ;
	//cards to show in the frame
	Ordonance ordonance= new Ordonance();
	InfoPatient infoPatient= new InfoPatient();
	ActPatient actPatient= new ActPatient();
	OdfPatient odfPatient= new OdfPatient();
	RecherchePatientView recherchePatientView= new RecherchePatientView();
	
	public Patient card1= new Patient(infoPatient,actPatient,odfPatient,ordonance,recherchePatientView);
	
	public GestionStockView card2= new GestionStockView();


	private MenuBar menuBar= new MenuBar();

	public MainFrame(Patient card1,GestionStockView card2 , MenuBar menuBar){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screenSize.width, screenSize.height);

		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.menuBar= menuBar;
		this.setJMenuBar(this.menuBar.menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cards = new JPanel(new CardLayout());
		this.card1= card1;
		this.card2= card2;
		cards.add(this.card1, "Card 1");
		cards.add(this.card2, "Card 2");

		getContentPane().add(cards); 
		//setEnabled(false);
	}

}
