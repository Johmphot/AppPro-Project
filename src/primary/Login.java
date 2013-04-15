package primary;
import iceworld.given.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import org.json.simple.parser.ParseException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.LinkedList;

public class Login extends JFrame 
{

	private JPanel contentPane;
	private JPasswordField passwordField;
	public static Icetizen myUser;
	public static ICEWorldImmigration immigration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		SplashScreen s= new SplashScreen();
		
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Login frame = new Login();
					
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() 
	{
		setResizable(false);
		setTitle("Login To ICE World");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnOpen = new JMenu("Open");
		mnFile.add(mnOpen);

		JMenuItem mntmNewLoginWindow = new JMenuItem("New Login Window");
		mntmNewLoginWindow.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Login newFrame = new Login();
				newFrame.setVisible(true);
			}
		});
		mnOpen.add(mntmNewLoginWindow);

		JSeparator separator_2 = new JSeparator();
		mnOpen.add(separator_2);

		JMenuItem mntmIceWorldPeek = new JMenuItem("ICE World Peek");
		mntmIceWorldPeek.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					ICEWorldPeek.main(null);
				} 
				catch (Exception e) {}
			}
		});
		mnOpen.add(mntmIceWorldPeek);

		JMenuItem mntmPreferences = new JMenuItem("Preferences...");
		mntmPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Preferences pref = new Preferences();
				pref.setVisible(true);
			}
		});
		mnFile.add(mntmPreferences);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				About ab = new About();
				ab.setVisible(true);
			}
		});
		mnFile.add(mntmAbout);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelpContents = new JMenuItem("Help Contents");
		mntmHelpContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnHelp.add(mntmHelpContents);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(320, 176, 261, 28);
		contentPane.add(formattedTextField);

		passwordField = new JPasswordField();
		passwordField.setBounds(320, 248, 261, 28);
		contentPane.add(passwordField);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(230, 182, 67, 16);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(230, 254, 65, 16);
		contentPane.add(lblPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String name = formattedTextField.getText();
				String password = new String(passwordField.getPassword());
				if (userLogin(name,password))
				{
					Main world = new Main(name);
					dispose();
					world.setVisible(true);
					
				}
				else
				{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Incorrect Username//Password", "Login Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(349, 322, 117, 29);
		contentPane.add(btnLogin);

		JButton btnAlien = new JButton("Login As Alien");
		btnAlien.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(alienLogin())
				{
					Main world = new Main("Alien");
					dispose();
					world.setVisible(true);
				}
			}
		});
		btnAlien.setBounds(301, 385, 214, 29);
		contentPane.add(btnAlien);

		JLabel lblIceWorld = new JLabel("ICE World");
		lblIceWorld.setHorizontalAlignment(SwingConstants.CENTER);
		lblIceWorld.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		lblIceWorld.setBounds(244, 71, 337, 51);
		contentPane.add(lblIceWorld);
	}

	public boolean userLogin(String username,String password)
	{
		myUser = new Icetizen();
		immigration = new ICEWorldImmigration((MyIcetizen) myUser);
		myUser.setIcePortID(253); //Port ID 253
		myUser.setUsername(username);
		myUser.setListeningPort(10018);
		if(immigration.login(password))
		{
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Login as "+username, "Login Sucessful", JOptionPane.INFORMATION_MESSAGE);
			LinkedList<Icetizen> user = new LinkedList<Icetizen>();
			Fetch a = new Fetch(user);
			a.start();
			try {
				myUser.fetchLook();
			} catch (ParseException e) {}
			return true;
		}
		else return false;
	}

	public boolean alienLogin()
	{
		myUser = new Icetizen();
		immigration = new ICEWorldImmigration((MyIcetizen) myUser); 
		myUser.setIcePortID(253); //Port ID 253
		myUser.setListeningPort(10018);
		IcetizenLook look = new IcetizenLook();
		look.gidB = "B001";
		look.gidH = "H001";
		look.gidS = "S001";
		look.gidW = "W001";
		if(immigration.loginAlien())
		{
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Login as an Alien", "Login Sucessful", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		else return false;
	}
}