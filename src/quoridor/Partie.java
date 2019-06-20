package quoridor;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
  * Cette classe gère les différents aspect de la partie
  */
public class Partie {

    private Mode mode;
    private Plateau plateau;
    private ArrayList<Joueur> joueurs;

    /**
      * Créé un nouvel objet Partie avec un fichier de configuration
      * @param fileName le nom du fichier de configuration
      * @param gui le booleen pour savoir si la partie se joue en mode graphique
      */
    public Partie(String fileName, boolean gui) {
        this.configuration(fileName);
        this.initialisation("Joueur1", "Joueur2", "Joueur3", "Joueur4", Difficulte.FACILE, Difficulte.FACILE, Difficulte.FACILE, Difficulte.FACILE);
        this.start(0, gui);
    }

    /**
      * Créé un nouvel objet Partie
      * @param mode Le mode de jeu de la partie
      * @param gui le booleen pour savoir si la partie se joue en mode graphique
      */
    public Partie(Mode mode, boolean gui) {
        this.mode = mode;
        this.initialisation("Joueur1", "Joueur2", "Joueur3", "Joueur4", Difficulte.FACILE, Difficulte.FACILE, Difficulte.FACILE, Difficulte.FACILE);
        this.start(0, gui);
    }

    /**
      * Créé un nouvel objet Partie avec un fichier de sauvegarde
      * @param save le booleen qui gère si on utilise une sauvegarde
      * @param saveFile le nom du fichier de configuration
      * @param gui le booleen pour savoir si la partie se joue en mode graphique
      */
    public Partie(boolean save, String saveFile, boolean gui) {
        this.charger(saveFile, gui);
    }

    /**
      * Créé un nouvel objet Partie
      * @param mode le mode de jeu
      * @param pseudo1 le nom du joueur 1
      * @param pseudo2 le nom du joueur 2
      * @param difficulte1 la difficulte de L'IA si le joueur 1 est un IA
      * @param difficulte2 la difficulte de L'IA si le joueur 2 est un IA
      */
    public Partie(Mode mode, String pseudo1, String pseudo2, Difficulte difficulte1, Difficulte difficulte2) {
        this.mode = mode;
        this.initialisation(pseudo1, pseudo2, null, null, difficulte1, difficulte2, null, null);
    }

    /**
      * Créé un nouvel objet Partie
      * @param mode le mode de jeu
      * @param pseudo1 le nom du joueur 1
      * @param pseudo2 le nom du joueur 2
      * @param pseudo3 le nom du joueur 3
      * @param pseudo4 le nom du joueur 4
      * @param difficulte1 la difficulte de L'IA si le joueur 1 est un IA
      * @param difficulte2 la difficulte de L'IA si le joueur 2 est un IA
      * @param difficulte3 la difficulte de L'IA si le joueur 3 est un IA
      * @param difficulte4 la difficulte de L'IA si le joueur 4 est un IA
      */
    public Partie(Mode mode, String pseudo1, String pseudo2, String pseudo3, String pseudo4, Difficulte difficulte1, Difficulte difficulte2, Difficulte difficulte3, Difficulte difficulte4) {
        this.mode = mode;
        this.initialisation(pseudo1, pseudo2, pseudo3, pseudo4, difficulte1, difficulte2, difficulte3, difficulte4);
    }

    public Partie(String fileName) {
        this.charger(fileName, true);
    }

    /**
      * Retourne le mode de jeu utilisé
      * @return le mode de jeu utilisé
      */
    public Mode getMode() {
        return mode;
    }

    /**
      * Retourne le plateau
      * @return l'objet plateau utilisé dans la partie
      */
    public Plateau getPlateau(){
      return this.plateau;
    }

