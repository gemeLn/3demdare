package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import graphics.Screen;
import graphics.SpriteSheet;
import graphics.Texture;
import main.Main;

public class Player {
	private int x, y, w, h, dir, xvel, yvel, movespeed, jumps, totalJumps, jumpHeight;
	SpriteSheet sheet;
	Texture sprite;
	ArrayList<Hitbox> hitboxes;
	ArrayList<Hitbox> tppads;
	boolean inAir = true;
	private boolean checkYDone, checkXDone;
	boolean walking;
	boolean hitWall = false;
	private long nextWallJump = 0;
	private boolean walljump = false;
	private long wallJumpCD = 1000;

	public Player(int x, int y, int w, int h, String path, ArrayList<Hitbox> hitboxes, ArrayList<Hitbox> tppads) {
		movespeed = 10;
		this.hitboxes = hitboxes;
		this.tppads = tppads;
		sprite = new Texture("XD", path, w, h);
		xvel = 0;
		yvel = 1;
		totalJumps = 2;
		jumps = totalJumps;
		jumpHeight = 16;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

	}

	public void render(Screen screen) {
		screen.drawTexture(x, y, sprite);
	}

	public int avg(int i1, int i2) {
		return (int) ((i1 + i2) / 2);
	}

	public void update() {
		// System.out.println(x);
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
				// Wall
				if (h.getType() == Hitbox.TPIN) {
					Hitbox temp = tppads.get(h.getTPID());
					if (dir == 1) {
						x = temp.x + temp.width;
						y = avg(temp.y, temp.y + temp.height)-(this.h/2);
						if (x > 480) {
							Main.getInstance().getLevel().advance(x - 480);
							x = 480;
						}
					} else if (dir == -1) {
						x = temp.x - w;
						y = avg(temp.y, temp.y + temp.height)-(this.h/2);
						if (x > 480) {
							Main.getInstance().getLevel().advance(x - 480);
							x = 480;
						}
					}

				} else {
					hitWall = true;
					if (inAir) {
						walljump = true;
					}
					if (dir == 1) {
						x = h.x - w;
					} else {
						x = h.x + h.width;
					}
				}
				checkXDone = true;

			} else if (ybox.intersects(h)) {
				if (yvel < 0) {
					// Ceiling
					if (h.getType() == Hitbox.TPIN) {
						Hitbox temp = tppads.get(h.getTPID());
						x = temp.x - w;
						y = temp.y - this.h;
						yvel = -jumpHeight;
						if (x > 480) {
							Main.getInstance().getLevel().advance(x - 480);
							x = 480;
						}
					} else {
						yvel = 1;
					}
				} else {
					// Ground
					if (h.getType() == Hitbox.TPIN) {
						Hitbox temp = tppads.get(h.getTPID());
						x = avg(temp.x, temp.x + temp.width) - (w / 2);
						y = temp.y + temp.height;
						if (x > 480) {
							Main.getInstance().getLevel().advance(x - 480);
							x = 480;
						}
					} else {
						yvel = 0;
						y = (int) (h.y - this.h);
						inAir = false;
						jumps = totalJumps;
						walljump = false;
					}
				}
				checkYDone = true;

			}

		}
		if (xbox.intersects(Main.getInstance().getLevel().getShutdown().getHitbox())) {
			xvel = 0;
			yvel = 0;
		}

		if (inAir) {
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
			walljump = false;
			yvel = -jumpHeight;
			nextWallJump = System.currentTimeMillis() + wallJumpCD;
		} else if (jumps > 0) {
			inAir = true;
			yvel = -jumpHeight;
			jumps--;
		}

	}

	// Handling Key Inputs
	public KeyListener listener = new KeyListener() {

		boolean pressed = false;

		public void keyPressed(KeyEvent e) {
			if (Main.getInstance().getState() == Main.State.Game) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					walking = true;
					xvel = -movespeed;
					dir = -1;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					walking = true;
					xvel = +movespeed;
					dir = 1;
				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					jump();
				}
			}

			if (Main.getInstance().getState() == Main.State.Menu) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (!pressed) {
						Main.getInstance().getMenu().downPressed();
						pressed = true;
					}
				}

				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (!pressed) {
						Main.getInstance().getMenu().upPressed();
						pressed = true;
					}
				}

				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (!pressed) {
						Main.getInstance().getMenu().leftPressed();
						pressed = true;
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (!pressed) {
						Main.getInstance().getMenu().rightPressed();
						pressed = true;
					}
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (Main.getInstance().getState() == Main.State.Game) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					walking = false;
					if (dir == -1) {
						xvel = 0;
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					walking = false;
					if (dir == 1) {
						xvel = 0;
					}
				}

			}
			if (Main.getInstance().getState() == Main.State.Menu) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					pressed = false;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					pressed = false;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					pressed = false;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					pressed = false;
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