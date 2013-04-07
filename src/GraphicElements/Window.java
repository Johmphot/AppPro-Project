package GraphicElements;

import javax.swing.JPanel;

public class Window extends JPanel
{

	private GameCanvas canvas = null;

	public Window() 
	{
		add(getCanvas());
		validate();
	}

	private GameCanvas getCanvas()
	{
		if (canvas == null) 
		{
			canvas = new GameCanvas();
		}
		return canvas;
	}
}