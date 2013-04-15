package primary;

import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

public class Icetizen implements MyIcetizen
{
	int portID, listeningPort;
	String username;
	IcetizenLook look;

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
	public void setIcetizenLook(IcetizenLook arg0) 
	{
		look = arg0;
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

}