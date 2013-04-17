package graphicElements;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;

import primary.Login;

public class GameCanvas extends Canvas implements Runnable 
{
	private final long FRAME_DELAY = 16; 
	private final int MIN_SLEEP_TIME = 4;

	private final int HEIGHT = 560;
	private final int WIDTH = 964;

	public IsometricMap iso = null;
	private IsometricSprite lastTile;

	private volatile boolean active = true;
	private BufferStrategy buffer;
	private Graphics graphics;

	private Thread thread = null;

	public GameCanvas() 
	{
		setup();
		addMouseMotionListener(new MouseMotionListener() 
		{

			@Override
			public void mouseMoved(MouseEvent e) 
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				iso.setMousePoint(e.getPoint());
			}

			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if(arg0.getButton()==1)
				{
					Toolkit.getDefaultToolkit().beep();
					IsometricSprite tile = iso.getPoint(getMousePosition().getLocation());
					int x = Translator.toGrid(tile).x;
					int y = Translator.toGrid(tile).y;
					lastTile.setFocused(false);
					lastTile = tile;
					tile.setFocused(true);
					//animateWalk(x,y);
					System.out.println("Walk to "+x+","+y);
					primary.Login.immigration.walk(x, y);
				}
			}
		});
	}

	@Override
	public void run() 
	{
		long cycleTime = System.currentTimeMillis();
		while (active) 
		{
			render();
			draw();

			cycleTime += FRAME_DELAY;
			long diff = cycleTime - System.currentTimeMillis();
			try 
			{
				Thread.sleep(Math.max(MIN_SLEEP_TIME, diff));
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void render() 
	{
		graphics = buffer.getDrawGraphics();

		// // Clearing old data
		graphics.setColor(Color.gray);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);

		if (graphics instanceof Graphics2D) 
		{
			((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		// Renders map
		renderMap(graphics);
	}

	public void renderMap(Graphics g)
	{
		if (iso != null)
		{
			if (iso.isReady()) 
			{
				iso.draw(g);
			} 
		}
	}	

	private void draw() 
	{
		if (!buffer.contentsLost()) 
		{
			buffer.show();
		}
		if (graphics != null) 
		{
			graphics.dispose();
		}
	}

	private void setup() 
	{
		setIgnoreRepaint(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		lastTile = new IsometricSprite(0,0);
	}

	public void addNotify() 
	{
		super.addNotify();

		// Creating buffered strategy.
		createBufferStrategy(2);

		// FOR SOME REASON 3 works better on debian...
		buffer = getBufferStrategy();
		requestFocus();

		// Starting
		start();
	}

	private void start() 
	{
		if (thread == null) 
		{
			thread = new Thread(this, "GameCanvas");
			thread.setPriority(Thread.NORM_PRIORITY);
		}
		thread.start();

		if (iso == null)
		{
			iso = new IsometricMap();
			iso.start();
		}
	}
	
	public void showCurrentPosition()
	{
		Point pos = new Point(0,0);
		if(Login.myUser.getPoint()!=null) pos = Login.myUser.getPoint();
		IsometricSprite ip = iso.getPoint(Translator.toIso(pos));
		System.out.println(pos);
		ip.setCurrentPos(true);
	}
	
	public void animateWalk(int nx,int ny)
	{
		Point pos = Login.myUser.getPoint();
		int ox = Login.myUser.getPoint().x;
		int oy = Login.myUser.getPoint().y;
		System.out.println(ox+","+oy);
		IsometricSprite ip,previous;
		previous = iso.getPoint(pos);
		previous.setCurrentPos(true);
	}

}