package graphicElements;
import java.awt.Point;


public class Translator 
{
	private static int subtractor = 0;
	
	//convert to ordinary (x,y) coordinate 
	public static Point toGrid(IsometricSprite p) 
	{
		subtractor = getSubtractor(p);
		int xn = p.getX() - subtractor; 
		int yn = p.getY() - xn;
		Point result = new Point(xn,yn);
		return result;
	}
	
	public static Point toIso(Point p)
	{
		int oy = p.y + p.x;
		int ox = p.x + (99 - p.y);
		Point result = new Point(ox,oy);
		return result;
	}
	
	public static int getSubtractor(IsometricSprite p)
	{
		int x = p.getX();
		int y = p.getY();
		int target = 0;
		int cnt=0;
		int index = 0;
		int[] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30
						,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60
						,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90
						,91,92,93,94,95,96,97,98,99};
		
		for(int add = 99;add<=297;add++)
		{
			if(x+y == add) 
			{
				index = (add-99)/2;
				break;
			}
		}
		target = array[index];
		int xp = x;
		while (xp>target) 
		{
			xp--;
			cnt++;
		}
		return cnt;
		
	}
}
