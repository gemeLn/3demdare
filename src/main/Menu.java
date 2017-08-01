package main;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import graphics.Screen;
import graphics.Texture;

public class Menu {
	public enum State {
		contact,app,options,main, lose, win, start, end;
	}
	Texture menuBackground;
	Texture contactBackground;
	Texture currentApp;
	Texture controlsDisplay;
	Texture win;
	
	int currentbackground;
	int currentcontact;
	int currentapp;
	int yLIndex;
	int startIndex;
	
	boolean contactFirst;
	private State menuState;
	
	ArrayList<Texture> menuHighlight = new ArrayList<Texture>();
	ArrayList<Texture> contact = new ArrayList<Texture>();
	ArrayList<Texture> app = new ArrayList<Texture>();
	ArrayList<Texture> yL = new ArrayList<Texture>();
	ArrayList<Texture> start = new ArrayList<Texture>();

	
	public void render(Screen screen) {
		screen.drawTexture(0, 0, menuBackground);
	}

	public void update() {

	}

	public Menu() {
		currentbackground = 0;
		currentcontact = 0;
		currentapp = 0;
		yLIndex = 0;
		setMenuState(State.start);
		
		//Menu textures
		menuHighlight.add(new Texture("Menu Play", "/sprites/menubackgroundPlay.png", 960, 540));
		menuHighlight.add(new Texture("Menu Safareee", "/sprites/menubackgroundSafareee.png", 960, 540));
		menuHighlight.add(new Texture("Menu AppStore", "/sprites/menubackgroundAppstore.png", 960, 540));
		menuHighlight.add(new Texture("Menu Contacts", "/sprites/menubackgroundContacts.png", 960, 540));
		menuHighlight.add(new Texture("Menu Settings", "/sprites/menubackgroundSettings.png", 960, 540));
		
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
		
		//You lose screen textures
		yL.add(new Texture("ylAppSelect", "/sprites/yLAppSelect.png", 960, 540));
		yL.add(new Texture("ylTryAgain", "/sprites/yLTryAgain.png", 960, 540));
		yL.add(new Texture("ylMenu", "/sprites/yLMenu.png", 960, 540));
		
		//Controls
		controlsDisplay = new Texture("Controls", "/sprites/controls.png", 960, 540);
		
		win = new Texture("win", "/sprites/win.png",960, 540);
		
		start.add(new Texture("Controls", "/sprites/M0.png",960, 540));
		start.add(new Texture("/sprites/M1.png", 960, 540));
		start.add(new Texture("/sprites/M2.png", 960, 540));
		start.add(new Texture("/sprites/M3.png", 960, 540));
		
		menuBackground = start.get(0); 
	}

	//Select stuff
	public void downPressed() { // active when u press down key
		if (getMenuState() == State.main) {
			currentbackground = 1;
			menuBackground = menuHighlight.get(currentbackground);
		} 
		else if(menuState == State.app){
			if(currentapp != 4){
				currentapp = 4;
				menuBackground = app.get(currentapp);
			}
		}
		else if(menuState == State.lose) {
			if(yLIndex == 1){
				yLIndex = 2;
				menuBackground = yL.get(yLIndex);
			}
		}
	}

	public void upPressed() { // active when u press up key
		if (getMenuState() == State.main) {
			currentbackground = 0;
			menuBackground = menuHighlight.get(currentbackground);
		}
		else if (getMenuState() == State.app) {
			if(currentapp == 4){
				currentapp = 0;
				menuBackground = app.get(currentapp);
			}
		}
		else if(menuState == State.lose) {
			if(yLIndex == 2){
				yLIndex = 1;
				menuBackground = yL.get(yLIndex);
			}
		}
	}

	public void leftPressed() { // active when u press left key
		if (getMenuState() == State.main) {
			if (currentbackground > 1) {
				currentbackground --;
				menuBackground = menuHighlight.get(currentbackground);
			}
		}else if(getMenuState() == State.app) {
			if (currentapp > 0) {
				currentapp --;
				menuBackground = app.get(currentapp);
			}
		}
		else if(getMenuState() == State.contact) {
			if (currentcontact > 0) {
				currentcontact --;
				menuBackground = contact.get(currentcontact);
			}
		}else if(menuState == State.lose) {
			if(yLIndex != 0){
				yLIndex = 0;
				menuBackground = yL.get(yLIndex);
			}
		}
	}

