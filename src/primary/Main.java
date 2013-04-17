package primary;
import iceworld.given.*;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.border.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField typeBox;
	String chatDialogue="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{
					Main frame = new Main("ICE World");
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
	public Main(String name)
	{
		setResizable(false);
		setTitle("ICE World: "+name);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		
		Login.music.music();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		mnFile.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnFile);

		JMenu mnOpen = new JMenu("Open");
		mnOpen.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		mnFile.add(mnOpen);

		JMenuItem mntmNewWindows = new JMenuItem("New Windows");
		mntmNewWindows.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		mntmNewWindows.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Login.effect.play();
				Login newFrame = new Login();
				newFrame.setVisible(true);
			}
		});
		mnOpen.add(mntmNewWindows);

		JSeparator separator_3 = new JSeparator();
		mnOpen.add(separator_3);

		JMenuItem mntmIceWorldPeek = new JMenuItem("ICE World Peek");
		mntmIceWorldPeek.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		mntmIceWorldPeek.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Login.effect.play();
				try 
				{
					ICEWorldPeek.main(null);
				} 
				catch (Exception e1) {}
			}
		});
		mnOpen.add(mntmIceWorldPeek);

		JMenuItem mntmPreferences = new JMenuItem("Preferences...");
		mntmPreferences.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		mntmPreferences.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Login.effect.play();
				Preferences pref = new Preferences();
				pref.setVisible(true);
				pref.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnFile.add(mntmPreferences);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		mntmAbout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				Login.effect.play();
				About ab = new About();
				ab.setVisible(true);
			}
		});
		mnFile.add(mntmAbout);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Login.effect.play();
				JDialog exit = new JDialog();
				int n = JOptionPane.showConfirmDialog(exit,"Do you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
				if(n==JOptionPane.YES_OPTION)
				{
					Login.immigration.logout();
					System.exit(0);
				}
				else
				{
					exit.dispose();
				}
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		mnFile.add(mntmExit);

		JMenu mnAccount = new JMenu("Account");
		mnAccount.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		mnAccount.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnAccount);

		if (!name.equals("Alien")) 
		{
			JMenuItem mntmCustomization = new JMenuItem("Customization...");
			mntmCustomization
					.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
			mntmCustomization.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					Login.effect.play();
					Customize c;
					try {
						c = new Customize();
						c.setVisible(true);
						c.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			mnAccount.add(mntmCustomization);
			JSeparator separator_2 = new JSeparator();
			mnAccount.add(separator_2);
		}
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		mntmLogout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(Login.immigration.logout())
				{
					Login.effect.play();
					dispose();
					Login newFrame = new Login();
					newFrame.setVisible(true);
				}
			}
		});
		mnAccount.add(mntmLogout);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		mnHelp.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnHelp);

		JMenuItem mntmHelpContents = new JMenuItem("Help Contents");
		mntmHelpContents.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Login.effect.play();
				Help help = new Help();
				help.setSize(800, 1000);
			}
		});
		mntmHelpContents.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		mntmHelpContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnHelp.add(mntmHelpContents);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final graphicElements.Window window = new graphicElements.Window();
		window.setSize(964, 560);
		window.canvas.setBounds(0, 0, 964, 560);
		window.setLayout(null);
		window.setOpaque(false);

		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.DARK_GRAY);
		controlPanel.setBorder(null);
		controlPanel.setBounds(6, 570, 1002, 148);
		contentPane.add(controlPanel);
		controlPanel.setLayout(null);

		final JTextArea chatBox = new JTextArea();
		chatBox.setBounds(6, 7, 323, 135);
		chatBox.setBackground(Color.LIGHT_GRAY);
		chatBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		chatBox.setEditable(false);

		typeBox = new JTextField();
		typeBox.setBounds(395, 21, 323, 55);
		controlPanel.add(typeBox);
		typeBox.setBackground(Color.LIGHT_GRAY);
		typeBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		typeBox.setColumns(10);
		
		JButton btnTalk = new JButton("Talk");
		btnTalk.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		btnTalk.setBounds(395, 88, 161, 29);
		controlPanel.add(btnTalk);
		btnTalk.addActionListener(new ActionListener()
		{
		     public void actionPerformed(ActionEvent ae)
		     {
		    	 Login.effect.play();
		    	 String text = typeBox.getText();
		    	 if (text.length()<=100) 
		    	 {
					Login.immigration.talk(text);
					chatDialogue += text + "\n";
					chatBox.setText(chatDialogue);
		    	 }
		    	 else
		    	 {
		    		JFrame warn = new JFrame();
		    		JOptionPane.showMessageDialog(warn, "Message is longer than 100 characters ", "Talk Error", JOptionPane.ERROR_MESSAGE);
		    	 }
		     }
		});

		JButton btnYell = new JButton("Yell");
		btnYell.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		btnYell.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Login.effect.play();
				String text = typeBox.getText().toUpperCase();
		    	 if (text.length()<=10) 
		    	 {
					Login.immigration.yell(text);
					chatDialogue += text + "\n";
					chatBox.setText(chatDialogue);
					toggleYell y = new toggleYell(text);
					Timer timer = new Timer();
				    timer.schedule(y,5000);
				    
		    	 }
		    	 else
		    	 {
		    		JFrame warn = new JFrame();
		    		JOptionPane.showMessageDialog(warn, "Message is longer than 10 characters ", "Yell Error", JOptionPane.ERROR_MESSAGE);
		    	 }
			}
		});
		btnYell.setBounds(557, 88, 161, 29);
		controlPanel.add(btnYell);

		JLabel lblWeather = new JLabel("Weather");
		lblWeather.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblWeather.setForeground(Color.WHITE);
		lblWeather.setBounds(864, 6, 61, 16);
		controlPanel.add(lblWeather);

		WeatherPanel wth = new WeatherPanel();
		wth.setBounds(834, 34, 121, 98);
		Thread weatherThread = new Thread(wth);
		weatherThread.start();
		controlPanel.add(wth);
		
		JScrollPane scrollPane = new JScrollPane(chatBox);
		scrollPane.setBounds(6, 6, 323, 135);
		controlPanel.add(scrollPane);

		JLabel lblZoom = new JLabel("Zoom");
		lblZoom.setForeground(Color.WHITE);
		lblZoom.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblZoom.setBounds(982, 6, 36, 16);
		contentPane.add(lblZoom);

		final JSlider slider = new JSlider();
		slider.setBackground(Color.LIGHT_GRAY);
		slider.setValue(0);
		slider.setMaximum(10);
		slider.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent e) 
			{
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) 
				{
					int level = (int) source.getValue();
					window.canvas.iso.zoomLevel = level;
					window.canvas.iso.regenerateMap();
				}
			}
		});
		slider.setMinorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBounds(982, 28, 36, 530);
		contentPane.add(slider);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(6, 6, 964, 560);
		contentPane.add(layeredPane);
		
		Weather weather = new Weather();
		weather.setBounds(6, 6, 964, 560);
		
		Snow sn = new Snow();
		sn.setOpaque(false);
		sn.setBounds(0, 0, 964, 560);
		
		//layeredPane.add(weather,new Integer(-100));
		layeredPane.add(window, new Integer(0));
		//layeredPane.add(sn, new Integer(100));
		
		InputMap arrow = new InputMap();
		ActionMap action = new ActionMap();
		// add CONTROL to speed up movement
		
		arrow = window.getInputMap();
		arrow.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,false),"down");
		arrow.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,false),"up");
		arrow.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,false),"left");
		arrow.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false),"right");
		
		action = window.getActionMap();
		action.put("left", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) 
	        {
				int speed = window.canvas.iso.zoomLevel+1;
				window.canvas.iso.WIDTH += 5*speed;
	        }
		});
		action.put("right", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) 
	        {
				int speed = window.canvas.iso.zoomLevel+1;
				window.canvas.iso.WIDTH -= 5*speed;
	        }
		});
		action.put("up", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) 
	        {
				int speed = window.canvas.iso.zoomLevel+1;
				window.canvas.iso.HEIGHT += 5*speed;
	        }
		});
		action.put("down", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) 
	        {
				int speed = window.canvas.iso.zoomLevel+1;
				window.canvas.iso.HEIGHT -= 5*speed;
	        }
		});
		
		arrow.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,InputEvent.CTRL_DOWN_MASK,false), "in");
		arrow.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,InputEvent.CTRL_DOWN_MASK,false), "out");
		
		action.put("in", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) 
	        {
				if(window.canvas.iso.zoomLevel<10)
				{
					window.canvas.iso.zoomLevel+=1;
					slider.setValue(window.canvas.iso.zoomLevel);
				}
	        }
		});
		action.put("out", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) 
	        {
				if(window.canvas.iso.zoomLevel>0)
				{
					window.canvas.iso.zoomLevel-=1;
					slider.setValue(window.canvas.iso.zoomLevel);
				}
	        }
		});
	}
	
	public class toggleYell extends TimerTask
	{
		JDialog frame;
		
		public toggleYell(String t)
		{
			frame = new JDialog();
			frame.setSize(1000,400);
			JLabel txt = new JLabel(t);
			txt.setFont(new Font("Helvetica Neue", Font.BOLD, 150));
			frame.getContentPane().setLayout(new GridLayout(1,1,0,0));
			frame.getContentPane().add(txt);
			frame.setVisible(true);
		}
		public void run()
		{
			frame.dispose();
		}
	}
}

