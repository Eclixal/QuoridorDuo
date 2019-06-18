package quoridor.ui.view;

import quoridor.Plateau;
import quoridor.Partie;
import quoridor.Joueur;
import quoridor.ui.listener.PlateauListener;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class PlateauView extends JPanel {

  private ArrayList<Joueur> joueurs;
  private Plateau plateau;
  private int tour;
  private JTable table;

  public PlateauView(Partie partie){
    this.plateau = partie.getPlateau();
    this.joueurs = partie.getJoueurs();
    this.tour = 0;
    this.plateau.setValue(3,4,5);
    this.plateau.setValue(3,5,5);
    this.plateau.setValue(3,6,5);

    this.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(10,10,10,10);
    constraints.fill = GridBagConstraints.VERTICAL;


    PlateauModel model = new PlateauModel(this.plateau.getDAMIER());

    this.table = new JTable(model);

    if(this.joueurs.get(0).isIA()){
      this.joueurs.get(0).jeu(true, -1, -1);
    }
    else{
      this.table.addMouseListener(new PlateauListener(this, this.joueurs.get(0)));
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
        table.setRowHeight(i,20);
        TableColumn column = this.table.getColumnModel().getColumn(i);
        column.setMinWidth(20);
        column.setMaxWidth(20);
      }
    }

    this.add(this.table, constraints);
  }

  public void changerJoueur(){
    if(this.tour != 3){
      this.tour++;
    }
    else{
      this.tour = 0;
    }
    if(this.joueurs.get(tour).isIA()){
      this.joueurs.get(tour).jeu(true, -1, -1);
    }
    else{
      this.table.addMouseListener(new PlateauListener(this, this.joueurs.get(tour)));
    }
  }
}
