package entities;

import graphics.Screen;

public class Shutdown {
	private Hitbox hitbox;
	public long tick = -60;
	boolean gameStart;

	public Shutdown() {
		setHitbox(new Hitbox(0, 0, 0, 540));
		hitbox.setType(Hitbox.SHUTDOWN);
	}

	public void update() {
		tick++;
		if (tick % 1 == 0 && tick > 0) {
			getHitbox().width += 7;
		}
	}

	public void minusWidth(int i) {
		hitbox.width -= i;
	}
	
	public void render(Screen screen) {
		screen.fillRect(0, 0, getHitbox().width, getHitbox().height, 0x000000);
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}

	public void changeGameStart(boolean change) {
		gameStart = change;
	}

}