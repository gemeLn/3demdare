package main;

import java.util.ArrayList;

import graphics.Screen;
import graphics.Texture;

public class Menu {
	Texture menuBackground;
	ArrayList<Texture> menuHighlight = new ArrayList<Texture>();

	public void render(Screen screen) {

		screen.drawTexture(0, 0, menuBackground);
	}

	public void update() {

	}

	public Menu() {
		menuBackground = new Texture("Menu Background", "/sprites/menubackground.png", 960, 540);
		menuHighlight.add(new Texture("Menu Play", "/sprites/menubackgroundPlay.png", 960, 540));

	}

	public void downPressed() { // active when u press down key

	}

	public void upPressed() { // active when u press up key
		menuBackground = menuHighlight.get(0);
		System.out.println("hi");
	}

	public void leftPressed() { // etc...

	}

	public void rightPressed() {

	}
}