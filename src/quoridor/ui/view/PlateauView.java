package quoridor.ui.view;

import quoridor.Plateau;
import quoridor.ui.listener.PlateauListener;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class PlateauView extends JPanel {

  private Plateau plateau;

  public PlateauView(Plateau plateau){
    this.plateau = plateau;
    this.plateau.setValue(3,4,5);
    this.plateau.setValue(3,5,5);
    this.plateau.setValue(3,6,5);

    this.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(10,10,10,10);
    constraints.fill = GridBagConstraints.VERTICAL;


    PlateauModel model = new PlateauModel(this.plateau.getDAMIER());

    JTable table = new JTable(model);

    table.addMouseListener(new PlateauListener());

    table.setRowSelectionAllowed(false);
    table.setRowHeight(50);
    for(int i = 0;i < this.plateau.getTaille();i++){
      if(i%2 == 0){
        TableColumn column = table.getColumnModel().getColumn(i);
        column.setMinWidth(50);
        column.setMaxWidth(50);
      }
      else{
        table.setRowHeight(i,20);
        TableColumn column = table.getColumnModel().getColumn(i);
        column.setMinWidth(20);
        column.setMaxWidth(20);
      }
    }

    this.add(table, constraints);
  }
}
