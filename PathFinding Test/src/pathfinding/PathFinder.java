package pathfinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.geom.Point;

public class PathFinder {
	
	private int MAX_SEARCHES;
	private boolean heuristics;

	public PathFinder(int maxSearches, boolean heuristics) {
		MAX_SEARCHES = maxSearches;
		this.heuristics = heuristics;
	}
	
	public ArrayList<Path> findPath(Map map, Node startNode, Node endNode) {
		Set<Node> visited = new HashSet<Node>();
        ArrayList<Path> paths = new ArrayList<Path>();
        ArrayList<Node> open = new ArrayList<Node>();
        
        open.add(startNode);
        
        Node currentNode = startNode;
        Node nextNode;
        
        Path currentPath = new Path(startNode);
        Path finalPath = null;
        
	}
	
	public Node findNext(Map map, Node current, Node goal, ArrayList<Node> options) {
		System.out.println(current.getId());
		if(!options.contains(goal)) {
			options.sort(new SortAStar(goal, heuristics));
			return options.get(0);
		} //Else add the goal
		else {
			return goal;
		}
	}

	public ArrayList<Node> visitNode(Map map, Node n, Set<Node> visited) {
		Node[][] nodes = map.getNodes();
		
		Set<Node> nearestNodes = new HashSet<Node>();
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				Point currentPos = n.getPos();
				
				int x = (int) Math.abs(currentPos.getX() + j - 1);
				int y = (int) Math.abs(currentPos.getY() + i - 1);
				if(currentPos.getX() != x || currentPos.getY() != y)
					if(x < map.getWidth() && y < map.getHeight()) {
						if(!visited.contains(nodes[y][x]))
							nearestNodes.add(nodes[y][x]);
					}
			}
		}
		
		Object[] oArray = nearestNodes.toArray();
		ArrayList<Node> nodeList = new ArrayList<Node>();
		
		for(int i = 0; i < oArray.length; i++) {
			nodeList.add((Node) oArray[i]);
		}
		
		return nodeList;
	}

	public static Path idealPath(ArrayList<Path> paths) {
		Path currentPath = null;
		for(Path path : paths) {
			if(currentPath == null || path.getCost() < currentPath.getCost())
				currentPath = path;
		}
		return currentPath;
	}

	public float getDist(Point p1, Point p2) {
		float distX = (float)(Math.abs(p1.getX() - p2.getX()));
		float distY = (float)(Math.abs(p1.getY() - p2.getY()));
		float dist2 = (float)(Math.pow(distX, 2) + Math.pow(distY, 2));
		return (float)(Math.pow(dist2, 1/2));
	}
}

class SortAStar implements Comparator<Node> {

	private Node goal;
	
	private boolean useHeuristic = false;

	public SortAStar(Node g, boolean useHeuristic) {
		goal = g;
		this.useHeuristic = useHeuristic;
	}
	
	public SortAStar(Node g) {
		goal = g;
	}
	
	public int compare(Node n1, Node n2) {
		float h1 = getDistBetween(n1.getPos(), goal.getPos());
		float h2 = getDistBetween(n2.getPos(), goal.getPos());
		
		float g1 = n1.getPathCost();
		float g2 = n2.getPathCost();
	}
	
	private float getDistBetween(Point a, Point b) {
		float distX = Math.abs(a.getX() - b.getX());
		float distY = Math.abs(b.getY() - b.getY());
		return (float)(Math.pow(distX, 2) + Math.pow(distY, 2));
	}
	
}

class SortLowCost implements Comparator<Path> {
	public int compare(Path p1, Path p2) {
		if(p1.getCost() != p2.getCost()) {
		if(p1.getCost() < p2.getCost())
			return -1;
		else return 1;
		} else return 0;
	}
}
