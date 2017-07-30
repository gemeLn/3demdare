package main;

import graphics.Screen;
import graphics.Texture;

public class Menu {
	public void render(Screen screen) {
		
		screen.drawTexture(0, 0, Menubackground);
	}

	public void update() {

	}

	public Menu() {
		Menubackground = new Texture("Menu Background", "/sprites/menubackground.png", 960, 540);
	}

	Texture Menubackground;

}