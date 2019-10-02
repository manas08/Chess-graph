package cz.uhk.diplom;

import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import cz.uhk.diplom.model.Image;
import cz.uhk.diplom.model.Vertex;

import javax.imageio.ImageIO;

public class Settings {

	BufferedImage img1 = null, img2 = null, img3 = null, img4 = null, kraj1 = null, kraj2 = null, kraj3 = null, kraj4 = null,
			kraj5 = null, kraj6 = null, kraj7 = null, kraj8 = null, kraj9 = null, kraj10 = null, kraj11 = null, kraj12 = null,
					kraj13 = null, kraj14 = null, kraj15 = null, kraj16 = null, img3G=null, img4G = null;
	Vertex vertex;
	private Image obrazek = new Image();
	private List<Vertex> vertices;
	private List<Vertex> kone;
	private List<Vertex> points;
	private List<Vertex> steps;
	private int mode;
	
	public Settings() {
		try {
			img1 = ImageIO.read(getClass().getResourceAsStream("/textures/whitehorse.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/textures/blackhorse.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/textures/brick.jpg"));
			img4 = ImageIO.read(getClass().getResourceAsStream("/textures/brick2.jpg"));
			img3G = ImageIO.read(getClass().getResourceAsStream("/textures/brickG.jpg"));
			img4G = ImageIO.read(getClass().getResourceAsStream("/textures/brick2G.jpg"));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setBoard(List<Vertex> kone, List<Vertex> vertices, List<Vertex> points, Image obrazek, MainWindow main, int mode){
		this.kone=kone;
		this.vertices=vertices;
		this.points=points;
		this.obrazek=obrazek;
		this.mode = mode;
		steps = new ArrayList<>();

		switch (mode) {
		case 1:
			drawBoard3x3();
			vertex = new Vertex(845,378, img1, 3);
			break;
		case 2:
			drawBoard4x4();
			vertex = new Vertex(795,328, img1, 3);
			break;
		case 3:
			drawBoard5x5();
			vertex = new Vertex(745,278, img1, 3);
			break;
		case 4:
			drawBoard6x6();
			vertex = new Vertex(695,228, img1, 3);
			break;
		default:
			break;
		}
		this.vertex.setRow(1);
		this.vertex.setCollumn(1);
		this.kone.add(vertex);
		
		Vertex point = new Vertex(vertex.getX1(), vertex.getY1(), 30, 30);
		point.setRow(1);
		point.setCollumn(1);
		this.points.add(point);
		this.obrazek.pridej(point);
		this.obrazek.pridej(vertex);

		main.setKone(this.kone);
		main.setObrazek(this.obrazek);
		main.setVertices(this.vertices);
		main.setPoints(this.points);
	}

	private void drawBoard3x3() {
		// prvni vykresleni
		vertex = new Vertex(20, 20, img1, 3);
		kone.add(vertex);
		obrazek.pridej(vertex);
		vertex = new Vertex(50, 50, img2, 3);
		kone.add(vertex);
		obrazek.pridej(vertex);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double pom1 = ((width/2)-150)/100.0;
		double pom2 = ((height/2)-200)/100.0;
		
		boolean obr = false;
		int row = 1;
		for (double i = pom2; i < pom2 + 3; i++) {
			int collumn = 1;						
			
			//System.out.println(pom1 + " " + pom2);
			//System.out.println(i + " !!!!");
			
			for (double j = pom1; j < pom1 + 3; j++) {
				if (obr) {
					vertex = new Vertex((int)(j*100), (int)(i*100), img4, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(true);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = false;
				}else {
					vertex = new Vertex((int)(j*100), (int)(i*100), img3, 2);
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
		
		
		vertex = new Vertex((int)(pom1*100 - 65), (int)(pom2*100 + 303), kraj9, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 - 65), (int)(pom2*100 - 65), kraj10, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 - 3), (int)(pom2*100 - 65), kraj11, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 + 303), (int)(pom2*100 - 4), kraj12, 2);
		obrazek.pridej(vertex);
		
		vertices.get(0).setEnable(false);
	}


	private void drawBoard4x4() {
		// prvni vykresleni
		vertex = new Vertex(20, 20, img1, 3);
		kone.add(vertex);
		obrazek.pridej(vertex);
		vertex = new Vertex(50, 50, img2, 3);
		kone.add(vertex);
		obrazek.pridej(vertex);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double pom1 = ((width/2)-200)/100.0;
		double pom2 = ((height/2)-250)/100.0;
		
		boolean obr = false;
		int row = 1;
		for (double i = pom2; i < pom2 + 4; i++) {
			int collumn = 1;
			if (obr) {
				obr = false;
			}else {
				obr = true;
			}
			
			for (double j = pom1; j < pom1 + 4; j++) {
				if (obr) {
					vertex = new Vertex((int)(j*100), (int)(i*100), img3, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(false);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = false;
				}else {
					vertex = new Vertex((int)(j*100), (int)(i*100), img4, 2);
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
		
		
		vertex = new Vertex((int)(pom1*100 - 65), (int)(pom2*100 + 403), kraj13, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 - 65), (int)(pom2*100 - 64), kraj14, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 - 5), (int)(pom2*100 - 64), kraj15, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 + 403), (int)(pom2*100 - 4), kraj16, 2);
		obrazek.pridej(vertex);
		
		vertices.get(0).setEnable(false);
	}


	private void drawBoard5x5() {
		// prvni vykresleni
		vertex = new Vertex(20, 20, img1, 3);
		kone.add(vertex);
		obrazek.pridej(vertex);
		vertex = new Vertex(50, 50, img2, 3);
		kone.add(vertex);
		obrazek.pridej(vertex);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double pom1 = ((width/2)-250)/100.0;
		double pom2 = ((height/2)-300)/100.0;
		
		boolean obr = false;
		int row = 1;
		for (double i = pom2; i < pom2 + 5; i++) {
			int collumn = 1;
			
			for (double j = pom1; j < pom1 + 5; j++) {
				if (obr) {
					vertex = new Vertex((int)(j*100), (int)(i*100), img4, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(true);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = false;
				}else {
					vertex = new Vertex((int)(j*100), (int)(i*100), img3, 2);
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
		
		
		vertex = new Vertex((int)(pom1*100 - 65), (int)(pom2*100 + 503), kraj5, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 - 65), (int)(pom2*100 - 64), kraj6, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 - 5), (int)(pom2*100 - 64), kraj7, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 + 503), (int)(pom2*100 - 3), kraj8, 2);
		obrazek.pridej(vertex);
		
		vertices.get(0).setEnable(false);
	}

	private void drawBoard6x6() {
		// prvni vykresleni
		vertex = new Vertex(20, 20, img1, 3);
		kone.add(vertex);
		obrazek.pridej(vertex);
		vertex = new Vertex(50, 50, img2, 3);
		kone.add(vertex);
		obrazek.pridej(vertex);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double pom1 = ((width/2)-300)/100.0;
		double pom2 = ((height/2)-350)/100.0;
		
		boolean obr = false;
		int row = 1;
		for (double i = pom2; i < pom2 + 6; i++) {
			int collumn = 1;
			if (obr) {
				obr = false;
			}else {
				obr = true;
			}
			
			for (double j = pom1; j < pom1 + 6; j++) {
				if (obr) {
					vertex = new Vertex((int)(j*100), (int)(i*100), img3, 2);
					vertex.setCollumn(collumn);
					vertex.setRow(row);
					vertex.setWhite(false);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = false;
				}else {
					vertex = new Vertex((int)(j*100), (int)(i*100), img4, 2);
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
		
		
		vertex = new Vertex((int)(pom1*100 - 65), (int)(pom2*100 + 603), kraj1, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 - 65), (int)(pom2*100 - 65), kraj2, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 - 3), (int)(pom2*100 - 65), kraj3, 2);
		obrazek.pridej(vertex);
		vertex = new Vertex((int)(pom1*100 + 603), (int)(pom2*100 - 3), kraj4, 2);
		obrazek.pridej(vertex);
		
		vertices.get(0).setEnable(false);
	}
	
	public void setAvailableVertices(List<Vertex> vertices, int i, int row) {
		System.out.println(vertices.size() + "///////////" + steps.size());

		for (Vertex v : steps) {
			if (v.isWhite()) {
				v.setImg(img4);
			}else {
				v.setImg(img3);
			}
		}
		
		steps = new ArrayList<>();
		Vertex vertex = null;
		for (Vertex vertex2 : vertices) {
			vertex2.setEnable(false);
		}
		
		switch (mode) {
		case 1:
			if (i-7 >= 0) {
				vertex = vertices.get(i-7);
				if (vertex != null && vertex.getRow()<row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i-5 >= 0) {
				vertex = vertices.get(i-5);
				if (vertex != null && vertex.getRow()<row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i-1 >= 0) {
				vertex = vertices.get(i-1);
				if (vertex != null && vertex.getRow()<row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+1 <= 8) {
				vertex = vertices.get(i+1);
				if (vertex != null && vertex.getRow()>row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+5 <= 8) {
				vertex = vertices.get(i+5);
				if (vertex != null && vertex.getRow()>row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+7 <= 8) {
				vertex = vertices.get(i+7);
				if (vertex != null && vertex.getRow()>row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			break;
		case 2:
			if (i-9 >= 0) {
				vertex = vertices.get(i-9);
				if (vertex != null && vertex.getRow()<row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i-7 >= 0) {
				vertex = vertices.get(i-7);
				if (vertex != null && vertex.getRow()==row-2) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i-6 >= 0) {
				vertex = vertices.get(i-6);
				if (vertex != null && vertex.getRow()==row-1) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i-2 >= 0) {
				vertex = vertices.get(i-2);
				if (vertex != null && vertex.getRow()<row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+2 <= 15) {
				vertex = vertices.get(i+2);
				if (vertex != null && vertex.getRow()>row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+6 <= 15) {
				vertex = vertices.get(i+6);
				if (vertex != null && vertex.getRow()==row+1) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+7 <= 15) {
				vertex = vertices.get(i+7);
				if (vertex != null && vertex.getRow()==row+2) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+9 <= 15) {
				vertex = vertices.get(i+9);
				if (vertex != null && vertex.getRow()>row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			break;
		case 3:
			if (i-11 >= 0) {
				vertex = vertices.get(i-11);
				if (vertex != null && vertex.getRow()==row-2) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i-9 >= 0) {
				vertex = vertices.get(i-9);
				if (vertex != null && vertex.getRow()==row-2) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i-7 >= 0) {
				vertex = vertices.get(i-7);
				if (vertex != null && vertex.getRow()==row-1) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i-3 >= 0) {
				vertex = vertices.get(i-3);
				if (vertex != null && vertex.getRow()<row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+3 <= 24) {
				vertex = vertices.get(i+3);
				if (vertex != null && vertex.getRow()>row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+7 <= 24) {
				vertex = vertices.get(i+7);
				if (vertex != null && vertex.getRow()==row+1) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+9 <= 24) {
				vertex = vertices.get(i+9);
				if (vertex != null && vertex.getRow()==row+2) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+11 <= 24) {
				vertex = vertices.get(i+11);
				if (vertex != null && vertex.getRow()==row+2) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			break;
		case 4:
			if (i-13 >= 0) {
				vertex = vertices.get(i-13);
				if (vertex != null && vertex.getRow()==row-2) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i-11 >= 0) {
				vertex = vertices.get(i-11);
				if (vertex != null && vertex.getRow()==row-2) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i-8 >= 0) {
				vertex = vertices.get(i-8);
				if (vertex != null && vertex.getRow()==row-1) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i-4>= 0) {
				vertex = vertices.get(i-4);
				if (vertex != null && vertex.getRow()<row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+4 <= 35) {
				vertex = vertices.get(i+4);
				if (vertex != null && vertex.getRow()>row) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+8 <= 35) {
				vertex = vertices.get(i+8);
				if (vertex != null && vertex.getRow()==row+1) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+11 <= 35) {
				vertex = vertices.get(i+11);
				if (vertex != null && vertex.getRow()==row+2) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			if (i+13 <= 35) {
				vertex = vertices.get(i+13);
				if (vertex != null && vertex.getRow()==row+2) {
					vertex.setEnable(true);
					steps.add(vertex);
				}
			}
			break;
		default:
			break;
		}
		
		for (Vertex vertex2 : points) {
			System.out.println("++++++++" + vertex2.getRow() + " " + vertex2.getCollumn());
			int rowV = vertex2.getRow();
			int collumnV = vertex2.getCollumn();
			for (Vertex vertex3 : vertices) {
				if (vertex3.getRow()==rowV && vertex3.getCollumn()==collumnV) {
					vertex3.setEnable(false);
				}
			}
		}
		
		for (Vertex v : steps) {
			if (v.isWhite()) {
				v.setImg(img4G);
			}else {
				v.setImg(img3G);
			}
		}
		System.out.println("++++++++" + points.size());
	}
	
}
