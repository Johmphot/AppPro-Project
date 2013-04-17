package primary;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.*;
import javax.swing.*;

public class About extends JDialog{


public About(){
setSize(980, 351);
setImage();
setVisible(true);
}

private void setImage() {
	BufferedImage myPicture = null;
	try {
		myPicture = ImageIO.read(new File("src/aboutpage.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JLabel picLabel = new JLabel(new ImageIcon( myPicture ));
	add( picLabel );
}





}
