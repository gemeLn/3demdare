package Level;

import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entities.Hitbox;
import entities.Player;
import entities.Shutdown;
import graphics.Screen;
import graphics.Texture;

public class Level {
	// Declare shit
	int tileScale = 10;
	int tpID = 0;;
	Player player;
	Shutdown shutdown;
	Screen screen;
	Texture bg;
	ArrayList<Hitbox> onScreen = new ArrayList<Hitbox>();
	ArrayList<String> hitboxNumbers = new ArrayList<String>();
	ArrayList<Hitbox> hitboxes = new ArrayList<Hitbox>();
	ArrayList<Hitbox> tpPadsIn = new ArrayList<Hitbox>();
	ArrayList<Hitbox> tpPadsOut = new ArrayList<Hitbox>();
	public int ScreenPosX = 0;
	public static long wallCD = 800;

	public Level(Screen screen) {
		tpID = 0;
		this.screen = screen;
		shutdown = new Shutdown();
		bg = new Texture("BG", "/sprites/bg.png", 10000, 540);
		System.out.println("Bg ran");
		try {
			loadLevel(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player = new Player(100, 150, 64, 64, "/sprites/xd.png", onScreen, tpPadsIn, tpPadsOut);
	}

	public void loadLevel(int level) throws IOException {
		// loads the level hitbox file
		BufferedReader buff_in = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream("/sprites/level" + level + ".lv")));
		String tempString = buff_in.readLine();
		while (tempString != null) {
			hitboxNumbers.add(tempString);
			tempString = buff_in.readLine();
		}
		buff_in.close();
		for (int i = 0; i < hitboxNumbers.size(); i++) {
			String type;
			Hitbox hitbox;
			StringTokenizer tempToken = new StringTokenizer(hitboxNumbers.get(i));
			hitboxes.add(hitbox = new Hitbox(tempToken.nextToken(), tempToken.nextToken(), tempToken.nextToken(),
					tempToken.nextToken(), tileScale, type = tempToken.nextToken()));
			if (Integer.parseInt(type) == Hitbox.TPIN) {

				hitbox.setTPID(tpID);
				i++;
				tempToken = new StringTokenizer(hitboxNumbers.get(i));
				Hitbox hitbox2;
				hitboxes.add(hitbox2 = new Hitbox(tempToken.nextToken(), tempToken.nextToken(), tempToken.nextToken(),
						tempToken.nextToken(), tileScale, type = tempToken.nextToken()));
				hitbox2.setTPID(tpID);
				tpPadsOut.add(hitbox2);
				tpPadsIn.add(hitbox);
				tpID++;
			}

		}

	}

	public void update() {
		player.update();
		// shutdown.update();
		for (Hitbox h : hitboxes) {
			if (onScreen(h.x, h.x + h.width)) {
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
		return !(i1 < 0 && i2 < 0) && !(i1 > 960 && i2 > 960);
	}

	public void render() {
		screen.drawBG(-ScreenPosX, -ScreenPosX + 960, bg);
		shutdown.render(screen);
		player.render(screen);
		for (Hitbox h : onScreen) {
			if (h.getType() == 1) {
				screen.drawRect(h.x, h.y, h.width, h.height, 0x00ff00);
			} else {
				screen.drawRect(h.x, h.y, h.width, h.height, 0x000000);
			}
		}

	}

	public void advance(int xvel) {
		ScreenPosX -= xvel;
		for (Hitbox h : hitboxes) {
			h.shiftX(-xvel);
		}

		shutdown.getHitbox().shiftX(-xvel);
	}

	public Shutdown getShutdown() {
		return shutdown;
	}

	public KeyListener getListener() {
		return player.getListener();
	}
}