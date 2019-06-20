package quoridor.ui.listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

import quoridor.ui.view.PauseView;
import quoridor.ui.view.PlateauView;
import quoridor.Joueur;

public class PlateauListener extends MouseAdapter {

  private Joueur joueur;
  private PlateauView plateauView;

  public PlateauListener(PlateauView plateauView, Joueur joueur){
    this.joueur = joueur;
    this.plateauView = plateauView;
  }

  public void mouseClicked(MouseEvent e) {
    if (e.getSource() == plateauView.getTable()) {
      JTable jTable = (JTable) e.getSource();
      int row = jTable.rowAtPoint(e.getPoint());
      int col = jTable.columnAtPoint(e.getPoint());
      String resultat = this.joueur.jeu(true, row, col);
      if(resultat.isEmpty()){
        this.plateauView.changerJoueur();
      }
      else{
        this.plateauView.getErrorMessage().setText(resultat);
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
          @Override
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
