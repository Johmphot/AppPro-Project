import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

public class Customize {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setSize(450,300);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 197, 266);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(89, 5, 1, 1);
		panel_5.setLayout(null);
		panel.add(panel_5);
		
		JButton button_4 = new JButton("<<");
		button_4.setBounds(0, 0, 63, 29);
		panel_5.add(button_4);
		
		JButton button_5 = new JButton(">>");
		button_5.setBounds(104, 0, 63, 29);
		panel_5.add(button_5);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("  Body");
		textPane.setAlignmentX(1.0f);
		textPane.setBounds(61, 6, 47, 17);
		panel_5.add(textPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(209, 6, 235, 55);
		frame.getContentPane().add(panel_2);
		
		JButton button_2 = new JButton("<<");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setBounds(6, 6, 66, 42);
		panel_2.add(button_2);
		
		JButton button_3 = new JButton(">>");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_3.setBounds(163, 6, 66, 42);
		panel_2.add(button_3);
		
		JTextPane txtpnBody = new JTextPane();
		txtpnBody.setOpaque(false);
		txtpnBody.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		txtpnBody.setBackground(Color.WHITE);
		txtpnBody.setText("   Body");
		txtpnBody.setBounds(77, 16, 80, 32);
		panel_2.add(txtpnBody);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(209, 65, 235, 55);
		frame.getContentPane().add(panel_1);
		
		JButton button = new JButton("<<");
		button.setBounds(6, 6, 66, 42);
		panel_1.add(button);
		
		JButton button_1 = new JButton(">>");
		button_1.setBounds(163, 6, 66, 42);
		panel_1.add(button_1);
		
		JTextPane txtpnHeadGear = new JTextPane();
		txtpnHeadGear.setText("Head Gear");
		txtpnHeadGear.setOpaque(false);
		txtpnHeadGear.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		txtpnHeadGear.setBackground(Color.WHITE);
		txtpnHeadGear.setBounds(75, 16, 86, 32);
		panel_1.add(txtpnHeadGear);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(209, 124, 235, 55);
		frame.getContentPane().add(panel_3);
		
		JButton button_8 = new JButton("<<");
		button_8.setBounds(6, 6, 66, 42);
		panel_3.add(button_8);
		
		JButton button_9 = new JButton(">>");
		button_9.setBounds(163, 6, 66, 42);
		panel_3.add(button_9);
		
		JTextPane txtpnShirt = new JTextPane();
		txtpnShirt.setText("     Shirt");
		txtpnShirt.setOpaque(false);
		txtpnShirt.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		txtpnShirt.setBackground(Color.WHITE);
		txtpnShirt.setBounds(71, 16, 80, 32);
		panel_3.add(txtpnShirt);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(209, 184, 235, 55);
		frame.getContentPane().add(panel_4);
		
		JButton button_10 = new JButton("<<");
		button_10.setBounds(6, 6, 66, 42);
		panel_4.add(button_10);
		
		JButton button_11 = new JButton(">>");
		button_11.setBounds(163, 6, 66, 42);
		panel_4.add(button_11);
		
		JTextPane txtpnWeapon = new JTextPane();
		txtpnWeapon.setText("  Weapon");
		txtpnWeapon.setOpaque(false);
		txtpnWeapon.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		txtpnWeapon.setBackground(Color.WHITE);
		txtpnWeapon.setBounds(73, 16, 80, 32);
		panel_4.add(txtpnWeapon);
		
		JButton btnCancel = new JButton(" Cancel");
		btnCancel.setBounds(215, 243, 117, 29);
		frame.getContentPane().add(btnCancel);
		
		JButton btnOk = new JButton(" OK");
		btnOk.setBounds(327, 243, 117, 29);
		frame.getContentPane().add(btnOk);
		JPanel icetizen = new JPanel();
		JPanel chooser = new JPanel(new GridLayout(1,3,0,0));
		
		frame.setVisible(true);
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
	}
}
