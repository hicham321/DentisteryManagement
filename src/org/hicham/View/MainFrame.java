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
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
	RecherchePatientView recherchePatientView= new RecherchePatientView();
	
	public HomePanel card1= new HomePanel();
	
	public Patient card2= new Patient(infoPatient,actPatient,odfPatient,ordonance,recherchePatientView);
	
	public GestionStockView card3= new GestionStockView();



	private MenuBar menuBar= new MenuBar();

	public MainFrame(HomePanel card1,Patient card2,GestionStockView card3 , MenuBar menuBar){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screenSize.width, screenSize.height);

		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.menuBar= menuBar;
		this.setJMenuBar(this.menuBar.menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cards = new JPanel(new CardLayout());
		this.card1=card1;
		this.card2= card2;
		this.card3= card3;
		cards.add(this.card1, "Card 1");

		cards.add(this.card2, "Card 2");
		cards.add(this.card3, "Card 3");

		getContentPane().add(cards); 
		//setEnabled(false);
		this.pack();
	}

}
