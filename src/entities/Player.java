package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import graphics.Screen;
import graphics.SpriteSheet;
import graphics.Texture;
import main.Main;

public class Player {
	int x, y, w, h, dir, xvel, yvel, movespeed;
	SpriteSheet sheet;
	Texture sprite;

	public Player(int x, int y, int w, int h, String path) {
		movespeed = 10;
		sprite = new Texture("XD", path, w, h);
		xvel = 0;
		yvel = 0;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

	}

	public void render(Screen screen) {
		screen.drawTexture(x, y, sprite);
	}

	public void update() {
		if (x + xvel > 270) {
			Main.getInstance().getLevel().advance(xvel);
		} else if (x + xvel < 0) {
			Main.getInstance().getLevel().advance(xvel);
		} else {
			x += xvel;
			y += yvel;
		}
	}

	// Handling Key Inputs
	public KeyListener listener = new KeyListener() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				xvel = -movespeed;
				dir = -1;
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				xvel = +movespeed;
				dir = 1;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (dir == -1) {
					xvel = 0;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (dir == 1) {
					xvel = 0;
				}
			}

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	};

	public int getXVel() {
		return xvel;
	}

	public KeyListener getListener() {
		return listener;
	}

}
