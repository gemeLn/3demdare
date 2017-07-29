package entities;

import graphics.Screen;

public class Shutdown {
	Hitbox hitbox;
	long tick;
	
	public Shutdown(){
		hitbox = new Hitbox(0, 0, 0, 540);
	}
	
	public void update(){
		tick++;
		if(tick%1 == 0){
			hitbox.width++;
		}
	}
	
	public void render(Screen screen){
		screen.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height, 0x000000);
	}
	
}