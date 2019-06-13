package quoridor;

/**
  * Cette classe gère les pions utilisés par les joueurs
  */
public class Pion {

    private final String COULEUR;
    private Coordonnee coordonnee;

    /**
      * Créé un nouvel objet Pion
      * @param couleur la couleur du joueur (désigne une forme en mode texte)
      * @param coordonnee les coordonnées de départ du pion
      */
    public Pion(String couleur, Coordonnee coordonnee) {
        this.COULEUR = couleur;
        this.coordonnee = coordonnee;
    }

    /**
      * Retourne la couleur du pion
      * @return la couleur du pion
      */
    public String getCouleur() {
        return this.COULEUR;
    }

    /**
      * Retourne les coordonnées du pion
      * @return les coordonnées du pion sous la forme d'un objet Coordonnee
      */
    public Coordonnee getCoordonnee() {
        return this.coordonnee;
    }

    /**
      * Modifie les coordonnées du pion
      * @param coordonnees les nouvelles coordonnées du pion
      */
    public void setCoordonnee(Coordonnee coordonnees) {
        this.coordonnee = coordonnees;
    }
}
