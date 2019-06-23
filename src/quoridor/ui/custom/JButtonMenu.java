package quoridor.ui.custom;

import javax.swing.*;
import java.awt.*;

/**
  * Défini la forme et le fond des boutons du jeu
  */
public class JButtonMenu extends JButton {

    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

/**
  * Défini le texte du bouton
  * @param text Le texte du bouton
  */
    public JButtonMenu(String text) {
        super(text);
        super.setContentAreaFilled(false);
    }

/**
  * Modifie la couleur du bouton selon ce que la souris est et fait
  * @param g Le graphique du bouton
  */
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

/**
  * Obtenir la couleur du background quand on passe la souris dessus
  * @return La couleur
  */
    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

/**
  * Modifier la couleur du background quand on passe la souris dessus
  * @param hoverBackgroundColor la couleur quand on passe la souris sur le bouton
  */
    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    /**
      * Obtenir la couleur du background quand on clique dessus
      * @return La couleur
      */
    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    /**
      * Modifier la couleur du background quand on clique dessus
      * @param pressedBackgroundColor quand on clique sur le bouton
      */
    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
}
