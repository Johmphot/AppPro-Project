package Primary;
import iceworld.given.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.border.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnOpen = new JMenu("Open");
		mnFile.add(mnOpen);

		JMenuItem mntmNewWindows = new JMenuItem("New Windows");
		mnOpen.add(mntmNewWindows);

		JSeparator separator_3 = new JSeparator();
		mnOpen.add(separator_3);

		JMenuItem mntmIceWorldPeek = new JMenuItem("ICE World Peek");
		mntmIceWorldPeek.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					ICEWorldPeek.main(null);
				} 
				catch (Exception e1) {}
			}
		});
		mnOpen.add(mntmIceWorldPeek);

		JMenuItem mntmPreferences = new JMenuItem("Preferences...");
		mntmPreferences.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Preferences pref = new Preferences();
				pref.setVisible(true);
				pref.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnFile.add(mntmPreferences);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnFile.add(mntmAbout);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);

		JMenu mnAccount = new JMenu("Account");
		menuBar.add(mnAccount);

		JMenuItem mntmCustomization = new JMenuItem("Customization...");
		mntmCustomization.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

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

		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ICEWorldImmigration immigration = new ICEWorldImmigration((MyIcetizen) Login.user);
				if(immigration.logout())
				{
					dispose();
					Login newFrame = new Login();
					newFrame.setVisible(true);
				}
			}
		});
		mnAccount.add(mntmLogout);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelpContents = new JMenuItem("Help Contents");
		mntmHelpContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnHelp.add(mntmHelpContents);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(15, 668, 230, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		final GraphicElements.Window window = new GraphicElements.Window();
		window.setBounds(6, 6, 964, 512);
		contentPane.add(window);

		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(null);
		controlPanel.setBounds(277, 525, 741, 193);
		contentPane.add(controlPanel);
		controlPanel.setLayout(null);

		JButton btnTalk = new JButton("Talk");
		btnTalk.setBounds(29, 141, 60, 20);
		controlPanel.add(btnTalk);

		JButton btnYell = new JButton("Yell");
		btnYell.setBounds(101, 141, 60, 20);
		controlPanel.add(btnYell);

		JButton btnSpecialAction = new JButton("Special Action");
		btnSpecialAction.setBounds(29, 35, 132, 52);
		controlPanel.add(btnSpecialAction);

		JPanel miniMap = new JPanel();
		miniMap.setBackground(Color.WHITE);
		miniMap.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		miniMap.setBounds(414, 6, 298, 176);
		controlPanel.add(miniMap);

		JLabel lblWeather = new JLabel("Weather");
		lblWeather.setBounds(258, 22, 61, 16);
		controlPanel.add(lblWeather);

		JPanel weatherPanel = new JPanel();
		weatherPanel.setBackground(Color.WHITE);
		weatherPanel.setBounds(229, 50, 121, 98);
		controlPanel.add(weatherPanel);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(15, 530, 230, 122);
		contentPane.add(textArea);

		JLabel lblZoom = new JLabel("Zoom");
		lblZoom.setBounds(982, 6, 36, 16);
		contentPane.add(lblZoom);

		JSlider slider = new JSlider();
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
		slider.setBounds(982, 28, 36, 490);
		contentPane.add(slider);
		
		
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
	}
}
