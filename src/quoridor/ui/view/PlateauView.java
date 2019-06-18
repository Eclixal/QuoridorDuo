package quoridor.ui.view;

import quoridor.Plateau;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.table.TableColumn;

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

    frame.pack();
  }
}
