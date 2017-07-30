package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import graphics.Screen;
import graphics.SpriteSheet;
import graphics.Texture;
import main.Main;

public class Player {
	private int x, y, w, h, dir, xvel, yvel, movespeed, defaultspeed, sprintspeed, jumps, totalJumps, jumpHeight;
	SpriteSheet sheet;
	Texture sprite;
	ArrayList<Hitbox> hitboxes;
	boolean inAir = true;
	private boolean checkYDone, checkXDone;
	boolean walking;
	boolean hitWall = false;
	boolean walljump = true;
	long nextWallJump = 0;

	public Player(int x, int y, int w, int h, String path, ArrayList<Hitbox> hitboxes) {
		defaultspeed = 10;
		movespeed = defaultspeed;
		sprintspeed = 20;
		this.hitboxes = hitboxes;
		sprite = new Texture("XD", path, w, h);
		xvel = 0;
		yvel = 1;
		totalJumps = 2;
		jumps = totalJumps;
		jumpHeight = 15;

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
		// System.out.println(yvel);
		Hitbox ybox = new Hitbox(x, y + yvel + 1, w, h);
		// New X Hitbox
		Hitbox xbox = new Hitbox(x + xvel, y, w, h);
		hitWall = false;
		inAir = true;
		checkXDone = false;
		checkYDone = false;

		for (Hitbox h : hitboxes) {
			if (checkXDone && checkYDone) {
				break;
			}
			if (xbox.intersects(h)) {
				hitWall = true;
				if (dir == 1) {
					x = h.x - w;
				} else {
					x = h.x + h.width;
				}
				if (inAir) {
					walljump = true;
				}
				checkXDone = true;
			} else if (ybox.intersects(h)) {
				if (yvel < 0) {
					// ceiling
					yvel = 1;
				} else {
					// floor
					walljump = false;
					yvel = 0;
					y = (int) (h.y - this.h);
					inAir = false;
					jumps = totalJumps;
				}
				checkYDone = true;

			}

		}
		if (xbox.intersects(Main.getInstance().getLevel().getShutdown().getHitbox())) {
			xvel = 0;
			yvel = 0;
		}

		if (inAir) {
			// System.out.println("doing");
			yvel++;
		}
		y += yvel;
		// Shifting Background
		if (x + xvel > 480 || x + xvel < 0) {
			if (hitWall)
				Main.getInstance().getLevel().advance(0);
			else
				Main.getInstance().getLevel().advance(xvel);

		} else {
			if (!hitWall)
				x += xvel;
		}
	}

	public void jump() {
		if (walljump && System.currentTimeMillis() > nextWallJump) {
			nextWallJump = System.currentTimeMillis() + 800;
			if (dir == -1) {
				//xvel = 10;

			} else {
				//xvel = -10;
			}
			yvel = -jumpHeight;
			walljump = false;
		} else if (jumps > 0) {
			inAir = true;
			y--;
			yvel = -jumpHeight;
			jumps--;
		}

	}

	// Handling Key Inputs
	public KeyListener listener = new KeyListener() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				jump();
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				//if (!walljump) {
					walking = true;
					xvel = movespeed;
					dir = 1;
				//}
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				//if (!walljump) {
					walking = true;
					xvel = -movespeed;
					dir = -1;
				//}
			}

			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				movespeed = sprintspeed;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				walking = false;
				if (dir == -1) {
					xvel = 0;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				walking = false;
				if (dir == 1) {
					xvel = 0;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				movespeed = defaultspeed;
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