	public void rightPressed() { // active when u press right key
		if (getMenuState() == State.main) {
			if (currentbackground < 4) {
				currentbackground ++;
				menuBackground = menuHighlight.get(currentbackground);
			}
		}
		if (getMenuState() == State.app) {
			if (currentapp < 4) {
				currentapp ++;
				menuBackground = app.get(currentapp);
			}
		}
		else if(getMenuState() == State.contact){
			if (currentcontact < 3) {
				currentcontact ++;
				menuBackground = contact.get(currentcontact);
			}
		}
		else if(menuState == State.lose) {
			if(yLIndex == 0){
				yLIndex = 1;
				menuBackground = yL.get(1);
			}
		}
		else if(menuState == State.start){
			startIndex++;
			if(startIndex == 4){
				menuState = State.main;
				menuBackground = menuHighlight.get(0);
			} else{
				menuBackground = start.get(startIndex);
			}
				
		}
	}	
	
	//Escape!!!
	public void escape() {
		if (getMenuState() == State.contact) {
			setMenuState(State.main);
			menuBackground = menuHighlight.get(0);
			currentbackground = 0;
		}
		else if (getMenuState() == State.options) {
			setMenuState(State.main);
			menuBackground = menuHighlight.get(0);
			currentbackground = 0;
		}
		System.out.println("test");
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
	public void enter() throws IOException {
		//App select (level select)
		if (getMenuState() == State.app){ 
			switch(currentapp) {
				//Messages
				case 0: 
					Main.getInstance().getLevel().loadLevel(1);
					Main.getInstance().setState(Main.State.Game);
					return;
					
				//Reddit
				case 1: 
					Main.getInstance().getLevel().loadLevel(2);
					Main.getInstance().setState(Main.State.Game);
					return;
				
				//YouTube
				case 2: 
					Main.getInstance().getLevel().loadLevel(3);
					Main.getInstance().setState(Main.State.Game);
					return;
				
				//Snapchat
				case 3: 
					Main.getInstance().getLevel().loadLevel(4);
					Main.getInstance().setState(Main.State.Game);
					return;
				
				//Internet
				case 4: 
					Main.getInstance().getLevel().loadLevel(5);
					Main.getInstance().setState(Main.State.Game);
					return;
			}	
		}
		//Main menu
		else if (getMenuState() == State.main){
			switch(currentbackground) {
				//Play
				case 0: menuBackground = app.get(0);
					setMenuState(State.app);
					return;
			
				//Safareee
				case 1: safareee();
					return;
			
				//App Store
				case 2: 
					return;
			
				//Contacts
				case 3: menuBackground = contactBackground;
						setMenuState(State.contact);
						currentcontact = -1;
						return;
			
				//Settings
				case 4: 
					menuBackground = controlsDisplay;
					setMenuState(State.options);
					return;
			}
		}
		else if (getMenuState() == State.lose){
			switch(yLIndex) {
				//yLAppSelect
				case 0:
					menuBackground = app.get(0);
					setMenuState(State.app);
					return;
			
				//yLMenu
				case 1:
					Main.getInstance().getLevel().loadLevel(Main.getInstance().getLevel().getLevel());
					Main.getInstance().setState(Main.State.Game);
					return;
			
				//yLTryAgain
				case 2: 
					menuBackground = menuHighlight.get(0);
					setMenuState(State.main);
					return;
			
			}
		} else if (menuState == State.win){
			menuBackground = app.get(0);
			menuState = State.app;
		}
	}

	public State getMenuState() {
		return menuState;
	}

	public void setMenuState(State menuState) {
		this.menuState = menuState;
	}

	public void setLoseBG() {
		menuBackground = yL.get(0);
	}

	public void setBGW() {
		menuBackground = win;
	}
}