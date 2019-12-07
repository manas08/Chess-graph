package cz.uhk.diplom;

import java.util.*;
import cz.uhk.diplom.tangible.Pair;
import cz.uhk.diplom.tangible.RandomNumbers;

public class NeuralNetwork{
	public static int[] DX = {1, 2, 2, 1, -1, -2, -2, -1};
	public static int[] DY = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static final int Max_size = 100;
	public static final int Max_nIterations = 200;
	
	public static int size;
	public static int results = 0;
	public static int nNeurons;
	public static int nIterations;
	public static ArrayList< Pair<Integer, Integer>>[][] adj;
	public static int[][] label;
	public static Neuron_Info[] Neuron;
	public static int[] next_state = new int[8 * Max_size * Max_size];
	public static int[] next_output = new int[8 * Max_size * Max_size];
	public static ArrayList<Integer>[] solves;


	public static boolean Final_Check;
	

int XSIZE       =80;
int YSIZE       =80;
int CIRCLESIZE = 8;
int PENWIDTH = 9;
	
	public static void init()
	{
		solves = new ArrayList[9864];
		Neuron = new Neuron_Info[8 * Max_size * Max_size];
		for (int i = 0; i < 8 * Max_size * Max_size; i++)
		{
			Neuron[i] = new Neuron_Info();
		}
			
		ArrayList<Integer>[][] cell = new ArrayList[Max_size][Max_size];
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				cell[i][j] = new ArrayList<>(); // HERE
			}
		}

		nNeurons = 0;
		for (int x1 = 0; x1 < size; x1++)
		{
			for (int y1 = 0; y1 < size; y1++)
			{
				int i = x1 + y1 * size;

				for (int t = 0; t < 8; t++)
				{
					int x2 = x1 + DX[t];
					int y2 = y1 + DY[t];

					if ((x2 >= 0) && (x2 < size) && (y2 >= 0) && (y2 < size))
					{
						int j = x2 + y2 * size;

						if (i < j)
						{
							cell[x1][y1].add(nNeurons);
							cell[x2][y2].add(nNeurons);

							Neuron[nNeurons].x1 = x1;
							Neuron[nNeurons].y1 = y1;
							Neuron[nNeurons].x2 = x2;
							Neuron[nNeurons].y2 = y2;
							nNeurons++;
						}
					}
				}
			}
		}

		for (int i = 0; i < nNeurons; i++)
		{
			Neuron[i].adj.clear();
		}

		
		for (int x = 0; x < size; x++)
		{
			for (int y = 0; y < size; y++)
			{
				for (int u = 0; u < cell[x][y].size(); u++)
				{
					int i = cell[x][y].get(u);

					for (int v = u + 1; v < cell[x][y].size(); v++)
					{
						int j = cell[x][y].get(v);

						Neuron[i].adj.add(j);
						Neuron[j].adj.add(i);
					}
				}
			}
		}
	}

	public static void randomize_neurons_output()
	{
		for (int i = 0; i < nNeurons; i++)
		{
			Neuron[i].state = 0;
			Neuron[i].output = RandomNumbers.nextNumber() % 2;
		}
	}

	public static void calculate_next_generation()
	{
		for (int i = 0; i < nNeurons; i++)
		{
			next_state[i] = Neuron[i].state + 4 - Neuron[i].output;
			for (int j = 0; j < Neuron[i].adj.size(); j++)
			{
				int v = Neuron[i].adj.get(j);
				next_state[i] -= Neuron[v].output;
			}

			if (next_state[i] < 0)
			{
				next_output[i] = 0;
			}
			else
			{
				if (next_state[i] > 3)
				{
					next_output[i] = 1;
				}
				else
				{
					next_output[i] = Neuron[i].output;
				}
			}
		}
	}

	public static Pair<Integer, Integer> change_neurons()
	{
		int nChanges = 0;
		int nActive = 0;

		for (int i = 0; i < nNeurons; i++)
		{
			if (next_state[i] != Neuron[i].state)
			{
				nChanges++;
			}

			Neuron[i].state = next_state[i];
			Neuron[i].output = next_output[i];

			nActive += Neuron[i].output;
		}

		return new Pair<Integer, Integer>(nChanges, nActive);
	}

	private static boolean check_degree()
	{
		int[][] degree = new int[Max_size][Max_size];
		for (int i = 0; i < nNeurons; i++)
		{
			if (Neuron[i].output == 1)
			{
				degree[Neuron[i].x1][Neuron[i].y1]++;
				degree[Neuron[i].x2][Neuron[i].y2]++;
			}
		}

		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				if (degree[i][j] != 2)
				{
					return false;
				}
			}
		}
		
		return true;
	}

	private static void visit(int i, int j, int count)
	{
		label[i][j] = count;

		if (count == size * size)
		{
			if (i * j != 2)
			{
				Final_Check = false;
			}
		}

		for (int t = 0; t < adj[i][j].size(); t++)
		{
			int u = adj[i][j].get(t).first; //HERE
			int v = adj[i][j].get(t).second;

			if (label[u][v] == 0)
			{
				visit(u, v, count + 1);
				return;
			}
		}
	}

	private static int check_connection()
	{
		adj = new ArrayList[Max_size][Max_size];
		label = new int[Max_size][Max_size];
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				adj[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < nNeurons; i++)
		{
			if (Neuron[i].output == 1)
			{
				int x1 = Neuron[i].x1;
				int y1 = Neuron[i].y1;
				int x2 = Neuron[i].x2;
				int y2 = Neuron[i].y2;

				adj[x1][y1].add(new Pair<Integer, Integer>(x2, y2));
				adj[x2][y2].add(new Pair<Integer, Integer>(x1, y1));
			}
		}
		
		Final_Check = true;
		int nComponents = 0;
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				if (label[i][j] == 0)
				{
					nComponents++;
					visit(i, j, 1);
				}
			}
		}

		return nComponents;
	}

	private static void Write_Solution()
	{
		//freopen("Solution.txt", "w", stdout);
		
		//System.out.print(size);
		//System.out.print("\n");
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				list.add(label[i][j]);
				//System.out.print(" ");
				//System.out.print(label[i][j]);
			}
			//System.out.print("\n");
		}
		solves[results] = list;
		results++;
	}

	private static void process()
	{
		boolean even = false;
		int nTimes = 0;
		int count = 0;
		
		while (true)
		{
			nTimes++;
			/*
			System.out.print("\n");
			System.out.print("Time ");
			System.out.print(nTimes);
			System.out.print(":");
			System.out.print("\n");
			*/
			randomize_neurons_output();

			//System.out.print("Local Optimization.");
			//System.out.print("\n");
			nIterations = 0;
			while (true)
			{
				calculate_next_generation();
				Pair<Integer, Integer> info = change_neurons();
				int nChanges = info.first;
				int nActive = info.second;

				if (nChanges == 0)
				{
					break;
				}
				if (check_degree())
				{
					even = true;
					break;
				}

				nIterations++;
				if (nIterations == Max_nIterations)
				{
					break;
				}
			}

			if (even)
			{
				//System.out.print("All original cell have degree 2.");
				//System.out.print("\n");

				int nComponents = check_connection();
				//System.out.print("Number of connected components: ");
				//System.out.print(nComponents);
				//System.out.print("\n");

				if (nComponents == 1)
				{
					if (Final_Check)
					{
						
						boolean con = true;
						for (int i = 0; i < solves.length; i++) {
							if (solves[i] != null) {
								int same = 0;
								for (int j = 0; j < size; j++) {
									for (int j2 = 0; j2 < size; j2++) {
										if (label[j][j2] == solves[i].get(j*size+j2)) {
											same++;
										}
									}
								}
								if (same==size*size) {
									con = false;
								}
							}
						}
						
						if (con) {
							//System.out.print("\nSolution is found!");
							//System.out.print("\n");
							Write_Solution();
							count++;
							
							System.out.println(count);


								//System.out.print(size);
								System.out.print("\n");
								for (int i = 0; i < size; i++)
								{
									for (int j = 0; j < size; j++)
									{
										System.out.print(label[i][j]);
										System.out.print(" ");
									}
									System.out.print("\n");
								}

								System.out.println(count);
								
						}
					}
					else
					{
						//System.out.print("The solution cannot pass the final check.");
						//System.out.print("\n");
					}
				}
			}
		}
	}
	
	/*
}
	*/
	public static void Main()
	{
		RandomNumbers.seed(1575403299);
		System.out.print("The size of chessboard: ");
		size = 6;

		init();
		process();
	}
}
