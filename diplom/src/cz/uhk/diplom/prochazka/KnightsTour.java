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
		if (n2 == "uzav�en� cesty") {
			uzav = true;
			podm = false;
			n2 = "uzav�en�";
		} else {
			n2 = "otev�en�";
		}
		solutionsCount = 0;

		solutionBoard = new int[ySize][xSize];

		// v�stup
		PrintStream originalStdout = System.out;
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream("backtracking.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(out);

		System.out.println("Tento soubor obsahuje " + pocetReseni + " �e�en� vygenerovan� algoritmem Backtracking.");
		System.out.println(
				"�e�en� jsou vyps�na v podob� ��sel ve tvaru velikosti �achovnice " + xSize + "x" + ySize + ".");
		System.out.println("Ka�d� ��slo reprezentuje krok jezdcovy proch�zky.");
		System.out.println("��slo 0 je pol��ko, kde jezdec za��n�");
		System.out.println("��slo 1 je pol��ko, kam se jezdec p�esunul z kroku 0 atd...");
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
				"Pr�v� bylo vygenerov�no " + pocetReseni + " cest jezdcovy proch�zky " + "\n na �achovnici " + xSize
						+ "x" + ySize + ", jedn� se o " + n2
						+ ".\nLze je nal�zt v souboru backtracking.txt ve slo�ce s aplikac�.",
				"Hotovo", JOptionPane.INFORMATION_MESSAGE, null);
	}

	/**
	 * Resi jezdcovu prochazku
	 */
	public void solve() {
		// hled�n� �e�en� od v�ech mo�n�ch v�choz�ch pozic jezdce
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++) {
				takeTurn(j, i, 0);
				solutionBoard[i][j] = NOT_VISITED; // rekurzivn� reset pole
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
		// pokud m�me v�echna �e�en� co jsme cht�li -> konec
		if (pocetReseni == solutionsCount) {
			return;
		}
		solutionBoard[y][x] = turnNr;

		// pokud bylo nalezno �e�en�
		if (turnNr == (xSize * ySize) - 1) {
			// podm�nka pro v�stup uzav�en�ch �e�en�
			if (uzav) {
				for (Coords c : getFields(x, y)) {
					if (solutionBoard[c.getY()][c.getX()] == 0) {
						podm = true;
					}
				}
			}

			printSolution(); // tisk �e�en�
			return;

			// pokud je�t� nebylo �e�en� nalezeno
		} else {
			// proveden� tahu na je�t� nenav�t�ven� pole dle pravidel �achu
			for (Coords c : getFields(x, y)) {
				if (solutionBoard[c.getY()][c.getX()] == NOT_VISITED) {
					takeTurn(c.getX(), c.getY(), turnNr + 1);
					// rekurzivn� reset pole
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

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}
}