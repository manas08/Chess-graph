package cz.uhk.diplom.prochazka;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import cz.uhk.diplom.MainWindow;
import cz.uhk.diplom.model.Image;
import cz.uhk.diplom.model.Vertex;
import cz.uhk.diplom.tangible.RandomNumbers;
import cz.uhk.diplom.utils.Link;

/**
 * 
 * @author manas08
 *
 *         Algoritm based on Hopfield's neural network for searching Knight's
 *         tours in chessboard
 *
 */

public class NeuralNetworkTour {

	int CSIZE;
	int DSIZE;
	int NSIZE;
	boolean hamiltonian;
	int number;
	int totalTrials = 0;
	int XSIZE = 80;
	int YSIZE = 80;
	int numberOfBoards;

	int[][] U;
	int[][] V;
	int[][] D;
	int[][] A;

	List<Link> links;
	List<Integer[]> path;

	BufferedImage img3 = null;
	BufferedImage img4 = null;
	BufferedImage img5 = null;
	BufferedImage img6 = null;
	BufferedImage img7 = null;
	BufferedImage img8 = null;

	MainWindow main;

	public NeuralNetworkTour() {
		try {
			img3 = ImageIO.read(getClass().getResourceAsStream("/textures/brick.jpg"));
			img4 = ImageIO.read(getClass().getResourceAsStream("/textures/brick2.jpg"));
			img5 = ImageIO.read(getClass().getResourceAsStream("/textures/smallbricks.jpg"));
			img6 = ImageIO.read(getClass().getResourceAsStream("/textures/smallbricks2.jpg"));
			img7 = ImageIO.read(getClass().getResourceAsStream("/textures/verysmallbricks.jpg"));
			img8 = ImageIO.read(getClass().getResourceAsStream("/textures/verysmallbricks2.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cmdGoClick(MainWindow main, int x, int y) {
		this.CSIZE = x;
		this.DSIZE = y;
		this.NSIZE = CSIZE * DSIZE;
		this.main = main;
		int numHamiltonian = 0;
		boolean stopped = false;

		U = new int[NSIZE][NSIZE];
		V = new int[NSIZE][NSIZE];
		D = new int[NSIZE][NSIZE];
		A = new int[NSIZE][NSIZE];
		links = new ArrayList<>();

		PrintStream originalStdout = System.out;
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream("solutions.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(out);

		System.out.println(
				"Tento soubor obsahuje " + numberOfBoards + " vygenerovaných øešení Hopfieldovou neuronovou sítí.");
		System.out.println("Øešení jsou vypsána v podobì èísel ve tvaru velikosti šachovnice " + x + "x" + y + ".");
		System.out.println("Každé èíslo reprezentuje krok jezdcovy procházky.");
		System.out.println("Èíslo 0 je políèko, kde jezdec zaèíná");
		System.out.println("Èíslo 1 je políèko, kam se jezdec pøesunul z kroku 0 atd...");
		System.out.println();

		do {

			Initialize();

			int n;
			int t = 0;
			int diag = 1;
			int dU;
			int k;
			int sum_row;
			int sum_col;
			int[] U_;
			int[] V_;
			int[] D_;

			while (diag > 0) {
				diag = 0;
				n = 1;

				for (int i = 0; i < NSIZE; i++) {
					U_ = U[i];
					V_ = V[i];
					D_ = D[i];
					for (int j = n; j < NSIZE; j++) {

						if (D_[j] == 1) {
							sum_row = 0;
							sum_col = 0;
							for (k = 0; k < NSIZE; k++) {
								if (D_[k] != 0) {
									sum_row += V_[k];
								}
								if (D[k][j] == 1) {
									sum_col += V[k][j];
								}
							}
							dU = -(sum_row - 2) - (sum_col - 2);
						} else {
							dU = 0;
						}

						U_[j] += dU;

						if (U_[j] > 10) {
							U_[j] = 10;
						}
						if (U_[j] < -10) {
							U_[j] = -10;
						}
						if (U_[j] > 3) {
							V_[j] = 1;
						}
						if (U_[j] < 0) {
							V_[j] = 0;
						}

						if (V_[j] == 1) {
							V[j][i] = 1;
						}
						if (V_[j] == 0) {
							V[j][i] = 0;
						}

						diag += (dU == 0 ? 0 : 1);
					}
					n++;
				}

				if (t > 1000) {
					break;
				}
				t++;

			}

			if (stopped) {
				break;
			}

			if (t > 1000) {
				hamiltonian = false;
			} else if (CheckHamiltonian()) {
				numHamiltonian++;
				hamiltonian = true;
				DrawNeurons();
			} else {
				hamiltonian = false;
			}

			// totalTrials < targetTrials
		} while (numHamiltonian < numberOfBoards);

		System.setOut(originalStdout);
		stopped = true;
	}

	public boolean CheckHamiltonian() {
		int p = 1;
		int linkNum = 0;
		int linkCount;
		for (int m = 0; m < CSIZE; m++) {
			for (int n = p; n < DSIZE; n++) {
				if (V[m][n] == 1) {
					Link l = links.get(linkNum++);
					l.i = m;
					l.j = n;
					l.visited = false;
				}
			}
			p++;
		}

		linkCount = linkNum;
		linkNum = 0;
		int numTraversed = 0;
		Link l = links.get(linkNum);
		int startPt = l.i;
		int nextPt = l.j;
		boolean found;
		while (true) {
			l.visited = true;
			found = false;
			for (int i = 0; i < linkCount; i++) {
				Link l2 = links.get(i);
				if (l2.visited) {
					continue;
				}
				if (l2.i == nextPt) {
					l = l2;
					nextPt = l2.j;
					found = true;
					numTraversed++;
					break;
				}
				if (l2.j == nextPt) {
					l = l2;
					nextPt = l2.i;
					found = true;
					numTraversed++;
					break;
				}
			}
			if (!found) {
				break;
			}
			if ((nextPt == startPt) || (numTraversed >= (linkCount - 1))) {
				break;
			}
		}
		if ((nextPt == startPt) && (numTraversed >= (linkCount - 1))) {
			if (oneComponent()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public void Initialize() {
		int n;
		int m;
		int count;

		// clear old arrays
		U = new int[NSIZE][NSIZE];
		V = new int[NSIZE][NSIZE];
		D = new int[NSIZE][NSIZE];
		A = new int[NSIZE][NSIZE];
		links = new ArrayList<>();

		int numLinks;
		if (CSIZE > DSIZE) {
			numLinks = 4 * (CSIZE - 2) * (CSIZE - 1) + 1;
		} else {
			numLinks = 4 * (DSIZE - 2) * (DSIZE - 1) + 1;
		}
		for (int i = 0; i < numLinks; i++) {
			Link l = new Link();
			links.add(l);
		}

		for (m = 0; m < NSIZE; m++) {
			for (n = 0; n < NSIZE; n++) {
				U[m][n] = -(int) (RandomNumbers.nextNumber() % CSIZE);
				V[m][n] = 0;
			}
		}
		for (m = 0; m < NSIZE; m++) {
			for (n = 0; n < NSIZE; n++) {
				D[m][n] = 0;
			}
		}

		// directions
		for (int i = 0; i < NSIZE; i++) {
			if (i + 1 < CSIZE * (i / CSIZE + 1) && i + 1 - (2 * CSIZE) >= 0) {
				D[i][i + 1 - (2 * CSIZE)] = 1;
			}
			if (i + 2 < CSIZE * (i / CSIZE + 1) && i + 2 - CSIZE >= 0) {
				D[i][i + 2 - CSIZE] = 1;
			}
			if (i + 2 < CSIZE * (i / CSIZE + 1) && i + 2 + CSIZE < NSIZE) {
				D[i][i + 2 + CSIZE] = 1;
			}
			if (i + 1 < CSIZE * (i / CSIZE + 1) && i + 1 + (2 * CSIZE) < NSIZE) {
				D[i][i + 1 + (2 * CSIZE)] = 1;
			}
			if (i - 1 >= CSIZE * (i / CSIZE) && i - 1 + (2 * CSIZE) < NSIZE) {
				D[i][i - 1 + (2 * CSIZE)] = 1;
			}
			if (i - 2 >= CSIZE * (i / CSIZE) && i - 2 + CSIZE < NSIZE) {
				D[i][i - 2 + CSIZE] = 1;
			}
			if (i - 2 >= CSIZE * (i / CSIZE) && i - 2 - CSIZE >= 0) {
				D[i][i - 2 - CSIZE] = 1;
			}
			if (i - 1 >= CSIZE * (i / CSIZE) && i - 1 - (2 * CSIZE) >= 0) {
				D[i][i - 1 - (2 * CSIZE)] = 1;
			}
		}

		count = -1;
		for (int i = 0; i < NSIZE; i++) {
			if (i % CSIZE == 0) {
				count++;
				A[i][0] = XSIZE + XSIZE / 2;
				A[i][1] = YSIZE + YSIZE / 2 + YSIZE * count;
			} else {
				A[i][0] = XSIZE + XSIZE / 2 + XSIZE * (i % CSIZE);
				A[i][1] = YSIZE + YSIZE / 2 + YSIZE * count;
			}
		}
	}

	public void DrawNeurons() {

		int p = 1;

		List<Integer> points = new ArrayList<>();
		drawBoard();

		for (int m = 0; m < NSIZE; m++) {
			for (int n = p; n < NSIZE; n++) {
				if (V[m][n] == 1) {

					int x1 = A[m][0] - 120;
					int y1 = A[m][1] - 120;
					int x2 = A[n][0] - 120;
					int y2 = A[n][1] - 120;

					points.add(x1 / 80);
					points.add(y1 / 80);
					points.add(x2 / 80);
					points.add(y2 / 80);

					main.drawTest(x1 / 80, y1 / 80, x2 / 80, y2 / 80, CSIZE);
				}
			}
			p++;
		}

		Integer[] vertex = new Integer[3];
		vertex[0] = 0;
		vertex[1] = 0;
		vertex[2] = 0;
		path = new ArrayList<Integer[]>();
		path.add(vertex);
		int w = 0;
		p = 0;
		boolean b = false;
		totalTrials++;
		while (path.size() < NSIZE) {

			for (int i = 0; i < points.size(); i = i + 4) {

				if (path.size() == w) {
					main.clear();
					cmdGoClick(main, CSIZE, DSIZE);
					return;
				}

				if ((path.get(w)[0] == points.get(i)) && (path.get(w)[1] == points.get(i + 1))) {
					vertex = new Integer[3];
					vertex[0] = points.get(i + 2);
					vertex[1] = points.get(i + 3);
					for (Integer[] integer : path) {
						if (integer[0] == vertex[0] && integer[1] == vertex[1]) {
							b = true;
						}
					}
					if (!b) {
						vertex[2] = path.size();
						path.add(vertex);
						break;
					}
				} else if ((path.get(w)[0] == points.get(i + 2)) && (path.get(w)[1] == points.get(i + 3))) {
					vertex = new Integer[3];
					vertex[0] = points.get(i);
					vertex[1] = points.get(i + 1);
					for (Integer[] integer : path) {
						if (integer[0] == vertex[0] && integer[1] == vertex[1]) {
							b = true;
						}
					}
					if (!b) {
						vertex[2] = path.size();
						path.add(vertex);
						break;
					}
				}
				b = false;
			}
			w++;
		}

		number++;

		System.out.println(number + ". øešení (Neural network)");
		if (CSIZE == DSIZE) {
			for (int i = 0; i < CSIZE; i++) {
				for (int j = 0; j < DSIZE; j++) {
					for (Integer[] integer : path) {
						if (integer[0] == i && integer[1] == j) {
							System.out.print(integer[2] + " ");
						}
					}
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		} else {
			for (int i = 0; i < DSIZE; i++) {
				for (int j = 0; j < CSIZE; j++) {
					for (Integer[] integer : path) {
						if (integer[1] == i && integer[0] == j) {
							System.out.print(integer[2] + " ");
						}
					}
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}

	}

	private void drawBoard() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double pom1 = 0, pom2 = 0;
		if (CSIZE >= 10) {
			pom1 = ((width / 2) - CSIZE * 16.5) / 33.0;
			pom2 = ((height / 2) - ((DSIZE * 16.5) + 50.0)) / 33.0;
		} else {
			pom1 = ((width / 2) - CSIZE * 50) / 100.0;
			pom2 = ((height / 2) - ((DSIZE * 50) + 50)) / 100.0;
		}

		Image obrazek = this.main.getObrazek();
		List<Vertex> vertices = this.main.getVertices();
		Vertex vertex;

		boolean obr = false;
		int row = 1;
		for (double i = pom2; i < pom2 + DSIZE; i++) {
			int collumn = 1;
			if (obr) {
				obr = false;
			} else {
				obr = true;
			}

			for (double j = pom1; j < pom1 + CSIZE; j++) {
				if (obr) {
					if (CSIZE >= 10) {
						vertex = new Vertex((int) (j * 33), (int) (i * 33), img5, 2);
					} else if (CSIZE > 28) {
						vertex = new Vertex((int) (j * 15), (int) (i * 15), img7, 2);
					} else {
						vertex = new Vertex((int) (j * 100), (int) (i * 100), img3, 2);
					}
					if (CSIZE == DSIZE) {
						vertex.setCollumn(collumn);
						vertex.setRow(row);
					} else {
						vertex.setCollumn(row);
						vertex.setRow(collumn);
					}
					vertex.setWhite(false);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = false;
				} else {
					if (CSIZE >= 10) {
						vertex = new Vertex((int) (j * 33), (int) (i * 33), img6, 2);
					} else if (CSIZE > 28) {
						vertex = new Vertex((int) (j * 15), (int) (i * 15), img8, 2);
					} else {
						vertex = new Vertex((int) (j * 100), (int) (i * 100), img4, 2);
					}
					if (CSIZE == DSIZE) {
						vertex.setCollumn(collumn);
						vertex.setRow(row);
					} else {
						vertex.setCollumn(row);
						vertex.setRow(collumn);
					}
					vertex.setWhite(true);
					collumn++;
					vertices.add(vertex);
					obrazek.pridej(vertex);
					obr = true;
				}
			}
			row++;
		}
		main.setObrazek(obrazek);
		main.setVertices(vertices);
	}

	public boolean oneComponent() {

		List<Integer> points = new ArrayList<>();
		int p = 1;
		for (int m = 0; m < NSIZE; m++) {
			for (int n = p; n < NSIZE; n++) {
				if (V[m][n] == 1) {
					int x1 = A[m][0] - 120;
					int y1 = A[m][1] - 120;
					int x2 = A[n][0] - 120;
					int y2 = A[n][1] - 120;

					points.add(x1 / 80);
					points.add(y1 / 80);
					points.add(x2 / 80);
					points.add(y2 / 80);
				}
			}
			p++;
		}

		Integer[] vertex = new Integer[3];
		vertex[0] = 0;
		vertex[1] = 0;
		vertex[2] = 0;
		path = new ArrayList<Integer[]>();
		path.add(vertex);
		int w = 0;
		p = 0;
		boolean b = false;
		totalTrials++;
		while (path.size() < NSIZE) {

			for (int i = 0; i < points.size(); i = i + 4) {

				if (path.size() == w) {
					return false;
				}
				if ((path.get(w)[0] == points.get(i)) && (path.get(w)[1] == points.get(i + 1))) {
					vertex = new Integer[3];
					vertex[0] = points.get(i + 2);
					vertex[1] = points.get(i + 3);
					for (Integer[] integer : path) {
						if (integer[0] == vertex[0] && integer[1] == vertex[1]) {
							b = true;
						}
					}
					if (!b) {
						vertex[2] = path.size();
						path.add(vertex);
						break;
					}
				} else if ((path.get(w)[0] == points.get(i + 2)) && (path.get(w)[1] == points.get(i + 3))) {
					vertex = new Integer[3];
					vertex[0] = points.get(i);
					vertex[1] = points.get(i + 1);
					for (Integer[] integer : path) {
						if (integer[0] == vertex[0] && integer[1] == vertex[1]) {
							b = true;
						}
					}
					if (!b) {
						vertex[2] = path.size();
						path.add(vertex);
						break;
					}
				}
				b = false;
			}
			w++;
		}
		return true;
	}

	public void setNumberOfBoards(int jml1) {
		this.numberOfBoards = jml1;
	}

	public String getNumberOfBoards() {
		return String.valueOf(numberOfBoards);
	}
}
