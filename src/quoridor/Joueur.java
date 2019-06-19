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
    private int numDeplacement = 1;

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
        this.pion = pion;
        this.plateau = plateau;

        if (barrieres == null) {
            this.barrieres = new ArrayList<Barriere>();
            for (int i = 0; i < 20/plateau.getPartie().getMode().toString().length(); i++)
                this.barrieres.add(new Barriere(this.getCouleur(), new Coordonnee(-1,-1,-1,-1)));
        } else {
            this.barrieres = barrieres;
            for (Barriere barriere : this.barrieres) {
                if (barriere.getCoordonnee().getX1() != -1 && barriere.getCoordonnee().getY1() != -1
                    && barriere.getCoordonnee().getX2() == -1) {
                    this.plateau.setValue(barriere.getCoordonnee().getX1(), barriere.getCoordonnee().getY1(), 5);
                    this.plateau.setValue(barriere.getCoordonnee().getX1() + 1, barriere.getCoordonnee().getY1(), 5);
                    this.plateau.setValue(barriere.getCoordonnee().getX1() + 2, barriere.getCoordonnee().getY1(), 5);
                } else if (barriere.getCoordonnee().getX1() != -1 && barriere.getCoordonnee().getY1() != -1
                        && barriere.getCoordonnee().getX2() == -2) {
                    this.plateau.setValue(barriere.getCoordonnee().getX1(), barriere.getCoordonnee().getY1(), 5);
                    this.plateau.setValue(barriere.getCoordonnee().getX1(), barriere.getCoordonnee().getY1() + 1, 5);
                    this.plateau.setValue(barriere.getCoordonnee().getX1(), barriere.getCoordonnee().getY1() + 2, 5);
                }
            }
        }

        if (pion.getCoordonnee().getX1() == 0)
            this.fin = new Coordonnee(this.plateau.getTaille()-1, -1, -1, -1);
        else if (pion.getCoordonnee().getX1() == this.plateau.getTaille()-1)
            this.fin = new Coordonnee(0, -1, -1, -1);
        else if (pion.getCoordonnee().getY1() == 0)
            this.fin = new Coordonnee(-1, this.plateau.getTaille()-1, -1, -1);
        else
            this.fin = new Coordonnee(-1, 0, -1, -1);

        this.plateau.setValue(this.pion.getCoordonnee().getX1(), this.pion.getCoordonnee().getY1(), this.getNumero());
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
    public boolean deplacerPion(Coordonnee coordonnee) {
      boolean ret = false;
      int x = coordonnee.getX1();
      int y = coordonnee.getY1();
      if(x >= 0 && x < this.plateau.getTaille() && y >= 0 && y < this.plateau.getTaille()){
        if (this.plateau.getValue(x, y) == 0) {
          ArrayList<Integer> liste = this.getDeplacementPossibles(pion.getCoordonnee().getX1(), pion.getCoordonnee().getY1(), this.plateau.getDAMIER());
          int i = 0;
          while(i < liste.size()){
            int a = liste.get(i);
            i++;
            int b = liste.get(i);
            if(x == a && y == b){
              this.plateau.setValue(this.pion.getCoordonnee().getX1(),this.pion.getCoordonnee().getY1(), 0);
              this.pion.setCoordonnee(new Coordonnee(x,y,-1,-1));
              this.plateau.setValue(x,y,this.NUMERO);
              ret = true;
            }
            i++;
          }
        }
      }
      return ret;
    }

    /**
     * Permet de savoir s'il existe un chemin pour gagner
     * @return true s'il existe la possibilité de placer un pion
     */
    public boolean existWay(int x, int y, int[][] tmp, int j) {
        boolean exist = false;
        ArrayList<Integer> liste = getDeplacementPossibles(x, y, tmp);

        int i = 0;
        while(i < liste.size() && !exist) {
            System.out.println(i);
            if(isValide(liste.get(i), liste.get(i+1), tmp)) {
                plateau.getPartie().getJoueurs().get(j).setNumDeplacement(plateau.getPartie().getJoueurs().get(j).getNumDeplacement()+1);
                tmp[liste.get(i)][liste.get(i+1)] = plateau.getPartie().getJoueurs().get(j).getNumDeplacement();

                if (((plateau.getPartie().getJoueurs().get(j).getFin().getX1() != -1 && liste.get(i) == plateau.getPartie().getJoueurs().get(j).getFin().getX1()) || (plateau.getPartie().getJoueurs().get(j).getFin().getY1() != -1 && liste.get(i+1) == plateau.getPartie().getJoueurs().get(j).getFin().getY1())) || (plateau.getPartie().getJoueurs().get(j).getFin().getX1() != -1 && x == plateau.getPartie().getJoueurs().get(j).getFin().getX1() || plateau.getPartie().getJoueurs().get(j).getFin().getY1() != -1 && y == plateau.getPartie().getJoueurs().get(j).getFin().getY1())) {
                    exist = true;
                } else {
                    if (existWay(liste.get(i), liste.get(i+1), tmp, j)) {
                        exist = true;
                    } else {
                        tmp[liste.get(i)][liste.get(i+1)] =  -1;
                        plateau.getPartie().getJoueurs().get(j).setNumDeplacement(plateau.getPartie().getJoueurs().get(j).getNumDeplacement()-1);
                    }
                }
            }
            i = i + 2;
        }

        return exist;
    }

    public int getNumDeplacement() {
        return numDeplacement;
    }

    public void setNumDeplacement(int numDeplacement) {
        this.numDeplacement = numDeplacement;
    }



    private boolean isValide(int x, int y, int[][] tmp) {
        boolean ret = false;
        if (x >= 0 && x < this.plateau.getTaille() && y >= 0 && y < this.plateau.getTaille() && tmp[x][y] == 0)
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
    public boolean placerBarriere(Coordonnee coordonnee) {
        boolean ret = true;
        int[][] tmp = new int[plateau.getTaille()][plateau.getTaille()];

        if(coordonnee.getX1() >= 0 && coordonnee.getX1() < this.plateau.getTaille() && coordonnee.getY1() >= 0 && coordonnee.getY1() < this.plateau.getTaille()) {

            for (int x = 0; x < plateau.getDAMIER().length; x++) {
                for (int y = 0; y < plateau.getDAMIER()[x].length; y++)
                    tmp[x][y] = plateau.getDAMIER()[x][y];
            }

            if (coordonnee.getX2() == -1 && coordonnee.getY1()%2 != 0) {
                if (coordonnee.getX1() + 1 < this.plateau.getTaille() && coordonnee.getX1() + 2 < this.plateau.getTaille()
                    && tmp[coordonnee.getX1()][coordonnee.getY1()] == 0
                        && tmp[coordonnee.getX1() + 1][coordonnee.getY1()] == 0
                        && tmp[coordonnee.getX1() + 2][coordonnee.getY1()] == 0) {
                    tmp[coordonnee.getX1()][coordonnee.getY1()] = 5;
                    tmp[coordonnee.getX1() + 1][coordonnee.getY1()] = 5;
                    tmp[coordonnee.getX1() + 2][coordonnee.getY1()] = 5;
                } else
                    ret = false;
            } else if (coordonnee.getX2() == -2 && coordonnee.getX1()%2 != 0) {
                if (coordonnee.getY1() + 1 < this.plateau.getTaille() && coordonnee.getY1() + 2 < this.plateau.getTaille()
                    && tmp[coordonnee.getX1()][coordonnee.getY1()] == 0
                        && tmp[coordonnee.getX1()][coordonnee.getY1() + 1] == 0
                        && tmp[coordonnee.getX1()][coordonnee.getY1() + 2] == 0) {
                    tmp[coordonnee.getX1()][coordonnee.getY1()] = 5;
                    tmp[coordonnee.getX1()][coordonnee.getY1() + 1] = 5;
                    tmp[coordonnee.getX1()][coordonnee.getY1() + 2] = 5;
                } else
                    ret = false;
            } else
                ret = false;

           if (ret) {
               boolean canPlace = true;

               for (int i = 0; i < plateau.getPartie().getJoueurs().size() && canPlace; i++) {
                   plateau.getPartie().getJoueurs().get(i).setNumDeplacement(1);
                   tmp = new int[plateau.getTaille()][plateau.getTaille()];

                   for (int x = 0; x < plateau.getDAMIER().length; x++)
                       for (int y = 0; y < plateau.getDAMIER()[x].length; y++)
                           tmp[x][y] = plateau.getDAMIER()[x][y];

                   if (coordonnee.getX2() == -1 && coordonnee.getY1() % 2 != 0) {
                       if (coordonnee.getX1() + 1 < this.plateau.getTaille() && coordonnee.getX1() + 2 < this.plateau.getTaille()) {
                           tmp[coordonnee.getX1()][coordonnee.getY1()] = 5;
                           tmp[coordonnee.getX1() + 1][coordonnee.getY1()] = 5;
                           tmp[coordonnee.getX1() + 2][coordonnee.getY1()] = 5;
                       }
                   } else if (coordonnee.getX2() == -2 && coordonnee.getX1() % 2 != 0) {
                       if (coordonnee.getY1() + 1 < this.plateau.getTaille() && coordonnee.getY1() + 2 < this.plateau.getTaille()) {
                           tmp[coordonnee.getX1()][coordonnee.getY1()] = 5;
                           tmp[coordonnee.getX1()][coordonnee.getY1() + 1] = 5;
                           tmp[coordonnee.getX1()][coordonnee.getY1() + 2] = 5;
                       }
                   }

                   if (!existWay(plateau.getPartie().getJoueurs().get(i).getPion().getCoordonnee().getX1(), plateau.getPartie().getJoueurs().get(i).getPion().getCoordonnee().getY1(), tmp, i))
                       canPlace = false;
               }

               if (canPlace) {
                   if (coordonnee.getX2() == -1 && coordonnee.getY1()%2 != 0) {
                       if (coordonnee.getX1() + 1 < this.plateau.getTaille() && coordonnee.getX1() + 2 < this.plateau.getTaille()) {
                           this.plateau.setValue(coordonnee.getX1(), coordonnee.getY1(), 5);
                           this.plateau.setValue(coordonnee.getX1() + 1, coordonnee.getY1(), 5);
                           this.plateau.setValue(coordonnee.getX1() + 2, coordonnee.getY1(), 5);
                       }
                   } else if (coordonnee.getX2() == -2 && coordonnee.getX1()%2 != 0) {
                       if (coordonnee.getY1() + 1 < this.plateau.getTaille() && coordonnee.getY1() + 2 < this.plateau.getTaille()) {
                           this.plateau.setValue(coordonnee.getX1(), coordonnee.getY1(), 5);
                           this.plateau.setValue(coordonnee.getX1(), coordonnee.getY1() + 1, 5);
                           this.plateau.setValue(coordonnee.getX1(), coordonnee.getY1() + 2, 5);
                       }
                   }
                   boolean changed = false;
                   for (int i = 0; i < barrieres.size() && !changed; i++) {
                       if (barrieres.get(i).getCoordonnee().getX1() == -1) {
                           barrieres.get(i).setCoordonnee(coordonnee);
                           changed = true;
                       }
                   }
               } else
                   ret = false;
           }
        }
        return ret;
    }

    /**
      * Retourne les différents déplacements possibles du pion
      * @return les différents déplacements possibles du pion sous la forme d'un tableau à deux dimensions
      */
    public ArrayList<Integer> getDeplacementPossibles(int x, int y, int[][] tableau) {
      ArrayList<Integer> liste = new ArrayList<Integer>();
      if((x+2) < this.plateau.getTaille()) {
          if(tableau[x+2][y] == 0 && tableau[x+1][y] == 0){
              liste.add(x+2);
              liste.add(y);
          }
          else if((x+4) < this.plateau.getTaille()){
              if(tableau[x+2][y] != 0 && tableau[x+1][y] == 0 && tableau[x+4][y] == 0 && tableau[x+3][y] == 0){
                  liste.add(x+4);
                  liste.add(y);
              }
              else if(tableau[x+2][y] != 0 && tableau[x+1][y] == 0 && ((tableau[x + 4][y] != 0 && tableau[x + 3][y] == 0) || tableau[x + 3][y] != 0)){
                  if((y+2) < this.plateau.getTaille() && tableau[x+2][y+2] == 0 && tableau[x+2][y+1] == 0){
                      liste.add(x+2);
                      liste.add(y+2);
                  }
                  if((y-2) >= 0 && tableau[x+2][y-2] == 0 && tableau[x+1][y-2] == 0){
                      liste.add(x+2);
                      liste.add(y-2);
                  }
              }
          }
      }
        if((y+2) < this.plateau.getTaille()){
          if(tableau[x][y+2] == 0 && tableau[x][y+1] == 0){
              liste.add(x);
              liste.add(y+2);
          }
          else if((y+4) < this.plateau.getTaille()){
            if(tableau[x][y+2] != 0 && tableau[x][y+1] == 0 && tableau[x][y+4] == 0 && tableau[x][y+3] == 0){
              liste.add(x);
              liste.add(y+4);
            }
            else if(tableau[x][y+2] != 0 && tableau[x][y+1] == 0 && ((tableau[x][y + 4] != 0 && tableau[x][y + 3] != 0) || tableau[x][y + 3] != 0)){
              if((x+2) < this.plateau.getTaille() && tableau[x+2][y+2] == 0 && tableau[x+1][y+2] == 0){
                liste.add(x+2);
                liste.add(y+2);
              }
              if((x-2) >= 0 && tableau[x-2][y+2] == 0 && tableau[x-1][y+2] == 0){
                liste.add(x-2);
                liste.add(y+2);
                System.out.println(1);
              }
            }
          }
        }
      if((x-2) >= 0 ){
        if(tableau[x-2][y] == 0 && tableau[x-1][y] == 0){
          liste.add(x-2);
          liste.add(y);
        }
        else if((x-4) >= 0){
          if(tableau[x-2][y] != 0 && tableau[x-1][y] == 0 && tableau[x-4][y] == 0 && tableau[x-3][y] == 0){
            liste.add(x-4);
            liste.add(y);
          }
          else if(tableau[x-2][y] != 0 && tableau[x-1][y] == 0 && ((tableau[x - 4][y] != 0 && tableau[x - 3][y] != 0) || tableau[x - 3][y] != 0)){
            if((y+2) < this.plateau.getTaille() && tableau[x-2][y+2] == 0 && tableau[x-2][y+1] == 0){
              liste.add(x-2);
              liste.add(y+2);
            }
            if((y-2) >= 0 && tableau[x-2][y-2] == 0 && tableau[x-1][y-2] == 0){
              liste.add(x-2);
              liste.add(y-2);
            }
          }
        }
      }
      if((y-1) >= 0 && (y-2) >= 0){
        if(tableau[x][y-2] == 0  && tableau[x][y-1] == 0){
          liste.add(x);
          liste.add(y-2);
        }
        else if((y-4) >= 0){
          if(tableau[x][y-2] != 0 && tableau[x][y-1] == 0 && tableau[x][y-4] == 0 && tableau[x][y-3] == 0){
            liste.add(x);
            liste.add(y+4);
          }
          else if(tableau[x][y-2] != 0 && tableau[x][y-1] == 0 && ((tableau[x][y - 4] != 0 && tableau[x][y - 3] != 0) || tableau[x][y - 3] != 0)){
            if((x+2) < this.plateau.getTaille() && tableau[x+2][y-2] == 0 && tableau[x+1][y-2] == 0){
              liste.add(x+2);
              liste.add(y-2);
            }
            if((x-2) >= 0 && tableau[x-2][y-2] == 0 && tableau[x-1][y-2] == 0){
              liste.add(x-2);
              liste.add(y-2);
            }
          }
        }
      }
      return liste;
      }

    /**
      * Permet au joueur de jouer
      */
    public abstract String jeu(boolean gui, int x, int y);

    public abstract boolean isIA();
}
