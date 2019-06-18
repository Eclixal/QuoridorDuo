package quoridor.ui.view;

import quoridor.Plateau;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.table.TableColumn;
import javax.swing.ListSelectionModel;

public class PlateauView {

  private Plateau plateau;

  public PlateauView(Plateau plateau){
    this.plateau = plateau;
    this.plateau.setValue(3,4,5);
    this.plateau.setValue(3,5,5);
    this.plateau.setValue(3,6,5);
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

    ListSelectionModel cellSelectionModel = table.getSelectionModel();
    cellSelectionModel.addListSelectionListener(new PlateauListener());

    frame.pack();
  }
}
