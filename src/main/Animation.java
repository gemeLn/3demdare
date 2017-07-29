package main;

import java.lang.reflect.Method;

public class Animation {
	 
	String animationPlaying;
	
	public void update() throws Throwable { //this method tries to update every 60 frames
		
		Method method = getClass().getDeclaredMethod(animationPlaying);
		method.invoke(this);
		
	}
	
	public void walking(){
		
	}
	
	
}
