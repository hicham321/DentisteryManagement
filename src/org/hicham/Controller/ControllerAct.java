package org.hicham.Controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.accessibility.AccessibleTableModelChange;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.hicham.Model.Act;
import org.hicham.Model.ActQueries;
import org.hicham.Model.Patient;
import org.hicham.Model.PatientQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.InfoPatient;
import org.hicham.View.RecherchePatientView;

import javafx.stage.FileChooser;

public class ControllerAct {

	ActPatientView actPatientView= new ActPatientView();
	ActQueries actQueries        = new     ActQueries();
	PatientQueries patientQueries= new PatientQueries();
	
	
	InfoPatient infoPatient = new InfoPatient();
	RecherchePatientView recherchePatientView = new RecherchePatientView();
	
	ControllerInfoPatient controllerInfoPatient= new ControllerInfoPatient(infoPatient,patientQueries,recherchePatientView);
	
	Act currentAct= new Act();
    
	int returnVal;
	JFileChooser filechooser= new JFileChooser();
	File sourceFileImage;
	String dbPathToFileImage="";
	
	BufferedImage bfImage;
	public ControllerAct(ActPatientView actPatientView,ActQueries actQueries,PatientQueries patientQueries ,ControllerInfoPatient controllerInfoPatient){
		
		this.actPatientView= actPatientView;
		this.actQueries= actQueries;
		this.patientQueries=patientQueries;
		this.controllerInfoPatient= controllerInfoPatient;
		this.actPatientView.addActPatientViewActionListener(new ActPatientViewActionListener() );

	}
	class ActPatientViewActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if(arg0.getSource()==actPatientView.getOk()){
				
				//put act information
				String actText=actPatientView.getActText().getText();
				Date actDate=actPatientView.getDatePicker().getDate();
				String actTemp= actPatientView.getTimePicker().getValue().toString();
				double actPayement= Double.parseDouble(actPatientView.getPayementCombo().getSelectedItem().toString());
                String dbImagePath=dbPathToFileImage;
				currentAct= new Act(actText,actPayement,actDate,actTemp,dbImagePath);
				//setting patient for oneToMany relationship between Patient and Act
				currentAct.setPatient(controllerInfoPatient.getCurrentPatient());
				actQueries.addAct(currentAct);

			}
			if (arg0.getSource()==actPatientView.getOuvrir()) {
				
				FileNameExtensionFilter imageFilter = new FileNameExtensionFilter( "Image files", 
						ImageIO.getReaderFileSuffixes());
				filechooser.addChoosableFileFilter(imageFilter);
				filechooser.setAcceptAllFileFilterUsed(false);	
				
				returnVal = filechooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					//destination file should changed after packaging the Jar  
					String imageDestinationDir="C://Users/Hicham/ImageRadio";
					
					dbPathToFileImage=imageDestinationDir+"/"+CopyFileImage(imageDestinationDir);
					try{
					setImageInPanel(sourceFileImage);
					}catch(Exception ex){
						ex.printStackTrace();
					}
					
					
				}

			}


		}
		
		
        //this copies selected image into the directory and gets the new path of the image
		public String CopyFileImage(String pathTodestinationImageDir){
			String imageName="";
			try{
				sourceFileImage = filechooser.getSelectedFile();

				imageName=sourceFileImage.getName();
				//the path to the destination file should be changed when packaging the Jar file
				File destinationFile = new File(pathTodestinationImageDir);
				FileInputStream fileInputStream = new FileInputStream(sourceFileImage);
				FileOutputStream fileOutputStream = new FileOutputStream( destinationFile);

				int bufferSize;
				byte[] bufffer = new byte[512];
				while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
					fileOutputStream.write(bufffer, 0, bufferSize);
				}
				fileInputStream.close();
				fileOutputStream.close();

			}catch(Exception ex){
				ex.printStackTrace();

			}
			return imageName;
		}
		public void setImageInPanel(File imageFile){
			SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
			    	try{
			    	bfImage = ImageIO.read(imageFile);
			    	}catch(Exception ex){
			    		ex.printStackTrace();
			    	}
					Image newimg = bfImage.getScaledInstance( 300, 300,  java.awt.Image.SCALE_SMOOTH ) ;  
		            JLabel picLabel= new JLabel(new ImageIcon(newimg));
		            actPatientView.getPanelImageAct().add(picLabel);
		    		picLabel.setBounds(0,0 , 300, 300);	
			    }
			});
			
            
		}
	}
}
