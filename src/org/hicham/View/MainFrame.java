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
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	ProtheseView protheseView= new ProtheseView(protheseTotaleView,prothesePartielleView
			,protheseFixeView);
	
	RegisterView registerView= new RegisterView();
    ChangeMotPassView changeMotPassView= new ChangeMotPassView();

	public HomePanel card1= new HomePanel(registerView,changeMotPassView);
	
	public PatientView card2= new PatientView(infoPatient,actPatient,odfPatient,
			protheseView,ordonance);

	public GestionStockView card3= new GestionStockView();

	public RendezVousView card4= new RendezVousView();

    public JustificationAbsenceView card5= new JustificationAbsenceView();
    
    public OrdonanceMenuView card6= new OrdonanceMenuView();
    
    public ExamenComplimentaireView card7= new ExamenComplimentaireView();
    
    public InfoDentisteView card8= new InfoDentisteView();

	private MenuBar menuBar= new MenuBar();

	public MainFrame(HomePanel card1,PatientView card2,GestionStockView card3
			,RendezVousView card4,JustificationAbsenceView card5,RegisterView registerView 
			,OrdonanceMenuView card6,ExamenComplimentaireView card7, InfoDentisteView card8
			, MenuBar menuBar
			){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screenSize.width, screenSize.height);
      
		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.menuBar= menuBar;
		this.setJMenuBar(this.menuBar.menu);
		this.registerView= registerView;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cards = new JPanel(new CardLayout());
		this.card1=card1;
		this.card2= card2;
		this.card3= card3;
		this.card4=card4;
		this.card5=card5;
		this.card6=card6;
		this.card7= card7;
		this.card8= card8;
		cards.add(this.card1, "Card 1");
		cards.add(this.card2, "Card 2");
		cards.add(this.card3, "Card 3");
		cards.add(this.card4, "Card 4");
		cards.add(this.card5, "Card 5");
		cards.add(this.card6, "Card 6");
		cards.add(this.card7, "Card 7");
		cards.add(this.card8, "Card 8");


       
		getContentPane().add(cards);
		disableFrameComponents();
		this.pack();
	}
	public void disableFrameComponents(){
		for (java.awt.Component componentIterator:this.menuBar.getMenu().getComponents()){
			componentIterator.setEnabled(false);
		}
		
	}
	public void enableFrameComponents(){
		for (java.awt.Component componentIterator:this.menuBar.getMenu().getComponents()){
			componentIterator.setEnabled(true);
		}
		
	}

}
