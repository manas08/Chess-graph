package cz.uhk.diplom.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Image implements Serializable {
	private List<GraphicsObject> grafickeObjekty = new ArrayList<>();
	private Graphics2D g;
	
	public void pridej(GraphicsObject o) {
		// pridame objekt do kolekce
		grafickeObjekty.add(o);
	}

	public void odeber(GraphicsObject o) {
		// pridame objekt do kolekce
		grafickeObjekty.remove(o);
	}
	
	public void nakresliCelyObrazek(Graphics2D g) {
		//Obdelnik o = new Obdelnik(0, 0, 600, 600);
		//o.nakresli(g);
		this.g = g;
		for (GraphicsObject o : grafickeObjekty) {
			o.nakresli(g);
		}
		//g.fillRect(20, 200, 200, 200);
		//g.setColor(Color.WHITE);
		//g.drawString("Nejaky text, je jedno jaky", 40, 250);
	}
	
	public void smazCelyObrazek() {
		for (GraphicsObject o : grafickeObjekty) {
			odeber(o);
		}
	}
	
	public List<GraphicsObject> getObjects(){
		return grafickeObjekty;
	}
	
	public Graphics2D getGraphics() {
		return g;
	}
}
