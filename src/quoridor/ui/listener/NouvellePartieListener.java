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

public class NouvellePartieListener extends MouseAdapter {

    private NouvellePartie nouvellePartie;

    public NouvellePartieListener(NouvellePartie nouvellePartie) {
        this.nouvellePartie = nouvellePartie;
    }

    @Override
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
