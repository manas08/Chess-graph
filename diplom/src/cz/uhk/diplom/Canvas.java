package cz.uhk.diplom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import cz.uhk.diplom.model.Image;

public class Canvas extends JComponent {
	private Image obrazek; // = new Obrazek();
	
	public Canvas(Image obrazek) {
		this.obrazek = obrazek;
	}
	
	public void setObrazek(Image obrazek) {
		this.obrazek = obrazek;
		repaint();
	}
	
	public void repaint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		super.paint(g2);
		obrazek.nakresliCelyObrazek(g2);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		super.paint(g2);

	    RenderingHints rhints = g2.getRenderingHints();
	    boolean antialiasOn = rhints.containsValue(RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
		obrazek.nakresliCelyObrazek(g2);
	}
}
