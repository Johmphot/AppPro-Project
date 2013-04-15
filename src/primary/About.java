package primary;
 import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.*;
import javax.swing.*;

public class About extends JFrame{

	ImagePanel aboutPanel;

	public About(){
		setImage();
		setSize(980, 351);
		setGUI();
	}

	private void setImage() {
		aboutPanel = new ImagePanel("src/aboutpage.png");
	}

	private void setGUI() 
	{
		add(aboutPanel);
	}


	class ImagePanel extends JPanel
	{

		private BufferedImage image;
		String fileUrl;

		public ImagePanel(String fileUrl) 
		{
			this.fileUrl = fileUrl;
			try 
			{                
				image = ImageIO.read(new File(fileUrl));
			} 
			catch (IOException ex) 
			{
				System.out.println("Failed");
			}
		}

		@Override
		protected void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			g.drawImage(image, 0, 0, null);            
		}

	}


}
