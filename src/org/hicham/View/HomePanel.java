package org.hicham.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDesktopPane;


public class HomePanel  extends JDesktopPane{

	private Image image;
    RegisterView registerView= new RegisterView();
    ChangeMotPassView changeMotPassView= new ChangeMotPassView();
    
    
	public HomePanel(RegisterView registerView,ChangeMotPassView changeMotPassView ) {
		
		try{
			this.registerView= registerView;
			this.changeMotPassView= changeMotPassView;
			this.setLayout( null);
			this.setBackground(Color.decode("#d2fdf9"));
			
			Image image = ImageIO.read(this.getClass().getResource("/resources/Tooth-Background.jpg"));
			//Image newimg = image.getScaledInstance( 1400, 750,  java.awt.Image.SCALE_SMOOTH ) ;  
			this.image =  image.getScaledInstance( 1400, 750,  java.awt.Image.SCALE_SMOOTH ) ;  
			//
			this.registerView.setVisible(true);
			this.registerView.toFront();
			this.registerView.setSelected(true);
			Dimension jInternalFrameSize = this.registerView.getSize();
			
			this.registerView.setLocation((1400 - jInternalFrameSize.width)/2,
			    (750- jInternalFrameSize.height)/2);
			this.add(this.registerView);
			
			//
			this.changeMotPassView.toFront();
			this.changeMotPassView.setSelected(true);
			Dimension jInternalFrameSiz = this.changeMotPassView.getSize();
			
			this.changeMotPassView.setLocation((1400 - jInternalFrameSiz.width)/2,
			    (750- jInternalFrameSiz.height)/2);
			this.add(this.changeMotPassView);
			
			
			}catch(Exception ex){

			ex.printStackTrace();
		}

	}
	 @Override
     protected void paintComponent(Graphics grphcs) {
         super.paintComponent(grphcs);
         grphcs.drawImage(image, 0, 0, null);
     }

     @Override
     public Dimension getPreferredSize() {
         return new Dimension(1400, 750);
     }
	
}
