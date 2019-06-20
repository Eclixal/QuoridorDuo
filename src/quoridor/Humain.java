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

    public String jeu(boolean gui, int x, int y) {
      String ret = "";
      if(!gui){
        scanner = new Scanner(System.in);
        System.out.println("Joueur "+this.nom+" : 1 - Déplacer le pion | 2 - Placer une barrière | 3 - Sauvegarder et quitter");
        String choix = scanner.nextLine();
        if(choix.equals("1")){
          System.out.print("Position vertical : ");
          x = Integer.parseInt(scanner.nextLine());
          System.out.print("Position horizontal :");
          y = Integer.parseInt(scanner.nextLine());
          boolean joue = this.deplacerPion(new Coordonnee(x,y,-1,-1));
          if(!joue){
            System.out.println("Ce déplacement est impossible !");
            this.jeu(gui, -1, -1);
          }
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
            System.out.print("Placer la barrière horizontalement (-2) ou verticalement (-1) : ");
            int x2 = Integer.parseInt(scanner.nextLine());
            boolean joue = this.placerBarriere(new Coordonnee(x1,y1,x2,-1));
            if(!joue){
              System.out.println("Ce placement est impossible !");
              this.jeu(gui, -1, -1);
            }
          }
          else{
            System.out.println("Vous n'avez plus de barrières !");
            this.jeu(gui, -1, -1);
          }
        }
        else if(choix.equals("3")){
          this.plateau.getPartie().sauvegarder(getNumero()-1, false);
        }
        else{
          System.out.println("Numéro de choix impossible !");
          this.jeu(gui, -1, -1);
        }
      }
      else{
        if(x%2 == 0 && y%2 == 0){
          boolean jouer = this.deplacerPion(new Coordonnee(x,y,-1,-1));
          if(!jouer){
            ret = "Ce déplacement est impossible !";
          }
        }
        else if((x%2 == 0 && y%2 !=0) || (x%2 != 0 && y%2 == 0)){
          int z = 0;
          if(x%2 == 0 && y!= 0){
            z = -1;
          }
          else{
            z = -2;
          }
          boolean jouer = this.placerBarriere(new Coordonnee(x,y,z,-1));
          if(!jouer){
            ret = "Ce placement est impossible !";
          }
        }
        else{
          ret = "Ce placement est impossible !";
        }
      }
      return ret;
    }

    public boolean isIA(){
      return false;
    }
}
