package quoridor.ui.listener;

import quoridor.Difficulte;
import quoridor.Mode;
import quoridor.Partie;
import quoridor.ui.view.DeuxJoueursConfigurationView;
import quoridor.ui.view.NouvellePartie;
import quoridor.ui.view.PlateauView;
import sun.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
  * Gère l'écouteur du menu du mode deux joueurs
  */
public class DeuxJoueursListener extends MouseAdapter {

    private DeuxJoueursConfigurationView deuxJoueursConfigurationView;

/**
  * Initialise l'écouteur
  * @param deuxJoueursConfigurationView la fenêtre qui doit être géré
  */
    public DeuxJoueursListener(DeuxJoueursConfigurationView deuxJoueursConfigurationView) {
        this.deuxJoueursConfigurationView = deuxJoueursConfigurationView;
    }

/**
  * Gère les actions quand on clique sur le menu
  * @param e L'événement
  */
    public void mouseClicked(MouseEvent e) {
        playSound("sons/main_gui_click.wav");
        if (e.getSource() == deuxJoueursConfigurationView.getJouer()) {
            if (!deuxJoueursConfigurationView.getjTextFieldJ1().getText().isEmpty() && !deuxJoueursConfigurationView.getjTextFieldJ2().getText().isEmpty()) {
                AudioPlayer.player.stop(deuxJoueursConfigurationView.getAudioDataStream());

                Partie partie = null;
                if (deuxJoueursConfigurationView.getjComboBox().getSelectedItem().equals("Humain VS Humain"))
                    partie = new Partie(Mode.HH, deuxJoueursConfigurationView.getjTextFieldJ1().getText(), deuxJoueursConfigurationView.getjTextFieldJ2().getText(), null, null);
                else if (deuxJoueursConfigurationView.getjComboBox().getSelectedItem().equals("Humain VS IA"))
                    partie = new Partie(Mode.HI, deuxJoueursConfigurationView.getjTextFieldJ1().getText(), deuxJoueursConfigurationView.getjTextFieldJ2().getText(), null, Difficulte.valueOf(deuxJoueursConfigurationView.getjComboBoxDifficultyJ2().getSelectedItem().toString().toUpperCase()));
                else if (deuxJoueursConfigurationView.getjComboBox().getSelectedItem().equals("IA VS IA"))
                    partie = new Partie(Mode.II, deuxJoueursConfigurationView.getjTextFieldJ1().getText(), deuxJoueursConfigurationView.getjTextFieldJ2().getText(), Difficulte.valueOf(deuxJoueursConfigurationView.getjComboBoxDifficultyJ1().getSelectedItem().toString().toUpperCase()), Difficulte.valueOf(deuxJoueursConfigurationView.getjComboBoxDifficultyJ2().getSelectedItem().toString().toUpperCase()));

                if (partie != null) {
                    deuxJoueursConfigurationView.getMainFrame().setContentPane(new PlateauView(deuxJoueursConfigurationView.getMainFrame(), partie));
                    deuxJoueursConfigurationView.getMainFrame().validate();
                }
            }
        } else if (e.getSource() == deuxJoueursConfigurationView.getRetour()) {
            AudioPlayer.player.stop(deuxJoueursConfigurationView.getAudioDataStream());

            deuxJoueursConfigurationView.getMainFrame().setContentPane(new NouvellePartie(deuxJoueursConfigurationView.getMainFrame()));
            deuxJoueursConfigurationView.getMainFrame().validate();
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
