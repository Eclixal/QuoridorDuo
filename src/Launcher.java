import quoridor.Partie;
import java.util.Scanner;

/**
  * Cette classe permet de lancer l'application
  */
public class Launcher {

    public static void main(String[] args) {
      if(args[0].equals(null)){
        Scanner scan = new Scanner(System.in);
        System.out.print("Nom du fichier : ");
        String file = scan.nextLine();
        new Partie(file);
      }
      else if(args[0].equals("gui")){

      }
    }
}
