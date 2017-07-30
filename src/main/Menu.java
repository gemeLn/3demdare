package main;

import java.util.ArrayList;

import graphics.Screen;
import graphics.Texture;

public class Menu {
	Texture menuBackground;
	int currentbackground;
	ArrayList<Texture> menuHighlight = new ArrayList<Texture>();

	public void render(Screen screen) {

		screen.drawTexture(0, 0, menuBackground);
	}

	public void update() {

	}

	public Menu() {
		currentbackground = 0;
		
		menuBackground = new Texture("Menu Background", "/sprites/menubackground.png", 960, 540);
		menuHighlight.add(new Texture("Menu Play", "/sprites/menubackgroundPlay.png", 960, 540));
		menuHighlight.add(new Texture("Menu Safareee", "/sprites/menubackgroundSafareee.png", 960, 540));
		menuHighlight.add(new Texture("Menu AppStore", "/sprites/menubackgroundAppstore.png", 960, 540));
		menuHighlight.add(new Texture("Menu Contacts", "/sprites/menubackgroundContacts.png", 960, 540));
		menuHighlight.add(new Texture("Menu Settings", "/sprites/menubackgroundSettings.png", 960, 540));
	}

	public void downPressed() { // active when u press down key
		currentbackground = 1;
		menuBackground = menuHighlight.get(currentbackground);
	}

	public void upPressed() { // active when u press up key
		currentbackground = 0;
		menuBackground = menuHighlight.get(currentbackground);
		//System.out.println("hi");
	}

	public void leftPressed() { // etc...
		if (currentbackground > 1) {
			currentbackground --;
		}
		
		menuBackground = menuHighlight.get(currentbackground);
	}

	public void rightPressed() {
		if (currentbackground < 4) {
			currentbackground ++;
		}
		
		menuBackground = menuHighlight.get(currentbackground);
	}
}