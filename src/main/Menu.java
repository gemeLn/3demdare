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
	Texture currentApp;
	Texture controlsDisplay;
	
	int currentbackground;
	int currentcontact;
	int currentapp;
	
	boolean contactOn;
	boolean contactsOn;
	boolean contactFirst;
	boolean appSelectOn;
	boolean optionsOn;
	
	ArrayList<Texture> menuHighlight = new ArrayList<Texture>();
	ArrayList<Texture> contact = new ArrayList<Texture>();
	ArrayList<Texture> app = new ArrayList<Texture>();

	
	public void render(Screen screen) {
		screen.drawTexture(0, 0, menuBackground);
	}

	public void update() {

	}

	public Menu() {
		currentbackground = 0;
		currentcontact = 0;
		currentapp = 0;
		
		contactOn = false;
		contactFirst = false;
		
		//Menu textures
		menuHighlight.add(new Texture("Menu Play", "/sprites/menubackgroundPlay.png", 960, 540));
		menuHighlight.add(new Texture("Menu Safareee", "/sprites/menubackgroundSafareee.png", 960, 540));
		menuHighlight.add(new Texture("Menu AppStore", "/sprites/menubackgroundAppstore.png", 960, 540));
		menuHighlight.add(new Texture("Menu Contacts", "/sprites/menubackgroundContacts.png", 960, 540));
		menuHighlight.add(new Texture("Menu Settings", "/sprites/menubackgroundSettings.png", 960, 540));
		menuBackground = menuHighlight.get(0); 
		
		//Contact textures
		contactBackground = new Texture("Contacts", "/sprites/contacts.png", 960, 540);
		contact.add(new Texture("Emi B.", "/sprites/contactsEmi.png", 960, 540));
		contact.add(new Texture("Dylan B.", "/sprites/contactsDylan.png", 960, 540));
		contact.add(new Texture("Geoffrey D.", "/sprites/contactsGeoffrey.png", 960, 540));
		contact.add(new Texture("Matty T.", "/sprites/contactsMatty.png", 960, 540));
		
		//App select textures
		currentApp = new Texture("Select", "/sprites/appselect.png", 960, 540);
		app.add(new Texture("Messages", "/sprites/appselectmessages.png", 960, 540));
		app.add(new Texture("Reddit", "/sprites/appselectreddit.png", 960, 540));
		app.add(new Texture("YouTube", "/sprites/appselectyoutube.png", 960, 540));
		app.add(new Texture("Snapchat", "/sprites/appselectsnapchat.png", 960, 540));
		app.add(new Texture("Internet", "/sprites/appselectinternet.png", 960, 540));
		
		//Controls
		controlsDisplay = new Texture("Controls", "/sprites/controls.png", 960, 540);
	}

	//Select stuff
	public void downPressed() { // active when u press down key
		if (contactOn == false && contactFirst == false) {
			currentbackground = 1;
			menuBackground = menuHighlight.get(currentbackground);
		}
	}

	public void upPressed() { // active when u press up key
		if (contactOn == false && contactFirst == false) {
			currentbackground = 0;
			menuBackground = menuHighlight.get(currentbackground);
		}
	}

	public void leftPressed() { // active when u press left key
		if (contactOn == false && contactFirst == false) {
			if (currentbackground > 1) {
				currentbackground --;
				menuBackground = menuHighlight.get(currentbackground);
			}
		}
		if(appSelectOn == true) {
			if (currentapp > 0) {
				currentapp --;
				menuBackground = app.get(currentapp);
			}
		}
		else if(contactOn) {
			if (currentcontact > 0) {
				currentcontact --;
				menuBackground = contact.get(currentcontact);
			}
		} 
		else {
			menuBackground = contact.get(0);
			contactOn = true;
		}
	}

	public void rightPressed() { // active when u press right key
		if (contactOn == false && contactFirst == false) {
			if (currentbackground < 4) {
				currentbackground ++;
				menuBackground = menuHighlight.get(currentbackground);
			}
		}
		if (appSelectOn == true) {
			if (currentapp < 5) {
				currentapp ++;
				menuBackground = app.get(currentapp);
			}
		}
		else if(contactOn){
			if (currentcontact < 3) {
				currentcontact ++;
				menuBackground = contact.get(currentcontact);
			}
		} 
		else {
			menuBackground = contact.get(0);
			contactOn = true;
		}
	}	
	
	//Escape!!!
	public void escape() {
		if (contactsOn == true) {
			menuBackground = menuHighlight.get(0);
		}
		else if (optionsOn == true) {
			menuBackground = menuHighlight.get(0);
		}
	}
	
	//Safareee link thing (takes you to the LD website)
	public void safareee() {
        String url = "https://ldjam.com/";

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
        else {
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
		//App select (level select)
		if (appSelectOn == true){ 
			switch(currentapp) {
				//Messages
				case 0: 
				return;
				
				//Reddit
				case 1: 
				return;
				
				//YouTube
				case 2: Main.getInstance().setState(Main.State.Game);
				return;
				
				//Snapchat
				case 3: 
				return;
				
				//Internet
				case 4: 
				return;
			}	
		}
		//Main menu
		else if (appSelectOn == false && contactsOn == false){
			switch(currentbackground) {
				//Play
				case 0: menuBackground = currentApp;
						appSelectOn = true;
				return;
			
				//Safareee
				case 1: safareee();
				return;
			
				//App Store
				case 2: 
				return;
			
				//Contacts
				case 3: menuBackground = contactBackground;
						contactsOn = true;
						contactFirst = true;
				return;
			
				//Settings
				case 4: menuBackground = controlsDisplay;
						optionsOn = true;
				return;
			}
		}
	}
}