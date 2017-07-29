package entities;

import java.awt.Rectangle;

public class Hitbox extends Rectangle {
	public Hitbox(String x, String y, String w, String h) {
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
		width = Integer.parseInt(w);
		height = Integer.parseInt(h);
	}

}
