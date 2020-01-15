package cz.uhk.diplom.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HelpNote extends JTextArea {

    private BufferedImage img;
    JTextField jLabel;
    int size;

    public HelpNote(int a, int b) {
        super(a,b);

        setSize(a, b);
        setBounds(20, 200, a, b);
		setVisible(true);
		//setBorder(BorderFactory.createSoftBevelBorder(0, Color.WHITE, Color.BLACK));
		setEditable(false);
		jLabel = new JTextField();
		setText(0);
		jLabel.setOpaque(false);
		jLabel.setBorder(null);
		jLabel.setEditable(false);
		jLabel.setEnabled(false);
		jLabel.setBackground(new Color(1,1,1, (float) 0.01));
		jLabel.setDisabledTextColor(Color.BLACK);
		add(jLabel);
		setDisabledTextColor(Color.BLACK);
		setEnabled(false);
		setBorder(BorderFactory.createCompoundBorder(
		        getBorder(), 
		        BorderFactory.createEmptyBorder(105, 80, 60, 60)));
		setBackground(new Color(1,1,1, (float) 0.01));
        try{
    		img= ImageIO.read(getClass().getResourceAsStream("/textures/notestick.png"));
        } catch(IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img,0,0,null);
        super.paintComponent(g);
    }

	public void setText(int s) {
		switch (s) {
		case 0:
			jLabel.setText("Hled�n� Hamiltonovsk� kru�nice");
			jLabel.setFont(new Font("serif", Font.BOLD, 17));
			jLabel.setBounds(50,80,250,25);
			jLabel.setSize(250,25);
			setText("( \"kreslen� jedn�m tahem\" ) \n \n - Spojte body pomoc� hran. \n \n - Nav�tivte ka�d� bod \n a vra�te se do po��te�n�ho bodu.\n \n "
					+ " - B�l� hrany ozna�uj� mo�n� cesty. \n \n - Krok zp�t naleznete na doln� li�t�.");
			break;
		case 1:
			jLabel.setText("Jezdcova proch�zka");
			jLabel.setFont(new Font("serif", Font.BOLD, 19));
			jLabel.setBounds(90,80,200,25);
			jLabel.setSize(200,25);
			setText(" \n \n - Proje�te ka�d� pole �achovnice. \n \n - Ka�d� pole mus�te nav�t�vit pr�v� 1x. \n \n - Krok zp�t naleznete na doln� li�t�.");
			break;

		case 2:
			jLabel.setText("Kryc� hra");
			jLabel.setFont(new Font("serif", Font.BOLD, 19));
			jLabel.setBounds(120,80,150,25);
			jLabel.setSize(150,25);
			setText(" \n - Um�st�te kon� na �achovnici, \n tak aby ka�d� pole bylo kryto. \n \n - Kryt� pole ozna�eno �erven�. \n \n - M�te omezen� po�et kon�. ( " + size + " ) \n \n"
					+ " - P�id�n� kon� naleznete na doln� li�t�.");
			break;
		default:
			break;
		}
	}

	public void limitHorses(int size) {
		this.size = size;
	}
    
}