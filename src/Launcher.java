import quoridor.Mode;
import quoridor.Controlleur;
import quoridor.Partie;
import quoridor.Difficulte;
import quoridor.ui.MainFrame;
import quoridor.ui.view.Menu;
import quoridor.ui.view.PlateauView;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.TimerTask;

/**
  * Cette classe permet de lancer l'application
  */
public class Launcher {

    public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      System.out.print("Jouer en mode graphique ? (o/n) : ");
      String string = scan.nextLine();
      if(string.equals("o")){
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
          ex.printStackTrace();
        }
        MainFrame mainFrame = new MainFrame();
        mainFrame.setContentPane(new Menu(mainFrame));
        mainFrame.validate();
        mainFrame.repaint();
      }
      else if(string.equals("n")){
        new Controlleur();
    }
  }
}
