package Level;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tilemap {

	public void init() throws FileNotFoundException{

		
	}
	
	public void loadLevel(int level) throws IOException {
		//loads the level hitbox file
		FileReader in_file = new FileReader("src/sprites/level" + level + ".lv");
		BufferedReader buff_in = new BufferedReader(in_file);
		String tempString = buff_in.readLine();
		//tempString takes first line
		ArrayList<String> levelData = new ArrayList<String>();
		//holds all the level data strings
		while(tempString != null){ //checks if the tempString reached the end of the file
			levelData.add(tempString); //adds the line to the array of level data
			tempString = buff_in.readLine(); //reads next line
		}
		for(int i = 0; i < levelData.size(); i++)
			setHitbox(levelData.get(i)); //sends line by line of levelData to setHitbox which contains all the hitbox for a level.
	}
	
	private void setHitbox(String line){
		ArrayList<Character> setHitData = new ArrayList<Character>();
		for (int i = 0;i < line.length(); i++){
		    setHitData.add(line.charAt(i));
		}
	}
}
