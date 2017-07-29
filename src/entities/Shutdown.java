package entities;

import graphics.Screen;

public class Shutdown {
	private Hitbox hitbox;
	long tick = -60;
	boolean gameStart;
	
	public Shutdown(){
		setHitbox(new Hitbox(0, 0, 0, 540));
	}
	
	public void update(){
		tick++;
		if(tick%1 == 0 && tick > 0){
			getHitbox().width+= 4;
		}
	}
	
	public void render(Screen screen){
		screen.fillRect(getHitbox().x, getHitbox().y, getHitbox().width, getHitbox().height, 0x000000);
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}
	
	public void changeGameStart(boolean change){
		gameStart = change;
	}
	
}