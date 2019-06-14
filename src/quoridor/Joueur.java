package quoridor;

import java.util.ArrayList;
import java.util.Arrays;

/**
  * Classe abstraite gérant les joueurs
  */
public abstract class Joueur {

    protected String nom;
    protected final int NUMERO;
    protected final String COULEUR;
    protected ArrayList<Barriere> barrieres;
    protected Pion pion;
    protected Plateau plateau;
    protected Coordonnee fin;

    /**
      * Créé un nouvel objet Humain
      * @param nom le nom du joueur
      * @param numero le numéro du joueur defini selon l'ordre de création (ex joueur 1 , joueur 2 ...)
      * @param couleur la couleur du joueur (indique la forme du pion en mode texte)
      * @param barrieres liste contenant les barrières restantes du joueur
      * @param pion le pion utilisé par le joueur
      * @param plateau le plateau de jeu
      */
    public Joueur(String nom, int numero, String couleur, ArrayList<Barriere> barrieres, Pion pion, Plateau plateau) {
        this.nom = nom;
        this.NUMERO = numero;
        this.COULEUR = couleur;
        this.barrieres = barrieres;
        this.pion = pion;
        this.plateau = plateau;

        if (pion.getCoordonnee().getX1() == 0)
            this.fin = new Coordonnee(this.plateau.getTaille()-1, -1, -1, -1);
        else if (pion.getCoordonnee().getX1() == this.plateau.getTaille()-1)
            this.fin = new Coordonnee(0, -1, -1, -1);
        else if (pion.getCoordonnee().getY1() == 0)
            this.fin = new Coordonnee(-1, this.plateau.getTaille()-1, -1, -1);
        else
            this.fin = new Coordonnee(-1, 0, -1, -1);

        this.plateau.setValue(this.pion.getCoordonnee().getX1(), this.pion.getCoordonnee().getY1(), this.getNumero());

        for (int i = 0; i < 20/plateau.getPartie().getMode().toString().length(); i++)
            this.barrieres.add(new Barriere(this.getCouleur(), new Coordonnee(-1,-1,-1,-1)));
    }

    /**
      * Retourne le nom du joueur
      * @return le nom du joueur
      */
    public String getNom() {
        return nom;
    }

    /**
      * Retourne le numéro du joueur
      * @return le numero du joueur
      */
    public int getNumero() {
        return this.NUMERO;
    }

    /**
      * Retourne la couleur du joueur
      * @return la couleur du joueur
      */
    public String getCouleur() {
        return this.COULEUR;
    }

    /**
      * Retourne le pion utilisé par le joueur
      * @return le pion utilisé par le joueur
      */
    public Pion getPion() {
        return this.pion;
    }

    /**
      * Retourne la liste des barrières restantes du joueur
      * @return la liste des barrières restantes du joueur
      */
    public ArrayList<Barriere> getBarrieres() {
        return this.barrieres;
    }

    /**
      * Déplace le pion vers de nouvelles coordonnées
      * si celles-ci sont atteignables
      * @param coordonnee les coordonnées à atteindre
      */
    public void deplacerPion(Coordonnee coordonnee) {
      int x = coordonnee.getX1();
      int y = coordonnee.getY1();
      if (this.plateau.getValue(x, y) == 0) {
        int[][] tab = this.getDeplacementPossibles(pion.getCoordonnee().getX1(), pion.getCoordonnee().getY1());
        for(int i=0;i < 5;i++){
          int a = tab[i][0];
          int b = tab[i][1];
          if(x == a && y == b){
            this.plateau.setValue(this.pion.getCoordonnee().getX1(),this.pion.getCoordonnee().getY1(), 0);
            this.pion.setCoordonnee(new Coordonnee(x,y,-1,-1));
            this.plateau.setValue(x,y,this.NUMERO);
          }
        }
      } else {
        System.out.println("Le placement est impossible !");
      }
    }

    private int numDeplacement = 1;

