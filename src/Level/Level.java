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
	int tileScale = 20;
	Player player;
	Screen screen;
	Texture bg;
	ArrayList<Hitbox> onScreen = new ArrayList<Hitbox>();
	ArrayList<String> hitboxNumbers = new ArrayList<String>();
	ArrayList<Hitbox> hitboxes = new ArrayList<Hitbox>();
	public int ScreenPosX = 0;

	public Level(Screen screen) {
		this.screen = screen;

		bg = new Texture("BG", "/sprites/bg.png", 1920, 540);
		try {
			loadLevel(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player = new Player(100, 100, 50, 50, "/sprites/xd.png", onScreen);
	}

	public void loadLevel(int level) throws IOException {
		// loads the level hitbox file
		FileReader in_file = new FileReader("src/sprites/level.lv");
		BufferedReader buff_in = new BufferedReader(in_file);
		String tempString = buff_in.readLine();
		while (tempString != null) {
			hitboxNumbers.add(tempString);
			tempString = buff_in.readLine();
		}
		buff_in.close();
		for (int i = 0; i < hitboxNumbers.size(); i++) {
			StringTokenizer tempToken = new StringTokenizer(hitboxNumbers.get(i));
			hitboxes.add(new Hitbox(tempToken.nextToken(), tempToken.nextToken(), tempToken.nextToken(),
					tempToken.nextToken(), tileScale));
		}
	}

	public void update() {
		player.update();
		for (Hitbox h : hitboxes) {
			if (onScreen(h.x,h.x+h.width)) {
				if (!onScreen.contains(h)) {
					onScreen.add(h);
				}
			} else {
				if (onScreen.contains(h)) {
					onScreen.remove(h);
				}
			}
		}
	}

	public boolean onScreen(int i1, int i2) {
		return !(i1<0&&i2<0)&&!(i1>960&&i2>960);
	}

	public void render() {
		screen.drawTexture(ScreenPosX, 0, bg);
		player.render(screen);
		for (Hitbox h : onScreen) {
			screen.drawRect(h.x, h.y, h.width, h.height, 0xffffff);
		}

	}

	public void advance(int xvel) {
		if (ScreenPosX - xvel < 0) {
			ScreenPosX -= xvel;
			for (Hitbox h : hitboxes) {
				h.shiftX(-xvel);
			}
		}

	}

	public KeyListener getListener() {
		return player.getListener();
	}
}
