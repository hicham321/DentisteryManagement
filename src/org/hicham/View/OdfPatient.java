package org.hicham.View;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.text.AbstractDocument;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class OdfPatient extends JPanel {

	JLabel actLab = new JLabel("Diagnostique: ");
	JTextArea actText = new JTextArea();

	JLabel dateRendezVousLab = new JLabel("Rendez Vous: ");

	JLabel payementTotalLab = new JLabel("Payement: ");
	JTextField payementTotal = new JTextField();

	JButton ok = new JButton("");
	JButton modifie = new JButton("");
	JButton supp = new JButton("");

	JXDatePicker datePicker = new JXDatePicker();
	JSpinner timePicker = new JSpinner(new SpinnerDateModel());

	private JButton nouveauAct = new JButton("Nouveau");

	JLabel actComboLab = new JLabel("Rendez Vous: ");
	final DefaultComboBoxModel odfModel = new DefaultComboBoxModel();
	private JComboBox listOdfCombo = new JComboBox(odfModel);
	private JScrollPane actListScrol = new JScrollPane(listOdfCombo);
	//
	//
	JLabel nomPrenomLab = new JLabel("Nom et Prenom: ");
	JLabel nomPrenom = new JLabel("");

	JLabel payementOdfLab = new JLabel("Payement Total: ");
	JTextField payementOdfText = new JTextField();
	JLabel payementRestLab = new JLabel("Le Reste de Payement: ");
	JTextField payementRestText = new JTextField();
	JLabel payementActuelLab = new JLabel("Payement Actuel: ");
	JTextField payementActuelText = new JTextField();


	JScrollPane scrol = new JScrollPane(actText);

	public OdfPatient() {

		this.setLayout(null);
		this.setBackground(Color.decode("#d2fdf9"));
		JPanel panelOdf = new JPanel();
		panelOdf.setBackground(Color.WHITE);
		panelOdf.setLayout(null);
		panelOdf.setBorder(BorderFactory.createTitledBorder("Info Act: "));

		Border border = BorderFactory.createLineBorder(Color.BLACK);
		actText.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 0, 10, 10)));

		actText.setLineWrap(true);
		actText.setWrapStyleWord(true);

		datePicker.setDate(Calendar.getInstance().getTime());
		datePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timePicker, "HH:mm");
		timePicker.setEditor(timeEditor);
		timePicker.setValue(new Date()); // will only show the current time
		
		//put images on Buttons
		putImageIcon("/resources/addImageIcon.png", this.ok);
		putImageIcon("/resources/editImageIcon.jpg", this.modifie);
		putImageIcon("/resources/deleteIcon.png", this.supp);


		AutoCompleteDecorator.decorate(listOdfCombo);
		this.odfModel.addElement("");
		listOdfCombo.setSelectedIndex(0);

		panelOdf.add(datePicker);
		panelOdf.add(timePicker);
		panelOdf.add(ok);
		panelOdf.add(actLab);

		panelOdf.add(actText);
		panelOdf.add(dateRendezVousLab);
		panelOdf.add(payementTotalLab);
		((AbstractDocument) payementTotal.getDocument()).setDocumentFilter(new MyDocumentFilter());
		panelOdf.add(payementTotal);
		panelOdf.add(modifie);
		panelOdf.add(supp);

		panelOdf.add(actComboLab);
		panelOdf.add(listOdfCombo);
		panelOdf.add(nouveauAct);
		//
		//
		((AbstractDocument) payementOdfText.getDocument())
		        .setDocumentFilter(new MyDocumentFilter());
		((AbstractDocument) payementRestText.getDocument())
		        .setDocumentFilter(new MyDocumentFilter());
		((AbstractDocument) payementActuelText.getDocument())
        .setDocumentFilter(new MyDocumentFilter());


		panelOdf.add(nomPrenomLab);
		panelOdf.add(nomPrenom);
		panelOdf.add(payementOdfLab);
		panelOdf.add(payementOdfText);
		panelOdf.add(payementRestLab);
		panelOdf.add(payementRestText);
		panelOdf.add(payementActuelLab);
		panelOdf.add(payementActuelText);

		

		panelOdf.add(scrol);
		scrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		nouveauAct.setBounds(200, 30, 100, 40);
		//
		//
		nomPrenomLab.setBounds(30, 70, 150, 20);
		nomPrenom.setBounds(220, 70, 150, 20);
		payementOdfLab.setBounds(30, 320, 150, 20);
		payementOdfText.setBounds(220, 320, 150, 20);
		payementRestLab.setBounds(30, 360, 150, 20);
		payementRestText.setBounds(220, 360, 150, 20);
		payementActuelLab.setBounds(30, 440, 150, 20);
		payementActuelText.setBounds(220, 440, 150, 20);
		//
		//
		actLab.setBounds(30, 190, 150, 20);
		actText.setBounds(220, 150, 350, 100);
		dateRendezVousLab.setBounds(30, 280, 150, 20);
		datePicker.setBounds(220, 280, 150, 20);
		timePicker.setBounds(400, 280, 150, 20);
		payementTotalLab.setBounds(30, 400, 150, 20);
		payementTotal.setBounds(220, 400, 150, 20);
		ok.setBounds(180, 470, 80, 65);
		modifie.setBounds(350, 470, 80, 65);
		supp.setBounds(520, 470, 80, 65);

		actComboLab.setBounds(30, 110, 150, 20);
		listOdfCombo.setBounds(220, 110, 150, 20);

		panelOdf.setBounds(0, 0, 700, 600);

		this.add(panelOdf);
	}

	public void addActPatientViewActionListener(ActionListener listener) {
		this.ok.addActionListener(listener);
		this.nouveauAct.addActionListener(listener);
		this.listOdfCombo.addActionListener(listener);
		this.modifie.addActionListener(listener);
		this.supp.addActionListener(listener);

	}
	
	//method to put image Icon
	public void putImageIcon(String sourseImagePath,JButton button){
		try {
			Image img = ImageIO.read(getClass().getResource(sourseImagePath));
			Image newimg = img.getScaledInstance( 70, 63,  java.awt.Image.SCALE_SMOOTH ) ;  
			button.setIcon(new ImageIcon(newimg));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// this is a document filter for using only digits in a Jtextfield(related
	// to payement
	class MyDocumentFilter extends DocumentFilter {
		@Override
		public void insertString(DocumentFilter.FilterBypass fp, int offset
				, String string, AttributeSet aset)throws BadLocationException{
			int len = string.length();
			boolean isValidInteger = true;

			for (int i = 0; i < len; i++) {
				if (!Character.isDigit(string.charAt(i))) {
					isValidInteger = false;
					break;
				}
			}
			if (isValidInteger)
				super.insertString(fp, offset, string, aset);
			else
				Toolkit.getDefaultToolkit().beep();
		}

		@Override
		public void replace(DocumentFilter.FilterBypass fp, int offset, int length
				,String string, AttributeSet aset)throws BadLocationException {
			int len = string.length();
			boolean isValidInteger = true;

			for (int i = 0; i < len; i++) {
				if (!Character.isDigit(string.charAt(i))) {
					isValidInteger = false;
					break;
				}
			}
			if (isValidInteger)
				super.replace(fp, offset, length, string, aset);
			else
				Toolkit.getDefaultToolkit().beep();
		}
	}

	public JTextArea getActText() {
		return actText;
	}

	public JButton getOk() {
		return ok;
	}

	public JXDatePicker getDatePicker() {
		return datePicker;
	}

	public JSpinner getTimePicker() {
		return timePicker;
	}

	public JButton getNouveauAct() {
		return nouveauAct;
	}

	public JComboBox getListActCombo() {
		return listOdfCombo;
	}

	public JButton getModifie() {
		return modifie;
	}

	public JTextField getPayementTotal() {
		return payementTotal;
	}

	//
	//

	public JButton getSupp() {
		return supp;
	}

	public JTextField getPayementOdfText() {
		return payementOdfText;
	}

	public JTextField getPayementRestText() {
		return payementRestText;
	}

	public JTextField getPayementActuelText() {
		return payementActuelText;
	}
	

}
