package cz.uhk.diplom.model;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Image implements Serializable {
	private List<GraphicsObject> grafickeObjekty = new ArrayList<>();
	private Graphics2D g;

	public void pridej(GraphicsObject o) {
		grafickeObjekty.add(o);
	}

	public void odeber(GraphicsObject o) {
		grafickeObjekty.remove(o);
	}

	public void nakresliCelyObrazek(Graphics2D g) {
		this.g = g;
		for (GraphicsObject o : grafickeObjekty) {
			o.nakresli(g);
		}
	}

	public void smazCelyObrazek() {
		for (GraphicsObject o : grafickeObjekty) {
			odeber(o);
		}
	}

	public List<GraphicsObject> getObjects() {
		return grafickeObjekty;
	}

	public Graphics2D getGraphics() {
		return g;
	}
}
