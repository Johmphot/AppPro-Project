import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class ICEWorldPeek 
{
	public static void main(String[] args) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("-----ICE World Peek-----");
		int num;
		 do
		 {
			System.out.println("HTTP Request");
			requestList();
			System.out.print("Select: ");
			num = Integer.parseInt(in.readLine());
			String arg, inputLine;
			URL url = new URL("http://iceworld.sls-atl.com/");
			switch (num) 
			{
				case 1:
					url = new URL("http://iceworld.sls-atl.com/api/&cmd=time");
					break;
				case 2:
					url = new URL("http://iceworld.sls-atl.com/api/&cmd=states");
					break;
				case 3:
					System.out.print("actions from: ");
					arg = in.readLine();
					url = new URL("http://iceworld.sls-atl.com/api/&cmd=actions&from="+ arg);
					break;
				case 4:
					System.out.print("uid: ");
					arg = in.readLine();
					url = new URL("http://iceworld.sls-atl.com/api/&cmd=gresources&uid="+ arg);
					break;
				case 5:
					System.out.print("gid: ");
					arg = in.readLine();
					url = new URL("http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+ arg);
					break;
			}
			if (num!=0) 
			{
				URLConnection iceWorld = url.openConnection();
				BufferedReader temp = new BufferedReader(new InputStreamReader(iceWorld.getInputStream()));
				while ((inputLine = temp.readLine()) != null) 
				{
					System.out.println(inputLine);
				}
				temp.close();
				System.out.println("");
			}
		}
		while(num!=0);
	}
	
	public static void requestList()
	{
		System.out.println("1.Time");
		System.out.println("2.States");
		System.out.println("3.Actions");
		System.out.println("4.gResources");
		System.out.println("5.gurl");
	}
	
}
