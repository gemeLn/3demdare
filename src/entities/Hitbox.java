package entities;

import java.awt.Rectangle;

public class Hitbox extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tpID;

	public int getType() {
		return type;
	}

	public int getTPID() {
		return tpID;
	}

	public void setTPID(int i) {
		tpID = i;
	}

	public final static int TPIN = 1;
	public final static int TPOUT = 2;
	private int type;

	public Hitbox(String x, String y, String w, String h, int m, String type) {
		this.x = Integer.parseInt(x) * m;
		this.y = Integer.parseInt(y) * m;
		width = Integer.parseInt(w) * m;
		height = Integer.parseInt(h) * m;
		this.type = Integer.parseInt(type);
	}

	public Hitbox(int x, int y, int w, int h, int m) {
		this.x = x * m;
		this.y = y * m;
		width = w * m;
		height = h * m;
	}

	public Hitbox(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}

	public void shiftX(int i) {
		x += i;
	}
}
