package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import graphics.Screen;
import graphics.SpriteSheet;
import graphics.Texture;
import main.Animation;
import main.Main;

public class Player {
	private int x, y, w, h, dir, xvel, yvel, movespeed, jumps, totalJumps, jumpHeight;
	SpriteSheet sheet;
	Texture sprite;
	ArrayList<Hitbox> hitboxes;
	ArrayList<Hitbox> tpIn;
	ArrayList<Hitbox> tpOut;
	boolean inAir = true;
	private boolean checkYDone, checkXDone;
	boolean walking;
	boolean hitWall = false;
	boolean rwalking;
	boolean lwalking;
	boolean jumping;
	private boolean walljump = false;
	private Hitbox walljumpBox;
	private int walljumpStrict;
	Animation animation;
	SpriteSheet player;
	SpriteSheet jump;
	Texture players;
	SpriteSheet idle;
	int index, jumpingIndex, idleIndex;
	int tick;

	private final int gcap = 20;

	public Player(int x, int y, int w, int h, String path, ArrayList<Hitbox> hitboxes, ArrayList<Hitbox> tppadsIn,
			ArrayList<Hitbox> tppadsOut) {
		movespeed = 10;
		this.hitboxes = hitboxes;
		tpIn = tppadsIn;
		tpOut = tppadsOut;
		sprite = new Texture("XD", path, w, h);
		xvel = 0;
		yvel = 1;
		totalJumps = 1;
		jumps = totalJumps;
		jumpHeight = 16;
		walljumpStrict = 15;
		index = 6;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		animation = new Animation();
		players = new Texture("/sprites/walk.png", 26 * w, w);
		player = new SpriteSheet(players, w, h);
		jump = new SpriteSheet(new Texture("/sprites/jump.png", 8 * w, w), w, h);
		idle = new SpriteSheet(new Texture("/sprites/idle.png", 7 * w, w), w, h);
		sprite = player.getTexture(0, 0);
		idleIndex = 0;

	}

	public void render(Screen screen) {
		screen.drawTexture(x, y, sprite, dir == 1);
	}

	public int avg(int i1, int i2) {
		return (int) ((i1 + i2) / 2);
	}

	public ArrayList<Hitbox> getTpList(Hitbox h) {
		if (h.getType() == Hitbox.TPOUT) {
			System.out.println("Into TPOUT");
			return tpIn;
		}
		System.out.println("Into TPIN");
		return tpOut;
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
				if (h.getType() == Hitbox.TPIN || h.getType() == Hitbox.TPOUT) {
					Hitbox temp = getTpList(h).get(h.getTPID());
					if (dir == 1) {
						x = temp.x + temp.width;
						y = avg(temp.y, temp.y + temp.height) - (this.h / 2);
						if (x > 480) {
							Main.getInstance().getLevel().advance(x - 480);
							x = 480;
						}
					} else if (dir == -1) {
						x = temp.x - w;
						y = avg(temp.y, temp.y + temp.height) - (this.h / 2);
						if (x > 480) {
							Main.getInstance().getLevel().advance(x - 480);
							x = 480;
						}
					}

				} else {
					hitWall = true;
					if (inAir) {
						// Walljump
						if (h.getType() != Hitbox.NOJUMP && h.checkJump()) {
							walljump = true;
							walljumpBox = h;
							h.jumped(System.currentTimeMillis());
						}
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
					if (h.getType() == Hitbox.TPIN || h.getType() == Hitbox.TPOUT) {
						Hitbox temp = getTpList(h).get(h.getTPID());
						x = temp.x + (temp.width / 2) - w / 2;
						y = temp.y - this.h - 1;
						yvel = -jumpHeight;
						if (x > 480) {
							Main.getInstance().getLevel().advance(x - 480);
							x = 480;
						} else if (x < 0) {
							Main.getInstance().getLevel().advance(x - 480);
							x = 480;
						}
					} else {
						yvel = 1;
					}
				} else {
					// Ground
					if (h.getType() == Hitbox.TPIN || h.getType() == Hitbox.TPOUT) {
						Hitbox temp = getTpList(h).get(h.getTPID());
						x = avg(temp.x, temp.x + temp.width) - (w / 2);
						//x = temp.x*x/h.x;
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
						jumping = false;
					}
				}
				checkYDone = true;

			}

		}
		if (xbox.intersects(Main.getInstance().getLevel().getShutdown().getHitbox())) {
			xvel = 0;
			yvel = 0;
		}

		if (inAir && yvel < gcap) {
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
		if (jumping) {
			if (tick % 2 == 0) {
				sprite = jump.getTexture(jumpingIndex, 0);
				jumpingIndex++;
				if (jumpingIndex == 8)
					jumpingIndex = 7;
			}
		} else if (rwalking || lwalking) {
			if (tick % 3 == 0) {
				sprite = player.getTexture(index, 0);
				index++;
				if (index == 26)
					index = 7;
			}
		} else {
			if (tick % 10 == 0) {
				sprite = idle.getTexture(idleIndex, 0);
				idleIndex++;
				if (idleIndex == 7)
					idleIndex = 0;
			}
		}
		tick++;
	}

	public void jump() {
		if (walljump
				&& new Hitbox(x - walljumpStrict, y - walljumpStrict, x + w + walljumpStrict, y + h + walljumpStrict)
						.intersects(walljumpBox)) {
			walljump = false;
			yvel = (int) (-jumpHeight * 5 / 4);
			jumping = true;
			jumpingIndex = 0;
		} else if (jumps > 0) {
			inAir = true;
			yvel = -jumpHeight;
			jumps--;
			jumping = true;
			jumpingIndex = 0;
		}

	}

	// Handling Key Inputs
	public KeyListener listener = new KeyListener() {

		public void keyPressed(KeyEvent e) {
			if (Main.getInstance().getState() == Main.State.Game) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					walking = true;
					lwalking = true;
					xvel = -movespeed;
					dir = -1;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					rwalking = true;
					walking = true;
					xvel = +movespeed;
					dir = 1;
				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					jump();
				}
			}

			// Menu Key Inputs
			if (Main.getInstance().getState() == Main.State.Menu) {

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					Main.getInstance().getMenu().downPressed();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					Main.getInstance().getMenu().upPressed();
				}

				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					Main.getInstance().getMenu().leftPressed();
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					Main.getInstance().getMenu().rightPressed();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Main.getInstance().getMenu().enter();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (Main.getInstance().getState() == Main.State.Game) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					index = 0;
					lwalking = false;
					if (dir == -1) {
						xvel = 0;
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					index = 0;
					rwalking = false;
					if (dir == 1) {
						xvel = 0;
					}
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