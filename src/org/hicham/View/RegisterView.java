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

public class RegisterView extends JInternalFrame {
	
	private JLabel typeUserLab= new JLabel();
	final DefaultComboBoxModel typeUserModel = new DefaultComboBoxModel();
	final JComboBox typeUserCombo = new JComboBox(typeUserModel);    
	private JScrollPane typeUserListScrol = new JScrollPane(typeUserCombo);
	
	private JLabel passwordLab= new JLabel();
	private JTextField password= new JTextField();
	
	private JButton ok =new JButton("Ok");
	private JButton annule =new JButton("Annuler");
	
	
	public RegisterView (){
		this.typeUserModel.addElement("Assistant");
		this.typeUserModel.addElement("Dentiste");
		typeUserCombo.setSelectedIndex(0);
		JPanel panel = new JPanel();
		
		panel.add(annule);
		panel.add(ok);
		panel.add(typeUserLab);
		panel.add(typeUserCombo);
		panel.add(password);
		panel.add(passwordLab);	
	
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
