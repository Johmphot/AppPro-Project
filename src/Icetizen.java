
import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

public class Icetizen implements MyIcetizen
{
	int portID, listeningPort;

	@Override
	public int getIcePortID() 
	{
		return portID;
	}

	@Override
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

	@Override
	public String getUsername() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIcePortID(int id) 
	{
		portID = id;
	}

	@Override
	public void setIcetizenLook(IcetizenLook arg0) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setListeningPort(int arg0) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setUsername(String arg0) 
	{
		// TODO Auto-generated method stub

	}

}