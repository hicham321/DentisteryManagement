package org.hicham.View;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame{
	public JPanel cards ;
	//cards to show in the frame
	public Patient card1= new Patient();
	public Ordonance card2= new Ordonance();
	
	private MenuBar menu= new MenuBar();
	
	public MainFrame(Patient card1, Ordonance card2){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(0,0,screenSize.width, screenSize.height);
     
		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setJMenuBar(menu.menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cards = new JPanel(new CardLayout());
        this.card1= card1;
        this.card2=card2;
		cards.add(this.card1, "Card 1");
		cards.add(this.card2, "Card 2");

		getContentPane().add(cards); 
        //setEnabled(false);
	}

}
