package primary;

import iceworld.given.IcetizenLook;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import org.json.simple.parser.*;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Fetch extends Thread {
	static int size, count=0;
	public String weatherCondition = "";
	public static int REFRESH_INTERVAL=5;
	JSONParser json = new JSONParser();
	LinkedList<Icetizen> user;
	 ContainerFactory containerFactory = new ContainerFactory() {
		    public List creatArrayContainer() { return new LinkedList(); } 
		    public Map createObjectContainer() { return new LinkedHashMap(); }

	 };
	 Map jsonMap, jsonData, weather, iceTizen, jsonUserDetail, jsonUser, lastKnown;
	 
	public static void main(String[] args){
		LinkedList<Icetizen> user = new LinkedList<Icetizen>();
		Fetch a = new Fetch(user);
		a.start();
	}
	 
	 
	public Fetch(LinkedList<Icetizen> user){
		this.user = user;
		this.fetch();
	}
	
	public void fetch() {
		user.clear();
		String inputLine = "";
		try{
		    URL url = new URL(" http://iceworld.sls-atl.com/api/&cmd=states");
		    URLConnection urlc = url.openConnection();
		    BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
		    String buffs;
		    while ((buffs = in.readLine()) != null){
		        inputLine = inputLine + buffs;
		    }
		    in.close();
		 }catch (Exception e){
		     System.err.println("Error");
		 }
		try {
			try {
				jsonMap = (Map)this.json.parse(inputLine, this.containerFactory);
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonData = (Map)jsonMap.get("data");
			weather = (Map)jsonData.get("weather");
			iceTizen = (Map)jsonData.get("icetizen");
			size = iceTizen.size();
			Object [] key = iceTizen.keySet().toArray();
			for(int i=0 ; i<size ; i++){
				
				jsonUserDetail = (Map)iceTizen.get(key[i]);
				jsonUser = (Map)jsonUserDetail.get("user");
				lastKnown = (Map)jsonUserDetail.get("last_known_destination");
				if(Login.myUser.username.equalsIgnoreCase((String)jsonUser.get("username"))){
					Login.myUser.setUID((String)key[i]);
					Login.myUser.type = Integer.parseInt(jsonUser.get("type").toString());
					String d = (String)lastKnown.get("position");
					if(d==null){
						Login.myUser.setPoint(0, 0);
					}else{
					int x = Integer.parseInt(d.substring(1, d.indexOf(',')));
					int y = Integer.parseInt(d.substring(d.indexOf(',')+1,d.indexOf(')')));
					Login.myUser.setPoint(x, y);
					try {
						Login.myUser.fetchLook();
						//System.out.println("=..=");
					} catch (org.json.simple.parser.ParseException e) {
						// TODO Auto-generated catch block
						System.out.print("myuser fetch look mai dai");
					}
					}
					
				}else{
				Icetizen n = new Icetizen();
				n.setUID((String)key[i]);
				n.setUsername((String) jsonUser.get("username"));
				//System.out.println(n.username);
				n.type = Integer.parseInt(jsonUser.get("type").toString());
				n.ip = (String) jsonUser.get("ip");
				n.port = Integer.parseInt(jsonUser.get("port").toString());
				n.portID = Integer.parseInt(jsonUser.get("pid").toString());
				/*try {
					n.fetchLook();
				} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					System.out.print("n fetch look mai dai");
				}*/
				//String time = (String)lastKnown.get("timestamp");
				String d = (String)lastKnown.get("position");
				//System.out.print("D = "+d);
				if(d==null){
					n.setPoint(0, 0);
				}else{
					int x = Integer.parseInt(d.substring(1, d.indexOf(',')));
					int y = Integer.parseInt(d.substring(d.indexOf(',')+1,d.indexOf(')')));
					//System.out.print(x+" aahhh "+y);
					n.setPoint(x, y);
				}
				//System.out.println(n.x+" "+n.y);
				user.add(n);
				
				}
			}
			//weatherCondition = (String) weather.get("condition");
				
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
	
	
	public void run() {
		
		try
		{
			while(true)
			{	
				fetch();
				Thread.sleep(REFRESH_INTERVAL*1000);
				//System.out.println("Fetch number "+count++);
				//System.out.println("myuser"+Login.myUser.username+Login.myUser.uid+Login.myUser.p);
				System.out.println("=============================");
				//System.out.println("time"+time);
				for(int i =0;i<user.size();i++){
				System.out.println(user.get(i).getUsername());
				System.out.println("uid:"+user.get(i).uid);
				//System.out.println("port id"+user.get(i).getIcePortID());
				//System.out.println("B"+user.get(i).getIcetizenLook().gidB+"H"+user.get(i).getIcetizenLook().gidH);
				System.out.println("Position"+user.get(i).getPoint());
				}
				System.out.println("_____________________________");
		}}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
