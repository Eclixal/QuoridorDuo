import quoridor.Partie;
import java.util.Scanner;

/**
  * Cette classe permet de lancer l'application
  */
public class Launcher {

    public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      System.out.print("Fichier de configuration ? (o/n) :");
      String string = scan.nextLine();
      if(string.equals("o")){
        System.out.print("Nom du fichier : ");
        string = scan.nextLine();
        Partie partie = new Partie(string);
      }
      else if(string.equals("n")){
        Partie partie = new Partie();
      }
    }
}
