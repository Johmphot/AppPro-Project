package GraphicElements;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;


public class IsometricSprite implements Drawable
{
	private int width; // zoom level
	private int height;

	private boolean highlighted = false;
	private boolean focused = false;

	private Polygon poly = null;
	private Rectangle rect;
	private Polygon old_poly;

	private int x, y;

	private boolean passable = true;
	private boolean buildable = true;
	private TileItem item = null;

	private volatile ArrayList<IsometricSprite> linkedPoints;


	public IsometricSprite(int x, int y) {
		// Generate polygon
		this.x = x;
		this.y = y;

		int[] px = { x * width + width, x * width, x * width - width, x * width };
		int[] py = { y * height, y * height + height, y * height, y * height - height };

		poly = new Polygon(px, py, 4);
		old_poly = new Polygon(px, py, 4);

	}

	/**
	 * Drawing outlines
	 * @param g
	 */
	private void drawOutlines(Graphics g) 
	{
		g.setColor(Color.black);
		if (focused) 
		{
			g.setColor(Color.red);
			g.fillPolygon(poly);
		} 
		else if (highlighted) 
		{
			g.setColor(Color.orange);
			g.fillPolygon(poly);
		}
		else 
		{
			g.drawPolygon(poly);
		}
	}

	/**
	 * Drawing items
	 * @param g
	 */
	private void drawItem(Graphics g) 
	{
		if (item != null) {
			item.draw(g);
		}
	}

	/**
	 * Drawing points
	 * @param g
	 */
	private void drawPoint(Graphics g) 
	{
		rect = poly.getBounds();
		g.setColor(Color.black);
		g.drawString("(" + x + "," + y + ")", rect.x + width - 12, rect.y
				+ height + 5);
	}

	@Override
	public void draw(Graphics g) 
	{
		if (poly == null) 
			return;

		drawOutlines(g);
		//drawPoint(g);
		//drawItem(g);

		regeneratePoly();
	}


	/**
	 * Regenarate the poly.
	 */
	private void regeneratePoly() 
	{
		// Creating old bounds
		old_poly = new Polygon(poly.xpoints, poly.ypoints, poly.npoints);
		// Regenerate poly

		int[] px = { x * width + width, x * width, x * width - width,
				x * width };
		int[] py = { y * height, y * height + height, y * height,
				y * height - height };

		poly.xpoints = px;
		poly.ypoints = py;

		poly.invalidate();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "x=" + x + ", y=" + y + ", height=" + height + ", width="
				+ width;
	}

	/**
	 * @param highlighted
	 */
	public void setHighlight(boolean highlighted)
	{
		this.highlighted = highlighted;
	}

	/**
	 * @param p
	 * @return
	 */
	public boolean contains(Point p) 
	{
		return poly.contains(p);
	}

	/**
	 * @param p
	 * @return
	 */
	public boolean relativeContains(Point p) 
	{
		return old_poly.contains(p);
	}

	/**
	 * @param trans
	 */
	public void transformPoly(Point trans) 
	{
		if (trans == null)
			return;
		poly.translate(trans.x, trans.y);
	}

	/**
	 * @param focused
	 */
	public void setFocused(boolean focused) 
	{
		this.focused = focused;
	}
	// Real center
	public Point getCenter() 
	{
		rect = poly.getBounds();
		return new Point((int) rect.getCenterX(), (int) rect.getCenterY());
	}
	// Relative center
	public Point getRelativeCenter() 
	{
		Rectangle rect = old_poly.getBounds();
		return new Point((int) rect.getCenterX(), (int) rect.getCenterY());
	}
	public int getX()
	{
		return x;
	}
	public int getY() 
	{
		return y;
	}

	public void setPassable(boolean b) 
	{
		passable = b;
	}

	public boolean isPassable() 
	{
		return passable;
	}

	public void setTileItem(TileItem item) 
	{
		this.item = item;
		item.setIsometricPoint(this);
	}
	public Point getImageLocation(int img_width, int img_height) 
	{
		Point tmp = getCenter();
		return new Point(tmp.x - img_width/2, tmp.y - img_height + this.height/2);
	}

	public Point getRelativeImageLocation(int img_width, int img_height) 
	{
		Point tmp = getRelativeCenter();
		return new Point(tmp.x - img_width/2, tmp.y - img_height + this.height/2);
	}

	public void setLinkedPoints(ArrayList<IsometricSprite> linkedPoints) 
	{
		this.linkedPoints = linkedPoints;
	}

	public ArrayList<IsometricSprite> getLinkedPoints() 
	{
		return linkedPoints;
	}

	public void setBuildable(boolean buildable) 
	{
		this.buildable = buildable;
	}
	public boolean isBuildable() {
		return buildable;
	}
	public boolean getCollide(Rectangle r) 
	{
		return poly.intersects(r.x, r.y, r.width, r.height);
	}
	public boolean getRelativeCollide(Rectangle r) 
	{
		return old_poly.intersects(r.x, r.y, r.width, r.height);
	}
	public void unsetTileItem() 
	{
		item = null;
	}
	public int getHeight() 
	{
		return height;
	}
	public int getWidth() 
	{
		return width;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
}