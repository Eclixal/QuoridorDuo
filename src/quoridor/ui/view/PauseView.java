package quoridor.ui.view;

import quoridor.Partie;
import quoridor.ui.MainFrame;
import quoridor.ui.custom.JButtonMenu;
import quoridor.ui.listener.FinListener;
import quoridor.ui.listener.PauseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PauseView extends JPanel {

    private MainFrame mainFrame;

    private JLabel jLabel;

    private JButtonMenu jButtonReprendre;
    private JButtonMenu jButtonSauvegarder;
    private JButtonMenu jButtonAbandonner;

    private PlateauView plateauView;

    public PauseView(MainFrame mainFrame, PlateauView plateauView) {
        this.plateauView = plateauView;
        this.mainFrame = mainFrame;
        this.setLayout(new GridBagLayout());

        this.setBackground(Color.decode("#b4e9e2"));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;

        this.jLabel = new JLabel("Pause");
        this.jLabel.setFont(new Font("Courier New", Font.BOLD, 120));
        this.jLabel.setBorder(new EmptyBorder(10, 10, 30, 10));
        this.jLabel.setForeground(Color.decode("#309286"));

        this.add(this.jLabel,  constraints);

        constraints.insets = new Insets(10,10,10,10);
        constraints.gridy = 1;
        constraints.gridx = 0;

        this.jButtonReprendre = new JButtonMenu("Reprendre");
        this.jButtonReprendre.setMargin(new Insets(20,30,20,30));
        this.jButtonReprendre.setFont(new Font("Courier New", Font.BOLD, 19));
        this.jButtonReprendre.setBackground(Color.decode("#309286"));
        this.jButtonReprendre.setForeground(Color.decode("#ebefd0"));
        this.jButtonReprendre.setBorderPainted(false);
        this.jButtonReprendre.setFocusPainted(false);
        this.jButtonReprendre.setHoverBackgroundColor(Color.decode("#59a59b"));
        this.jButtonReprendre.setPressedBackgroundColor(Color.decode("#64afa5"));
        this.jButtonReprendre.addMouseListener(new PauseListener(this, plateauView));

        constraints.gridy = 2;
        constraints.gridx = 0;
        this.add(this.jButtonReprendre, constraints);

        this.jButtonSauvegarder = new JButtonMenu("Sauvegarder et quitter");
        this.jButtonSauvegarder.setMargin(new Insets(20,30,20,30));
        this.jButtonSauvegarder.setFont(new Font("Courier New", Font.BOLD, 19));
        this.jButtonSauvegarder.setBackground(Color.decode("#309286"));
        this.jButtonSauvegarder.setForeground(Color.decode("#ebefd0"));
        this.jButtonSauvegarder.setBorderPainted(false);
        this.jButtonSauvegarder.setFocusPainted(false);
        this.jButtonSauvegarder.setHoverBackgroundColor(Color.decode("#59a59b"));
        this.jButtonSauvegarder.setPressedBackgroundColor(Color.decode("#64afa5"));
        this.jButtonSauvegarder.addMouseListener(new PauseListener(this, plateauView));

        constraints.gridy = 3;
        constraints.gridx = 0;
        this.add(this.jButtonSauvegarder, constraints);

        this.jButtonAbandonner = new JButtonMenu("Abandonner");
        this.jButtonAbandonner.setMargin(new Insets(20,30,20,30));
        this.jButtonAbandonner.setFont(new Font("Courier New", Font.BOLD, 19));
        this.jButtonAbandonner.setBackground(Color.decode("#309286"));
        this.jButtonAbandonner.setForeground(Color.decode("#ebefd0"));
        this.jButtonAbandonner.setBorderPainted(false);
        this.jButtonAbandonner.setFocusPainted(false);
        this.jButtonAbandonner.setHoverBackgroundColor(Color.decode("#59a59b"));
        this.jButtonAbandonner.setPressedBackgroundColor(Color.decode("#64afa5"));
        this.jButtonAbandonner.addMouseListener(new PauseListener(this, plateauView));

        constraints.gridy = 4;
        constraints.gridx = 0;
        this.add(this.jButtonAbandonner, constraints);

        this.setVisible(true);
    }

    public JButtonMenu getjButtonAbandonner() {
        return jButtonAbandonner;
    }

    public JButtonMenu getjButtonReprendre() {
        return jButtonReprendre;
    }

    public JButtonMenu getjButtonSauvegarder() {
        return jButtonSauvegarder;
    }

    public PlateauView getPlateauView() {
        return plateauView;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
