package cz.uhk.diplom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import cz.uhk.diplom.model.Vertex;
import cz.uhk.diplom.utils.Filer;
import cz.uhk.diplom.utils.MyTextArea;
import cz.uhk.diplom.model.Edge;
import cz.uhk.diplom.model.Image;

public class MainWindow extends JFrame implements MouseListener, MouseMotionListener {
	private Image obrazek = new Image();
	private Canvas platno = new Canvas(obrazek);
	private GUI gui = new GUI();
	private Filer filer = new Filer();
	private List<Vertex> vertices = new ArrayList<>();
	private List<Vertex> kone = new ArrayList<>();
	private List<Vertex> points = new ArrayList<>();
	private List<Edge> edges = new ArrayList<>();
	private int clickX = 0;
	private int clickY = 0;
	private int saveX = 0;
	private int saveY = 0;
	private int saveId = 0;
	private int chesssize = 6;
	private int tvar=0, counter = 0;
	private Vertex vertex;
	private Edge edge;
	private Settings settings;
	private CoverGame coverGame;
	private Simulation simulation;
	private Hamilton hamilton;
	private static MainMenu mainMenu;
	private static MyTextArea help;
	private int test = 0;
	private JMenuBar jMenuBar;
	private boolean isCoverGame = false, isHamiltonGame = false;
	private int idKun;
	private int mode;
	private int kal = 0;
	BufferedImage img1 = null;
	
