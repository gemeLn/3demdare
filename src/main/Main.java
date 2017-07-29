package main;

import Level.Level;
import graphics.Screen;
import graphics.Window;

public class Main {
	enum State {
		Menu, Game;
	}

	// Declare Variables Here
	State state;
	Menu menu;
	Window window = new Window("FastWalker", 960, 540);
	Screen screen;
	Level level;
	static Main instance;
	double fps = 1000 / 60;
	long timeLR = System.currentTimeMillis();
	boolean MainLoopOn = true;

	// Methods
	public static Main getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.init();

	}

	void init() {
		instance = this;
		state = State.Game;
		//menu = new Menu();
		window.show();
		screen = window.getScreen();
		level = new Level(screen);
		window.addKeyListener(level.getListener());
		loop();
	}

	public Level getLevel() {
		return level;
	}

	private void loop() {
		while (MainLoopOn) {
			if ((double) (System.currentTimeMillis() - timeLR) > fps) {
				if (state == State.Menu) {
					menu.update();
					menu.render(screen);
				} else if (state == State.Game) {
					window.update();
					screen.clear(0xffffff);
					level.update();
					level.render();
					timeLR = System.currentTimeMillis();
				}

			}
		}
	}
}
