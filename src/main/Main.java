package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Level.Level;
import graphics.Screen;
import graphics.Window;

public class Main {
	public static final int WIDTH = 960;
	public static final int HEIGHT = 540;

	public enum State {
		Menu, Game;
	}

	// Declare Variables Here
	private State state;
	Menu menu;
	Window window = new Window("Emoji Runner", 960, 540);
	Screen screen;
	Level level;
	static Main instance;
	double fps = 1000.0 / 75.0;
	double fpsD = 70.0;
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

	Point previous = new Point();

	void init() throws InterruptedException {
		instance = this;
		setState(State.Menu);
		System.out.println("hi");
		menu = new Menu();
		window.show();
		screen = window.getScreen();
		level = new Level(screen);
		window.addKeyListener(level.getListener());

		window.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					previous = e.getPoint();
					System.out.println(
							(Math.round(e.getX() - level.ScreenPosX) / 10) + "," + (Math.round(e.getY() / 10)));

				} else if (e.getButton() == MouseEvent.BUTTON3) {
					System.out.println("W:" + Math.round((e.getX() - previous.x) / 10) + ", H:"
							+ Math.round(((e.getY() - previous.y) / 10)));
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

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
					if (start + 1000.0 < System.currentTimeMillis()) {
						start = System.currentTimeMillis();
						dTick = tick;
						if (dTick < 55) {
							fpsD += 1;
							fps = 1000.0 / fpsD;
						} else if (dTick > 65) {
							fpsD -= 1;
							fps = 1000.0 / fpsD;
						}
						tick = 0;
					}

					screen.drawString("" + dTick, 20, 20, new Font("Comic-Sans MS", 1, 20), Color.BLACK);
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