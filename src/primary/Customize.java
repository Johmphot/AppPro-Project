
package primary;

import iceworld.given.IcetizenLook;

import java.awt.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import org.json.simple.parser.*;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

public class Customize extends JFrame{
	static IcetizenLook looks= Login.myUser.getIcetizenLook();
	static String[] body, head, shirt, weapon;
	JPanel draw;
	static JSONParser json = new JSONParser();
	static ContainerFactory containerFactory = new ContainerFactory() {
		    public LinkedList creatArrayContainer() { return new LinkedList(); } 
		    public Map createObjectContainer() { return new LinkedHashMap(); }

	 };
	 JLabel bodyLabel = new JLabel();
	 JLabel headLabel = new JLabel();
	 JLabel shirtLabel= new JLabel();
	 JLabel weaponLabel = new JLabel();
	 ImageIcon bodyIcon = new ImageIcon(),headIcon= new ImageIcon(),shirtIcon =new ImageIcon(),weaponIcon= new ImageIcon();
	 static int bodyIndex = 0, headIndex =0, shirtIndex=0, weaponIndex=0;
	 //ImageIcon bodyIcon = new ImageIcon();
	 JButton bDown, bUp, hDown, hUp, sDown, sUp, wDown, wUp,btnCancel, btnOk;
	 ButtonListener b = new ButtonListener();
	 private JLabel lblShirt;
	 private JLabel lblWeapon;
	
	 public static boolean lookChange = false;
	 
	public static void main(String [] args) throws IOException{
		Customize c = new Customize();

		
	}
	
	public static void getLook(){
		//looks.gidB="B101";
		//IcetizenLook look= Login.user.getIcetizenLook();
		//IcetizenLook l= Login.user.getIcetizenLook();
		for(int i=0; i<body.length;i++){
			if(body[i].equalsIgnoreCase(looks.gidB))
				bodyIndex=i;
		}
		for(int i=0; i<head.length;i++){
			if(head[i].equalsIgnoreCase(looks.gidH))
				headIndex=i;
		}
		for(int i=0; i<shirt.length;i++){
			if(shirt[i].equalsIgnoreCase(looks.gidS))
				shirtIndex=i;
		}
		for(int i=0; i<weapon.length;i++){
			if(weapon[i].equalsIgnoreCase(looks.gidW))
				weaponIndex=i;
		}
	}
	
	public static void getGraphicsArray() throws IOException{
		String inputLine;
	    URL url = null;
		url= new URL("http://iceworld.sls-atl.com/api/&cmd=gresources&uid=0");
		//{"status":1,"data":["B001","B002","B003","B004","B005","B006","B007",......
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		int b=0,h=0,s=0,w=0;
		while ((inputLine = temp.readLine()) != null) 
		{
		//System.out.println(inputLine);			
		String datArray = inputLine.substring(inputLine.lastIndexOf("["));
		//System.out.println(datArray);
		for(int i=0;i<datArray.length();i++){
			if(datArray.charAt(i)=='B')
				b++;
			if(datArray.charAt(i)=='H')
				h++;
			if(datArray.charAt(i)=='S')
				s++;
			if(datArray.charAt(i)=='W')
				w++;
		}
		
		System.out.println("w="+w);
		
		body=new String[b];
		head=new String[h];
		shirt=new String[s];
		weapon=new String[w];
		int bcount=0,hcount=0,scount=0,wcount=0;
		
		for(int i=0;i<datArray.length();i++){
			if(datArray.charAt(i)=='B'){
				body[bcount]=datArray.substring(i, i+4);
				bcount++;
			}	
			if(datArray.charAt(i)=='H'){
				head[hcount]=datArray.substring(i, i+4);
				hcount++;
			}
			if(datArray.charAt(i)=='S'){
				shirt[scount]=datArray.substring(i, i+4);
				scount++;
			}
			if(datArray.charAt(i)=='W'){
				weapon[wcount]=datArray.substring(i, i+4);
				wcount++;
			}
		}
		//System.out.println("B"+b+"H"+h+"S"+s+"W"+w);
		/*for(int i=0;i<bcount;i++){
			System.out.print(body[i]+",");
		}
		*/
		}
		
	}
	
