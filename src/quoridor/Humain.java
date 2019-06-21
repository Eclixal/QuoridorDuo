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


    /**
      * Joue un tour pour un IA
      * @param x la position x du deplacement que le joueur veut faire (utile que pour le mode graphique pour un humain)
      * @param y la postion y du deplacement que le joueur veut faire (utile que pour le mode graphique pour un humain)
      * @return un message s'il y a une erreur
      */
    public String jeu(int x, int y) {
      String ret = "";
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
      return ret;
    }

    /**
      * Retourne si c'est un IA ou non
      * @return Vrai si c'est un IA
      */
    public boolean isIA(){
      return false;
    }
}
