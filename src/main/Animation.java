package main;

import java.lang.reflect.Method;

import graphics.SpriteSheet;
import graphics.Texture;

public class Animation {
	 
	String animationPlaying;
	SpriteSheet player;
	int index = 5;
	
	public Animation(){
		//player = new SpriteSheet(new Texture("/sprites/walkcyclenoborder.png",1150, 50), 50, 50);
	}
	
	public void update() throws Throwable { //this method tries to update every 60 frames
		
		Method method = getClass().getDeclaredMethod(animationPlaying);
		method.invoke(this);
		
	}
	
	public Texture walking(){
		if(index < 22)
			index++;
		return player.getTexture(index, 1);
	}
	
	public void jumping(){
		
	}
	
	
}
