package cz.uhk.diplom.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Vertex implements GraphicsObject {
	private int x1, y1, x2, y2; // X2 a Y2 zde znaci velikost pixelu 
	private BufferedImage img;
	private int mode;
	private boolean enable = true;
	private boolean white = true;
	private int row;
	private int collumn;
	private int id;
	private List<Vertex> visited;
	private boolean navstiveno = false;
	private Color color;
	private MouseAdapter ma;

	public Vertex(int x1, int y1, int x2, int y2, int mode) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = y2;
		this.y2 = y2;
		this.mode = mode;
		this.visited = new ArrayList<Vertex>();
		switch (mode) {
		case 1:
			color =new Color(255,0,0);
			break;
		case 4:
			color = new Color(0,0,0);
			break;
		default:
			break;
		}
	}

	public Vertex(int x1, int y1, BufferedImage img, int mode, boolean white) {
		this.x1 = x1;
		this.y1 = y1;
		this.img = img;
		this.mode = mode;
		this.white = white;
		this.visited = new ArrayList<Vertex>();
	}

	public Vertex(int x1, int y1, BufferedImage img, int mode) {
		this.x1 = x1;
		this.y1 = y1;
		this.img = img;
		this.mode = mode;
		this.visited = new ArrayList<Vertex>();
		switch (mode) {
		case 1:
			color =new Color(255,0,0);
			break;
		case 4:
			color = new Color(0,0,0);
			break;
		default:
			break;
		}
	}
	
	
	@Override
	public void nakresli(Graphics2D g) {
		if(mode == 1) {
			g.setColor(color);
			g.fillOval(x1, y1, x2, y2);
		}
		if(mode == 2)
			g.drawImage(img, x1, y1, null);
			//g.setColor(new Color(40,40,40));
			//g.fillRect(700,200,20,20);
		if(mode == 3)
			g.drawImage(img, x1-22, y1-28, null);
		if(mode == 4) {
			g.setColor(color);
			g.fillOval(x1, y1, x2, y2);
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

	public List<Vertex> getVisited() {
		return visited;
	}

	public void setVisited(List<Vertex> visited) {
		this.visited = visited;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCollumn() {
		return collumn;
	}

	public void setCollumn(int collumn) {
		this.collumn = collumn;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isNavstiveno() {
		return navstiveno;
	}

	public void setNavstiveno(boolean navstiveno) {
		this.navstiveno = navstiveno;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
