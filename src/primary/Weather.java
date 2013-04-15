package primary;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Weather extends JPanel {

	Image image1, image2, image3, image4; //1=Sunny, 2=Cloudy, 3=Rain, 4=Snow

	public Weather() // image is local
	{
		image1 = Toolkit.getDefaultToolkit().createImage("/Users/milludomrat/Desktop/sunny.jpg");
		image2 = Toolkit.getDefaultToolkit().createImage("/Users/milludomrat/Desktop/cloudy.png");
		image3 = Toolkit.getDefaultToolkit().createImage("/Users/milludomrat/Desktop/rain.jpg");
		image4 = Toolkit.getDefaultToolkit().createImage("/Users/milludomrat/Desktop/snow.png");
	}

	@Override
	/*public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, this);
		}
	}*/

	public static void main(String[] args) throws Exception {
		URL url = null;
		String inputLine = "";
		url = new URL("http://iceworld.sls-atl.com/api/&cmd=states");
		URLConnection iceWorld = url.openConnection();
		BufferedReader temp = new BufferedReader(new InputStreamReader(iceWorld.getInputStream()));
		inputLine = temp.readLine().substring(43);
		String result = inputLine.substring(0, inputLine.indexOf(","));
		System.out.println(result);
		temp.close();

		String scence1 = "\"Sunny\"";
		String scence2 = "\"Cloudy\"";
		String scence3 = "\"Raining\"";
		String scence4 = "\"Snowing\"";
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (image != null) {
				g.drawImage(image, 0, 0, this);
			}
		}

		if (result.equalsIgnoreCase(scence1)) {
			g.drawImage(image, 0, 0 ,this);
			//System.out.println("sun");
		} else if (result.equalsIgnoreCase(scence2)) {
			System.out.println("cloud");
		} else if (result.equalsIgnoreCase(scence3)) {
			System.out.println("rain");
		} else if (result.equalsIgnoreCase(scence4)) {
			System.out.println("snow");
		}
		 
		SwingUtilities.invokeLater(new Runnable() {
		  
		 @Override public void run() { JFrame frame = new JFrame();
		 frame.add(new Weather());
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(4000, 4000); frame.setLocationRelativeTo(null);
		 frame.setVisible(true); } });
		 
	}
	
}
