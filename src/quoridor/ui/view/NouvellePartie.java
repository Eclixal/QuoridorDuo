package quoridor.ui.view;

import quoridor.ui.MainFrame;
import quoridor.ui.custom.JButtonMenu;
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
  * Gère la fenêtre d'une nouvelle partie
  */
public class NouvellePartie extends JPanel {

  private JButtonMenu retour;
  private JLabel jLabel;

  private JButtonMenu jButtonMenu2J;
  private JButtonMenu jButtonMenu4J;

  private ContinuousAudioDataStream audioDataStream;

  private MainFrame mainFrame;

/**
  * Affiche la fenêtre d'une nouvelle partie
  * @param mainFrame Le frame principal qui gère tous les conposants
  */
  public NouvellePartie(MainFrame mainFrame) {
      this.mainFrame = mainFrame;
      this.setLayout(new GridBagLayout());

      this.setBackground(Color.decode("#b4e9e2"));

      GridBagConstraints constraints = new GridBagConstraints();
      constraints.insets = new Insets(10,10,10,10);

      this.jLabel = new JLabel("Nouvelle Partie");
      this.jLabel.setBorder(new EmptyBorder(10, 10, 50, 10));
      this.jLabel.setFont(new Font("Courier New", Font.BOLD, 120));
      this.jLabel.setForeground(Color.decode("#309286"));

      constraints.gridy = 0;
      constraints.gridx = 0;

      this.add(this.jLabel, constraints);

      JPanel jPanel = new JPanel();
      jPanel.setLayout(new GridBagLayout());
      GridBagConstraints constraints1 = new GridBagConstraints();
      constraints1.insets = new Insets(30,50,30,50);
      jPanel.setBackground(Color.decode("#b4e9e2"));

      this.jButtonMenu2J = new JButtonMenu("2 joueurs");
      this.jButtonMenu2J.setMargin(new Insets(50,50,50,50));
      this.jButtonMenu2J.setFont(new Font("Courier New", Font.BOLD, 19));
      this.jButtonMenu2J.setBackground(Color.decode("#309286"));
      this.jButtonMenu2J.setForeground(Color.decode("#ebefd0"));
      this.jButtonMenu2J.setBorderPainted(false);
      this.jButtonMenu2J.setFocusPainted(false);
      this.jButtonMenu2J.setHoverBackgroundColor(Color.decode("#59a59b"));
      this.jButtonMenu2J.setPressedBackgroundColor(Color.decode("#64afa5"));
      this.jButtonMenu2J.addMouseListener(new NouvellePartieListener(this));

      constraints1.gridy = 0;
      constraints1.gridx = 0;

      jPanel.add(this.jButtonMenu2J, constraints1);

      this.jButtonMenu4J = new JButtonMenu("4 joueurs");
      this.jButtonMenu4J.setMargin(new Insets(50,50,50,50));
      this.jButtonMenu4J.setFont(new Font("Courier New", Font.BOLD, 19));
      this.jButtonMenu4J.setBackground(Color.decode("#309286"));
      this.jButtonMenu4J.setForeground(Color.decode("#ebefd0"));
      this.jButtonMenu4J.setBorderPainted(false);
      this.jButtonMenu4J.setFocusPainted(false);
      this.jButtonMenu4J.setHoverBackgroundColor(Color.decode("#59a59b"));
      this.jButtonMenu4J.setPressedBackgroundColor(Color.decode("#64afa5"));
      this.jButtonMenu4J.addMouseListener(new NouvellePartieListener(this));

      constraints1.gridy = 0;
      constraints1.gridx = 2;

      jPanel.add(this.jButtonMenu4J, constraints1);

      constraints.gridy = 1;
      constraints.gridx = 0;
      this.add(jPanel, constraints);

      constraints.fill = GridBagConstraints.HORIZONTAL;
      constraints.insets = new Insets(10,60,10,60);

      this.retour = new JButtonMenu("Retour");
      this.retour.setMargin(new Insets(20,10,20,10));
      this.retour.setFont(new Font("Courier New", Font.BOLD, 19));
      this.retour.setBackground(Color.decode("#309286"));
      this.retour.setForeground(Color.decode("#ebefd0"));
      this.retour.setBorderPainted(false);
      this.retour.setFocusPainted(false);
      this.retour.setHoverBackgroundColor(Color.decode("#59a59b"));
      this.retour.setPressedBackgroundColor(Color.decode("#64afa5"));
      this.retour.addMouseListener(new NouvellePartieListener(this));

      constraints.gridy = 2;
      constraints.gridx = 0;
      this.add(this.retour, constraints);

      music();
      this.setVisible(true);
  }

/**
  * Renvoie le bouton deux joueurs
  * @return Le bouton
  */
    public JButtonMenu getjButtonMenu2J() {
        return jButtonMenu2J;
    }

/**
  * Renvoie le bouton quatre joueurs
  * @return Le bouton
  */
    public JButtonMenu getjButtonMenu4J() {
        return jButtonMenu4J;
    }

/**
  * Renvoie le bouton retour
  * @return Le bouton
  */
    public JButtonMenu getRetour() {
        return retour;
    }

/**
  * Gère la musique de la fenêtre nouvelle partie
  */
    private void music() {
        try {
            AudioData data = new AudioStream(new FileInputStream("sons/main_theme_menu1.wav")).getData();
            audioDataStream = new ContinuousAudioDataStream(data);
            AudioPlayer.player.start(audioDataStream);
        } catch(IOException ignored) { }
    }

/**
  * Renvoie la musique qui doit continuer
  * @return la musique
  */
    public ContinuousAudioDataStream getAudioDataStream() {
        return audioDataStream;
    }

/**
  * Renvoie la fenêtre principal du mode graphique
  * @return la frame
  */
    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
