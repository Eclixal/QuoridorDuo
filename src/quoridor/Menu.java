package view;

import quoridor.Partie;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

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
    frame.setLayout(null);

    label = new JLabel("Quoridor");
    label.setBounds(100,10,50,50);
    frame.add(label);

    nouvelle = new JButton("Nouvelle partie");
    nouvelle.setBounds(150,150,50,50);
    frame.add(nouvelle);

    charger = new JButton("Charger une partie");
    charger.setBounds(150,300,50,50);
    frame.add(charger);

    frame.setSize(1000,600);
  }
}
