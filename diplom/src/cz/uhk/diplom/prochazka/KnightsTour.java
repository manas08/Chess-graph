package cz.uhk.diplom.prochazka;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Resicka jezdcovy prochazky backtrackingem
 * @author Pavel Micka
 */
public class KnightsTour {

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
     * Pole pro reseni
     * 0 -> pocatecni pozice kone
     * 1 -> prvni tah
     * 2 -> druhy tah
     * .
     * .
     * .
     * n -> n-ty tah
     */
    private int[][] solutionBoard;

    private boolean podm = true, uzav = false;

    /*
    public static void main(String[] args) {
        //for (int i = 1; i < 20; i++) {
            KnightsTour kt = new KnightsTour(6,6);
        //}
    }
     */

    /**
     * Konstruktor resitele jezdcovy prochazky
     * @param xSize velikost sachovnice na ose x
     * @param ySize velikost sachovnice na ose y
     */
    public KnightsTour(int xSize, int ySize, String n2) {
    	if (n2 == "uzavøené cesty") {
			uzav = true;
			podm = false;
			n2 = "uzavøené";
		}else {
			n2 = "otevøené";
		}
        solutionsCount = 0;

        this.xSize = xSize;
        this.ySize = ySize;

        solutionBoard = new int[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                solutionBoard[i][j] = NOT_VISITED;
            }
        }
        solve();
        System.out.println("<td>"+solutionsCount+"</td>");
        
        if (uzav) {
            solutionsCount = solutionsCount/(2*(xSize*ySize));
		}
        JOptionPane.showMessageDialog(
        	    null, 
        	    "Celkový poèet cest na šachovnici " + xSize + "x" + ySize + ", které jsou " + n2 + " je: " + solutionsCount, 
        	    "Výsledek",
        	    JOptionPane.INFORMATION_MESSAGE, 
        	    null); 
    }

    /**
     * Resi jezdcovu prochazku
     */
    public void solve() {
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                takeTurn(j, i, 0);
                solutionBoard[i][j] = NOT_VISITED; //reset pole
            }
        }
    }

    /**
     * Vrati policka, na ktera muze kun skocit
     * @param x souradnice kone x
     * @param y souradnice kone y
     * @return souradnice, na ktere muze kun skocit
     */
    private List<Coords> getFields(int x, int y) {
        List<Coords> l = new ArrayList<Coords>();
        if (x + 2 < xSize && y - 1 >= 0)
            l.add(new Coords(x + 2, y - 1)); //doprava nahoru
        if (x + 1 < xSize && y - 2 >= 0)
            l.add(new Coords(x + 1, y - 2)); //nahoru doprava
        if (x - 1 >= 0 && y - 2 >= 0)
            l.add(new Coords(x - 1, y - 2)); //nahoru doleva
        if (x - 2 >= 0 && y - 1 >= 0)
            l.add(new Coords(x - 2, y - 1)); //doleva nahoru
        if (x - 2 >= 0 && y + 1 < ySize)
            l.add(new Coords(x - 2, y + 1)); //doleva dolu
        if (x - 1 >= 0 && y + 2 < ySize)
            l.add(new Coords(x - 1, y + 2)); //dolu doleva
        if (x + 1 < xSize && y + 2 < ySize)
            l.add(new Coords(x + 1, y + 2)); //dolu doprava
        if (x + 2 < xSize && y + 1 < ySize)
            l.add(new Coords(x + 2, y + 1)); //doprava dolu
        return l;
    }

    /**
     * Provede tah konem
     * @param x cilova souradnice x
     * @param y cilova souradnice y
     * @param turnNr cislo tahu
     */
    private void takeTurn(int x, int y, int turnNr) {
    	
        solutionBoard[y][x] = turnNr;
        if (turnNr == (xSize * ySize) - 1) {

        	//UZAVRENE orientovane, nezalezi na zaèátku
        	// pro neorientovane vydìl 2 ... a aby se kruznice neopakovali (nemìnili se jen zaèátky kružnic) vydìl ještì poètem polí (3x10 - 30)
           
        	if (uzav) {
            	for (Coords c : getFields(x, y)) {
                    if (solutionBoard[c.getY()][c.getX()] == 0) {
                        //solutionBoard[c.getY()][c.getX()] = 36; //reset policka
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
                    solutionBoard[c.getY()][c.getX()] = NOT_VISITED; //reset policka
                }
            }
        }
    }

    /**
     * Vypise reseni
     */
    private void printSolution() {
        //System.out.println(solutionBoard.length + " Reseni # " + solutionBoard[solutionBoard.length-1].length);
    	
    	if (podm) {
    		
            solutionsCount++;
			podm = false;
	        
			System.out.println("Reseni #" + solutionsCount);
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
         * @param x the x to set
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * @return the y
         */
        public int getY() {
            return y;
        }

        /**
         * @param y the y to set
         */
        public void setY(int y) {
            this.y = y;
        }
    }
}