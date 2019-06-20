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

public class NouvellePartie extends JPanel {

  private JButtonMenu retour;
  private JLabel jLabel;

  private JButtonMenu jButtonMenu2J;
  private JButtonMenu jButtonMenu4J;

  private MainFrame mainFrame;

  public NouvellePartie(MainFrame mainFrame) {
      this.mainFrame = mainFrame;
      this.setLayout(new GridBagLayout());

      GridBagConstraints constraints = new GridBagConstraints();
      constraints.insets = new Insets(10,10,10,10);

      this.jLabel = new JLabel("Nouvelle Partie");
      this.jLabel.setBorder(new EmptyBorder(10, 10, 50, 10));
      this.jLabel.setFont(new Font("Courier New", Font.BOLD, 120));

      constraints.gridy = 0;
      constraints.gridx = 0;

      this.add(this.jLabel, constraints);

      JPanel jPanel = new JPanel();
      jPanel.setLayout(new GridBagLayout());
      GridBagConstraints constraints1 = new GridBagConstraints();
      constraints1.insets = new Insets(30,50,30,50);

      this.jButtonMenu2J = new JButtonMenu("2 joueurs");
      this.jButtonMenu2J.setMargin(new Insets(50,50,50,50));
      this.jButtonMenu2J.setFont(new Font("Courier New", Font.BOLD, this.jButtonMenu2J.getFont().getSize()));
      this.jButtonMenu2J.setBackground(Color.decode("#ab1e1e"));
      this.jButtonMenu2J.setForeground(Color.WHITE);
      this.jButtonMenu2J.setBorderPainted(false);
      this.jButtonMenu2J.setFocusPainted(false);
      this.jButtonMenu2J.setHoverBackgroundColor(Color.decode("#cb2d2d"));
      this.jButtonMenu2J.setPressedBackgroundColor(Color.decode("#e33a3a"));
      this.jButtonMenu2J.addMouseListener(new NouvellePartieListener(this));

      constraints1.gridy = 0;
      constraints1.gridx = 0;

      jPanel.add(this.jButtonMenu2J, constraints1);

      this.jButtonMenu4J = new JButtonMenu("4 joueurs");
      this.jButtonMenu4J.setMargin(new Insets(50,50,50,50));
      this.jButtonMenu4J.setFont(new Font("Courier New", Font.BOLD, this.jButtonMenu4J.getFont().getSize()));
      this.jButtonMenu4J.setBackground(Color.decode("#ab1e1e"));
      this.jButtonMenu4J.setForeground(Color.WHITE);
      this.jButtonMenu4J.setBorderPainted(false);
      this.jButtonMenu4J.setFocusPainted(false);
      this.jButtonMenu4J.setHoverBackgroundColor(Color.decode("#cb2d2d"));
      this.jButtonMenu4J.setPressedBackgroundColor(Color.decode("#e33a3a"));
      this.jButtonMenu4J.addMouseListener(new NouvellePartieListener(this));

      constraints1.gridy = 0;
      constraints1.gridx = 2;

      jPanel.add(this.jButtonMenu4J, constraints1);

      constraints.gridy = 1;
      constraints.gridx = 0;
      this.add(jPanel, constraints);

      this.retour = new JButtonMenu("Retour");
      this.retour.setMargin(new Insets(20,30,20,30));
      this.retour.setFont(new Font("Courier New", Font.BOLD, this.retour.getFont().getSize()));
      this.retour.setBackground(Color.decode("#252525"));
      this.retour.setForeground(Color.WHITE);
      this.retour.setBorderPainted(false);
      this.retour.setFocusPainted(false);
      this.retour.setHoverBackgroundColor(Color.decode("#3d3d3d"));
      this.retour.setPressedBackgroundColor(Color.decode("#484848"));
      this.retour.addMouseListener(new NouvellePartieListener(this));

      constraints.gridy = 2;
      constraints.gridx = 0;
      this.add(this.retour, constraints);

      music();
      this.setVisible(true);
  }

    public JButtonMenu getjButtonMenu2J() {
        return jButtonMenu2J;
    }

    public JButtonMenu getjButtonMenu4J() {
        return jButtonMenu4J;
    }

    public JButtonMenu getRetour() {
        return retour;
    }

    private ContinuousAudioDataStream audioDataStream;
    private void music() {
        try {
            AudioData data = new AudioStream(new FileInputStream("sons/main_theme_menu1.wav")).getData();
            audioDataStream = new ContinuousAudioDataStream(data);
            AudioPlayer.player.start(audioDataStream);
        } catch(IOException ignored) { }
    }

    public ContinuousAudioDataStream getAudioDataStream() {
        return audioDataStream;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
