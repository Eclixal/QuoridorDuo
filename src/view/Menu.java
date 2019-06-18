package view;

import quoridor.Partie;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class Menu {

  JLabel label;
  JButton nouvelle;
  JButton charger;

  public Menu(){
    this.initialise();
  }

  public void initialise(){
    JFrame frame = new JFrame("Quoridor");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    BorderLayout layout = new BorderLayout();
    frame.setLayout(layout);

    label = new JLabel("Quoridor");
    frame.add(label,BorderLayout.NORTH);

    JPanel panel = new JPanel();
    frame.add(panel,BorderLayout.CENTER);

    GridLayout grid = new GridLayout(2,3);
    panel.setLayout(grid);
    grid.setHgap(100);
    grid.setVgap(100);

    nouvelle = new JButton("Nouvelle partie",new ImageIcon("../images/pionRouge.png"));
    panel.add(nouvelle);

    charger = new JButton("Charger une partie");
    panel.add(charger);

    frame.pack();
  }
}
