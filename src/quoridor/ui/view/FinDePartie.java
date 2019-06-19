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

public class FinDePartie extends JPanel {

    private MainFrame mainFrame;

    private JLabel jLabel;

    private JButtonMenu jButtonMenu;

    public FinDePartie(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new GridBagLayout());

    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
