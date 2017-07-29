package Level;

import java.awt.event.KeyListener;

import entities.Player;
import graphics.Screen;

public class Level {
	// Declare shit
	Player player;
	Screen screen;

	public Level(Screen screen) {
		this.screen = screen;
		player = new Player(100, 100, 50, 50, "/sprites/xd.png");
	}

	public void update() {
		player.update();
	}

	public void render() {
		player.render(screen);

	}

	public KeyListener getListener() {
		return player.getListener();
	}
}
