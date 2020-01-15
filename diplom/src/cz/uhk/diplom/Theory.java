package cz.uhk.diplom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;

import cz.uhk.diplom.model.Image;
import cz.uhk.diplom.model.Vertex;

public class Theory {
	BufferedImage img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12,
	img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24, img25,
	moves;
	Vertex vertex;
	Image obrazek;
	int animationCount = 1, step = 0;
	JLabel l1;
	int width, height;
	MainWindow main;

	public Theory() {
		try {
			img1 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour1.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour2.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour3.png"));
			img4 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour4.png"));
			img5 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour5.png"));
			img6 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour6.png"));
			img7 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour7.png"));
			img8 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour8.png"));
			img9 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour9.png"));
			img10 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour10.png"));
			img11 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour11.png"));
			img12 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour12.png"));
			img13 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour13.png"));
			img14 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour14.png"));
			img15 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour15.png"));
			img16 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour16.png"));
			img17 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour17.png"));
			img18 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour18.png"));
			img19 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour19.png"));
			img20 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour20.png"));
			img21 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour21.png"));
			img22 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour22.png"));
			img23 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour23.png"));
			img24 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour24.png"));
			img25 = ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour25.png"));
			moves = ImageIO.read(getClass().getResourceAsStream("/textures/moves.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawTheory(Image obrazek, MainWindow main){
		this.main = main;
		this.obrazek = obrazek;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();

		//vertex = new Vertex(((width / 2) + 200),((height / 2) - 220), img1, 3);

		//this.obrazek.pridej(vertex);
		//this.main.setObrazek(this.obrazek);
		animationCount++;
	}

	public void animate(Image obrazek, MainWindow main) {
		this.obrazek = obrazek;
		try {
			TimeUnit.MILLISECONDS.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (animationCount % 25 == 0) {
			vertex = new Vertex(((width / 2) + 20),((height / 2) - 220), img25, 3);
		}else if (animationCount % 24 == 0) {
			vertex = new Vertex(500, 500, img24, 3);
		}else if (animationCount % 23 == 0) {
			vertex = new Vertex(500, 500, img23, 3);
		}else if (animationCount % 22 == 0) {
			vertex = new Vertex(500, 500, img22, 3);
		}else if (animationCount % 21 == 0) {
			vertex = new Vertex(500, 500, img21, 3);
		}else if (animationCount % 20 == 0) {
			vertex = new Vertex(500, 500, img20, 3);
		}else if (animationCount % 19 == 0) {
			vertex = new Vertex(500, 500, img19, 3);
		}else if (animationCount % 18 == 0) {
			vertex = new Vertex(500, 500, img18, 3);
		}else if (animationCount % 17 == 0) {
			vertex = new Vertex(500, 500, img17, 3);
		}else if (animationCount % 16 == 0) {
			vertex = new Vertex(500, 500, img16, 3);
		}else if (animationCount % 15 == 0) {
			vertex = new Vertex(500, 500, img15, 3);
		}else if (animationCount % 14 == 0) {
			vertex = new Vertex(500, 500, img14, 3);
		}else if (animationCount % 13 == 0) {
			vertex = new Vertex(500, 500, img13, 3);
		}else if (animationCount % 12 == 0) {
			vertex = new Vertex(500, 500, img12, 3);
		}else if (animationCount % 11 == 0) {
			vertex = new Vertex(500, 500, img11, 3);
		}else if (animationCount % 10 == 0) {
			vertex = new Vertex(500, 500, img10, 3);
		}else if (animationCount % 9 == 0) {
			vertex = new Vertex(500, 500, img9, 3);
		}else if (animationCount % 8 == 0) {
			vertex = new Vertex(500, 500, img8, 3);
		}else if (animationCount % 7 == 0) {
			vertex = new Vertex(500, 500, img7, 3);
		}else if (animationCount % 6 == 0) {
			vertex = new Vertex(500, 500, img6, 3);
		}else if (animationCount % 5 == 0) {
			vertex = new Vertex(500, 500, img5, 3);
		}else if (animationCount % 4 == 0) {
			vertex = new Vertex(500, 500, img4, 3);
		}else if (animationCount % 3 == 0) {
			vertex = new Vertex(500, 500, img3, 3);
		}else if (animationCount % 2 == 0) {
			vertex = new Vertex(500, 500, img2, 3);
		}else {
			vertex = new Vertex(500, 500, img1, 3);
		}
		this.obrazek.pridej(vertex);
		main.setObrazek(this.obrazek);
		animationCount++;
	}

	public MainWindow getMain() {
		return main;
	}
}
