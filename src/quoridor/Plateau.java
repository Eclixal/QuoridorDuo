package quoridor;

/**
  * Cette classe gère le plateau de jeu
  */
public class Plateau {

    private final int TAILLE;
    private final int[][] DAMIER;

    /**
      * Créé un nouvel objet Plateau
      * @param taille la taille du plateau (longueur et largeur car le plateau est forcément un carré)
      */
    public Plateau(int taille) {
        this.TAILLE = taille*2;
        this.DAMIER = new int[this.TAILLE][this.TAILLE];
    }

    /**
      * Retourne la taille du plateau
      * @return la taille du plateau
      */
    public int getTaille() {
        return TAILLE;
    }
}
