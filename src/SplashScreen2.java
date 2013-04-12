<<<<<<< HEAD
import java.awt.*;
import javax.swing.*;

public class SplashScreen extends JWindow {

	public SplashScreen() {
		super();
		// Get dimension of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// Instantiate TransparentBGScreen(JComponent) and provide it with
		// screen dimension and pointer to this SplashScreen(JWindow)
		TransparentBGScreen bg = new TransparentBGScreen(this, dim);
		// Add TransparentBGScreen to this SplashScreen(JWindow)
		getContentPane().add(bg);
		pack();
		// Show splash screen
		setVisible(true);
		// Wait for a while. In this case, 1100 milliseconds
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
		}
		// Dispose this SplashScreen(JWindow)
		dispose();
	}

	class TransparentBGScreen extends JComponent {
		private Image background;
		private ImageIcon splash;
		private int screenWidth;
		private int screenHeight;
		private int imgWidth;
		private int imgHeight;

		public TransparentBGScreen(SplashScreen window, Dimension screenDim) {
			this.screenWidth = (int) screenDim.getWidth();
			this.screenHeight = (int) screenDim.getHeight();
			// Get splash screen image
			splash = new ImageIcon(getClass().getResource("splash.png"));
			this.imgWidth = splash.getIconWidth();
			this.imgHeight = splash.getIconHeight();
			// Set preferred size of this component(TransparentBGScreen) to be
			// the same as image dimension
			setPreferredSize(new Dimension(imgWidth, imgHeight));
			// Set SplashScreen(JWindow) location to be at the center of the
			// screen
			window.setLocation((screenWidth - imgWidth) / 2,
					(screenHeight - imgHeight) / 2);
			updateBackground();
		}

		/**
		 * This method capture the screen where the splash screen is shown and
		 * store that screen capture to "background"(Image) (to mimic
		 * transparent background of the window)
		 */
		public void updateBackground() {
			try {
				Robot rbt = new Robot();
				background = rbt.createScreenCapture(new Rectangle(
						(screenWidth - imgWidth) / 2,
						(screenHeight - imgHeight) / 2, imgWidth, imgHeight));
			} catch (Exception ex) {
			}
		}

		public void paintComponent(Graphics g) {
			// Paint background which is screen capture first so it will be
			// behind splash screen image
			g.drawImage(background, 0, 0, null);
			// Paint the splash screen image
			g.drawImage(splash.getImage(), 0, 0, null);
		}
	}
}
=======

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class SplashScreen2 {
  public static void main(String[] arg) {
    JWindow jwin = new JWindow();
    Image image= Toolkit.getDefaultToolkit().getImage("iceworld.jpg");
    
    /*InputStream is = getClass().getResourceAsStream("icewolrd.jpg");
        image = ImageIO.read(is);*/
    //ImageIcon splashIcon = new ImageIcon(image);
    JLabel splashLabel = null;
    splashLabel = new JLabel(new ImageIcon(image));
    jwin.getContentPane()
        .add(splashLabel);
    jwin.setBounds(400, 200, 500, 300);
    jwin.setVisible(true);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    jwin.setVisible(false);
    jwin.dispose();

  }
}

/*import java.awt.Image;


public class SplashScreen extends Thread{
	int delay = 6000;
	
	public SplashScreen(){
		Image s = new Image();
	}
	
	public void run() {		
		splash.setVisible(true);
		this.sleep(delay);
		splash.setVsiible(false);
	} 
	

}*/
>>>>>>> SplashScreen
