package view;

import quoridor.Plateau;
import javax.swing.JTable;
import javax.swing.JFrame;

public class PlateauView {

  private Plateau plateau;

  public PlateauView(Plateau plateau){
    this.plateau = plateau;
    this.initialise();
  }

  public void initialise(){
    JFrame frame = new JFrame("Quoridor");
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    PlateauModel model = new PlateauModel(this.plateau.getDAMIER());

    JTable table = new JTable(model);
    frame.add(table);
    table.setRowSelectionAllowed(false);
  }
}
