package quoridor;

import java.util.ArrayList;

/**
  * Cette classe gère les joueurs IA
  */
public class IA extends Joueur {

    private final Difficulte DIFFICULTE;
    private int[][] plusCourtChemin;

    /**
      * Créé un nouvel objet IA
      * @param nom le nom du joueur
      * @param numero le numéro du joueur defini selon l'ordre de création (ex joueur 1 , joueur 2 ...)
      * @param couleur la couleur du joueur (indique la forme du pion en mode texte)
      * @param barrieres liste contenant les barrières restantes du joueur
      * @param pion le pion utilisé par le joueur
      * @param plateau le plateau de jeu
      * @param difficulte le niveau de difficulté de cette IA
      */
    public IA(String nom, int numero, String couleur, ArrayList<Barriere> barrieres, Pion pion, Plateau plateau, Difficulte difficulte) {
        super(nom, numero, couleur, barrieres, pion, plateau);
        this.DIFFICULTE = difficulte;
    }

    /**
      * Retourne la difficulté de l'IA
      * @return la difficulté de l'IA
      */
    public Difficulte getDifficulte() {
        return this.DIFFICULTE;
    }

    /**
      * Retourne le plus court chemin en déplacement de pion pour gagner que l'IA a prévu
      * @return un tableau a deux dimensions contenant le plus court chemin identifié par l'IA
      */
    public int[][] getPlusCourtChemin() {
        return plusCourtChemin;
    }

    /**
      * Modifie le plus court chemin en déplacement de pion que l'IA prévoie
      * @param plusCourtChemin un tableau a deux dimensions contenant le plus court chemin que l'IA doit identifier
      */
    public void setPlusCourtChemin(int[][] plusCourtChemin) {
        this.plusCourtChemin = plusCourtChemin;
    }

    /**
      * Identifie le plus court chemin pour chacun des joueurs et planifie les actions de l'IA en conséquences
      */
    public void plannification() {

    }

    /**
      * Joue un tour pour un IA
      * @param gui le booleen pour savoir si la partie se joue en mode graphique
      * @param x la position x du deplacement que le joueur veut faire (utile que pour le mode graphique pour un humain)
      * @param y la postion y du deplacement que le joueur veut faire (utile que pour le mode graphique pour un humain)
      * @return un message s'il y a une erreur
      */
    public String jeu(boolean gui, int x, int y) {

        if(this.getDifficulte() == Difficulte.FACILE){
        double i1 = Math.random() * 2;
        int i = (int) i1;
        boolean verif = false;
        for(Barriere barr : this.barrieres){
          if(barr.getCoordonnee().getX1() == -1){
            verif = true;
          }
        }
        if(i == 0 || !verif){
          ArrayList liste = this.getDeplacementPossibles(this.pion.getCoordonnee().getX1(), this.pion.getCoordonnee().getY1(), this.plateau.getDAMIER());
          int j = 1;
          while(j%2 != 0){
            double test = Math.random() * liste.size();
            j = (int) test;
          }
          this.deplacerPion(new Coordonnee((int) liste.get(j), (int) liste.get(j+1), -1, -1));
        }
        else{
          boolean trouve = false;
          while(!trouve) {
            double a1 = Math.random() * this.plateau.getTaille();
            double b1 = Math.random() * this.plateau.getTaille();
            int a = (int) a1;
            int b = (int) b1;
            int c = 0;
            if((a%2 == 0 && b%2 != 0) || (a%2 != 0 && b%2 ==0)){
              if(a%2 == 0 && b%2 != 0){
                c = -1;
              }
              else if(a%2 != 0 && b%2 ==0){
                c = -2;
              }
              if(this.placerBarriere(new Coordonnee(a,b,c,-1))){
                  trouve = true;
              }
            }
          }
        }
      }
      return "";
    }

    /**
      * Retourne si c'est un IA ou non
      * @return Vrai si c'est un IA
      */
    public boolean isIA(){
      return true;
    }
}
