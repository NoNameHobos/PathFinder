package pathfinding;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public class Node {

	public static final int MAX_GREEN = 150;
	public static final int MAX_RED = 150;

	private Point pos;
	private int cost;

	private Map map;

	private int id;
	
	public Node(Map map, int x, int y, int cost) {
		pos = new Point(x, y);
		this.cost = cost;
		this.map = map;
		id = x + map.getHeight() * y;
	}
	public Node(Map map, int x, int y, int cost, int id) {
		pos = new Point(x, y);
		this.cost = cost;
		this.map = map;
		this.id = id;
	}

	public void render(Graphics g) {
		float red = (float)((cost / 100.0) * MAX_RED);
		float green = (float)((1- cost / 100.0) * MAX_GREEN);
		float blue = 0;
		Rectangle r = new Rectangle(pos.getX() * Map.TW, pos.getY() * Map.TH, Map.TW, Map.TH);
		g.setColor(new Color(red/255, green/255, blue/255));
		g.fill(r);
		g.setColor(Color.black);
		g.draw(r);
		g.drawString(Integer.toString(id), pos.getX() * Map.TW, pos.getY() * Map.TH);
	}

	// Getters and Setters
	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getId() {
		return id;
	}

}