    /**
     * Permet de savoir s'il existe un chemin pour gagner
     * @return true s'il existe la possibilité de placer un pion
     */
    public boolean existWay(int x, int y, int[][] tmp) {
        boolean exist = false;
        int[][] possibleMove = getDeplacementPossibles(x, y);

        for (int i = 0; i < 5 && !exist; i++) {
            if (isValide(possibleMove[i][0], possibleMove[i][1])) {
                numDeplacement++;
                tmp[possibleMove[i][0]][possibleMove[i][1]] = numDeplacement;
                if (((getFin().getX1() != -1 && possibleMove[i][0] == getFin().getX1()) || (getFin().getY1() != -1 && possibleMove[i][1] == getFin().getY1())) || (getFin().getX1() != -1 && x == getFin().getX1() || getFin().getY1() != -1 && y == getFin().getY1())) {
                    exist = true;
                } else {
                    if (existWay(possibleMove[i][0], possibleMove[i][1], tmp)) {
                        exist = true;
                    } else {
                        tmp[possibleMove[i][0]][possibleMove[i][1]] =  -1;
                        numDeplacement--;
                    }
                }
            }
        }

        return exist;
    }

    private boolean isValide(int x, int y) {
        boolean ret = false;
        if (x >= 0 && x < this.plateau.getTaille() && y >= 0 && y < this.plateau.getTaille() && this.plateau.getValue(x, y) == 0)
            ret = true;
        return ret;
    }

    public Coordonnee getFin() {
        return fin;
    }

    /**
      * Place une barrière aux coordonnées sélectionnées s'il en reste une au joueur
      * @param coordonnee les coordonnées où placer la barrière
      */
    public void placerBarriere(Coordonnee coordonnee) {
        int[][] tmp = new int[plateau.getTaille()][plateau.getTaille()];

        for (int x = 0; x < plateau.getDAMIER().length; x++) {
            for (int y = 0; y < plateau.getDAMIER()[x].length; y++)
                tmp[x][y] = plateau.getDAMIER()[x][y];
        }

        tmp[coordonnee.getX1()][coordonnee.getY1()] = 5;
        tmp[coordonnee.getX1()-(coordonnee.getX1()-coordonnee.getX2())][coordonnee.getY1()-(coordonnee.getY1()-coordonnee.getY2())] = 5;
        tmp[coordonnee.getX2()][coordonnee.getY2()] = 5;

        boolean canPlace = true;
        for (int i = 0; i < plateau.getPartie().getJoueurs().size() && canPlace; i++) {
            if (!existWay(plateau.getPartie().getJoueurs().get(i).getPion().getCoordonnee().getX1(), plateau.getPartie().getJoueurs().get(i).getPion().getCoordonnee().getY1(), tmp))
                canPlace = false;
        }

        if (canPlace) {
            this.plateau.setValue(coordonnee.getX1(), coordonnee.getY1(), 5);
            this.plateau.setValue(coordonnee.getX1()-(coordonnee.getX1()-coordonnee.getX2()), coordonnee.getY1()-(coordonnee.getY1()-coordonnee.getY2()), 5);
            this.plateau.setValue(coordonnee.getX2(), coordonnee.getY2(), 5);

            boolean changed = false;
            for (int i = 0; i < barrieres.size() && !changed; i++) {
                if (barrieres.get(i).getCoordonnee().getX1() == -1) {
                    barrieres.get(i).setCoordonnee(coordonnee);
                    changed = true;
                }
            }
        } else {
            System.out.println("ERREUR TU PEUX PAS !!!!!");
        }
    }

