package org.hicham.Controller;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.hicham.Main.Main;
import org.hicham.Model.Act;
import org.hicham.Model.ActQueries;
import org.hicham.Model.ImageOdf;
import org.hicham.Model.ImageProtheseFixe;
import org.hicham.Model.Odf;
import org.hicham.Model.OdfQueries;
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

public class ControllerOdf{
	OdfPatient odfPatient= new OdfPatient();
    OdfQueries odfQueries= new OdfQueries();
    
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ActPatientView actPatientView= new ActPatientView();
	ActQueries actQueries        = new     ActQueries();
	PatientQueries patientQueries= new PatientQueries();
	Ordonance ordonance= new Ordonance();
	InfoPatient infoPatient = new InfoPatient();


	ControllerInfoPatient controllerInfoPatient= new ControllerInfoPatient(infoPatient, patientQueries
			, actPatientView, odfPatient, protheseFixeView
			, prothesePartielleView, protheseTotaleView, ordonance);


	int returnVal;
	JFileChooser filechooser= new JFileChooser();
	
	List<String>imagePaths= new ArrayList<>();
	Odf currentOdf= new Odf();
	
	List<String> imageOrder= new ArrayList<>();
	List<String> deletedImages= new ArrayList<>();
	List<String> addedImages= new ArrayList<>();
	int selectedImage;
	
	double montantActuel=0;
    
	public ControllerOdf(OdfPatient odfPatient
			,OdfQueries odfQueries,InfoPatient infoPatient
			,ControllerInfoPatient controllerInfoPatient){
		this.odfQueries= odfQueries;
		this.odfPatient= odfPatient;
		this.infoPatient= infoPatient;
		this.controllerInfoPatient= controllerInfoPatient;
		this.odfPatient.addOdfActionListener(new OdfActionListener() );
	}

