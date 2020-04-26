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
	 *         Backtracking algoritm for searching Knight's tours in chessboard
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
	private int solutionsCount, pocetReseni;
	/**
	 * Pole pro reseni 0 -> pocatecni pozice kone 1 -> prvni tah 2 -> druhy tah . .
	 * . n -> n-ty tah
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

	public KnightsTour(int xSize, int ySize, String n2, int pocetReseni) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.pocetReseni = pocetReseni;
		if (n2 == "uzavøené cesty") {
			uzav = true;
			podm = false;
			n2 = "uzavøené";
		} else {
			n2 = "otevøené";
		}
		solutionsCount = 0;

		solutionBoard = new int[ySize][xSize];

		// výstup
		PrintStream originalStdout = System.out;
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream("backtracking.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(out);

		System.out.println("Tento soubor obsahuje " + pocetReseni + " øešení vygenerovaná algoritmem Backtracking.");
		System.out.println(
				"Øešení jsou vypsána v podobì èísel ve tvaru velikosti šachovnice " + xSize + "x" + ySize + ".");
		System.out.println("Každé èíslo reprezentuje krok jezdcovy procházky.");
		System.out.println("Èíslo 0 je políèko, kde jezdec zaèíná");
		System.out.println("Èíslo 1 je políèko, kam se jezdec pøesunul z kroku 0 atd...");
		System.out.println();

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
		JOptionPane.showMessageDialog(null,
				"Právì bylo vygenerováno " + pocetReseni + " cest jezdcovy procházky " + "\n na šachovnici " + xSize
						+ "x" + ySize + ", jedná se o " + n2
						+ ".\nLze je nalézt v souboru backtracking.txt ve složce s aplikací.",
				"Hotovo", JOptionPane.INFORMATION_MESSAGE, null);
	}

	/**
	 * Resi jezdcovu prochazku
	 */
	public void solve() {
		// hledání øešení od všech možných výchozích pozic jezdce
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++) {
				takeTurn(j, i, 0);
				solutionBoard[i][j] = NOT_VISITED; // rekurzivní reset pole
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
		// pokud máme všechna øešení co jsme chtìli -> konec
		if (pocetReseni == solutionsCount) {
			return;
		}
		solutionBoard[y][x] = turnNr;

		// pokud bylo nalezno øešení
		if (turnNr == (xSize * ySize) - 1) {
			// podmínka pro výstup uzavøených øešení
			if (uzav) {
				for (Coords c : getFields(x, y)) {
					if (solutionBoard[c.getY()][c.getX()] == 0) {
						podm = true;
					}
				}
			}

			printSolution(); // tisk øešení
			return;

			// pokud ještì nebylo øešení nalezeno
		} else {
			// provedení tahu na ještì nenavštívené pole dle pravidel šachu
			for (Coords c : getFields(x, y)) {
				if (solutionBoard[c.getY()][c.getX()] == NOT_VISITED) {
					takeTurn(c.getX(), c.getY(), turnNr + 1);
					// rekurzivní reset pole
					solutionBoard[c.getY()][c.getX()] = NOT_VISITED;
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

			System.out.println(solutionsCount + ". øešení (Backtracking)");
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

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}
}