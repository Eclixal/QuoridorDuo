package quoridor;

import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.util.Scanner;

public class Controlleur {

  private Partie partie;

  public Controlleur(){
    int premierJoueur = this.initialiserPartie();
    String nomGagnant = this.commencerPartie(premierJoueur);
    this.finPartie(nomGagnant);
  }

  public int initialiserPartie(){
    int ret = 0;
    Scanner scan = new Scanner(System.in);
    System.out.print("Fichier de sauvegarde ? (o/n) : ");
    String string = scan.nextLine();
    if (string.equals("o")) {
      String fileName = null;
      while (fileName == null) {
        System.out.print("Nom du fichier : ");
        try {
          String str = scan.nextLine();
          new DataInputStream(new BufferedInputStream(new FileInputStream(str)));
          fileName = str;
        }catch (Exception ignored) { }
      }
      this.partie = new Partie(fileName);
      ret = this.partie.getTour();
    } else if (string.equals("n")) {
      Mode mode = null;
      while (mode == null) {
        System.out.print("Mode de jeu ? (HH/HI/II/HHHH/HHHI/HHII/HIII/IIII) : ");
        try {
          mode = Mode.valueOf(scan.nextLine());
        } catch (IllegalArgumentException ignored) {}
      }
      if(mode == Mode.valueOf("HH") || mode == Mode.valueOf("HI") || mode == Mode.valueOf("II")){
        String joueur1 = null;
        String joueur2 = null;
        Difficulte difficulte1 = null;
        Difficulte difficulte2 = null;
        System.out.print("Nom du joueur 1 : ");
        try{
          joueur1 = scan.nextLine();
        } catch (IllegalArgumentException ignored) {}
        System.out.print("Nom du joueur 2 : ");
        try{
          joueur2 = scan.nextLine();
        } catch (IllegalArgumentException ignored) {}

        if(mode == Mode.valueOf("II")){
          System.out.print("Difficulte du joueur 1 : (FACILE/MOYEN)");
          try{
            difficulte1 = Difficulte.valueOf(scan.nextLine());
          } catch (IllegalArgumentException ignored) {}
        }
        if(mode == Mode.valueOf("HI") || mode == Mode.valueOf("II")){
          System.out.print("Difficulte du joueur 2 : (FACILE/MOYEN)");
          try{
            difficulte2 = Difficulte.valueOf(scan.nextLine());
          } catch (IllegalArgumentException ignored) {}
        }
        this.partie = new Partie(mode,joueur1,joueur2,difficulte1,difficulte2);
      }
      else{
        String joueur1 = null;
        String joueur2 = null;
        String joueur3 = null;
        String joueur4 = null;
        Difficulte difficulte1 = null;
        Difficulte difficulte2 = null;
        Difficulte difficulte3 = null;
        Difficulte difficulte4 = null;
        System.out.print("Nom du joueur 1 : ");
        try{
          joueur1 = scan.nextLine();
        } catch (IllegalArgumentException ignored) {}
        System.out.print("Nom du joueur 2 : ");
        try{
          joueur2 = scan.nextLine();
        } catch (IllegalArgumentException ignored) {}
        System.out.print("Nom du joueur 3 : ");
        try{
          joueur3 = scan.nextLine();
        } catch (IllegalArgumentException ignored) {}
        System.out.print("Nom du joueur 4 : ");
        try{
          joueur4 = scan.nextLine();
        } catch (IllegalArgumentException ignored) {}

        if(mode == Mode.valueOf("IIII")){
          System.out.print("Difficulte du joueur 1 : (FACILE/MOYEN)");
          try{
            difficulte1 = Difficulte.valueOf(scan.nextLine());
          } catch (IllegalArgumentException ignored) {}
        }
        if(mode == Mode.valueOf("HIII") || mode == Mode.valueOf("IIII")){
          System.out.print("Difficulte du joueur 2 : (FACILE/MOYEN)");
          try{
            difficulte2 = Difficulte.valueOf(scan.nextLine());
          } catch (IllegalArgumentException ignored) {}
        }
        if(mode == Mode.valueOf("HHII") || mode == Mode.valueOf("HIII") || mode == Mode.valueOf("IIII")){
          System.out.print("Difficulte du joueur 3 : (FACILE/MOYEN)");
          try{
            difficulte3 = Difficulte.valueOf(scan.nextLine());
          } catch (IllegalArgumentException ignored) {}
        }
        if(mode == Mode.valueOf("HHHI") || mode == Mode.valueOf("HHII") || mode == Mode.valueOf("HIII") || mode == Mode.valueOf("IIII")){
          System.out.print("Difficulte du joueur 4 : (FACILE/MOYEN)");
          try{
            difficulte4 = Difficulte.valueOf(scan.nextLine());
          } catch (IllegalArgumentException ignored) {}
        }
        this.partie = new Partie(mode,joueur1,joueur2,joueur3,joueur4,difficulte1,difficulte2,difficulte3,difficulte4);
      }
    }else if (string.equals("save")) {

    }
    else{
      System.out.println("Commande non vaide");
    }
    return ret;
  }

  public String commencerPartie(int premierJoueur){
    boolean fin = false;
    String nomGagnant = "";
    String message;
    int tour = 0;
    while(!fin){
      this.partie.afficher();
      if((this.partie.getMode().toString().length() == 2 && tour == 2) || (this.partie.getMode().toString().length() == 4 && tour == 4)){
        tour = 0;
      }
      if(this.partie.getJoueurs().get(tour).isIA()){
        try{
          Thread.sleep(1000);
        } catch(InterruptedException e){
          System.out.println(e.getMessage());
        }
        this.partie.jouer(tour, -1, -1);
      }
      else{
        nomGagnant = this.partie.getJoueurs().get(tour).getNom();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Joueur "+nomGagnant+" : 1 - Déplacer le pion | 2 - Placer une barrière | 3 - Sauvegarder et quitter");
        String choix = scanner.nextLine();
        int x = 0;
        int y = 0;
        if(choix.equals("1")){
          System.out.print("Position vertical : ");
          x = Integer.parseInt(scanner.nextLine());
          System.out.print("Position horizontal :");
          y = Integer.parseInt(scanner.nextLine());
        }
        else if(choix.equals("2")){
          System.out.print("Position vertical de la première case de la barrière : ");
          x = Integer.parseInt(scanner.nextLine());
          System.out.print("Position horizontal de la première case de la barrière :");
          y = Integer.parseInt(scanner.nextLine());
        }
        else if(choix.equals("3")){
          this.partie.sauvegarder(tour);
          System.exit(0);
        }
        else{
          message = "Numéro de choix impossible !";
        }
        message = this.partie.jouer(tour, x, y);
        if(message.equals("")){
          tour++;
        }
        else if(message.equals("gagné")){
          fin = true;
        }
        else{
          System.out.println(message);
        }
      }
    }
    return nomGagnant;
  }

  public void finPartie(String nomGagnant) {
    this.partie.afficher();
    System.out.println("Le joueur "+nomGagnant+" a gagné !");
  }
}
