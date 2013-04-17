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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class WeatherPanel extends JPanel implements Runnable {

	Image image1, image2, image3, image4; //1=Sunny, 2=Cloudy, 3=Rain, 4=Snow
	Image paintThis;
	Thread weatherThread;
	JLabel txt = new JLabel();
	
	public void init()
	{
		weatherThread = null;
		this.setLayout(new GridLayout(1,1,0,0));
		//load image
	}
	
	public void paintComponent(Graphics g) 
	{
		g.setColor(Color.BLACK);
		g.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		super.paintComponent(g);
		//g.drawImage(paintThis,0,0,null);
	}
	
	public void run()
	{
		while(true)
		{
			String inputLine = "";
			String result="";
			try 
			{
				URL url = new URL("http://iceworld.sls-atl.com/api/&cmd=states");
				URLConnection iceWorld = url.openConnection();
				BufferedReader temp = new BufferedReader(new InputStreamReader(iceWorld.getInputStream()));
				inputLine = temp.readLine().substring(44);
				result = inputLine.substring(0, inputLine.indexOf(",")-1);
				temp.close();
			} 
			catch (MalformedURLException e) {} 
			catch (IOException e) {}
			
			if (result.equalsIgnoreCase("Sunny")) 
			{
				//paintThis = image1;
				this.setOpaque(true);
				this.setBackground(Color.yellow);
				txt.setText(result);
			} 
			else if (result.equalsIgnoreCase("Cloudy")) 
			{
				//paintThis = image2;
				this.setOpaque(true);
				this.setBackground(Color.blue);
				txt.setText(result);		
			} 
			else if (result.equalsIgnoreCase("Raining")) 
			{
				//paintThis = image3;
				this.setOpaque(true);
				this.setBackground(Color.lightGray);
				txt.setText(result);
		
			} 
			else if (result.equalsIgnoreCase("Snowing")) 
			{
				//paintThis = image4;
				this.setOpaque(true);
				this.setBackground(Color.white);
				txt.setText(result);
			}
			add(txt);
			repaint();
			try 
			{
				Thread.sleep(240000);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
