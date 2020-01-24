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
	BufferedImage img1, img1R, img1G, img1T, img2, img2R, img2G, img2T, img3, img3R, img3G, img3T, img3D, img4, img4R, img4G, img4T,
	img5, img5R, img5G, img5T, img6, img6R, img6G, img6T, img7, img7R, img7G, img7T, img7D, img8, img8R, img8G, img8T, img9, img9R, img9G, img9T;

	public Hamilton(Image obrazek, List<Edge> edges, List<Vertex> points, MainWindow main) {
		this.obrazek = obrazek;
		this.edges = edges;
		this.points = points;
		this.main = main;
		try {
			img1 = ImageIO.read(getClass().getResourceAsStream("/textures/pub.png"));
			img1R = ImageIO.read(getClass().getResourceAsStream("/textures/pubR.png"));
			img1G = ImageIO.read(getClass().getResourceAsStream("/textures/pubG.png"));
			img1T = ImageIO.read(getClass().getResourceAsStream("/textures/student.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/textures/woolBall.png"));
			img2R = ImageIO.read(getClass().getResourceAsStream("/textures/woolBallR.png"));
			img2G = ImageIO.read(getClass().getResourceAsStream("/textures/woolBallG.png"));
			img2T = ImageIO.read(getClass().getResourceAsStream("/textures/cat.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/textures/star.png"));
			img3R = ImageIO.read(getClass().getResourceAsStream("/textures/starR.png"));
			img3G = ImageIO.read(getClass().getResourceAsStream("/textures/starG.png"));
			img3T = ImageIO.read(getClass().getResourceAsStream("/textures/spaceman.png"));
			img3D = ImageIO.read(getClass().getResourceAsStream("/textures/spaceship.png"));
			img4 = ImageIO.read(getClass().getResourceAsStream("/textures/apple.png"));
			img4R = ImageIO.read(getClass().getResourceAsStream("/textures/appleR.png"));
			img4G = ImageIO.read(getClass().getResourceAsStream("/textures/appleG.png"));
			img4T = ImageIO.read(getClass().getResourceAsStream("/textures/jezek.png"));
			img5 = ImageIO.read(getClass().getResourceAsStream("/textures/bucket.png"));
			img5R = ImageIO.read(getClass().getResourceAsStream("/textures/bucketR.png"));
			img5G = ImageIO.read(getClass().getResourceAsStream("/textures/bucketG.png"));
			img5T = ImageIO.read(getClass().getResourceAsStream("/textures/archimedes.png"));
			img6 = ImageIO.read(getClass().getResourceAsStream("/textures/donut.png"));
			img6R = ImageIO.read(getClass().getResourceAsStream("/textures/donutR.png"));
			img6G = ImageIO.read(getClass().getResourceAsStream("/textures/donutG.png"));
			img6T = ImageIO.read(getClass().getResourceAsStream("/textures/homer.png"));
			img7 = ImageIO.read(getClass().getResourceAsStream("/textures/town.png"));
			img7R = ImageIO.read(getClass().getResourceAsStream("/textures/townR.png"));
			img7G = ImageIO.read(getClass().getResourceAsStream("/textures/townG.png"));
			img7T = ImageIO.read(getClass().getResourceAsStream("/textures/postman.png"));
			img7D = ImageIO.read(getClass().getResourceAsStream("/textures/postcar.png"));
			img8 = ImageIO.read(getClass().getResourceAsStream("/textures/tennisBall.png"));
			img8R = ImageIO.read(getClass().getResourceAsStream("/textures/tennisBallR.png"));
			img8G = ImageIO.read(getClass().getResourceAsStream("/textures/tennisBallG.png"));
			img8T = ImageIO.read(getClass().getResourceAsStream("/textures/tennisPlayer.png"));
			img9 = ImageIO.read(getClass().getResourceAsStream("/textures/popelnice.png"));
			img9R = ImageIO.read(getClass().getResourceAsStream("/textures/popelniceR.png"));
			img9G = ImageIO.read(getClass().getResourceAsStream("/textures/popelniceG.png"));
			img9T = ImageIO.read(getClass().getResourceAsStream("/textures/popelar.png"));
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
		Vertex v4 = new Vertex((int) (width / 4), (int) (height / 8), img1T, 6);
		Vertex v5 = new Vertex((int) (width / 4), (int) (height / 10), "Pomozte studentovi navštívit všechny hospody.", 10);
		this.obrazek.pridej(v4);		
		this.obrazek.pridej(v5);
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
		Vertex v5 = new Vertex((int) (width / 4), (int) (height / 8), img2T, 6);
		Vertex v6 = new Vertex((int) (width / 4), (int) (height / 10), "Posbírejte pro koèièku všechny klubka.", 10);
		this.obrazek.pridej(v5);		
		this.obrazek.pridej(v6);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 3);
		Edge edge2 = new Edge(v2.getX1() + 10, v2.getY1() + 10, v3.getX1() + 10, v3.getY1() + 10, 2);
		Edge edge3 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 3);
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
		Vertex v1 = new Vertex((int) (5 * width / 12), (int) (5 * height / 18), img3, 7);
		Vertex v2 = new Vertex((int) (7 * width / 12), (int) (5 * height / 18), img3, 7);
		Vertex v3 = new Vertex((int) (5 * width / 12), (int) (9 * height / 18), img3, 7);
		Vertex v4 = new Vertex((int) (7 * width / 12), (int) (9 * height / 18), img3, 7);
		Vertex v5 = new Vertex((int) (width / 4), (int) (height / 8), img3T, 6);
		Vertex v6 = new Vertex((int) (5 * width / 16), (int) (height / 8), img3D, 6);
		Vertex v7 = new Vertex((int) (width / 4), (int) (height / 10), "Naplánujte kosmonautovi cestu po všech hvìzdách.", 10);
		this.obrazek.pridej(v5);		
		this.obrazek.pridej(v6);		
		this.obrazek.pridej(v7);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 3);
		Edge edge2 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v3.getX1() + 10, v3.getY1() + 10, 3);
		Edge edge3 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		Edge edge4 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 3);
		Edge edge5 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 3);
		Edge edge6 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 2);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v1.getId());
		edge3.setV1ID(v3.getId());
		edge4.setV1ID(v3.getId());
		edge5.setV1ID(v4.getId());
		edge6.setV1ID(v1.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v2.getId());
		edge4.setV2ID(v4.getId());
		edge5.setV2ID(v2.getId());
		edge6.setV2ID(v4.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
	}

	private void drawLevel3() {
		Vertex v1 = new Vertex((int) (40 * width / 100), (int) (15 * height / 100), img4, 6);
		Vertex v2 = new Vertex((int) (55 * width / 100), (int) (17 * height / 100), img4, 6);
		Vertex v3 = new Vertex((int) (47 * width / 100), (int) (36 * height / 100), img4, 6);
		Vertex v4 = new Vertex((int) (61 * width / 100), (int) (38 * height / 100), img4, 6);
		Vertex v5 = new Vertex((int) (43 * width / 100), (int) (63 * height / 100), img4, 6);
		Vertex v6 = new Vertex((int) (60 * width / 100), (int) (65 * height / 100), img4, 6);
		Vertex v7 = new Vertex((int) (width / 4), (int) (height / 8), img4T, 6);
		Vertex v8 = new Vertex((int) (width / 4), (int) (height / 10), "Pomozte ježkovi posbírat všechny jablíèka.", 10);
		this.obrazek.pridej(v7);		
		this.obrazek.pridej(v8);
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
		Edge edge3 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 3);
		Edge edge4 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 2);
		Edge edge5 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v6.getX1() + 10, v6.getY1() + 10, 2);
		Edge edge6 = new Edge(v5.getX1() + 10, v5.getY1() + 10, v6.getX1() + 10, v6.getY1() + 10, 3);
		Edge edge7 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 3);
		Edge edge8 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 3);
		Edge edge9 = new Edge(v5.getX1() + 10, v5.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 3);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v2.getId());
		edge3.setV1ID(v3.getId());
		edge4.setV1ID(v3.getId());
		edge5.setV1ID(v4.getId());
		edge6.setV1ID(v5.getId());
		edge7.setV1ID(v1.getId());
		edge8.setV1ID(v1.getId());
		edge9.setV1ID(v5.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v4.getId());
		edge4.setV2ID(v5.getId());
		edge5.setV2ID(v6.getId());
		edge6.setV2ID(v6.getId());
		edge7.setV2ID(v4.getId());
		edge8.setV2ID(v5.getId());
		edge9.setV2ID(v4.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
		edges.add(edge7);
		edges.add(edge8);
		edges.add(edge9);
	}

	private void drawLevel4() {
		Vertex v1 = new Vertex((int) (50 * width / 100), (int) (15 * height / 100), img5, 6);
		Vertex v2 = new Vertex((int) (40 * width / 100), (int) (40 * height / 100), img5, 6);
		Vertex v3 = new Vertex((int) (60 * width / 100), (int) (40 * height / 100), img5, 6);
		Vertex v4 = new Vertex((int) (50* width / 100), (int) (45 * height / 100), img5, 6);
		Vertex v5 = new Vertex((int) (35 * width / 100), (int) (55 * height / 100), img5, 6);
		Vertex v6 = new Vertex((int) (65 * width / 100), (int) (55 * height / 100), img5, 6);
		Vertex v7 = new Vertex((int) (50 * width / 100), (int) (65 * height / 100), img5, 6);
		Vertex v8 = new Vertex((int) (width / 4), (int) (height / 8), img5T, 6);
		Vertex v9 = new Vertex((int) (width / 4), (int) (height / 10), "Pomozte Archimedovi posbírat všechny kýble.", 10);
		this.obrazek.pridej(v8);		
		this.obrazek.pridej(v9);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		points.add(v5);
		points.add(v6);
		points.add(v7);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		Edge edge2 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v3.getX1() + 10, v3.getY1() + 10, 2);
		Edge edge3 = new Edge(v2.getX1() + 10, v2.getY1() + 10, v7.getX1() + 10, v7.getY1() + 10, 3);
		Edge edge4 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v7.getX1() + 10, v7.getY1() + 10, 3);
		Edge edge5 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 2);
		Edge edge6 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v6.getX1() + 10, v6.getY1() + 10, 2);
		Edge edge7 = new Edge(v5.getX1() + 10, v5.getY1() + 10, v7.getX1() + 10, v7.getY1() + 10, 3);
		Edge edge8 = new Edge(v6.getX1() + 10, v6.getY1() + 10, v7.getX1() + 10, v7.getY1() + 10, 3);
		Edge edge9 = new Edge(v5.getX1() + 10, v5.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 3);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v1.getId());
		edge3.setV1ID(v2.getId());
		edge4.setV1ID(v3.getId());
		edge5.setV1ID(v4.getId());
		edge6.setV1ID(v4.getId());
		edge7.setV1ID(v5.getId());
		edge8.setV1ID(v6.getId());
		edge9.setV1ID(v5.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v7.getId());
		edge4.setV2ID(v7.getId());
		edge5.setV2ID(v5.getId());
		edge6.setV2ID(v6.getId());
		edge7.setV2ID(v7.getId());
		edge8.setV2ID(v7.getId());
		edge9.setV2ID(v2.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
		edges.add(edge7);
		edges.add(edge8);
		edges.add(edge9);
	}

	private void drawLevel5() {
		Vertex v1 = new Vertex((int) (38 * width / 100), (int) (20 * height / 100), img6, 6);
		Vertex v2 = new Vertex((int) (50 * width / 100), (int) (15 * height / 100), img6, 6);
		Vertex v3 = new Vertex((int) (58 * width / 100), (int) (22 * height / 100), img6, 6);
		Vertex v4 = new Vertex((int) (50* width / 100), (int) (55 * height / 100), img6, 6);
		Vertex v5 = new Vertex((int) (55 * width / 100), (int) (55 * height / 100), img6, 6);
		Vertex v6 = new Vertex((int) (60 * width / 100), (int) (45 * height / 100), img6, 6);
		Vertex v7 = new Vertex((int) (width / 4), (int) (height / 8), img6T, 6);
		Vertex v8 = new Vertex((int) (width / 4), (int) (height / 10), "Seberte Homerovi všechny donuty.", 10);
		this.obrazek.pridej(v7);		
		this.obrazek.pridej(v8);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		points.add(v5);
		points.add(v6);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 2);
		Edge edge2 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		Edge edge3 = new Edge(v2.getX1() + 10, v2.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 3);
		Edge edge4 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 3);
		Edge edge5 = new Edge(v5.getX1() + 10, v5.getY1() + 10, v6.getX1() + 10, v6.getY1() + 10, 3);
		Edge edge6 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v6.getX1() + 10, v6.getY1() + 10, 2);
		Edge edge7 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v1.getX1() + 10, v1.getY1() + 10, 3);
		Edge edge8 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 3);
		Edge edge9 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 3);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v4.getId());
		edge3.setV1ID(v2.getId());
		edge4.setV1ID(v3.getId());
		edge5.setV1ID(v5.getId());
		edge6.setV1ID(v3.getId());
		edge7.setV1ID(v3.getId());
		edge8.setV1ID(v4.getId());
		edge9.setV1ID(v1.getId());
		edge1.setV2ID(v4.getId());
		edge2.setV2ID(v2.getId());
		edge3.setV2ID(v5.getId());
		edge4.setV2ID(v5.getId());
		edge5.setV2ID(v6.getId());
		edge6.setV2ID(v6.getId());
		edge7.setV2ID(v1.getId());
		edge8.setV2ID(v5.getId());
		edge9.setV2ID(v2.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
		edges.add(edge7);
		edges.add(edge8);
		edges.add(edge9);
	}

	private void drawLevel6() {
		Vertex v1 = new Vertex((int) (40 * width / 100), (int) (20 * height / 100), img7, 6);
		Vertex v2 = new Vertex((int) (50 * width / 100), (int) (15 * height / 100), img7, 6);
		Vertex v3 = new Vertex((int) (60 * width / 100), (int) (20 * height / 100), img7, 6);
		Vertex v4 = new Vertex((int) (45* width / 100), (int) (45 * height / 100), img7, 6);
		Vertex v5 = new Vertex((int) (55 * width / 100), (int) (45 * height / 100), img7, 6);
		Vertex v6 = new Vertex((int) (40 * width / 100), (int) (70 * height / 100), img7, 6);
		Vertex v7 = new Vertex((int) (50 * width / 100), (int) (75 * height / 100), img7, 6);
		Vertex v8 = new Vertex((int) (60 * width / 100), (int) (70 * height / 100), img7, 6);
		Vertex v9 = new Vertex((int) (width / 4), (int) (height / 8), img7T, 6);
		Vertex v10 = new Vertex((int) (5 * width / 16), (int) (height / 8), img7D, 6);
		Vertex v11 = new Vertex((int) (width / 4), (int) (height / 10), "Naplánujte pošákovi cestu pro rozvoz pošty.", 10);
		this.obrazek.pridej(v9);		
		this.obrazek.pridej(v10);		
		this.obrazek.pridej(v11);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		points.add(v5);
		points.add(v6);
		points.add(v7);
		points.add(v8);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 3);
		Edge edge2 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v3.getX1() + 10, v3.getY1() + 10, 2);
		Edge edge3 = new Edge(v2.getX1() + 10, v2.getY1() + 10, v3.getX1() + 10, v3.getY1() + 10, 2);
		Edge edge4 = new Edge(v2.getX1() + 10, v2.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 3);
		Edge edge5 = new Edge(v2.getX1() + 10, v2.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 3);
		Edge edge6 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v7.getX1() + 10, v7.getY1() + 10, 2);
		Edge edge7 = new Edge(v5.getX1() + 10, v5.getY1() + 10, v7.getX1() + 10, v7.getY1() + 10, 3);
		Edge edge8 = new Edge(v6.getX1() + 10, v6.getY1() + 10, v7.getX1() + 10, v7.getY1() + 10, 3);
		Edge edge9 = new Edge(v6.getX1() + 10, v6.getY1() + 10, v8.getX1() + 10, v8.getY1() + 10, 2);
		Edge edge10 = new Edge(v7.getX1() + 10, v7.getY1() + 10, v8.getX1() + 10, v8.getY1() + 10, 3);
		Edge edge11 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 3);
		Edge edge12 = new Edge(v5.getX1() + 10, v5.getY1() + 10, v6.getX1() + 10, v6.getY1() + 10, 3);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v1.getId());
		edge3.setV1ID(v2.getId());
		edge4.setV1ID(v2.getId());
		edge5.setV1ID(v2.getId());
		edge6.setV1ID(v4.getId());
		edge7.setV1ID(v5.getId());
		edge8.setV1ID(v6.getId());
		edge9.setV1ID(v6.getId());
		edge10.setV1ID(v7.getId());
		edge11.setV1ID(v1.getId());
		edge12.setV1ID(v5.getId());
		edge1.setV2ID(v2.getId());
		edge2.setV2ID(v3.getId());
		edge3.setV2ID(v3.getId());
		edge4.setV2ID(v4.getId());
		edge5.setV2ID(v5.getId());
		edge6.setV2ID(v7.getId());
		edge7.setV2ID(v7.getId());
		edge8.setV2ID(v7.getId());
		edge9.setV2ID(v8.getId());
		edge10.setV2ID(v8.getId());
		edge11.setV2ID(v5.getId());
		edge12.setV2ID(v6.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
		edges.add(edge7);
		edges.add(edge8);
		edges.add(edge9);
		edges.add(edge10);
		edges.add(edge11);
		edges.add(edge12);
	}

	private void drawLevel7() {
		Vertex v1 = new Vertex((int) (35 * width / 100), (int) (25 * height / 100), img8, 6);
		Vertex v2 = new Vertex((int) (46 * width / 100), (int) (15 * height / 100), img8, 6);
		Vertex v3 = new Vertex((int) (54 * width / 100), (int) (18 * height / 100), img8, 6);
		Vertex v4 = new Vertex((int) (66* width / 100), (int) (30 * height / 100), img8, 6);
		Vertex v5 = new Vertex((int) (37 * width / 100), (int) (45 * height / 100), img8, 6);
		Vertex v6 = new Vertex((int) (50 * width / 100), (int) (60 * height / 100), img8, 6);
		Vertex v7 = new Vertex((int) (width / 4), (int) (height / 8), img8T, 6);
		Vertex v8 = new Vertex((int) (width / 4), (int) (height / 10), "Pomozte tenistovi sebrat všechny míèky.", 10);
		this.obrazek.pridej(v7);		
		this.obrazek.pridej(v8);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		points.add(v5);
		points.add(v6);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 2);
		Edge edge2 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		Edge edge3 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 3);
		Edge edge4 = new Edge(v6.getX1() + 10, v6.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 3);
		Edge edge5 = new Edge(v2.getX1() + 10, v2.getY1() + 10, v6.getX1() + 10, v6.getY1() + 10, 3);
		Edge edge6 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v6.getX1() + 10, v6.getY1() + 10, 2);
		Edge edge7 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v1.getX1() + 10, v1.getY1() + 10, 3);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v4.getId());
		edge3.setV1ID(v2.getId());
		edge4.setV1ID(v3.getId());
		edge5.setV1ID(v5.getId());
		edge6.setV1ID(v3.getId());
		edge7.setV1ID(v3.getId());
		edge1.setV2ID(v4.getId());
		edge2.setV2ID(v2.getId());
		edge3.setV2ID(v5.getId());
		edge4.setV2ID(v5.getId());
		edge5.setV2ID(v6.getId());
		edge6.setV2ID(v6.getId());
		edge7.setV2ID(v1.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
		edges.add(edge7);
	}

	private void drawLevel8() {
		Vertex v1 = new Vertex((int) (40 * width / 100), (int) (25 * height / 100), img9, 6);
		Vertex v2 = new Vertex((int) (60 * width / 100), (int) (18 * height / 100), img9, 6);
		Vertex v3 = new Vertex((int) (62 * width / 100), (int) (35 * height / 100), img9, 6);
		Vertex v4 = new Vertex((int) (45* width / 100), (int) (50 * height / 100), img9, 6);
		Vertex v5 = new Vertex((int) (52 * width / 100), (int) (55 * height / 100), img9, 6);
		Vertex v6 = new Vertex((int) (width / 4), (int) (height / 8), img9T, 6);
		Vertex v7 = new Vertex((int) (width / 4), (int) (height / 10), "Naplánujte popeláøùj nejlepší cestu.", 10);
		this.obrazek.pridej(v6);		
		this.obrazek.pridej(v7);
		points.add(v1);
		points.add(v2);
		points.add(v3);
		points.add(v4);
		points.add(v5);

		for (int i = 0; i < points.size(); i++) {
			points.get(i).setId(i + 1);
		}

		Edge edge1 = new Edge(v1.getX1() + 10, v1.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 2);
		Edge edge2 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v2.getX1() + 10, v2.getY1() + 10, 2);
		Edge edge3 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v4.getX1() + 10, v4.getY1() + 10, 3);
		Edge edge4 = new Edge(v4.getX1() + 10, v4.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 3);
		Edge edge5 = new Edge(v2.getX1() + 10, v2.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 3);
		Edge edge6 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v5.getX1() + 10, v5.getY1() + 10, 3);
		Edge edge7 = new Edge(v3.getX1() + 10, v3.getY1() + 10, v1.getX1() + 10, v1.getY1() + 10, 3);
		edge1.setV1ID(v1.getId());
		edge2.setV1ID(v4.getId());
		edge3.setV1ID(v2.getId());
		edge4.setV1ID(v3.getId());
		edge5.setV1ID(v5.getId());
		edge6.setV1ID(v3.getId());
		edge7.setV1ID(v3.getId());
		edge1.setV2ID(v4.getId());
		edge2.setV2ID(v2.getId());
		edge3.setV2ID(v5.getId());
		edge4.setV2ID(v5.getId());
		edge5.setV2ID(v6.getId());
		edge6.setV2ID(v5.getId());
		edge7.setV2ID(v1.getId());

		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
		edges.add(edge7);
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
			main.changeHelp(4);
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
		case 5:
			drawLevel5();
			break;
		case 6:
			drawLevel6();
			break;
		case 7:
			drawLevel7();
			break;
		case 8:
			drawLevel8();
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
			case 4:
				vertex.setImg(img5);
				break;
			case 5:
				vertex.setImg(img6);
				break;
			case 6:
				vertex.setImg(img7);
				break;
			case 7:
				vertex.setImg(img8);
				break;
			case 8:
				vertex.setImg(img9);
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
			case 4:
				vertex.setImg(img5G);
				break;
			case 5:
				vertex.setImg(img6G);
				break;
			case 6:
				vertex.setImg(img7G);
				break;
			case 7:
				vertex.setImg(img8G);
				break;
			case 8:
				vertex.setImg(img9G);
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
			case 4:
				vertex.setImg(img5R);
				break;
			case 5:
				vertex.setImg(img6R);
				break;
			case 6:
				vertex.setImg(img7R);
				break;
			case 7:
				vertex.setImg(img8R);
				break;
			case 8:
				vertex.setImg(img9R);
				break;
			default:
				break;
			}
		}
	}

}
