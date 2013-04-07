package GraphicElements;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface ToolMenuItem 
{

	public void setWidth(int width);
	public void draw(Graphics g, int x, int y);
	public boolean inBounds(Point p);
	public void mouseAction(MouseEvent evt);
	public void mouseWheelAction(MouseWheelEvent evt);
	public void setTile(IsometricSprite iso);
	public int getHeight();
	public void mainMenuClosed();
}