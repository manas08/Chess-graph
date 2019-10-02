package cz.uhk.diplom.model;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Image implements Serializable {
	private List<GraphicsObject> grafickeObjekty = new ArrayList<>();
	
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
		for (GraphicsObject o : grafickeObjekty) {
			o.nakresli(g);
		}
	}
	
	public void smazCelyObrazek() {
		for (GraphicsObject o : grafickeObjekty) {
			odeber(o);
		}
	}
	
	public List<GraphicsObject> getObjects(){
		return grafickeObjekty;
	}
}
