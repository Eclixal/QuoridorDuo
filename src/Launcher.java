import quoridor.Mode;
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
        System.out.print("Fichier de configuration ? (o/n/save) : ");
        string = scan.nextLine();
        if (string.equals("o")) {
          System.out.print("Nom du fichier : ");
          string = scan.nextLine();
          Partie partie = new Partie(string, false);
        } else if (string.equals("n")) {
          Mode mode = null;
          while (mode == null) {
            System.out.print("Mode de jeu ? (HH/HI/II/HHHH/HHHI/HHII/HIII/IIII) : ");
            try {
              mode = Mode.valueOf(scan.nextLine());
            } catch (IllegalArgumentException ignored) {}
          }


          if(mode == Mode.valueOf("HH") || mode == Mode.valueOf("HI") || mode == Mode.valueOf("II")){
            String joueur1 = null;
            String joueur2 = null;
            Difficulte difficulte1 = null;
            Difficulte difficulte2 = null;
            System.out.print("Nom du joueur 1 : ");
            try{
              joueur1 = scan.nextLine();
            } catch (IllegalArgumentException ignored) {}
            System.out.print("Nom du joueur 2 : ");
            try{
              joueur2 = scan.nextLine();
            } catch (IllegalArgumentException ignored) {}

            if(mode == Mode.valueOf("II")){
              System.out.print("Difficulte du joueur 1 : (FACILE/MOYEN)");
              try{
                difficulte1 = Difficulte.valueOf(scan.nextLine());
              } catch (IllegalArgumentException ignored) {}
            }
            if(mode == Mode.valueOf("HI") || mode == Mode.valueOf("II")){
              System.out.print("Difficulte du joueur 2 : (FACILE/MOYEN)");
              try{
                difficulte2 = Difficulte.valueOf(scan.nextLine());
              } catch (IllegalArgumentException ignored) {}
            }
            Partie partie = new Partie(mode,joueur1,joueur2,difficulte1,difficulte2,false);
          }

          else{
            String joueur1 = null;
            String joueur2 = null;
            String joueur3 = null;
            String joueur4 = null;
            Difficulte difficulte1 = null;
            Difficulte difficulte2 = null;
            Difficulte difficulte3 = null;
            Difficulte difficulte4 = null;
            System.out.print("Nom du joueur 1 : ");
            try{
              joueur1 = scan.nextLine();
            } catch (IllegalArgumentException ignored) {}
            System.out.print("Nom du joueur 2 : ");
            try{
              joueur2 = scan.nextLine();
            } catch (IllegalArgumentException ignored) {}
            System.out.print("Nom du joueur 3 : ");
            try{
              joueur3 = scan.nextLine();
            } catch (IllegalArgumentException ignored) {}
            System.out.print("Nom du joueur 4 : ");
            try{
              joueur4 = scan.nextLine();
            } catch (IllegalArgumentException ignored) {}

            if(mode == Mode.valueOf("IIII")){
              System.out.print("Difficulte du joueur 1 : (FACILE/MOYEN)");
              try{
                difficulte1 = Difficulte.valueOf(scan.nextLine());
              } catch (IllegalArgumentException ignored) {}
            }
            if(mode == Mode.valueOf("HIII") || mode == Mode.valueOf("IIII")){
              System.out.print("Difficulte du joueur 2 : (FACILE/MOYEN)");
              try{
                difficulte2 = Difficulte.valueOf(scan.nextLine());
              } catch (IllegalArgumentException ignored) {}
            }
            if(mode == Mode.valueOf("HHII") || mode == Mode.valueOf("HIII") || mode == Mode.valueOf("IIII")){
              System.out.print("Difficulte du joueur 3 : (FACILE/MOYEN)");
              try{
                difficulte3 = Difficulte.valueOf(scan.nextLine());
              } catch (IllegalArgumentException ignored) {}
            }
            if(mode == Mode.valueOf("HHHI") || mode == Mode.valueOf("HHII") || mode == Mode.valueOf("HIII") || mode == Mode.valueOf("IIII")){
              System.out.print("Difficulte du joueur 4 : (FACILE/MOYEN)");
              try{
                difficulte4 = Difficulte.valueOf(scan.nextLine());
              } catch (IllegalArgumentException ignored) {}
            }
            Partie partie = new Partie(mode,joueur1,joueur2,joueur3,joueur4,difficulte1,difficulte2,difficulte3,difficulte4,false);
          }
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
      else{
        System.out.println("Commande non vaide");
      }
    }
  }
}
