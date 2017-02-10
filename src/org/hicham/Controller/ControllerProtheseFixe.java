package org.hicham.Controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.hicham.Model.ActQueries;
import org.hicham.Model.ImageProtheseFixe;
import org.hicham.Model.MedicamentQueries;
import org.hicham.Model.PatientQueries;
import org.hicham.Model.ProtheseFixe;
import org.hicham.Model.ProtheseFixeQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.InfoPatient;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.ProtheseFixeView;
import org.hicham.View.RecherchePatientView;

import com.sun.xml.internal.ws.api.Component;

public class ControllerProtheseFixe {
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProtheseFixeQueries protheseFixeQueries= new ProtheseFixeQueries();

	ActPatientView actPatientView= new ActPatientView();
	ActQueries actQueries        = new     ActQueries();
	PatientQueries patientQueries= new PatientQueries();
	OdfPatient odfPatient= new OdfPatient();
	Ordonance ordonance= new Ordonance();
	InfoPatient infoPatient = new InfoPatient();

	RecherchePatientView recherchePatientView = new RecherchePatientView();

	ControllerInfoPatient controllerInfoPatient= new ControllerInfoPatient(infoPatient
			,patientQueries,recherchePatientView
			,actPatientView,odfPatient,protheseFixeView,ordonance);


	int returnVal;
	JFileChooser filechooser= new JFileChooser();
	BufferedImage bfImage;
	List<String>imagePaths= new ArrayList<>();
	ProtheseFixe currentProtheseFixe= new ProtheseFixe();
	public ControllerProtheseFixe(ProtheseFixeView protheseFixeView
			,ProtheseFixeQueries protheseFixeQueries
			,ControllerInfoPatient controllerInfoPatient){
		this.protheseFixeQueries= protheseFixeQueries;
		this.protheseFixeView= protheseFixeView;
		this.controllerInfoPatient= controllerInfoPatient;
		this.protheseFixeView.addProtheseFixActionListener(new ProtheseFixActionListener() );
	}

