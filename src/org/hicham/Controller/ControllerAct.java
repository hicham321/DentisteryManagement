package org.hicham.Controller;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.hicham.Controller.ControllerProtheseFixe.ProtheseFixeMouseListener;
import org.hicham.Main.Main;
import org.hicham.Model.Act;
import org.hicham.Model.ActQueries;
import org.hicham.Model.ImageAct;
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
import org.hicham.View.ProthesePartielleView;
import org.hicham.View.ProtheseTotaleView;

import com.sun.xml.internal.ws.api.Component;

public class ControllerAct {
	ActPatientView actPatientView= new ActPatientView();
	ActQueries actQueries= new ActQueries();
	
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	PatientQueries patientQueries= new PatientQueries();
	OdfPatient odfPatient= new OdfPatient();
	Ordonance ordonance= new Ordonance();
	InfoPatient infoPatient = new InfoPatient();

    ControllerInfoPatient controllerInfoPatient= new ControllerInfoPatient(infoPatient, patientQueries
    		, actPatientView, odfPatient, protheseFixeView
    		, prothesePartielleView, protheseTotaleView, ordonance);
	


	int returnVal;
	JFileChooser filechooser= new JFileChooser();
	
	List<String>imagePaths= new ArrayList<>();
	Act currentAct= new Act();
	
	List<String> imageOrder= new ArrayList<>();
	List<String> deletedImages= new ArrayList<>();
	List<String> addedImages= new ArrayList<>();
	int selectedImage;
	
	double montantActuel=0;
    
	public ControllerAct(ActPatientView actPatientView
			,ActQueries actQueries, InfoPatient infoPatient
			,ControllerInfoPatient controllerInfoPatient){
		this.actQueries= actQueries;
		this.actPatientView= actPatientView;
		this.infoPatient=infoPatient;
		this.controllerInfoPatient= controllerInfoPatient;
		this.actPatientView.addActActionListener(new ActActionListener() );
	}

