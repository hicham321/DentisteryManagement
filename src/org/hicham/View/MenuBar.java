package org.hicham.View;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar {

	JMenuBar menu = new JMenuBar();

	JMenu menuouvrir = new JMenu("Fichier");

	JMenuItem ajoutbase = new JMenuItem("Ouvrir");

	JMenuItem quiter = new JMenuItem("quiter ");

	JMenuItem motpasse = new JMenuItem("Changé Le Mot De passe");

	JMenuItem patient = new JMenuItem("Patient");

	JMenuItem ordonance = new JMenuItem("Ordonance");

	JMenuItem gestionStock = new JMenuItem("Gestion De Stock");

	JMenuItem labo = new JMenuItem("Labo");

	JMenuItem retour = new JMenuItem("Retour");

	JMenuItem menuapropos = new JMenuItem("?");

	public MenuBar(){

		menu.setBounds(0, 0, 1370, 30);

		menu.setFont(new Font("sans-serif", Font.PLAIN, 12));

		menuouvrir.add(ajoutbase);
		menuouvrir.add(motpasse);
		menuouvrir.add(quiter);

		menu.add(menuouvrir);
		menu.add(patient);
		menu.add(ordonance);
		menu.add(gestionStock);
		menu.add(labo);
		menu.add(retour);
		menu.add(menuapropos);

	}

	public void menuBarActionListener(ActionListener listener){

		this.ajoutbase.addActionListener(listener);
		this.motpasse.addActionListener(listener);
		this.quiter.addActionListener(listener);

		this.patient.addActionListener(listener);

		this.ordonance.addActionListener(listener);

		this.gestionStock.addActionListener(listener);

		this.labo.addActionListener(listener);

		this.retour.addActionListener(listener);

		this.labo.addActionListener(listener);

		this.menuapropos.addActionListener(listener);

	}

	public JMenuBar getMenu() {
		return menu;
	}

	public JMenu getMenuouvrir() {
		return menuouvrir;
	}

	public JMenuItem getAjoutbase() {
		return ajoutbase;
	}

	public JMenuItem getQuiter() {
		return quiter;
	}

	public JMenuItem getMotpasse() {
		return motpasse;
	}

	public JMenuItem getPatient() {
		return patient;
	}

	public JMenuItem getOrdonance() {
		return ordonance;
	}

	public JMenuItem getGestionStock() {
		return gestionStock;
	}

	public JMenuItem getLabo() {
		return labo;
	}

	public JMenuItem getRetour() {
		return retour;
	}

	public JMenuItem getMenuapropos() {
		return menuapropos;
	}

}
