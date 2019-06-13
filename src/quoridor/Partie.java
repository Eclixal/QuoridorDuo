package quoridor;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
  * Cette classe gère les différents aspect de la partie
  */
public class Partie {

    private int tour;
    private Mode mode;
    private Plateau plateau;
    private ArrayList<Joueur> joueur;

    /**
      * Créé un nouvel objet Partie
      * @param fileName le nom du fichier de configuration
      */
    public Partie(String fileName) {
        this.configuration(fileName);
        this.initialisation();
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


    }

    /**
      * Configure les éléments non constants de la partie à l'aide du fichier de configuration
      * @param fileName le nom du fichier de configuration
      */
    private void configuration(String fileName) {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(fileName));


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
}
