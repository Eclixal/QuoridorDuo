package quoridor;

import java.io.*;
import java.util.ArrayList;

/**
  * Cette classe gère les différents aspect de la partie
  */
public class Partie {

    private int tour;
    private Mode mode;
    private Plateau plateau;
    private ArrayList<Joueur> joueurs;

    /**
      * Créé un nouvel objet Partie
      * @param fileName le nom du fichier de configuration
      */
    public Partie(String fileName) {
        this.configuration(fileName);
        this.initialisation();
        this.start();
    }

  /**
    * Retourne le numéro du tour actuel
    * @return le numéro du tour
    */
  public int getTour() {
        return tour;
    }

    /**
      * Retourne le mode de jeu utilisé
      * @return le mode de jeu utilisé
      */
    public Mode getMode() {
        return mode;
    }

    /**
      * Sauvegarde la partie
      */
    public void sauvegarder() {

    }

    /**
      * Charge les données de sauvegarde contenues dans le fichier sélectionné
      * @param filename le fichier contenant les données à charger
      */
    public void charger(String filename) {

    }

    /**
      * Initialise les différents éléments constants de la partie
      */
    private void initialisation() {
        this.tour = 0;
        this.plateau = new Plateau(9, this);
        this.joueurs = new ArrayList<Joueur>();

        switch (this.mode) {
            case HH:
                this.joueurs.add(new Humain("Joueur1", 1, "RED", new ArrayList<Barriere>(), new Pion("RED", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau));
                this.joueurs.add(new Humain("Joueur2", 2, "BLUE", new ArrayList<Barriere>(), new Pion("BLUE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),plateau.getTaille(),-1,-1)), plateau));
                break;
            case HI:
                this.joueurs.add(new Humain("Joueur1", 1, "RED", new ArrayList<Barriere>(), new Pion("RED", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau));
                this.joueurs.add(new IA("Joueur2", 2, "BLUE", new ArrayList<Barriere>(), new Pion("BLUE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),plateau.getTaille(),-1,-1)), plateau, Difficulte.FACILE));
                break;
            case II:
                this.joueurs.add(new IA("Joueur1", 1, "RED", new ArrayList<Barriere>(), new Pion("RED", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau, Difficulte.FACILE));
                this.joueurs.add(new IA("Joueur2", 2, "BLUE", new ArrayList<Barriere>(), new Pion("BLUE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),plateau.getTaille(),-1,-1)), plateau, Difficulte.FACILE));
            case HHHH:
                this.joueurs.add(new Humain("Joueur1", 1, "RED", new ArrayList<Barriere>(), new Pion("RED", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau));
                this.joueurs.add(new Humain("Joueur2", 2, "BLUE", new ArrayList<Barriere>(), new Pion("BLUE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau));
                this.joueurs.add(new Humain("Joueur3", 3, "GREEN", new ArrayList<Barriere>(), new Pion("GREEN", new Coordonnee(0, (int) Math.round((double)plateau.getTaille()/2),-1,-1)), plateau));
                this.joueurs.add(new Humain("Joueur4", 4, "YELLOW", new ArrayList<Barriere>(), new Pion("YELLOW", new Coordonnee(0, (int) Math.round((double)plateau.getTaille()/2),-1,-1)), plateau));
            case HHHI:
                this.joueurs.add(new Humain("Joueur1", 1, "RED", new ArrayList<Barriere>(), new Pion("RED", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau));
                this.joueurs.add(new Humain("Joueur2", 2, "BLUE", new ArrayList<Barriere>(), new Pion("BLUE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau));
                this.joueurs.add(new Humain("Joueur3", 3, "GREEN", new ArrayList<Barriere>(), new Pion("GREEN", new Coordonnee(0, (int) Math.round((double)plateau.getTaille()/2),-1,-1)), plateau));
                this.joueurs.add(new IA("Joueur4", 4, "YELLOW", new ArrayList<Barriere>(), new Pion("YELLOW", new Coordonnee(0, (int) Math.round((double)plateau.getTaille()/2),-1,-1)), plateau, Difficulte.FACILE));
            case HHII:
                this.joueurs.add(new Humain("Joueur1", 1, "RED", new ArrayList<Barriere>(), new Pion("RED", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau));
                this.joueurs.add(new Humain("Joueur2", 2, "BLUE", new ArrayList<Barriere>(), new Pion("BLUE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau));
                this.joueurs.add(new IA("Joueur3", 3, "GREEN", new ArrayList<Barriere>(), new Pion("GREEN", new Coordonnee(0, (int) Math.round((double)plateau.getTaille()/2),-1,-1)), plateau, Difficulte.FACILE));
                this.joueurs.add(new IA("Joueur4", 4, "YELLOW", new ArrayList<Barriere>(), new Pion("YELLOW", new Coordonnee(0, (int) Math.round((double)plateau.getTaille()/2),-1,-1)), plateau, Difficulte.FACILE));
            case HIII:
                this.joueurs.add(new Humain("Joueur1", 1, "RED", new ArrayList<Barriere>(), new Pion("RED", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau));
                this.joueurs.add(new IA("Joueur2", 2, "BLUE", new ArrayList<Barriere>(), new Pion("BLUE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau, Difficulte.FACILE));
                this.joueurs.add(new IA("Joueur3", 3, "GREEN", new ArrayList<Barriere>(), new Pion("GREEN", new Coordonnee(0, (int) Math.round((double)plateau.getTaille()/2),-1,-1)), plateau, Difficulte.FACILE));
                this.joueurs.add(new IA("Joueur4", 4, "YELLOW", new ArrayList<Barriere>(), new Pion("YELLOW", new Coordonnee(0, (int) Math.round((double)plateau.getTaille()/2),-1,-1)), plateau, Difficulte.FACILE));
            case IIII:
                this.joueurs.add(new IA("Joueur1", 1, "RED", new ArrayList<Barriere>(), new Pion("RED", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau, Difficulte.FACILE));
                this.joueurs.add(new IA("Joueur2", 2, "BLUE", new ArrayList<Barriere>(), new Pion("BLUE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2),0,-1,-1)), plateau, Difficulte.FACILE));
                this.joueurs.add(new IA("Joueur3", 3, "GREEN", new ArrayList<Barriere>(), new Pion("GREEN", new Coordonnee(0, (int) Math.round((double)plateau.getTaille()/2),-1,-1)), plateau, Difficulte.FACILE));
                this.joueurs.add(new IA("Joueur4", 4, "YELLOW", new ArrayList<Barriere>(), new Pion("YELLOW", new Coordonnee(0, (int) Math.round((double)plateau.getTaille()/2),-1,-1)), plateau, Difficulte.FACILE));
            default:
                break;
        }
    }

    /**
      * Configure les éléments non constants de la partie à l'aide du fichier de configuration
      * @param fileName le nom du fichier de configuration
      */
    private void configuration(String fileName) {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(fileName));

            this.mode = Mode.HH;

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
      * Lance la partie
      */
    public void start() {

    }

    /**
      * Termine la partie
      */
    public void fin() {

    }

    public void afficher(){
      for(int i = 0;i < this.plateau.getTaille();i++){
        for(int j = 0;j < this.plateau.getTaille();j++){
          if(i%2 != 0 || j%2 != 0){
            if(this.plateau.getValue(i,j) == 5){
              if(i%2 != 0 && j%2 == 0){
                System.out.print("--\t");
              }
              else if(i%2 == 0 && j%2 != 0){
                System.out.print("|\t");
              }
              else{
                System.out.print("\t");
              }
            }
            else{
              System.out.print("\t");
            }
          }
          else{
            System.out.print(this.plateau.getValue(i,j)+"\t");
          }
        }
        System.out.println();
      }
    }
}
