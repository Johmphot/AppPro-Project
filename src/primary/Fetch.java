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
	JSONParser json = new JSONParser();
	LinkedList<Icetizen> user;
	 ContainerFactory containerFactory = new ContainerFactory() {
		    public List creatArrayContainer() { return new LinkedList(); } 
		    public Map createObjectContainer() { return new LinkedHashMap(); }

	 };
	 Map jsonMap, jsonData, weather, iceTizen, jsonUserDetail, jsonUser, lastKnown;
	 String time, d;
	 
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
				Icetizen n = new Icetizen();
				jsonUserDetail = (Map)iceTizen.get(key[i]);
				jsonUser = (Map)jsonUserDetail.get("user");
				lastKnown = (Map)jsonUserDetail.get("last_known_destination");
				if(Login.myUser.username.equalsIgnoreCase((String)jsonUser.get("username"))){
					Login.myUser.setUID((String)key[i]);
					Login.myUser.type = Integer.parseInt(jsonUser.get("type").toString());
					try {
						Login.myUser.fetchLook();
						//System.out.println("=..=");
					} catch (org.json.simple.parser.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}else{
				n.setUID((String)key[i]);
				
				n.setUsername((String) jsonUser.get("username"));
				//System.out.println(n.username);
				n.type = Integer.parseInt(jsonUser.get("type").toString());
				n.ip = (String) jsonUser.get("ip");
				n.port = Integer.parseInt(jsonUser.get("port").toString());
				n.portID = Integer.parseInt(jsonUser.get("pid").toString());
				//time = lastKnown.get("timestamp").toString();
				//d = lastKnown.get("position").toString();
				//n.x = Integer.parseInt(d.substring(1, d.indexOf(',')));
				//n.y = Integer.parseInt(d.substring(d.indexOf(',')+1,d.indexOf(')')));
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
			fetch();
			Thread.sleep(5000);
			//System.out.println("Fetch number "+count++);
			System.out.println("=============================");
			//System.out.println("time"+time);
			for(int i =0;i<user.size();i++){
			System.out.println(user.get(i).getUsername());
			System.out.println("uid:"+user.get(i).uid);
			System.out.println("port id"+user.get(i).getIcePortID());
			//System.out.println("B"+user.get(i).getIcetizenLook().gidB+"H"+user.get(i).getIcetizenLook().gidH);
			
		}}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
