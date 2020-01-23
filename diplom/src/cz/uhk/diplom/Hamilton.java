package cz.uhk.diplom;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

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
	BufferedImage img1, img1R, img1G, img2, img2R, img2G, img3, img3R, img3G, img4, img4R, img4G, 
	img5, img5R, img5G, img6, img6R, img6G, img7, img7R, img7G, img8, img8R, img8G, img9, img9R, img9G;

	public Hamilton(Image obrazek, List<Edge> edges, List<Vertex> points, MainWindow main) {
		this.obrazek = obrazek;
		this.edges = edges;
		this.points = points;
		this.main = main;
		try {
			img1 = ImageIO.read(getClass().getResourceAsStream("/textures/pub.png"));
			img1R = ImageIO.read(getClass().getResourceAsStream("/textures/pubR.png"));
			img1G = ImageIO.read(getClass().getResourceAsStream("/textures/pubG.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/textures/woolBall.png"));
			img2R = ImageIO.read(getClass().getResourceAsStream("/textures/woolBallR.png"));
			img2G = ImageIO.read(getClass().getResourceAsStream("/textures/woolBallG.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/textures/star.png"));
			img3R = ImageIO.read(getClass().getResourceAsStream("/textures/starR.png"));
			img3G = ImageIO.read(getClass().getResourceAsStream("/textures/starG.png"));
			img4 = ImageIO.read(getClass().getResourceAsStream("/textures/apple.png"));
			img4R = ImageIO.read(getClass().getResourceAsStream("/textures/appleR.png"));
			img4G = ImageIO.read(getClass().getResourceAsStream("/textures/appleG.png"));
			img5 = ImageIO.read(getClass().getResourceAsStream("/textures/bucket.png"));
			img5R = ImageIO.read(getClass().getResourceAsStream("/textures/bucketR.png"));
			img5G = ImageIO.read(getClass().getResourceAsStream("/textures/bucketG.png"));
			img6 = ImageIO.read(getClass().getResourceAsStream("/textures/donut.png"));
			img6R = ImageIO.read(getClass().getResourceAsStream("/textures/donutR.png"));
			img6G = ImageIO.read(getClass().getResourceAsStream("/textures/donutG.png"));
			img7 = ImageIO.read(getClass().getResourceAsStream("/textures/town.png"));
			img7R = ImageIO.read(getClass().getResourceAsStream("/textures/townR.png"));
			img7G = ImageIO.read(getClass().getResourceAsStream("/textures/townG.png"));
			img8 = ImageIO.read(getClass().getResourceAsStream("/textures/tennisBall.png"));
			img8R = ImageIO.read(getClass().getResourceAsStream("/textures/tennisBallR.png"));
			img8G = ImageIO.read(getClass().getResourceAsStream("/textures/tennisBallG.png"));
			img9 = ImageIO.read(getClass().getResourceAsStream("/textures/popelnice.png"));
			img9R = ImageIO.read(getClass().getResourceAsStream("/textures/popelniceR.png"));
			img9G = ImageIO.read(getClass().getResourceAsStream("/textures/popelniceG.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		level = -1;
		nextLevel(this.obrazek);
	}

	private void drawLevel0() {
		Vertex v1 = new Vertex((int) (width / 2), (int) (height / 3), img1, 5);
		Vertex v2 = new Vertex((int) (width / 3), (int) (height / 2), img1, 5);
		Vertex v3 = new Vertex((int) (2 * width / 3), (int) (height / 2), img1, 5);
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
		Vertex v1 = new Vertex((int) (width / 2), (int) (height / 5), img2, 6);
		Vertex v2 = new Vertex((int) (width / 3), (int) (2 * height / 5), img2, 6);
		Vertex v3 = new Vertex((int) (width / 2), (int) (3 * height / 5), img2, 6);
		Vertex v4 = new Vertex((int) (2 * width / 3), (int) (2 * height / 5), img2, 6);
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
/*
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
*/
	private void drawLevel2() {
		Vertex v1 = new Vertex((int) (5 * width / 12), (int) (5 * height / 18), img3, 7);
		Vertex v2 = new Vertex((int) (7 * width / 12), (int) (5 * height / 18), img3, 7);
		Vertex v3 = new Vertex((int) (5 * width / 12), (int) (9 * height / 18), img3, 7);
		Vertex v4 = new Vertex((int) (7 * width / 12), (int) (9 * height / 18), img3, 7);
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

	private void drawLevel3() {
		Vertex v1 = new Vertex((int) (34 * width / 100), (int) (10 * height / 100), img4, 6);
		Vertex v2 = new Vertex((int) (43 * width / 100), (int) (17 * height / 100), img4, 6);
		Vertex v3 = new Vertex((int) (47 * width / 100), (int) (36 * height / 100), img4, 6);
		Vertex v4 = new Vertex((int) (61 * width / 100), (int) (38 * height / 100), img4, 6);
		Vertex v5 = new Vertex((int) (43 * width / 100), (int) (63 * height / 100), img4, 6);
		Vertex v6 = new Vertex((int) (60 * width / 100), (int) (65 * height / 100), img4, 6);
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
		
		
		switch (level) {
		case 0:
			drawLevel0();
			break;
		case 1:
			drawLevel1();
			break;
		case 2:
			main.changeHelp(4);
			drawLevel2();
			break;
		case 3:
			drawLevel3();
			break;
		case 4:
			
			break;
		default:
			break;
		}

		for (int i = 0; i < edges.size(); i++) {
			this.obrazek.pridej(edges.get(i));
		}
		for (int i = 0; i < points.size(); i++) {
			this.obrazek.pridej(points.get(i));
		}

		main.setObrazek(this.obrazek);
		main.setEdges(this.edges);
		main.setPoints(this.points);

		pocetHran = edges.size();
	}

	public void changeImg(Vertex vertex, int b) {
		if (b == 0) {
			switch (level) {
			case 0:
				vertex.setImg(img1);
				break;
			case 1:
				vertex.setImg(img2);
				break;
			case 2:
				vertex.setImg(img3);
				break;
			case 3:
				vertex.setImg(img4);
				break;
			default:
				break;
			}
		}else if(b == 1) {
			switch (level) {
			case 0:
				vertex.setImg(img1G);
				break;
			case 1:
				vertex.setImg(img2G);
				break;
			case 2:
				vertex.setImg(img3G);
				break;
			case 3:
				vertex.setImg(img4G);
				break;
			default:
				break;
			}
		}else {
			switch (level) {
			case 0:
				vertex.setImg(img1R);
				break;
			case 1:
				vertex.setImg(img2R);
				break;
			case 2:
				vertex.setImg(img3R);
				break;
			case 3:
				vertex.setImg(img4R);
				break;
			default:
				break;
			}
		}
	}

}
