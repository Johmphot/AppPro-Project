//not use
package primary;

import java.awt.*;
import java.util.*;

public class WeatherScence {
	private static int rainHeight = 5;
	private static Random random = new Random();

	public static void sunny(Graphics g, int width, int height) {
		g.setColor(new Color(0, 255, 255));
		g.fillRect(0, 0, width, height);
	}

	public static void cloudy(Graphics g, int width, int height) {
		g.setColor(new Color(70, 130, 180));
		g.fillRect(0, 0, width, height);
	}

	public static void raining(Graphics g, int width, int height) {
		g.setColor(new Color(0, 0, 128));
		g.fillRect(0, 0, width, height);
	}

	public static void rainingAnimated(Graphics g, int width, int height) {
		g.setColor(Color.WHITE);
		int i = 0;
		int x = 0;
		int y = 0;
		while (i < 100) {
			i++;
			x = random.nextInt(width);
			y = random.nextInt(height);
			g.drawLine(x, y, x, y + rainHeight);
		}
	}

	public static void snowing(Graphics g, int width, int height) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);
	}

	public static void snowingAnimated(Graphics g, int width, int height) {
		g.setColor(Color.WHITE);
		int i = 0;
		int x = 0;
		int y = 0;
		while (i < 100) {
			i++;
			x = random.nextInt(width);
			y = random.nextInt(height);
			g.fillOval(x, y, 10, 10);

		}
	}

}
