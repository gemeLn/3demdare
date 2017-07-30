package main;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import graphics.Screen;
import graphics.Texture;

public class Menu {
	Texture menuBackground;
	Texture contactBackground;
	
	int currentbackground;
	int currentcontact;
	
	ArrayList<Texture> menuHighlight = new ArrayList<Texture>();
	ArrayList<Texture> contact = new ArrayList<Texture>();

	
	public void render(Screen screen) {
		screen.drawTexture(0, 0, menuBackground);
	}

	public void update() {

	}

	public Menu() {
		currentbackground = 0;
		currentcontact = 0;
		
		menuBackground = new Texture("Menu Background", "/sprites/menubackground.png", 960, 540);
		menuHighlight.add(new Texture("Menu Play", "/sprites/menubackgroundPlay.png", 960, 540));
		menuHighlight.add(new Texture("Menu Safareee", "/sprites/menubackgroundSafareee.png", 960, 540));
		menuHighlight.add(new Texture("Menu AppStore", "/sprites/menubackgroundAppstore.png", 960, 540));
		menuHighlight.add(new Texture("Menu Contacts", "/sprites/menubackgroundContacts.png", 960, 540));
		menuHighlight.add(new Texture("Menu Settings", "/sprites/menubackgroundSettings.png", 960, 540));
		
		contactBackground = new Texture("Emi B.", "", 960, 540);
		contact.add(new Texture("Dylan B.", "", 960, 540));
		contact.add(new Texture("Geoffrey D.", "", 960, 540));
		contact.add(new Texture("Matty T.", "", 960, 540));
	}

	//Select stuff
	public void downPressed() { // active when u press down key
		currentbackground = 1;
		menuBackground = menuHighlight.get(currentbackground);
	}

	public void upPressed() { // active when u press up key
		currentbackground = 0;
		menuBackground = menuHighlight.get(currentbackground);
	}

	public void leftPressed() { // active when u press left key
		if (currentbackground > 1) {
			currentbackground --;
		}
		else if (currentcontact > 1) {
			currentcontact --;
		}
		
		menuBackground = menuHighlight.get(currentbackground);
	}

	public void rightPressed() { // active when u press right key
		if (currentbackground < 4) {
			currentbackground ++;
		}
		else if (currentcontact < 1) {
			currentcontact ++;
		}
		
		menuBackground = menuHighlight.get(currentbackground);
	}	
	
	//Safareee link
	public void safareee() {
        String url = "https://ldjam.com/";

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	
	//When you press enter on selected thing
	public void enter() {
		switch(currentbackground) {
			//Play
			case 0: Main.getInstance().setState(Main.State.Game);
			return;
			
			//Safareee
			case 1: safareee();
			return;
			
			//App Store
			case 2:
			return;
			
			//Contacts
			case 3: menuBackground = contactBackground;
			return;
			
			//Settings
			case 4:
			return;
		}
	}
}