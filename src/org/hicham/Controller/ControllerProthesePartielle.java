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

import org.hicham.Main.Main;
import org.hicham.Model.ActQueries;
import org.hicham.Model.ImageProthesePartielle;
import org.hicham.Model.PatientQueries;
import org.hicham.Model.ProthesePartielle;
import org.hicham.Model.ProthesePartielleQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.InfoPatient;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.ProtheseFixeView;
import org.hicham.View.ProthesePartielleView;
import org.hicham.View.ProtheseTotaleView;

public class ControllerProthesePartielle {
	
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProthesePartielleQueries prothesePartielleQueries= new ProthesePartielleQueries();
		
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	ActPatientView actPatientView= new ActPatientView();
	ActQueries actQueries        = new     ActQueries();
	PatientQueries patientQueries= new PatientQueries();
	OdfPatient odfPatient= new OdfPatient();
	Ordonance ordonance= new Ordonance();
	InfoPatient infoPatient = new InfoPatient();


	ControllerInfoPatient controllerInfoPatient= new ControllerInfoPatient(infoPatient
			,patientQueries
			,actPatientView,odfPatient,protheseFixeView,prothesePartielleView,protheseTotaleView,ordonance);


	int returnVal;
	JFileChooser filechooser= new JFileChooser();
	
	List<String>imagePaths= new ArrayList<>();
	ProthesePartielle currentProthesePartielle= new ProthesePartielle();
	
	List<String> imageOrder= new ArrayList<>();
	List<String> deletedImages= new ArrayList<>();
	List<String> addedImages= new ArrayList<>();
	int selectedImage;
	double montantActuel=0;

	
	
