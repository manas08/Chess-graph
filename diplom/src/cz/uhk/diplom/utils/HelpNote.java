package cz.uhk.diplom.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cz.uhk.diplom.MainWindow;

public class HelpNote extends JTextArea {

    private BufferedImage img;
    JTextField jLabel, jLabel1, jLabel2;
    JTextArea text1, text2, text3;
    JButton btnNext = new JButton(">");
    JButton btnBack = new JButton("<");
    JButton btnShow = new JButton("Uka øešení");
    JButton btnTry = new JButton("Zkusím to sám");
    int size, mode = 0, limit;
	MainWindow main;
	boolean podm = false;

    public HelpNote(int a, int b) {
        super(a,b);

        setSize(a, b);
        setBounds(20, 200, a, b);
		setVisible(true);
		//setBorder(BorderFactory.createSoftBevelBorder(0, Color.WHITE, Color.BLACK));
		setEditable(false);
		jLabel = new JTextField();
		jLabel.setText("Jezdcova procházka");
		jLabel.setOpaque(false);
		jLabel.setBorder(null);
		jLabel.setEditable(false);
		jLabel.setEnabled(false);
		jLabel.setBackground(new Color(1,1,1, (float) 0.0001));
		jLabel.setDisabledTextColor(Color.BLACK);

		jLabel.setFont(new Font("serif", Font.BOLD, 19));
		jLabel.setSize(250,25);
		jLabel.setBounds(90,80,250,25);
		add(jLabel);

		jLabel1 = new JTextField();
		jLabel1.setText("Krycí hra");
		jLabel1.setOpaque(false);
		jLabel1.setBorder(null);
		jLabel1.setEditable(false);
		jLabel1.setEnabled(false);
		jLabel1.setBackground(new Color(1,1,1, (float) 0.0001));
		jLabel1.setDisabledTextColor(Color.BLACK);

		jLabel1.setFont(new Font("serif", Font.BOLD, 19));
		jLabel1.setSize(200,25);
		jLabel1.setBounds(130,80,200,25);
		add(jLabel1);

		jLabel2 = new JTextField();
		jLabel2.setText("Hledání Hamiltonovské krunice");
		jLabel2.setOpaque(false);
		jLabel2.setBorder(null);
		jLabel2.setEditable(false);
		jLabel2.setEnabled(false);
		jLabel2.setBackground(new Color(1,1,1, (float) 0.0001));
		jLabel2.setDisabledTextColor(Color.BLACK);

		jLabel2.setFont(new Font("serif", Font.BOLD, 18));
		jLabel2.setSize(300,25);
		jLabel2.setBounds(45,80,300,25);
		add(jLabel2);

		text1 = new JTextArea();
		text1.setBounds(80, 110, 300, 200);
		text1.setSize(300, 200);
		text1.setOpaque(false);
		text1.setBorder(null);
		text1.setEditable(false);
		text1.setEnabled(false);
		text1.setBackground(new Color(1,1,1, (float) 0.0001));
		text1.setDisabledTextColor(Color.BLACK);
		add(text1);
		
		btnNext.setPreferredSize(new Dimension(30, 25));
		btnNext.setBounds(180,320, 30, 25);
		btnNext.setBackground(new Color(228,228,228));
		btnNext.setMargin(new Insets(1,1,1,1));
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mode < 4) {
					mode++;
					setText(mode);
				}
				if (mode == 4) {
					btnBack.setEnabled(true);
					btnNext.setEnabled(false);
				}else {
					btnNext.setEnabled(true);
					btnBack.setEnabled(false);
				}
			}
		});
		btnNext.setFocusPainted(false);
		btnNext.setEnabled(false);
		add(btnNext);

		btnBack.setPreferredSize(new Dimension(30, 25));
		btnBack.setBounds(140,320, 30, 25);
		btnBack.setBackground(new Color(228,228,228));
		btnBack.setMargin(new Insets(1,1,1,1));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mode > 3) {
					mode--;
					setText(mode);
				}
				if (mode == 3) {
					btnBack.setEnabled(false);
					btnNext.setEnabled(true);
				}else {
					btnNext.setEnabled(false);
					btnBack.setEnabled(true);
				}
			}
		});
		btnBack.setFocusPainted(false);
		add(btnBack);
		

		btnShow.setPreferredSize(new Dimension(100, 25));
		btnShow.setBounds(140,310, 100, 25);
		btnShow.setBackground(new Color(228,228,228));
		btnShow.setMargin(new Insets(1,1,1,1));
		btnShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (mode) {
				case 1:
					if (main.getChesssize() == 4) {
					    JOptionPane.showMessageDialog(main, "Šachovnice 4x4 nemá øešení jezdcovy procházky. :)", "Chyták!", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					main.clear();
					main.setMode(3);
					main.switchGame(main.getChesssize());
					break;
				case 2:
					main.showSolution();
					btnShow.setEnabled(false);
					break;

				default:
					break;
				}
			}
		});
		btnShow.setFocusPainted(false);
		add(btnShow);

		btnTry.setPreferredSize(new Dimension(100, 25));
		btnTry.setBounds(140,310, 100, 25);
		btnTry.setBackground(new Color(228,228,228));
		btnTry.setMargin(new Insets(1,1,1,1));
		btnTry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mode == 5) {
					main.setMode(1);
					main.switchGame(main.getChesssize());
				}
			}
		});
		btnTry.setFocusPainted(false);
		add(btnTry);
		
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
		this.mode=s;
		switch (s) {
		case 1:
			jLabel.setVisible(true);
			jLabel1.setVisible(false);
			jLabel2.setVisible(false);
			btnBack.setVisible(false);
			btnNext.setVisible(false);
			btnShow.setVisible(true);
			btnTry.setVisible(false);
			text1.setText(" \n - Projeïte kadé pole šachovnice. \n \n - Konì pøesunete tahem \n nebo kliknutím na zeléné pole\n \n - Kadé pole musíte navštívit právì 1x. \n \n - Krok zpìt naleznete na dolní lištì.");
			break;
		case 2:
			jLabel.setVisible(false);
			jLabel1.setVisible(true);
			jLabel2.setVisible(false);
			btnBack.setVisible(false);
			btnNext.setVisible(false);
			btnShow.setVisible(true);
			btnTry.setVisible(false);
			text1.setText(" \n - Umístìte konì na šachovnici, \n tak aby kadé pole bylo kryto. \n \n - Kryté pole oznaèeno èervenì. \n \n - Máte omezenı poèet koní. ( " + limit + " ) \n \n"
					+ " - Pøidání konì naleznete na dolní lištì.");
			break;
		case 3:
			if (!podm) {
				jLabel.setVisible(false);
				jLabel1.setVisible(false);
				jLabel2.setVisible(true);
				btnBack.setVisible(false);
				btnNext.setVisible(false);
				btnShow.setVisible(false);
				btnTry.setVisible(false);
			}
			text1.setText(" \n - Spojte body pomocí hran. \n \n - Navštivte kadı bod \n a vrate se do poèáteèního bodu.\n \n "
					+ " - Krok zpìt naleznete na dolní lištì.");
			break;
		case 4:
			btnBack.setVisible(true);
			btnNext.setVisible(true);
			btnShow.setVisible(false);
			btnTry.setVisible(false);
			podm = true;
			text1.setText("- Bílé hrany oznaèují moné cesty. \n \n - Plné èáry jsou povinné. \n \n "
					+ " - Èárkované jsou nepovinné. ");
			break;
		case 5:
			jLabel.setVisible(true);
			jLabel1.setVisible(false);
			jLabel2.setVisible(false);
			btnBack.setVisible(false);
			btnNext.setVisible(false);
			btnShow.setVisible(false);
			btnTry.setVisible(true);
			text1.setText(" \n \n - Vygenerované øešení. \n \n - Èervené èáry znaèí jezdcovu cestu. \n \n - Jedná se o otevøené øešení úlohy.");
			break;
		default:
			break;
		}
	}

	public void limitHorses(int size) {
		this.limit = size;
		switch (size) {
		case 6:
			this.limit = 3;
			break;
		case 7:
			this.limit = 4;
			break;
		case 8:
			this.limit = 4;
			break;
		default:
			break;
		}
	}

	public void setMain(MainWindow mainWindow) {
		this.main = mainWindow;
	}
    
}