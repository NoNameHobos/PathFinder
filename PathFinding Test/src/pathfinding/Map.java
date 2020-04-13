package pathfinding;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public class Map {

	public static final int TW = 48;
	public static final int TH = 48;
	
	private Node[][] nodes;
	
	private int mapHeight, mapWidth;
	private Path path;
	
	private Node startNode;
	private Node endNode;
	
	public Map(ArrayList<String> map) {
		mapHeight = map.size();
		mapWidth = map.get(0).split(" ").length;
		
		String[][] mD = new String[mapHeight][mapWidth];
		
		nodes = new Node[mapHeight][mapWidth];
		
		for(int i = 0; i < mapHeight; i++) {
			
			mD[i] = map.get(i).split(" ");
			
			for(int j = 0; j < mapWidth; j++) {
				nodes[i][j] = new Node(this, j, i, Integer.parseInt(mD[i][j]));
			}
		}
		startNode = nodes[1][0];
		endNode = nodes[1][9];
		
		path = PathFinder.findPath(this, startNode, endNode);
	}
	
	public void update(int mapWidth, int mapHeight) {
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < mapHeight; i++) {
			for(int j = 0; j < mapWidth; j++) {
				nodes[i][j].render(g);
			}
		}
		
		g.setColor(Color.orange);
		String str = Float.toString(path.getCost());
		g.drawString(str, 30, 400);
		
		if(path != null) {
			
			float x = 700;
			float y = 30;

			g.setColor(Color.white);
			g.drawString("Path", x - 50, y);
			
			int pathSize = path.getNodes().size();
			for(int i = 0; i < pathSize; i++) {
				Node n = path.getNode(i);
				Point p = n.getPos();
				g.setColor(Color.blue);
				g.drawOval(p.getX()*TW, p.getY() * TH, TW, TH);
				g.setColor(Color.white);
				g.drawString(Integer.toString(n.getId()), x - 50, y + (i + 1) * 24);
			}
			
			//Render start and finish
			g.setColor(Color.red);
			Point start = path.getNode(0).getPos();
			Point end = path.getFinal().getPos();
			g.drawOval(start.getX() * TW, start.getY() * TH, TW, TH);
			g.drawOval(end.getX() * TW, end.getY() * TH, TW, TH);
			
			g.setColor(Color.black);
			Point aStart = startNode.getPos();
			Point aEnd = endNode.getPos();
			g.drawOval(aStart.getX() * TW, aStart.getY() * TH, TW, TH);
			g.drawOval(aEnd.getX() * TW, aEnd.getY() * TH, TW, TH);

		}
	}

	
	//Getters and Setters
	public int getHeight() {
		return mapHeight;
	}

	public int getWidth() {
		return mapWidth;
	}

	public Node[][] getNodes() {
		return nodes;
	}
	
	
}
