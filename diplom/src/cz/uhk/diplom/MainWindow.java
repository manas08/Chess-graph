package cz.uhk.diplom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import cz.uhk.diplom.model.GraphicsObject;
import cz.uhk.diplom.model.Vertex;
import cz.uhk.diplom.prochazka.KnightsTour;
import cz.uhk.diplom.utils.Filer;
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
	private int chesssize = 4;
	private GraphicsObject o;
	private int tvar=0, counter = 0;
	private Vertex vertex;
	private Edge edge;
	private Settings settings;
	private CoverGame coverGame;
	private Simulation simulation;
	private Hamilton hamilton;
	private int test = 0;
	private JMenuBar jMenuBar;
	private boolean isCoverGame = false, isHamiltonGame = false;
	private int idKun;
	private Image icon;
	
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
		
		settings = new Settings();
		coverGame = new CoverGame();
		simulation = new Simulation();
		//settings.setBoard(kone, vertices, points, obrazek, this, chesssize);
		//settings.setAvailableVertices(vertices, 0, 1, 1);

		//vykresleni platna
		//platno.repaint();
	}

	public static void main(String[] args) {
		MainWindow okno = new MainWindow();
		okno.setVisible(true);
		MainMenu mainMenu = new MainMenu(okno);
		mainMenu.setVisible(true);
		mainMenu.setAlwaysOnTop (true);
		mainMenu.setFocusableWindowState(false);		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
			//System.out.println(vis + " " + (hamilton.getNumberOfEdges()-hamilton.getNumberOfDashedEdges()) + " PPPPP");
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
							obrazek.pridej(this.vertex);
							
							if (!isCoverGame) {
								vertex.setEnable(false);
								settings.setAvailableVertices(vertices, i, vertex.getRow(), vertex.getCollumn());
							}else {
								coverGame.setAvailableVertices(vertices, kone, vertex.getRow(),vertex.getCollumn());
							}
						}else {
							this.vertex.setX1(saveX);
							this.vertex.setY1(saveY);
							kone.remove(getKone(idKun));
							kone.add(this.vertex);
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
				kone.remove(getKone(idKun));
				kone.add(this.vertex);
				obrazek.pridej(this.vertex);
				if (!isCoverGame) {
					obrazek.odeber(edge);
					edges.remove(edge);
				}
			}
			counter = 0;
			platno.repaint();
			
			if (points.size() == (chesssize+2)*(chesssize+2) && !isCoverGame) {
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
			if (edges.size() == hamilton.getNumberOfEdges()) {
				for (Vertex vertex : points) {
					if (vertex.getX1() < e.getX() && vertex.getX1()+20 > e.getX() && e.getY() > vertex.getY1() && e.getY() < vertex.getY1()+20) {
						vertex.setColor(new Color(0,255,0));
						//System.out.println(vertex.getX1() + " " + vertex.getY1() + " " + e.getX() + " " + e.getY() + " " + vertex.getId());
					}else {
						vertex.setColor(new Color(0,0,0));
					}
				}
				platno.repaint();
			}
		}
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

	public void setEdges(List<Edge> edges) {
		this.edges=edges;
	}
	
	public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.BLACK);
    }
	
	public void setSize(int i) {
		this.chesssize=i;
		obrazek = new Image();
		platno.setObrazek(obrazek);
		
		vertices.clear();
		edges.clear();
		points.clear();
		kone.clear();

		settings.setBoard(kone, vertices, points, obrazek, this, chesssize);
		settings.setAvailableVertices(vertices, 0, 1, 1);
		platno.repaint(platno.getGraphics());
	}

	public void switchGame(int i, int size) {
        gui.enabledMenu(true);
		obrazek = new Image();
		platno.setObrazek(obrazek);
		vertices.clear();
		edges.clear();
		points.clear();
		kone.clear();
		
		switch (i) {
		case 0:
			isCoverGame = false;
			isHamiltonGame = false;
			setSize(size);
			break;
		case 1:
			isCoverGame = true;
			isHamiltonGame = false;
			coverGame.setBoard(kone, vertices, obrazek, this, size);
			coverGame.setAvailableVertices(vertices, kone, 1, 1);
			break;
		case 2:
			isCoverGame = true;
			isHamiltonGame = false;
			simulation.setBoard(kone, vertices,edges, obrazek, this, size);
			
			while(true) {
				if (simulation.getPath().size() != (size+2)*(size+2)) {
					if (simulation.searchHam(vertices, this)) {
						this.vertex = simulation.moveHorse(this);
						this.edge = simulation.makeRoad();
						edges.add(edge);
						//obrazek.pridej(edge);
						//obrazek.pridej(vertex);
					}else {
						this.vertex = simulation.moveHorse(this);
						//obrazek.odeber(edges.get(edges.size()-1));
						if (edges.size() > 0) {
							edges.remove(edges.get(edges.size()-1));
						}
						//obrazek.pridej(vertex);
					}
					//mainRepaint();
				}else {	//if(simulation.getPath().get(0) == simulation.getPath().get(simulation.getPath().size()-1) && simulation.getPath().size() == (size+2)*(size+2)+1)
						//System.out.println();
						//System.out.println("---------------------------------------");
						//System.out.println();
						//System.out.println(pocet + " pocet " + size);
					/*	
					if (simulation.getPolicko() < (size+2)*(size+2)) {
							this.vertex = simulation.nextPath();
							obrazek.odeber(edges.get(edges.size()-1));
							edges.remove(edges.get(edges.size()-1));
							obrazek.pridej(vertex);
						}*/
						break;
				}
				
			}
			
			Vertex v = simulation.getPath().get(0);
			Vertex v1 = null;
			for (int j = 1; j<simulation.getPath().size(); j++) {
				v1 = simulation.getPath().get(j);
				this.edge = new Edge(v.getX1()+50, v.getY1()+50, v1.getX1()+50, v1.getY1()+50, 1);
				edges.add(edge);
				obrazek.pridej(edge);
				v = v1;
			}
			platno.repaint();
			break;
		case 3:
			isHamiltonGame = true;
			isCoverGame = false;
			hamilton = new Hamilton(obrazek, edges, points, this);
			platno.repaint();
			break;
		default:
			break;
		}

		jMenuBar = gui.changeBottomMenu(i, jMenuBar, this);

        getContentPane().revalidate();
        getContentPane().repaint();
		platno.repaint(platno.getGraphics());
	}
	
	public void pridatKone() {
		coverGame.pridatKone(this, obrazek, kone);
		coverGame.setAvailableVertices(vertices, kone, 1, 1);
		platno.repaint();
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
				edges.clear();
				//System.out.println("!!!!!!!!!!!!!!!!!!!!!");
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

				//System.out.println(vertices.size() + " OOOOOO " +vertices.get(vertices.size()-1).getColor() + " " + vertices.get(vertices.size()-1).getId());
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
}
