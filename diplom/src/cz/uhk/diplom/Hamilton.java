package cz.uhk.diplom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import cz.uhk.diplom.model.Edge;
import cz.uhk.diplom.model.Image;
import cz.uhk.diplom.model.Vertex;

public class Hamilton {
	Image obrazek;
	List<Edge> edges; 
	List<Vertex> points;
	MainWindow main;
	private int pocetHran;
	private int level;
	private double width;
	private double height;

	public Hamilton(Image obrazek, List<Edge> edges, List<Vertex> points, MainWindow main) {
		this.obrazek = obrazek;
		this.edges = edges;
		this.points = points;
		this.main = main;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		level = -1;
		nextLevel(this.obrazek);
	}

	private void drawLevel0() {
		Vertex v1 = new Vertex((int)(width/2), (int)(height/3), 20, 20, 4);
		Vertex v2 = new Vertex((int)(width/3), (int)(height/2), 20, 20, 4);
		Vertex v3 = new Vertex((int)(2*width/3), (int)(height/2), 20, 20, 4);
		Vertex v4 = new Vertex((int)(2*width/3), (int)(1.5*height/2), 20, 20, 4);
		v1.setId(1);
		v2.setId(2);
		v3.setId(3);
		v4.setId(4);
		
		Edge edge1 = new Edge(v1.getX1()+10, v1.getY1()+10, v2.getX1()+10, v2.getY1()+10, 2);
		Edge edge2 = new Edge(v1.getX1()+10, v1.getY1()+10, v3.getX1()+10, v3.getY1()+10, 2);
		Edge edge3 = new Edge(v3.getX1()+10, v3.getY1()+10, v2.getX1()+10, v2.getY1()+10, 2);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v1.getId());
		edge3.setV1ID(v3.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v2.getId());
		
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		
		obrazek.pridej(edge1);
		obrazek.pridej(edge2);
		obrazek.pridej(edge3);
		obrazek.pridej(v1);
		obrazek.pridej(v2);
		obrazek.pridej(v3);
		obrazek.pridej(v4);
	}

	private void drawLevel1() {
		Vertex v1 = new Vertex((int)(width/4), (int)(height/3), 20, 20, 4);
		Vertex v2 = new Vertex((int)(width/5), (int)(height/2), 20, 20, 4);
		Vertex v3 = new Vertex((int)(2*width/4), (int)(height/2), 20, 20, 4);
		Vertex v4 = new Vertex((int)(2*width/5), (int)(1.5*height/2), 20, 20, 4);
		v1.setId(1);
		v2.setId(2);
		v3.setId(3);
		v4.setId(4);
		
		Edge edge1 = new Edge(v1.getX1()+10, v1.getY1()+10, v2.getX1()+10, v2.getY1()+10, 2);
		Edge edge2 = new Edge(v1.getX1()+10, v1.getY1()+10, v3.getX1()+10, v3.getY1()+10, 2);
		Edge edge3 = new Edge(v3.getX1()+10, v3.getY1()+10, v2.getX1()+10, v2.getY1()+10, 2);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v1.getId());
		edge3.setV1ID(v3.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v2.getId());
		
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		
		obrazek.pridej(edge1);
		obrazek.pridej(edge2);
		obrazek.pridej(edge3);
		obrazek.pridej(v1);
		obrazek.pridej(v2);
		obrazek.pridej(v3);
		obrazek.pridej(v4);
	}

	private void drawLevel2() {
		Vertex v1 = new Vertex((int)(width/2), (int)(height/3), 20, 20, 4);
		Vertex v2 = new Vertex((int)(width/3), (int)(height/2), 20, 20, 4);
		Vertex v3 = new Vertex((int)(2*width/3), (int)(height/2), 20, 20, 4);
		Vertex v4 = new Vertex((int)(2*width/3), (int)(1.5*height/2), 20, 20, 4);
		v1.setId(1);
		v2.setId(2);
		v3.setId(3);
		v4.setId(4);
		
		Edge edge1 = new Edge(v1.getX1()+10, v1.getY1()+10, v2.getX1()+10, v2.getY1()+10, 2);
		Edge edge2 = new Edge(v1.getX1()+10, v1.getY1()+10, v3.getX1()+10, v3.getY1()+10, 2);
		Edge edge3 = new Edge(v3.getX1()+10, v3.getY1()+10, v2.getX1()+10, v2.getY1()+10, 2);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v1.getId());
		edge3.setV1ID(v3.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v2.getId());
		
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		
		obrazek.pridej(edge1);
		obrazek.pridej(edge2);
		obrazek.pridej(edge3);
		obrazek.pridej(v1);
		obrazek.pridej(v2);
		obrazek.pridej(v3);
		obrazek.pridej(v4);
	}

	private void drawLevel3() {
		Vertex v1 = new Vertex((int)(width/2), (int)(height/3), 20, 20, 4);
		Vertex v2 = new Vertex((int)(width/3), (int)(height/2), 20, 20, 4);
		Vertex v3 = new Vertex((int)(2*width/3), (int)(height/2), 20, 20, 4);
		Vertex v4 = new Vertex((int)(2*width/3), (int)(1.5*height/2), 20, 20, 4);
		v1.setId(1);
		v2.setId(2);
		v3.setId(3);
		v4.setId(4);
		
		Edge edge1 = new Edge(v1.getX1()+10, v1.getY1()+10, v2.getX1()+10, v2.getY1()+10, 2);
		Edge edge2 = new Edge(v1.getX1()+10, v1.getY1()+10, v3.getX1()+10, v3.getY1()+10, 2);
		Edge edge3 = new Edge(v3.getX1()+10, v3.getY1()+10, v2.getX1()+10, v2.getY1()+10, 2);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v1.getId());
		edge3.setV1ID(v3.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v2.getId());
		
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		
		obrazek.pridej(edge1);
		obrazek.pridej(edge2);
		obrazek.pridej(edge3);
		obrazek.pridej(v1);
		obrazek.pridej(v2);
		obrazek.pridej(v3);
		obrazek.pridej(v4);
	}
	
	private void drawLevel4() {
		Vertex v1 = new Vertex((int)(width/2), (int)(height/3), 20, 20, 4);
		Vertex v2 = new Vertex((int)(width/3), (int)(height/2), 20, 20, 4);
		Vertex v3 = new Vertex((int)(2*width/3), (int)(height/2), 20, 20, 4);
		Vertex v4 = new Vertex((int)(2*width/3), (int)(1.5*height/2), 20, 20, 4);
		v1.setId(1);
		v2.setId(2);
		v3.setId(3);
		v4.setId(4);
		
		Edge edge1 = new Edge(v1.getX1()+10, v1.getY1()+10, v2.getX1()+10, v2.getY1()+10, 2);
		Edge edge2 = new Edge(v1.getX1()+10, v1.getY1()+10, v3.getX1()+10, v3.getY1()+10, 2);
		Edge edge3 = new Edge(v3.getX1()+10, v3.getY1()+10, v2.getX1()+10, v2.getY1()+10, 2);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v1.getId());
		edge3.setV1ID(v3.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v2.getId());
		
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		
		obrazek.pridej(edge1);
		obrazek.pridej(edge2);
		obrazek.pridej(edge3);
		obrazek.pridej(v1);
		obrazek.pridej(v2);
		obrazek.pridej(v3);
		obrazek.pridej(v4);
	}
	

	public int getNumberOfEdges() {
		return pocetHran;
	}

	public void nextLevel(Image obrazek) {
		level++;
		this.obrazek = obrazek;
		edges.clear();
		points.clear();
		switch (level) {
		case 0:
			drawLevel0();
			break;
		case 1:
			drawLevel1();
			break;
		case 2:
			drawLevel2();
			break;
		case 3:
			drawLevel3();
			break;
		case 4:
			drawLevel4();
			break;
		default:
			break;
		}
		
		main.setObrazek(this.obrazek);
		main.setEdges(this.edges);
		main.setPoints(this.points);
		
		pocetHran = edges.size();
	}
	
	
}
