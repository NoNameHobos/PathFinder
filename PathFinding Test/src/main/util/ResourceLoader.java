package main.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import pathfinding.Map;

public class ResourceLoader {

	public static Map loadMap(String dir) {
		File file = new File(new File("").getAbsolutePath() + dir);
		BufferedReader br;
		ArrayList<String> mapData = new ArrayList<String>();
		
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				mapData.add(st);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Map(mapData);
	}

}
