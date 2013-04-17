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

public class Weather extends JPanel implements Runnable{
	 URL url = null;
	  String inputLine = "";
	  URLConnection iceWorld;
	  BufferedReader temp;
	  String result;
  //public static void main(String[] args) throws Exception {
	public Weather()
	{
		try 
		{
			url = new URL("http://iceworld.sls-atl.com/api/&cmd=states");
			iceWorld = url.openConnection();
			temp = new BufferedReader(new InputStreamReader(iceWorld.getInputStream()));
			inputLine = temp.readLine().substring(43);
			temp.close();
		}
		catch (MalformedURLException e) {}
		catch (IOException e) {}
	}
  public void run() {
	 
	  result = inputLine.substring(0, inputLine.indexOf(","));
	  System.out.println(result);

	  String scence1 = "\"Sunny\"";
	  String scence2 = "\"Cloudy\"";
	  String scence3 = "\"Raining\"";
	  String scence4 = "\"Snowing\"";
  
	  Graphics g = this.getGraphics();
	  if (result.equalsIgnoreCase(scence1)) {
	   WeatherScence.sunny(g,4000,4000);
	  } else if (result.equalsIgnoreCase(scence2)) {
	   WeatherScence.cloudy(g,4000,4000);
	  } else if (result.equalsIgnoreCase(scence3)) {
	   WeatherScence.raining(g,4000,4000);
	  } else if (result.equalsIgnoreCase(scence4)) {
	   WeatherScence.snowing(g,4000,4000);
	  }
  }
}