	public static ImageIcon getBody(int i) throws IOException{
		//getGraphicsArray();
		URL url = null;
		String breq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+body[i];
		String bla, linkToImage ="";;
		ImageIcon bodyimg;
		url= new URL (breq);
		//{"status":1,"data":{"gid":"B001","location":"graphics\/body\/blue.png"}}
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((bla = temp.readLine()) != null) 
		{
			
			try {
				Map jsonMap = (Map) json.parse(bla, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");
				
				linkToImage = (String) jsonData.get("location");
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}	
			Image image = null;
			URL blink = new URL("http://iceworld.sls-atl.com/"+linkToImage);
			image = ImageIO.read(blink);
			
			bodyimg= new ImageIcon(image);
	
		return bodyimg;
	}
	public static ImageIcon getHead(int i) throws IOException{
		URL url = null;
		String hreq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+head[i];
		String bla, linkToImage ="";;
		ImageIcon headimg;
		url= new URL (hreq);
		//{"status":1,"data":{"gid":"B001","location":"graphics\/body\/blue.png"}}
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((bla = temp.readLine()) != null) 
		{
			
			try {
				Map jsonMap = (Map) json.parse(bla, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");
				
				linkToImage = (String) jsonData.get("location");
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}	
			Image image = null;
			URL hlink = new URL("http://iceworld.sls-atl.com/"+linkToImage);
			image = ImageIO.read(hlink);
			headimg= new ImageIcon(image);
	
		return headimg;
	}
	public static ImageIcon getShirt(int i) throws IOException{
		URL url = null;
		String sreq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+shirt[i];
		String bla, linkToImage ="";
		ImageIcon shirtimg;
		url= new URL (sreq);
		//{"status":1,"data":{"gid":"B001","location":"graphics\/body\/blue.png"}}
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((bla = temp.readLine()) != null) 
		{
			
			try {
				Map jsonMap = (Map) json.parse(bla, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");
				
				linkToImage = (String) jsonData.get("location");
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
			Image image = null;
			URL slink = new URL("http://iceworld.sls-atl.com/"+linkToImage);
			image = ImageIO.read(slink);
			shirtimg= new ImageIcon(image);
	
		return shirtimg;
	}
	public static ImageIcon getWeapon(int i) throws IOException{
		URL url = null;
		String wreq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+weapon[i];
		String bla, linkToImage ="";;
		ImageIcon weaponimg;
		url= new URL (wreq);
		//{"status":1,"data":{"gid":"B001","location":"graphics\/body\/blue.png"}}
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((bla = temp.readLine()) != null) 
		{
			
			try {
				Map jsonMap = (Map) json.parse(bla, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");
				
				linkToImage = (String) jsonData.get("location");
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
			Image image = null;
			URL wlink = new URL("http://iceworld.sls-atl.com/"+linkToImage);
			image = ImageIO.read(wlink);
			weaponimg= new ImageIcon(image);
	
		return weaponimg;
	}

	public Customize() throws IOException 
	{
		setTitle("Customize");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(800,550);
		getContentPane().setLayout(null);
		
		//JLabel body = new JLabel(getBody(1));
		
	    draw = new JPanel();
		draw.setBounds(6, 6, 409, 523);
		getContentPane().add(draw);
		//draw.setSize(200,300);
		draw.setLayout(null);
		
		Customize.getGraphicsArray();
		getLook();
		
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(null);
		bodyPanel.setBounds(427, 26, 235, 55);
		getContentPane().add(bodyPanel);

		bDown = new JButton("<<");
		bDown.addActionListener(b);
		bDown.setBounds(45, 26, 71, 29);
		bodyPanel.add(bDown);

		bUp = new JButton(">>");
		bUp.setBounds(112, 26, 71, 29);
		bUp.addActionListener(b);
		bodyPanel.add(bUp);
		
		JLabel lblBody = new JLabel("Body");
		lblBody.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblBody.setBounds(99, 6, 47, 16);
		bodyPanel.add(lblBody);

		JPanel headPanel = new JPanel();
		headPanel.setLayout(null);
		headPanel.setBounds(427, 106, 235, 55);
		getContentPane().add(headPanel);
		
		hDown = new JButton("<<");
		hDown.setBounds(45, 26, 71, 29);
		hDown.addActionListener(b);
		headPanel.add(hDown);
		
		hUp = new JButton(">>");
		hUp.setBounds(112, 26, 71, 29);
		hUp.addActionListener(b);
		headPanel.add(hUp);
		
		JLabel lblHeadGear = new JLabel("Head Gear");
		lblHeadGear.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblHeadGear.setBounds(75, 6, 80, 16);
		headPanel.add(lblHeadGear);

		JPanel shirtPanel = new JPanel();
		shirtPanel.setLayout(null);
		shirtPanel.setBounds(427, 185, 235, 55);
		getContentPane().add(shirtPanel);
		
		sUp = new JButton(">>");
		sUp.setBounds(112, 26, 71, 29);
		sUp.addActionListener(b);
		shirtPanel.add(sUp);
		
		sDown = new JButton("<<");
		sDown.setBounds(45, 26, 71, 29);
		sDown.addActionListener(b);
		shirtPanel.add(sDown);
		
		lblShirt = new JLabel("Shirt");
		lblShirt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblShirt.setBounds(96, 6, 39, 16);
		shirtPanel.add(lblShirt);

		JPanel weaponPanel = new JPanel();
		weaponPanel.setLayout(null);
		weaponPanel.setBounds(427, 268, 235, 55);
		getContentPane().add(weaponPanel);
		
		wUp = new JButton(">>");
		wUp.setBounds(112, 26, 71, 29);
		wUp.addActionListener(b);
		weaponPanel.add(wUp);
		
		wDown = new JButton("<<");
		wDown.setBounds(45, 26, 71, 29);
		wDown.addActionListener(b);
		weaponPanel.add(wDown);
		
		lblWeapon = new JLabel("Weapon");
		lblWeapon.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblWeapon.setBounds(83, 6, 80, 16);
		weaponPanel.add(lblWeapon);

		btnCancel = new JButton(" Cancel");
		btnCancel.setBounds(427, 373, 107, 27);
		btnCancel.addActionListener(b);
		getContentPane().add(btnCancel);
		

		btnOk = new JButton(" OK");
		btnOk.setBounds(555, 372, 107, 29);
		btnOk.addActionListener(b);
		getContentPane().add(btnOk);
		
		showAll();
		this.setVisible(true);
		
	}
	private void showBody() throws IOException {
		bodyIcon = getBody(bodyIndex);
		bodyLabel.setIcon(bodyIcon);
		bodyLabel.setBounds(10, 10, bodyIcon.getIconWidth(), bodyIcon.getIconHeight());
		draw.add(bodyLabel);
		
	}
	private void showShirt() throws IOException {
		shirtIcon  = getShirt(shirtIndex);
		shirtLabel.setIcon(shirtIcon);
		shirtLabel.setBounds(10, 10, shirtIcon.getIconWidth(), shirtIcon.getIconHeight());
		draw.add(shirtLabel);
		
	}
	private void showHead() throws IOException {
		headIcon  = getHead(headIndex);
		headLabel.setIcon(headIcon);
		headLabel.setBounds(10, 10, headIcon.getIconWidth(), headIcon.getIconHeight());
		draw.add(headLabel);
		
	}
	private void showWeapon() throws IOException {
		weaponIcon  = getWeapon(weaponIndex);
		weaponLabel.setIcon(weaponIcon);
		weaponLabel.setBounds(10, 10, weaponIcon.getIconWidth(), weaponIcon.getIconHeight());
		draw.add(weaponLabel);
		
	}
	public void showAll() throws IOException{
		//draw.removeAll();
		showWeapon();
		showHead();
		showShirt();
		showBody();
		
	}
	
	public class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if(source==bUp){
				if(bodyIndex==body.length-1)
					bodyIndex=0;
				++bodyIndex;
					try {
						showAll();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}else if(source==bDown){
				if(bodyIndex==0)
					bodyIndex=body.length-1;
				--bodyIndex;
					try {
						showAll();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}else if(source==hUp){
				if(headIndex==head.length-1)
					headIndex=0;
				++headIndex;
					try {
						showAll();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}else if(source==hDown){
				if(headIndex==0)
					headIndex=head.length-1;
				--headIndex;
					try {
						showAll();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}else if(source==sUp){
				if(shirtIndex==shirt.length-1)
					shirtIndex=0;
				++shirtIndex;
					try {
						showAll();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}else if(source==sDown){
				if(shirtIndex==0)
					shirtIndex=shirt.length-1;
				--shirtIndex;
					try {
						showAll();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}else if(source==wUp){
				if(weaponIndex==weapon.length-1)
					weaponIndex=0;
				++weaponIndex;
					try {
						showAll();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}else if(source==wDown){
				if(weaponIndex==0)
					weaponIndex=weapon.length-1;
				--weaponIndex;
					try {
						showAll();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}else if(source==btnOk){
				//IcetizenLook look = new IcetizenLook();
				looks.gidB = body[bodyIndex];
				looks.gidH = head[headIndex];
				looks.gidS = shirt[shirtIndex];
				looks.gidW = weapon[weaponIndex];

				Login.immigration.customization(looks);
				Login.myUser.setIcetizenLook(looks);
				//lookChange = true;
				Login.myUser.setImage(bodyIcon.getImage(),headIcon.getImage(),shirtIcon.getImage(),weaponIcon.getImage());
				//Login.immigration.customization(look);
				//Login.immigration.talk("YO!!");
				dispose();
			}else if(source==btnCancel){
				dispose();
			}
			
		}
		
	}
}
