package cz.uhk.diplom;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI {
	public static JFrame frame;
	private static ButtonGroup effectsGroup, gameGroup;
	static JPanel p;
	JMenuItem kun = new JMenuItem("    Nový kùn    ");
	JMenuItem zpet = new JMenuItem("    Krok zpìt    ");
	JMenuItem hranaZpet = new JMenuItem("    Krok zpìt    ");
	JMenuBar nabidka = new JMenuBar();
	JMenu soubor = new JMenu("  Soubor  ");
	JMenu efekty = new JMenu("  Velikost šachovnice  ");
	JMenu mainMenu = new JMenu("  Hlavní menu  ");

	public JMenuBar createGUI(MainWindow frame, int width,int height) {
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

		// Open
		JMenuItem openFile = new JMenuItem("Otevøít obrázek");
		try {
			openFile.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/textures/open.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		openFile.setHorizontalTextPosition(JMenuItem.RIGHT);
		openFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.load();
			}
		});

		// Save
		JMenuItem saveFile = new JMenuItem("Uložit jako...");
		try {
			saveFile.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/textures/save.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		saveFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.save();
			}
		});

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
				JOptionPane.showMessageDialog(frame, "Ukázka grafických algoritmù pro zpracování obrazu.\n\n\n\n© 2018 Radim Krátký\nUniverzita Hradec Králové\nFakulta informatiky a managementu\nPGRF3 - 2. projekt","O programu",JOptionPane.INFORMATION_MESSAGE);
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

		// Settings
		JMenu settings = new JMenu("Nastaveni...");
		try {
			settings.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/textures/settings.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JCheckBox lock = new JCheckBox(" Uzamèení stran ", false);
		lock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (frame.isResizable()) {
					frame.setResizable(false);
				} else
					frame.setResizable(true);
			}
		});
		settings.add(lock);

		soubor.add(openFile);
		soubor.add(saveFile);
		soubor.addSeparator();
		soubor.add(settings);
		soubor.add(aboutApp);
		soubor.add(exitApp);

		// Effects
		efekty.setFont(new Font("times new roman", Font.PLAIN, 16));
		effectsGroup = new ButtonGroup();

		// Default
		JCheckBoxMenuItem chess3x3 = new JCheckBoxMenuItem("   3x3   ");
		chess3x3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(3);
			}
		});
		efekty.add(chess3x3);
		effectsGroup.add(chess3x3);

		// Brightness
		JCheckBoxMenuItem chess4x4 = new JCheckBoxMenuItem("   4x4   ");
		chess4x4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(4);
			}
		});
		efekty.add(chess4x4);
		effectsGroup.add(chess4x4);

		// Brightness
		JCheckBoxMenuItem chess5x5 = new JCheckBoxMenuItem("   5x5   ");
		chess5x5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(5);
			}
		});
		efekty.add(chess5x5);
		effectsGroup.add(chess5x5);

		// Brightness
		JCheckBoxMenuItem chess6x6 = new JCheckBoxMenuItem("   6x6   ", true);
		chess6x6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(6);
			}
		});
		efekty.add(chess6x6);
		effectsGroup.add(chess6x6);

		mainMenu.setFont(new Font("times new roman", Font.PLAIN, 16));
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
			        enabledMenu(false);
					frame.endGame();
					MainMenu main = new MainMenu(frame);
					main.setVisible(true);
					main.setAlwaysOnTop (true);
					main.setFocusableWindowState(false);
				}
			}
		});
		
		nabidka.add(soubor);
		nabidka.add(efekty);
		nabidka.add(mainMenu);
		nabidka.add(Box.createHorizontalGlue());

		return nabidka;
	}

	private void setButtonsFunctions(MainWindow frame) {

		hranaZpet.setFont(new Font("times new roman", Font.PLAIN, 16));

		hranaZpet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.hranaZpet();
			}
		});
		
		zpet.setFont(new Font("times new roman", Font.PLAIN, 16));

		zpet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.krokZpet();
			}
		});
		
		kun.setFont(new Font("times new roman", Font.PLAIN, 16));

		kun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.pridatKone();
			}
		});
	}

	public JMenuBar changeBottomMenu(int game, JMenuBar jMenuBar, MainWindow frame) {
		if (game == 1) {
			jMenuBar.remove(kun);
			jMenuBar.remove(hranaZpet);
	        jMenuBar.add(zpet);
		} else if (game == 2) {
			jMenuBar.remove(zpet);
			jMenuBar.add(kun);
	        jMenuBar.remove(hranaZpet);
		}else if (game == 3) {
			jMenuBar.remove(zpet);
			jMenuBar.remove(kun);
	        jMenuBar.remove(hranaZpet);
		}else if (game == 5) {
			jMenuBar.remove(zpet);
			jMenuBar.remove(kun);
	        jMenuBar.add(hranaZpet);
		}
		return jMenuBar;
	}

	public void enabledMenu(boolean enable) {
		this.soubor.setEnabled(enable);
		this.efekty.setEnabled(enable);
		this.mainMenu.setEnabled(enable);
		this.kun.setEnabled(enable);
		this.hranaZpet.setEnabled(enable);
		this.zpet.setEnabled(enable);
	}
}
