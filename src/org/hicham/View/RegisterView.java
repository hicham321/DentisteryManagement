package org.hicham.View;

import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.sun.prism.paint.Color;

public class RegisterView extends JInternalFrame {
	
	private JLabel typeUserLab= new JLabel("Utilisateur: ");
	final DefaultComboBoxModel typeUserModel = new DefaultComboBoxModel();
	final JComboBox typeUserCombo = new JComboBox(typeUserModel);    
	private JScrollPane typeUserListScrol = new JScrollPane(typeUserCombo);
	
	private JLabel passwordLab= new JLabel("Mot De Passe: ");
	private JTextField password= new JTextField();
	
	private JButton ok =new JButton("Ok");
	private JButton annule =new JButton("Annuler");
	
	
	public RegisterView (){
		
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.typeUserModel.addElement("");
		this.typeUserModel.addElement("Assistant");
		this.typeUserModel.addElement("Dentiste");
		typeUserCombo.setSelectedIndex(0);
		
		this.setSize(400, 200);
		this.setTitle("Utilisateur Et Mot De Passe: ");
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		panel.add(annule);
		panel.add(ok);
		panel.add(typeUserLab);
		panel.add(typeUserCombo);
		panel.add(password);
		panel.add(passwordLab);	
		panel.setBackground(java.awt.Color.WHITE);

		this.typeUserLab.setBounds(30,30 , 120,20 );
		this.typeUserCombo.setBounds(180,30 , 120,20 );
		this.passwordLab.setBounds(30,90 , 120,20 );
		this.password.setBounds(180,90 , 120,20 );
		this.ok.setBounds(30, 140, 120,20 );
		this.annule.setBounds(180,140 , 120,20 );
		
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(panel);
		
	    this.setVisible(true);
	}
	
	public void addRegisterViewActionListener(ActionListener listener){
		this.ok.addActionListener(listener);
		this.annule.addActionListener(listener);
	}

	public JButton getOk() {
		return ok;
	}

	public JButton getAnnule() {
		return annule;
	}
	
}