    /**
      * Retourne les différents déplacements possibles du pion
      * @return les différents déplacements possibles du pion sous la forme d'un tableau à deux dimensions
      */
    public int[][] getDeplacementPossibles(int x, int y) {
      int[][] tab = {{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}};
      if((x+2) < this.plateau.getTaille() && (x+1) >= 0){
        if(this.plateau.getValue(x+2,y) == 0 && this.plateau.getValue(x+1,y) == 0){
          tab[0][0] = x+2;
          tab[0][1] = y;
        }
        else if((x+4) < this.plateau.getTaille()){
          if(this.plateau.getValue(x+2,y) != 0 && this.plateau.getValue(x+1,y) == 0 && this.plateau.getValue(x+4,y) == 0 && this.plateau.getValue(x+3,y) == 0){
            tab[0][0] = x+4;
            tab[0][1] = y;
          }
          else if(this.plateau.getValue(x+2,y) != 0 && this.plateau.getValue(x+1,y) == 0 && ((this.plateau.getValue(x+4,y) != 0 && this.plateau.getValue(x+3,y) == 0) || this.plateau.getValue(x+3,y) != 0)){
            if((y+2) < this.plateau.getTaille() && this.plateau.getValue(x+2,y+2) == 0 && this.plateau.getValue(x+2,y+1) == 0){
              tab[0][0] = x+2;
              tab[0][1] = y+2;
            }
            if((y-2) >= 0 && this.plateau.getValue(x+2,y-2) == 0 && this.plateau.getValue(x+2,y-1) == 0){
              tab[4][0] = x+2;
              tab[4][1] = y-2;
            }
          }
        }
      }
      if((y+2) < this.plateau.getTaille() && (y+1) >= 0){
          if(this.plateau.getValue(x,y+2) == 0 && this.plateau.getValue(x,y+1) == 0){
              tab[1][0] = x;
              tab[1][1] = y+2;
          }
          else if((y+4) < this.plateau.getTaille()){
            if(this.plateau.getValue(x,y+2) != 0 && this.plateau.getValue(x,y+1) == 0 && this.plateau.getValue(x,y+4) == 0 && this.plateau.getValue(x,y+3) == 0){
              tab[0][0] = x;
              tab[0][1] = y+4;
            }
            else if(this.plateau.getValue(x,y+2) != 0 && this.plateau.getValue(x,y+1) == 0 && ((this.plateau.getValue(x,y+4) != 0 && this.plateau.getValue(x,y+3) == 0) || this.plateau.getValue(x,y+3) != 0)){
              if((x+2) < this.plateau.getTaille() && this.plateau.getValue(x+2,y+2) == 0 && this.plateau.getValue(x+1,y+2) == 0){
                tab[0][0] = x+2;
                tab[0][1] = y+2;
              }
              if((x-2) >= 0 && this.plateau.getValue(x-2,y+2) == 0 && this.plateau.getValue(x-2,y+1) == 0){
                tab[4][0] = x-2;
                tab[4][1] = y+2;
              }
            }
          }
        }
      if((x-1) < this.plateau.getTaille() && (x-2) >= 0){
        if(this.plateau.getValue(x-2,y) == 0 && this.plateau.getValue(x-1,y) == 0){
          tab[2][0] = x-2;
          tab[2][1] = y;
        }
        else if((x-4) > 0){
          if(this.plateau.getValue(x-2,y) != 0 && this.plateau.getValue(x-1,y) == 0 && this.plateau.getValue(x-4,y) == 0 && this.plateau.getValue(x-3,y) == 0){
            tab[0][0] = x-4;
            tab[0][1] = y;
          }
          else if(this.plateau.getValue(x-2,y) != 0 && this.plateau.getValue(x-1,y) == 0 && ((this.plateau.getValue(x-4,y) != 0 && this.plateau.getValue(x-3,y) == 0) || this.plateau.getValue(x-3,y) != 0)){
            if((y+2) < this.plateau.getTaille() && this.plateau.getValue(x-2,y+2) == 0 && this.plateau.getValue(x-2,y+1) == 0){
              tab[0][0] = x-2;
              tab[0][1] = y+2;
            }
            if((y-2) >= 0 && this.plateau.getValue(x-2,y-2) == 0 && this.plateau.getValue(x-2,y-1) == 0){
              tab[4][0] = x-2;
              tab[4][1] = y-2;
            }
          }
        }
      }
      if((y-1) < this.plateau.getTaille() && (y-2) >= 0){
        if(this.plateau.getValue(x,y-2) == 0  && this.plateau.getValue(x,y-1) == 0){
          tab[3][0] = x;
          tab[3][1] = y-2;
        }
        else if((y-4) < this.plateau.getTaille()){
          if(this.plateau.getValue(x,y-2) != 0 && this.plateau.getValue(x,y-1) == 0 && this.plateau.getValue(x,y-4) == 0 && this.plateau.getValue(x,y-3) == 0){
            tab[0][0] = x;
            tab[0][1] = y+4;
          }
          else if(this.plateau.getValue(x,y-2) != 0 && this.plateau.getValue(x,y-1) == 0 && ((this.plateau.getValue(x,y-4) != 0 && this.plateau.getValue(x,y-3) == 0) || this.plateau.getValue(x,y-3) != 0)){
            if((x+2) < this.plateau.getTaille() && this.plateau.getValue(x+2,y-2) == 0 && this.plateau.getValue(x+1,y-2) == 0){
              tab[0][0] = x+2;
              tab[0][1] = y-2;
            }
            if((x-2) >= 0 && this.plateau.getValue(x-2,y-2) == 0 && this.plateau.getValue(x-2,y-1) == 0){
              tab[4][0] = x-2;
              tab[4][1] = y-2;
            }
          }
        }
      }
      return tab;
      }

    /**
      * Permet au joueur de jouer
      */
    public abstract void jeu();
}
