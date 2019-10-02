package cz.uhk.diplom.model;

import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * Reprezentuje jeden mj. nakreslitelny graficky objekt
 * 
 * @author krizpa1
 *
 */
public interface GraphicsObject extends Serializable {
	/**
	 * Nakresli dany graficky objekt pomoci daneho "platno" Graphics2D g
	 * 
	 * @param g
	 */
	void nakresli(Graphics2D g);
	
	/**
	 * Nastavi sirku pomyslneho obdelniku, do ktereho je graficky objekt vepsan
	 * @param sirka
	 */
	void setX2(int x2);


	/**
	 * Nastavi vysku pomyslneho obdelniku, do ktereho je graficky objekt vepsan
	 * @param vyska
	 */
	void setY2(int y2);

	void setX1(int x1);
	void setY1(int y1);

	int getX2();
	int getY2();

	int getX1();
	int getY1();
}
