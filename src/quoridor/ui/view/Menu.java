package quoridor.ui.view;

import quoridor.ui.custom.JButtonMenu;
import quoridor.ui.listener.MenuListener;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.JFrame;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {

  private JLabel label;
  private JButtonMenu nouvelle;
  private JButtonMenu charger;
  private JButtonMenu quitter;

  private JPanel jPanel;

  public Menu() {
    this.setTitle("Quoridor");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400, 400);
    this.setResizable(false);
    this.setUndecorated(true);

    jPanel = new JPanel();
    jPanel.setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(10,10,10,10);

    this.label = new JLabel("Quoridor");
    this.label.setBorder(new EmptyBorder(10, 10, 100, 10));
    this.label.setFont(new Font("Courier New", Font.BOLD, 120));

    GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    device.setFullScreenWindow(this);

    constraints.gridy = 0;
    constraints.gridx = 0;

    jPanel.add(this.label, constraints);

    constraints.fill = GridBagConstraints.HORIZONTAL;

    this.nouvelle = new JButtonMenu("Nouvelle partie");
    this.nouvelle.setMargin(new Insets(10,10,10,10));
    this.nouvelle.setFont(new Font("Courier New", Font.BOLD, this.nouvelle.getFont().getSize()));
    this.nouvelle.setBackground(Color.decode("#252525"));
    this.nouvelle.setForeground(Color.WHITE);
    this.nouvelle.setBorderPainted(false);
    this.nouvelle.setFocusPainted(false);
    this.nouvelle.setHoverBackgroundColor(Color.decode("#3d3d3d"));
    this.nouvelle.setPressedBackgroundColor(Color.decode("#484848"));
    this.nouvelle.addMouseListener(new MenuListener(this));

    constraints.gridx = 0;
    constraints.gridy = 1;

    jPanel.add(this.nouvelle, constraints);

    this.charger = new JButtonMenu("Charger une partie");
    this.charger.setMargin(new Insets(10,10,10,10));
    this.charger.setFont(new Font("Courier New", Font.BOLD, this.charger.getFont().getSize()));
    this.charger.setBorderPainted(false);
    this.charger.setFocusPainted(false);
    this.charger.setBackground(Color.decode("#252525"));
    this.charger.setForeground(Color.WHITE);
    this.charger.setHoverBackgroundColor(Color.decode("#3d3d3d"));
    this.charger.setPressedBackgroundColor(Color.decode("#484848"));
    this.charger.addMouseListener(new MenuListener(this));

    constraints.gridx = 0;
    constraints.gridy = 2;

    jPanel.add(this.charger, constraints);

    this.quitter = new JButtonMenu("Quitter");
    this.quitter.setMargin(new Insets(10,10,10,10));
    this.quitter.setFont(new Font("Courier New", Font.BOLD, this.quitter.getFont().getSize()));
    this.quitter.setBorderPainted(false);
    this.quitter.setFocusPainted(false);
    this.quitter.setBackground(Color.decode("#252525"));
    this.quitter.setForeground(Color.WHITE);
    this.quitter.setHoverBackgroundColor(Color.decode("#3d3d3d"));
    this.quitter.setPressedBackgroundColor(Color.decode("#484848"));
    this.quitter.addMouseListener(new MenuListener(this));

    constraints.insets = new Insets(40,10,10,10);
    constraints.gridx = 0;
    constraints.gridy = 3;

    jPanel.add(this.quitter, constraints);

    this.setContentPane(jPanel);

    music();

    this.setVisible(true);
  }

  public JPanel getjPanel() {
    return jPanel;
  }

  public JButtonMenu getCharger() {
    return charger;
  }

  public JButtonMenu getNouvelle() {
    return nouvelle;
  }

  public JButtonMenu getQuitter() {
    return quitter;
  }

  private void music() {
    try {
      AudioData data = new AudioStream(new FileInputStream("sons/main_theme_menu2.wav")).getData();
      ContinuousAudioDataStream BGM = new ContinuousAudioDataStream(data);
      AudioPlayer.player.start(BGM);
    } catch(IOException ignored) {ignored.printStackTrace();}
  }
}
