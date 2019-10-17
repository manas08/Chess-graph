package cz.uhk.diplom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import cz.uhk.diplom.model.GraphicsObject;
import cz.uhk.diplom.model.Vertex;
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
	private int test = 0;
	private JMenuBar jMenuBar;
	private boolean isCoverGame = false;
	private int idKun;
	int pocet;
	
	public MainWindow() {
		setTitle("Grafika");
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
        
		
		//add(new JButton("Hi!"));
		//obrazek.pridej(new Obdelnik(0, 0, 20, 30));	
		platno.addMouseListener(this);
		platno.addMouseMotionListener(this);
		getContentPane().setBackground(Color.GRAY); 
		
		settings = new Settings();
		settings.setBoard(kone, vertices, points, obrazek, this, chesssize);
		settings.setAvailableVertices(vertices, 0, 1);

		//vykresleni platna
		platno.repaint();
	}

	public static void main(String[] args) {
		MainWindow okno = new MainWindow();
		okno.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (test != 0) {
			if (!isCoverGame) {
				edge.setX2(e.getX()+16);
				edge.setY2(e.getY()+14);
			}
			vertex.setX1(e.getX());
			vertex.setY1(e.getY());
			platno.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clickX = e.getX();
		clickY = e.getY();
		tvar = 0;
		test = 0;

		for (Vertex vertex : kone) {
			//System.out.println(vertex.getX1() + " " + vertex.getY1());
			if (vertex.getX1() <= clickX && vertex.getX1()+50 >= clickX && vertex.getY1() <= clickY && vertex.getY1()+77 >= clickY) {
				this.vertex = vertex;
				
				saveX = vertex.getX1();
				saveY = vertex.getY1();
				
				if (!isCoverGame) {
					edge = new Edge(vertex.getX1()+16, vertex.getY1()+14, vertex.getX1()+16, vertex.getY1()+14);
					edges.add(edge);
					obrazek.pridej(edge);
				}
				idKun = vertex.getId();
				platno.repaint();
				tvar++;
				test++;
			} 
		}		
		//System.out.println("=============================================");


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clickX = e.getX();
		clickY = e.getY();
		
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
							edges.add(edge);
							obrazek.pridej(edge);
							
							Vertex point = new Vertex(vertex.getX1()+35, vertex.getY1()+35, 30, 30);
							point.setRow(vertex.getRow());
							point.setCollumn(vertex.getCollumn());
							points.add(point);
							obrazek.pridej(point);
						}

						kone.remove(getKone(idKun));
						this.vertex.setId(i);
						kone.add(this.vertex);
						obrazek.pridej(this.vertex);
						platno.repaint();
						
						if (!isCoverGame) {
							vertex.setEnable(false);
							settings.setAvailableVertices(vertices, i, vertex.getRow());
						}else {
							coverGame.setAvailableVertices(vertices, kone, vertex.getRow());
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
						
						platno.repaint();
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
			kone.add(this.vertex);
			obrazek.pridej(this.vertex);
			if (!isCoverGame) {
				obrazek.odeber(edge);
				edges.remove(edge);
			}
			platno.repaint();
		}
		counter = 0;
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
		// TODO Auto-generated method stub
		
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
		settings.setAvailableVertices(vertices, 0, 1);
		platno.repaint(platno.getGraphics());
	}

	public void switchGame(int i, int size) {
        
		obrazek = new Image();
		platno.setObrazek(obrazek);
		vertices.clear();
		edges.clear();
		points.clear();
		kone.clear();
		
		switch (i) {
		case 0:
			isCoverGame = false;
			setSize(4);
			break;
		case 1:
			isCoverGame = true;
			coverGame = new CoverGame(obrazek);
			coverGame.setBoard(kone, vertices, obrazek, this, size);
			coverGame.setAvailableVertices(vertices, kone, 1);
			break;
		case 2:
			isCoverGame = true;
			simulation = new Simulation(obrazek);
			simulation.setBoard(kone, vertices,edges, obrazek, this, size);
			//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!3");
			pocet = 0;
			while(simulation.getPolicko() < (size+2)*(size+2)) {
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
						for (Vertex v : simulation.getPath()) {
							//System.out.println(v.getId());
						}
						//System.out.println();
						//System.out.println("---------------------------------------");
						//System.out.println();
						//System.out.println(pocet + " pocet " + size);
						pocet++;
						if (simulation.getPolicko() < (size+2)*(size+2)) {
							this.vertex = simulation.nextPath();
							//obrazek.odeber(edges.get(edges.size()-1));
							edges.remove(edges.get(edges.size()-1));
							//obrazek.pridej(vertex);
						}
				}
				
			}
			Vertex v = simulation.getPath().get(0);
			Vertex v1 = null;
			for (int j = 1; j<simulation.getPath().size(); j++) {
				v1 = simulation.getPath().get(j);
				this.edge = new Edge(v.getX1()+50, v.getY1()+50, v1.getX1()+50, v1.getY1()+50);
				edges.add(edge);
				obrazek.pridej(edge);
				v = v1;
			}
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
		coverGame.setAvailableVertices(vertices, kone, 1);
		platno.repaint();
	}
	
	public void mainRepaint() {
		platno.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
		platno.repaint(platno.getGraphics());
	}
	
	public int getPocet() {
		return pocet;
	}
}
