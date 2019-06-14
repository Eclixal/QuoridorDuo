package quoridor;

import java.util.ArrayList;
import java.util.Scanner;

/**
  * Cette classe gère les joueurs humains
  */
public class Humain extends Joueur {

    private Scanner scanner;

    /**
      * Créé un nouvel objet Humain
      * @param nom le nom du joueur
      * @param numero le numéro du joueur defini selon l'ordre de création (ex joueur 1 , joueur 2 ...)
      * @param couleur la couleur du joueur (indique la forme du pion en mode texte)
      * @param barrieres liste contenant les barrières restantes du joueur
      * @param pion le pion utilisé par le joueur
      * @param plateau le plateau de jeu
      */
    public Humain(String nom, int numero, String couleur, ArrayList<Barriere> barrieres, Pion pion, Plateau plateau) {
        super(nom, numero, couleur, barrieres, pion, plateau);
    }

    public void jeu() {
      scanner = new Scanner(System.in);
      System.out.println("Joueur "+this.name+" : 1 - Déplacer le pion | 2 - Placer une barrière");
      String choix = scanner.nextLine();
      if(choix.equals("1")){
        System.out.print("Position vertical : ");
        int x = Integer.parseInt(scanner.nextLine());
        System.out.print("Position horizontal :");
        int y = Integer.parseInt(scanner.nextLine());
        this.deplacerPion(new Coordonnee(x,y,-1,-1));
      }
      else if(choix.equals("2")){
        boolean verif = false;
        for(Barriere barr : this.barrieres){
          if(barr.getCoordonnee().getX1() == -1){
            verif = true;
          }
        }
        if(verif){
          System.out.print("Position vertical de la première case de la barrière : ");
          int x1 = Integer.parseInt(scanner.nextLine());
          System.out.print("Position horizontal de la première case de la barrière :");
          int y1 = Integer.parseInt(scanner.nextLine());
          System.out.print("Position vertical de la deuxième case de la barrière : ");
          int x2 = Integer.parseInt(scanner.nextLine());
          System.out.print("Position horizontal de la deuxième case de la barrière : ");
          int y2 = Integer.parseInt(scanner.nextLine());
          this.placerBarriere(new Coordonnee(x1,y1,x2,y2));
        }
        else{
          System.out.println("Vous n'avez plus de barrières !");
          this.play();
        }
      }
    }
}
