package cz.uhk.diplom.prochazka;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class KnightsTour {
	

	/**
	 * 
	 * @author manas08
	 *
	 * Backtracking algoritm
	 * for searching Knight's tours in chessboard
	 *
	 */
	
	

	/**
	 * Priznak nenavstivenosti policka
	 */
	private static int NOT_VISITED = -1;
	/**
	 * Velikost sachovnice na ose x
	 */
	private int xSize;
	/**
	 * Velikost sachovnice na ose y
	 */
	private int ySize;
	/**
	 * Pocet reseni
	 */
	private int solutionsCount;
	/**
	 * Pole pro reseni 0 -> pocatecni pozice kone 1 -> prvni tah 2 -> druhy tah
	 * . . . n -> n-ty tah
	 */
	private int[][] solutionBoard;

	private boolean podm = true, uzav = false;

	/**
	 * Konstruktor resitele jezdcovy prochazky
	 * 
	 * @param xSize
	 *            velikost sachovnice na ose x
	 * @param ySize
	 *            velikost sachovnice na ose y
	 * @param n2
	 *            typ hledane cesty
	 */

	/**
	 * Pro testov�n�
	 */
	// private long start;

	public KnightsTour(int xSize, int ySize, String n2) {
		this.xSize = xSize;
		this.ySize = ySize;
		if (n2 == "uzav�en� cesty") {
			uzav = true;
			podm = false;
			n2 = "uzav�en�";
		} else {
			n2 = "otev�en�";
		}
		solutionsCount = 0;

		solutionBoard = new int[ySize][xSize];
		
		PrintStream originalStdout = System.out;
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream("backtracking.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out);


		System.out.println("Tento soubor obsahuje v�echna mo�n� �e�en� vygenerovan� algoritmem Backtracking.");
		System.out.println("�e�en� jsou vyps�na v podob� ��sel ve tvaru velikosti �achovnice " + xSize + "x" + ySize + ".");
		System.out.println("Ka�d� ��slo reprezentuje krok jezdcovy proch�zky.");
		System.out.println("��slo 0 je pol��ko, kde jezdec za��n�");
		System.out.println("��slo 1 je pol��ko, kam se jezdec p�esunul z kroku 0 atd...");
		System.out.println();
		
		// start = System.currentTimeMillis();
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++) {
				solutionBoard[i][j] = NOT_VISITED;
			}
		}
		solve();
		System.out.println("<td>" + solutionsCount + "</td>");

		if (uzav) {
			solutionsCount = solutionsCount / 2;
		}
		
		System.setOut(originalStdout);
		JOptionPane.showMessageDialog(null, "Celkov� po�et cest na �achovnici " + xSize + "x" + ySize + ", kter� jsou "
				+ n2 + " je: " + solutionsCount + "\nV�echna lze nal�zt v souboru backtracking.txt ve slo�ce s aplikac�.", "V�sledek", JOptionPane.INFORMATION_MESSAGE, null);
	}

	/**
	 * Resi jezdcovu prochazku
	 */
	public void solve() {
		if (uzav) {
			takeTurn(2, 2, 0);
			solutionBoard[2][2] = NOT_VISITED; // reset pole
		} else {
			for (int i = 0; i < ySize; i++) {
				for (int j = 0; j < xSize; j++) {
					takeTurn(j, i, 0);
					solutionBoard[i][j] = NOT_VISITED; // reset pole
				}
			}
		}
	}

	/**
	 * Vrati policka, na ktera muze kun skocit
	 * 
	 * @param x
	 *            souradnice kone x
	 * @param y
	 *            souradnice kone y
	 * @return souradnice, na ktere muze kun skocit
	 */
	private List<Coords> getFields(int x, int y) {
		List<Coords> l = new ArrayList<Coords>();
		if (x + 2 < xSize && y - 1 >= 0)
			l.add(new Coords(x + 2, y - 1)); // doprava nahoru
		if (x + 1 < xSize && y - 2 >= 0)
			l.add(new Coords(x + 1, y - 2)); // nahoru doprava
		if (x - 1 >= 0 && y - 2 >= 0)
			l.add(new Coords(x - 1, y - 2)); // nahoru doleva
		if (x - 2 >= 0 && y - 1 >= 0)
			l.add(new Coords(x - 2, y - 1)); // doleva nahoru
		if (x - 2 >= 0 && y + 1 < ySize)
			l.add(new Coords(x - 2, y + 1)); // doleva dolu
		if (x - 1 >= 0 && y + 2 < ySize)
			l.add(new Coords(x - 1, y + 2)); // dolu doleva
		if (x + 1 < xSize && y + 2 < ySize)
			l.add(new Coords(x + 1, y + 2)); // dolu doprava
		if (x + 2 < xSize && y + 1 < ySize)
			l.add(new Coords(x + 2, y + 1)); // doprava dolu
		return l;
	}

	/**
	 * Provede tah konem
	 * 
	 * @param x
	 *            cilova souradnice x
	 * @param y
	 *            cilova souradnice y
	 * @param turnNr
	 *            cislo tahu
	 */
	private void takeTurn(int x, int y, int turnNr) {

		solutionBoard[y][x] = turnNr;
		if (turnNr == (xSize * ySize) - 1) {
			// System.out.println(System.currentTimeMillis() - start + " " +
			// (System.currentTimeMillis() - start)/1000F);
			if (uzav) {
				for (Coords c : getFields(x, y)) {
					if (solutionBoard[c.getY()][c.getX()] == 0) {
						// System.out.println();
						// System.out.println();
						podm = true;
					}
				}
			}

			printSolution();
			return;
		} else {
			for (Coords c : getFields(x, y)) {
				if (solutionBoard[c.getY()][c.getX()] == NOT_VISITED) {
					takeTurn(c.getX(), c.getY(), turnNr + 1);
					solutionBoard[c.getY()][c.getX()] = NOT_VISITED; // reset
																		// policka
				}
			}
		}
	}

	/**
	 * Vypise reseni
	 */
	private void printSolution() {
		if (podm) {

			solutionsCount++;
			podm = false;

			System.out.println(solutionsCount + ". �e�en� (Backtracking)");
			for (int i = 0; i < solutionBoard.length; i++) {
				for (int j = 0; j < solutionBoard[i].length; j++) {
					System.out.print(solutionBoard[i][j] + " ");
				}
				System.out.println("");
			}
			System.out.println("");

			if (!uzav) {
				podm = true;
			}

		}
	}

	/**
	 * @return the solutionsCount
	 */
	public int getSolutionsCount() {
		return solutionsCount;
	}

	/**
	 * Reprezentuje souradnici
	 */
	private class Coords {
		private int x;
		private int y;

		public Coords(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}
	}
}