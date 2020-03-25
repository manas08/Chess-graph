package cz.uhk.diplom.prochazka;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public class KnightTest2 {
	
	
	/**
	 * 
	 * @author manas08
	 *
	 * Warnsdorff algoritm 
	 * for searching Knight's tours in chessboard
	 *
	 */
	
	public static PrintStream originalStdout;
	public static int n;
	public static int numberOfBoards;
	public static String n2;
	public static String response;
	public static int[][] label = new int[1000][1000];
	public static int[][] deg = new int[1000][1000];
	public static int[] DX = { 1, 2, 2, 1, -1, -2, -2, -1 };
	public static int[] DY = { -2, -1, 1, 2, 2, 1, -1, -2 };

	/**
	 * for testing
	 */
	// private static long start;
	// private static float moje;
	private static int numberofsolution = 0;

	public static void output_label() {
		for (int j = 0; j < n; j++) {
			System.out.print(label[0][j]-1);
			for (int i = 1; i < n; i++) {
				System.out.print(" ");
				System.out.print(label[i][j]-1);
			}
			System.out.print("\n");
		}
	}

	public static void output() {
		if (numberofsolution == numberOfBoards) {
			return;
		}
		numberofsolution++;
		System.out.println();
		System.out.println(numberofsolution + ". øešení (Warnsdorff)");
		output_label();

		/*
		 * if (numberofsolution == 0) { moje = (System.currentTimeMillis() -
		 * start)/1000F; }
		 */
		if (numberofsolution == numberOfBoards) {
			// System.out.println();
			// System.out.println(moje + " " + (System.currentTimeMillis() -
			// start)/1000F);
			// System.out.println();

			System.setOut(originalStdout);
			JOptionPane.showMessageDialog(null, "Právì bylo vygenerováno " + numberofsolution + " cest jezdcovy procházky "
					+ "\n na šachovnici " + n + "x" + n + ", jedná se o "
					+ n2 + ".\nLze je nalézt v souboru warnsdorff.txt ve složce s aplikací.", "Hotovo", JOptionPane.INFORMATION_MESSAGE, null);
		}
	}

	public static void init() {
		int i;
		int j;
		int u;
		int v;
		int t;

		// pro každé pole
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				// pro každý smìr
				for (t = 0; t < 8; t++) {
					// DX a DY obsahují pohyby jezdce po ose X a Y
					u = i + DX[t];
					v = j + DY[t];
					// když se pøi pohybu nedostanou mimo šachovnici
					// -> zvýší se ohodnocení
					if ((u >= 0) && (u < n) && (v >= 0) && (v < n)) {
						deg[i][j]++;
					}
				}
			}
		}
	}

	public static void BackTracking(int number, int i, int j) {
		if (numberofsolution == numberOfBoards) {
			return;
		}
		
		int u;
		int v;
		int t;
		int nChoices;
		int[] d = new int[8];
		int[] next = new int[8];

		label[i][j] = number;

		if (number < n * n) {
			nChoices = 0;
			for (t = 0; t < 8; t++) {
				u = i + DX[t];
				v = j + DY[t];
				if ((u >= 0) && (u < n) && (v >= 0) && (v < n)) {
					if (label[u][v] == 0) {
						d[nChoices] = deg[u][v];
						next[nChoices] = t;
						nChoices++;
						deg[u][v]--;
					}
				}
			}

			for (u = 0; u < nChoices; u++) {
				for (v = u + 1; v < nChoices; v++) {
					if (d[u] > d[v]) {
						int pom1 = d[u];
						d[u] = d[v];
						d[v] = pom1;

						pom1 = next[v];
						next[v] = next[u];
						next[u] = pom1;
					}
				}
			}

			for (t = 0; t < nChoices; t++) {
				BackTracking(number + 1, i + DX[next[t]], j + DY[next[t]]);
			}

			for (t = 0; t < 8; t++) {
				u = i + DX[t];
				v = j + DY[t];
				if ((u >= 0) && (u < n) && (v >= 0) && (v < n)) {
					if (label[u][v] == 0) {
						deg[u][v]++;
					}
				}
			}
		} else {
			// Case 1: Need to find a route
			if ((response.charAt(0) == 'r') || (response.charAt(0) == 'R')) {
				output();
			}

			// Case 2: Need to find a Hamilton circle
			else if ((i != 0) && (j != 0)) {

				for (t = 0; t < 8; t++) {
					u = i + DX[t];
					v = j + DY[t];
					if ((u == 2) && (v == 2)) {
						output();
					}
				}
				if (i + j == 3) {
					output();
				}
			}
		}

		label[i][j] = 0;
	}

	public void Main(int n, String n2, int numberOfBoards) {
		// start = System.currentTimeMillis();
		this.n = n;
		this.n2 = n2;
		this.numberOfBoards = numberOfBoards;

		originalStdout = System.out;
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream("warnsdorff.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setOut(out);
		System.out.println("Tento soubor obsahuje " + numberOfBoards + " vygenerovaných øešení Warnsdorffovým algoritmem.");
		System.out.println("Øešení jsou vypsána v podobì èísel ve tvaru velikosti šachovnice " + n + "x" + n + ".");
		System.out.println("Každé èíslo reprezentuje krok jezdcovy procházky.");
		System.out.println("Èíslo 0 je políèko, kde jezdec zaèíná");
		System.out.println("Èíslo 1 je políèko, kam se jezdec pøesunul z kroku 0 atd...");
		// r = open, c = closed
		if (n2 == "uzavøené cesty") {
			response = "c";
		}else {
			response = "r";
		}
		init();

		if ((response.charAt(0) == 'r') || (response.charAt(0) == 'R')) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					BackTracking(1, i, j);
				}
			}
		} else {
			BackTracking(1, n - 3, n - 3); // 2,2
		}

		System.out.print("There is no solution.");
		System.out.print("\n");
	}
}
