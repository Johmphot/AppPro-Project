package primary;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.Color;


public class Preferences extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Preferences frame = new Preferences();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Preferences() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setTitle("Preferences");
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(6, 25, 628, 247);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblIceWorld = new JLabel("ICE World");
		lblIceWorld.setForeground(Color.WHITE);
		lblIceWorld.setBounds(6, 6, 82, 19);
		lblIceWorld.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		panel.add(lblIceWorld);

		JLabel lblRefreshInterval = new JLabel("Refresh Interval");
		lblRefreshInterval.setForeground(Color.WHITE);
		lblRefreshInterval.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblRefreshInterval.setBounds(34, 43, 104, 16);
		panel.add(lblRefreshInterval);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		comboBox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JComboBox cb = (JComboBox)e.getSource();
				Fetching.REFRESH_INTERVAL = Integer.parseInt((String)cb.getSelectedItem());
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBox.setSelectedIndex(4);
		comboBox.setBounds(151, 39, 68, 27);
		panel.add(comboBox);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setAlignmentX(0.1f);
		panel_2.setBounds(6, 284, 628, 144);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblSounds = new JLabel("Sounds");
		lblSounds.setForeground(Color.WHITE);
		lblSounds.setBounds(6, 6, 62, 19);
		lblSounds.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		panel_2.add(lblSounds);
		int value;

		JLabel lblBackgroundVolume = new JLabel("Background Music Volume");
		lblBackgroundVolume.setForeground(Color.WHITE);
		lblBackgroundVolume.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblBackgroundVolume.setBounds(73, 40, 172, 16);
		panel_2.add(lblBackgroundVolume);

		JLabel lblSoundEffectVolume = new JLabel("Sound Effect Volume");
		lblSoundEffectVolume.setForeground(Color.WHITE);
		lblSoundEffectVolume.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		lblSoundEffectVolume.setBounds(389, 40, 142, 16);
		panel_2.add(lblSoundEffectVolume);
		
		JButton bm_vDown = new JButton("<");
		bm_vDown.setBackground(Color.GRAY);
		bm_vDown.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Login.music.decrease();
			}
		});
		bm_vDown.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		bm_vDown.setBounds(39, 76, 117, 47);
		panel_2.add(bm_vDown);
		
		JButton bm_vUp = new JButton(">");
		bm_vUp.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		bm_vUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Login.music.increase();
			}
		});
		bm_vUp.setBounds(156, 76, 117, 47);
		panel_2.add(bm_vUp);
		
		JButton se_vUp = new JButton(">");
		se_vUp.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		se_vUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Login.effect.increase();
			}
		});
		se_vUp.setBounds(453, 76, 117, 47);
		panel_2.add(se_vUp);
		
		JButton se_vDown = new JButton("<");
		se_vDown.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		se_vDown.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Login.effect.decrease();
			}
		});
		se_vDown.setBounds(328, 76, 117, 47);
		panel_2.add(se_vDown);

	}
}