	class ActActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource()==actPatientView.getAjoute()) {
				if(!controllerInfoPatient.patientSelected){
					int input = JOptionPane.showOptionDialog(null
							,"Vous n'avez pas selectioné un patient. "
							, "Erreur Patient"
							, JOptionPane.OK_CANCEL_OPTION
							, JOptionPane.INFORMATION_MESSAGE, null, null, null);

					if(input == JOptionPane.OK_OPTION){

					}	
				}else{
					//Check if image panel doesn't contain images 
					int countComponent=0;
					for (java.awt.Component labeliterator:actPatientView.getImagePanel().getComponents()){
						countComponent++;
					}
					if (countComponent==0) {
						int input = JOptionPane.showOptionDialog(null
								,"Vous n'avez pas ajouté des images, continué sans ajouté? "
								, "Ajout d'image"
								, JOptionPane.OK_CANCEL_OPTION
								, JOptionPane.INFORMATION_MESSAGE, null, null, null);

						if(input == JOptionPane.OK_OPTION){
							ajouteAct();
							//refresh combo code
							refreshComboAct();
						}	

					}
					else{
						ajouteAct();
						//refresh combo code
						refreshComboAct();
					}
				}
			}
			if (e.getSource()==actPatientView.getModifie()) {

				int input = JOptionPane.showOptionDialog(null
						, "Enrigestré les modification?"						,"Modifie Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					modifyFieldAct();
					actQueries.addAct(currentAct);
					List<String> oldImageLien= new ArrayList<>();
					List<String> newImagesLien= new ArrayList<>();
					for (int i = 0; i < currentAct.getImageAct().size(); i++) {
						oldImageLien.add(currentAct.getImageAct().get(i).getLien());
						
					}
					for (int i = 0; i < addedImages.size(); i++) {
						try {
							java.net.URL jarLocation = Main.class.getProtectionDomain().getCodeSource().getLocation();
							URL path=new java.net.URL(jarLocation, ".");
							String jarPath=actQueries.CopyFileImage(path.getPath()+"ImageProtheseFixe"
							, addedImages.get(i));
							
							//String newPath=actQueries.CopyFileImage("C:/Users/Hicham/ImagesProtheseFixe", addedImages.get(i));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					actQueries.addNewImages(addedImages, oldImageLien,currentAct);
                    for (int i = 0; i < deletedImages.size(); i++) {
                    	System.out.println(deletedImages.toString());
						actQueries.deleteActImages(deletedImages.get(i));
					}
                    clearImageList();
				}	
			}
			if (e.getSource()==actPatientView.getSupp()) {
				//delete query
				int input = JOptionPane.showOptionDialog(null
						, "Etes vous sure de vouloir supprimer la prothese?"						,"Supprime Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					

					actQueries.deleteAct(currentAct);
					actPatientView.clearImages();
					actPatientView.setEmptyFields();
					clearImageList();
					//refresh combo code
                    refreshComboAct();
				}	
			}
			if (e.getSource()==actPatientView.getAjouteImage()) {
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
						imageOrder.add(lienImage);
						addedImages.add(lienImage);
						
					}catch(Exception ex){
						ex.printStackTrace();
					}

				}

				//show image in panel
			}
			if (e.getSource()==actPatientView.getNouveau()) {
				currentAct= new Act();
				montantActuel=0;
				actPatientView.getAjoute().setEnabled(true);
				actPatientView.clearImages();
				clearImageList();
				actPatientView.setEmptyFields();	
			}
			if (e.getSource()== actPatientView.getListRVCombo()) {
				//set current prothese
				actPatientView.getAjoute().setEnabled(false);
				int selectedDate= actPatientView.getListRVCombo().getSelectedIndex();
				setSelectedActInfo(selectedDate);
				actPatientView.getPayementActuelText().setText("");
				actPatientView.getPayementTotalText().setText(new Double(currentAct.getPayementTotal()).toString());
				montantActuel= currentAct.getPayementActuel();

				actPatientView.clearImages();
				clearImageList();
			}
			if (e.getSource()== actPatientView.getDeleteImage()) {
				int input = JOptionPane.showOptionDialog(null
						, "Etes vous sure de vouloir supprimer l'image?"	,"Supprime Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					int counter=0;
					System.out.println("selected image"+ selectedImage);

					for (java.awt.Component labeliterator:actPatientView.getImagePanel().getComponents()){
						if (counter==selectedImage) {
							System.out.println("counter "+counter+" selected image"+ selectedImage);
							((JLabel) labeliterator).setIcon( null );
							actPatientView.getImagePanel().remove(labeliterator);
							//remove from image order
							deletedImages.add(imageOrder.get(selectedImage));
							imageOrder.remove(selectedImage);
							actPatientView.getImagePanel().revalidate();
							actPatientView.getImagePanel().repaint();
						}
						counter++;

						}
                    
	            	actPatientView.getShowImage().setVisible(false);				
				}	
				
			}
            if (e.getSource()== actPatientView.getAnnuleImage()) {
            	
            	//removing image label from internal frame
            	for (java.awt.Component labelIterator:actPatientView.getPanelShowImage().getComponents()){
            		
            		if (labelIterator instanceof JLabel) {
            			((JLabel) labelIterator).setIcon( null ); 
					}
        			
				}
            	actPatientView.getShowImage().setVisible(false);				
			}
            if (e.getSource()==actPatientView.getAddPay()) {
            	actPatientView.getPayementFrame().setVisible(true);

            }
            if (e.getSource()==actPatientView.getOkPay()) {            	
            	//modify the payement and payement total if changed
            	
            	double payementAjout=new Double(actPatientView.getPayementActuelText().getText());
            	double payementTotal= new Double( actPatientView.getPayementTotalText().getText());
            	double payementRest=actQueries.updatePayement(currentAct,payementAjout,payementTotal);
            	//update the rest label and the total and actual labels
            	//protheseFixeView.getPayementActuel().setText(new Double(currentProtheseFixe.getPayementActuel()).toString());
            	actPatientView.getPayementActuel().setText(new Double(montantActuel+payementAjout).toString());
            	montantActuel=montantActuel+payementAjout;
            	actPatientView.getPayementTotal().setText(new Double(payementTotal).toString());
            	actPatientView.getPayementRest().setText(new Double(payementRest).toString());
            	
            	actPatientView.getPayementFrame().setVisible(false);
            }
            if (e.getSource()==actPatientView.getAnnulePay()) {

            	actPatientView.getPayementFrame().setVisible(false);

			}
		}

	}
	public class ProtheseFixeMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel)e.getSource();            
            //iterate over panel components to get the current label
            int countComponent=0;
            for (java.awt.Component labeliterator:actPatientView.getImagePanel().getComponents()){
            	if (label.equals(labeliterator)) {
					selectedImage=countComponent;
				}
            	countComponent++;
    		}
            String lienFile= imageOrder.get(selectedImage);
            File imageFile= new File(lienFile);
            BufferedImage bfImageLabel=null;
            try{
    			 bfImageLabel = ImageIO.read(imageFile);
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}
    		Image newimg = bfImageLabel.getScaledInstance( actPatientView.getImageLabel().getWidth(), 
    				actPatientView.getImageLabel().getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;
    		bfImageLabel.flush();
    		bfImageLabel = null;
    		actPatientView.getImageLabel().setIcon(new ImageIcon(newimg));
            actPatientView.getPanelShowImage().revalidate();
            actPatientView.getShowImage().setVisible(true);                       
		}
	
		
	}
	public synchronized JLabel setImageInLabel(File imageFile){
		BufferedImage bfImage=null;
		try{
			bfImage = ImageIO.read(imageFile);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Image newimg = bfImage.getScaledInstance( 300, 300,  java.awt.Image.SCALE_SMOOTH ) ;
		JLabel picLabel=new JLabel();
		picLabel.setIcon(new ImageIcon(newimg));
		
		bfImage.flush();
		bfImage = null;
		actPatientView.addActMouseListener(new ProtheseFixeMouseListener(), picLabel);
		
		return picLabel;
	}
	public void makingImageLabels(List<String> liens){
		for (int i = 0; i < liens.size(); i++) {
			//create new label 
			JLabel label= new JLabel("");
			//add image from liens to the label
			File imageFile= new File(liens.get(i));
			label=setImageInLabel(imageFile);
			label.setCursor(new Cursor(Cursor.HAND_CURSOR));

			//add the label dynamically into the panel
			this.actPatientView.getImagePanel().add(label);
		}
		this.actPatientView.getImagePanel().revalidate();

	}
	public void showNewImage(String lien){
		//create new label 

		JLabel label= new JLabel("");
		//add image from liens to the label
		File imageFile= new File(lien);
		label= setImageInLabel(imageFile);
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//add the label dynamically into the panel
		this.actPatientView.getImagePanel().add(label);
		this.actPatientView.getImagePanel().revalidate();
	}
	public void modifyFieldAct(){

		currentAct.setDate(actPatientView.getDatePicker().getDate());  
		String time = new SimpleDateFormat("HH:mm").format(actPatientView.getTimePicker().getValue());		
		currentAct.setTemp(time);
		currentAct.setEntante(actPatientView.getEntente().getText());
		
	}
	public void clearImageList(){
		imagePaths.clear();	
		imageOrder.clear();
		deletedImages.clear();
		addedImages.clear();
	}
	public void setSelectedActInfo(int selectedAct){
		Act act= controllerInfoPatient.getCurrentPatient()
				.getActList().get(selectedAct);
		currentAct= act;
		actPatientView.getEntente().setText(act.getEntante());
		actPatientView.getPayementActuel().setText(new Double(act.getPayementActuel()).toString());
		actPatientView.getPayementTotal().setText(new Double(act.getPayementTotal()).toString());
		String payementReste=new Double(act.getPayementTotal()- act.getPayementActuel()).toString();
		actPatientView.getPayementRest().setText(payementReste);
		actPatientView.getPayementTotalText().setText(new Double(act.getPayementTotal()).toString());
		String dateValue= act.getDate().toString();
		Date date= new Date();
		try {
			 date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actPatientView.getDatePicker().setDate(date);
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date dateObject= new Date();
		try {
		      dateObject = formatter.parse(act.getTemp());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		actPatientView.getTimePicker().setValue(dateObject);

		for (ImageAct image : act.getImageAct() ) {

			//put a different thread for every Image
			new SwingWorker() {
				@Override
				protected Object doInBackground() throws Exception {
					JLabel label= new JLabel("");
					//add image from liens to the label
					File imageFile= new File(image.getLien());
					label= setImageInLabel(imageFile);
					label.setCursor(new Cursor(Cursor.HAND_CURSOR));
					actPatientView.getImagePanel().add(label);
					addImageOrder(image.getLien());
					actPatientView.getImagePanel().revalidate();			
					return null;
				}
				
				
			}.execute();
		}
	}
	public void ajouteAct(){
		//insert images in protheseImages table
		Date date= actPatientView.getDatePicker().getDate();
		String time = new SimpleDateFormat("HH:mm").format(actPatientView.getTimePicker().getValue());		
		String entante= actPatientView.getEntente().getText();
		double payTotal= new Double(actPatientView.getPayementTotalText().getText());
		double payActuel= new Double(actPatientView.getPayementActuelText().getText());

		currentAct= new Act(entante,time, date,payTotal,payActuel);
		currentAct.setPatient(controllerInfoPatient.getCurrentPatient());
		actQueries.addAct(currentAct);
		//iterate over list of paths
		for (int i = 0; i < imageOrder.size(); i++) {
			//copying
			//destination should change when moving to jar file execution
			try {
				java.net.URL jarLocation = Main.class.getProtectionDomain().getCodeSource().getLocation();
				URL path=new java.net.URL(jarLocation, ".");
				
				String newImagePath=actQueries.CopyFileImage(path.getPath() +"ImageProtheseFixe"
						,imageOrder.get(i) );
				ImageAct imageAct= new ImageAct(newImagePath);//needs to change after copying
				imageAct.setAct(currentAct);
				actQueries.addActImage(imageAct);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//clear image panel and set fields empty
		//set images empty
		actPatientView.clearImages();
		clearImageList();
		actPatientView.setEmptyFields();
	}
	public  void addImageOrder(String lien){
		imageOrder.add(lien);
	}
	public void refreshComboAct(){
		int selectedItem=infoPatient.getRechCombo().getSelectedIndex();
		infoPatient.getRechCombo().setSelectedIndex(selectedItem);
	}
	
	
}
