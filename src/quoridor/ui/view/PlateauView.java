package quoridor.ui.view;

import quoridor.Plateau;
import quoridor.Partie;
import quoridor.Joueur;
import quoridor.ui.MainFrame;
import quoridor.ui.listener.PlateauListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class PlateauView extends JPanel {

  private ArrayList<Joueur> joueurs;
  private Plateau plateau;
  private int tour;
  private JTable table;
  private PlateauListener listener;

  private MainFrame mainFrame;

  public PlateauView(MainFrame mainFrame, Partie partie) {
    this.mainFrame = mainFrame;
    this.plateau = partie.getPlateau();
    this.joueurs = partie.getJoueurs();
    this.tour = 0;

    this.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(10,10,10,10);
    constraints.fill = GridBagConstraints.VERTICAL;


    PlateauModel model = new PlateauModel(this.plateau.getDAMIER());

    this.table = new JTable(model);

    if(this.joueurs.get(0).isIA()){
      this.joueurs.get(0).jeu(true, -1, -1);
      this.changerJoueur();
    }
    else{
      this.listener = new PlateauListener(this, this.joueurs.get(0));
      this.table.addMouseListener(this.listener);
    }

    this.table.setRowSelectionAllowed(false);
    this.table.setRowHeight(50);

    for(int i = 0;i < this.plateau.getTaille();i++){
      if(i%2 == 0){
        TableColumn column = this.table.getColumnModel().getColumn(i);
        column.setMinWidth(50);
        column.setMaxWidth(50);
      }
      else{
        table.setRowHeight(i,10);
        TableColumn column = this.table.getColumnModel().getColumn(i);
        column.setMinWidth(10);
        column.setMaxWidth(10);
      }
    }
    Color color = new Color(187, 120, 49, 255);
    this.table.setGridColor(color);

    this.add(this.table, constraints);
  }

  public void changerJoueur(){
    this.revalidate();
    this.repaint();
    if(!(this.joueurs.get(tour).isIA())){
      this.table.removeMouseListener(this.listener);
    }
    if(this.joueurs.get(tour).getFin().getX1() == this.joueurs.get(tour).getPion().getCoordonnee().getX1() || this.joueurs.get(tour).getFin().getY1() == this.joueurs.get(tour).getPion().getCoordonnee().getY1()){
      this.finJeu();
    }
    else{
      if(this.tour < this.joueurs.size()-1){
        this.tour++;
      }
      else{
        this.tour = 0;
      }
      if(this.joueurs.get(tour).isIA()){
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
          @Override
          public void run() {
            tourIA();
          }
        }, 250);
      }
      else{
        this.listener = new PlateauListener(this, this.joueurs.get(tour));
        this.table.addMouseListener(this.listener);
      }
    }
  }

  public void tourIA(){
    this.joueurs.get(tour).jeu(true, -1, -1);
    this.changerJoueur();
  }


  public MainFrame getMainFrame() {
    return mainFrame;
  }

  public void finJeu() {
    getMainFrame().setContentPane(new FinDePartie(mainFrame, this.joueurs.get(tour).getNom()));
    getMainFrame().validate();
  }
}
