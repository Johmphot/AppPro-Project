package GraphicElements;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class IsometricMap implements Drawable 
{

	public int HEIGHT = 512;
	public int WIDTH = 964;

	private int MAX_TILES_X = 100;
	private int MAX_TILES_Y = 100;

	private HashMap<Point, IsometricSprite> pointMap = null;
	private int last_x, total_rows;
	private Point mousePoint = null;
	private Point focusPoint = null;

	private boolean dragged = false;

	private int firstRow, firstPoint, lastRow, lastPoint;

	private boolean ready = false;
	private long total, current;
	
	public int zoomLevel = 0;
	private final int TOTAL_ROWS = 100;

	public void start() 
	{
		if (pointMap == null) 
		{
			generateBlankMap(TOTAL_ROWS);
		}
	}

	public void draw(Graphics g) 
	{

		if(!ready) return;

		if(focusPoint != null) 
		{
			firstRow = Math.max(0, focusPoint.y - MAX_TILES_Y);
			firstPoint = Math.max(0, focusPoint.x - MAX_TILES_X);
		}

		lastRow = Math.min(total_rows, firstRow + 2 * MAX_TILES_Y);
		lastPoint = Math.min(last_x, firstPoint + 2 * MAX_TILES_X);


		// Recalculates center or focus position.
		Point center = calculatePos(focusPoint);

		for (int y = firstRow; y < lastRow; y++) 
		{
			for (int x = lastPoint; x >= firstPoint; x--) 
			{
				IsometricSprite ip = pointMap.get(new Point(x, y));
				if (ip != null) {

					// Transforms sprite to normal position
					ip.transformPoly(center);

					// Deals with mouse dragging and highlighting.

					if (mousePoint != null && ip.contains(mousePoint)) 
					{
						if (!dragged) 
						{
							ip.setHighlight(true);
						} 
						else 
						{
							focusPoint = new Point(x, y);
						}
					} 
					else 
					{
						ip.setHighlight(false);
					}
					ip.draw(g);
				}
			}
		}
	}

	private Point calculatePos(Point mid) {
		IsometricSprite ip = pointMap.get(mid);
		Point tmp = ip.getCenter();
		int tmp_x = WIDTH / 2 - tmp.x;
		int tmp_y = HEIGHT / 2 - tmp.y;
		// return null;
		return new Point(tmp_x, tmp_y);
	}

	public void generateBlankMap(int rows) {
		total = rows * rows;
		current = 0;

		pointMap = new HashMap<Point, IsometricSprite>();

		int tmp_backwards = rows + 1;

		int tileRows = (rows * 2) - 1; // 9 rida kokku kui on 5x5 maatriks.
		int lastX = rows - 1;

		boolean decrease = false;

		for (int y = 0; y < tileRows; y++) 
		{

			if (y + 1 >= rows) 
			{
				decrease = true;
				tmp_backwards--;
			}

			int tilesInRow = (y < rows - 1 ? y + 1 : (y + 1 == rows ? rows : tmp_backwards));

			for (int x = 0; x < tilesInRow; x++) 
			{
				current++;
				int tmp_x = lastX + 2 * x;

				last_x = Math.max(tmp_x, last_x);

				Point tmp_point = new Point(tmp_x, y);
				IsometricSprite tmp_ip = new IsometricSprite(tmp_x, y); 
				adjustZoom(tmp_ip,zoomLevel);
				pointMap.put(tmp_point, tmp_ip);

				// Generates random focus point
				if (y + 1 == rows && x == tilesInRow / 2) 
				{
					focusPoint = tmp_point;
					tmp_ip.setFocused(true);

				}
			}
			if (!decrease)   lastX--;
			else lastX++;
		}
		total_rows = tileRows;
		System.out.println("Map generated. Rows: " + total_rows + " Points: " + total);

		linkTiles();
		ready = true;
	}
	
	public void regenerateMap()
	{
		linkTiles();
	}

	public void setMousePoint(Point mousePoint) 
	{
		this.mousePoint = mousePoint;
	}

	private void linkTiles() 
	{
		int link = 0;
		synchronized (pointMap) 
		{
			Iterator<Point> it = pointMap.keySet().iterator();
			while (it.hasNext()) 
			{

				Point tmp_p = it.next();
				IsometricSprite ip = pointMap.get(tmp_p);
				ArrayList<IsometricSprite> tmp_list = new ArrayList<IsometricSprite>();

				final int total_points = 9;
				final int total_cycles = 3;
				for (int i = 0; i < total_points; i++) 
				{
					int loop = i % total_cycles;
					int cycle = i / total_cycles;

					Point tmp = new Point(tmp_p.x + loop - cycle, tmp_p.y - 2 + loop + cycle);
					IsometricSprite tmp_ip = pointMap.get(tmp);
					if (tmp_ip != null && tmp_ip != ip) 
					{
						tmp_list.add(tmp_ip);
					}
				}
				link += tmp_list.size();
				adjustZoom(ip,zoomLevel);
				ip.setLinkedPoints(tmp_list);
			}
		}
		System.out.println("Points successfully linked. Total links generated: "+ link);
	
	}

	public void setDragged(boolean dragged) 
	{
		this.dragged = dragged;
	}

	public boolean isReady() 
	{
		return ready;
	}

	public long getTotalTiles() 
	{
		return total;
	}

	public long getCurrentTile() 
	{
		return current;
	}

	public IsometricSprite getPoint(Point p) 
	{

		for (int y = firstRow; y < lastRow; y++)
		{
			for (int x = lastPoint; x >= firstPoint; x--) 
			{
				IsometricSprite ip = pointMap.get(new Point(x, y));
				if (ip != null && ip.relativeContains(p)) 
				{
					return ip;
				}
			}
		}
		return null;
	}
	
	public void adjustZoom(IsometricSprite tile,int level) 
	{
		switch(level)
		{
		case 0: //100
			tile.setHeight(2);
			tile.setWidth(4);
			MAX_TILES_X = 100;
			MAX_TILES_Y = 100;
			break;
		case 1: //85
			tile.setHeight(3);
			tile.setWidth(6);
			MAX_TILES_X = 100;
			MAX_TILES_Y = 100;
			break;
		case 2: //65
			tile.setHeight(4);
			tile.setWidth(8);
			MAX_TILES_X = 100;
			MAX_TILES_Y = 100;
			break;
		case 3: //50
			tile.setHeight(5);
			tile.setWidth(10);
			MAX_TILES_X = 100;
			MAX_TILES_Y = 100;
			break;
		case 4: //35
			tile.setHeight(8);
			tile.setWidth(16);
			MAX_TILES_X = 100;
			MAX_TILES_Y = 100;
			break;
		case 5: //25
			tile.setHeight(10);
			tile.setWidth(20);
			MAX_TILES_X = 100;
			MAX_TILES_Y = 100;
			break;
		case 6: //17
			tile.setHeight(15);
			tile.setWidth(30);
			MAX_TILES_X = 100;
			MAX_TILES_Y = 100;
			break;
		case 7: //12
			tile.setHeight(20);
			tile.setWidth(40);
			MAX_TILES_X = 100;
			MAX_TILES_Y = 100;
			break;
		case 8: //8
			tile.setHeight(30);
			tile.setWidth(60);
			MAX_TILES_X = 100;
			MAX_TILES_Y = 100;
			break;
		case 9: //6
			tile.setHeight(40);
			tile.setWidth(80);
			MAX_TILES_X = 100;
			MAX_TILES_Y = 100;
			break;
		case 10: //5
			tile.setHeight(50);
			tile.setWidth(100);
			MAX_TILES_X = 100;
			MAX_TILES_Y = 100;
			break;
		}
	}
		
}

