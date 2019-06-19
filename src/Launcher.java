import quoridor.Mode;
import quoridor.Partie;
import quoridor.ui.MainFrame;
import quoridor.ui.view.Menu;
import quoridor.ui.view.PlateauView;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
  * Cette classe permet de lancer l'application
  */
public class Launcher {

    public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      System.out.print("Fichier de configuration ? (o/n/save) :");
      String string = scan.nextLine();
      if (string.equals("o")) {
        System.out.print("Nom du fichier : ");
        string = scan.nextLine();
        Partie partie = new Partie(string, false);
      } else if (string.equals("n")) {
        Mode mode = null;
        while (mode == null) {
          System.out.print("Mode de jeu ? (HH/HI/II/HHHH/HHHI/HHII/HIII/IIII) :");
          try {
            mode = Mode.valueOf(scan.nextLine());
          } catch (IllegalArgumentException ignored) {}
        }
        Partie partie = new Partie(mode, false);
      } else if (string.equals("save")) {
        String fileName = null;
        while (fileName == null) {
          System.out.print("Fichier de sauvegarde ? : ");
          try {
            String str = scan.nextLine();
            new DataInputStream(new BufferedInputStream(new FileInputStream(str)));
            fileName = str;
          } catch (Exception ignored) { }
        }
        Partie partie = new Partie(true, fileName, false);
      }
      else if(string.equals("gui")){
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
          ex.printStackTrace();
        }

        Partie partie = new Partie();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setContentPane(new Menu(mainFrame));
        mainFrame.validate();
        mainFrame.repaint();
      }
    }
}
