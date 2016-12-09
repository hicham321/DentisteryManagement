package org.hicham.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel  extends JPanel{

	private BufferedImage image;
    private JLabel picLabel;

	public HomePanel() {
		try{

			this.setLayout( null);
			this.setBackground(Color.decode("#d2fdf9"));
			
			image = ImageIO.read(this.getClass().getResource("/resources/Tooth-Background.jpg"));
			Image newimg = image.getScaledInstance( 400, 400,  java.awt.Image.SCALE_SMOOTH ) ;  

			picLabel = new JLabel(new ImageIcon(newimg));
			this.add(picLabel);
			
			picLabel.setBounds(0,0 , 400, 400);	
			}catch(Exception ex){

			ex.printStackTrace();
		}

	}
	
}
