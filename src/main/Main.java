package main;

import Level.Level;
import graphics.Screen;
import graphics.Window;

public class Main {
	public enum State {
		Menu, Game;
	}

	// Declare Variables Here
	private State state;
	Menu menu;
	Window window = new Window("FastWalker", 960, 540);
	Screen screen;
	Level level;
	static Main instance;
	double fps = 1000.0 / 100.0;
	long timeLR = System.currentTimeMillis();
	boolean MainLoopOn = true;

	// Methods
	public static Main getInstance() {
		return instance;
	}

	public static void main(String[] args) throws InterruptedException {
		Main main = new Main();
		main.init();

	}

	void init() throws InterruptedException {
		instance = this;
		setState(State.Menu);
		menu = new Menu();
		window.show();
		screen = window.getScreen();
		level = new Level(screen);
		window.addKeyListener(level.getListener());
		loop();
	}

	public Level getLevel() {
		return level;
	}
	
	public Menu getMenu() {
		return menu;
	}

	private void loop() throws InterruptedException {
		int tick = 60;
		int dTick = tick;
		long start = System.currentTimeMillis();
		Thread.sleep(1000);
		long timeLR = System.currentTimeMillis();
		while (MainLoopOn) {
			if ((double) (System.currentTimeMillis() - timeLR) > fps) {
				if (getState() == State.Menu) {
					window.update();
					screen.clear(0x000000);
					menu.update();
					menu.render(screen);
				} else if (getState() == State.Game) {
					window.update();
					screen.clear(0xffffff);
					level.update();
					level.render();
					if(start + 1000.0 < System.currentTimeMillis()){
						start = System.currentTimeMillis();
						dTick = tick;
						tick = 0;
					}	
					screen.drawString("" + dTick, 20, 20);
					tick++;
					timeLR = System.currentTimeMillis();
				}

			}
		}
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
