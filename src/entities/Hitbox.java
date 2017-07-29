package entities;

import java.awt.Rectangle;

public class Hitbox extends Rectangle {
	int m = 1;
	public Hitbox(String x, String y, String w, String h) {
		this.x = Integer.parseInt(x) * m;
		this.y = Integer.parseInt(y) * m;
		width = Integer.parseInt(w);
		height = Integer.parseInt(h);
	}

	public Hitbox(int x, int y, int w, int h) {
		this.x = x * m;
		this.y = y * m;
		width = w;
		height = h;
	}
	
	public void changeM(int x){
		m = x;
	}

	public void shiftX(int i) {
		x += i;
	}
}
