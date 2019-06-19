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

public class FinDePartie extends JPanel {

    private MainFrame mainFrame;

    private JLabel jLabel;

    private JButtonMenu jButtonMenu;

    public FinDePartie(MainFrame mainFrame, String pseudo) {
        this.mainFrame = mainFrame;
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;

        this.jLabel = new JLabel("<html><center>Victoire de<br> " + pseudo + "</center></html>");
        this.jLabel.setFont(new Font("Courier New", Font.BOLD, 120));
        this.jLabel.setBorder(new EmptyBorder(10, 10, 30, 10));

        this.add(this.jLabel,  constraints);

        constraints.insets = new Insets(10,10,10,10);
        constraints.gridy = 1;
        constraints.gridx = 0;

        this.jButtonMenu = new JButtonMenu("Menu Principal");
        this.jButtonMenu.setMargin(new Insets(20,30,20,30));
        this.jButtonMenu.setFont(new Font("Courier New", Font.BOLD, this.jButtonMenu.getFont().getSize()));
        this.jButtonMenu.setBackground(Color.decode("#252525"));
        this.jButtonMenu.setForeground(Color.WHITE);
        this.jButtonMenu.setBorderPainted(false);
        this.jButtonMenu.setFocusPainted(false);
        this.jButtonMenu.setHoverBackgroundColor(Color.decode("#3d3d3d"));
        this.jButtonMenu.setPressedBackgroundColor(Color.decode("#484848"));
        this.jButtonMenu.addMouseListener(new FinListener(this));

        this.add(this.jButtonMenu, constraints);

        this.setVisible(true);
    }

    public JButtonMenu getjButtonMenu() {
        return jButtonMenu;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
