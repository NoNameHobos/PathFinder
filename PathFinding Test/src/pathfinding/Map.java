package pathfinding;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public class Map {

	public static final int TW = 48;
	public static final int TH = 48;
	
	private Node[][] nodes;
	
	private int mapHeight, mapWidth;
	private ArrayList<Path> paths;
	
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
		PathFinder pf = new PathFinder((mapHeight * mapWidth)*(mapHeight * mapWidth), true);
		paths = pf.findPath(this, nodes[0][0], nodes[0][mapWidth - 1]);
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
		for(int i = 0; i < paths.size(); i++) {
			String str = Integer.toString(i+1) + ". " + Float.toString(paths.get(i).getCost());
			g.drawString(str, 30, 400 + i * 30);
		}
		
		Path path = paths.get(0);
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
			
			int i = 0;
			g.drawString("Visited", x, y);
			for(Node n : path.getVisited()) {
				i++;
				g.drawString(Integer.toString(n.getId()), x, y + i * 24);
			}
			
			//Render start and finish
			g.setColor(Color.red);
			Point start = path.getNode(0).getPos();
			Point end = path.getFinal().getPos();
			g.drawOval(start.getX() * TW, start.getY() * TH, TW, TH);
			g.drawOval(end.getX() * TW, end.getY() * TH, TW, TH);

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
