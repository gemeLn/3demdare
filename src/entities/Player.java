package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import graphics.Screen;
import graphics.SpriteSheet;
import graphics.Texture;
import main.Main;

public class Player {
	private int x, y, w, h, dir, xvel, yvel, movespeed, jumps, totalJumps,jumpHeight;
	SpriteSheet sheet;
	Texture sprite;
	ArrayList<Hitbox> hitboxes;
	boolean inAir = false;
	private boolean checkYDone, checkXDone;

	public Player(int x, int y, int w, int h, String path, ArrayList<Hitbox> hitboxes) {
		movespeed = 10;
		this.hitboxes = hitboxes;
		sprite = new Texture("XD", path, w, h);
		xvel = 0;
		yvel = 1;
		totalJumps = 2;
		jumps = totalJumps;
		jumpHeight = 12;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

	}

	public void render(Screen screen) {
		screen.drawTexture(x, y, sprite);
	}

	public void update() {
		// Collision Detect
		// New Y Hitbox
		Hitbox ybox = new Hitbox(x, y + yvel + 1, w, h);
		// New X Hitbox
		Hitbox xbox = new Hitbox(x + xvel, y, w, h);
		inAir = true;
		checkXDone = false;
		checkYDone = false;
		for (Hitbox h : hitboxes) {
			if (checkXDone && checkYDone) {
				break;
			}
			if (ybox.intersects(h)) {
				yvel = 0;
				y = (int) (h.y - this.h);
				inAir = false;
				checkYDone = true;
				jumps = totalJumps;
			}
			if (xbox.intersects(h)) {
				xvel = 0;
				if (inAir) {
					jumps = 1;
				}
				checkXDone = true;
			}
		}
		if(xbox.intersects(Main.getInstance().getLevel().getShutdown().getHitbox())){
			xvel = 0;
			yvel = 0;
		}
		
		if (inAir) {
			yvel++;
		}
		y += yvel;
		// Shifting Background
		if (x + xvel > 480||x+xvel<0) {
			Main.getInstance().getLevel().advance(xvel);
			
		} else {
			x += xvel;
		}
	}

	public void jump() {

		if (jumps > 0) {
			inAir = true;
			yvel = -jumpHeight;
			jumps--;
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
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				jump();
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