package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import graphics.Screen;
import graphics.SpriteSheet;
import graphics.Texture;

public class Player {
	int x, y, w, h, dir;
	SpriteSheet sheet;
	Texture sprite;

	public Player(int x, int y, int w, int h, String path) {
		sprite = new Texture("XD", path, w, h);

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

	public KeyListener listener = new KeyListener() {
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyCode());

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	};

	public KeyListener getListener() {
		return listener;
	}

}
