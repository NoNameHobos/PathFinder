package engine;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import engine.display.Display;
import main.util.ResourceLoader;
import pathfinding.Map;
import pathfinding.Path;
import pathfinding.PathFinder;

public class Engine implements Game {

	public static String TITLE;
	public static int WIDTH, HEIGHT;
	
	private Display display;
	
	private Map map;
	
	private Path path;
	
	public Engine(String _title, int _width, int _height) {
		TITLE = _title;
		WIDTH = _width;
		HEIGHT = _height;
		
		display = new Display(this, _width, _height);
	}

	@Override
	public boolean closeRequested() {
		// TODO Auto-generated method stub
		System.exit(0);
		return false;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return TITLE;
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		map = ResourceLoader.loadMap("\\maps\\map1");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		map.render(g);
		
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
}
