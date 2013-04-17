package primary;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Snow extends JPanel implements Runnable{

	Thread ani;
	
	public Snow(){
		setOpaque(false);
		ani = new Thread(this);
		ani.start();
	}
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		
		Dimension d = this.getSize();
		
		for(int i = 0;i<200;i++){
			int x = (int)(Math.random()*d.width);
			int y = (int)(Math.random()*d.height);
			
			g.setColor(Color.WHITE);
			g.fillOval(x,y,10,10);
		}
		
	}
	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
