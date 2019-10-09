package cz.uhk.diplom.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class HamVertex implements GraphicsObject {
	private int x1, y1, x2, y2; // X2 a Y2 zde znaci velikost pixelu 
	private BufferedImage img;
	private boolean navstiveno = false;
	private boolean white = true;
	private int row;
	private int collumn;
	private int id;
	private List<Integer> visited;

	public HamVertex(int x1, int y1, BufferedImage img, boolean white) {
		this.x1 = x1;
		this.y1 = y1;
		this.img = img;
		this.white = white;
		this.navstiveno = false;
		this.visited = new ArrayList<Integer>();
	}

	public HamVertex(int x1, int y1, BufferedImage img) {
		this.x1 = x1;
		this.y1 = y1;
		this.img = img;
		this.navstiveno = false;
		this.visited = new ArrayList<Integer>();
	}
	
	
	@Override
	public void nakresli(Graphics2D g) {
		g.drawImage(img, x1, y1, null);
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
	
	public List<Integer> getVisited() {
		return visited;
	}

	public void setVisited(List<Integer> visited) {
		this.visited = visited;
	}

	public boolean isNavstiveno() {
		return navstiveno;
	}

	public void setNavstiveno(boolean navstiveno) {
		this.navstiveno = navstiveno;
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

}
