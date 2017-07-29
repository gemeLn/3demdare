package Level;

import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entities.Player;
import graphics.Screen;
import graphics.Texture;

public class Level {
	// Declare shit
	Player player;
	Screen screen;
	Texture bg;
	ArrayList<String> hitbox = new ArrayList<String>();
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
			hitbox.add(tempString);
			tempString = buff_in.readLine();
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
