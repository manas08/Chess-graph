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
import cz.uhk.diplom.Theory;

public class TheoryPictures extends JTextArea {

    private BufferedImage img1, img2, img3;
    JTextArea jLabel, jt, jt1;
    int size, mode = 0, a, b;
	JButton btnNext = new JButton(">");
	JButton btnBack = new JButton("<");
	Theory theory;

    public TheoryPictures(int a, int b, Theory theory, int mode) {
        super(a,b);
        this.a = a;
        this.b = b;
        this.theory = theory;
        this.mode = mode;

        try{
    		img1= ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour1.png"));
    		img2= ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour2.png"));
    		img3= ImageIO.read(getClass().getResourceAsStream("/textures/tour/tour3.png"));
        } catch(IOException e) {
            System.out.println(e.toString());
        }
        
        setSize(a, b);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
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
    	switch (mode) {
		case 0:
	        g.drawImage(img1,0,0,null);
	        super.paintComponent(g);
			break;
		case 1:
	        g.drawImage(img2,0,0,null);
	        super.paintComponent(g);
			break;
		case 2:
	        g.drawImage(img3,0,0,null);
	        super.paintComponent(g);
			break;
		default:
			break;
		}
    }

	public void limitHorses(int size) {
		this.size = size;
	}

	public void setMode(int mode2) {
		this.mode = mode2;
	}
    
}