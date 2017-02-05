package org.hicham.Controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.hicham.Model.ImageProtheseFixe;
import org.hicham.Model.ProtheseFixe;
import org.hicham.Model.ProtheseFixeQueries;
import org.hicham.View.ProtheseFixeView;

public class ControllerProtheseFixe {
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
    ProtheseFixeQueries protheseFixeQueries= new ProtheseFixeQueries();
	
    int returnVal;
	JFileChooser filechooser= new JFileChooser();
	BufferedImage bfImage;
    ProtheseFixe CurrentProtheseFixe= new ProtheseFixe();
	public ControllerProtheseFixe(ProtheseFixeView protheseFixeView
			,ProtheseFixeQueries protheseFixeQueries){
		this.protheseFixeQueries= protheseFixeQueries;
		this.protheseFixeView= protheseFixeView;
		this.protheseFixeView.addProtheseFixActionListener(new ProtheseFixActionListener() );
	}
	
    class ProtheseFixActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()==protheseFixeView.getAjoute()) {
				//insert query
				
			}
			if (e.getSource()==protheseFixeView.getModifie()) {
				//update query
				
			}
			if (e.getSource()==protheseFixeView.getSupp()) {
				//delete query
			}
			if (e.getSource()==protheseFixeView.getAjouteImage()) {
				//add a new image to image panel
				//insert image into protheseImage
				FileNameExtensionFilter imageFilter = new FileNameExtensionFilter
						("Image files", ImageIO.getReaderFileSuffixes());
				filechooser.addChoosableFileFilter(imageFilter);
				filechooser.setAcceptAllFileFilterUsed(false);	

				returnVal = filechooser.showOpenDialog(null);
				String lienImage= "";

				if(returnVal == JFileChooser.APPROVE_OPTION){
					try{
						lienImage=filechooser.getSelectedFile().getPath();
		                showNewImage(lienImage);
					}catch(Exception ex){
						ex.printStackTrace();
					}


				}
                /*ImageProtheseFixe imageProtheseFixe= new ImageProtheseFixe(lienImage);
                imageProtheseFixe.setProtheseFixe(CurrentProtheseFixe);
                protheseFixeQueries.addProtheseFixeImage(imageProtheseFixe);*/
                
                //show image in panel
			}
			if (e.getSource()==protheseFixeView.getNouveau()) {
				
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
        System.out.println("label added to panel");
	}
	public void setEmptyFields(){
		protheseFixeView.getEntente().setText("");
		protheseFixeView.getTypeProthese().setText("");
		protheseFixeView.getTimePicker().setValue(null);
		
	}

}
