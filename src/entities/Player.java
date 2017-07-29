package entities;

import graphics.Screen;
import graphics.SpriteSheet;
import graphics.Texture;

public class Player {
	int x, y, w, h;
	SpriteSheet sheet;
	Texture sprite;

	public Player(int x, int y, int w, int h, String path) {
		sprite = new Texture("XD", path);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

	}

	public void render(Screen screen) {
		screen.drawTexture(x, y, sprite);
	}

	public void update() {
	}
}
