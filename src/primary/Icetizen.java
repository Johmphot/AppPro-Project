package primary;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

public class Icetizen implements MyIcetizen
{

	public static IcetizenLook look;
	public String uid, ip;
	int port, portID, listeningPort, type, x, y;
	public String username;
	BufferedImage img;
	static JSONParser json = new JSONParser();
	 static ContainerFactory containerFactory = new ContainerFactory() {
		    public List creatArrayContainer() { return new LinkedList(); } 
		    public Map createObjectContainer() { return new LinkedHashMap(); }

	 };

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
	

	public void fetchLook() throws org.json.simple.parser.ParseException{
		IcetizenLook lookk = new IcetizenLook();
		String inputLine = "";
		try
	    {
	      URL url = new URL("http://iceworld.sls-atl.com/api/&cmd=gresources&uid="+uid);
	      URLConnection urlc = url.openConnection();
	      BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
	      String buffs;
	      while ((buffs = in.readLine()) != null)
	      {
	        inputLine = inputLine + buffs;
	      }
	      in.close();
	    }
	    catch (Exception e)
	    {
	      System.out.println("Error");
	    }
		List<Map> jsonData = null;
		try {
			Map jsonMap = (Map)json.parse(inputLine, containerFactory);
			jsonData = (List<Map>)jsonMap.get("data");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lookk.gidH = ((String) jsonData.get(0).get("H")==null) ? "H008": (String) jsonData.get(0).get("H");
		lookk.gidB = ((String) jsonData.get(0).get("B")==null) ? "B001": (String) jsonData.get(0).get("B");
		lookk.gidW = ((String) jsonData.get(0).get("W")==null) ? "S019": (String) jsonData.get(0).get("W");
		lookk.gidS = ((String) jsonData.get(0).get("S")==null) ? "W050": (String) jsonData.get(0).get("S");
		this.setIcetizenLook(lookk);
		System.out.print(username+look.gidB);
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
	
	public String getUID(){
		return uid;
	}

	@Override
	public void setIcePortID(int id) 
	{
		portID = id;
	}
	

	@Override
	public void setIcetizenLook(IcetizenLook look) 
	{
		this.look=look;
	}

	@Override
	public void setListeningPort(int arg0) 
	{
		listeningPort = arg0;
	}

	@Override
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public void setUID(String uid){
		this.uid=uid;
	}
	
	public void setImage()
	{
		img = new BufferedImage(400,500, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		Image imgB, imgH, imgS, imgW;
		IcetizenLook look = primary.Login.myUser.getIcetizenLook();
		imgB = getComponentImage(look.gidB);
		imgH = getComponentImage(look.gidH);
		imgS = getComponentImage(look.gidS);
		imgW = getComponentImage(look.gidW);
		
		g.drawImage(imgB, 0, 0, null);
		g.drawImage(imgH, 0, 0, null);
		g.drawImage(imgS, 0, 0, null);
		g.drawImage(imgW, 0, 0, null);
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
	
	public Image getComponentImage(String id)
	{
		URL url = null;
		String sreq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+id;
		String bla, linkToImage ="";;
		Image img = null;
		try 
		{
			url= new URL (sreq);
		} 
		catch (MalformedURLException e1) {}
		//{"status":1,"data":{"gid":"B001","location":"graphics\/body\/blue.png"}}
		URLConnection connection = null;
		BufferedReader temp;
		try 
		{
			connection = url.openConnection();
			connection.connect();
			temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((bla = temp.readLine()) != null) 
			{
				
				Map jsonMap = (Map) json.parse(bla, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");
					
				linkToImage = (String) jsonData.get("location");
					
			}
		} 
		catch (IOException e1){}
		catch (org.json.simple.parser.ParseException e1) {}	
		URL slink;
		try 
		{
			slink = new URL("http://iceworld.sls-atl.com/"+linkToImage);
			img = ImageIO.read(slink);
		}
		catch (MalformedURLException e) {}
		catch (IOException e) {}
	
		return img;
	}
	
	
	
}