    /**
      * Sauvegarde la partie
      * @param joueurTo le joueur qui doit jouer lors du chargement de la sauvegarde
      */
    public void sauvegarder(int joueurTo, boolean gui) {
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("sauvegarde_" + UUID.randomUUID().toString().split("-")[1] + ".bin")));

            out.writeUTF(this.mode.name());
            out.writeInt(joueurTo);

            for (Joueur joueur : this.joueurs) {
                out.writeUTF(joueur.getNom());
                out.writeInt(joueur.getNumero());
                out.writeUTF(joueur.getCouleur());
                if (joueur instanceof IA)
                    out.writeUTF(((IA)joueur).getDifficulte().name());
                out.writeUTF(joueur.getPion().getCoordonnee().getX1() + "/" + joueur.getPion().getCoordonnee().getY1());
                for (Barriere barriere : joueur.getBarrieres())
                    out.writeUTF(barriere.getCoordonnee().getX1() + "/" + barriere.getCoordonnee().getY1() + "/" + barriere.getCoordonnee().getX2() + "/" + barriere.getCoordonnee().getY2());
            }

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!gui)
            System.exit(0);
    }

    /**
      * Charge les données de sauvegarde contenues dans le fichier sélectionné
      * @param filename le fichier contenant les données à charger
      * @param gui le booleen pour savoir si la partie se joue en mode graphique
      */
    public void charger(String filename, boolean gui) {
        try {
            this.plateau = new Plateau(9, this);
            this.joueurs = new ArrayList<>();

            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(filename))));

            this.mode = Mode.valueOf(in.readUTF());
            int joueurTo = in.readInt();

            switch (this.mode) {
                case HH:
                    String nomJ1 = in.readUTF(); int numeroJ1 = in.readInt(); String couleurJ1 = in.readUTF(); String[] pionJ1 = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ1 = new ArrayList<>();
                    loadBarrieres(in, couleurJ1, barrieresJ1);
                    this.joueurs.add(new Humain(nomJ1, numeroJ1, couleurJ1, barrieresJ1, new Pion(couleurJ1, new Coordonnee(Integer.parseInt(pionJ1[0]), Integer.parseInt(pionJ1[1]), -1,-1)), this.plateau));
                    String nomJ2 = in.readUTF(); int numeroJ2 = in.readInt(); String couleurJ2 = in.readUTF(); String[] pionJ2 = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ2 = new ArrayList<>();
                    loadBarrieres(in, couleurJ2, barrieresJ2);
                    this.joueurs.add(new Humain(nomJ2, numeroJ2, couleurJ2, barrieresJ2, new Pion(couleurJ2, new Coordonnee(Integer.parseInt(pionJ2[0]), Integer.parseInt(pionJ2[1]), -1,-1)), this.plateau));
                    break;

                case HI:
                    String nomJ11 = in.readUTF(); int numeroJ11 = in.readInt(); String couleurJ11 = in.readUTF(); String[] pionJ11 = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ11 = new ArrayList<>();
                    loadBarrieres(in, couleurJ11, barrieresJ11);
                    this.joueurs.add(new Humain(nomJ11, numeroJ11, couleurJ11, barrieresJ11, new Pion(couleurJ11, new Coordonnee(Integer.parseInt(pionJ11[0]), Integer.parseInt(pionJ11[1]), -1,-1)), this.plateau));
                    String nomJ22 = in.readUTF(); int numeroJ22 = in.readInt(); String couleurJ22 = in.readUTF(); Difficulte difficulte = Difficulte.valueOf(in.readUTF()); String[] pionJ22 = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ22 = new ArrayList<>();
                    loadBarrieres(in, couleurJ22, barrieresJ22);
                    this.joueurs.add(new IA(nomJ22, numeroJ22, couleurJ22, barrieresJ22, new Pion(couleurJ22, new Coordonnee(Integer.parseInt(pionJ22[0]), Integer.parseInt(pionJ22[1]), -1,-1)), this.plateau, difficulte));
                    break;

                case II:
                    String nomJ111 = in.readUTF(); int numeroJ111 = in.readInt(); String couleurJ111 = in.readUTF(); Difficulte difficulteJ1 = Difficulte.valueOf(in.readUTF()); String[] pionJ111 = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ111 = new ArrayList<>();
                    loadBarrieres(in, couleurJ111, barrieresJ111);
                    this.joueurs.add(new IA(nomJ111, numeroJ111, couleurJ111, barrieresJ111, new Pion(couleurJ111, new Coordonnee(Integer.parseInt(pionJ111[0]), Integer.parseInt(pionJ111[1]), -1,-1)), this.plateau, difficulteJ1));
                    String nomJ222 = in.readUTF(); int numeroJ222 = in.readInt(); String couleurJ222 = in.readUTF(); Difficulte difficulteJ2 = Difficulte.valueOf(in.readUTF()); String[] pionJ222 = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ222 = new ArrayList<>();
                    loadBarrieres(in, couleurJ222, barrieresJ222);
                    this.joueurs.add(new IA(nomJ222, numeroJ222, couleurJ222, barrieresJ222, new Pion(couleurJ222, new Coordonnee(Integer.parseInt(pionJ222[0]), Integer.parseInt(pionJ222[1]), -1,-1)), this.plateau, difficulteJ2));
                    break;

                case HHHH:
                    String nomJ1HHHH = in.readUTF(); int numeroJ1HHHH = in.readInt(); String couleurJ1HHHH = in.readUTF(); String[] pionJ1HHHH = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ1HHHH = new ArrayList<>();
                    loadBarrieres(in, couleurJ1HHHH, barrieresJ1HHHH);
                    this.joueurs.add(new Humain(nomJ1HHHH, numeroJ1HHHH, couleurJ1HHHH, barrieresJ1HHHH, new Pion(couleurJ1HHHH, new Coordonnee(Integer.parseInt(pionJ1HHHH[0]), Integer.parseInt(pionJ1HHHH[1]), -1,-1)), this.plateau));
                    String nomJ2HHHH = in.readUTF(); int numeroJ2HHHH = in.readInt(); String couleurJ2HHHH = in.readUTF(); String[] pionJ2HHHH = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ2HHHH = new ArrayList<>();
                    loadBarrieres(in, couleurJ2HHHH, barrieresJ2HHHH);
                    this.joueurs.add(new Humain(nomJ2HHHH, numeroJ2HHHH, couleurJ2HHHH, barrieresJ2HHHH, new Pion(couleurJ2HHHH, new Coordonnee(Integer.parseInt(pionJ2HHHH[0]), Integer.parseInt(pionJ2HHHH[1]), -1,-1)), this.plateau));
                    String nomJ3 = in.readUTF(); int numeroJ3 = in.readInt(); String couleurJ3 = in.readUTF(); String[] pionJ3 = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ3 = new ArrayList<>();
                    loadBarrieres(in, couleurJ3, barrieresJ3);
                    this.joueurs.add(new Humain(nomJ3, numeroJ3, couleurJ3, barrieresJ3, new Pion(couleurJ3, new Coordonnee(Integer.parseInt(pionJ3[0]), Integer.parseInt(pionJ3[1]), -1,-1)), this.plateau));
                    String nomJ4 = in.readUTF(); int numeroJ4 = in.readInt(); String couleurJ4 = in.readUTF(); String[] pionJ4 = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ4 = new ArrayList<>();
                    loadBarrieres(in, couleurJ4, barrieresJ4);
                    this.joueurs.add(new Humain(nomJ4, numeroJ4, couleurJ4, barrieresJ4, new Pion(couleurJ4, new Coordonnee(Integer.parseInt(pionJ4[0]), Integer.parseInt(pionJ4[1]), -1,-1)), this.plateau));
                    break;

                case HHHI:
                    String nomJ1HHH = in.readUTF(); int numeroJ1HHH = in.readInt(); String couleurJ1HHH = in.readUTF(); String[] pionJ1HHH = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ1HHH = new ArrayList<>();
                    loadBarrieres(in, couleurJ1HHH, barrieresJ1HHH);
                    this.joueurs.add(new Humain(nomJ1HHH, numeroJ1HHH, couleurJ1HHH, barrieresJ1HHH, new Pion(couleurJ1HHH, new Coordonnee(Integer.parseInt(pionJ1HHH[0]), Integer.parseInt(pionJ1HHH[1]), -1,-1)), this.plateau));
                    String nomJ2HHH = in.readUTF(); int numeroJ2HHH = in.readInt(); String couleurJ2HHH = in.readUTF(); String[] pionJ2HHH = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ2HHH = new ArrayList<>();
                    loadBarrieres(in, couleurJ2HHH, barrieresJ2HHH);
                    this.joueurs.add(new Humain(nomJ2HHH, numeroJ2HHH, couleurJ2HHH, barrieresJ2HHH, new Pion(couleurJ2HHH, new Coordonnee(Integer.parseInt(pionJ2HHH[0]), Integer.parseInt(pionJ2HHH[1]), -1,-1)), this.plateau));
                    String nomJ3HHH = in.readUTF(); int numeroJ3HHH = in.readInt(); String couleurJ3HHH = in.readUTF(); String[] pionJ3HHH = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ3HHH = new ArrayList<>();
                    loadBarrieres(in, couleurJ3HHH, barrieresJ3HHH);
                    this.joueurs.add(new Humain(nomJ3HHH, numeroJ3HHH, couleurJ3HHH, barrieresJ3HHH, new Pion(couleurJ3HHH, new Coordonnee(Integer.parseInt(pionJ3HHH[0]), Integer.parseInt(pionJ3HHH[1]), -1,-1)), this.plateau));
                    String nomJ4HHH = in.readUTF(); int numeroJ4HHH = in.readInt(); String couleurJ4HHH = in.readUTF(); Difficulte difficulteJ4HHH = Difficulte.valueOf(in.readUTF()); String[] pionJ4HHH = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ4HHH = new ArrayList<>();
                    loadBarrieres(in, couleurJ4HHH, barrieresJ4HHH);
                    this.joueurs.add(new IA(nomJ4HHH, numeroJ4HHH, couleurJ4HHH, barrieresJ4HHH, new Pion(couleurJ4HHH, new Coordonnee(Integer.parseInt(pionJ4HHH[0]), Integer.parseInt(pionJ4HHH[1]), -1,-1)), this.plateau, difficulteJ4HHH));
                    break;

                case HHII:
                    String nomJ1HH = in.readUTF(); int numeroJ1HH = in.readInt(); String couleurJ1HH = in.readUTF(); String[] pionJ1HH = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ1HH = new ArrayList<>();
                    loadBarrieres(in, couleurJ1HH, barrieresJ1HH);
                    this.joueurs.add(new Humain(nomJ1HH, numeroJ1HH, couleurJ1HH, barrieresJ1HH, new Pion(couleurJ1HH, new Coordonnee(Integer.parseInt(pionJ1HH[0]), Integer.parseInt(pionJ1HH[1]), -1,-1)), this.plateau));
                    String nomJ2HH = in.readUTF(); int numeroJ2HH = in.readInt(); String couleurJ2HH = in.readUTF(); String[] pionJ2HH = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ2HH = new ArrayList<>();
                    loadBarrieres(in, couleurJ2HH, barrieresJ2HH);
                    this.joueurs.add(new Humain(nomJ2HH, numeroJ2HH, couleurJ2HH, barrieresJ2HH, new Pion(couleurJ2HH, new Coordonnee(Integer.parseInt(pionJ2HH[0]), Integer.parseInt(pionJ2HH[1]), -1,-1)), this.plateau));
                    String nomJ3HH = in.readUTF(); int numeroJ3HH = in.readInt(); String couleurJ3HH = in.readUTF(); Difficulte difficulteJ3HH = Difficulte.valueOf(in.readUTF()); String[] pionJ3HH = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ3HH = new ArrayList<>();
                    loadBarrieres(in, couleurJ3HH, barrieresJ3HH);
                    this.joueurs.add(new IA(nomJ3HH, numeroJ3HH, couleurJ3HH, barrieresJ3HH, new Pion(couleurJ3HH, new Coordonnee(Integer.parseInt(pionJ3HH[0]), Integer.parseInt(pionJ3HH[1]), -1,-1)), this.plateau, difficulteJ3HH));
                    String nomJ4HH = in.readUTF(); int numeroJ4HH = in.readInt(); String couleurJ4HH = in.readUTF(); Difficulte difficulteJ4HH = Difficulte.valueOf(in.readUTF()); String[] pionJ4HH = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ4HH = new ArrayList<>();
                    loadBarrieres(in, couleurJ4HH, barrieresJ4HH);
                    this.joueurs.add(new IA(nomJ4HH, numeroJ4HH, couleurJ4HH, barrieresJ4HH, new Pion(couleurJ4HH, new Coordonnee(Integer.parseInt(pionJ4HH[0]), Integer.parseInt(pionJ4HH[1]), -1,-1)), this.plateau, difficulteJ4HH));
                    break;

                    case HIII:
                    String nomJ1H = in.readUTF(); int numeroJ1H = in.readInt(); String couleurJ1H = in.readUTF(); String[] pionJ1H = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ1H = new ArrayList<>();
                    loadBarrieres(in, couleurJ1H, barrieresJ1H);
                    this.joueurs.add(new Humain(nomJ1H, numeroJ1H, couleurJ1H, barrieresJ1H, new Pion(couleurJ1H, new Coordonnee(Integer.parseInt(pionJ1H[0]), Integer.parseInt(pionJ1H[1]), -1,-1)), this.plateau));
                    String nomJ2H = in.readUTF(); int numeroJ2H = in.readInt(); String couleurJ2H = in.readUTF();  Difficulte difficulteJ2H = Difficulte.valueOf(in.readUTF()); String[] pionJ2H = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ2H = new ArrayList<>();
                    loadBarrieres(in, couleurJ2H, barrieresJ2H);
                    this.joueurs.add(new IA(nomJ2H, numeroJ2H, couleurJ2H, barrieresJ2H, new Pion(couleurJ2H, new Coordonnee(Integer.parseInt(pionJ2H[0]), Integer.parseInt(pionJ2H[1]), -1,-1)), this.plateau, difficulteJ2H));
                    String nomJ3H = in.readUTF(); int numeroJ3H = in.readInt(); String couleurJ3H = in.readUTF(); Difficulte difficulteJ3H = Difficulte.valueOf(in.readUTF()); String[] pionJ3H = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ3H = new ArrayList<>();
                    loadBarrieres(in, couleurJ3H, barrieresJ3H);
                    this.joueurs.add(new IA(nomJ3H, numeroJ3H, couleurJ3H, barrieresJ3H, new Pion(couleurJ3H, new Coordonnee(Integer.parseInt(pionJ3H[0]), Integer.parseInt(pionJ3H[1]), -1,-1)), this.plateau, difficulteJ3H));
                    String nomJ4H = in.readUTF(); int numeroJ4H = in.readInt(); String couleurJ4H = in.readUTF(); Difficulte difficulteJ4H = Difficulte.valueOf(in.readUTF()); String[] pionJ4H = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ4H = new ArrayList<>();
                    loadBarrieres(in, couleurJ4H, barrieresJ4H);
                    this.joueurs.add(new IA(nomJ4H, numeroJ4H, couleurJ4H, barrieresJ4H, new Pion(couleurJ4H, new Coordonnee(Integer.parseInt(pionJ4H[0]), Integer.parseInt(pionJ4H[1]), -1,-1)), this.plateau, difficulteJ4H));
                    break;

                case IIII:
                    String nomJ1I = in.readUTF(); int numeroJ1I = in.readInt(); String couleurJ1I = in.readUTF(); Difficulte difficulteJ1I = Difficulte.valueOf(in.readUTF());  String[] pionJ1I = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ1I = new ArrayList<>();
                    loadBarrieres(in, couleurJ1I, barrieresJ1I);
                    this.joueurs.add(new IA(nomJ1I, numeroJ1I, couleurJ1I, barrieresJ1I, new Pion(couleurJ1I, new Coordonnee(Integer.parseInt(pionJ1I[0]), Integer.parseInt(pionJ1I[1]), -1,-1)), this.plateau, difficulteJ1I));
                    String nomJ2I = in.readUTF(); int numeroJ2I = in.readInt(); String couleurJ2I = in.readUTF();  Difficulte difficulteJ2I = Difficulte.valueOf(in.readUTF()); String[] pionJ2I = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ2I = new ArrayList<>();
                    loadBarrieres(in, couleurJ2I, barrieresJ2I);
                    this.joueurs.add(new IA(nomJ2I, numeroJ2I, couleurJ2I, barrieresJ2I, new Pion(couleurJ2I, new Coordonnee(Integer.parseInt(pionJ2I[0]), Integer.parseInt(pionJ2I[1]), -1,-1)), this.plateau, difficulteJ2I));
                    String nomJ3I = in.readUTF(); int numeroJ3I = in.readInt(); String couleurJ3I = in.readUTF(); Difficulte difficulteJ3I = Difficulte.valueOf(in.readUTF()); String[] pionJ3I = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ3I = new ArrayList<>();
                    loadBarrieres(in, couleurJ3I, barrieresJ3I);
                    this.joueurs.add(new IA(nomJ3I, numeroJ3I, couleurJ3I, barrieresJ3I, new Pion(couleurJ3I, new Coordonnee(Integer.parseInt(pionJ3I[0]), Integer.parseInt(pionJ3I[1]), -1,-1)), this.plateau, difficulteJ3I));
                    String nomJ4I = in.readUTF(); int numeroJ4I = in.readInt(); String couleurJ4I = in.readUTF(); Difficulte difficulteJ4I = Difficulte.valueOf(in.readUTF()); String[] pionJ4I = in.readUTF().split("/");
                    ArrayList<Barriere> barrieresJ4I = new ArrayList<>();
                    loadBarrieres(in, couleurJ4I, barrieresJ4I);
                    this.joueurs.add(new IA(nomJ4I, numeroJ4I, couleurJ4I, barrieresJ4I, new Pion(couleurJ4I, new Coordonnee(Integer.parseInt(pionJ4I[0]), Integer.parseInt(pionJ4I[1]), -1,-1)), this.plateau, difficulteJ4I));
                    break;
            }
            in.close();

            if (!gui)
                this.start(joueurTo, gui);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
      * Récupère les données des barrières dans le fichier de sauvegarde et les mets dans la liste de barrière du joueur
      * @param in l'objet qui permet de lire le fichier
      * @param couleur la couleur du joueur qui a posé la barrière
      * @param barrieres la liste de barrière du joueur
      * @throws IOException IOException
      */
    private void loadBarrieres(DataInputStream in, String couleur, ArrayList<Barriere> barrieres) throws IOException {
        for (int b = 0; b < 20/this.mode.name().length(); b++) {
            String[] barriereJ111 = in.readUTF().split("/");
            barrieresJ111.add(new Barriere(couleurJ111, new Coordonnee(Integer.parseInt(barriereJ111[0]), Integer.parseInt(barriereJ111[1]), Integer.parseInt(barriereJ111[2]), Integer.parseInt(barriereJ111[3]))));
        }
    }

    /**
      * Initialise les différents éléments constants de la partie
      * @param pseudo1 le nom du joueur 1
      * @param pseudo2 le nom du joueur 2
      * @param pseudo3 le nom du joueur 3
      * @param pseudo4 le nom du joueur 4
      * @param difficulte1 la difficulte de L'IA si le joueur 1 est un IA
      * @param difficulte2 la difficulte de L'IA si le joueur 2 est un IA
      * @param difficulte3 la difficulte de L'IA si le joueur 3 est un IA
      * @param difficulte4 la difficulte de L'IA si le joueur 4 est un IA
      */
    private void initialisation(String pseudo1, String pseudo2, String pseudo3, String pseudo4, Difficulte difficulte1, Difficulte difficulte2, Difficulte difficulte3, Difficulte difficulte4) {
        this.plateau = new Plateau(9, this);
        this.joueurs = new ArrayList<Joueur>();

        switch (this.mode) {
            case HH:
                this.joueurs.add(new Humain(pseudo1, 1, "ROUGE", null, new Pion("ROUGE", new Coordonnee(0,(int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau));
                this.joueurs.add(new Humain(pseudo2, 2, "BLEU", null, new Pion("BLEU", new Coordonnee(plateau.getTaille()-1, (int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau));
                break;

            case HI:
                this.joueurs.add(new Humain(pseudo1, 1, "ROUGE", null, new Pion("ROUGE", new Coordonnee(0,(int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau));
                this.joueurs.add(new IA(pseudo2, 2, "BLEU", null, new Pion("BLEU", new Coordonnee(plateau.getTaille()-1, (int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau, difficulte2));
                break;

            case II:
                this.joueurs.add(new IA(pseudo1, 1, "ROUGE", null, new Pion("ROUGE", new Coordonnee(0,(int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau, difficulte1));
                this.joueurs.add(new IA(pseudo2, 2, "BLEU", null, new Pion("BLEU", new Coordonnee(plateau.getTaille()-1, (int) Math.round((double)plateau.getTaille()/2)-1, -1, -1)), plateau, difficulte2));
                break;

            case HHHH:
                this.joueurs.add(new Humain(pseudo1, 1, "ROUGE", null, new Pion("ROUGE", new Coordonnee(0,(int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau));
                this.joueurs.add(new Humain(pseudo2, 2, "BLEU", null, new Pion("BLEU", new Coordonnee(plateau.getTaille()-1, (int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau));
                this.joueurs.add(new Humain(pseudo3, 3, "VERT", null, new Pion("VERT", new Coordonnee((int) Math.round((double)plateau.getTaille()/2)-1, plateau.getTaille()-1,-1,-1)), plateau));
                this.joueurs.add(new Humain(pseudo4, 4, "JAUNE", null, new Pion("JAUNE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2)-1, 0,-1,-1)), plateau));
                break;

            case HHHI:
                this.joueurs.add(new Humain(pseudo1, 1, "ROUGE", null, new Pion("ROUGE", new Coordonnee(0,(int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau));
                this.joueurs.add(new Humain(pseudo2, 2, "BLEU", null, new Pion("BLEU", new Coordonnee(plateau.getTaille()-1, (int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau));
                this.joueurs.add(new Humain(pseudo3, 3, "VERT", null, new Pion("VERT", new Coordonnee((int) Math.round((double)plateau.getTaille()/2)-1, plateau.getTaille()-1,-1,-1)), plateau));
                this.joueurs.add(new IA(pseudo4, 4, "JAUNE", null, new Pion("JAUNE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2)-1, 0,-1,-1)), plateau, difficulte4));
                break;

            case HHII:
                this.joueurs.add(new Humain(pseudo1, 1, "ROUGE", null, new Pion("ROUGE", new Coordonnee(0,(int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau));
                this.joueurs.add(new Humain(pseudo2, 2, "BLEU", null, new Pion("BLEU", new Coordonnee(plateau.getTaille()-1, (int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau));
                this.joueurs.add(new IA(pseudo3, 3, "VERT", null, new Pion("VERT", new Coordonnee((int) Math.round((double)plateau.getTaille()/2)-1, plateau.getTaille()-1,-1,-1)), plateau, difficulte3));
                this.joueurs.add(new IA(pseudo4, 4, "JAUNE", null, new Pion("JAUNE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2)-1, 0,-1,-1)), plateau, difficulte4));
                break;

            case HIII:
                this.joueurs.add(new Humain(pseudo1, 1, "ROUGE", null, new Pion("ROUGE", new Coordonnee(0,(int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau));
                this.joueurs.add(new IA(pseudo2, 2, "BLEU", null, new Pion("BLEU", new Coordonnee(plateau.getTaille()-1, (int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau, difficulte2));
                this.joueurs.add(new IA(pseudo3, 3, "VERT", null, new Pion("VERT", new Coordonnee((int) Math.round((double)plateau.getTaille()/2)-1, plateau.getTaille()-1,-1,-1)), plateau, difficulte3));
                this.joueurs.add(new IA(pseudo4, 4, "JAUNE", null, new Pion("JAUNE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2)-1, 0,-1,-1)), plateau, difficulte4));
                break;

            case IIII:
                this.joueurs.add(new IA(pseudo1, 1, "ROUGE", null, new Pion("ROUGE", new Coordonnee(0,(int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau, difficulte1));
                this.joueurs.add(new IA(pseudo2, 2, "BLEU", null, new Pion("BLEU", new Coordonnee(plateau.getTaille()-1, (int) Math.round((double)plateau.getTaille()/2)-1,-1,-1)), plateau, difficulte2));
                this.joueurs.add(new IA(pseudo3, 3, "VERT", null, new Pion("VERT", new Coordonnee((int) Math.round((double)plateau.getTaille()/2)-1, plateau.getTaille()-1,-1,-1)), plateau, difficulte3));
                this.joueurs.add(new IA(pseudo4, 4, "JAUNE", null, new Pion("JAUNE", new Coordonnee((int) Math.round((double)plateau.getTaille()/2)-1, 0,-1,-1)), plateau, difficulte4));
        }
    }

    /**
      * Configure les éléments non constants de la partie à l'aide du fichier de configuration
      * @param fileName le nom du fichier de configuration
      */
    private void configuration(String fileName) {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(fileName));

            this.mode = Mode.HI;

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
      * Lance la partie
      * @param j le premier joueur qui doit jouer (utile pour reprendre une partie)
      * @param gui le booleen pour savoir si la partie se joue en mode graphique
      */
    public void start(int j, boolean gui) {
      boolean fin = false;
      int gagnant = 0;
      if (gui) {

      } else {
          if(this.mode.toString().length() == 2){
              while(!fin){
                  int i = j;
                  while(i < 2 && !fin) {
                      this.afficher();
                      Coordonnee finC = this.joueurs.get(i).getFin();
                      this.joueurs.get(i).jeu(gui,-1,-1);
                      if(finC.getX1() == this.joueurs.get(i).getPion().getCoordonnee().getX1() || finC.getY1() == this.joueurs.get(i).getPion().getCoordonnee().getY1()){
                          fin = true;
                          gagnant = i;
                      }
                      i++;
                  }
              }
          }
          else{
              while(!fin){
                  int i = j;
                  while(i < 4 && !fin) {
                      this.afficher();
                      Coordonnee finC = this.joueurs.get(i).getFin();
                      this.joueurs.get(i).jeu(gui,-1,-1);
                      if(finC.getX1() == this.joueurs.get(i).getPion().getCoordonnee().getX1() || finC.getY1() == this.joueurs.get(i).getPion().getCoordonnee().getY1()){
                          fin = true;
                          gagnant = i;
                      }
                      i++;
                  }
              }
          }
      }
    this.fin(gagnant);
  }

    /**
      * Termine la partie
      * @param gagnant L'indice du gagnant dans la liste joueurs
      */
    public void fin(int gagnant) {
      System.out.println("Le joueur "+this.joueurs.get(gagnant).getNom()+" a gagné !");
      this.afficher();
    }

    /**
      * Affiche le plateau en mode texte
      */
    public void afficher(){
      System.out.print("\t");
      for(int k = 0;k < this.plateau.getTaille();k++){
        System.out.print(k+"\t");
      }
      System.out.println();
      System.out.print("\t");
      for(int k = 0;k < this.plateau.getTaille();k++){
        System.out.print("--\t");
      }
      System.out.println();
      for(int i = 0;i < this.plateau.getTaille();i++){
        System.out.print(i+"|\t");
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
          else {
            System.out.print(this.plateau.getValue(i,j)+"\t");
          }
        }
        System.out.println();
      }
    }

    /**
      * Récupère la liste de joueurs
      * @return la lsite de joueurss
      */
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
}
