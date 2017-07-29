package main;

import Level.Level;
import graphics.Screen;
import graphics.Window;

public class Main {
	// Declare Variables Here
	Window window = new Window("FastWalker", 960, 540);
	Screen screen;
	Level level;
	double fps = 1000 / 60;
	long timeLR = System.currentTimeMillis();
	boolean MainLoopOn = true;

	// Methods
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
	}

	void init() {
		window.show();
		screen = window.getScreen();
		level = new Level(screen);
		loop();
	}

	private void loop() {
		while (MainLoopOn) {
			if ((double) (System.currentTimeMillis() - timeLR) > fps) {
				window.update();
				level.update();
				level.render();
				screen.clear(0xffffff);
				timeLR = System.currentTimeMillis();
			}
		}
	}
}
