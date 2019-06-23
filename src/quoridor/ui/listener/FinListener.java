package quoridor.ui.listener;

import quoridor.ui.custom.SauvegardeFileChooser;
import quoridor.ui.view.FinDePartie;
import quoridor.ui.view.Menu;
import quoridor.ui.view.NouvellePartie;
import sun.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
  * Gère les actions de la frame de fin de partie
  */
public class FinListener extends MouseAdapter {

    private FinDePartie finDePartie;

/**
  * Initialise l'écouteur
  * @param finDePartie la frame de fin de partie
  */
    public FinListener(FinDePartie finDePartie) {
        this.finDePartie = finDePartie;
    }

/**
  * Gère quand on clique
  * @param e l'événement
  */
    public void mouseClicked(MouseEvent e) {
        playSound("sons/main_gui_click.wav");
        if (e.getSource() == finDePartie.getjButtonMenu()) {
            finDePartie.getMainFrame().setContentPane(new Menu(finDePartie.getMainFrame()));
            finDePartie.getMainFrame().validate();
        }
    }

/**
  * Joue la musique quand on revient au menu principal
  * @param soundName le nom de la musique
  */
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
