package cz.uhk.diplom.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

public class Edge implements GraphicsObject {
	private int x1, y1, x2, y2;
	private int mode;
	private int v1ID;
	private int v2ID;
	private boolean visited;

	public Edge(int x1, int y1, int x2, int y2, int mode) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.mode = mode;
		this.visited = false;
	}

	@Override
	public void nakresli(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (mode == 1) {
			g.setColor(new Color(255, 0, 0));
			g.setStroke(new BasicStroke(3));
			g.drawLine(x1, y1, x2, y2);
		} else if (mode == 3) {
			g.setColor(new Color(238, 238, 238));
			Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
			g.setStroke(dashed);
			g.drawLine(x1, y1, x2, y2);
		}else {
			g.setColor(new Color(238, 238, 238));
			g.setStroke(new BasicStroke(3));
			g.drawLine(x1, y1, x2, y2);
		}
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

	public int getV1ID() {
		return v1ID;
	}

	public void setV1ID(int v1id) {
		v1ID = v1id;
	}

	public int getV2ID() {
		return v2ID;
	}

	public void setV2ID(int v2id) {
		v2ID = v2id;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

}
