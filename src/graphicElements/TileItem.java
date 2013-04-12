package graphicElements;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class TileItem implements Drawable 
{

	protected BufferedImage img = null;
	protected IsometricSprite iso = null;
	protected boolean passable = true;
	protected boolean buildable = false;
	protected boolean fixed = false;
	protected IsometricSprite edge_iso = null;

	public void setIsometricPoint(IsometricSprite iso)
	{
		if (fixed) return;

		this.iso = iso;
		iso.setPassable(passable);
		iso.setBuildable(buildable);

		// Getting the right shape
		Point drawPoint = iso.getImageLocation(img.getWidth(), img.getHeight());
		Rectangle tmp = img.getData().getBounds();
		tmp.translate(drawPoint.x, drawPoint.y);

		// Edge points
		ArrayList<IsometricSprite> edge = new ArrayList<IsometricSprite>();

		// Starting recursive loop
		setContainsBuildable(iso.getLinkedPoints(), tmp, new ArrayList<IsometricSprite>(), edge, new Point((int) tmp.getMinX(), (int) tmp.getMaxY()));

		setRelativePos(edge);
	}

	private void setRelativePos(ArrayList<IsometricSprite> edge_point) 
	{
		if (edge_point.isEmpty()) 
		{
			this.edge_iso = null;
			return;
		}
		// Check force tile change as true.
		fixed = true;

		// Getting last sprite and changing links to sprites
		IsometricSprite iso = edge_point.get(edge_point.size() - 1);
		this.edge_iso = this.iso;
		this.iso = iso;
		this.iso.setTileItem(this);

		// Unsetting old sprite
		this.edge_iso.unsetTileItem();
	}

	private void setContainsBuildable(ArrayList<IsometricSprite> spriteList, Rectangle r, ArrayList<IsometricSprite> isos, ArrayList<IsometricSprite> edge, Point lastPoint) {

		for (IsometricSprite sp : spriteList)
		{
			if (!isos.contains(sp)) 
			{
				isos.add(sp);
				// Check if its not me
				if (sp != iso) {
					// Check the last contact point
					if (sp.contains(lastPoint)) 
					{
						edge.add(sp);
					}
					// Add collision info
					if (sp.getCollide(r)) 
					{
						sp.setBuildable(buildable);
						sp.setPassable(passable);
					}

					// Send it to loop
					setContainsBuildable(sp.getLinkedPoints(), r, isos, edge,
							lastPoint);
				}
			}
		}
		return;
	}

	/**
	 * Linking tile
	 * @param img
	 */
	public TileItem(BufferedImage img) 
	{
		this.img = img;
	}

	/* (non-Javadoc)
	 * @see gui.addon.Drawable#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g) 
	{
		if (iso == null || img == null)	return;

		Point drawPoint = iso.getImageLocation(img.getWidth(), img.getHeight());

		if (edge_iso != null) 
		{
			int diff_x = edge_iso.getX() - iso.getX();
			int diff_y = edge_iso.getY() - iso.getY();

			int corr_x = diff_x * edge_iso.getWidth();
			int corr_y = diff_y * edge_iso.getHeight();

			drawPoint.x += corr_x;
			drawPoint.y += corr_y;

		} 
		g.drawImage(img, drawPoint.x, drawPoint.y, null);
	}

	protected void setPassable(boolean passable) 
	{
		this.passable = passable;
	}

	protected void setBuildable(boolean buildable) 
	{
		this.buildable = buildable;
	}
}