	class ProtheseFixActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource()==protheseFixeView.getAjoute()) {
				int input = JOptionPane.showOptionDialog(null
						,"Vous n'avez pas ajouté des images"
						, ""
						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){

					//insert images in protheseImages table
					String date= protheseFixeView.getDatePicker().getDate().toString();
					String time= protheseFixeView.getTimePicker().getValue().toString();
					String num= protheseFixeView.getNumero().getText();
					String typeProthese= protheseFixeView.getTypeProthese().getText();
					String entante= protheseFixeView.getEntente().getText();

					currentProtheseFixe= new ProtheseFixe(num, entante, typeProthese, time, date);
					currentProtheseFixe.setPatient(controllerInfoPatient.getCurrentPatient());
					protheseFixeQueries.addProtheseFixe(currentProtheseFixe);
					//iterate over list of paths
					for (int i = 0; i < imagePaths.size(); i++) {
						ImageProtheseFixe imageProtheseFixe= new ImageProtheseFixe(imagePaths.get(i));
						imageProtheseFixe.setProtheseFixe(currentProtheseFixe);
						protheseFixeQueries.addProtheseFixeImage(imageProtheseFixe);
					}
					//clear image panel and set fields empty
					//set images empty
					for (java.awt.Component label:protheseFixeView.getImagePanel().getComponents()){
						((JLabel) label).setIcon( null );    
					}
					imagePaths.clear();	
					setEmptyFields();
					
					}	

			}
			if (e.getSource()==protheseFixeView.getModifie()) {
				//update query
				modifyFieldProtheseFixe();
				protheseFixeQueries.addProtheseFixe(currentProtheseFixe);
			}
			if (e.getSource()==protheseFixeView.getSupp()) {
				//delete query
				int input = JOptionPane.showOptionDialog(null
						,"Etes vous sure de vouloir supprimer la prothese?"
						, "Supprime Prothese"
						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){

					protheseFixeQueries.deleteProtheseFixe(currentProtheseFixe);
				}	
			}
			if (e.getSource()==protheseFixeView.getAjouteImage()) {
				//add a new image to image panel
				//insert image into protheseImage
				FileNameExtensionFilter imageFilter = new FileNameExtensionFilter
						("Image files", ImageIO.getReaderFileSuffixes());
				filechooser.addChoosableFileFilter(imageFilter);
				filechooser.setAcceptAllFileFilterUsed(false);	

				returnVal = filechooser.showOpenDialog(null);
				String lienImage="";
				if(returnVal == JFileChooser.APPROVE_OPTION){
					try{
						lienImage=filechooser.getSelectedFile().getPath();
						showNewImage(lienImage);
						imagePaths.add(lienImage);
					}catch(Exception ex){
						ex.printStackTrace();
					}


				}

				//show image in panel
			}
			if (e.getSource()==protheseFixeView.getNouveau()) {
				for (java.awt.Component label:protheseFixeView.getImagePanel().getComponents()){
					((JLabel) label).setIcon( null );    
				}
				setEmptyFields();
				
			}
			if (e.getSource()== protheseFixeView.getListRVCombo()) {
				int selectedDate= protheseFixeView.getListRVCombo().getSelectedIndex();
				setSelectedProtheseFixeInfo(selectedDate);
			}
		}

	}
	public JLabel setImageInLabel(File imageFile){

		try{
			bfImage = ImageIO.read(imageFile);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Image newimg = bfImage.getScaledInstance( 300, 300,  java.awt.Image.SCALE_SMOOTH ) ;
		JLabel picLabel=new JLabel();
		picLabel.setIcon(new ImageIcon(newimg));

		return picLabel;
	}
	public void makingImageLabels(List<String> liens){
		for (int i = 0; i < liens.size(); i++) {
			//create new label 
			JLabel label= new JLabel("");
			//add image from liens to the label
			File imageFile= new File(liens.get(i));
			label=setImageInLabel(imageFile);
			//add the label dynamically into the panel
			this.protheseFixeView.getImagePanel().add(label);
		}
		this.protheseFixeView.getImagePanel().revalidate();

	}
	public void showNewImage(String lien){
		//create new label 

		JLabel label= new JLabel("");
		//add image from liens to the label
		File imageFile= new File(lien);
		label= setImageInLabel(imageFile);
		//add the label dynamically into the panel
		this.protheseFixeView.getImagePanel().add(label);
		this.protheseFixeView.getImagePanel().revalidate();
	}
	public void setEmptyFields(){
		protheseFixeView.getTimePicker().setValue(new Date());
		protheseFixeView.getEntente().setText("");
		protheseFixeView.getTypeProthese().setText("");
		protheseFixeView.getNumero().setText("");
		protheseFixeView.getDatePicker().setDate(new Date());
	}
	public void modifyFieldProtheseFixe(){
		
		currentProtheseFixe.setDate(protheseFixeView.getDatePicker().getDate().toString());     
		currentProtheseFixe.setTemp(protheseFixeView.getTimePicker().getValue().toString());
		currentProtheseFixe.setEntante(protheseFixeView.getEntente().getText());
		currentProtheseFixe.setTypeProthese(protheseFixeView.getTypeProthese().getText());
		currentProtheseFixe.setNumero(protheseFixeView.getNumero().getText());
	}
	public void setSelectedProtheseFixeInfo(int selectedProtheseFixe){
		ProtheseFixe protheseFixe= controllerInfoPatient.getCurrentPatient()
				.getProtheseFixes().get(selectedProtheseFixe);
		protheseFixeView.getEntente().setText(protheseFixe.getEntante());
		protheseFixeView.getNumero().setText(protheseFixe.getNumero());
		protheseFixeView.getTypeProthese().setText(protheseFixe.getTypeProthese());
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date date= new Date();
		try {
			 date = format.parse(protheseFixe.getDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		protheseFixeView.getDatePicker().setDate(date);
		
		for (ImageProtheseFixe image : protheseFixe.getImageProtheseFixe() ) {

			//put a different thread for every Image
			new Thread(new Runnable() {
				public void run() {
					try{

						JLabel label= new JLabel("");
						//add image from liens to the label
						File imageFile= new File(image.getLien());
						label= setImageInLabel(imageFile);
						protheseFixeView.getImagePanel().add(label);
						protheseFixeView.getImagePanel().revalidate();

					}catch(Exception ex){
						ex.printStackTrace();
					}
				}	
			}).start();
		}
	}


}
