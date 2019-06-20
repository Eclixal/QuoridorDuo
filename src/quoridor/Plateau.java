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
      * @param partie la partie qui utilise ce plateau
      */
    public Plateau(int taille, Partie partie) {
        this.TAILLE = taille*2-1;
        this.partie = partie;
        this.DAMIER = new int[this.TAILLE][this.TAILLE];
    }

    /**
      * Retourne le partie qui utilise le plateau
      * @return la partie
      */
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
      * Retourne la valeur à l'index du tableau
      * @param x La position x
      * @param y La position y
      * @return La valeur
      */
      public int getValue(int x, int y){
        return this.DAMIER[x][y];
      }

      /**
        * Definit une valeur à l'index du tableau
        * @param x La position x
        * @param y La position y
        * @param value la valeur
        */
        public void setValue(int x, int y, int value) {
          this.DAMIER[x][y] = value;
        }

        /**
          * Retourne le tableau du plateau
          * @return le tableau à deux dimensions du plateau
          */
        public int[][] getDAMIER() {
            return DAMIER;
        }
}
