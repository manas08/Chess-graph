package cz.uhk.diplom.prochazka;

import java.util.*;
public class KnightTest2 {

		public static int n;
		public static String response;
		public static int[][] label = new int[1000][1000];
		public static int[][] deg = new int[1000][1000];
		public static int[] DX = {1, 2, 2, 1, -1, -2, -2, -1};
		public static int[] DY = {-2, -1, 1, 2, 2, 1, -1, -2};
		
		private static long start;
		private static int numberofsolution = 0;

		public static void output_label()
		{
			for (int j = 0; j < n; j++)
			{
				System.out.print(label[0][j]);
				for (int i = 1; i < n; i++)
				{
					System.out.print(" ");
					System.out.print(label[i][j]);
				}
				System.out.print("\n");
			}
		}

		public static void output()
		{
			System.out.print("Solution:");
			System.out.print("\n");
			output_label();

			numberofsolution++;
			if (numberofsolution == 20) {
				System.exit(0);	
			}
		}

		public static void init()
		{
			int i;
			int j;
			int u;
			int v;
			int t;

			// pro každé pole
			for (i = 0; i < n; i++)
			{
				for (j = 0; j < n; j++)
				{
					// pro každý smìr
					for (t = 0; t < 8; t++)
					{
						// DX a DY obsahují pohyby jezdce po ose X a Y
						u = i + DX[t];
						v = j + DY[t];
						// když se pøi pohybu nedostanou mimo šachovnici
						// -> zvýší se ohodnocení
						if ((u >= 0) && (u < n) && (v >= 0) && (v < n))
						{
							deg[i][j]++;
						}
					}
				}
			}
		}

		public static void BackTracking(int number, int i, int j)
		{
			int u;
			int v;
			int t;
			int nChoices;
			int[] d = new int[8];
			int[] next = new int[8];

			label[i][j] = number;

			if (number < n * n)
			{
				nChoices = 0;
				for (t = 0; t < 8; t++)
				{
					u = i + DX[t];
					v = j + DY[t];
					if ((u >= 0) && (u < n) && (v >= 0) && (v < n))
					{
						if (label[u][v] == 0)
						{
							d[nChoices] = deg[u][v];
							next[nChoices] = t;
							nChoices++;
							deg[u][v]--;
						}
					}
				}

				for (u = 0; u < nChoices; u++)
				{
					for (v = u + 1; v < nChoices; v++)
					{
						if (d[u] > d[v])
						{
							int pom1 = d[u];
							d[u] = d[v];
							d[v] = pom1;
							
							pom1 = next[v];
							next[v] = next[u];
							next[u] = pom1;
						}
					}
				}

				for (t = 0; t < nChoices; t++)
				{
					BackTracking(number + 1, i + DX[next[t]], j + DY[next[t]]);
				}

				for (t = 0; t < 8; t++)
				{
					u = i + DX[t];
					v = j + DY[t];
					if ((u >= 0) && (u < n) && (v >= 0) && (v < n))
					{
						if (label[u][v] == 0)
						{
							deg[u][v]++;
						}
					}
				}
			}
			else
			{
				//Case 1: Only need to find a route
				if ((response.charAt(0) == 'r') || (response.charAt(0) == 'R'))
				{
					output();
				}

				//Case 2: Need to find a Hamilton circle
				else if ((i != 0) && (j != 0))
				{

					// pro každý smìr
					for (t = 0; t < 8; t++)
					{
						// DX a DY obsahují pohyby jezdce po ose X a Y
						u = i + DX[t];
						v = j + DY[t];
						// když se pøi pohybu nedostanou mimo šachovnici
						// -> zvýší se ohodnocení
						if ((u == 2) && (v == 2))
						{
							System.out.println();
							System.out.println(System.currentTimeMillis() - start + " " + (System.currentTimeMillis() - start)/1000F);
							System.out.println();
							output();
						}
					}
					if (i + j == 3)
					{
						output();
					}
				}
			}

			label[i][j] = 0;
		}

		public static void Main()
		{
			start = System.currentTimeMillis();
			n = 26;
			response = "c";
			init();

			if ((response.charAt(0) == 'r') || (response.charAt(0) == 'R'))
			{
				for (int i = 0; i < n; i++)
				{
					for (int j = 0; j < n; j++)
					{
						BackTracking(1, i, j);
					}
				}
			}
			else
			{
				BackTracking(1, 2, 2);
			}

			System.out.print("There is no solution.");
			System.out.print("\n");
		}
}
