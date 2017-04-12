package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
				int input = JOptionPane.showOptionDialog(null
						, "Supprime les informations Dentiste? Ces informations vont etre afficher dans l'ordonnace. "
						, "Supprimer les Infomations"
						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					Dentiste dentiste = dentisteQueries.getdentiste();
					dentisteQueries.delete(dentiste);
					infoDentisteView.setFieldsDisabled(true);
					infoDentisteView.setFieldsEmpty();
					infoDentisteView.getAjout().setEnabled(true);

				}	

			}
		}
		
	}
	public Dentiste getInfoDentiste(){
		
		String nom= infoDentisteView.getNomText().getText();
		String prenom= infoDentisteView.getPrenomtText().getText();
		String arab= infoDentisteView.getArabText().getText();
		String route= infoDentisteView.getRouteText().getText();
		String city= infoDentisteView.getCityText().getText();
		String wilaya= infoDentisteView.getWilayaText().getText();
		String tel= infoDentisteView.getTelextT().getText();	
		Dentiste dentiste= new Dentiste(nom, prenom, arab, route, city, wilaya, tel);
		return dentiste;
	}

}
