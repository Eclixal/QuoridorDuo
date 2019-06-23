package quoridor.ui.view;

import quoridor.ui.MainFrame;
import quoridor.ui.custom.JButtonMenu;
import quoridor.ui.listener.FinListener;
import quoridor.ui.listener.NouvellePartieListener;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

/**
  * Gère la frame de fin de partie
  */
public class FinDePartie extends JPanel {

    private MainFrame mainFrame;

    private JLabel jLabel;

    private JButtonMenu jButtonMenu;

/**
  * Affiche la fin de partie
  * @param mainFrame la frame principale
  * @param pseudo le pseudo du joueur qui a gagné
  */
    public FinDePartie(MainFrame mainFrame, String pseudo) {
        this.mainFrame = mainFrame;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#b4e9e2"));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;

        this.jLabel = new JLabel("<html><center>Victoire de<br> " + pseudo + "</center></html>");
        this.jLabel.setFont(new Font("Courier New", Font.BOLD, 120));
        this.jLabel.setBorder(new EmptyBorder(10, 10, 30, 10));
        this.jLabel.setForeground(Color.decode("#309286"));

        this.add(this.jLabel,  constraints);

        constraints.insets = new Insets(10,10,10,10);
        constraints.gridy = 1;
        constraints.gridx = 0;

        this.jButtonMenu = new JButtonMenu("Menu Principal");
        this.jButtonMenu.setMargin(new Insets(20,30,20,30));
        this.jButtonMenu.setFont(new Font("Courier New", Font.BOLD, 19));
        this.jButtonMenu.setBackground(Color.decode("#309286"));
        this.jButtonMenu.setForeground(Color.decode("#ebefd0"));
        this.jButtonMenu.setBorderPainted(false);
        this.jButtonMenu.setFocusPainted(false);
        this.jButtonMenu.setHoverBackgroundColor(Color.decode("#59a59b"));
        this.jButtonMenu.setPressedBackgroundColor(Color.decode("#64afa5"));
        this.jButtonMenu.addMouseListener(new FinListener(this));

        this.add(this.jButtonMenu, constraints);

        this.setVisible(true);
    }

/**
  * Renvoie le bouton de retour au menu
  * @return le bouton
  */
    public JButtonMenu getjButtonMenu() {
        return jButtonMenu;
    }

/**
  * Renvoie la frame principale
  * @return la frame
  */
    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
