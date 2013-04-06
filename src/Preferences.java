import java.awt.EventQueue;

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


public class Preferences extends JFrame {
	private JTextField textField;

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
		setTitle("Preferences");
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 25, 296, 247);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblIceWorld = new JLabel("ICE World");
		lblIceWorld.setBounds(0, 0, 68, 19);
		lblIceWorld.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(lblIceWorld);

		JLabel lblRefreshInterval = new JLabel("Refresh Interval");
		lblRefreshInterval.setBounds(6, 43, 104, 16);
		panel.add(lblRefreshInterval);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBox.setBounds(122, 39, 68, 27);
		panel.add(comboBox);

		JPanel panel_World = new JPanel();
		panel_World.setBounds(314, 25, 296, 247);
		getContentPane().add(panel_World);
		panel_World.setLayout(null);

		JLabel lblIcetizens = new JLabel("ICE-tizens");
		lblIcetizens.setBounds(0, 0, 75, 19);
		lblIcetizens.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_World.add(lblIcetizens);

		JLabel lblTalkingBubbleDuration = new JLabel("Talking Bubble Duration");
		lblTalkingBubbleDuration.setBounds(10, 41, 161, 16);
		panel_World.add(lblTalkingBubbleDuration);

		textField = new JTextField();
		textField.setText("5");
		textField.setBounds(177, 35, 44, 28);
		panel_World.add(textField);
		textField.setColumns(10);

		JLabel lblSeconds = new JLabel("seconds");
		lblSeconds.setBounds(233, 41, 61, 16);
		panel_World.add(lblSeconds);

		JPanel panel_2 = new JPanel();
		panel_2.setAlignmentX(0.1f);
		panel_2.setBounds(6, 284, 604, 144);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblSounds = new JLabel("Sounds");
		lblSounds.setBounds(6, 6, 52, 19);
		lblSounds.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_2.add(lblSounds);

		JSlider slider = new JSlider();
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(100);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(5);
		slider.setBounds(6, 87, 296, 51);
		panel_2.add(slider);

		JSlider slider_1 = new JSlider();
		slider_1.setPaintLabels(true);
		slider_1.setMajorTickSpacing(100);
		slider_1.setMinorTickSpacing(5);
		slider_1.setPaintTicks(true);
		slider_1.setBounds(302, 87, 296, 51);
		panel_2.add(slider_1);

		JLabel lblBackgroundVolume = new JLabel("Background Music Volume");
		lblBackgroundVolume.setBounds(70, 59, 166, 16);
		panel_2.add(lblBackgroundVolume);

		JLabel lblSoundEffectVolume = new JLabel("Sound Effect Volume");
		lblSoundEffectVolume.setBounds(392, 59, 130, 16);
		panel_2.add(lblSoundEffectVolume);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 272, 604, 12);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(301, 6, 12, 272);
		getContentPane().add(separator_1);

	}
}