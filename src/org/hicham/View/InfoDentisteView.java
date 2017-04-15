package org.hicham.View;


import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.hicham.Model.Dentiste;

public class InfoDentisteView extends JPanel{

	private javax.swing.JTextField TelextT;
	private javax.swing.JTextField arabText;
	private javax.swing.JTextField cityText;
	private javax.swing.JButton ajout;
	private javax.swing.JButton supp;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JTextField nomText;
	private javax.swing.JTextField prenomtText;
	private javax.swing.JTextField routeText;
	private javax.swing.JTextField wilayaText;





	public InfoDentisteView(){
		
		java.awt.GridBagConstraints gridBagConstraints;
        this.setBackground(Color.WHITE);
		jLabel1 = new javax.swing.JLabel();
		nomText = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		ajout = new javax.swing.JButton();
		supp = new javax.swing.JButton();
		TelextT = new javax.swing.JTextField();
		wilayaText = new javax.swing.JTextField();
		cityText = new javax.swing.JTextField();
		routeText = new javax.swing.JTextField();
		arabText = new javax.swing.JTextField();
		prenomtText = new javax.swing.JTextField();

		setLayout(new java.awt.GridBagLayout());
		
		arabText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		jLabel1.setText("nom");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(49, 46, 0, 0);
		add(jLabel1, gridBagConstraints);


		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.ipadx = 344;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(49, 91, 0, 71);
		add(nomText, gridBagConstraints);

		jLabel2.setText("prenom");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(29, 46, 0, 0);
		add(jLabel2, gridBagConstraints);

		jLabel3.setText("nom et prenom en arabe");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 7;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(22, 46, 0, 0);
		add(jLabel3, gridBagConstraints);

		jLabel4.setText("route");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(24, 46, 0, 0);
		add(jLabel4, gridBagConstraints);

		jLabel5.setText("city");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(28, 46, 0, 0);
		add(jLabel5, gridBagConstraints);

		jLabel6.setText("wilaya");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(30, 46, 0, 0);
		add(jLabel6, gridBagConstraints);

		jLabel7.setText("Telephone");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 9;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(32, 46, 0, 0);
		add(jLabel7, gridBagConstraints);

		ajout.setText("Ok");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 6;
		gridBagConstraints.gridy = 11;
		gridBagConstraints.gridwidth = 8;
		gridBagConstraints.ipadx = 29;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(39, 13, 87, 0);
		add(ajout, gridBagConstraints);

		supp.setText("Supprimer");

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 11;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(39, 91, 87, 0);
		add(supp, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 9;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.ipadx = 344;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(29, 91, 0, 71);
		add(TelextT, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.ipadx = 344;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(24, 91, 0, 71);
		add(wilayaText, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.ipadx = 344;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(22, 91, 0, 71);
		add(cityText, gridBagConstraints);


		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.ipadx = 344;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(18, 91, 0, 71);
		add(routeText, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.ipadx = 344;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(18, 91, 0, 71);
		add(arabText, gridBagConstraints);


		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.ipadx = 344;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(26, 91, 0, 71);
		add(prenomtText, gridBagConstraints);

	}

	public void addInfoDentisteActionListener(ActionListener listener){
		this.ajout.addActionListener(listener);
		this.supp.addActionListener(listener);

	}
	public void setFieldsDisabled(boolean disabled){
		this.arabText.setEnabled(disabled);
		this.cityText.setEnabled(disabled);
		this.nomText.setEnabled(disabled);
		this.prenomtText.setEnabled(disabled);
		this.routeText.setEnabled(disabled);
		this.TelextT.setEnabled(disabled);
		this.wilayaText.setEnabled(disabled);
	}
	public void setFieldsEmpty(){
		this.arabText.setText("");
		this.cityText.setText("");
		this.nomText.setText("");
		this.prenomtText.setText("");
		this.routeText.setText("");
		this.TelextT.setText("");
		this.wilayaText.setText("");
	}
	public void populateDentisteView(Dentiste dentiste){
		this.arabText.setText(dentiste.getNomPrenomArab());
		this.cityText.setText(dentiste.getCity());
		this.nomText.setText(dentiste.getNom());
		this.prenomtText.setText(dentiste.getPrenom());
		this.routeText.setText(dentiste.getRoute());
		this.TelextT.setText(dentiste.getTel());
		this.wilayaText.setText(dentiste.getWilaya());
	}

	
	public javax.swing.JTextField getTelextT() {
		return TelextT;
	}

	public javax.swing.JTextField getArabText() {
		return arabText;
	}

	public javax.swing.JTextField getCityText() {
		return cityText;
	}

	public javax.swing.JButton getAjout() {
		return ajout;
	}

	public javax.swing.JButton getSupp() {
		return supp;
	}

	public javax.swing.JTextField getNomText() {
		return nomText;
	}

	public javax.swing.JTextField getPrenomtText() {
		return prenomtText;
	}

	public javax.swing.JTextField getRouteText() {
		return routeText;
	}

	public javax.swing.JTextField getWilayaText() {
		return wilayaText;
	}
	


}
	