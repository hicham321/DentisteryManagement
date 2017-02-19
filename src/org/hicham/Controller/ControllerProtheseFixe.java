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
	
	List<String> imageOrder= new ArrayList<>();
	List<String> deletedImages= new ArrayList<>();
	List<String> addedImages= new ArrayList<>();
	int selectedImage;
    
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
				//Check if image panel doesn't contain images 
				int countComponent=0;
				for (java.awt.Component labeliterator:protheseFixeView.getImagePanel().getComponents()){
					countComponent++;
				}
				if (countComponent==0) {
					int input = JOptionPane.showOptionDialog(null
							,"Vous n'avez pas ajouté des images, continué sans ajouté? "
							, ""
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
			if (e.getSource()==protheseFixeView.getModifie()) {
				//update query

				int input = JOptionPane.showOptionDialog(null
						, "Modifie Prothese"						,"Enrigestré les modification?"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					modifyFieldProtheseFixe();
					protheseFixeQueries.addProtheseFixe(currentProtheseFixe);
					List<String> oldImageLien= new ArrayList<>();
					for (int i = 0; i < currentProtheseFixe.getImageProtheseFixe().size(); i++) {
						oldImageLien.add(currentProtheseFixe.getImageProtheseFixe().get(i).getLien());
					}
					protheseFixeQueries.comparePathLists(imagePaths, oldImageLien,currentProtheseFixe);
					//clearImages();
                    for (int i = 0; i < deletedImages.size(); i++) {
						protheseFixeQueries.deleteProtheseImages(deletedImages.get(i));
						System.out.println(deletedImages.get(i)+ "to be deleted");
					}
					//protheseFixeQueries.deleteFoundImageProtheseFixe(imageOrder.get(selectedImage));
				}	
			}
			if (e.getSource()==protheseFixeView.getSupp()) {
				//delete query
				int input = JOptionPane.showOptionDialog(null
						, "Supprime Prothese"						,"Etes vous sure de vouloir supprimer la prothese?"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					

					protheseFixeQueries.deleteProtheseFixe(currentProtheseFixe);
					clearImages();
					//refresh combo code

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
				clearImages();
				setEmptyFields();	
			}
			if (e.getSource()== protheseFixeView.getListRVCombo()) {
				//set current prothese
				int selectedDate= protheseFixeView.getListRVCombo().getSelectedIndex();
				setSelectedProtheseFixeInfo(selectedDate);
				clearImages();
			}
			if (e.getSource()== protheseFixeView.getDeleteImage()) {
				//code to delete the shown image 
				//first get the count 
				//after search for the lien in the imageOrder list
				//after search for the lien in the imagepath and delete it 
				//after that if it's an add than all is good if it's a modify than we need to delete the image from table
				int input = JOptionPane.showOptionDialog(null
						, "Supprime Prothese"						,"Etes vous sure de vouloir supprimer l'image?"

						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					int counter=0;
					System.out.println("selected image"+ selectedImage);

					for (java.awt.Component labeliterator:protheseFixeView.getImagePanel().getComponents()){
						if (counter==selectedImage) {
							System.out.println("counter "+counter+" selected image"+ selectedImage);
							((JLabel) labeliterator).setIcon( null );
							protheseFixeView.getImagePanel().remove(labeliterator);
							//remove from image order
							imageOrder.remove(selectedImage);
							protheseFixeView.getImagePanel().revalidate();
							protheseFixeView.getImagePanel().repaint();
						}
						counter++;

						}
                    
					//protheseFixeQueries.deleteProtheseFixe(currentProtheseFixe);
					deletedImages.add(imageOrder.get(selectedImage));
				}	
				
			}
            if (e.getSource()== protheseFixeView.getAnnuleImage()) {
            	
            	//removing image label from internal frame
            	for (java.awt.Component labelIterator:protheseFixeView.getPanelShowImage().getComponents()){
            		
            		if (labelIterator instanceof JLabel) {
            			((JLabel) labelIterator).setIcon( null ); 
					}
        			
				}
            	protheseFixeView.getShowImage().setVisible(false);				
			}
		}

	}
	public class ProtheseFixeMouseListener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel)e.getSource();            
            //iterate over panel components to get the current label
            int countComponent=0;
            for (java.awt.Component labeliterator:protheseFixeView.getImagePanel().getComponents()){
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
    		Image newimg = bfImageLabel.getScaledInstance( protheseFixeView.getImageLabel().getWidth(), 
    				protheseFixeView.getImageLabel().getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;
    		protheseFixeView.getImageLabel().setIcon(new ImageIcon(newimg));
            protheseFixeView.getPanelShowImage().revalidate();
            protheseFixeView.getShowImage().setVisible(true);                       
		}
	
		
	}
	public synchronized JLabel setImageInLabel(File imageFile){

		try{
			bfImage = ImageIO.read(imageFile);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Image newimg = bfImage.getScaledInstance( 300, 300,  java.awt.Image.SCALE_SMOOTH ) ;
		JLabel picLabel=new JLabel();
		picLabel.setIcon(new ImageIcon(newimg));
		
		
		protheseFixeView.addProtheseFixeMouseListener(new ProtheseFixeMouseListener(), picLabel);
		
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
		
		currentProtheseFixe.setDate(protheseFixeView.getDatePicker().getDate());     
		currentProtheseFixe.setTemp(protheseFixeView.getTimePicker().getValue().toString());
		currentProtheseFixe.setEntante(protheseFixeView.getEntente().getText());
		currentProtheseFixe.setTypeProthese(protheseFixeView.getTypeProthese().getText());
		currentProtheseFixe.setNumero(protheseFixeView.getNumero().getText());
	}
	public void clearImages(){
	    for (java.awt.Component label:protheseFixeView.getImagePanel().getComponents()){
			protheseFixeView.getImagePanel().remove(label);
		}
		protheseFixeView.getImagePanel().revalidate();
		protheseFixeView.getImagePanel().repaint();
		imagePaths.clear();	
		imageOrder.clear();
		deletedImages.clear();
		addedImages.clear();
	}
	public void setSelectedProtheseFixeInfo(int selectedProtheseFixe){
		ProtheseFixe protheseFixe= controllerInfoPatient.getCurrentPatient()
				.getProtheseFixes().get(selectedProtheseFixe);
		currentProtheseFixe= protheseFixe;
		protheseFixeView.getEntente().setText(protheseFixe.getEntante());
		protheseFixeView.getNumero().setText(protheseFixe.getNumero());
		protheseFixeView.getTypeProthese().setText(protheseFixe.getTypeProthese());
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date date= new Date();
		/*try {
			 date = format.parse(protheseFixe.getDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		protheseFixeView.getDatePicker().setDate(date);
		
		for (ImageProtheseFixe image : protheseFixe.getImageProtheseFixe() ) {

			//put a different thread for every Image
			new SwingWorker() {
				@Override
				protected Object doInBackground() throws Exception {
					JLabel label= new JLabel("");
					//add image from liens to the label
					File imageFile= new File(image.getLien());
					label= setImageInLabel(imageFile);
					label.setCursor(new Cursor(Cursor.HAND_CURSOR));
					protheseFixeView.getImagePanel().add(label);
					addImageOrder(image.getLien());
					protheseFixeView.getImagePanel().revalidate();			
					return null;
				}
				
				
			}.execute();
		}
	}
	public void ajouteProthese(){
		//insert images in protheseImages table
		Date date= protheseFixeView.getDatePicker().getDate();
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
		clearImages();
		setEmptyFields();
	}
	public  void addImageOrder(String lien){
		imageOrder.add(lien);
	}
	
	
}
