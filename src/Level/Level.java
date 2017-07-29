package Level;

import java.awt.event.KeyListener;

import entities.Player;
import graphics.Screen;
import graphics.Texture;

public class Level {
	// Declare shit
	Player player;
	Screen screen;
	Texture bg;
	public int ScreenPosX = 0;

	public Level(Screen screen) {
		this.screen = screen;
		player = new Player(100, 100, 50, 50, "/sprites/xd.png");
		bg = new Texture("BG", "/sprites/bg.png", 1920, 540);
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
