package Level;

import entities.Player;
import graphics.Screen;

public class Level {
	// Declare shit
	Player player = new Player(100, 100, 100, 100, "/sprites/xd.png");
	Screen screen;

	public Level(Screen screen) {
		this.screen = screen;
	}

	public void update() {
		player.update();
	}

	public void render() {
		player.render(screen);

	}
}
