package quoridor.ui.view;

import quoridor.ui.MainFrame;
import quoridor.ui.custom.JButtonMenu;
import quoridor.ui.listener.DeuxJoueursListener;
import quoridor.ui.listener.JComboListener;
import quoridor.ui.listener.NouvellePartieListener;
import sun.applet.Main;
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

public class DeuxJoueursConfigurationView extends JPanel {

  private JButtonMenu jouer;
  private JButtonMenu retour;
  private JLabel jLabel;

  private JTextField jTextFieldJ1;
  private JTextField jTextFieldJ2;

  private JComboBox<String> jComboBox;
    private JComboBox<String> jComboBoxDifficultyJ1;
    private JComboBox<String> jComboBoxDifficultyJ2;

  private MainFrame mainFrame;

  public DeuxJoueursConfigurationView(MainFrame mainFrame) {
      this.mainFrame = mainFrame;
      this.setLayout(new GridBagLayout());

      this.setBackground(Color.decode("#b4e9e2"));

      GridBagConstraints constraints = new GridBagConstraints();
      constraints.insets = new Insets(10,10,10,10);

      this.jLabel = new JLabel("Mode 2 joueurs");
      this.jLabel.setBorder(new EmptyBorder(10, 10, 50, 10));
      this.jLabel.setFont(new Font("Courier New", Font.BOLD, 80));
      this.jLabel.setForeground(Color.decode("#309286"));

      constraints.gridy = 0;
      constraints.gridx = 0;

      this.add(this.jLabel, constraints);

      JPanel jPanel = new JPanel();
      jPanel.setLayout(new GridBagLayout());
      jPanel.setBackground(Color.decode("#b4e9e2"));

      GridBagConstraints constraints1 = new GridBagConstraints();
      constraints1.insets = new Insets(30,50,30,50);

      JPanel panelC = new JPanel();
      panelC.setLayout(new FlowLayout());
      panelC.setBackground(Color.decode("#b4e9e2"));

      JLabel jLabel = new JLabel("Configuration : ");
      jLabel.setFont(new Font("Courier New", Font.BOLD, 23));
      jLabel.setForeground(Color.decode("#309286"));

      this.jComboBox = new JComboBox<>();
      this.jComboBox.addItem("Humain VS Humain");
      this.jComboBox.addItem("Humain VS IA");
      this.jComboBox.addItem("IA VS IA");
      this.jComboBox.addItemListener(new JComboListener(this));

      panelC.add(jLabel);
      panelC.add(this.jComboBox);

      constraints1.gridy = 0;
      constraints1.gridx = 0;

      jPanel.add(panelC, constraints1);

      JPanel panelJ1 = new JPanel();
      panelJ1.setLayout(new GridBagLayout());
      GridBagConstraints constraintsJ1 = new GridBagConstraints();
      panelJ1.setBackground(Color.decode("#b4e9e2"));

      constraintsJ1.fill = GridBagConstraints.HORIZONTAL;
      constraintsJ1.insets = new Insets(10,30,10,30);

      panelJ1.setPreferredSize(new Dimension(300,200));
      Border lineborder = BorderFactory.createLineBorder(Color.decode("#309286"), 2);
      panelJ1.setBorder(lineborder);

      JLabel pseudoJ1 = new JLabel("Pseudo :");
      pseudoJ1.setFont(new Font("Courier New", Font.BOLD, pseudoJ1.getFont().getSize()*2));
      constraintsJ1.gridy = 0;
      constraintsJ1.gridx = 1;
      panelJ1.add(pseudoJ1, constraintsJ1);
      pseudoJ1.setForeground(Color.decode("#309286"));

      JLabel avatarJ1 = new JLabel(new ImageIcon(new ImageIcon("images/Rond_rouge.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
      constraintsJ1.gridy = 0;
      constraintsJ1.gridx = 0;
      panelJ1.add(avatarJ1, constraintsJ1);

      this.jTextFieldJ1 = new JTextField("Joueur 1");
      this.jTextFieldJ1.setPreferredSize(new Dimension(this.getSize().width+50, this.getSize().height+40));
      this.jTextFieldJ1.setFont(new Font("Courier New", Font.BOLD, 16));
      this.jTextFieldJ1.setForeground(Color.decode("#ebefd0"));
      this.jTextFieldJ1.setBackground(Color.decode("#309286"));

      constraintsJ1.gridy = 1;
      constraintsJ1.gridx = 1;
      panelJ1.add(this.jTextFieldJ1, constraintsJ1);


      this.jComboBoxDifficultyJ1 = new JComboBox<>();
      this.jComboBoxDifficultyJ1.addItem("Facile");
//      this.jComboBoxDifficulty.addItem("Normal");
//      this.jComboBoxDifficulty.addItem("Difficile");
//      this.jComboBoxDifficulty.addItem("Impossible");

      this.jComboBoxDifficultyJ1.setVisible(false);

      constraintsJ1.gridy = 2;
      constraintsJ1.gridx = 1;

      panelJ1.add(this.jComboBoxDifficultyJ1, constraintsJ1);

      constraints1.gridy = 0;
      constraints1.gridx = 2;

      jPanel.add(panelJ1, constraints1);

      JPanel panelJ2 = new JPanel();
      panelJ2.setPreferredSize(new Dimension(300,200));
      panelJ2.setBorder(lineborder);
      panelJ2.setLayout(new GridBagLayout());
      GridBagConstraints constraintsJ2 = new GridBagConstraints();
      panelJ2.setBackground(Color.decode("#b4e9e2"));

      constraintsJ2.fill = GridBagConstraints.HORIZONTAL;
      constraintsJ2.insets = new Insets(5,20,5,20);

      JLabel pseudoJ2 = new JLabel("Pseudo :");
      pseudoJ2.setFont(new Font("Courier New", Font.BOLD, pseudoJ2.getFont().getSize()*2));
      pseudoJ2.setForeground(Color.decode("#309286"));

      constraintsJ2.gridy = 0;
      constraintsJ2.gridx = 1;
      panelJ2.add(pseudoJ2, constraintsJ2);

      JLabel avatarJ2 = new JLabel(new ImageIcon(new ImageIcon("images/Rond_bleu.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
      constraintsJ2.gridy = 0;
      constraintsJ2.gridx = 0;
      panelJ2.add(avatarJ2, constraintsJ2);

      this.jTextFieldJ2 = new JTextField("Joueur 2");
      this.jTextFieldJ2.setPreferredSize(new Dimension(this.getSize().width+50, this.getSize().height+40));
      this.jTextFieldJ2.setFont(new Font("Courier New", Font.BOLD, 16));
      this.jTextFieldJ2.setForeground(Color.decode("#ebefd0"));
      this.jTextFieldJ2.setBackground(Color.decode("#309286"));

      constraintsJ2.gridy = 1;
      constraintsJ2.gridx = 1;
      panelJ2.add(this.jTextFieldJ2, constraintsJ2);

      this.jComboBoxDifficultyJ2 = new JComboBox<>();
      this.jComboBoxDifficultyJ2.addItem("Facile");
//      this.jComboBoxDifficulty.addItem("Normal");
//      this.jComboBoxDifficulty.addItem("Difficile");
//      this.jComboBoxDifficulty.addItem("Impossible");

      this.jComboBoxDifficultyJ2.setVisible(false);

      constraintsJ2.gridy = 2;
      constraintsJ2.gridx = 1;

      panelJ2.add(this.jComboBoxDifficultyJ2, constraintsJ2);

      constraints1.gridy = 1;
      constraints1.gridx = 2;

      jPanel.add(panelJ2, constraints1);

      constraints1.insets = new Insets(10,10,10,10);

      this.jouer = new JButtonMenu("Jouer");
      this.jouer.setMargin(new Insets(30,30,30,30));
      this.jouer.setFont(new Font("Courier New", Font.BOLD, 19));
      this.jouer.setBackground(Color.decode("#309286"));
      this.jouer.setForeground(Color.decode("#ebefd0"));
      this.jouer.setBorderPainted(false);
      this.jouer.setFocusPainted(false);
      this.jouer.setHoverBackgroundColor(Color.decode("#59a59b"));
      this.jouer.setPressedBackgroundColor(Color.decode("#64afa5"));
      this.jouer.addMouseListener(new DeuxJoueursListener(this));


      constraints1.gridx = 0;
      constraints1.gridy = 1;

      jPanel.add(this.jouer, constraints1);

      this.retour = new JButtonMenu("Retour");
      this.retour.setMargin(new Insets(30,30,30,30));
      this.retour.setFont(new Font("Courier New", Font.BOLD, 19));
      this.retour.setBackground(Color.decode("#309286"));
      this.retour.setForeground(Color.decode("#ebefd0"));
      this.retour.setBorderPainted(false);
      this.retour.setFocusPainted(false);
      this.retour.setHoverBackgroundColor(Color.decode("#59a59b"));
      this.retour.setPressedBackgroundColor(Color.decode("#64afa5"));
      this.retour.addMouseListener(new DeuxJoueursListener(this));


      constraints1.gridx = 1;
      constraints1.gridy = 1;

      jPanel.add(this.retour, constraints1);

      constraints.gridy = 3;
      constraints.gridx = 0;

      this.add(jPanel, constraints);

      music();
      this.setVisible(true);
  }

    public JButtonMenu getJouer() {
        return jouer;
    }

    public JTextField getjTextFieldJ1() {
        return jTextFieldJ1;
    }

    public JTextField getjTextFieldJ2() {
        return jTextFieldJ2;
    }

    public JComboBox<String> getjComboBox() {
        return jComboBox;
    }

    public JComboBox<String> getjComboBoxDifficultyJ1() {
        return jComboBoxDifficultyJ1;
    }

    public JComboBox<String> getjComboBoxDifficultyJ2() {
        return jComboBoxDifficultyJ2;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    private ContinuousAudioDataStream audioDataStream;
    private void music() {
        try {
            AudioData data = new AudioStream(new FileInputStream("sons/main_theme_menu1.wav")).getData();
            audioDataStream = new ContinuousAudioDataStream(data);
            AudioPlayer.player.start(audioDataStream);
        } catch(IOException ignored) { }
    }

    public JButtonMenu getRetour() {
        return retour;
    }

    public ContinuousAudioDataStream getAudioDataStream() {
        return audioDataStream;
    }

    public void changeMode(Object item) {
      if (item.equals("Humain VS IA")) {
          this.jComboBoxDifficultyJ1.setVisible(false);
          this.jComboBoxDifficultyJ2.setVisible(true);
      } else if (item.equals("Humain VS Humain")) {
          this.jComboBoxDifficultyJ1.setVisible(false);
          this.jComboBoxDifficultyJ2.setVisible(false);
      } else if (item.equals("IA VS IA")) {
          this.jComboBoxDifficultyJ1.setVisible(true);
          this.jComboBoxDifficultyJ2.setVisible(true);
      }
    }
}
