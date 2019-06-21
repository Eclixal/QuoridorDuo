package quoridor.ui.listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

import quoridor.Partie;
import quoridor.ui.view.PauseView;
import quoridor.ui.view.PlateauView;
import quoridor.Joueur;

public class PlateauListener extends MouseAdapter {

  private Partie partie;
  private PlateauView plateauView;
  private int tour;

  public PlateauListener(PlateauView plateauView, Partie partie, int tour){
    this.partie = partie;
    this.plateauView = plateauView;
    this.tour = tour;
  }

  public void mouseClicked(MouseEvent e) {
    if (e.getSource() == plateauView.getTable()) {
      JTable jTable = (JTable) e.getSource();
      int row = jTable.rowAtPoint(e.getPoint());
      int col = jTable.columnAtPoint(e.getPoint());
      String resultat = this.partie.jouer(tour, row, col);
      if(resultat.isEmpty()){
        this.plateauView.revalidate();
        this.plateauView.repaint();
        this.plateauView.changerJoueur();
      }
      else if(resultat.equals("gagné")){
        this.plateauView.finJeu();
      }
      else{
        this.plateauView.getErrorMessage().setText(resultat);
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
          public void run() {
            plateauView.getErrorMessage().setText("");
          }
        }, 2000);
      }
    } else if (e.getSource() == plateauView.getjButtonMenu()) {
      plateauView.setPause(true);
      plateauView.getMainFrame().setContentPane(new PauseView(plateauView.getMainFrame(), plateauView));
      plateauView.getMainFrame().validate();
    }
  }
}
