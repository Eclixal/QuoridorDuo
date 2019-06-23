package quoridor.ui.listener;

import quoridor.ui.view.DeuxJoueursConfigurationView;
import quoridor.ui.view.Menu;
import quoridor.ui.view.NouvellePartie;
import quoridor.ui.view.QuatreJoueursConfigurationView;
import sun.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
  * Gère les actions des boutons de la fenêtre nouvelle partie
  */
public class NouvellePartieListener extends MouseAdapter {

    private NouvellePartie nouvellePartie;

/**
  * Initialise l'écouteur de nouvelle partie
  * @param nouvellePartie La fenêtre qui doit être géré
  */
    public NouvellePartieListener(NouvellePartie nouvellePartie) {
        this.nouvellePartie = nouvellePartie;
    }

/**
  * Gère quand on clique sur un bouton
  * @param e L'événement
  */
    public void mouseClicked(MouseEvent e) {
        playSound("sons/main_gui_click.wav");
        if (e.getSource() == this.nouvellePartie.getjButtonMenu2J()) {
            AudioPlayer.player.stop(nouvellePartie.getAudioDataStream());
            nouvellePartie.getMainFrame().setContentPane(new DeuxJoueursConfigurationView(nouvellePartie.getMainFrame()));
            nouvellePartie.getMainFrame().validate();
        } else if (e.getSource() == this.nouvellePartie.getjButtonMenu4J()) {
            AudioPlayer.player.stop(nouvellePartie.getAudioDataStream());
            nouvellePartie.getMainFrame().setContentPane(new QuatreJoueursConfigurationView(nouvellePartie.getMainFrame()));
            nouvellePartie.getMainFrame().validate();
        } else if (e.getSource() == this.nouvellePartie.getRetour()) {
            AudioPlayer.player.stop(nouvellePartie.getAudioDataStream());
            nouvellePartie.getMainFrame().setContentPane(new Menu(nouvellePartie.getMainFrame()));
            nouvellePartie.getMainFrame().validate();
        }
    }

/**
  * Gère le son du menu pour ne pas qu'elle s'arrête
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
