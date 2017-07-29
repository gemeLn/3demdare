package main;

import graphics.Screen;
import graphics.Texture;

public class Menu {
	public void render(Screen screen) {
		screen.clear(0x000000);
		screen.drawTexture(0, 0, Menubackground);
	}

	public void update() {

	}

	public Menu() {
		Menubackground = new Texture("name", "/sprites/___.png", 1960, 540);
	}

	Texture Menubackground;

}