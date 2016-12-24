package org.hicham.View;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


public class ChangeMotPassView extends JInternalFrame{

	JLabel oldPassLab= new JLabel("Ancien mot de passe: ");
	JPasswordField oldPass= new JPasswordField();
	JLabel newPassLab= new JLabel("Nouveau mot de passe:");
	JPasswordField newPass= new JPasswordField();

	JButton ok= new JButton("Ok");
	JButton annuler= new JButton("Annuler");
	
	JLabel errorLab= new JLabel("*  Incorrect");

	public ChangeMotPassView(){

		this.setSize(400, 200);
		this.setTitle("Changer le mot De Passe");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);

		panel.add(oldPassLab);
		panel.add(oldPass);
		panel.add(newPassLab);
		panel.add(newPass);
		panel.add(ok);
		panel.add(annuler);
		panel.add(errorLab);
		errorLab.setForeground(Color.red);
		errorLab.setVisible(false);

		oldPassLab.setBounds(30,30 , 120,20);
		oldPass.setBounds(180,30 , 120,20);
		newPassLab.setBounds(30,90 , 120,20);
		newPass.setBounds(180,90 , 120,20 );
		ok.setBounds(30, 140, 120,20);
		annuler.setBounds(180,140 , 120,20);
		errorLab.setBounds(305,30 , 120,20);


		this.add(panel);

		this.setVisible(false);	
	}

	public void addChangePassActionListener(ActionListener Listener){
		this.ok.addActionListener(Listener);
		this.annuler.addActionListener(Listener);
	}

	public JPasswordField getOldPass() {
		return oldPass;
	}

	public JPasswordField getNewPass() {
		return newPass;
	}

	public JButton getOk() {
		return ok;
	}

	public JButton getAnnuler() {
		return annuler;
	}

	public JLabel getErrorLab() {
		return errorLab;
	}


}
