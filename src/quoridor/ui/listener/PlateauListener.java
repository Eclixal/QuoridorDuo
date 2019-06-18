package quoridor.ui.listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlateauListener extends MouseAdapter {

  @Override
  public void mouseClicked(MouseEvent e) {
    JTable jTable = (JTable) e.getSource();
    int row = jTable.rowAtPoint(e.getPoint());
    int col = jTable.columnAtPoint(e.getPoint());
  }
}
