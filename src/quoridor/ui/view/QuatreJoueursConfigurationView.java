package quoridor.ui.view;

import quoridor.ui.MainFrame;
import quoridor.ui.custom.JButtonMenu;
import quoridor.ui.listener.DeuxJoueursListener;
import quoridor.ui.listener.JComboListener;
import quoridor.ui.listener.QuatreJoueursListener;
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

public class QuatreJoueursConfigurationView extends JPanel {

  private JButtonMenu jouer;
  private JLabel jLabel;

  private JTextField jTextFieldJ1;
    private JTextField jTextFieldJ2;
    private JTextField jTextFieldJ3;
    private JTextField jTextFieldJ4;

  private JComboBox<String> jComboBox;
    private JComboBox<String> jComboBoxDifficultyJ1;
    private JComboBox<String> jComboBoxDifficultyJ2;
    private JComboBox<String> jComboBoxDifficultyJ3;
    private JComboBox<String> jComboBoxDifficultyJ4;

  private MainFrame mainFrame;

  public QuatreJoueursConfigurationView(MainFrame mainFrame) {
      this.mainFrame = mainFrame;
      this.setLayout(new GridBagLayout());

      GridBagConstraints constraints = new GridBagConstraints();
      constraints.insets = new Insets(10,10,10,10);

      this.jLabel = new JLabel("Mode 4 joueurs");
      this.jLabel.setBorder(new EmptyBorder(10, 10, 50, 10));
      this.jLabel.setFont(new Font("Courier New", Font.BOLD, 80));

      constraints.gridy = 0;
      constraints.gridx = 0;

      this.add(this.jLabel, constraints);

      JPanel jPanel = new JPanel();
      jPanel.setLayout(new GridBagLayout());
      GridBagConstraints constraints1 = new GridBagConstraints();
      constraints1.insets = new Insets(30,50,30,50);

      JPanel panelC = new JPanel();
      panelC.setLayout(new FlowLayout());
      JLabel jLabel = new JLabel("Configuration : ");
      this.jComboBox = new JComboBox<>();
      this.jComboBox.addItem("Humain vs Humain vs Humain vs Humain");
      this.jComboBox.addItem("Humain vs Humain vs Humain vs IA");
      this.jComboBox.addItem("Humain vs Humain vs IA vs IA");
      this.jComboBox.addItem("Humain vs IA vs IA vs IA");
      this.jComboBox.addItem("IA vs IA vs IA vs IA");
      this.jComboBox.addItemListener(new JComboListener(this));

      panelC.add(jLabel);
      panelC.add(this.jComboBox);

      constraints1.gridy = 0;
      constraints1.gridx = 0;

      jPanel.add(panelC, constraints1);

      JPanel panelJ1 = new JPanel();
      panelJ1.setLayout(new GridBagLayout());
      GridBagConstraints constraintsJ1 = new GridBagConstraints();

      constraintsJ1.fill = GridBagConstraints.HORIZONTAL;
      constraintsJ1.insets = new Insets(10,30,10,30);

      panelJ1.setPreferredSize(new Dimension(300,200));
      Border lineborder = BorderFactory.createLineBorder(Color.decode("#252525"), 2);
      panelJ1.setBorder(lineborder);

      JLabel pseudoJ1 = new JLabel("Pseudo :");
      pseudoJ1.setFont(new Font("Courier New", Font.BOLD, pseudoJ1.getFont().getSize()*2));
      constraintsJ1.gridy = 0;
      constraintsJ1.gridx = 1;
      panelJ1.add(pseudoJ1, constraintsJ1);

      JLabel avatarJ1 = new JLabel(new ImageIcon(new ImageIcon("../images/Rond_rouge.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
      constraintsJ1.gridy = 0;
      constraintsJ1.gridx = 0;
      panelJ1.add(avatarJ1, constraintsJ1);

      this.jTextFieldJ1 = new JTextField("Joueur 1");
      this.jTextFieldJ1.setPreferredSize(new Dimension(this.getSize().width+50, this.getSize().height+40));
      this.jTextFieldJ1.setFont(new Font("Courier New", Font.BOLD, (int) (this.jTextFieldJ1.getFont().getSize()*1.2)));

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
      constraints1.gridx = 1;

      jPanel.add(panelJ1, constraints1);

      JPanel panelJ2 = new JPanel();
      panelJ2.setPreferredSize(new Dimension(300,200));
      panelJ2.setBorder(lineborder);
      panelJ2.setLayout(new GridBagLayout());
      GridBagConstraints constraintsJ2 = new GridBagConstraints();

      constraintsJ2.fill = GridBagConstraints.HORIZONTAL;
      constraintsJ2.insets = new Insets(5,20,5,20);

      JLabel pseudoJ2 = new JLabel("Pseudo :");
      pseudoJ2.setFont(new Font("Courier New", Font.BOLD, pseudoJ2.getFont().getSize()*2));
      constraintsJ2.gridy = 0;
      constraintsJ2.gridx = 1;
      panelJ2.add(pseudoJ2, constraintsJ2);

      JLabel avatarJ2 = new JLabel(new ImageIcon(new ImageIcon("../images/Rond_bleu.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
      constraintsJ2.gridy = 0;
      constraintsJ2.gridx = 0;
      panelJ2.add(avatarJ2, constraintsJ2);

      this.jTextFieldJ2 = new JTextField("Joueur 2");
      this.jTextFieldJ2.setPreferredSize(new Dimension(this.getSize().width+50, this.getSize().height+40));
      this.jTextFieldJ2.setFont(new Font("Courier New", Font.BOLD, (int) (this.jTextFieldJ2.getFont().getSize()*1.2)));

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
      constraints1.gridx = 1;

      jPanel.add(panelJ2, constraints1);

      JPanel panelJ3 = new JPanel();
      panelJ3.setPreferredSize(new Dimension(300,200));
      panelJ3.setBorder(lineborder);
      panelJ3.setLayout(new GridBagLayout());
      GridBagConstraints constraintsJ3 = new GridBagConstraints();

      constraintsJ3.fill = GridBagConstraints.HORIZONTAL;
      constraintsJ3.insets = new Insets(5,20,5,20);

      JLabel pseudoJ3 = new JLabel("Pseudo :");
      pseudoJ3.setFont(new Font("Courier New", Font.BOLD, pseudoJ3.getFont().getSize()*2));
      constraintsJ3.gridy = 0;
      constraintsJ3.gridx = 1;
      panelJ3.add(pseudoJ3, constraintsJ3);

      JLabel avatarJ3 = new JLabel(new ImageIcon(new ImageIcon("../images/Rond_vert.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
      constraintsJ3.gridy = 0;
      constraintsJ3.gridx = 0;
      panelJ3.add(avatarJ3, constraintsJ3);

      this.jTextFieldJ3 = new JTextField("Joueur 3");
      this.jTextFieldJ3.setPreferredSize(new Dimension(this.getSize().width+50, this.getSize().height+40));
      this.jTextFieldJ3.setFont(new Font("Courier New", Font.BOLD, (int) (this.jTextFieldJ3.getFont().getSize()*1.2)));

      constraintsJ3.gridy = 1;
      constraintsJ3.gridx = 1;
      panelJ3.add(this.jTextFieldJ3, constraintsJ3);

      this.jComboBoxDifficultyJ3 = new JComboBox<>();
      this.jComboBoxDifficultyJ3.addItem("Facile");
//      this.jComboBoxDifficultyJ3.addItem("Normal");
//      this.jComboBoxDifficultyJ3.addItem("Difficile");
//      this.jComboBoxDifficultyJ3.addItem("Impossible");

      this.jComboBoxDifficultyJ3.setVisible(false);

      constraintsJ3.gridy = 2;
      constraintsJ3.gridx = 1;

      panelJ3.add(this.jComboBoxDifficultyJ3, constraintsJ3);

      constraints1.gridy = 0;
      constraints1.gridx = 2;

      jPanel.add(panelJ3, constraints1);

      JPanel panelJ4 = new JPanel();
      panelJ4.setPreferredSize(new Dimension(300,200));
      panelJ4.setBorder(lineborder);
      panelJ4.setLayout(new GridBagLayout());
      GridBagConstraints constraintsJ4 = new GridBagConstraints();

      constraintsJ4.fill = GridBagConstraints.HORIZONTAL;
      constraintsJ4.insets = new Insets(5,20,5,20);

      JLabel pseudoJ4 = new JLabel("Pseudo :");
      pseudoJ4.setFont(new Font("Courier New", Font.BOLD, pseudoJ4.getFont().getSize()*2));
      constraintsJ4.gridy = 0;
      constraintsJ4.gridx = 1;
      panelJ4.add(pseudoJ4, constraintsJ4);

      JLabel avatarJ4 = new JLabel(new ImageIcon(new ImageIcon("../images/Rond_jaune.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
      constraintsJ4.gridy = 0;
      constraintsJ4.gridx = 0;
      panelJ4.add(avatarJ4, constraintsJ4);

      this.jTextFieldJ4 = new JTextField("Joueur 4");
      this.jTextFieldJ4.setPreferredSize(new Dimension(this.getSize().width+50, this.getSize().height+40));
      this.jTextFieldJ4.setFont(new Font("Courier New", Font.BOLD, (int) (this.jTextFieldJ4.getFont().getSize()*1.2)));

      constraintsJ4.gridy = 1;
      constraintsJ4.gridx = 1;
      panelJ4.add(this.jTextFieldJ4, constraintsJ4);

      this.jComboBoxDifficultyJ4 = new JComboBox<>();
      this.jComboBoxDifficultyJ4.addItem("Facile");
//      this.jComboBoxDifficultyJ4.addItem("Normal");
//      this.jComboBoxDifficultyJ4.addItem("Difficile");
//      this.jComboBoxDifficultyJ4.addItem("Impossible");

      this.jComboBoxDifficultyJ4.setVisible(false);

      constraintsJ4.gridy = 2;
      constraintsJ4.gridx = 1;

      panelJ4.add(this.jComboBoxDifficultyJ4, constraintsJ4);

      constraints1.gridy = 1;
      constraints1.gridx = 2;

      jPanel.add(panelJ4, constraints1);

      this.jouer = new JButtonMenu("Jouer");
      this.jouer.setMargin(new Insets(50,70,50,70));
      this.jouer.setFont(new Font("Courier New", Font.BOLD, this.jouer.getFont().getSize()));
      this.jouer.setBackground(Color.decode("#252525"));
      this.jouer.setForeground(Color.WHITE);
      this.jouer.setBorderPainted(false);
      this.jouer.setFocusPainted(false);
      this.jouer.setHoverBackgroundColor(Color.decode("#3d3d3d"));
      this.jouer.setPressedBackgroundColor(Color.decode("#484848"));
      this.jouer.addMouseListener(new QuatreJoueursListener(this));

      constraints1.insets = new Insets(10,50,10,50);

      constraints1.gridx = 0;
      constraints1.gridy = 1;

      jPanel.add(this.jouer, constraints1);

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

    public JTextField getjTextFieldJ3() {
        return jTextFieldJ3;
    }

    public JTextField getjTextFieldJ4() {
        return jTextFieldJ4;
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

    public JComboBox<String> getjComboBoxDifficultyJ3() {
        return jComboBoxDifficultyJ3;
    }

    public JComboBox<String> getjComboBoxDifficultyJ4() {
        return jComboBoxDifficultyJ4;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    private ContinuousAudioDataStream audioDataStream;
    private void music() {
        try {
            AudioData data = new AudioStream(new FileInputStream("../sons/main_theme_menu1.wav")).getData();
            audioDataStream = new ContinuousAudioDataStream(data);
            AudioPlayer.player.start(audioDataStream);
        } catch(IOException ignored) { }
    }

    public ContinuousAudioDataStream getAudioDataStream() {
        return audioDataStream;
    }

    public void changeMode(Object item) {
      if (item.equals("Humain vs Humain vs Humain vs Humain")) {
          this.jComboBoxDifficultyJ1.setVisible(false);
          this.jComboBoxDifficultyJ2.setVisible(false);
          this.jComboBoxDifficultyJ3.setVisible(false);
          this.jComboBoxDifficultyJ4.setVisible(false);
      } else if (item.equals("Humain vs Humain vs Humain vs IA")) {
          this.jComboBoxDifficultyJ1.setVisible(false);
          this.jComboBoxDifficultyJ2.setVisible(false);
          this.jComboBoxDifficultyJ3.setVisible(false);
          this.jComboBoxDifficultyJ4.setVisible(true);
      } else if (item.equals("Humain vs Humain vs IA vs IA")) {
          this.jComboBoxDifficultyJ1.setVisible(false);
          this.jComboBoxDifficultyJ2.setVisible(false);
          this.jComboBoxDifficultyJ3.setVisible(true);
          this.jComboBoxDifficultyJ4.setVisible(true);
      } else if (item.equals("Humain vs IA vs IA vs IA")) {
          this.jComboBoxDifficultyJ1.setVisible(false);
          this.jComboBoxDifficultyJ2.setVisible(true);
          this.jComboBoxDifficultyJ3.setVisible(true);
          this.jComboBoxDifficultyJ4.setVisible(true);
      } else if (item.equals("IA vs IA vs IA vs IA")) {
          this.jComboBoxDifficultyJ1.setVisible(true);
          this.jComboBoxDifficultyJ2.setVisible(true);
          this.jComboBoxDifficultyJ3.setVisible(true);
          this.jComboBoxDifficultyJ4.setVisible(true);
      }
    }
}
