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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu extends JPanel {

  private JLabel label;
  private JButtonMenu nouvelle;
  private JButtonMenu charger;
  private JButtonMenu quitter;

  private MainFrame mainFrame;

  public Menu(MainFrame mainFrame) {
    this.mainFrame = mainFrame;

    this.setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(10,10,10,10);

    this.label = new JLabel("Quoridor");
    this.label.setBorder(new EmptyBorder(10, 10, 100, 10));
    this.label.setFont(new Font("Courier New", Font.BOLD, 120));

    constraints.gridy = 0;
    constraints.gridx = 0;

    this.add(this.label, constraints);

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

    this.add(this.nouvelle, constraints);

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

    this.add(this.charger, constraints);

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

    this.add(this.quitter, constraints);

    music();

    this.setVisible(true);
  }

  public JPanel getjPanel() {
    return this;
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

  public MainFrame getMainFrame() {
    return mainFrame;
  }

  private ContinuousAudioDataStream audioDataStream;
  private void music() {
    try {
      AudioData data = new AudioStream(new FileInputStream("sons/main_theme_menu2.wav")).getData();
      audioDataStream = new ContinuousAudioDataStream(data);
      AudioPlayer.player.start(audioDataStream);
    } catch(IOException ignored) { }
  }

  public ContinuousAudioDataStream getAudioDataStream() {
    return audioDataStream;
  }
}
