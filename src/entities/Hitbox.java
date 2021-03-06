package entities;

import java.awt.Rectangle;
import Level.Level;

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

	public void setType(int i) {
		type = i;
	}

	public final static int TPINUP = 1;
	public final static int TPOUT = 2;
	public final static int TP2Way = 5;
	public final static int NOJUMP = 3;
	public final static int ICE = 4;
	public final static int TPINSIDE = 6;
	public final static int DIE = 7;
	public final static int SHUTDOWN = 99;
	public final static int WIN = 100;

	private int type;
	private long nextJump = 0;
	public final static int upTP = 0;
	public final static int sideTP = 1;
	private int tpDir = sideTP;

	public void setDir(int i) {
		tpDir = i;
	}

	public int getDir() {
		return tpDir;
	}

	public Hitbox(String x, String y, String w, String h, int m, String type) {
		this.x = Integer.parseInt(x) * m;
		this.y = Integer.parseInt(y) * m;
		width = Integer.parseInt(w) * m;
		height = Integer.parseInt(h) * m;
		this.type = Integer.parseInt(type);
		nextJump = 0;
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

	public boolean checkJump() {
		return (System.currentTimeMillis() > nextJump);
	}

	public void jumped(long i) {
		nextJump = i + Level.wallCD;
	}
}
