package quoridor.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
  * Gestion la fenêtre principal de la partie graphique
  */
public class MainFrame extends JFrame {

/**
  * Affiche une fenêtre en plein écran
  */
    public MainFrame() {
        this.setTitle("Quoridor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        device.setFullScreenWindow(this);
        this.setVisible(false);
        this.setVisible(true);
    }
}
