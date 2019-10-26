package cz.uhk.diplom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cz.uhk.diplom.prochazka.KnightsTour;

public class MainMenu extends JFrame implements ActionListener {
	JButton b1, b2, b3, b4, b5, b6, b7, b8;
    JLabel l1;
    JPanel panel1;
    MainWindow mainWindow;
    private List<JButton> buttons;
    private int mode;
	
	public MainMenu(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		buttons = new ArrayList<>();
		setTitle("ChessGraph");
        setSize(672,470);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

	    setResizable(false);
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("E:\\Programy\\Eclipse\\ChessGraph\\Chess-graph\\diplom\\res\\textures\\chessmenu.png")));
        setLayout(new FlowLayout());


		setLayout(null);
        l1=new JLabel("Hlavní nabídka");
        
        b1=new JButton("Default game");
		b1.setPreferredSize(new Dimension(100,30));
        b1.addActionListener(this);
		b1.setBounds(75, 150, 120, 30);

        b2=new JButton("Cover game");
		b2.setPreferredSize(new Dimension(100,30));
        b2.addActionListener(this);
		b2.setBounds(75, 190, 120, 30);

        b3=new JButton("Simulation");
		b3.setPreferredSize(new Dimension(100,30));
        b3.addActionListener(this);
		b3.setBounds(75, 230, 120, 30);

        b4=new JButton("Generate");
		b4.setPreferredSize(new Dimension(100,30));
        b4.addActionListener(this);
		b4.setBounds(75, 270, 120, 30);

        b5=new JButton("Hamilton");
		b5.setPreferredSize(new Dimension(100,30));
        b5.addActionListener(this);
		b5.setBounds(75, 310, 120, 30);
		
		
		l1.setPreferredSize(new Dimension(450,50));
		l1.setBounds(25, 55, 450, 50);
		l1.setFont(new Font("times new roman", Font.BOLD, 45));
		l1.setForeground(new Color(49,49,49));
        add(l1);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        // Just for refresh :) Not optional!
        setSize(671,469);
        setSize(672,470);
        

        b6=new JButton("4x4");
		b6.setPreferredSize(new Dimension(100,30));
        b6.addActionListener(this);
		b6.setBounds(75, 190, 120, 30);
		
        b7=new JButton("5x5");
		b7.setPreferredSize(new Dimension(100,30));
        b7.addActionListener(this);
		b7.setBounds(75, 230, 120, 30);

        b8=new JButton("6x6");
		b8.setPreferredSize(new Dimension(100,30));
        b8.addActionListener(this);
		b8.setBounds(75, 270, 120, 30);

		b6.setVisible(false);
		b7.setVisible(false);
		b8.setVisible(false);
        add(b6);
        add(b7);
        add(b8);


		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
		buttons.add(b4);
		buttons.add(b5);
		buttons.add(b6);
		buttons.add(b7);
		buttons.add(b8);

		for (JButton jButton : buttons) {
			jButton.setOpaque(true);
			jButton.setContentAreaFilled(true);
			jButton.setBorderPainted(true);
			jButton.setFont(new Font("times new roman", Font.PLAIN, 20));
	    	jButton.setBorder(new LineBorder(new Color(139,69,19)));
	    	jButton.setForeground(new Color(218,165,32));
	    	jButton.setBackground(new Color(49,47,44));
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	jButton.setForeground(new Color(51,51,51));
			    	jButton.setBackground(new Color(218,165,32));
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	jButton.setForeground(new Color(218,165,32));
			    	jButton.setBackground(new Color(49,47,44));
			    }
			});
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			mode = 1;
			b1.setVisible(false);
			b2.setVisible(false);
			b3.setVisible(false);
			b4.setVisible(false);
			b5.setVisible(false);
			
			b6.setVisible(true);
			b7.setVisible(true);
			b8.setVisible(true);
		}else if (e.getSource() == b2) {
			mode = 2;
			b1.setVisible(false);
			b2.setVisible(false);
			b3.setVisible(false);
			b4.setVisible(false);
			b5.setVisible(false);
			
			b6.setVisible(true);
			b7.setVisible(true);
			b8.setVisible(true);
		}else if (e.getSource() == b3) {
			mode = 3;
			b1.setVisible(false);
			b2.setVisible(false);
			b3.setVisible(false);
			b4.setVisible(false);
			b5.setVisible(false);
			
			b6.setVisible(true);
			b7.setVisible(true);
			b8.setVisible(true);
		}else if (e.getSource() == b4) {
			mode = 4;
			this.dispose();
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
			 
			  if (result != 2 && result != -1) {
				String[] options2 = {"otevøené cesty", "uzavøené cesty"};
			    String n2 = (String)JOptionPane.showInputDialog(null, "Typ cesty??", 
			            "Typ cesty", JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
			
			    System.out.println(n2 + " +++++");
			    if (n2 != null) {
			    	KnightsTour kt = new KnightsTour(c.getSelectedIndex()+3, c1.getSelectedIndex()+3, n2);
			    }
			  }
		}else if (e.getSource() == b5) {
			mode = 5;
			mainWindow.switchGame(3, 0);
			this.dispose();
		}else if (e.getSource() == b6) {
			switch (mode) {
			case 1:
				mainWindow.switchGame(0, 2);
				break;
			case 2:
				mainWindow.switchGame(1, 2);
				break;
			case 3:
				mainWindow.switchGame(2, 2);
				break;
			default:
				break;
			}
			this.dispose();
		}else if (e.getSource() == b7) {
			switch (mode) {
			case 1:
				mainWindow.switchGame(0, 3);
				break;
			case 2:
				mainWindow.switchGame(1, 3);
				break;
			case 3:
				mainWindow.switchGame(2, 3);
				break;
			default:
				break;
			}
			this.dispose();
		}else if (e.getSource() == b8) {switch (mode) {
			case 1:
				mainWindow.switchGame(0, 4);
				break;
			case 2:
				mainWindow.switchGame(1, 4);
				break;
			case 3:
				mainWindow.switchGame(2, 4);
				break;
			default:
				break;
			}
			this.dispose();
		}
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void settingsCoverGame() {
		
	}
	
}
