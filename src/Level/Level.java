 package Level;

import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entities.Hitbox;
import entities.Player;
import graphics.Screen;
import graphics.Texture;

public class Level {
	// Declare shit
	Player player;
	Screen screen;
	Texture bg;
	ArrayList<String> hitboxNumbers = new ArrayList<String>();
	ArrayList<Hitbox> hitboxs = new ArrayList<Hitbox>();
	public int ScreenPosX = 0;

	public Level(Screen screen) {
		this.screen = screen;
		player = new Player(100, 100, 50, 50, "/sprites/xd.png");
		bg = new Texture("BG", "/sprites/bg.png", 1920, 540);
	}
	
	public void loadLevel(int level) throws IOException {
		//loads the level hitbox file
		FileReader in_file = new FileReader("src/sprites/level" + level + ".lv");
		BufferedReader buff_in = new BufferedReader(in_file);
		String tempString = buff_in.readLine();
		while(tempString != null){
			hitboxNumbers.add(tempString);
			tempString = buff_in.readLine();
		}
		
		for(int i = 0; i < hitboxNumbers.size(); i++){
			StringTokenizer tempToken = new StringTokenizer(hitboxNumbers.get(i));
			hitboxs.add(new Hitbox(tempToken.nextToken(),tempToken.nextToken(),tempToken.nextToken(),tempToken.nextToken()));
		}
		
	}

	public void update() {
		player.update();
	}

	public void render() {
		screen.drawTexture(ScreenPosX, 0, bg);
		player.render(screen);

	}

	public void advance(int xvel) {
		ScreenPosX -= xvel;
	}

	public KeyListener getListener() {
		return player.getListener();
	}
}
