import java.io.File;

import engine.Engine;

public class Main {

	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath", new File("").getAbsolutePath());
		
		new Engine("Pathfinder Test", 800, 600);
	}
	
}
