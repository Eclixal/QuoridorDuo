package quoridor.ui.listener;

import quoridor.Plateau;
import quoridor.ui.custom.SauvegardeFileChooser;
import quoridor.ui.view.Menu;
import quoridor.ui.view.NouvellePartie;
import quoridor.ui.view.PauseView;
import quoridor.ui.view.PlateauView;
import sun.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
  * Gère les actions du menu pause
  */
public class PauseListener extends MouseAdapter {

    private PauseView pauseView;
    private PlateauView plateauView;

/**
  * Initailise l'écouteur
  * @param pauseView la frame du menu pause
  * @param plateauView la frame du plateau
  */
    public PauseListener(PauseView pauseView, PlateauView plateauView) {
        this.pauseView = pauseView;
        this.plateauView = plateauView;
    }

/**
  * Gère quand on clique
  * @param e l'événement
  */
    public void mouseClicked(MouseEvent e) {
        playSound("sons/main_gui_click.wav");
        if (e.getSource() == pauseView.getjButtonReprendre()) {
            pauseView.getMainFrame().setContentPane(plateauView);
            pauseView.getMainFrame().validate();
            plateauView.setPause(false);
        } if (e.getSource() == pauseView.getjButtonSauvegarder()) {
            pauseView.getPlateauView().getPlateau().getPartie().sauvegarder(pauseView.getPlateauView().getTour());
            pauseView.getMainFrame().setContentPane(new Menu(pauseView.getMainFrame()));
            pauseView.getMainFrame().validate();
        } if (e.getSource() == pauseView.getjButtonAbandonner()) {
            pauseView.getMainFrame().setContentPane(new Menu(pauseView.getMainFrame()));
            pauseView.getMainFrame().validate();
        }
    }

/**
  * Joue le musique si on retourne au menu principal
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
