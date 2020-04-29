package cz.uhk.diplom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import cz.uhk.diplom.prochazka.KnightTest2;
import cz.uhk.diplom.prochazka.KnightsTour;
import cz.uhk.diplom.prochazka.NeuralNetworkTour;

public class MainMenu extends JFrame implements ActionListener {
	JButton b0, b1, b2, b3, b4, b5, b7, b8, b9, b10, b11, b12, b13, bZpet;
	JLabel l1;
	JPanel panel1;
	MainWindow mainWindow;
	private List<JButton> buttons;
	private int mode;

	public MainMenu(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		buttons = new ArrayList<>();
		setTitle("CheGra");
		setSize(672, 470);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		try {
			setContentPane(
					new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/textures/chessmenu.png")))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setLayout(null);
		l1 = new JLabel("Jezdcova procházka");

		bZpet = new JButton("Zpìt");
		bZpet.setPreferredSize(new Dimension(100, 25));
		bZpet.addActionListener(this);
		bZpet.setBounds(15, 400, 50, 25);

		b0 = new JButton("Popis problému");
		b0.setPreferredSize(new Dimension(100, 30));
		b0.addActionListener(this);
		b0.setBounds(75, 150, 150, 30);

		b1 = new JButton("Najdi procházku");
		b1.setPreferredSize(new Dimension(100, 30));
		b1.addActionListener(this);
		b1.setBounds(75, 190, 150, 30);

		b3 = new JButton("Simulace");
		b3.setPreferredSize(new Dimension(100, 30));
		b3.addActionListener(this);
		b3.setBounds(75, 230, 150, 30);

		b2 = new JButton("Krycí hra");
		b2.setPreferredSize(new Dimension(100, 30));
		b2.addActionListener(this);
		b2.setBounds(75, 270, 150, 30);

		b4 = new JButton("Generate");
		b4.setPreferredSize(new Dimension(100, 30));
		b4.addActionListener(this);
		b4.setBounds(75, 310, 150, 30);

		b5 = new JButton("Hra Hamilton");
		b5.setPreferredSize(new Dimension(100, 30));
		b5.addActionListener(this);
		b5.setBounds(75, 350, 150, 30);
		// pro studenty
		// b5.setBounds(75, 310, 150, 30);

		b10 = new JButton("Neural network");
		b10.setPreferredSize(new Dimension(100, 30));
		b10.addActionListener(this);
		b10.setBounds(75, 390, 150, 30);

		l1.setPreferredSize(new Dimension(450, 50));
		l1.setBounds(25, 55, 450, 50);
		l1.setFont(new Font("times new roman", Font.BOLD, 45));
		l1.setForeground(new Color(49, 49, 49));

		add(l1);
		add(bZpet);
		add(b0);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b10);

		b7 = new JButton("4x4");
		b7.setPreferredSize(new Dimension(100, 30));
		b7.addActionListener(this);
		b7.setBounds(75, 190, 120, 30);

		b8 = new JButton("5x5");
		b8.setPreferredSize(new Dimension(100, 30));
		b8.addActionListener(this);
		b8.setBounds(75, 230, 120, 30);

		b9 = new JButton("6x6");
		b9.setPreferredSize(new Dimension(100, 30));
		b9.addActionListener(this);
		b9.setBounds(75, 270, 120, 30);

		b11 = new JButton("Level 1");
		b11.setPreferredSize(new Dimension(100, 30));
		b11.addActionListener(this);
		b11.setBounds(75, 230, 120, 30);

		b12 = new JButton("Level 2");
		b12.setPreferredSize(new Dimension(100, 30));
		b12.addActionListener(this);
		b12.setBounds(75, 270, 120, 30);

		b13 = new JButton("Level 3");
		b13.setPreferredSize(new Dimension(100, 30));
		b13.addActionListener(this);
		b13.setBounds(75, 310, 120, 30);

		bZpet.setVisible(false);
		b7.setVisible(false);
		b8.setVisible(false);
		b9.setVisible(false);
		b11.setVisible(false);
		b12.setVisible(false);
		b13.setVisible(false);
		add(b7);
		add(b8);
		add(b9);
		add(b11);
		add(b12);
		add(b13);

		buttons.add(bZpet);
		buttons.add(b0);
		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
		buttons.add(b4);
		buttons.add(b5);
		buttons.add(b7);
		buttons.add(b8);
		buttons.add(b9);
		buttons.add(b10);
		buttons.add(b11);
		buttons.add(b12);
		buttons.add(b13);

		for (JButton jButton : buttons) {
			jButton.setOpaque(true);
			jButton.setContentAreaFilled(true);
			jButton.setBorderPainted(true);
			jButton.setFont(new Font("times new roman", Font.PLAIN, 20));
			jButton.setBorder(new LineBorder(new Color(139, 69, 19)));
			jButton.setForeground(new Color(218, 165, 32));
			jButton.setBackground(new Color(49, 47, 44));
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					jButton.setForeground(new Color(51, 51, 51));
					jButton.setBackground(new Color(218, 165, 32));
				}

				public void mouseExited(java.awt.event.MouseEvent evt) {
					jButton.setForeground(new Color(218, 165, 32));
					jButton.setBackground(new Color(49, 47, 44));
				}
			});

			jButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		setSize(671, 469);
		setSize(672, 470);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bZpet) {
			this.dispose();
			mainWindow.openMenu(mainWindow);
		} else if (e.getSource() == b0) {
			this.dispose();
			mode = 0;
			mainWindow.setMode(mode);
			mainWindow.switchGame(0);
		} else if (e.getSource() == b1) {
			mode = 1;
			bZpet.setVisible(true);
			b0.setVisible(false);
			b1.setVisible(false);
			b2.setVisible(false);
			b3.setVisible(false);
			b4.setVisible(false);
			b5.setVisible(false);
			b10.setVisible(false);

			b7.setVisible(true);
			b8.setVisible(true);
			b9.setVisible(true);
		} else if (e.getSource() == b2) {
			mode = 2;
			bZpet.setVisible(true);
			b0.setVisible(false);
			b1.setVisible(false);
			b2.setVisible(false);
			b3.setVisible(false);
			b4.setVisible(false);
			b5.setVisible(false);
			b10.setVisible(false);

			b7.setVisible(true);
			b7.setBounds(75, 150, 120, 30);
			b8.setVisible(true);
			b8.setBounds(75, 190, 120, 30);
			b11.setVisible(true);
			b12.setVisible(true);
			b13.setVisible(true);
		} else if (e.getSource() == b3) {
			mode = 3;
			bZpet.setVisible(true);
			b0.setVisible(false);
			b1.setVisible(false);
			b2.setVisible(false);
			b3.setVisible(false);
			b4.setVisible(false);
			b5.setVisible(false);
			b10.setVisible(false);

			b7.setVisible(true);
			b8.setVisible(true);
			b9.setVisible(true);
		} else if (e.getSource() == b4) {
			mode = 4;
			this.dispose();
			boolean warnsdorff = false;
			boolean lichost = false;

			String[] options3 = { "Backtracking", "Warnsdorffùv algoritmus" };
			String n3 = (String) JOptionPane.showInputDialog(null, "Který algoritmus??", "Zvolte algoritmus.",
					JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]);
			if (n3 == "Warnsdorffùv algoritmus") {
				warnsdorff = true;
			} else if (n3 == "Backtracking") {
				warnsdorff = false;
			} else {
				mainWindow.openMenu(mainWindow);
				mainWindow.setMode(mode);
				return;
			}

			String[] options = { "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
			String[] options1 = { "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
			JPanel myPanel;
			JComboBox<String> c = null;
			JComboBox<String> c1 = null;
			JTextField t = null;
			int result, jml1, jml = 0;
			while (true) {
				myPanel = new JPanel();
				if (warnsdorff) {
					myPanel.add(new JLabel("Šachovnice NxN ... N = "));
					t = new JTextField(4);
					t.setRequestFocusEnabled(true);
					myPanel.add(t);
				} else {
					myPanel.add(new JLabel("Výška šachovnice: "));
					c = new JComboBox<>(options);
					c1 = new JComboBox<>(options1);
					myPanel.add(c);
					myPanel.add(Box.createHorizontalStrut(25));
					myPanel.add(new JLabel("Šíøka šachovnice: "));
					myPanel.add(c1);
				}

				result = JOptionPane.showConfirmDialog(null, myPanel, "Velikost šachovnice",
						JOptionPane.OK_CANCEL_OPTION);

				if (result == 2 || result == -1) {
					break;
				}
				if (warnsdorff && t.getText() != null) {
					jml = Integer.parseInt(t.getText());
					if (jml > 0) {
						if (jml < 5) {
							JOptionPane.showMessageDialog(this,
									"Pøíliš malá šachovnice, musí být vìtší než 4x4. Na této šachovnici neexistuje jezdcova procházka.",
									"Chyba", JOptionPane.ERROR_MESSAGE);
						} else if (jml > 35) {
							JPanel jPanel = new JPanel();
							jPanel.add(new JLabel(
									"Jedná se o velkou šachovnici pro Warnsdorffa. Mùže dojít k zacyklení algoritmu. V takovém pøípadì bude nutné ukonèit program pomocí správce úloh!!!"
											+ " Pokraèovat?"));
							Object[] optionsContinue = { "Ano", "Zmìnit šachovnici" };
							int result1 = JOptionPane.showOptionDialog(null, jPanel, "!!! Upozornìní !!!",
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
									optionsContinue, null);
							if (result1 != 1 && result1 != -1) {
								break;
							}
						} else {
							break;
						}
					} else {
						JOptionPane.showMessageDialog(this, "Zadejte kladná nenulová èísla.", "Chyba",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (!warnsdorff) {
					if (c.getSelectedIndex() + 3 < 5 && c1.getSelectedIndex() + 3 < 5) {
						JOptionPane.showMessageDialog(this,
								"Pøíliš malá šachovnice, musí být vìtší než 4x4. Na této šachovnici neexistuje øešení.", "Chyba",
								JOptionPane.ERROR_MESSAGE);
					} else if ((c.getSelectedIndex() + 3 > 8 && c1.getSelectedIndex() + 3 > 8)
							|| (c1.getSelectedIndex() + c.getSelectedIndex() + 6 > 16)
							|| ((c1.getSelectedIndex() + c.getSelectedIndex() + 6 == 16)
									&& c.getSelectedIndex() - c1.getSelectedIndex() > 4)) {
						JOptionPane.showMessageDialog(this, "Šachovnice nesmí být vìtší než 8x8 nebo 10x6",
								"Chyba", JOptionPane.ERROR_MESSAGE);
					} else if (c.getSelectedIndex() + 3 > 6 && c1.getSelectedIndex() + 3 > 6) {
						JPanel jPanel = new JPanel();
						jPanel.add(new JLabel(
								"Chvilku to bude trvat, ale øešení se vygenerují. Poèet øešení zadejte s rozumem."
										+ " Pokraèovat?"));
						Object[] optionsContinue = { "Ano", "Zmìnit šachovnici" };
						int result1 = JOptionPane.showOptionDialog(null, jPanel, "!!! Upozornìní !!!",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, optionsContinue,
								null);
						if (result1 != 1 && result1 != -1) {
							break;
						}
					} else {
						break;
					}
				}
			}

			// Warnsdorff, na lichých nejsou uzavøené øešení
			if (warnsdorff && jml % 2 != 0) {
				lichost = true;
			} else if (!warnsdorff && (c.getSelectedIndex() + 3) % 2 != 0
					&& c.getSelectedIndex() == c1.getSelectedIndex()) {
				lichost = true;
			}

			if (result != 2 && result != -1) {
				String[] options2 = { "otevøené cesty", "uzavøené cesty" };
				String[] optionsL = { "otevøené cesty" };

				String n2 = null;
				if (lichost) {
					n2 = (String) JOptionPane.showInputDialog(null,
							"V tomto rozmìru šachovnice existují jen otevøená øešení.", "Typ cesty",
							JOptionPane.QUESTION_MESSAGE, null, optionsL, optionsL[0]);
				} else {
					n2 = (String) JOptionPane.showInputDialog(null, "Typ cesty??", "Typ cesty",
							JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
				}

				if (n2 != null) {
					if (warnsdorff) {
						boolean podm = true;
						while (podm) {
							myPanel = new JPanel();
							myPanel.add(new JLabel("Poèet: "));
							JTextField t1 = new JTextField(4);
							t1.setRequestFocusEnabled(true);
							myPanel.add(t1);
							result = JOptionPane.showConfirmDialog(null, myPanel, "Kolik šachovnic hledat",
									JOptionPane.OK_CANCEL_OPTION);

							if (result != 2 && result != -1) {
								if (t1.getText() != null) {
									jml1 = Integer.parseInt(t1.getText());

									if (jml1 > 0) {
										KnightTest2 knightTest2 = new KnightTest2();
										knightTest2.Main(jml, n2, jml1);
										break;
									} else {
										JOptionPane.showMessageDialog(this, "Zadejte kladná nenulová èísla.", "Chyba",
												JOptionPane.ERROR_MESSAGE);
									}
								}
							} else {
								break;
							}
						}
					} else {
						boolean podm = true;
						while (podm) {
							myPanel = new JPanel();
							myPanel.add(new JLabel("Poèet: "));
							JTextField t1 = new JTextField(4);
							t1.setRequestFocusEnabled(true);
							myPanel.add(t1);
							result = JOptionPane.showConfirmDialog(null, myPanel, "Kolik šachovnic hledat",
									JOptionPane.OK_CANCEL_OPTION);

							if (result != 2 && result != -1) {
								if (t1.getText() != null) {
									jml1 = Integer.parseInt(t1.getText());

									if (jml1 > 0) {
										KnightsTour kt = new KnightsTour(c.getSelectedIndex() + 3,
												c1.getSelectedIndex() + 3, n2, jml1);
										break;
									} else {
										JOptionPane.showMessageDialog(this, "Zadejte kladná nenulová èísla.", "Chyba",
												JOptionPane.ERROR_MESSAGE);
									}
								}
							} else {
								break;
							}
						}
					}
					MainWindow.openMenu(mainWindow);
				} else {
					mainWindow.openMenu(mainWindow);
				}
			} else if (result == 2 || result == -1) {
				mainWindow.openMenu(mainWindow);
			}
		} else if (e.getSource() == b5) {
			this.dispose();
			mode = 5;
			mainWindow.setMode(mode);
			mainWindow.switchGame(0);
		} else if (e.getSource() == b7) {
			this.dispose();
			mainWindow.switchGame(4);
		} else if (e.getSource() == b8) {
			this.dispose();
			mainWindow.switchGame(5);
		} else if (e.getSource() == b9) {
			this.dispose();
			mainWindow.switchGame(6);
		} else if (e.getSource() == b10) {
			this.dispose();
			mode = 6;
			NeuralNetworkTour form = new NeuralNetworkTour();

			boolean poc = true;
			while (true) {
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Výška šachovnice: "));
				JTextField t1 = new JTextField(4);
				t1.setRequestFocusEnabled(true);
				myPanel.add(t1);
				myPanel.add(Box.createHorizontalStrut(25));
				myPanel.add(new JLabel("Šíøka šachovnice: "));
				JTextField t2 = new JTextField(4);
				myPanel.add(t2);

				int result = JOptionPane.showConfirmDialog(null, myPanel, "Velikost šachovnice",
						JOptionPane.OK_CANCEL_OPTION);

				if (result != 2 && result != -1) {
					if (t1.getText() != null && t2.getText() != null) {
						int jml1 = Integer.parseInt(t1.getText());
						int jml2 = Integer.parseInt(t2.getText());

						if (jml1 > 0 && jml2 > 0) {
							if (jml1 % 2 != 0 && jml2 % 2 != 0) {
								JOptionPane.showMessageDialog(this, "Oba rozmìry nemohou být lichá èísla.", "Chyba",
										JOptionPane.ERROR_MESSAGE);
							} else {
								if (jml1 < 5 && jml2 < 5) {
									JOptionPane.showMessageDialog(this,
											"Pøíliš malá šachovnice, musí být vìtší než 4x4. Na této šachovnici neexistuje øešení.", "Chyba",
											JOptionPane.ERROR_MESSAGE);
								} else if (jml1 > 23 && jml2 > 23) {
									JPanel jPanel = new JPanel();
									jPanel.add(new JLabel(
											"Chvilku to bude trvat, ale øešení se vygeneruje (Jedno øešení šachovnice 26x26 - cca 25 minut ; 28x28 - 2 hodiny). Vypnutí pouze pomocí správce úloh."
													+ " Pokraèovat?"));
									Object[] optionsContinue = { "Ano", "Zmìnit šachovnici" };
									int result1 = JOptionPane.showOptionDialog(null, jPanel,
											"!!! Upozornìní - SILNÌ NEDOPORUÈUJEME !!!",
											JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
											optionsContinue, null);
									if (result1 != 1 && result1 != -1) {
										break;
									}
								} else if (jml1 > 19 || jml2 > 19) {
									JPanel jPanel = new JPanel();
									jPanel.add(new JLabel(
											"Chvilku to bude trvat, ale øešení se vygeneruje (Jedno øešení šachovnice 26x26 - cca 25 minut)."
													+ " Pokraèovat?"));
									Object[] optionsContinue = { "Ano", "Zmìnit šachovnici" };
									int result1 = JOptionPane.showOptionDialog(null, jPanel, "!!! Upozornìní !!!",
											JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
											optionsContinue, null);
									if (result1 != 1 && result1 != -1) {
										break;
									}
								} else {
									boolean podm = true;
									while (poc) {
										myPanel = new JPanel();
										myPanel.add(new JLabel("Poèet: "));
										t1 = new JTextField(4);
										t1.setRequestFocusEnabled(true);
										myPanel.add(t1);

										result = JOptionPane.showConfirmDialog(null, myPanel, "Kolik šachovnic hledat",
												JOptionPane.OK_CANCEL_OPTION);

										if (result != 2 && result != -1) {
											if (t1.getText() != null) {
												jml1 = Integer.parseInt(t1.getText());

												if (jml1 > 0) {
													form.setNumberOfBoards(jml1);
													poc = false;
													break;
												} else {
													JOptionPane.showMessageDialog(this, "Zadejte kladné èíslo.",
															"Chyba", JOptionPane.ERROR_MESSAGE);
												}
											}
										} else {
											podm = false;
											break;
										}
									}
									if (!podm) {
										mainWindow.openMenu(mainWindow);
										break;
									}
									if (jml1 > jml2) {
										form.cmdGoClick(mainWindow, jml1, jml2);
										break;
									} else if (jml1 == jml2 && jml1 % 2 == 0 && jml2 % 2 == 0) {
										form.cmdGoClick(mainWindow, jml1, jml2);
										JOptionPane.showMessageDialog(this,
												"Vaše øešení (" + form.getNumberOfBoards() + ") byla nalezeny. \n "
														+ "Najdete je v souboru solutions.txt ve složce s programem",
												"Úspìch", JOptionPane.INFORMATION_MESSAGE);
										break;
									} else if (jml1 < jml2) {
										form.cmdGoClick(mainWindow, jml2, jml1);
										JOptionPane.showMessageDialog(this,
												"Vaše øešení (" + form.getNumberOfBoards() + ") byla nalezeny. \n "
														+ "Najdete je v souboru solutions.txt ve složce s programem",
												"Úspìch", JOptionPane.INFORMATION_MESSAGE);
										break;
									} else {
										JOptionPane.showMessageDialog(this,
												"Ètvercové šachovnice musí mít sudé velikosti.", "Chyba",
												JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						} else {
							JOptionPane.showMessageDialog(this, "Zadejte nenulová èísla.", "Chyba",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					mainWindow.openMenu(mainWindow);
					break;
				}
			}

			mainWindow.setMenuEnable(true);
		} else if (e.getSource() == b11) {
			this.dispose();
			mainWindow.switchGame(6);
		} else if (e.getSource() == b12) {
			this.dispose();
			mainWindow.switchGame(7);
		} else if (e.getSource() == b13) {
			this.dispose();
			mainWindow.switchGame(8);
		}
		mainWindow.setMode(mode);
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

}
