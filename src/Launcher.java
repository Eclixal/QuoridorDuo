import quoridor.Partie;
import java.util.Scanner;

/**
  * Cette classe permet de lancer l'application
  */
public class Launcher {

    public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      System.out.print("Nom du fichier : ");
      String file = scan.nextLine();
      Partie partie = new Partie(file);
      partie.afficher();
    }
}
