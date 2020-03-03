package cz.uhk.diplom.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;

import cz.uhk.diplom.MainWindow;

public class TheoryPictures extends JTextArea {

	private BufferedImage img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12,
	img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24, img25,
	moves, knightTour;
    JTextArea jLabel, jt, jt1;
    int size, mode = 26, a, b, podm = 1;
	JButton btnNext = new JButton(">");
	JButton btnBack = new JButton("<");
	double width, height;

    public TheoryPictures(int a, int b, int mode) {
        super(a,b);
        this.a = a;
        this.b = b;
        this.mode = mode;

        if (mode == 3) {
			podm = 0;
		}
        
        try{
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
			moves = ImageIO.read(getClass().getResourceAsStream("/textures/moves220.jpg"));
			knightTour = ImageIO.read(getClass().getResourceAsStream("/textures/knight400.png"));
        } catch(IOException e) {
            System.out.println(e.toString());
        }
        
        setSize(a, b);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		
        setBounds((int) (((width / 2) + 200)), (int) (((height / 2) - 250)), a, b);
		setVisible(true);
		//setBorder(BorderFactory.createSoftBevelBorder(0, Color.WHITE, Color.BLACK));
		setEditable(false);
		setFocusable(false);
		
		setDisabledTextColor(Color.WHITE);
		setEnabled(false);
		setBorder(BorderFactory.createCompoundBorder(
		        getBorder(), 
		        BorderFactory.createEmptyBorder(105, 80, 60, 60)));
		setBackground(new Color(1,1,1, (float) 0.0001));
		
    }

    @Override
    protected void paintComponent(Graphics g) {
        setBounds((int) (((width / 2) + 200)), (int) (((height / 2) - 250)), a, b);
    	switch (mode) {
		case 0:
	        g.drawImage(img1,0,0,null);
			break;
		case 1:
	        g.drawImage(img2,0,0,null);
			break;
		case 2:
	        g.drawImage(img3,0,0,null);
			break;
		case 3:
	        g.drawImage(img4,0,0,null);
			break;
		case 4:
	        g.drawImage(img5,0,0,null);
			break;
		case 5:
	        g.drawImage(img6,0,0,null);
			break;
		case 6:
	        g.drawImage(img7,0,0,null);
			break;
		case 7:
	        g.drawImage(img8,0,0,null);
			break;
		case 8:
	        g.drawImage(img9,0,0,null);
			break;
		case 9:
	        g.drawImage(img10,0,0,null);
			break;
		case 10:
	        g.drawImage(img11,0,0,null);
			break;
		case 11:
	        g.drawImage(img12,0,0,null);
			break;
		case 12:
	        g.drawImage(img13,0,0,null);
			break;
		case 13:
	        g.drawImage(img14,0,0,null);
			break;
		case 14:
	        g.drawImage(img15,0,0,null);
			break;
		case 15:
	        g.drawImage(img16,0,0,null);
			break;
		case 16:
	        g.drawImage(img17,0,0,null);
			break;
		case 17:
	        g.drawImage(img18,0,0,null);
			break;
		case 18:
	        g.drawImage(img19,0,0,null);
			break;
		case 19:
	        g.drawImage(img20,0,0,null);
			break;
		case 20:
	        g.drawImage(img21,0,0,null);
			break;
		case 21:
	        g.drawImage(img22,0,0,null);
			break;
		case 22:
	        g.drawImage(img23,0,0,null);
			break;
		case 23:
	        g.drawImage(img24,0,0,null);
			break;
		case 24:
	        g.drawImage(img25,0,0,null);
			break;
		case 25:
	        g.drawImage(moves,0,0,null);
			break;
		case 26:
	        setBounds((int) (((width / 2) + 150)), (int) (((height / 2) - 350)), a, b);
	        g.drawImage(knightTour,0,0,null);
			break;
		default:
			break;
		}
        super.paintComponent(g);
    }

	public void limitHorses(int size) {
		this.size = size;
	}

	public void setMode(int mode2) {
		this.mode = mode2;
	}    
}