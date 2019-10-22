package cz.uhk.diplom;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import cz.uhk.diplom.model.Edge;
import cz.uhk.diplom.model.Image;
import cz.uhk.diplom.model.Vertex;

public class Simulation {

	BufferedImage img1 = null, img2 = null, img3 = null, img4 = null, kraj1 = null, kraj2 = null, kraj3 = null, kraj4 = null,
			kraj5 = null, kraj6 = null, kraj7 = null, kraj8 = null, kraj9 = null, kraj10 = null, kraj11 = null, kraj12 = null,
					kraj13 = null, kraj14 = null, kraj15 = null, kraj16 = null, img3G=null, img4G = null;
	
	private Image obrazek;
	private int mode;
	Vertex vertex, kun;
	Edge edge;
	boolean prohledej = true;
	private int policko = 0;
	
	private List<Vertex> vertices = new ArrayList<>();
	private List<Vertex> kone = new ArrayList<>();
	private List<Vertex> steps = new ArrayList<>();
	private List<Edge> edges = new ArrayList<>();
	private List<Vertex> path = new ArrayList<>();
	
	public Simulation(Image image) {
		this.obrazek=image;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void setBoard(List<Vertex> kone, List<Vertex> vertices,List<Edge> edges, Image obrazek, MainWindow main, int mode){
		this.kone=kone;
		this.vertices=vertices;
		this.obrazek=obrazek;
		this.edges=edges;
		this.mode = mode;

		switch (mode) {
		case 2:
			drawBoard4x4();
			kun = new Vertex(795,328, img2, 3);
			break;
		case 3:
			drawBoard5x5();
			kun = new Vertex(745,278, img2, 3);
			break;
		case 4:
			drawBoard6x6();
			kun = new Vertex(695,228, img2, 3);
			break;
		default:
			break;
		}
		this.kun.setRow(1);
		this.kun.setCollumn(1);
		this.kun.setId(policko);
		this.kone.add(kun);
		this.obrazek.pridej(kun);

		main.setKone(this.kone);
		main.setObrazek(this.obrazek);
		for (Vertex vertex : vertices) {
			vertex.setEnable(true);
		}
		main.setVertices(this.vertices);
		main.setEdges(this.edges);
	}



	private void drawBoard4x4() {
		// prvni vykresleni
		vertex = new Vertex(20, 20, img1, 3);
		obrazek.pridej(vertex);
		vertex = new Vertex(50, 50, img2, 3);
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
	}


	private void drawBoard5x5() {
		// prvni vykresleni
		vertex = new Vertex(20, 20, img1, 3);
		obrazek.pridej(vertex);
		vertex = new Vertex(50, 50, img2, 3);
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
	}

	private void drawBoard6x6() {
		// prvni vykresleni
		vertex = new Vertex(20, 20, img1, 3);
		obrazek.pridej(vertex);
		vertex = new Vertex(50, 50, img2, 3);
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
	}

	public void setAvailableVertices(List<Vertex> vertices, Vertex v) {
		/*
		for (Vertex vertex : steps) {
			if (vertex.isWhite()) {
				vertex.setImg(img4);
			}else {
				vertex.setImg(img3);
			}
		}
		*/

		// UZAVRENE CESTY
		/*
		if (path.size() == 25) {
			vertices.get(0).setEnable(true);
		}
		*/
		
		steps = new ArrayList<>();
		Vertex vertex = null;
		
		switch (mode) {
		case 2:
			if (v.getId()-9 >= 0) {
				vertex = vertices.get(v.getId()-9);
				if (vertex != null && vertex.getRow()<v.getRow()) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-9);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()-7 >= 0) {
				vertex = vertices.get(v.getId()-7);
				if (vertex != null && vertex.getRow()==v.getRow()-2) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-7);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()-6 >= 0) {
				vertex = vertices.get(v.getId()-6);
				if (vertex != null && vertex.getRow()==v.getRow()-1) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-6);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()-2 >= 0) {
				vertex = vertices.get(v.getId()-2);
				if (vertex != null && vertex.getRow()<v.getRow()) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-2);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+2 <= 15) {
				vertex = vertices.get(v.getId()+2);
				if (vertex != null && vertex.getRow()>v.getRow()) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+2);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+6 <= 15) {
				vertex = vertices.get(v.getId()+6);
				if (vertex != null && vertex.getRow()==v.getRow()+1) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+6);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+7 <= 15) {
				vertex = vertices.get(v.getId()+7);
				if (vertex != null && vertex.getRow()==v.getRow()+2) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+7);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+9 <= 15) {
				vertex = vertices.get(v.getId()+9);
				if (vertex != null && vertex.getRow()>v.getRow()) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+9);
						steps.add(vertex);
					}
				}
			}
			break;
		case 3:
			if (v.getId()-11 >= 0) {
				vertex = vertices.get(v.getId()-11);
				if (vertex != null && vertex.getRow()==v.getRow()-2) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-11);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()-9 >= 0) {
				vertex = vertices.get(v.getId()-9);
				if (vertex != null && vertex.getRow()==v.getRow()-2) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-9);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()-7 >= 0) {
				vertex = vertices.get(v.getId()-7);
				if (vertex != null && vertex.getRow()==v.getRow()-1) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-7);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()-3 >= 0) {
				vertex = vertices.get(v.getId()-3);
				if (vertex != null && vertex.getRow()<v.getRow()) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-3);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+3 <= 24) {
				vertex = vertices.get(v.getId()+3);
				if (vertex != null && vertex.getRow()>v.getRow()) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+3);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+7 <= 24) {
				vertex = vertices.get(v.getId()+7);
				if (vertex != null && vertex.getRow()==v.getRow()+1) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+7);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+9 <= 24) {
				vertex = vertices.get(v.getId()+9);
				if (vertex != null && vertex.getRow()==v.getRow()+2) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+9);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+11 <= 24) {
				vertex = vertices.get(v.getId()+11);
				if (vertex != null && vertex.getRow()==v.getRow()+2) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+11);
						steps.add(vertex);
					}
				}
			}
			break;
		case 4:
			if (v.getId()-13 >= 0) {
				vertex = vertices.get(v.getId()-13);
				if (vertex != null && vertex.getRow()==v.getRow()-2) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-13);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()-11 >= 0) {
				vertex = vertices.get(v.getId()-11);
				if (vertex != null && vertex.getRow()==v.getRow()-2) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-11);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()-8 >= 0) {
				vertex = vertices.get(v.getId()-8);
				if (vertex != null && vertex.getRow()==v.getRow()-1) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-8);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()-4>= 0) {
				vertex = vertices.get(v.getId()-4);
				if (vertex != null && vertex.getRow()<v.getRow()) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()-4);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+4 <= 35) {
				vertex = vertices.get(v.getId()+4);
				if (vertex != null && vertex.getRow()>v.getRow()) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+4);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+8 <= 35) {
				vertex = vertices.get(v.getId()+8);
				if (vertex != null && vertex.getRow()==v.getRow()+1) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+8);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+11 <= 35) {
				vertex = vertices.get(v.getId()+11);
				if (vertex != null && vertex.getRow()==v.getRow()+2) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+11);
						steps.add(vertex);
					}
				}
			}
			if (v.getId()+13 <= 35) {
				vertex = vertices.get(v.getId()+13);
				if (vertex != null && vertex.getRow()==v.getRow()+2) {
					if (vertex.isEnable()) {
						vertex.setId(v.getId()+13);
						steps.add(vertex);
					}
				}
			}
			break;
		default:
			break;
		}
		
		for (int i = 0;  i < steps.size(); i++) {
			if (v.getVisited().contains(steps.get(i))) {
				steps.get(i).setEnable(false);
			}
		}	
		
	}
	
	public boolean searchHam(List<Vertex> vertices, MainWindow main) {
		this.vertices = vertices;
		
		if (path.size() == 0) {
			this.kun.setId(policko);
			setAvailableVertices(this.vertices, kun);
		}

		edge = new Edge(kun.getX1()+16, kun.getY1()+14, kun.getX1()+16, kun.getY1()+14);
		
		/*
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		Vertex actual = this.vertices.get(kun.getId());
		if (path.size() == 0) {
			actual = this.vertices.get(policko);
			actual.setId(policko);
			path.add(actual);
			actual.setEnable(false);
			this.vertices.get(actual.getId()).setEnable(false);
			//System.out.println(this.steps.get(0).isEnable() + " " + this.steps.get(0).getId() + " ENABLE " + this.vertices.get(0).isEnable() + " " + actual.getId() + " " +actual.isEnable());
		}

		boolean correct = false;
		for (int i = 0; i<steps.size(); i++) {
			//System.out.println();
			//System.out.println(steps.size() +" "+actual.getId() + " " + actual.getVisited().size()+ " " + i + " ACTUAL");
			/*
			if (actual.getVisited().size() != 0) {
				System.out.println(actual.getVisited().get(0));
			}
			*/
			//System.out.println();
			if (!actual.getVisited().contains(steps.get(i))) {
				/*
				System.out.println();
				System.out.println(steps.size() + " velikost");
				System.out.println(steps.get(i).getId() + " " + steps.get(i).getRow() + " " + steps.get(i).getCollumn() + " step");
				*/
				kun.setId(steps.get(i).getId());
				steps.get(i).setEnable(false);
				this.vertices.get(steps.get(i).getId()).setEnable(false);

				path.add(steps.get(i));
				actual.getVisited().add(steps.get(i));
				correct = true;
				break;
			}
		}
		main.setVertices(this.vertices);
		if (!correct) {
			prohledej = false;
			return false;
		}else {
			prohledej = true;
			return true;
		}
		
	}
	
	public List<Vertex> getPath(){
		return path;
	}

	public Vertex moveHorse(MainWindow main) {

		//System.out.println(path.size() + " SIZE");
		if (prohledej) {
			
			kun.setX1(path.get(path.size()-1).getX1() + 35);
			kun.setY1(path.get(path.size()-1).getY1() + 38);
			kun.setRow(path.get(path.size()-1).getRow());
			kun.setCollumn(path.get(path.size()-1).getCollumn());
			
		}else {
			path.get(path.size()-1).setEnable(true);
			path.get(path.size()-1).setVisited(new ArrayList<>());
			path.remove(path.size()-1);

			//System.out.println(path.size() + " SIZE");
			
			if (path.size()-1 < 0) {
				obrazek.odeber(kun);

				//System.out.println(path.size() + " SIZE CoREECT " + policko + " " + kun.getId() + " " + kun.getCollumn() + " " + kun.getRow());
				System.out.println(main.getPocet() + " POCET CoREECT");
				
				policko++;
				kun.setId(policko);
				if ((policko % (mode+2)) == 0) {
					kun.setCollumn(1);
					kun.setRow(kun.getRow()+1);
				}else {
					kun.setCollumn(kun.getCollumn()+1);
				}
				
				edges = new ArrayList<>();
				path = new ArrayList<>();
				edges.clear();
				kone.clear();
				kone.add(kun);
				obrazek.pridej(kun);

				main.setKone(this.kone);
				main.setObrazek(this.obrazek);
				for (Vertex vertex : vertices) {
					vertex.setEnable(true);
				}
				main.setVertices(this.vertices);
				main.setEdges(this.edges);

				//System.out.println(path.size() + " SIZE CoREECT " + policko + " " + kun.getId() + " " + kun.getCollumn() + " " + kun.getRow());
				System.out.println();
				setAvailableVertices(vertices, kun);
				return kun;
			}
			
			kun.setId(path.get(path.size()-1).getId());
			kun.setX1(path.get(path.size()-1).getX1() + 35);
			kun.setY1(path.get(path.size()-1).getY1() + 38);
			kun.setRow(path.get(path.size()-1).getRow());
			kun.setCollumn(path.get(path.size()-1).getCollumn());
			
		}
		/*System.out.println();
		System.out.println(kun.getId());
		System.out.println(path.size() + " sizePath");
		System.out.println(kun.getRow() + " " + kun.getCollumn() + " radek a sloupec");
		*/
		setAvailableVertices(vertices, kun);
		return kun;
	}
	
	public Edge makeRoad() {
		edge.setX2(path.get(path.size()-1).getX1()+50);
		edge.setY2(path.get(path.size()-1).getY1()+50);
		return edge;
	}


	public Vertex nextPath() {
		path.get(path.size()-1).setEnable(true);
		path.get(path.size()-1).setVisited(new ArrayList<>());
		path.remove(path.size()-1);
		
		kun.setId(path.get(path.size()-1).getId());
		kun.setX1(path.get(path.size()-1).getX1() + 35);
		kun.setY1(path.get(path.size()-1).getY1() + 38);
		kun.setRow(path.get(path.size()-1).getRow());
		kun.setCollumn(path.get(path.size()-1).getCollumn());

		setAvailableVertices(vertices, kun);
		return kun;
	}
	
	public int getPolicko() {
		return policko;
	}
	
	public void setPolicko() {
		policko = 0;
	}
	
	public void generateAllSolutions() {
		
	}
}
