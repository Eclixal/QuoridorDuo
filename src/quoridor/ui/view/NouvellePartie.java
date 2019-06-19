package quoridor.ui.view;

import quoridor.ui.custom.JButtonMenu;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class NouvellePartie extends JPanel {

  private JButtonMenu retour;
  private JButtonMenu jouer;
  private JLabel jLabel;

  public NouvellePartie() {
    this.setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(10,10,10,10);

    this.jLabel = new JLabel("Nouvelle Partie");
    this.jLabel.setBorder(new EmptyBorder(10, 10, 50, 10));
    this.jLabel.setFont(new Font("Courier New", Font.BOLD, 120));

    constraints.gridy = 0;
    constraints.gridx = 0;

    this.add(this.jLabel, constraints);

    this.retour = new JButtonMenu("Retour");
    this.retour.setMargin(new Insets(20,30,20,30));
    this.retour.setFont(new Font("Courier New", Font.BOLD, this.retour.getFont().getSize()));
    this.retour.setBackground(Color.decode("#252525"));
    this.retour.setForeground(Color.WHITE);
    this.retour.setBorderPainted(false);
    this.retour.setFocusPainted(false);
    this.retour.setHoverBackgroundColor(Color.decode("#3d3d3d"));
    this.retour.setPressedBackgroundColor(Color.decode("#484848"));

    JPanel jPanel = new JPanel();
    jPanel.setLayout(new GridBagLayout());
    GridBagConstraints constraints1 = new GridBagConstraints();
    constraints1.insets = new Insets(30,50,30,50);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setPreferredSize(new Dimension(200,200));
    Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
    panel.add(new JLabel("Joueur1"), BorderLayout.NORTH);
    JToolBar jToolBar = new JToolBar();
    jToolBar.setFloatable(false);
    JButton button;
    jToolBar.setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx=0;
    gbc.gridy=0;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    button = new JButton("<");
    jToolBar.add(button, gbc);

    gbc.gridx=1;
    jToolBar.add(new JLabel("Aucun joueur"), gbc);

    button = new JButton(">");
    gbc.gridx=2;
    jToolBar.add(button, gbc);

    panel.add(jToolBar, BorderLayout.SOUTH);


    panel.setBorder(lineborder);

    constraints1.gridy = 1;
    constraints1.gridx = 0;

    jPanel.add(panel, constraints1);

    JPanel panel1 = new JPanel();
    panel1.setPreferredSize(new Dimension(200,200));
    panel1.setBorder(lineborder);
    constraints1.gridy = 1;
    constraints1.gridx = 1;
    jPanel.add(panel1, constraints1);

    JPanel panel2 = new JPanel();
    panel2.setPreferredSize(new Dimension(200,200));
    panel2.setBorder(lineborder);
    constraints1.gridy = 2;
    constraints1.gridx = 0;
    jPanel.add(panel2, constraints1);

    JPanel panel3 = new JPanel();
    panel3.setPreferredSize(new Dimension(200,200));
    panel3.setBorder(lineborder);
    constraints1.gridy = 2;
    constraints1.gridx = 1;
    jPanel.add(panel3, constraints1);

    constraints1.gridy = 3;
    constraints1.gridx = 0;

    jPanel.add(this.retour, constraints1);

    this.jouer = new JButtonMenu("Jouer");
    this.jouer.setMargin(new Insets(20,30,20,30));
    this.jouer.setFont(new Font("Courier New", Font.BOLD, this.retour.getFont().getSize()));
    this.jouer.setBackground(Color.decode("#252525"));
    this.jouer.setForeground(Color.WHITE);
    this.jouer.setBorderPainted(false);
    this.jouer.setFocusPainted(false);
    this.jouer.setHoverBackgroundColor(Color.decode("#3d3d3d"));
    this.jouer.setPressedBackgroundColor(Color.decode("#484848"));

    constraints1.insets = new Insets(10,50,10,50);

    constraints1.gridx = 1;
    constraints1.gridy = 3;

    jPanel.add(this.jouer, constraints1);

    constraints.gridy = 3;
    constraints.gridx = 0;
    this.add(jPanel, constraints);

    music();
    this.setVisible(true);
  }

  private void music() {
    try {
      AudioData data = new AudioStream(new FileInputStream("sons/main_theme_menu1.wav")).getData();
      ContinuousAudioDataStream BGM = new ContinuousAudioDataStream(data);
      AudioPlayer.player.start(BGM);
    } catch(IOException ignored) { }
  }
}
