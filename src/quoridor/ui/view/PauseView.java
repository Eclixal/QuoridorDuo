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

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;

        this.jLabel = new JLabel("Pause");
        this.jLabel.setFont(new Font("Courier New", Font.BOLD, 120));
        this.jLabel.setBorder(new EmptyBorder(10, 10, 30, 10));

        this.add(this.jLabel,  constraints);

        constraints.insets = new Insets(10,10,10,10);
        constraints.gridy = 1;
        constraints.gridx = 0;

        this.jButtonReprendre = new JButtonMenu("Reprendre");
        this.jButtonReprendre.setMargin(new Insets(20,30,20,30));
        this.jButtonReprendre.setFont(new Font("Courier New", Font.BOLD, this.jButtonReprendre.getFont().getSize()));
        this.jButtonReprendre.setBackground(Color.decode("#252525"));
        this.jButtonReprendre.setForeground(Color.WHITE);
        this.jButtonReprendre.setBorderPainted(false);
        this.jButtonReprendre.setFocusPainted(false);
        this.jButtonReprendre.setHoverBackgroundColor(Color.decode("#3d3d3d"));
        this.jButtonReprendre.setPressedBackgroundColor(Color.decode("#484848"));
        this.jButtonReprendre.addMouseListener(new PauseListener(this, plateauView));

        constraints.gridy = 2;
        constraints.gridx = 0;
        this.add(this.jButtonReprendre, constraints);

        this.jButtonSauvegarder = new JButtonMenu("Sauvegarder et quitter");
        this.jButtonSauvegarder.setMargin(new Insets(20,30,20,30));
        this.jButtonSauvegarder.setFont(new Font("Courier New", Font.BOLD, this.jButtonSauvegarder.getFont().getSize()));
        this.jButtonSauvegarder.setBackground(Color.decode("#252525"));
        this.jButtonSauvegarder.setForeground(Color.WHITE);
        this.jButtonSauvegarder.setBorderPainted(false);
        this.jButtonSauvegarder.setFocusPainted(false);
        this.jButtonSauvegarder.setHoverBackgroundColor(Color.decode("#3d3d3d"));
        this.jButtonSauvegarder.setPressedBackgroundColor(Color.decode("#484848"));
        this.jButtonSauvegarder.addMouseListener(new PauseListener(this, plateauView));

        constraints.gridy = 3;
        constraints.gridx = 0;
        this.add(this.jButtonSauvegarder, constraints);

        this.jButtonAbandonner = new JButtonMenu("Abandonner");
        this.jButtonAbandonner.setMargin(new Insets(20,30,20,30));
        this.jButtonAbandonner.setFont(new Font("Courier New", Font.BOLD, this.jButtonAbandonner.getFont().getSize()));
        this.jButtonAbandonner.setBackground(Color.decode("#252525"));
        this.jButtonAbandonner.setForeground(Color.WHITE);
        this.jButtonAbandonner.setBorderPainted(false);
        this.jButtonAbandonner.setFocusPainted(false);
        this.jButtonAbandonner.setHoverBackgroundColor(Color.decode("#3d3d3d"));
        this.jButtonAbandonner.setPressedBackgroundColor(Color.decode("#484848"));
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
