package view;

import javax.swing.table.AbstractTableModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PlateauModel extends AbstractTableModel{

  private int[][] damier;

  public PlateauModel(int[][] damier){
    this.damier = damier;
  }

  public Object getValueAt(int r, int c){
    Object ret = new Object();
    int valeur = this.damier[r][c];
    if(valeur == 0 && r%2 == 0 && c%2 == 0){
      JPanel panel = new JPanel();
      ret = panel;
    }
    else if(valeur == 1){
      ImageIcon image = new ImageIcon("../images/test.png");
      ret = image;
    }
    else if(valeur == 2){
      ImageIcon image = new ImageIcon("../images/pionBleu.png");
      ret = image;
    }
    else if(valeur == 3){
      ImageIcon image = new ImageIcon("../images/pionVert.png");
      ret = image;
    }
    else if(valeur == 4){
      ImageIcon image = new ImageIcon("../images/pionJaune.png");
      ret = image;
    }
    else if(valeur == 5){
      JPanel panel = new JPanel();
      ret = panel;
    }
    else if(valeur == 0 && r%2 == 0 && c%2 != 0){
      JPanel panel = new JPanel();
      ret = panel;
    }
    else if(valeur == 0 && r%2 != 0 && c%2 == 0){
      JPanel panel = new JPanel();
      ret = panel;
    }
    else if(valeur == 0 && r%2 != 0 && c%2 !=0){
      JPanel panel = new JPanel();
      ret = panel;
    }
    return ret;
  }

  public int getColumnCount(){
    return this.damier.length;
  }

  public int getRowCount(){
    return this.damier.length;
  }
}
