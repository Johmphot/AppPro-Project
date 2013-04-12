package Primary;
import iceworld.given.*;

public class Icetizen implements MyIcetizen
{
	int portID, listeningPort;
	String username;

	public int getIcePortID() 
	{
		return portID;
	}

	public IcetizenLook getIcetizenLook()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getListeningPort() 
	{
		return listeningPort;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setIcePortID(int id) 
	{
		portID = id;
	}

	public void setIcetizenLook(IcetizenLook arg0) 
	{
		// TODO Auto-generated method stub

	}

	public void setListeningPort(int arg0) 
	{
		listeningPort = arg0;
	}

	public void setUsername(String arg0) 
	{
		username = arg0;
	}

}