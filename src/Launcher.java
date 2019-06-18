import quoridor.Mode;
import quoridor.Partie;
import view.Menu;
import view.PlateauView;

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
        Partie partie = new Partie(string);
      } else if (string.equals("n")) {
        Mode mode = null;
        while (mode == null) {
          System.out.print("Mode de jeu ? (HH/HI/II/HHHH/HHHI/HHII/HIII/IIII) :");
          try {
            mode = Mode.valueOf(scan.nextLine());
          } catch (IllegalArgumentException ignored) {}
        }
        Partie partie = new Partie(mode);
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
        Partie partie = new Partie(true, fileName);
      }
      else if(string.equals("gui")){
        Partie partie = new Partie();
        new PlateauView(partie.getPlateau());
      }
    }
}
