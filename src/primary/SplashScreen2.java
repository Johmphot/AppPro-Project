// not use

package primary;

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
      Thread.sleep(2500);
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
