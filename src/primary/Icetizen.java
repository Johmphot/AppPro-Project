package primary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

public class Icetizen implements MyIcetizen
{
	int portID, listeningPort;
	String username;
	IcetizenLook look;
	public String uid, ip;
	int port, portID, listeningPort, type, x, y;
	public String username;

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
	
	
	
}