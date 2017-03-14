package org.hicham.Controller;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.hicham.Controller.ControllerProtheseFixe.ProtheseFixActionListener;
import org.hicham.Controller.ControllerProtheseFixe.ProtheseFixeMouseListener;
import org.hicham.Model.ActQueries;
import org.hicham.Model.ImageProtheseFixe;
import org.hicham.Model.ImageProtheseTotale;
import org.hicham.Model.PatientQueries;
import org.hicham.Model.ProtheseFixe;
import org.hicham.Model.ProtheseFixeQueries;
import org.hicham.Model.ProtheseTotale;
import org.hicham.Model.ProtheseTotaleQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.InfoPatient;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.ProtheseFixeView;
import org.hicham.View.ProthesePartielleView;
import org.hicham.View.ProtheseTotaleView;
import org.hicham.View.RecherchePatientView;

public class ControllerProtheseTotale {
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	ProtheseTotaleQueries protheseTotaleQueries= new ProtheseTotaleQueries();
	
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ActPatientView actPatientView= new ActPatientView();
	ActQueries actQueries        = new     ActQueries();
	PatientQueries patientQueries= new PatientQueries();
	OdfPatient odfPatient= new OdfPatient();
	Ordonance ordonance= new Ordonance();
	InfoPatient infoPatient = new InfoPatient();

	RecherchePatientView recherchePatientView = new RecherchePatientView();

	ControllerInfoPatient controllerInfoPatient= new ControllerInfoPatient(infoPatient
			,patientQueries,recherchePatientView
			,actPatientView,odfPatient,protheseFixeView,prothesePartielleView,protheseTotaleView,ordonance);


	int returnVal;
	JFileChooser filechooser= new JFileChooser();
	
	List<String>imagePaths= new ArrayList<>();
	ProtheseTotale currentProtheseTotale= new ProtheseTotale();
	
	List<String> imageOrder= new ArrayList<>();
	List<String> deletedImages= new ArrayList<>();
	List<String> addedImages= new ArrayList<>();
	int selectedImage;
	
