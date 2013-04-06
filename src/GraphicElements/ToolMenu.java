package GraphicElements;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

/**
 * Toolbox menu from where you can add buildings and shit.
 * 
 * @author kolmas
 * 
 */
public class ToolMenu implements Drawable {

	/**
	 * Menu width
	 */
	private final int WIDTH = 160;

	/**
	 * Is menu active
	 */
	private boolean active = false;
	/**
	 * Menu location on screen
	 */
	private int x, y;

	/**
	 * Sections that are in menu
	 */
	private ArrayList<ToolMenuItem> sections;
	/**
	 * Tile we clicked on
	 */
	private IsometricSprite iso;

	/**
	 * Active section
	 */
	private ToolMenuItem activeSection = null;

	/**
	 * Is menu active?
	 * 
	 * @return
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Set active/unactive. If we lose focus lets make sure every sections knows
	 * about it.
	 * 
	 * @param active
	 */
	public void setActive(boolean active) 
	{
		this.active = active;
		if (!isActive()) 
		{
			synchronized (sections) 
			{
				for (ToolMenuItem tmi : sections) 
				{
					tmi.mainMenuClosed();
				}
			}
			activeSection = null;
		}
	}

	/**
	 * Linking tile
	 * 
	 * @param iso
	 */
	public void setTile(IsometricSprite iso) 
	{
		this.iso = iso;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.addon.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g)
	{
		synchronized (sections)
		{
			int height_used = 0;
			for (ToolMenuItem tmi : sections) 
			{
				// Linking tile before drawing
				tmi.setTile(iso);
				// Drawing
				tmi.draw(g, x, y + height_used);
				// Setting height
				height_used += tmi.getHeight();
			}
		}
	}

	/**
	 * Adding section to menu
	 * 
	 * @param section
	 */
	private void addSection(ToolMenuItem section)
	{
		if (sections == null)
		{
			sections = new ArrayList<ToolMenuItem>();
		}
		// Setting width
		section.setWidth(WIDTH);
		sections.add(section);
	}

	/**
	 * Dealing with mouse actions
	 * 
	 * @param e
	 */
	public void mouseAction(MouseEvent e) 
	{
		synchronized (sections)
		{
			for (ToolMenuItem tmi : sections) 
			{
				if (tmi.inBounds(e.getPoint())) 
				{
					tmi.mouseAction(e);
					// Setting found one as active
					activeSection = tmi;
					return;
				}
			}
		}
	}

	/**
	 * Dealing with mouseWheelAction
	 * 
	 * @param e
	 */
	public void mouseWheelAction(MouseWheelEvent e) 
	{
		// lets see if we have our some menu item active that would be
		// interested in that.
		if (activeSection != null) 
		{
			activeSection.mouseWheelAction(e);
			return;
		}
		synchronized (sections)
		{
			for (ToolMenuItem tmi : sections) 
			{
				if (tmi.inBounds(e.getPoint())) 
				{
					tmi.mouseWheelAction(e);
					return;
				}
			}
		}
	}
}