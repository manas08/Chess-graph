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
			jLabel.setText("Hledání Hamiltonovské krunice");
			jLabel.setFont(new Font("serif", Font.BOLD, 17));
			jLabel.setBounds(50,80,250,25);
			jLabel.setSize(250,25);
			setText("( \"kreslení jedním tahem\" ) \n \n - Spojte body pomocí hran. \n \n - Navštivte kadı bod \n a vrate se do poèáteèního bodu.\n \n "
					+ " - Bílé hrany oznaèují moné cesty. \n \n - Krok zpìt naleznete na dolní lištì.");
			break;
		case 1:
			jLabel.setText("Jezdcova procházka");
			jLabel.setFont(new Font("serif", Font.BOLD, 19));
			jLabel.setBounds(90,80,200,25);
			jLabel.setSize(200,25);
			setText(" \n \n - Projeïte kadé pole šachovnice. \n \n - Kadé pole musíte navštívit právì 1x. \n \n - Krok zpìt naleznete na dolní lištì.");
			break;

		case 2:
			jLabel.setText("Krycí hra");
			jLabel.setFont(new Font("serif", Font.BOLD, 19));
			jLabel.setBounds(120,80,150,25);
			jLabel.setSize(150,25);
			setText(" \n - Umístìte konì na šachovnici, \n tak aby kadé pole bylo kryto. \n \n - Kryté pole oznaèeno èervenì. \n \n - Máte omezenı poèet koní. ( " + size + " ) \n \n"
					+ " - Pøidání konì naleznete na dolní lištì.");
			break;
		default:
			break;
		}
	}

	public void limitHorses(int size) {
		this.size = size;
	}
    
}