package cz.uhk.diplom;

import java.awt.Dimension;
import java.awt.Toolkit;
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
		Vertex v1 = new Vertex((int) (width / 2), (int) (height / 3), 20, 20, 4);
		Vertex v2 = new Vertex((int) (width / 3), (int) (height / 2), 20, 20, 4);
		Vertex v3 = new Vertex((int) (2 * width / 3), (int) (height / 2), 20, 20, 4);
		points.add(v1);
		points.add(v2);
		points.add(v3);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		Edge edge2 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v3.getX1() + 10, v3.getY1() + 10, 2);
		Edge edge3 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v1.getId());
		edge3.setV1ID(v3.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v2.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
	}

	private void drawLevel1() {
		Vertex v1 = new Vertex((int) (width / 2), (int) (height / 5), 20, 20, 4);
		Vertex v2 = new Vertex((int) (width / 3), (int) (2 * height / 5), 20, 20, 4);
		Vertex v3 = new Vertex((int) (width / 2), (int) (3 * height / 5), 20, 20, 4);
		Vertex v4 = new Vertex((int) (2 * width / 3), (int) (2 * height / 5), 20, 20, 4);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		Edge edge2 = new Edge(v2.getX1() + 10, v2.getY1() + 10, v3.getX1() + 10, v3.getY1() + 10, 2);
		Edge edge3 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 2);
		Edge edge4 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v1.getX1() + 10, v1.getY1() + 10, 2);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v2.getId());
		edge3.setV1ID(v3.getId());
		edge4.setV1ID(v4.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v4.getId());
		edge4.setV2ID(v1.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
	}

	private void drawLevel2() {
		Vertex v1 = new Vertex((int) (width / 2), (int) (3 * height / 16), 20, 20, 4);
		Vertex v2 = new Vertex((int) (5 * width / 14), (int) (7 * height / 20), 20, 20, 4);
		Vertex v3 = new Vertex((int) (9 * width / 14), (int) (7 * height / 20), 20, 20, 4);
		Vertex v4 = new Vertex((int) (17 * width / 40), (int) (26 * height / 40), 20, 20, 4);
		Vertex v5 = new Vertex((int) (23 * width / 40), (int) (26 * height / 40), 20, 20, 4);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		points.add(v5);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 2);
		Edge edge2 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v3.getX1() + 10, v3.getY1() + 10, 2);
		Edge edge3 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		Edge edge4 = new Edge(v2.getX1() + 10, v2.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 2);
		Edge edge5 = new Edge(v5.getX1() + 10, v5.getY1() + 10, v1.getX1() + 10, v1.getY1() + 10, 2);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v4.getId());
		edge3.setV1ID(v3.getId());
		edge4.setV1ID(v2.getId());
		edge5.setV1ID(v5.getId());
		edge1.setV2ID(v4.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v2.getId());
		edge4.setV2ID(v5.getId());
		edge5.setV2ID(v1.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
	}

	private void drawLevel3() {
		Vertex v1 = new Vertex((int) (5 * width / 12), (int) (5 * height / 18), 20, 20, 4);
		Vertex v2 = new Vertex((int) (7 * width / 12), (int) (5 * height / 18), 20, 20, 4);
		Vertex v3 = new Vertex((int) (5 * width / 12), (int) (9 * height / 18), 20, 20, 4);
		Vertex v4 = new Vertex((int) (7 * width / 12), (int) (9 * height / 18), 20, 20, 4);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		Edge edge2 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v3.getX1() + 10, v3.getY1() + 10, 2);
		Edge edge3 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 3);
		Edge edge4 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 2);
		Edge edge5 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v1.getId());
		edge3.setV1ID(v3.getId());
		edge4.setV1ID(v3.getId());
		edge5.setV1ID(v4.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v2.getId());
		edge4.setV2ID(v4.getId());
		edge5.setV2ID(v2.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
	}

	private void drawLevel4() {
		Vertex v1 = new Vertex((int) (34 * width / 100), (int) (10 * height / 100), 20, 20, 4);
		Vertex v2 = new Vertex((int) (43 * width / 100), (int) (17 * height / 100), 20, 20, 4);
		Vertex v3 = new Vertex((int) (47 * width / 100), (int) (36 * height / 100), 20, 20, 4);
		Vertex v4 = new Vertex((int) (61 * width / 100), (int) (38 * height / 100), 20, 20, 4);
		Vertex v5 = new Vertex((int) (43 * width / 100), (int) (63 * height / 100), 20, 20, 4);
		Vertex v6 = new Vertex((int) (60 * width / 100), (int) (65 * height / 100), 20, 20, 4);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		points.add(v5);
		points.add(v6);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		Edge edge2 = new Edge(v2.getX1() + 10, v2.getY1() + 10, v3.getX1() + 10, v3.getY1() + 10, 2);
		Edge edge3 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 2);
		Edge edge4 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 2);
		Edge edge5 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v6.getX1() + 10, v6.getY1() + 10, 2);
		Edge edge6 = new Edge(v5.getX1() + 10, v5.getY1() + 10, v6.getX1() + 10, v6.getY1() + 10, 2);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v2.getId());
		edge3.setV1ID(v3.getId());
		edge4.setV1ID(v3.getId());
		edge5.setV1ID(v4.getId());
		edge6.setV1ID(v5.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v4.getId());
		edge4.setV2ID(v5.getId());
		edge5.setV2ID(v6.getId());
		edge6.setV2ID(v6.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
	}

	public int getNumberOfEdges() {
		return pocetHran;
	}

	public int getNumberOfDashedEdges() {
		int pocet = 0;
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getMode() == 3) {
				pocet++;
			}
		}
		return pocet;
	}

	public void nextLevel(Image obrazek) {
		level++;
		this.obrazek = obrazek;
		edges.clear();
		points.clear();
		
		//doèasnì
		if (level == 2) {
			level++;
		}
		
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

		for (int i = 0; i < edges.size(); i++) {
			obrazek.pridej(edges.get(i));
		}
		for (int i = 0; i < points.size(); i++) {
			obrazek.pridej(points.get(i));
		}

		main.setObrazek(this.obrazek);
		main.setEdges(this.edges);
		main.setPoints(this.points);

		pocetHran = edges.size();
	}

}
