package cz.uhk.diplom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;

import cz.uhk.diplom.model.Image;
import cz.uhk.diplom.prochazka.KnightsTour;
import cz.uhk.diplom.utils.Filer;

public class GUI {
	public static JFrame frame;
	private static ButtonGroup effectsGroup, gameGroup;
	static JPanel p;
	JMenuItem kun = new JMenuItem("    Nový kùn    ");
	JMenuItem zpet = new JMenuItem("    Krok zpìt    ");

	public JMenuBar createGUI(MainWindow frame, int width,int height) {
		frame.setBackground(Color.WHITE);

        java.awt.Image icon = null;
        try {
        	icon = ImageIO.read(getClass().getResource("/textures/icon4.png"));
        } catch (IOException ex) {
        }
        frame.setIconImage(icon);
		// Hlavní menu
		JMenuBar nabidka = new JMenuBar();
		nabidka.setPreferredSize(new Dimension(width, 30));
		
		// Soubor
		JMenu soubor = new JMenu("  Soubor  ");
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
		JMenu efekty = new JMenu("  Velikost šachovnice  ");
		efekty.setFont(new Font("times new roman", Font.PLAIN, 16));
		effectsGroup = new ButtonGroup();

		// Default
		JCheckBoxMenuItem chess3x3 = new JCheckBoxMenuItem("   3x3   ");
		chess3x3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setSize(1);
			}
		});
		efekty.add(chess3x3);
		effectsGroup.add(chess3x3);

		// Brightness
		JCheckBoxMenuItem chess4x4 = new JCheckBoxMenuItem("   4x4   ");
		chess4x4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setSize(2);
			}
		});
		efekty.add(chess4x4);
		effectsGroup.add(chess4x4);

		// Brightness
		JCheckBoxMenuItem chess5x5 = new JCheckBoxMenuItem("   5x5   ");
		chess5x5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setSize(3);
			}
		});
		efekty.add(chess5x5);
		effectsGroup.add(chess5x5);

		// Brightness
		JCheckBoxMenuItem chess6x6 = new JCheckBoxMenuItem("   6x6   ", true);
		chess6x6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setSize(4);
			}
		});
		efekty.add(chess6x6);
		effectsGroup.add(chess6x6);

		JMenu game = new JMenu("  Game  ");
		game.setFont(new Font("times new roman", Font.PLAIN, 16));
		gameGroup = new ButtonGroup();

		// Brightness
		JCheckBoxMenuItem defaultGame = new JCheckBoxMenuItem("Default Game", true);
		defaultGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(0, 0);
			}
		});
		game.add(defaultGame);
		gameGroup.add(defaultGame);
		
		// Brightness

		JMenu coverGame = new JMenu("  Cover Game  ");
		coverGame.setFont(new Font("times new roman", Font.PLAIN, 16));
		
		JCheckBoxMenuItem cover4x4 = new JCheckBoxMenuItem("   4x4   ");
		cover4x4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(1, 2);
			}
		});
		coverGame.add(cover4x4);
		gameGroup.add(cover4x4);

		JCheckBoxMenuItem cover5x5 = new JCheckBoxMenuItem("   5x5   ");
		cover5x5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(1, 3);
			}
		});
		coverGame.add(cover5x5);
		gameGroup.add(cover5x5);

		JCheckBoxMenuItem cover6x6 = new JCheckBoxMenuItem("   6x6   ");
		cover6x6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(1, 4);
			}
		});
		coverGame.add(cover6x6);
		gameGroup.add(cover6x6);
		

		JMenu simulation = new JMenu("  Simulation  ");
		simulation.setFont(new Font("times new roman", Font.PLAIN, 16));
		
		JCheckBoxMenuItem simulation4x4 = new JCheckBoxMenuItem("   4x4   ");
		simulation4x4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(2, 2);
			}
		});
		simulation.add(simulation4x4);
		gameGroup.add(simulation4x4);

		JCheckBoxMenuItem simulation5x5 = new JCheckBoxMenuItem("   5x5   ");
		simulation5x5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(2, 3);
			}
		});
		simulation.add(simulation5x5);
		gameGroup.add(simulation5x5);

		JCheckBoxMenuItem simulation6x6 = new JCheckBoxMenuItem("   6x6   ");
		simulation6x6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.switchGame(2, 4);
			}
		});
		simulation.add(simulation6x6);
		gameGroup.add(simulation6x6);
		

		JCheckBoxMenuItem generateSolutions = new JCheckBoxMenuItem("Generate Solutions", true);
		generateSolutions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			  String[] options = {"3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
			  String[] options1 = {"3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		      JPanel myPanel = new JPanel();
		      myPanel.add(new JLabel("Výška šachovnice: "));
		      JComboBox<String> c = new JComboBox<>(options);
		      JComboBox<String> c1 = new JComboBox<>(options1);
		      myPanel.add(c);
		      myPanel.add(Box.createHorizontalStrut(25)); // a spacer
		      myPanel.add(new JLabel("Šíøka šachovnice: "));
		      myPanel.add(c1);

		      int result = JOptionPane.showConfirmDialog(null, myPanel, 
		               "Velikost šachovnice", JOptionPane.OK_CANCEL_OPTION);
			      
		        /*
				//String[] options = {"3", "4", "5", "6"};
		        String n = (String)JOptionPane.showInputDialog(null, "Výška šachovnice??", 
		                "Výška šachovnice", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				String[] options1 = {"3", "4", "5", "6"};
		        String n1 = (String)JOptionPane.showInputDialog(null, "Šíøka šachovnice??", 
		                "Šíøka šachovnice", JOptionPane.QUESTION_MESSAGE, null, options1, options1[0]);
		        */
		      if (result != 2 && result != -1) {
				String[] options2 = {"otevøené cesty", "uzavøené cesty"};
		        String n2 = (String)JOptionPane.showInputDialog(null, "Typ cesty??", 
		                "Typ cesty", JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);

			      System.out.println(n2 + " +++++");
			      if (n2 != null) {
					KnightsTour kt = new KnightsTour(c.getSelectedIndex()+3, c1.getSelectedIndex()+3, n2);
			      }
		      }
			}
		});
		gameGroup.add(generateSolutions);
		
		game.add(coverGame);
		game.add(simulation);
		game.add(generateSolutions);
		
		nabidka.add(soubor);
		nabidka.add(efekty);
		nabidka.add(game);
		nabidka.add(Box.createHorizontalGlue());

		return nabidka;
	}

	public JMenuBar changeBottomMenu(int game, JMenuBar jMenuBar, MainWindow frame) {
		if (game == 0) {
			jMenuBar.remove(kun);

			zpet.setFont(new Font("times new roman", Font.PLAIN, 16));

			zpet.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.krokZpet();
				}
			});
	        jMenuBar.add(zpet);
		} else {
			jMenuBar.remove(zpet);
			
			kun.setFont(new Font("times new roman", Font.PLAIN, 16));

			kun.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.pridatKone();
				}
			});
	        jMenuBar.add(kun);
		}
		return jMenuBar;
	}
}