	public MainWindow() {
		setTitle("ChessGraph");
		setSize(500, 500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JToolBar tb = new JToolBar();
		add(tb, BorderLayout.NORTH);
		add(platno, BorderLayout.CENTER);
		setJMenuBar(gui.createGUI(this, 500, 500));
		jMenuBar = new JMenuBar();
		jMenuBar.setPreferredSize(new Dimension(500, 30));
        getContentPane().add(jMenuBar, BorderLayout.SOUTH);
        
		platno.addMouseListener(this);
		platno.addMouseMotionListener(this);
		getContentPane().setBackground(Color.GRAY); 
        gui.enabledMenu(false);

        help = new MyTextArea(350, 350);
        help.setVisible(false);
		platno.add(help);
		
		settings = new Settings();
		coverGame = new CoverGame();
		simulation = new Simulation();
	}

	public static void main(String[] args) {
		MainWindow okno = new MainWindow();
		okno.setVisible(true);
		openMenu(okno);
	}

	public static void openMenu(MainWindow okno) {
		mainMenu = new MainMenu(okno);
		mainMenu.setVisible(true);
		mainMenu.setAlwaysOnTop (true);
		mainMenu.setFocusableWindowState(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(mode == 1){
			for (Vertex vertex : settings.getSteps()) {
				if (vertex.isEnable()) {
					if (vertex.getX1() < e.getX() && vertex.getX1()+100 > e.getX() && e.getY() > vertex.getY1() && e.getY() < vertex.getY1()+100) {
						edge = new Edge(kone.get(0).getX1()+10, kone.get(0).getY1()+10, vertex.getX1()+50, vertex.getY1()+50, 1);
						edges.add(edge);
						obrazek.pridej(this.edge);
						
						this.vertex = kone.get(0);
						this.vertex.setX1(vertex.getX1()+35);
						this.vertex.setY1(vertex.getY1()+38);
						this.vertex.setRow(vertex.getRow());
						this.vertex.setCollumn(vertex.getCollumn());
						if (vertices.size() == 0) {
							vertices.add(vertex);
						}
						
						int locId = 0;
						for (int i = 0; i < vertices.size(); i++) {
							if (vertices.get(i) == vertex) {
								locId = i;
							}
						}
						this.vertex.setId(locId);
						
						Vertex point = new Vertex(vertex.getX1()+35, vertex.getY1()+35, 30, 30, 1);
						point.setRow(vertex.getRow());
						point.setCollumn(vertex.getCollumn());
						points.add(point);
						obrazek.pridej(point);

						kone.remove(getKone(idKun));
						kone.add(this.vertex);
						obrazek.pridej(this.vertex);
						
						vertex.setEnable(false);
						settings.setAvailableVertices(vertices, locId, vertex.getRow(), vertex.getCollumn());
					}
				}
			}
		}
	}

	public static double calculateAngle(int x1, int y1, int x2, int y2)
	{
	    double angle = Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));
	    return angle + Math.ceil( -angle / 360 ) * 360;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if (test != 0) {
			if (!isHamiltonGame) {
				if (!isCoverGame) {
					edge.setX2(e.getX()+16);
					edge.setY2(e.getY()+14);
				}
				vertex.setX1(e.getX());
				vertex.setY1(e.getY());
			}else {
				edge.setX2(e.getX());
				edge.setY2(e.getY());
			}
			platno.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clickX = e.getX();
		clickY = e.getY();
		tvar = 0;
		test = 0;
		Color c = new Color(0,255,0);
		if (isHamiltonGame) {
			for (Vertex vertex : points) {
				//System.out.println(vertex.getX1() + " " + vertex.getY1());
				if (vertex.getX1() <= clickX && vertex.getX1()+20 >= clickX && vertex.getY1() <= clickY && vertex.getY1()+20 >= clickY && vertex.getColor().getGreen() == c.getGreen()) {
					this.vertex = vertex;
					
					saveX = vertex.getX1();
					saveY = vertex.getY1();
					
					this.vertex.setColor(new Color(255,0,0));
					if (vertices.size() == 0) {
						vertices.add(this.vertex);
					}
					
					edge = new Edge(vertex.getX1()+10, vertex.getY1()+10, vertex.getX1()+10, vertex.getY1()+10, 1);
					edge.setV1ID(this.vertex.getId());
					edges.add(edge);
					obrazek.pridej(edge);
					obrazek.pridej(vertex);	//mozna zbytecne ..jen aby se prekryla lajna					
					
					idKun = vertex.getId();
					platno.repaint();
					test++;
					tvar++;
				} 
			}	
		}else {

			for (Vertex vertex : kone) {
				//System.out.println(vertex.getX1() + " " + vertex.getY1());
				if (vertex.getX1() <= clickX && vertex.getX1()+50 >= clickX && vertex.getY1() <= clickY && vertex.getY1()+77 >= clickY) {
					this.vertex = vertex;
					
					saveX = vertex.getX1();
					saveY = vertex.getY1();
					saveId = vertex.getId();
					
					if (!isCoverGame) {
							edge = new Edge(vertex.getX1()+16, vertex.getY1()+14, vertex.getX1()+16, vertex.getY1()+14, 1);
							edges.add(edge);
							obrazek.pridej(edge);					
					}
					
					idKun = vertex.getId();
					platno.repaint();
					tvar++;
					test++;
				} 
			}	
		}
	

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clickX = e.getX();
		clickY = e.getY();
		
		if (isHamiltonGame) {
			if (tvar != 0) {
				counter = 0;
				for (Vertex vertex : points) {
					//System.out.println(vertex.getX1() + " " + vertex.getY1());
					if (vertex.getX1() <= clickX && vertex.getX1()+20 >= clickX && vertex.getY1() <= clickY && vertex.getY1()+20 >= clickY) {
						for (Edge edge : edges) {
							if (((edge.getV1ID() == vertex.getId() && edge.getV2ID() == this.vertex.getId()) || (edge.getV2ID() == vertex.getId() && edge.getV1ID() == this.vertex.getId())) &&
									(!edge.isVisited())) {
								counter++;
								this.edge.setX2(vertex.getX1()+10);
								this.edge.setY2(vertex.getY1()+10);
								this.edge.setV2ID(vertex.getId());
								edge.setVisited(true);
								vertex.setColor(new Color(0,255,0));
							}
						}
						if (counter > 0) {
							obrazek.pridej(vertex);	
							vertices.add(vertex);
						}
					} 
				}	
				if(counter == 0) {
					obrazek.odeber(edge);
					edges.remove(edge);
					this.vertex.setColor(new Color(0,255,0));
				}
				platno.repaint();
			}

			int vis=0;
			for (int i = 0; i < edges.size(); i++) {
				if (edges.get(i).isVisited() && edges.get(i).getMode() == 2) {
					vis++;
				}
			}
			//System.out.println(vis + " " + (hamilton.getNumberOfEdges()-hamilton.getNumberOfDashedEdges()) + " WWWWWWW");
			if (vis == (hamilton.getNumberOfEdges()-hamilton.getNumberOfDashedEdges())) {
		        JOptionPane.showMessageDialog(
		        	    null, 
		        	    "Správnì dokonèená hamiltonovská cesta! \n\n"
		        	    + "Postupujete do dalšího levelu.", 
		        	    "Hotovo",
		        	    JOptionPane.INFORMATION_MESSAGE, 
		        	    null); 
				obrazek = new Image();
				platno.setObrazek(obrazek);
		        hamilton.nextLevel(obrazek);
			}
		}else {
			if (tvar != 0) {	
				//System.out.println(vertices.size() + "+++++++++");
				for (int i = 0; i < vertices.size(); i++) {
					Vertex vertex = vertices.get(i);
					if (vertex.getX1() <= clickX && vertex.getX1()+99 >= clickX && vertex.getY1() <= clickY && vertex.getY1()+99 >= clickY) {
						if (vertex.isEnable()) {
							this.vertex.setX1(vertex.getX1()+35);
							this.vertex.setY1(vertex.getY1()+38);
							this.vertex.setRow(vertex.getRow());
							this.vertex.setCollumn(vertex.getCollumn());
							
							if (!isCoverGame) {
								edge.setX2(vertex.getX1()+50);
								edge.setY2(vertex.getY1()+50);
								
								Vertex point = new Vertex(vertex.getX1()+35, vertex.getY1()+35, 30, 30, 1);
								point.setRow(vertex.getRow());
								point.setCollumn(vertex.getCollumn());
								points.add(point);
								obrazek.pridej(point);
							}

							
							kone.remove(getKone(idKun));
							this.vertex.setId(i);
							kone.add(this.vertex);
							obrazek.odeber(this.vertex);
							obrazek.pridej(this.vertex);
							if (!isCoverGame) {
								vertex.setEnable(false);
								settings.setAvailableVertices(vertices, i, vertex.getRow(), vertex.getCollumn());
							}else {
								vertex.setEnable(false);
								vertices.get(saveId).setEnable(true);
								coverGame.setAvailableVertices(vertices, kone, vertex.getRow(),vertex.getCollumn());
							}
						}else {
							this.vertex.setX1(saveX);
							this.vertex.setY1(saveY);
							
							obrazek.odeber(this.vertex);
							obrazek.pridej(this.vertex);
							if (!isCoverGame) {
								obrazek.odeber(edge);
								edges.remove(edge);
							}
						}
					}else {
						counter++;
					}
				}
			}

			//System.out.println(vertex.getX1() + " " + vertex.getY1() + " " + vertex.isEnable());
			if (counter == vertices.size()) {
				this.vertex.setX1(saveX);
				this.vertex.setY1(saveY);
				obrazek.odeber(this.vertex);
				obrazek.pridej(this.vertex);
				if (!isCoverGame) {
					obrazek.odeber(edge);
					edges.remove(edge);
				}
			}
			counter = 0;
			platno.repaint();
			
			if (points.size() == (chesssize)*(chesssize) && !isCoverGame) {
		        JOptionPane.showMessageDialog(
		        	    null, 
		        	    "Správnì dokonèená hamiltonovská cesta! :)", 
		        	    "Hotovo",
		        	    JOptionPane.INFORMATION_MESSAGE, 
		        	    null); 
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void mouseMoved(MouseEvent e) {

		if (isHamiltonGame) {
			//System.out.println(edges.size() + " " + hamilton.getNumberOfEdges() + " LLLLLLLLLLLLL");
			if (edges.size() == hamilton.getNumberOfEdges()) {
				for (Vertex vertex : points) {
					if (vertex.getX1() < e.getX() && vertex.getX1()+20 > e.getX() && e.getY() > vertex.getY1() && e.getY() < vertex.getY1()+20) {
						vertex.setColor(new Color(0,255,0));
					}else {
						vertex.setColor(new Color(0,0,0));
					}
				}
			}
		}else if(mode == 1){
			for (Vertex vertex : settings.getSteps()) {
				if (vertex.isEnable()) {
					if (vertex.getX1() < e.getX() && vertex.getX1()+100 > e.getX() && e.getY() > vertex.getY1() && e.getY() < vertex.getY1()+100) {
						settings.setColor(vertex, true);
					}else {
						settings.setColor(vertex, false);
					}
				}
			}
		}
		platno.repaint();
	}
	
	public Vertex getKone(int id) {
		for (Vertex vertex : kone) {
			if (vertex.getId() == id) {
				return vertex;
			}
		}
		return null;
	}
	
	public void setTvar(int tvar) {
		this.tvar = tvar;
	}
	
	public void load() {
		filer.load(this);
	}

	public void save() {
		filer.save(obrazek, vertices, edges);
	}

	public void setLoadedData(Image obrazek2, List<Vertex> vertices2, List<Edge> edges2) {
		this.obrazek=obrazek2;
		this.vertices=vertices2;
		this.edges=edges2;
		platno.setObrazek(obrazek);
	}
	
	public void setKone(List<Vertex> kone) {
		this.kone = kone;
	}

	public void setObrazek(Image obrazek) {
		this.obrazek=obrazek;
	}

	public void setPoints(List<Vertex> points) {
		this.points=points;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices=vertices;
	}

	public Image getObrazek() {
		return this.obrazek;
	}

	public List<Vertex> getVertices() {
		return this.vertices;
	}
	public void setEdges(List<Edge> edges) {
		this.edges=edges;
	}

	public void switchGame(int size) {
		this.chesssize=size;
        gui.enabledMenu(true);
		obrazek = new Image();
		platno.setObrazek(obrazek);
		vertices.clear();
		edges.clear();
		points.clear();
		kone.clear();
		help.setVisible(false);
		kal = 0;
		switch (mode) {
		case 1:
			help.setVisible(true);
			help.setText(1);
			isCoverGame = false;
			isHamiltonGame = false;
			settings.setBoard(kone, vertices, points, obrazek, this, chesssize);
			settings.setAvailableVertices(vertices, 0, 1, 1);
			break;
		case 2:
			help.setVisible(true);
			help.limitHorses(size);
			help.setText(2);
			isCoverGame = true;
			isHamiltonGame = false;
			coverGame.setBoard(kone, vertices, obrazek, this, size);
			coverGame.setAvailableVertices(vertices, kone, 1, 1);
			break;
		case 3:
			isCoverGame = true;
			isHamiltonGame = false;
			simulation.getPath().clear();
			simulation.setBoard(kone, vertices,edges, obrazek, this, size);
			while(true) {
				if (simulation.getPath().size() != (size)*(size)) {
					if (simulation.searchHam(vertices, this)) {
						this.vertex = simulation.moveHorse(this);
						this.edge = simulation.makeRoad();
						edges.add(edge);
						//obrazek.pridej(edge);
						//orazek.pridej(vertex);
					}else {
						if (kal == 1) {
							break;
						}else {
							this.vertex = simulation.moveHorse(this);
							//obrazek.odeber(edges.get(edges.size()-1));
							if (edges.size() > 0) {
								edges.remove(edges.get(edges.size()-1));
							}
							//obrazek.pridej(vertex);
						}
					}
					//mainRepaint();
				}else {
					break;
				}
				
			}
			
			if (simulation.getPath().size() != 0) {
				Vertex v = simulation.getPath().get(0);
				Vertex v1 = null;
				for (int j = 1; j<simulation.getPath().size(); j++) {
					v1 = simulation.getPath().get(j);
					this.edge = new Edge(v.getX1()+50, v.getY1()+50, v1.getX1()+50, v1.getY1()+50, 1);
					edges.add(edge);
					obrazek.pridej(edge);
					v = v1;
				}
			}else {
			    JOptionPane.showMessageDialog(this, "Nenalezeny žádné hamiltonovské cesty", "Nic nenalezeno", JOptionPane.ERROR_MESSAGE);
			}

			platno.repaint();
			break;
		case 5:
			help.setVisible(true);
			help.setText(0);
			isHamiltonGame = true;
			isCoverGame = false;
			hamilton = new Hamilton(obrazek, edges, points, this);
			platno.repaint();
			break;
		default:
			break;
		}

		jMenuBar = gui.changeBottomMenu(mode, jMenuBar, this);

        getContentPane().revalidate();
        getContentPane().repaint();
		platno.repaint(platno.getGraphics());
	}
	
	public void pridatKone() {
		if (chesssize > kone.size()) {
			coverGame.pridatKone(this, obrazek, kone);
			coverGame.setAvailableVertices(vertices, kone, kone.get(kone.size()-1).getRow(), kone.get(kone.size()-1).getCollumn());
			platno.repaint();
		}else {
		    JOptionPane.showMessageDialog(this, "Dosáhli jste limitu poskytnutých koní.", "Další už ne.", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void mainRepaint() {
		platno.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
		platno.repaint(platno.getGraphics());
	}

	public void krokZpet() {
		if (points.size()>1) {
			Vertex kun = null;
			for (Vertex vertex : kone) {
				//System.out.println(vertex.getRow() + " " + vertex.getCollumn() + " XXXXXXXXXXXXXXXX");
				for (int i = 0; i < vertices.size(); i++) {
					if (vertices.get(i).getRow() == vertex.getRow() && vertices.get(i).getCollumn() == vertex.getCollumn()) {
						vertices.get(i).setEnable(true);
						vertices.get(i).setNavstiveno(false);
					}
				}
				vertex.setX1(points.get(points.size()-2).getX1());
				vertex.setY1(points.get(points.size()-2).getY1());
				vertex.setRow(points.get(points.size()-2).getRow());
				vertex.setCollumn(points.get(points.size()-2).getCollumn());
				kun = vertex;
			}
			//System.out.println(getKone(idKun).getRow() + " !!!!!!!!!!!!!!!! " + getKone(idKun).getCollumn());
			
			obrazek.odeber(points.get(points.size()-1));
			obrazek.odeber(edges.get(edges.size()-1));
			
			edges.remove(edges.get(edges.size()-1));
			points.remove(points.get(points.size()-1));
			
			for (int j = 0; j < vertices.size(); j++) {
				if (vertices.get(j).getRow() == kun.getRow() && vertices.get(j).getCollumn() == kun.getCollumn()) {
					settings.setAvailableVertices(vertices, j, vertices.get(j).getRow(),vertices.get(j).getCollumn());
				}
			}
			platno.repaint();
		}
	}

	public void hranaZpet() {
		if (edges.size() > hamilton.getNumberOfEdges()) {
			if (vertices.size() == 2) {
				for (Vertex vertex : points) {
					vertex.setColor(new Color(0,0,0));
				}
				vertices.clear();
				for (Edge edge : edges) {
					if ((edge.getV1ID() == edges.get(edges.size()-1).getV1ID() && edge.getV2ID() == edges.get(edges.size()-1).getV2ID()) 
							|| (edge.getV1ID() == edges.get(edges.size()-1).getV2ID() && edge.getV2ID() == edges.get(edges.size()-1).getV1ID())) {
						edge.setVisited(false);
					}
				}
				obrazek.odeber(edges.get(edges.size()-1));
				edges.remove(edges.get(edges.size()-1));
			}else if (vertices.size() > 2) {
				/*
				for (Vertex vertex : vertices) {
					System.out.println(vertices.size() + " OOOOOO " + vertex.getId() + " " + vertex.getColor());
				}
				*/
				for (Edge edge : edges) {
					if ((edge.getV1ID() == vertices.get(vertices.size()-1).getId() || edge.getV2ID() == vertices.get(vertices.size()-1).getId()) && !edge.isVisited() && edge.getMode() == 2) {
						vertices.get(vertices.size()-1).setColor(new Color(0,0,0));
						break;
					}else {
						vertices.get(vertices.size()-1).setColor(new Color(255,0,0));
					}
				}
				vertices.remove(vertices.size()-1);
				vertices.get(vertices.size()-1).setColor(new Color(0,255,0));

				for (Edge edge : edges) {
					if ((edge.getV1ID() == edges.get(edges.size()-1).getV1ID() && edge.getV2ID() == edges.get(edges.size()-1).getV2ID()) 
							|| (edge.getV1ID() == edges.get(edges.size()-1).getV2ID() && edge.getV2ID() == edges.get(edges.size()-1).getV1ID())) {
						edge.setVisited(false);
					}
				}
				obrazek.odeber(edges.get(edges.size()-1));
				edges.remove(edges.get(edges.size()-1));
			}
			platno.repaint();
		}
	}
	
	public void endGame() {
		obrazek = new Image();
		platno.setObrazek(obrazek);
		vertices.clear();
		edges.clear();
		points.clear();
		kone.clear();
		platno.repaint();
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}

	public void breakLoop() {
		this.kal = 1;
	}

	/*
	public void setTest() {
        gui.enabledMenu(true);
		obrazek = new Image();
		platno.setObrazek(obrazek);
		vertices.clear();
		edges.clear();
		points.clear();
		kone.clear();
		kal = 0;
        getContentPane().revalidate();
        getContentPane().repaint();
		platno.repaint(platno.getGraphics());
	}
	public void drawTest(KnightPoint point, KnightPoint point2) {
		this.edge = new Edge(point.getX()*100 + 15, point.getY()*100 + 15, point2.getX()*100 + 15, point2.getY()*100 + 15, 1);
		drawTestPoints(point.getX(), point.getY());
		drawTestPoints(point2.getX(), point2.getY());
		edges.add(edge);
		obrazek.pridej(edge);
	}
	
	public void repaintTest() {
		platno.repaint();
	}

	*/
	
	public void drawTestPoints(int j, int u) {
		Vertex point = new Vertex(j, u, 30, 30, 1);
		points.add(point);
		obrazek.pridej(point);
	}
	
	public void drawTest(int pointX, int pointY, int point2X, int point2Y, int size) {
		int pomX1 = 0, pomX2 = 0, pomY1 = 0, pomY2 = 0;
		//System.out.println(pointX + " " +pointY + " " + point2X + " " +point2Y + " " +size);
		for (Vertex v : vertices) {
			if ((v.getRow()-1)*size + (v.getCollumn()-1) == pointX*size + pointY) {
				//System.out.println((v.getRow()-1)*size + " " + (v.getCollumn()-1) + " " + pointX*size + " " + pointY);
				pomX1 = v.getX1() + 50;
				pomY1 = v.getY1() + 50;
				continue;
			}
			if ((v.getRow()-1)*size + (v.getCollumn()-1) == point2X*size + point2Y) {
				//System.out.println((v.getRow()-1)*size + " " + (v.getCollumn()-1) + " " + point2X*size + " " + point2Y);
				pomX2 = v.getX1() + 50;
				pomY2 = v.getY1() + 50;
				continue;
			}
		}
		//System.out.println("++++++++++++++++++++++++++++++");
		//System.out.println(pomX1 + " " +pomY1 + " " + pomX2 + " " +pomY2);
		this.edge = new Edge(pomX1, pomY1, pomX2, pomY2, 1);
		drawTestPoints(pomX1-15, pomY1-15);
		drawTestPoints(pomX2-15, pomY2-15);
		edges.add(edge);
		obrazek.pridej(edge);
		platno.repaint();
	}

	public void clear() {
        gui.enabledMenu(true);
		obrazek = new Image();
		platno.setObrazek(obrazek);
		vertices.clear();
		edges.clear();
		points.clear();
		kone.clear();
		kal = 0;
        getContentPane().revalidate();
        getContentPane().repaint();
		platno.repaint(platno.getGraphics());
	}

	public void hideHelp() {
		help.setVisible(false);
	}
}