	double montantActuel=0;
	public ControllerProtheseTotale(ProtheseTotaleView protheseTotaleView
			,ProtheseTotaleQueries protheseTotaleQueries
			,ControllerInfoPatient controllerInfoPatient){
		this.protheseTotaleQueries= protheseTotaleQueries;
		this.protheseTotaleView= protheseTotaleView;
		this.controllerInfoPatient= controllerInfoPatient;
		this.protheseTotaleView.addProtheseTotaleActionListener(new ProtheseTotaleActionListener() );
	}
	class ProtheseTotaleActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource()==protheseTotaleView.getAjoute()) {
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
					for (java.awt.Component labeliterator:protheseTotaleView.getImagePanel().getComponents()){
						countComponent++;
					}
					if (countComponent==0) {
						int input = JOptionPane.showOptionDialog(null
								,"Vous n'avez pas ajouté des images, continué sans ajouté? "
								, "Ajout d'image"
								, JOptionPane.OK_CANCEL_OPTION
								, JOptionPane.INFORMATION_MESSAGE, null, null, null);

						if(input == JOptionPane.OK_OPTION){
							ajouteProthese();
							//refresh combo code
						}	

					}
					else{
						ajouteProthese();
						//refresh combo code
					}
				}
			}
			if (e.getSource()==protheseTotaleView.getModifie()) {

				int input = JOptionPane.showOptionDialog(null
						, "Enrigestré les modification?"						,"Modifie Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					modifyFieldProtheseFixe();
					protheseTotaleQueries.addProtheseTotale(currentProtheseTotale);
					List<String> oldImageLien= new ArrayList<>();
					List<String> newImagesLien= new ArrayList<>();
					for (int i = 0; i < currentProtheseTotale.getImageProtheseTotale().size(); i++) {
						oldImageLien.add(currentProtheseTotale.getImageProtheseTotale().get(i).getLien());
						
					}
					for (int i = 0; i < addedImages.size(); i++) {
						String newPath=protheseTotaleQueries.CopyFileImage("C:/Users/Hicham/ImagesProtheseFixe", addedImages.get(i));

					}
					protheseTotaleQueries.addNewImages(addedImages, oldImageLien,currentProtheseTotale);
                    for (int i = 0; i < deletedImages.size(); i++) {
                    	System.out.println(deletedImages.toString());
						protheseTotaleQueries.deleteProtheseImages(deletedImages.get(i));
					}
                    clearImageList();
				}	
			}
			if (e.getSource()==protheseTotaleView.getSupp()) {
				//delete query
				int input = JOptionPane.showOptionDialog(null
						, "Etes vous sure de vouloir supprimer la prothese?"						,"Supprime Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					

					protheseTotaleQueries.deleteProtheseTotale(currentProtheseTotale);
					protheseTotaleView.clearImages();
					protheseTotaleView.setEmptyFields();
					clearImageList();
					//refresh combo code

				}	
			}
			if (e.getSource()==protheseTotaleView.getAjouteImage()) {
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
			if (e.getSource()==protheseTotaleView.getNouveau()) {
				currentProtheseTotale= new ProtheseTotale();
				montantActuel=0;
				protheseTotaleView.getAjoute().setEnabled(true);
				protheseTotaleView.clearImages();
				clearImageList();
				protheseTotaleView.setEmptyFields();	
			}
			if (e.getSource()== protheseTotaleView.getListRVCombo()) {
				//set current prothese
				protheseTotaleView.getAjoute().setEnabled(false);
				int selectedDate= protheseTotaleView.getListRVCombo().getSelectedIndex();
				setSelectedProtheseFixeInfo(selectedDate);
				protheseTotaleView.getPayementActuelText().setText("");
				protheseTotaleView.getPayementTotalText().setText(new Double(currentProtheseTotale.getPayementTotal()).toString());
				montantActuel= currentProtheseTotale.getPayementActuel();

				protheseTotaleView.clearImages();
				clearImageList();
			}
			if (e.getSource()== protheseTotaleView.getDeleteImage()) {
				int input = JOptionPane.showOptionDialog(null
						, "Etes vous sure de vouloir supprimer l'image?"	,"Supprime Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					int counter=0;
					System.out.println("selected image"+ selectedImage);

					for (java.awt.Component labeliterator:protheseTotaleView.getImagePanel().getComponents()){
						if (counter==selectedImage) {
							System.out.println("counter "+counter+" selected image"+ selectedImage);
							((JLabel) labeliterator).setIcon( null );
							protheseTotaleView.getImagePanel().remove(labeliterator);
							//remove from image order
							deletedImages.add(imageOrder.get(selectedImage));
							imageOrder.remove(selectedImage);
							protheseTotaleView.getImagePanel().revalidate();
							protheseTotaleView.getImagePanel().repaint();
						}
						counter++;

						}
                    
	            	protheseTotaleView.getShowImage().setVisible(false);				
				}	
				
			}
            if (e.getSource()== protheseTotaleView.getAnnuleImage()) {
            	
            	//removing image label from internal frame
            	for (java.awt.Component labelIterator:protheseTotaleView.getPanelShowImage().getComponents()){
            		
            		if (labelIterator instanceof JLabel) {
            			((JLabel) labelIterator).setIcon( null ); 
					}
        			
				}
            	protheseTotaleView.getShowImage().setVisible(false);				
			}
            if (e.getSource()==protheseTotaleView.getAddPay()) {
            	protheseTotaleView.getPayementFrame().setVisible(true);

            }
            if (e.getSource()==protheseTotaleView.getOkPay()) {            	
            	//modify the payement and payement total if changed
            	
            	double payementAjout=new Double(protheseTotaleView.getPayementActuelText().getText());
            	double payementTotal= new Double( protheseTotaleView.getPayementTotalText().getText());
            	double payementRest=protheseTotaleQueries.updatePayement(currentProtheseTotale,payementAjout,payementTotal);
            	//update the rest label and the total and actual labels
            	//protheseFixeView.getPayementActuel().setText(new Double(currentProtheseFixe.getPayementActuel()).toString());
            	protheseTotaleView.getPayementActuel().setText(new Double(montantActuel+payementAjout).toString());
            	montantActuel=montantActuel+payementAjout;
            	protheseTotaleView.getPayementTotal().setText(new Double(payementTotal).toString());
            	protheseTotaleView.getPayementRest().setText(new Double(payementRest).toString());
            	
            	protheseTotaleView.getPayementFrame().setVisible(false);
            }
            if (e.getSource()==protheseTotaleView.getAnnulePay()) {

            	protheseTotaleView.getPayementFrame().setVisible(false);

			}
		}

	}
	public class ProtheseTotaleMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel)e.getSource();            
            //iterate over panel components to get the current label
            int countComponent=0;
            for (java.awt.Component labeliterator:protheseTotaleView.getImagePanel().getComponents()){
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
    		Image newimg = bfImageLabel.getScaledInstance( protheseTotaleView.getImageLabel().getWidth(), 
    				protheseTotaleView.getImageLabel().getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;
    		bfImageLabel.flush();
    		bfImageLabel = null;
    		protheseTotaleView.getImageLabel().setIcon(new ImageIcon(newimg));
            protheseTotaleView.getPanelShowImage().revalidate();
            protheseTotaleView.getShowImage().setVisible(true);                       
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
		protheseTotaleView.addProtheseTotaleMouseListener(new ProtheseTotaleMouseListener(), picLabel);
		
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
			this.protheseTotaleView.getImagePanel().add(label);
		}
		this.protheseTotaleView.getImagePanel().revalidate();

	}
	public void showNewImage(String lien){
		//create new label 

		JLabel label= new JLabel("");
		//add image from liens to the label
		File imageFile= new File(lien);
		label= setImageInLabel(imageFile);
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//add the label dynamically into the panel
		this.protheseTotaleView.getImagePanel().add(label);
		this.protheseTotaleView.getImagePanel().revalidate();
	}
	public void modifyFieldProtheseFixe(){

		currentProtheseTotale.setDate(protheseTotaleView.getDatePicker().getDate());  
		String time = new SimpleDateFormat("HH:mm").format(protheseTotaleView.getTimePicker().getValue());		
		currentProtheseTotale.setTemp(time);
		currentProtheseTotale.setEntante(protheseTotaleView.getEntente().getText());
		currentProtheseTotale.setTypeProthese(protheseTotaleView.getTypeProthese().getText());
		currentProtheseTotale.setNumero(protheseTotaleView.getNumero().getText());
		
	}
	public void clearImageList(){
		imagePaths.clear();	
		imageOrder.clear();
		deletedImages.clear();
		addedImages.clear();
	}
	public void setSelectedProtheseFixeInfo(int selectedProtheseTotale){
		ProtheseTotale protheseTotale= controllerInfoPatient.getCurrentPatient()
				.getProtheseTotales().get(selectedProtheseTotale);
		currentProtheseTotale= protheseTotale;
		protheseTotaleView.getEntente().setText(protheseTotale.getEntante());
		protheseTotaleView.getNumero().setText(protheseTotale.getNumero());
		protheseTotaleView.getTypeProthese().setText(protheseTotale.getTypeProthese());
		protheseTotaleView.getPayementActuel().setText(new Double(protheseTotale.getPayementActuel()).toString());
		protheseTotaleView.getPayementTotal().setText(new Double(protheseTotale.getPayementTotal()).toString());
		String payementReste=new Double(protheseTotale.getPayementTotal()- protheseTotale.getPayementActuel()).toString();
		protheseTotaleView.getPayementRest().setText(payementReste);
		protheseTotaleView.getPayementTotalText().setText(new Double(protheseTotale.getPayementTotal()).toString());
		String dateValue= protheseTotale.getDate().toString();
		Date date= new Date();
		try {
			 date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		protheseTotaleView.getDatePicker().setDate(date);
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date dateObject= new Date();
		try {
		      dateObject = formatter.parse(protheseTotale.getTemp());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		protheseTotaleView.getTimePicker().setValue(dateObject);

		for (ImageProtheseTotale image : protheseTotale.getImageProtheseTotale() ) {

			//put a different thread for every Image
			new SwingWorker() {
				@Override
				protected Object doInBackground() throws Exception {
					JLabel label= new JLabel("");
					//add image from liens to the label
					File imageFile= new File(image.getLien());
					label= setImageInLabel(imageFile);
					label.setCursor(new Cursor(Cursor.HAND_CURSOR));
					protheseTotaleView.getImagePanel().add(label);
					addImageOrder(image.getLien());
					protheseTotaleView.getImagePanel().revalidate();			
					return null;
				}
				
				
			}.execute();
		}
	}
	public void ajouteProthese(){
		//insert images in protheseImages table
		Date date= protheseTotaleView.getDatePicker().getDate();
		String time = new SimpleDateFormat("HH:mm").format(protheseTotaleView.getTimePicker().getValue());		
		String num= protheseTotaleView.getNumero().getText();
		String typeProthese= protheseTotaleView.getTypeProthese().getText();
		String entante= protheseTotaleView.getEntente().getText();
		double payTotal= new Double(protheseTotaleView.getPayementTotalText().getText());
		double payActuel= new Double(protheseTotaleView.getPayementActuelText().getText());

		currentProtheseTotale= new ProtheseTotale(num, entante, typeProthese, time, date,payTotal,payActuel);
		currentProtheseTotale.setPatient(controllerInfoPatient.getCurrentPatient());
		protheseTotaleQueries.addProtheseTotale(currentProtheseTotale);
		//iterate over list of paths
		for (int i = 0; i < imageOrder.size(); i++) {
			//copying
			//destination should change when moving to jar file execution
			String newImagePath=protheseTotaleQueries.CopyFileImage("C:/Users/Hicham/ImagesProtheseFixe",imageOrder.get(i) );
			ImageProtheseTotale imageProtheseTotale= new ImageProtheseTotale(newImagePath);//needs to change after copying
			imageProtheseTotale.setProtheseTotale(currentProtheseTotale);
			protheseTotaleQueries.addProtheseTotaleImage(imageProtheseTotale);
		}
		//clear image panel and set fields empty
		//set images empty
		protheseTotaleView.clearImages();
		clearImageList();
		protheseTotaleView.setEmptyFields();
	}
	public  void addImageOrder(String lien){
		imageOrder.add(lien);
	}
	

}