	public ControllerProthesePartielle(ProthesePartielleView prothesePartielleView
			,ProthesePartielleQueries prothesePartielleQueries, InfoPatient infoPatient
			,ControllerInfoPatient controllerInfoPatient){
		this.prothesePartielleQueries= prothesePartielleQueries;
		this.prothesePartielleView= prothesePartielleView;
		this.infoPatient= infoPatient;
		this.controllerInfoPatient= controllerInfoPatient;
		this.prothesePartielleView.addProthesePartielleActionListener(new ProthesePartielleActionListener() );
	}
	class ProthesePartielleActionListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource()==prothesePartielleView.getAjoute()) {
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
					for (java.awt.Component labeliterator:prothesePartielleView.getImagePanel().getComponents()){
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
							refreshComboProthese();
						}	

					}
					else{
						ajouteProthese();
						//refresh combo code
						refreshComboProthese();
					}
				}
			}
			if (e.getSource()==prothesePartielleView.getModifie()) {
				//update query

				int input = JOptionPane.showOptionDialog(null
						, "Enrigestré les modification?"						,"Modifie Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					modifyFieldProthesePartielle();
					prothesePartielleQueries.addProthesePartielle(currentProthesePartielle);
					List<String> oldImageLien= new ArrayList<>();
					List<String> newImagesLien= new ArrayList<>();
					for (int i = 0; i < currentProthesePartielle.getImageProthesePartielle().size(); i++) {
						oldImageLien.add(currentProthesePartielle.getImageProthesePartielle().get(i).getLien());
						//code to copy should be put here

					}
					for (int i = 0; i < addedImages.size(); i++) {
						
						try {
							java.net.URL jarLocation = Main.class.getProtectionDomain().getCodeSource().getLocation();
							URL path=new java.net.URL(jarLocation, ".");
							String jarPath=prothesePartielleQueries.CopyFileImage(path.getPath()+"ImageProtheseFixe"
							, addedImages.get(i));
							//String newPath=prothesePartielleQueries.CopyFileImage("C:/Users/Hicham/ImagesProtheseFixe", addedImages.get(i));
						} catch (Exception e1) {
							e1.printStackTrace();
						}

					}
					prothesePartielleQueries.addNewImages(addedImages, oldImageLien,currentProthesePartielle);
					for (int i = 0; i < deletedImages.size(); i++) {
						System.out.println(deletedImages.toString());
						prothesePartielleQueries.deleteProtheseImages(deletedImages.get(i));
					}
					clearImageList();
				}	
			}
			if (e.getSource()==prothesePartielleView.getSupp()) {
				//delete query
				int input = JOptionPane.showOptionDialog(null
						, "Etes vous sure de vouloir supprimer la prothese?"						,"Supprime Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){


					prothesePartielleQueries.deleteProthesePartielle(currentProthesePartielle);
					prothesePartielleView.clearImages();
					prothesePartielleView.setEmptyFields();
					clearImageList();
					//refresh combo code
					refreshComboProthese();
				}	
			}
			if (e.getSource()==prothesePartielleView.getAjouteImage()) {
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
			if (e.getSource()==prothesePartielleView.getNouveau()) {
				currentProthesePartielle= new ProthesePartielle();
				montantActuel=0;
				prothesePartielleView.getAjoute().setEnabled(true);
				prothesePartielleView.clearImages();
				clearImageList();
				prothesePartielleView.setEmptyFields();	
			}
			if (e.getSource()== prothesePartielleView.getListRVCombo()) {
				//set current prothese
				prothesePartielleView.getAjoute().setEnabled(false);
				int selectedDate= prothesePartielleView.getListRVCombo().getSelectedIndex();
				setSelectedProthesePartielleInfo(selectedDate);
				prothesePartielleView.getPayementActuelText().setText("");
				prothesePartielleView.getPayementTotalText().setText(new Double(currentProthesePartielle.getPayementTotal()).toString());
				montantActuel= currentProthesePartielle.getPayementActuel();

				prothesePartielleView.clearImages();
				clearImageList();
			}
			if (e.getSource()== prothesePartielleView.getDeleteImage()) {
				//code to delete the shown image 
				//first get the count 
				//after search for the lien in the imageOrder list
				//after search for the lien in the imagepath and delete it 
				//after that if it's an add than all is good if it's a modify than we need to delete the image from table
				int input = JOptionPane.showOptionDialog(null
						, "Etes vous sure de vouloir supprimer l'image?"	,"Supprime Prothese"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					int counter=0;
					System.out.println("selected image"+ selectedImage);

					for (java.awt.Component labeliterator:prothesePartielleView.getImagePanel().getComponents()){
						if (counter==selectedImage) {
							System.out.println("counter "+counter+" selected image"+ selectedImage);
							((JLabel) labeliterator).setIcon( null );
							prothesePartielleView.getImagePanel().remove(labeliterator);
							//remove from image order
							deletedImages.add(imageOrder.get(selectedImage));
							imageOrder.remove(selectedImage);
							prothesePartielleView.getImagePanel().revalidate();
							prothesePartielleView.getImagePanel().repaint();
						}
						counter++;

						}
                    
					prothesePartielleView.getShowImage().setVisible(false);				
				}	
				
			}
            if (e.getSource()== prothesePartielleView.getAnnuleImage()) {
            	
            	//removing image label from internal frame
            	for (java.awt.Component labelIterator:prothesePartielleView.getPanelShowImage().getComponents()){
            		
            		if (labelIterator instanceof JLabel) {
            			((JLabel) labelIterator).setIcon( null ); 
					}
        			
				}
            	prothesePartielleView.getShowImage().setVisible(false);				
			}
            if (e.getSource()==prothesePartielleView.getAddPay()) {
            	prothesePartielleView.getPayementFrame().setVisible(true);

            }
            if (e.getSource()==prothesePartielleView.getOkPay()) {            	
            	//modify the payement and payement total if changed
            	
            	double payementAjout=new Double(prothesePartielleView.getPayementActuelText().getText());
            	double payementTotal= new Double( prothesePartielleView.getPayementTotalText().getText());
            	double payementRest=prothesePartielleQueries.updatePayement(currentProthesePartielle,payementAjout,payementTotal);
            	//update the rest label and the total and actual labels
            	//protheseFixeView.getPayementActuel().setText(new Double(currentProtheseFixe.getPayementActuel()).toString());
            	prothesePartielleView.getPayementActuel().setText(new Double(montantActuel+payementAjout).toString());
            	montantActuel=montantActuel+payementAjout;
            	prothesePartielleView.getPayementTotal().setText(new Double(payementTotal).toString());
            	prothesePartielleView.getPayementRest().setText(new Double(payementRest).toString());
            	
            	prothesePartielleView.getPayementFrame().setVisible(false);
            }
            if (e.getSource()==prothesePartielleView.getAnnulePay()) {

            	prothesePartielleView.getPayementFrame().setVisible(false);

			}
		}

		
	}
	public class ProthesePartielleMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel)e.getSource();            
            //iterate over panel components to get the current label
            int countComponent=0;
            for (java.awt.Component labeliterator:prothesePartielleView.getImagePanel().getComponents()){
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
    		Image newimg = bfImageLabel.getScaledInstance( prothesePartielleView.getImageLabel().getWidth(), 
    				prothesePartielleView.getImageLabel().getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;
    		bfImageLabel.flush();
    		bfImageLabel = null;
    		prothesePartielleView.getImageLabel().setIcon(new ImageIcon(newimg));
    		prothesePartielleView.getPanelShowImage().revalidate();
    		prothesePartielleView.getShowImage().setVisible(true);                       
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
		prothesePartielleView.addProthesePartielleMouseListener(new ProthesePartielleMouseListener(), picLabel);
		
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
			this.prothesePartielleView.getImagePanel().add(label);
		}
		this.prothesePartielleView.getImagePanel().revalidate();

	}
	public void showNewImage(String lien){
		//create new label 

		JLabel label= new JLabel("");
		//add image from liens to the label
		File imageFile= new File(lien);
		label= setImageInLabel(imageFile);
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//add the label dynamically into the panel
		this.prothesePartielleView.getImagePanel().add(label);
		this.prothesePartielleView.getImagePanel().revalidate();
	}
	public void modifyFieldProthesePartielle(){
		currentProthesePartielle.setDate(prothesePartielleView.getDatePicker().getDate());     
		String time = new SimpleDateFormat("HH:mm").format(protheseFixeView.getTimePicker().getValue());		
		currentProthesePartielle.setTemp(time);
		currentProthesePartielle.setEntante(prothesePartielleView.getEntente().getText());
		currentProthesePartielle.setTypeProthese(prothesePartielleView.getTypeProthese().getText());
		currentProthesePartielle.setNumero(prothesePartielleView.getNumero().getText());
	}
	public void clearImageList(){
		imagePaths.clear();	
		imageOrder.clear();
		deletedImages.clear();
		addedImages.clear();
	}
	public void setSelectedProthesePartielleInfo(int selectedProthesePartielle){
		ProthesePartielle prothesePartielle= controllerInfoPatient.getCurrentPatient()
				.getProthesePartielles().get(selectedProthesePartielle);
		currentProthesePartielle= prothesePartielle;
		prothesePartielleView.getEntente().setText(prothesePartielle.getEntante());
		prothesePartielleView.getNumero().setText(prothesePartielle.getNumero());
		prothesePartielleView.getTypeProthese().setText(prothesePartielle.getTypeProthese());
		prothesePartielleView.getPayementActuel().setText(new Double(prothesePartielle.getPayementActuel()).toString());
		prothesePartielleView.getPayementTotal().setText(new Double(prothesePartielle.getPayementTotal()).toString());
		String payementReste=new Double(prothesePartielle.getPayementTotal()- prothesePartielle.getPayementActuel()).toString();
		prothesePartielleView.getPayementRest().setText(payementReste);
		prothesePartielleView.getPayementTotalText().setText(new Double(prothesePartielle.getPayementTotal()).toString());
		String dateValue= prothesePartielle.getDate().toString();
		Date date= new Date();
		try {
			 date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prothesePartielleView.getDatePicker().setDate(date);
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date dateObject= new Date();
		try {
		      dateObject = formatter.parse(prothesePartielle.getTemp());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		prothesePartielleView.getTimePicker().setValue(dateObject);

		for (ImageProthesePartielle image : prothesePartielle.getImageProthesePartielle() ) {

			//put a different thread for every Image
			new SwingWorker() {
				@Override
				protected Object doInBackground() throws Exception {
					JLabel label= new JLabel("");
					//add image from liens to the label
					File imageFile= new File(image.getLien());
					label= setImageInLabel(imageFile);
					label.setCursor(new Cursor(Cursor.HAND_CURSOR));
					prothesePartielleView.getImagePanel().add(label);
					addImageOrder(image.getLien());
					prothesePartielleView.getImagePanel().revalidate();			
					return null;
				}
				
				
			}.execute();
		}
	}
	public void ajouteProthese(){
		//insert images in protheseImages table
		Date date= prothesePartielleView.getDatePicker().getDate();
		String time = new SimpleDateFormat("HH:mm").format(prothesePartielleView.getTimePicker().getValue());	
		String num= prothesePartielleView.getNumero().getText();
		String typeProthese= prothesePartielleView.getTypeProthese().getText();
		String entante= prothesePartielleView.getEntente().getText();
		double payTotal= new Double(prothesePartielleView.getPayementTotalText().getText());
		double payActuel= new Double(prothesePartielleView.getPayementActuelText().getText());

		currentProthesePartielle= new ProthesePartielle(num, entante, typeProthese, time, date,payTotal,payActuel);
		currentProthesePartielle.setPatient(controllerInfoPatient.getCurrentPatient());
		prothesePartielleQueries.addProthesePartielle(currentProthesePartielle);
		//iterate over list of paths
		for (int i = 0; i < imageOrder.size(); i++) {
			//copying
			//destination should change when moving to jar file execution
			try {
				java.net.URL jarLocation = Main.class.getProtectionDomain().getCodeSource().getLocation();
				URL path=new java.net.URL(jarLocation, ".");
				String newImagePath=prothesePartielleQueries.CopyFileImage(path.getPath()+"ImageProtheseFixe",imageOrder.get(i) );
				ImageProthesePartielle imageProthesePartielle= new ImageProthesePartielle(newImagePath);//needs to change after copying
				imageProthesePartielle.setProthesePartielle(currentProthesePartielle);
				prothesePartielleQueries.addProthesePartielleImage(imageProthesePartielle);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//clear image panel and set fields empty
		//set images empty
		prothesePartielleView.clearImages();
		clearImageList();
		prothesePartielleView.setEmptyFields();
	}
	public  void addImageOrder(String lien){
		imageOrder.add(lien);
	}
	public void refreshComboProthese(){
		int selectedItem=infoPatient.getRechCombo().getSelectedIndex();
		infoPatient.getRechCombo().setSelectedIndex(selectedItem);
	}

}
