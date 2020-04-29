package cz.uhk.diplom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI {
	public static JFrame frame;
	private static ButtonGroup effectsGroup;
	static JPanel p;
	JMenuItem kun = new JMenuItem("    Nový kùn    ");
	JMenuItem zpet = new JMenuItem("    Krok zpìt    ");
	JMenuItem hranaZpet = new JMenuItem("    Krok zpìt    ");
	JMenuBar nabidka = new JMenuBar();
	JMenuBar jMenuBar;
	JMenu soubor = new JMenu("  Soubor  ");
	JMenu sachovnice = new JMenu("  Zmìnit šachovnici  ");
	JMenu mainMenu = new JMenu("  Hlavní menu  ");
	JCheckBoxMenuItem chessLevel1, chessLevel2, chessLevel3, chess6x6;

	public JMenuBar createGUI(MainWindow frame, int width, int height) {
		setButtonsFunctions(frame);

		frame.setBackground(Color.WHITE);

		java.awt.Image icon = null;
		try {
			icon = ImageIO.read(getClass().getResource("/textures/kone1.png"));
		} catch (IOException ex) {
		}
		frame.setIconImage(icon);

		// Hlavní menu
		nabidka.setPreferredSize(new Dimension(width, 30));

		// Soubor
		soubor.setFont(new Font("times new roman", Font.PLAIN, 16));

		// About
		JMenuItem aboutApp = new JMenuItem("O programu");
		try {
			aboutApp.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/textures/info.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		aboutApp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "CheGra.\n\n\n"
						+ "Program vytvoøen pro úèely FIM UHK.\nImplementovány hry založené na matematické oblasti teorie grafù."
						+ "\nObsahuje popis problému jezdcovy procházky s názornými pøíklady jeho øešení."
						+ "\n\n\n© 2020 Radim Krátký\nUniverzita Hradec Králové\nFakulta informatiky a managementu\nAplikovaná informatika",
						"O programu", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Exit
		JMenuItem exitApp = new JMenuItem("Zavøít program");
		try {
			exitApp.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/textures/close.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		exitApp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		soubor.add(aboutApp);
		soubor.addSeparator();
		soubor.add(exitApp);
		soubor.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Sachovnice
		sachovnice.setFont(new Font("times new roman", Font.PLAIN, 16));
		sachovnice.setCursor(new Cursor(Cursor.HAND_CURSOR));
		effectsGroup = new ButtonGroup();

		JCheckBoxMenuItem chess4x4 = new JCheckBoxMenuItem("   4x4   ");
		chess4x4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(4);
			}
		});
		sachovnice.add(chess4x4);
		effectsGroup.add(chess4x4);

		JCheckBoxMenuItem chess5x5 = new JCheckBoxMenuItem("   5x5   ");
		chess5x5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(5);
			}
		});
		sachovnice.add(chess5x5);
		effectsGroup.add(chess5x5);

		chess6x6 = new JCheckBoxMenuItem("   6x6   ");
		chess6x6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(6);
			}
		});
		sachovnice.add(chess6x6);
		effectsGroup.add(chess6x6);

		chessLevel1 = new JCheckBoxMenuItem("   Level 1   ");
		chessLevel1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(6);
			}
		});
		chessLevel1.setVisible(false);
		sachovnice.add(chessLevel1);
		effectsGroup.add(chessLevel1);

		chessLevel2 = new JCheckBoxMenuItem("   Level 2   ");
		chessLevel2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(7);
			}
		});
		chessLevel2.setVisible(false);
		sachovnice.add(chessLevel2);
		effectsGroup.add(chessLevel2);

		chessLevel3 = new JCheckBoxMenuItem("   Level 3   ");
		chessLevel3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(8);
			}
		});
		sachovnice.add(chessLevel3);
		effectsGroup.add(chessLevel3);

		// Hlavni nabidka
		mainMenu.setFont(new Font("times new roman", Font.PLAIN, 16));
		mainMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainMenu.isEnabled()) {
					frame.hideFrames();
					enabledMenu(false);
					frame.endGame();
					MainMenu main = new MainMenu(frame);
					main.setVisible(true);
					main.setAlwaysOnTop(true);
					main.setFocusableWindowState(false);
				}
			}
		});

		nabidka.add(soubor);
		nabidka.add(sachovnice);
		nabidka.add(mainMenu);
		nabidka.add(Box.createHorizontalGlue());

		return nabidka;
	}

	private void setButtonsFunctions(MainWindow frame) {

		hranaZpet.setFont(new Font("times new roman", Font.PLAIN, 16));
		hranaZpet.setCursor(new Cursor(Cursor.HAND_CURSOR));

		hranaZpet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.hranaZpet();
			}
		});

		zpet.setFont(new Font("times new roman", Font.PLAIN, 16));
		zpet.setCursor(new Cursor(Cursor.HAND_CURSOR));

		zpet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.krokZpet();
			}
		});

		kun.setFont(new Font("times new roman", Font.PLAIN, 16));
		kun.setCursor(new Cursor(Cursor.HAND_CURSOR));

		kun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.pridatKone();
			}
		});
	}

	public JMenuBar changeBottomMenu(int game, JMenuBar jMenuBar, MainWindow frame) {
		this.jMenuBar = jMenuBar;
		if (game == 0) {
			jMenuBar.remove(kun);
			jMenuBar.remove(hranaZpet);
			jMenuBar.remove(zpet);
		} else if (game == 1) {
			jMenuBar.remove(kun);
			jMenuBar.remove(hranaZpet);
			jMenuBar.add(zpet);
			chessLevel1.setVisible(false);
			chessLevel2.setVisible(false);
			chessLevel3.setVisible(false);
			chess6x6.setVisible(true);
		} else if (game == 2) {
			jMenuBar.remove(zpet);
			jMenuBar.add(kun);
			jMenuBar.remove(hranaZpet);
			chessLevel1.setVisible(true);
			chessLevel2.setVisible(true);
			chessLevel3.setVisible(true);
			chess6x6.setVisible(false);
		} else if (game == 3) {
			jMenuBar.remove(zpet);
			jMenuBar.remove(kun);
			jMenuBar.remove(hranaZpet);
		} else if (game == 5) {
			jMenuBar.remove(zpet);
			jMenuBar.remove(kun);
			jMenuBar.add(hranaZpet);
		} else if (game == 6) {
			jMenuBar.remove(zpet);
			jMenuBar.remove(kun);
			jMenuBar.remove(hranaZpet);
		}
		return jMenuBar;
	}

	public void enabledMenu(boolean enable) {
		this.soubor.setEnabled(enable);
		this.sachovnice.setEnabled(enable);
		this.mainMenu.setEnabled(enable);
		this.kun.setEnabled(enable);
		this.hranaZpet.setEnabled(enable);
		this.zpet.setEnabled(enable);
	}

	public void clearButtonSelection() {
		effectsGroup.clearSelection();
	}

	public void enableChessSize(boolean b) {
		sachovnice.setEnabled(b);
	}
}
