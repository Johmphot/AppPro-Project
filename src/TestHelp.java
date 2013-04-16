import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestHelp extends JFrame {
JDesktopPane desktop;

public TestHelp(){
setGUI();
}

private void setGUI(){
desktop = new JDesktopPane();
setContentPane(desktop);
setLayout(new FlowLayout());
}

public static void main(String[] args){
	TestHelp k = new TestHelp();
	Help a = new Help();
	a.setSize(800,1000);
	k.setPreferredSize(new Dimension(300,300));
	k.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	k.setVisible(true);
}

}
