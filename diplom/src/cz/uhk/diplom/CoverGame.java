package cz.uhk.diplom;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import cz.uhk.diplom.model.Image;
import cz.uhk.diplom.model.Vertex;

public class CoverGame {

	BufferedImage img1 = null, img2 = null, img3 = null, img4 = null, kraj1 = null, kraj2 = null, kraj3 = null,
			kraj4 = null, kraj5 = null, kraj6 = null, kraj7 = null, kraj8 = null, kraj9 = null, kraj10 = null,
			kraj11 = null, kraj12 = null, kraj13 = null, kraj14 = null, kraj15 = null, kraj16 = null, img3G = null,
			img4G = null;

	private Image obrazek;
	private int mode;
	Vertex vertex;

	private List<Vertex> vertices = new ArrayList<>();
	private List<Vertex> kone = new ArrayList<>();
	private List<Vertex> steps = new ArrayList<>();

	public CoverGame() {
		try {
			img1 = ImageIO.read(getClass().getResourceAsStream("/textures/whitehorse.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/textures/blackhorse.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/textures/brick.jpg"));
			img4 = ImageIO.read(getClass().getResourceAsStream("/textures/brick2.jpg"));
			img3G = ImageIO.read(getClass().getResourceAsStream("/textures/brickR.jpg"));
			img4G = ImageIO.read(getClass().getResourceAsStream("/textures/brick2R.jpg"));
			kraj1 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj1.jpg"));
			kraj2 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj2.jpg"));
			kraj3 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj3.jpg"));
			kraj4 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj4.jpg"));
			kraj5 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj5.jpg"));
			kraj6 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj6.jpg"));
			kraj7 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj7.jpg"));
			kraj8 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj8.jpg"));
			kraj9 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj9.jpg"));
			kraj10 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj10.jpg"));
			kraj11 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj11.jpg"));
			kraj12 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj12.jpg"));
			kraj13 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj13.jpg"));
			kraj14 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj14.jpg"));
			kraj15 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj15.jpg"));
			kraj16 = ImageIO.read(getClass().getResourceAsStream("/textures/kraj16.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBoard(List<Vertex> kone, List<Vertex> vertices, Image obrazek, MainWindow main, int mode) {
		this.kone = kone;
		this.vertices = vertices;
		this.obrazek = obrazek;
		this.mode = mode;

		switch (mode) {
		case 3:
			drawBoard3x3();
			vertex = new Vertex(845, 378, img2, 3);
			break;
		case 4:
			drawBoard4x4();
			vertex = new Vertex(795, 328, img2, 3);
			break;
		case 5:
			drawBoard5x5();
			vertex = new Vertex(745, 278, img2, 3);
			break;
		case 6:
			drawBoard6x6();
			vertex = new Vertex(695, 228, img2, 3);
			break;
		default:
			break;
		}
		vertex.setX1(this.vertices.get(0).getX1() + 35);
		vertex.setY1(this.vertices.get(0).getY1() + 38);
		this.vertex.setId(0);
		this.vertex.setRow(1);
		this.vertex.setCollumn(1);
		this.kone.add(vertex);
		this.obrazek.pridej(vertex);

		main.setKone(this.kone);
		main.setObrazek(this.obrazek);
		main.setVertices(this.vertices);
	}

	private void drawBoard3x3() {
		// prvni vykresleni
		vertex = new Vertex(20, 20, img1, 3);
		// kone.add(vertex);
		obrazek.pridej(vertex);
		vertex = new Vertex(50, 50, img2, 3);
		// kone.add(vertex);
		obrazek.pridej(vertex);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double pom1 = ((width / 2) - 150) / 100.0;
		double pom2 = ((height / 2) - 200) / 100.0;

		boolean obr = false;
		int row = 1;
		for (double i = pom2; i < pom2 + 3; i++) {
			int collumn = 1;

			for (double j = pom1; j < pom1 + 3; j++) {
				if (obr) {
					vertex = new Vertex((int) (j * 100), (int) (i * 100), img4, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(true);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = false;
				} else {
					vertex = new Vertex((int) (j * 100), (int) (i * 100), img3, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(false);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = true;
				}
			}
			row++;
		}

		vertex = new Vertex((int) (pom1 * 100 - 65), (int) (pom2 * 100 + 303), kraj9, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 - 65), (int) (pom2 * 100 - 65), kraj10, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 - 3), (int) (pom2 * 100 - 65), kraj11, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 + 303), (int) (pom2 * 100 - 4), kraj12, 2);
		obrazek.pridej(vertex);

		vertices.get(0).setEnable(false);
	}
	
	private void drawBoard4x4() {
		vertex = new Vertex(20, 20, img1, 3);
		obrazek.pridej(vertex);
		vertex = new Vertex(50, 50, img2, 3);
		obrazek.pridej(vertex);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double pom1 = ((width / 2) - 200) / 100.0;
		double pom2 = ((height / 2) - 250) / 100.0;

		boolean obr = false;
		int row = 1;
		for (double i = pom2; i < pom2 + 4; i++) {
			int collumn = 1;
			if (obr) {
				obr = false;
			} else {
				obr = true;
			}

			for (double j = pom1; j < pom1 + 4; j++) {
				if (obr) {
					vertex = new Vertex((int) (j * 100), (int) (i * 100), img3, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(false);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = false;
				} else {
					vertex = new Vertex((int) (j * 100), (int) (i * 100), img4, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(true);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = true;
				}
			}
			row++;
		}

		vertex = new Vertex((int) (pom1 * 100 - 65), (int) (pom2 * 100 + 403), kraj13, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 - 65), (int) (pom2 * 100 - 64), kraj14, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 - 5), (int) (pom2 * 100 - 64), kraj15, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 + 403), (int) (pom2 * 100 - 4), kraj16, 2);
		obrazek.pridej(vertex);
	}

	private void drawBoard5x5() {
		vertex = new Vertex(20, 20, img1, 3);
		obrazek.pridej(vertex);
		vertex = new Vertex(50, 50, img2, 3);
		obrazek.pridej(vertex);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double pom1 = ((width / 2) - 250) / 100.0;
		double pom2 = ((height / 2) - 300) / 100.0;

		boolean obr = false;
		int row = 1;
		for (double i = pom2; i < pom2 + 5; i++) {
			int collumn = 1;

			for (double j = pom1; j < pom1 + 5; j++) {
				if (obr) {
					vertex = new Vertex((int) (j * 100), (int) (i * 100), img4, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(true);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = false;
				} else {
					vertex = new Vertex((int) (j * 100), (int) (i * 100), img3, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(false);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = true;
				}
			}
			row++;
		}

		vertex = new Vertex((int) (pom1 * 100 - 65), (int) (pom2 * 100 + 503), kraj5, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 - 65), (int) (pom2 * 100 - 64), kraj6, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 - 5), (int) (pom2 * 100 - 64), kraj7, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 + 503), (int) (pom2 * 100 - 3), kraj8, 2);
		obrazek.pridej(vertex);
	}

