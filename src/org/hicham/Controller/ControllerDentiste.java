package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.Model.Dentiste;
import org.hicham.Model.DentisteQueries;
import org.hicham.View.InfoDentisteView;

public class ControllerDentiste {
	
	
	InfoDentisteView infoDentisteView= new InfoDentisteView();
	DentisteQueries dentisteQueries= new DentisteQueries();
	
	public ControllerDentiste(InfoDentisteView infoDentisteView, DentisteQueries dentisteQueries){
		
		this.infoDentisteView=infoDentisteView;
		this.dentisteQueries= dentisteQueries;
		this.infoDentisteView.addInfoDentisteActionListener(new InfoDentisteActionListener());
	}
	class InfoDentisteActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == infoDentisteView.getAjout()) {
				Dentiste dentiste = getInfoDentiste();		
				dentisteQueries.addorUpdateDentiste(dentiste);
			}
			if (e.getSource()== infoDentisteView.getSupp()) {
				
			}
		}
		
	}
	public Dentiste getInfoDentiste(){
		
		String nom= infoDentisteView.getNomText().getText();
		String prenom= infoDentisteView.getPrenomText().getText();
		String arab= infoDentisteView.getArabText().getText();
		String route= infoDentisteView.getRouteText().getText();
		String city= infoDentisteView.getCitytext().getText();
		//String wilaya= infoDentisteView.getNomText().getText();
		String tel= infoDentisteView.getTelephoneText().getText();	
		Dentiste dentiste= new Dentiste(nom, prenom, arab, route, city, "wilaya", tel);
		return dentiste;
	}

}
