package Level;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tilemap {
	
	int rowCounter;
	ArrayList<String> hitbox = new ArrayList<String>();
	
	public void init() throws FileNotFoundException{

		
	}
	
	public void loadLevel(int level) throws IOException {
		rowCounter = 0; //resets rowCounter
		//loads the level hitbox file
		FileReader in_file = new FileReader("src/sprites/level" + level + ".lv");
		BufferedReader buff_in = new BufferedReader(in_file);
		StringTokenizer tempString = new StringTokenizer(buff_in.readLine());
		
	}
	
	private void setHitbox(StringTokenizer tempString){
		String tempString.nextToken();
	}
}
