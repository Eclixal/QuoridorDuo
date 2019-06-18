package quoridor.ui.listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import quoridor.ui.view.PlateauView;
import quoridor.Joueur;

public class PlateauListener extends MouseAdapter {

  private Joueur joueur;
  private PlateauView plateauView;

  public PlateauListener(PlateauView plateauView, Joueur joueur){
    this.joueur = joueur;
  }

  public void mouseClicked(MouseEvent e) {
    JTable jTable = (JTable) e.getSource();
    int row = jTable.rowAtPoint(e.getPoint());
    int col = jTable.columnAtPoint(e.getPoint());
    if(this.joueur.jeu(true, row, col) == ""){
      this.plateauView.changerJoueur();
    }
    else{

    }
  }
}
