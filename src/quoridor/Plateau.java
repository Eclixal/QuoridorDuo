package quoridor;

/**
  * Cette classe gère le plateau de jeu
  */
public class Plateau {

    private final int TAILLE;
    private final int[][] DAMIER;
    private Partie partie;

    /**
      * Créé un nouvel objet Plateau
      * @param taille la taille du plateau (longueur et largeur car le plateau est forcément un carré)
      */
    public Plateau(int taille, Partie partie) {
        this.TAILLE = taille*2-1;
        this.partie = partie;
        this.DAMIER = new int[this.TAILLE][this.TAILLE];
    }

    public Partie getPartie() {
        return partie;
    }

    /**
      * Retourne la taille du plateau
      * @return la taille du plateau
      */
    public int getTaille() {
        return TAILLE;
    }

    /**
      * Get the value in an index of the table.
      * @param x The position x
      * @param y The position y
      * @return The value
      */
      public int getValue(int x, int y){
        return this.DAMIER[x][y];
      }

      /**
        * Set a value in an index of the table.
        * @param x The position x
        * @param y The position y
        */
        public void setValue(int x, int y, int value) {
          this.DAMIER[x][y] = value;
        }
}
