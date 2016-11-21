package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.Model.ProduitQueries;
import org.hicham.View.GestionStockView;

public class ControllerGestionStock {
	
	GestionStockView gestionStockView= new GestionStockView();
	ProduitQueries produitQueries =new ProduitQueries();
	
	public ControllerGestionStock(GestionStockView gestionStockView,ProduitQueries produitQueries){
		
		this.gestionStockView= gestionStockView;
		this.produitQueries=produitQueries;
		this.gestionStockView.addGestionStockActionListener(new GestionStockActionListener());
	}
	
	class GestionStockActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()== gestionStockView.getAjoutProduit()) {
				produitQueries.addProduit(gestionStockView.getTextProduitAjout().getText()
						, Double.parseDouble(gestionStockView.getTextPrixAjout().getText())
						,0);
				setEmptyAjoutProduitComponents();
			}
			if (e.getSource()== gestionStockView.getModifieItem()) {
				
			}
            if (e.getSource()== gestionStockView.getSupItem()) {
				
			}
            if (e.getSource()== gestionStockView.getAjoutQte()) {
				//update query for product row
			}
            if (e.getSource()== gestionStockView.getSousQte()) {
				
			}
            if (e.getSource()== gestionStockView.getProduitCombo()) {
				
			}
            if (e.getSource()== gestionStockView.getChoixBtn()) {
            	        gestionStockView.getPopmenu().show(gestionStockView.getChoixBtn(), 
            			gestionStockView.getChoixBtn().getBounds().x-312,
            			gestionStockView.getChoixBtn().getBounds().y-65 + gestionStockView.getChoixBtn().getBounds().height);
	
			}
			
		}
		public void setEmptyAjoutProduitComponents(){
			gestionStockView.getTextPrixAjout().setText("");
			gestionStockView.getTextProduitAjout().setText("");

		}
		
	}
	
	
	//This is for getting buttons from menu
	//addingquantity.getPopmenu().show(addingquantity.getChoixBtn(), addingquantity.getChoixBtn().getBounds().x-312, addingquantity.getChoixBtn().getBounds().y-65 + addingquantity.getChoixBtn().getBounds().height);

}
