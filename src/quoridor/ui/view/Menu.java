package quoridor.ui.view;

import quoridor.ui.MainFrame;
import quoridor.ui.custom.JButtonMenu;
import quoridor.ui.listener.MenuListener;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
  * Gère le menu du mode graphique
  */
public class Menu extends JPanel {

  private JLabel label;
  private JButtonMenu nouvelle;
  private JButtonMenu charger;
  private JButtonMenu quitter;
  private ContinuousAudioDataStream audioDataStream;

  private MainFrame mainFrame;

/**
  * Affiche le menu grâce au MainFrame
  * @param mainFrame Le frame principal qui gère tous les conposants
  */
  public Menu(MainFrame mainFrame) {
    this.mainFrame = mainFrame;

    this.setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(10,10,10,10);

    this.setBackground(Color.decode("#b4e9e2"));

    this.label = new JLabel("Quoridor");
    this.label.setBorder(new EmptyBorder(10, 10, 100, 10));
    this.label.setFont(new Font("Courier New", Font.BOLD, 120));
    this.label.setForeground(Color.decode("#309286"));

    constraints.gridy = 0;
    constraints.gridx = 0;

    this.add(this.label, constraints);

    constraints.fill = GridBagConstraints.HORIZONTAL;

    this.nouvelle = new JButtonMenu("Nouvelle partie");
    this.nouvelle.setMargin(new Insets(10,10,10,10));
    this.nouvelle.setFont(new Font("Courier New", Font.BOLD, 19));
    this.nouvelle.setBackground(Color.decode("#309286"));
    this.nouvelle.setForeground(Color.decode("#ebefd0"));
    this.nouvelle.setBorderPainted(false);
    this.nouvelle.setFocusPainted(false);
    this.nouvelle.setHoverBackgroundColor(Color.decode("#59a59b"));
    this.nouvelle.setPressedBackgroundColor(Color.decode("#64afa5"));
    this.nouvelle.addMouseListener(new MenuListener(this));

    constraints.gridx = 0;
    constraints.gridy = 1;

    this.add(this.nouvelle, constraints);

    this.charger = new JButtonMenu("Charger une partie");
    this.charger.setMargin(new Insets(10,10,10,10));
    this.charger.setFont(new Font("Courier New", Font.BOLD, 19));
    this.charger.setBorderPainted(false);
    this.charger.setFocusPainted(false);
    this.charger.setBackground(Color.decode("#309286"));
    this.charger.setForeground(Color.decode("#ebefd0"));
    this.charger.setHoverBackgroundColor(Color.decode("#59a59b"));
    this.charger.setPressedBackgroundColor(Color.decode("#64afa5"));
    this.charger.addMouseListener(new MenuListener(this));

    constraints.gridx = 0;
    constraints.gridy = 2;

    this.add(this.charger, constraints);

    this.quitter = new JButtonMenu("Quitter");
    this.quitter.setMargin(new Insets(10,10,10,10));
    this.quitter.setFont(new Font("Courier New", Font.BOLD, 19));
    this.quitter.setBorderPainted(false);
    this.quitter.setFocusPainted(false);
    this.quitter.setBackground(Color.decode("#309286"));
    this.quitter.setForeground(Color.decode("#ebefd0"));
    this.quitter.setHoverBackgroundColor(Color.decode("#59a59b"));
    this.quitter.setPressedBackgroundColor(Color.decode("#64afa5"));
    this.quitter.addMouseListener(new MenuListener(this));

    constraints.insets = new Insets(40,10,10,10);
    constraints.gridx = 0;
    constraints.gridy = 3;

    this.add(this.quitter, constraints);

    music();

    this.setVisible(true);
  }

/**
  * Renvoie le JPanel du menu
  * @return Le panel
  */
  public JPanel getjPanel() {
    return this;
  }

/**
  * Renvoie le bouton charger
  * @return le bouton
  */
  public JButtonMenu getCharger() {
    return charger;
  }

/**
  * Renvoie le bouton nouvelle partie
  * @return le bouton
  */
  public JButtonMenu getNouvelle() {
    return nouvelle;
  }

/**
  * Renvoie le bouton quitter
  * @return le bouton
  */
  public JButtonMenu getQuitter() {
    return quitter;
  }

/**
  * Renvoie le frame principal
  * @return le frame
  */
  public MainFrame getMainFrame() {
    return mainFrame;
  }

/**
  * Met la musique du menu
  */
  private void music() {
    try {
      AudioData data = new AudioStream(new FileInputStream("sons/main_theme_menu2.wav")).getData();
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
}
