package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.newdawn.slick.geom.Point;

public class PathFinder {
	
	public static Path findPath(Map map, Node startNode, Node endNode) {

		boolean concluded = false;

		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		
		startNode.setGCost(0);
		
		open.add(startNode);
		Node current = null;
		
		System.out.println(getDist(startNode.getPos(), endNode.getPos()));
		
		while (!concluded) {
			open.sort(new SortAStar(endNode));
			current = open.get(0);
			System.out.println("Count: (" + current.getPos().getX() + ", " + current.getPos().getY() + ")");
			closed.add(current);
			open.remove(0);
			
			if(current == endNode)
				concluded = true;
			
			ArrayList<Node> neighbours = getNeighbours(map, current);
			
			for(int i = 0; i < neighbours.size(); i++) {
				Node neighbour = neighbours.get(i);
				
				neighbour.setHCost(getDist(neighbour.getPos(), endNode.getPos()));
				
				if(closed.contains(neighbour)) {
					neighbours.remove(neighbour);
					continue;
				}
				
				boolean shorterPath = (current.getGCost() + neighbour.getCost() < neighbour.getGCost());
				if(shorterPath) System.out.println(shorterPath);
				if(!open.contains(neighbour) || shorterPath) {
					neighbour.setGCost(current.getGCost() + neighbour.getCost());
					neighbour.setFCost();
					neighbour.setParent(current);
					if(!open.contains(neighbour))
							open.add(neighbour);
				}
			}
		}
		ArrayList<Node> path = new ArrayList<Node>();
		System.out.println("----------------COMPILING PATH--------------------------------");
		do {
			path.add(current);
			current = current.getParent();
			System.out.println("Added " + current.getId() + " to path");
		} while(current != startNode);
		Collections.reverse(path);
		return new Path(path);
	}

	public static ArrayList<Node> getNeighbours(Map m, Node n) {
		ArrayList<Node> neighbours = new ArrayList<Node>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int x = (int)n.getPos().getX() + 1 - i;
				int y = (int)n.getPos().getY() + 1 - j;
				if(x < 0) x = 0;
				if(y < 0) y = 0;
				if(x > m.getWidth() - 1) x = m.getWidth() - 1;
				if(y > m.getHeight() - 1) y = m.getHeight() - 1;
				
				if(x != 0 || y != 0) {
					neighbours.add(m.getNodes()[y][x]);
				}
			}
		}
		return neighbours;
	}

	public static float getDist(Point p1, Point p2) {
		float distX = (float) (Math.abs(p1.getX() - p2.getX()));
		float distY = (float) (Math.abs(p1.getY() - p2.getY()));
		float dist2 = (float) (Math.pow(distX, 2) + Math.pow(distY, 2));
		System.out.println(distX + " " + distY);
		return (float) (Math.pow(dist2, 0.5));
	}
}

class SortAStar implements Comparator<Node> {

	private Node goal;

	public SortAStar(Node g) {
		goal = g;
	}
	public int compare(Node n1, Node n2) {
		float h1 = n1.getHCost();
		float h2 = n2.getHCost();

		float g1 = n1.getGCost();
		float g2 = n2.getGCost();

		float f1 = h1 + g1;
		float f2 = h2 + g2;

		if (f1 != f2) {
			return (int) Math.signum(f1 - f2);
		} else {
			return (int) Math.signum(h1 - h2);
		}
	}

	private float getDistBetween(Point a, Point b) {
		float distX = Math.abs(a.getX() - b.getX());
		float distY = Math.abs(b.getY() - b.getY());
		return (float) (Math.pow(distX, 2) + Math.pow(distY, 2));
	}

}