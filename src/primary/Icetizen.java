package primary;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

public class Icetizen implements MyIcetizen
{

	public static IcetizenLook look;
	public String uid, ip;
	int port, portID, listeningPort, type, x, y;
	public String username;
	BufferedImage img;

	@Override
	
	public int getIcePortID() 
	{
		return portID;
	}

	@Override
	public IcetizenLook getIcetizenLook()
	{	
		return look;
	}

	public IcetizenLook getLook(String uid){
		String inputLine;
	    URL url = null;
		url= new URL("http://iceworld.sls-atl.com/api/&cmd=gresources&uid="+uid);
		//{"status":1,"data":["B001","B002","B003","B004","B005","B006","B007",......
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((inputLine = temp.readLine()) != null) 
		{		
			
		}
	}
	
	@Override
	public int getListeningPort() 
	{
		return listeningPort;
	}

	@Override
	public String getUsername() 
	{
		return username;
	}

	@Override
	public void setIcePortID(int id) 
	{
		portID = id;
	}

	@Override
	public void setIcetizenLook(IcetizenLook look) 
	{
		this.look = look;
		Login.immigration.customization(look);
	}

	@Override
	public void setListeningPort(int arg0) 
	{
		listeningPort = arg0;
	}

	@Override
	public void setUsername(String arg0) 
	{
		username = arg0;
	}
	
	public void setUID(String uid){
		this.uid=uid;
	}
	
	public void setImage()
	{
		img = new BufferedImage(400,500, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		Image imgB, imgH, imgS, imgW;
		IcetizenLook look = primary.Login.user.getIcetizenLook();
		int body = Integer.parseInt(look.gidB.substring(1));
		int head = Integer.parseInt(look.gidH.substring(1));
		int shirt = Integer.parseInt(look.gidS.substring(1));
		int weapon = Integer.parseInt(look.gidW.substring(1));
		try
		{
			Customize.getGraphicsArray();
			imgB = Customize.getBody(body).getImage();
			imgH = Customize.getHead(head).getImage();
			imgS = Customize.getShirt(shirt).getImage();
			imgW = Customize.getWeapon(weapon).getImage();
	
			g.drawImage(imgB, 0, 0, null);
			g.drawImage(imgH, 0, 0, null);
			g.drawImage(imgS, 0, 0, null);
			g.drawImage(imgW, 0, 0, null);
		}
		catch(IOException e){}
	}
	public void setImage(Image imgB,Image imgH,Image imgS,Image imgW)
	{
		img = new BufferedImage(400,500, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		g.drawImage(imgB, 0, 0, null);
		g.drawImage(imgH, 0, 0, null);
		g.drawImage(imgS, 0, 0, null);
		g.drawImage(imgW, 0, 0, null);
		
	}
	
	public Image getImage()
	{
		return img;
	}
	
	
	
}