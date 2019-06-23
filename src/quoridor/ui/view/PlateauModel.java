package quoridor.ui.view;

import javax.swing.table.AbstractTableModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Image;

/**
  * Gère la dimensions et les composants du tableau (plateau)
  */
public class PlateauModel extends AbstractTableModel{

  private int[][] damier;

/**
  * Initialise le tableau
  * @param damier le plteau du jeu
  */
  public PlateauModel(int[][] damier){
    this.damier = damier;
  }

/**
  * Renvoie la valeur de la case aux coordonnées données
  * @param r la ligne
  * @param c la colonne
  */
  public Object getValueAt(int r, int c){
    Object ret = new Object();
    int valeur = this.damier[r][c];
    if(valeur == 1){
      ImageIcon image = new ImageIcon("images/Rond_rouge.png");
      ImageIcon icon = new ImageIcon(image.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
      ret = icon;
    }
    else if(valeur == 2){
      ImageIcon image = new ImageIcon("images/Rond_bleu.png");
      ImageIcon icon = new ImageIcon(image.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
      ret = icon;
    }
    else if(valeur == 3){
      ImageIcon image = new ImageIcon("images/Rond_vert.png");
      ImageIcon icon = new ImageIcon(image.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
      ret = icon;
    }
    else if(valeur == 4){
      ImageIcon image = new ImageIcon("images/Rond_jaune.png");
      ImageIcon icon = new ImageIcon(image.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
      ret = icon;
    }
    else if(valeur == 5){
      ImageIcon image = new ImageIcon("images/Barriere.jpg");
      ImageIcon icon = new ImageIcon(image.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
      ret = icon;
    }
    else{
      ImageIcon image = new ImageIcon();
      ret = image;
    }
    return ret;
  }

/**
  * Renvoie le nombre de colonne
  */
  public int getColumnCount(){
    return this.damier.length;
  }

/**
  * Renvoie  le nombre de ligne
  */
  public int getRowCount(){
    return this.damier.length;
  }

/**
  * Renvoie le type de classe dans la colonne du tableau
  */
  public Class getColumnClass(int c) {
      return this.getValueAt(0,c).getClass();
   }
}