	private void drawBoard6x6() {
		vertex = new Vertex(20, 20, img1, 3);
		obrazek.pridej(vertex);
		vertex = new Vertex(50, 50, img2, 3);
		obrazek.pridej(vertex);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double pom1 = ((width / 2) - 300) / 100.0;
		double pom2 = ((height / 2) - 350) / 100.0;

		boolean obr = false;
		int row = 1;
		for (double i = pom2; i < pom2 + 6; i++) {
			int collumn = 1;
			if (obr) {
				obr = false;
			} else {
				obr = true;
			}

			for (double j = pom1; j < pom1 + 6; j++) {
				if (obr) {
					vertex = new Vertex((int) (j * 100), (int) (i * 100), img3, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(false);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = false;
				} else {
					vertex = new Vertex((int) (j * 100), (int) (i * 100), img4, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(true);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = true;
				}
			}
			row++;
		}

		vertex = new Vertex((int) (pom1 * 100 - 65), (int) (pom2 * 100 + 603), kraj1, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 - 65), (int) (pom2 * 100 - 65), kraj2, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 - 3), (int) (pom2 * 100 - 65), kraj3, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int) (pom1 * 100 + 603), (int) (pom2 * 100 - 3), kraj4, 2);
		obrazek.pridej(vertex);
	}

	public void setAvailableVertices(List<Vertex> vertices, List<Vertex> kone, int row, int collumn) {
		this.kone = kone;

		for (Vertex v : vertices) {
			if (v.isWhite()) {
				v.setImg(img4);
			} else {
				v.setImg(img3);
			}
		}

		steps = new ArrayList<>();
		Vertex vertex = null;

		for (Vertex v : kone) {
			switch (mode) {
			case 3:
				if (v.getId() - 7 >= 0) {
					vertex = vertices.get(v.getId() - 7);
					if (vertex != null && vertex.getRow() < row) {
						vertex.setEnable(true);
						steps.add(vertex);
					}
				}
				if (v.getId() - 5 >= 0) {
					vertex = vertices.get(v.getId() - 5);
					if (vertex != null && vertex.getRow() < row) {
						vertex.setEnable(true);
						steps.add(vertex);
					}
				}
				if (v.getId() - 1 >= 0) {
					vertex = vertices.get(v.getId() - 1);
					if (vertex != null && vertex.getRow() < row) {
						vertex.setEnable(true);
						steps.add(vertex);
					}
				}
				if (v.getId() + 1 <= 8) {
					vertex = vertices.get(v.getId() + 1);
					if (vertex != null && vertex.getRow() > row) {
						vertex.setEnable(true);
						steps.add(vertex);
					}
				}
				if (v.getId() + 5 <= 8) {
					vertex = vertices.get(v.getId() + 5);
					if (vertex != null && vertex.getRow() > row) {
						vertex.setEnable(true);
						steps.add(vertex);
					}
				}
				if (v.getId() + 7 <= 8) {
					vertex = vertices.get(v.getId() + 7);
					if (vertex != null && vertex.getRow() > row) {
						vertex.setEnable(true);
						steps.add(vertex);
					}
				}
				break;
			case 4:
				if (v.getId() - 9 >= 0) {
					vertex = vertices.get(v.getId() - 9);
					if (vertex != null && vertex.getRow() < v.getRow() && collumn > vertex.getCollumn()) {
						steps.add(vertex);
					}
				}
				if (v.getId() - 7 >= 0) {
					vertex = vertices.get(v.getId() - 7);
					if (vertex != null && vertex.getRow() == v.getRow() - 2) {
						steps.add(vertex);
					}
				}
				if (v.getId() - 6 >= 0) {
					vertex = vertices.get(v.getId() - 6);
					if (vertex != null && vertex.getRow() == v.getRow() - 1) {
						steps.add(vertex);
					}
				}
				if (v.getId() - 2 >= 0) {
					vertex = vertices.get(v.getId() - 2);
					if (vertex != null && vertex.getRow() < v.getRow()) {
						steps.add(vertex);
					}
				}
				if (v.getId() + 2 <= 15) {
					vertex = vertices.get(v.getId() + 2);
					if (vertex != null && vertex.getRow() > v.getRow()) {
						steps.add(vertex);
					}
				}
				if (v.getId() + 6 <= 15) {
					vertex = vertices.get(v.getId() + 6);
					if (vertex != null && vertex.getRow() == v.getRow() + 1) {
						steps.add(vertex);
					}
				}
				if (v.getId() + 7 <= 15) {
					vertex = vertices.get(v.getId() + 7);
					if (vertex != null && vertex.getRow() == v.getRow() + 2) {
						steps.add(vertex);
					}
				}
				if (v.getId() + 9 <= 15) {
					vertex = vertices.get(v.getId() + 9);
					if (vertex != null && vertex.getRow() > v.getRow() && v.getCollumn() < vertex.getCollumn()) {
						steps.add(vertex);
					}
				}
				break;
			case 5:
				if (v.getId() - 11 >= 0) {
					vertex = vertices.get(v.getId() - 11);
					if (vertex != null && vertex.getRow() == v.getRow() - 2) {
						steps.add(vertex);
					}
				}
				if (v.getId() - 9 >= 0) {
					vertex = vertices.get(v.getId() - 9);
					if (vertex != null && vertex.getRow() == v.getRow() - 2) {
						steps.add(vertex);
					}
				}
				if (v.getId() - 7 >= 0) {
					vertex = vertices.get(v.getId() - 7);
					if (vertex != null && vertex.getRow() == v.getRow() - 1) {
						steps.add(vertex);
					}
				}
				if (v.getId() - 3 >= 0) {
					vertex = vertices.get(v.getId() - 3);
					if (vertex != null && vertex.getRow() < v.getRow()) {
						steps.add(vertex);
					}
				}
				if (v.getId() + 3 <= 24) {
					vertex = vertices.get(v.getId() + 3);
					if (vertex != null && vertex.getRow() > v.getRow()) {
						steps.add(vertex);
					}
				}
				if (v.getId() + 7 <= 24) {
					vertex = vertices.get(v.getId() + 7);
					if (vertex != null && vertex.getRow() == v.getRow() + 1) {
						steps.add(vertex);
					}
				}
				if (v.getId() + 9 <= 24) {
					vertex = vertices.get(v.getId() + 9);
					if (vertex != null && vertex.getRow() == v.getRow() + 2) {
						steps.add(vertex);
					}
				}
				if (v.getId() + 11 <= 24) {
					vertex = vertices.get(v.getId() + 11);
					if (vertex != null && vertex.getRow() == v.getRow() + 2) {
						steps.add(vertex);
					}
				}
				break;
			case 6:
				if (v.getId() - 13 >= 0) {
					vertex = vertices.get(v.getId() - 13);
					if (vertex != null && vertex.getRow() == v.getRow() - 2) {
						steps.add(vertex);
					}
				}
				if (v.getId() - 11 >= 0) {
					vertex = vertices.get(v.getId() - 11);
					if (vertex != null && vertex.getRow() == v.getRow() - 2) {
						steps.add(vertex);
					}
				}
				if (v.getId() - 8 >= 0) {
					vertex = vertices.get(v.getId() - 8);
					if (vertex != null && vertex.getRow() == v.getRow() - 1) {
						steps.add(vertex);
					}
				}
				if (v.getId() - 4 >= 0) {
					vertex = vertices.get(v.getId() - 4);
					if (vertex != null && vertex.getRow() < v.getRow()) {
						steps.add(vertex);
					}
				}
				if (v.getId() + 4 <= 35) {
					vertex = vertices.get(v.getId() + 4);
					if (vertex != null && vertex.getRow() > v.getRow()) {
						vertex.setEnable(true);
						steps.add(vertex);
					}
				}
				if (v.getId() + 8 <= 35) {
					vertex = vertices.get(v.getId() + 8);
					if (vertex != null && vertex.getRow() == v.getRow() + 1) {
						steps.add(vertex);
					}
				}
				if (v.getId() + 11 <= 35) {
					vertex = vertices.get(v.getId() + 11);
					if (vertex != null && vertex.getRow() == v.getRow() + 2) {
						steps.add(vertex);
					}
				}
				if (v.getId() + 13 <= 35) {
					vertex = vertices.get(v.getId() + 13);
					if (vertex != null && vertex.getRow() == v.getRow() + 2) {
						steps.add(vertex);
					}
				}
				break;
			default:
				break;
			}
		}

		for (Vertex v : steps) {
			if (v.isWhite()) {
				v.setImg(img4G);
			} else {
				v.setImg(img3G);
			}
		}
		
		for (Vertex v2 : kone) {
			for (Vertex v : vertices) {
				if (v.getRow() == v2.getRow() && v.getCollumn() == v2.getCollumn()) {
					if (v.isWhite()) {
						v.setImg(img4G);
					} else {
						v.setImg(img3G);
					}
				}
			}
		}
	}

	public void pridatKone(MainWindow main, Image obrazek, List<Vertex> kone) {
		this.kone = kone;
		this.obrazek = obrazek;

		int p = 0;
		while (true) {
			boolean loop = false;
			for (Vertex vertex : kone) {
				if (p == vertex.getId()) {
					loop = true;
					break;
				}
			}
			if (!loop) {
				break;
			}
			p++;
		}

		int x = p%mode + 1;
		int y = (p/mode) + 1;

		switch (mode) {
		case 4:
			vertex = new Vertex(795 + (100*(x-1)), 328 + (100*(y-1)), img2, 3);
			break;
		case 5:
			vertex = new Vertex(745 + (100*(x-1)), 278 + (100*(y-1)), img2, 3);
			break;
		case 6:
			vertex = new Vertex(695 + (100*(x-1)), 228 + (100*(y-1)), img2, 3);
			break;
		default:
			break;
		}
		
		this.vertex.setRow(y);
		this.vertex.setCollumn(x);
		this.vertex.setId(p);
		this.kone.add(vertex);
		this.obrazek.pridej(vertex);

		main.setKone(this.kone);
		main.setObrazek(this.obrazek);
	}
}
