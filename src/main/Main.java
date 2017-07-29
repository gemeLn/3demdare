package main;

import graphics.Screen;
import graphics.Window;

public class Main {
	// Declare Variables Here
	Window window = new Window("FastWalker", 400, 640);
	Screen screen;
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
		loop();
	}

	private void loop() {
		while (MainLoopOn) {
			if ((double) (System.currentTimeMillis() - timeLR) > fps) {
				window.update();
				screen.clear(0x000000);
				timeLR = System.currentTimeMillis();
			}
		}
	}
}
