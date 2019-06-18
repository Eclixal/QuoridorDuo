package quoridor.ui.view;

import quoridor.ui.custom.JButtonMenu;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class NouvellePartie extends JPanel {

  private JButtonMenu retour;
  private JButtonMenu jouer;

  public NouvellePartie() {
    this.setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(10,10,10,10);

    this.retour = new JButtonMenu("Retour");
    this.retour.setMargin(new Insets(10,10,10,10));
    this.retour.setFont(new Font("Courier New", Font.BOLD, this.retour.getFont().getSize()));
    this.retour.setBackground(Color.decode("#252525"));
    this.retour.setForeground(Color.WHITE);
    this.retour.setBorderPainted(false);
    this.retour.setFocusPainted(false);
    this.retour.setHoverBackgroundColor(Color.decode("#3d3d3d"));
    this.retour.setPressedBackgroundColor(Color.decode("#484848"));

    constraints.gridy = 0;
    constraints.gridx = 0;

    this.add(this.retour, constraints);

    this.jouer = new JButtonMenu("Jouer");
    this.jouer.setMargin(new Insets(10,10,10,10));
    this.jouer.setFont(new Font("Courier New", Font.BOLD, this.retour.getFont().getSize()));
    this.jouer.setBackground(Color.decode("#252525"));
    this.jouer.setForeground(Color.WHITE);
    this.jouer.setBorderPainted(false);
    this.jouer.setFocusPainted(false);
    this.jouer.setHoverBackgroundColor(Color.decode("#3d3d3d"));
    this.jouer.setPressedBackgroundColor(Color.decode("#484848"));

    constraints.gridy = 0;
    constraints.gridx = 1;

    this.add(this.jouer, constraints);

    music();
    this.setVisible(true);
  }

  private void music() {
    try {
      AudioData data = new AudioStream(new FileInputStream("sons/main_theme_menu1.wav")).getData();
      ContinuousAudioDataStream BGM = new ContinuousAudioDataStream(data);
      AudioPlayer.player.start(BGM);
    } catch(IOException ignored) {ignored.printStackTrace();}
  }
}
