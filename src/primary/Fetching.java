package primary;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Fetching extends Thread{
	public String weatherCondition = "";
	JSONParser json = new JSONParser();
	LinkedList<Icetizen> user;
	ContainerFactory containerFactory = new ContainerFactory() {
		public List creatArrayContainer() { return new LinkedList(); }
		public Map createObjectContainer() { return new LinkedHashMap(); }

	};
	static Map<String,Integer> uidPlace = new HashMap<String,Integer>();
	public static int REFRESH_INTERVAL = 5;


	public Fetching(LinkedList<Icetizen> user){
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
			Map jsonMap = (Map)this.json.parse(inputLine, this.containerFactory);
			Map jsonData = (Map)jsonMap.get("data");
			//Map weather = (Map)jsonData.get("weather");
			Map iceTizen = (Map)jsonData.get("icetizen");
			int size = iceTizen.size();
			Object [] key = iceTizen.keySet().toArray();
			int partition = size/4;
			CountDownLatch latch = new CountDownLatch(4);
			for(int i=0 ; i<3 ; i++){
				if(partition>=0) new FetchUsers(partition*i,partition*(i+1)-1,iceTizen,key,latch);
			}
			new FetchUsers(partition*3,size-1,iceTizen,key,latch);
			try {
				latch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//weatherCondition = (String) weather.get("condition");

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		System.out.println("fetch");
		for(int i=0 ; i<user.size() ; i++){
			//System.out.println(i);
			/*System.out.println(user.get(i).getUsername());
			System.out.println(user.get(i).getUID());
			System.out.println(user.get(i).getIcePortID());
			System.out.println(user.get(i).getPoint());
			System.out.println(user.get(i).getNewPoint());*/
		}
	}

	class FetchUsers implements Runnable{
		int start,end;
		Map iceTizen;
		Object[] key;
		protected Thread mainThread;
		CountDownLatch latch;

		public FetchUsers(int start, int end, Map iceTizen, Object [] key,CountDownLatch latch){
			this.start = start;
			this.end = end;
			this.iceTizen = iceTizen;
			this.key = key;
			this.latch = latch;
			start();
		}

		public void start(){
			mainThread =new Thread(this);
			mainThread.start();
		}

		public void run(){
			for(int i = start ; i<=end ; i++){
				Icetizen n = new Icetizen((String) key[i]);
				Map jsonUserDetail = (Map)iceTizen.get(key[i]);
				Map jsonUser = (Map)jsonUserDetail.get("user");
				Map lastKnown = (Map)jsonUserDetail.get("last_known_destination");
				n.username = (String) jsonUser.get("username");
				//System.out.print("print user "+jsonUser.get("username"));
				n.setUsername(n.username);
				n.type = Integer.parseInt(jsonUser.get("type").toString());
				n.ip = (String) jsonUser.get("ip");
				n.port = Integer.parseInt(jsonUser.get("port").toString());
				n.portID = Integer.parseInt(jsonUser.get("pid").toString());
				String d = (lastKnown.get("position")==null) ? null: lastKnown.get("position").toString();
				n.x = (d==null) ? (int)(Math.random()*100) : Integer.parseInt(d.substring(1, d.indexOf(',')));
				n.y = (d==null) ? (int)(Math.random()*100) : Integer.parseInt(d.substring(d.indexOf(',')+1,d.indexOf(')')));
				n.setPoint(n.x, n.y);
				uidPlace.put((String)key[i],user.size());
				if(Login.username.equalsIgnoreCase(n.username)){
					Login.myUser = new Icetizen((String)key[i]);
				}
				user.add(n);
				
			}
			latch.countDown();
		}
	}

	public String getTime(){
		String inputLine = "";
		String time = "";
		try{
			URL url = new URL(" http://iceworld.sls-atl.com/api/&cmd=time");
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
			Map jsonMap = (Map)this.json.parse(inputLine, this.containerFactory);
			time = (String) jsonMap.get("data");
		}catch(ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return time;
	}

	public void action(){
		String inputLine = "";
		String time= getTime();
		try{
			URL url = new URL(" http://iceworld.sls-atl.com/api/&cmd=actions&from="+"1366109045"/*time*/);
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
			Map jsonMap = (Map)this.json.parse(inputLine, this.containerFactory);
			LinkedList<Map> jsonData = (LinkedList<Map>)jsonMap.get("data");
			for(int j=0; j<jsonData.size();j++){
				Map firstdata = (Map)jsonData.get(j);
				String uid = ((String) firstdata.get("uid"));
				int type = Integer.parseInt((String) firstdata.get("action_type").toString());
				if(uidPlace.get(uid)==null) continue;
				int i= Integer.parseInt(uidPlace.get(uid).toString());
				String d = (String) firstdata.get("detail");
				if(type==1){
					user.get(i).newX = (d==null) ? (int)(Math.random()*100) : Integer.parseInt(d.substring(1, d.indexOf(',')));
					user.get(i).newY = (d==null) ? (int)(Math.random()*100) : Integer.parseInt(d.substring(d.indexOf(',')+1,d.indexOf(')')));
					user.get(i).setPoint(user.get(i).newX, user.get(i).newY);
				}else if(type==2){
					user.get(i).talk = (String)firstdata.get("detail");
				}else{
					user.get(i).yell = (String)firstdata.get("detail");
				}
			}
		}catch(ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String [] args){
		LinkedList<Icetizen> user = new LinkedList<Icetizen>();
		Fetching fetch = new Fetching(user);
		for(int i=0 ; i<user.size() ; i++){
			//System.out.println(i);
			System.out.println(user.get(i).getUsername());
			System.out.println(user.get(i).getUID());
			System.out.println(user.get(i).getIcePortID());
			System.out.println(user.get(i).getPoint());
			System.out.println(user.get(i).getNewPoint());
		}
		//System.out.println(fetch.getTime());
		
		//fetch.action();
		//int i = Integer.parseInt(uidPlace.get("46").toString());
		/*System.out.println(i);
		System.out.println(user.get(i).talk);*/
	}

	@Override
	public void run() {
		while(true)
		{
		LinkedList<Icetizen> user = new LinkedList<Icetizen>();
		Fetching a= new Fetching(user);
		if(Login.myUser.look==null) Login.myUser.setIcetizenLook(user.get(Integer.parseInt(Login.myUser.getUID())).getIcetizenLook());
		if(Login.myUser.look!=null) Login.myUser.setImage();
		try {
			a.sleep(REFRESH_INTERVAL*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}