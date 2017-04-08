package org.hicham.View;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuListener;

public class MenuBar {

	JMenuBar menu = new JMenuBar();

	JMenu menuouvrir = new JMenu("Fichier");


	JMenuItem quiter = new JMenuItem("Quiter ");

	JMenuItem motpasse = new JMenuItem("Changé Le Mot De passe");

	JMenu menuPatient = new JMenu("Patient");
	JMenuItem infoPatientItem = new JMenuItem("Info Patient ");
	JMenuItem rendezVousPatient = new JMenuItem("Rendez Vous ");

	JMenu ordonance = new JMenu("Ordonance");
	JMenuItem ordonanceItem = new JMenuItem("Ordonnance");
	JMenuItem examenItem = new JMenuItem("Examen Complimentaire");
	JMenuItem justificationItem = new JMenuItem("Justification d'absence");


	JMenu menuGestionStock = new JMenu("Labo");
	JMenuItem gestionStockItem = new JMenuItem("Gestion De Stock ");
	
	JMenu menuDentiste = new JMenu("Dentiste");
	JMenuItem dentisteInfoItem = new JMenuItem("Info Dentiste");

	JMenu retour = new JMenu("Retour");

	JMenu menuapropos = new JMenu("?");

	public MenuBar(){

		menu.setBounds(0, 0, 1370, 30);

		menu.setFont(new Font("sans-serif", Font.PLAIN, 12));

		menuouvrir.add(motpasse);
		menuouvrir.add(quiter);
		
		ordonance.add(ordonanceItem);
		ordonance.add(examenItem);
		ordonance.add(justificationItem);

		menuGestionStock.add(gestionStockItem);
        
		menuPatient.add(infoPatientItem);
		menuPatient.add(rendezVousPatient);
		
		menuDentiste.add(dentisteInfoItem);
		

		menu.add(menuouvrir);
		menu.add(menuPatient);
		menu.add(ordonance);
		menu.add(menuGestionStock);
		menu.add(menuDentiste);
		menu.add(retour);
		menu.add(menuapropos);

	}

	public void addMenuBarActionListener(ActionListener listener){

		this.motpasse.addActionListener(listener);
		this.quiter.addActionListener(listener);

		this.menuPatient.addActionListener(listener);
		
		this.rendezVousPatient.addActionListener(listener);
		this.infoPatientItem.addActionListener(listener);


		this.ordonance.addActionListener(listener);

		this.gestionStockItem.addActionListener(listener);
		
		this.ordonanceItem.addActionListener(listener);
		this.examenItem.addActionListener(listener);
		this.justificationItem.addActionListener(listener);

		this.retour.addActionListener(listener);


		this.menuapropos.addActionListener(listener);
		this.dentisteInfoItem.addActionListener(listener);


	}
	public void addMenuBarMenuListener(MenuListener menuListener){
		this.retour.addMenuListener(menuListener);
	}

	public JMenuBar getMenu() {
		return menu;
	}

	public JMenu getMenuouvrir() {
		return menuouvrir;
	}

	

	public JMenuItem getQuiter() {
		return quiter;
	}

	public JMenuItem getMotpasse() {
		return motpasse;
	}

	public JMenuItem getPatient() {
		return menuPatient;
	}

	public JMenuItem getOrdonance() {
		return ordonance;
	}

	public JMenuItem getGestionStock() {
		return menuGestionStock;
	}


	public JMenuItem getRetour() {
		return retour;
	}

	public JMenuItem getMenuapropos() {
		return menuapropos;
	}

	public JMenuItem getGestionStockItem() {
		return gestionStockItem;
	}

	public JMenuItem getInfoPatientItem() {
		return infoPatientItem;
	}

	public JMenuItem getRendezVousPatient() {
		return rendezVousPatient;
	}

	public JMenuItem getOrdonanceItem() {
		return ordonanceItem;
	}

	public JMenuItem getExamenItem() {
		return examenItem;
	}

	public JMenuItem getJustificationItem() {
		return justificationItem;
	}

	public JMenuItem getDentisteInfoItem() {
		return dentisteInfoItem;
	}
	

}
