package primary;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import org.json.simple.parser.*;

public class Fetch implements Runnable {
	
	public String weatherCondition = "";
	JSONParser json = new JSONParser();
	LinkedList<Icetizen> user;
	 ContainerFactory containerFactory = new ContainerFactory() {
		    public List creatArrayContainer() { return new LinkedList(); } 
		    public Map createObjectContainer() { return new LinkedHashMap(); }

	 };
	 Map jsonMap, jsonData, weather, iceTizen, jsonUserDetail, jsonUser, lastKnown;
	 
	public Fetch(LinkedList<Icetizen> user){
		this.user = user;
		this.fetch();
	}
	
	public void fetch(){
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
			jsonMap = (Map)this.json.parse(inputLine, this.containerFactory);
			jsonData = (Map)jsonMap.get("data");
			weather = (Map)jsonData.get("weather");
			iceTizen = (Map)jsonData.get("icetizen");
			int size = iceTizen.size();
			Object [] key = iceTizen.keySet().toArray();
			for(int i=0 ; i<size ; i++){
				Icetizen n = new Icetizen();
				n.setUID((String)key[i]);
				jsonUserDetail = (Map)iceTizen.get(key[i]);
				jsonUser = (Map)jsonUserDetail.get("user");
				lastKnown = (Map)jsonUserDetail.get("last_known_destination");
				n.username = (String) jsonUser.get("username");
				//System.out.println(n.username);
				n.type = Integer.parseInt(jsonUser.get("type").toString());
				n.ip = (String) jsonUser.get("ip");
				n.port = Integer.parseInt(jsonUser.get("port").toString());
				n.portID = Integer.parseInt(jsonUser.get("pid").toString());
				String d = lastKnown.get("position").toString();
				n.x = Integer.parseInt(d.substring(1, d.indexOf(',')));
				n.y = Integer.parseInt(d.substring(d.indexOf(',')+1,d.indexOf(')')));
				//System.out.println(n.x+" "+n.y);
				user.add(n);
			}
			weatherCondition = (String) weather.get("condition");
				
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
	
	public static void main(String [] args){
		LinkedList<Icetizen> user = new LinkedList<Icetizen>();
		new Fetch(user);
		//while(user.
	}
	
	
	/*int REFRESH_INTERVAL;
	static LinkedList<String> states = new LinkedList<String>();
	JSONParser json = new JSONParser();
	ContainerFactory containerFactory = new ContainerFactory() {
		    public List creatArrayContainer() { return new LinkedList(); } 
		    public Map createObjectContainer() { return new LinkedHashMap(); }

	 };
	 
	 public static void main(String[]args){
		 
		 
	 }
	 
	 public void getStates(LinkedList<String> s){
		 String inputLine = "";
			try
		    {
		      URL url = new URL("http://iceworld.sls-atl.com/api/&cmd=states");
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
			 try
			    {
			      Map jsonMap = (Map)this.json.parse(inputLine, this.containerFactory);
			      //{"status":1,"data":{"weather":{"condition":"Sunny","last_change":1365932400},
			      //"icetizen":{"2058":{"user":{"username":"Yonation","type":0,"ip":"61.90.116.99"
			      //,"port":"0","pid":"246"},"last_known_destination":{"position":"(10,4)
			      Map icetizen = (Map)jsonMap.get("icetizen");
			      /*for(int i=0; i<jsonData.size();i++);
			    	  String temp = jsonData.get(i);
			    	  s.add(temp);
			    	  System.out.print(s.get(1));
			      }
			     }catch (Exception e) {
						 System.out.println("print mai dai ");
					 }


	 }*/
	
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
