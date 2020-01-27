package cz.uhk.diplom.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import cz.uhk.diplom.MainWindow;

public class TheoryTable extends JTextArea {

    private BufferedImage img, img1, img2;
    JTextArea jLabel, jt, jt1;
    int size, mode = 0, a, b;
	JButton btnNext = new JButton(">");
	JButton btnBack = new JButton("<");
	JButton btnAnimate = new JButton("Animace");
	MainWindow main;

    public TheoryTable(int a, int b) {
        super(a,b);
        this.a = a;
        this.b = b;

        try{
    		img= ImageIO.read(getClass().getResourceAsStream("/textures/tabule.jpg"));
    		img1= ImageIO.read(getClass().getResourceAsStream("/textures/moves.jpg"));
    		img2= ImageIO.read(getClass().getResourceAsStream("/textures/knightTour.png"));
        } catch(IOException e) {
            System.out.println(e.toString());
        }
        
        setSize(a, b);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
        setBounds((int) (((width / 2) - 500)), (int) (((height / 2) - 330)), a, b);
		setVisible(true);
		//setBorder(BorderFactory.createSoftBevelBorder(0, Color.WHITE, Color.BLACK));
		setEditable(false);
		setFocusable(false);
		
		JLabel l1 = new JLabel("Jezdcova procházka");

		l1.setPreferredSize(new Dimension(450, 50));
		l1.setBounds(55, 25, 450, 50);
		l1.setFont(new Font("times new roman", Font.BOLD, 45));
		l1.setForeground(new Color(221,197,141));
		add(l1);
		
		jt = new JTextArea() {
			@Override
	        public void paintComponent(Graphics g) {
	            g.drawImage(img2, 0, 0, null);
	            super.paintComponents(g);
	        }
		};
		jt.setSize(220, 220);
        jt.setBounds(700, 50, 220, 220);
		jt.setVisible(true);
		jt.setEditable(false);
		add(jt);

		jt1 = new JTextArea() {
			@Override
	        public void paintComponent(Graphics g) {
	            g.drawImage(img1, 0, 0, null);
	            super.paintComponents(g);
	        }
		};
		jt1.setSize(220, 220);
        jt1.setBounds(700, 50, 220, 220);
		jt1.setVisible(true);
		jt1.setEditable(false);
		add(jt1);
		
		btnNext.setPreferredSize(new Dimension(35, 25));
		btnNext.setBounds(256,332, 35, 25);
		btnNext.setBackground(new Color(221,197,141));
		btnNext.setMargin(new Insets(1,1,1,1));
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mode < 2) {
					mode++;
					setText();
					main.changeTheoryPicture(mode);
				}
				if (mode == 2) {
					btnNext.setEnabled(false);
					btnAnimate.setVisible(true);
				}else {
					btnNext.setEnabled(true);
					btnBack.setEnabled(true);
					btnAnimate.setVisible(false);
				}
			}
		});
		btnNext.setFocusPainted(false);
		add(btnNext);

		btnBack.setPreferredSize(new Dimension(35, 25));
		btnBack.setBounds(219,332, 35, 25);
		btnBack.setBackground(new Color(221,197,141));
		btnBack.setMargin(new Insets(1,1,1,1));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mode > 0) {
					mode--;
					setText();
					main.changeTheoryPicture(mode);
				}
				if (mode == 0) {
					btnBack.setEnabled(false);
				}else {
					btnNext.setEnabled(true);
					btnBack.setEnabled(true);
				}
				btnAnimate.setVisible(false);
			}
		});
		btnBack.setFocusPainted(false);
		btnBack.setEnabled(false);
		add(btnBack);

		btnAnimate.setPreferredSize(new Dimension(75, 25));
		btnAnimate.setBounds(400,332, 75, 25);
		btnAnimate.setBackground(new Color(221,197,141));
		btnAnimate.setMargin(new Insets(1,1,1,1));
		btnAnimate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAnimate.setEnabled(false);
				btnBack.setEnabled(false);
				mode++;
				main.changeTheoryPicture(mode);
				mode--;
				btnBack.setEnabled(true);
				btnAnimate.setEnabled(true);
			}
		});
		btnAnimate.setFocusPainted(false);
		btnAnimate.setVisible(false);
		add(btnAnimate);
		
		jLabel = new JTextArea();
		jLabel.setOpaque(false);
		jLabel.setBorder(null);
		jLabel.setEditable(false);
		jLabel.setEnabled(false);
		jLabel.setBackground(new Color(1,1,1, (float) 0.0001));
		jLabel.setDisabledTextColor(Color.WHITE);

		jLabel.setFont(new Font("courier", Font.ITALIC, 16));
		jLabel.setBounds(70,100,250,200);
		jLabel.setSize(400,200);
		add(jLabel);
		setText();
		
		setDisabledTextColor(Color.WHITE);
		setEnabled(false);
		setBorder(BorderFactory.createCompoundBorder(
		        getBorder(), 
		        BorderFactory.createEmptyBorder(105, 80, 60, 60)));
		setBackground(new Color(1,1,1, (float) 0.0001));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img,0,0,null);
        super.paintComponent(g);
    }

	public void setText() {
		switch (mode) {
		case 0:
			jLabel.setText(" Cíl: \n \n - Kùò se pohybuje po prázdné šachovnici \n "
					+ "a jeho úkolem je, aby navštívil každé pole šachovnice. \n \n - Každé pole musí navštívit právì 1x.");

	        jt.setBounds(700, 50, 220, 220);
	        jt1.setBounds(900, 900, 220, 220);
			break;
		case 1:
			jLabel.setText(" Pohyb: \n \n - Kùò se mùže pohybovat dle šachových pravidel. \n \n - Tedy do písmene L. \n \n - Nesmí vstoupit na již navštivené pole.");

	        jt1.setBounds(700, 50, 220, 220);
	        jt.setBounds(900, 900, 220, 220);
			break;
		case 2:
			jLabel.setText(" Øešení: \n \n - Øešení lze nalézt na rùznì velkých šachovnicích. \n \n - Napøíklad na šachovnici 5x5 existuje 1 768 øešení. \n \n - Dokážete nalézt alespoò jedno?");
			break;
		default:
			break;
		}
	}

	public void limitHorses(int size) {
		this.size = size;
	}

	public void setMain(MainWindow mainWindow) {
		this.main = mainWindow;
	}
    
}