	class OdfActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource()==odfPatient.getAjoute()) {
				
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
					for (java.awt.Component labeliterator:odfPatient.getImagePanel().getComponents()){
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
							refreshComboOdf();
							System.out.println("selected patient again");
						}	

					}
					else{
						ajouteProthese();
						//refresh combo code
						refreshComboOdf();
						System.out.println("selected patient again");

					}
				}
			}
			if (e.getSource()==odfPatient.getModifie()) {

				int input = JOptionPane.showOptionDialog(null
						, "Enrigestré les modification?"						,"Modifie Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					modifyFieldProtheseFixe();
					odfQueries.addOdf(currentOdf);
					List<String> oldImageLien= new ArrayList<>();
					List<String> newImagesLien= new ArrayList<>();
					for (int i = 0; i < currentOdf.getImageOdf().size(); i++) {
						oldImageLien.add(currentOdf.getImageOdf().get(i).getLien());
						
					}
					for (int i = 0; i < addedImages.size(); i++) {
						try {
							java.net.URL jarLocation = Main.class.getProtectionDomain().getCodeSource().getLocation();
							URL path=new java.net.URL(jarLocation, ".");
							String jarPath=odfQueries.CopyFileImage(path.getPath()+"ImageProtheseFixe"
							, addedImages.get(i));
							//String newPath=odfQueries.CopyFileImage("C:/Users/Hicham/ImagesProtheseFixe", addedImages.get(i));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					odfQueries.addNewImages(addedImages, oldImageLien,currentOdf);
                    for (int i = 0; i < deletedImages.size(); i++) {
                    	System.out.println(deletedImages.toString());
						odfQueries.deleteOdfImages(deletedImages.get(i));
					}
                    clearImageList();
				}	
			}
			if (e.getSource()==odfPatient.getSupp()) {
				//delete query
				int input = JOptionPane.showOptionDialog(null
						, "Etes vous sure de vouloir supprimer la prothese?"						,"Supprime Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					

					odfQueries.deleteOdf(currentOdf);
					odfPatient.clearImages();
					odfPatient.setEmptyFields();
					clearImageList();
					//refresh combo code
					refreshComboOdf();
					System.out.println("selected patient again");
				}	
			}
			if (e.getSource()==odfPatient.getAjouteImage()) {
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
			if (e.getSource()==odfPatient.getNouveau()) {
				currentOdf= new Odf();
				montantActuel=0;
				odfPatient.getAjoute().setEnabled(true);
				odfPatient.clearImages();
				clearImageList();
				odfPatient.setEmptyFields();	
			}
			if (e.getSource()== odfPatient.getListRVCombo()) {
				//set current prothese
				odfPatient.getAjoute().setEnabled(false);
				int selectedDate= odfPatient.getListRVCombo().getSelectedIndex();
				setSelectedProtheseFixeInfo(selectedDate);
				odfPatient.getPayementActuelText().setText("");
				odfPatient.getPayementTotalText().setText(new Double(currentOdf.getPayementTotal()).toString());
				montantActuel= currentOdf.getPayementActuel();

				odfPatient.clearImages();
				clearImageList();
			}
			if (e.getSource()== odfPatient.getDeleteImage()) {
				int input = JOptionPane.showOptionDialog(null
						, "Etes vous sure de vouloir supprimer l'image?"	,"Supprime Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					int counter=0;
					System.out.println("selected image"+ selectedImage);

					for (java.awt.Component labeliterator:odfPatient.getImagePanel().getComponents()){
						if (counter==selectedImage) {
							System.out.println("counter "+counter+" selected image"+ selectedImage);
							((JLabel) labeliterator).setIcon( null );
							odfPatient.getImagePanel().remove(labeliterator);
							//remove from image order
							deletedImages.add(imageOrder.get(selectedImage));
							imageOrder.remove(selectedImage);
							odfPatient.getImagePanel().revalidate();
							odfPatient.getImagePanel().repaint();
						}
						counter++;

						}
                    
	            	odfPatient.getShowImage().setVisible(false);				
				}	
				
			}
            if (e.getSource()== odfPatient.getAnnuleImage()) {
            	
            	//removing image label from internal frame
            	for (java.awt.Component labelIterator:odfPatient.getPanelShowImage().getComponents()){
            		
            		if (labelIterator instanceof JLabel) {
            			((JLabel) labelIterator).setIcon( null ); 
					}
        			
				}
            	odfPatient.getShowImage().setVisible(false);				
			}
            if (e.getSource()==odfPatient.getAddPay()) {
            	odfPatient.getPayementFrame().setVisible(true);

            }
            if (e.getSource()==odfPatient.getOkPay()) {            	
            	//modify the payement and payement total if changed
            	
            	double payementAjout=new Double(odfPatient.getPayementActuelText().getText());
            	double payementTotal= new Double( odfPatient.getPayementTotalText().getText());
            	double payementRest=odfQueries.updatePayement(currentOdf,payementAjout,payementTotal);
            	//update the rest label and the total and actual labels
            	//protheseFixeView.getPayementActuel().setText(new Double(currentProtheseFixe.getPayementActuel()).toString());
            	odfPatient.getPayementActuel().setText(new Double(montantActuel+payementAjout).toString());
            	montantActuel=montantActuel+payementAjout;
            	odfPatient.getPayementTotal().setText(new Double(payementTotal).toString());
            	odfPatient.getPayementRest().setText(new Double(payementRest).toString());
            	
            	odfPatient.getPayementFrame().setVisible(false);
            }
            if (e.getSource()==odfPatient.getAnnulePay()) {

            	odfPatient.getPayementFrame().setVisible(false);

			}
		}

	}
	public class ProtheseFixeMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel)e.getSource();            
            //iterate over panel components to get the current label
            int countComponent=0;
            for (java.awt.Component labeliterator:odfPatient.getImagePanel().getComponents()){
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
    		Image newimg = bfImageLabel.getScaledInstance( odfPatient.getImageLabel().getWidth(), 
    				odfPatient.getImageLabel().getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;
    		bfImageLabel.flush();
    		bfImageLabel = null;
    		odfPatient.getImageLabel().setIcon(new ImageIcon(newimg));
            odfPatient.getPanelShowImage().revalidate();
            odfPatient.getShowImage().setVisible(true);                       
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
		odfPatient.addOdfMouseListener(new ProtheseFixeMouseListener(), picLabel);
		
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
			this.odfPatient.getImagePanel().add(label);
		}
		this.odfPatient.getImagePanel().revalidate();

	}
	public void showNewImage(String lien){
		//create new label 

		JLabel label= new JLabel("");
		//add image from liens to the label
		File imageFile= new File(lien);
		label= setImageInLabel(imageFile);
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//add the label dynamically into the panel
		this.odfPatient.getImagePanel().add(label);
		this.odfPatient.getImagePanel().revalidate();
	}
	public void modifyFieldProtheseFixe(){

		currentOdf.setDate(odfPatient.getDatePicker().getDate());  
		String time = new SimpleDateFormat("HH:mm").format(odfPatient.getTimePicker().getValue());		
		currentOdf.setTemp(time);
		currentOdf.setEntante(odfPatient.getEntente().getText());
		
	}
	public void clearImageList(){
		imagePaths.clear();	
		imageOrder.clear();
		deletedImages.clear();
		addedImages.clear();
	}
	public void setSelectedProtheseFixeInfo(int selectedOdf){
		Odf odf= controllerInfoPatient.getCurrentPatient()
				.getOdfList().get(selectedOdf);
		currentOdf= odf;
		odfPatient.getEntente().setText(odf.getEntante());
		odfPatient.getPayementActuel().setText(new Double(odf.getPayementActuel()).toString());
		odfPatient.getPayementTotal().setText(new Double(odf.getPayementTotal()).toString());
		String payementReste=new Double(odf.getPayementTotal()- odf.getPayementActuel()).toString();
		odfPatient.getPayementRest().setText(payementReste);
		odfPatient.getPayementTotalText().setText(new Double(odf.getPayementTotal()).toString());
		String dateValue= odf.getDate().toString();
		Date date= new Date();
		try {
			 date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		odfPatient.getDatePicker().setDate(date);
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date dateObject= new Date();
		try {
		      dateObject = formatter.parse(odf.getTemp());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		odfPatient.getTimePicker().setValue(dateObject);

		for (ImageOdf image : odf.getImageOdf() ) {

			//put a different thread for every Image
			new SwingWorker() {
				@Override
				protected Object doInBackground() throws Exception {
					JLabel label= new JLabel("");
					//add image from liens to the label
					File imageFile= new File(image.getLien());
					label= setImageInLabel(imageFile);
					label.setCursor(new Cursor(Cursor.HAND_CURSOR));
					odfPatient.getImagePanel().add(label);
					addImageOrder(image.getLien());
					odfPatient.getImagePanel().revalidate();			
					return null;
				}
				
				
			}.execute();
		}
	}
	public void ajouteProthese(){
		//insert images in protheseImages table
		Date date= odfPatient.getDatePicker().getDate();
		String time = new SimpleDateFormat("HH:mm").format(odfPatient.getTimePicker().getValue());		
		String entante= odfPatient.getEntente().getText();
		double payTotal= new Double(odfPatient.getPayementTotalText().getText());
		double payActuel= new Double(odfPatient.getPayementActuelText().getText());

		currentOdf= new Odf( entante, time, date,payTotal,payActuel);
		currentOdf.setPatient(controllerInfoPatient.getCurrentPatient());
		odfQueries.addOdf(currentOdf);
		//iterate over list of paths
		for (int i = 0; i < imageOrder.size(); i++) {
			//copying
			//destination should change when moving to jar file execution
			try {
				java.net.URL jarLocation = Main.class.getProtectionDomain().getCodeSource().getLocation();
				URL path=new java.net.URL(jarLocation, ".");
				String newImagePath=odfQueries.CopyFileImage(path.getPath()+"ImageProtheseFixe",imageOrder.get(i) );
				ImageOdf imageOdf= new ImageOdf(newImagePath);//needs to change after copying
				imageOdf.setOdf(currentOdf);
				odfQueries.addImageOdf(imageOdf);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//clear image panel and set fields empty
		//set images empty
		odfPatient.clearImages();
		clearImageList();
		odfPatient.setEmptyFields();
	}
	public  void addImageOrder(String lien){
		imageOrder.add(lien);
	}
	
	public void refreshComboOdf(){
		
		int selectedItem=infoPatient.getRechCombo().getSelectedIndex();
		infoPatient.getRechCombo().setSelectedIndex(selectedItem);
	}
		
}
