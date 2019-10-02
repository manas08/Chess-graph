package cz.uhk.diplom.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Edge implements GraphicsObject {
	private int x1, y1, x2, y2;
	
	public Edge(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void nakresli(Graphics2D g) {
		g.setColor(new Color(255,0,0));
	    g.setStroke(new BasicStroke(3));
		g.drawLine(x1, y1, x2, y2);		
	}

	@Override
	public void setX2(int x2) {
		this.x2 = x2;
	}

	@Override
	public void setY2(int y2) {
		this.y2 = y2;
	}

	@Override
	public int getX1() {
		return x1;
	}

	@Override
	public void setX1(int x1) {
		this.x1 = x1;
	}

	@Override
	public int getY1() {
		return y1;
	}

	@Override
	public void setY1(int y1) {
		this.y1 = y1;
	}
	
	@Override
	public int getX2() {
		return x2;
	}
	
	@Override
	public int getY2() {
		return y2;
	}

}
