package quoridor.ui.listener;

import quoridor.Difficulte;
import quoridor.Mode;
import quoridor.Partie;
import quoridor.ui.view.DeuxJoueursConfigurationView;
import quoridor.ui.view.PlateauView;
import quoridor.ui.view.QuatreJoueursConfigurationView;
import sun.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class QuatreJoueursListener extends MouseAdapter {

    private QuatreJoueursConfigurationView quatreJoueursConfigurationView;

    public QuatreJoueursListener(QuatreJoueursConfigurationView quatreJoueursConfigurationView) {
        this.quatreJoueursConfigurationView = quatreJoueursConfigurationView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        playSound("../sons/main_gui_click.wav");
        if (e.getSource() == quatreJoueursConfigurationView.getJouer()) {
            if (!quatreJoueursConfigurationView.getjTextFieldJ1().getText().isEmpty() && !quatreJoueursConfigurationView.getjTextFieldJ2().getText().isEmpty()
               && !quatreJoueursConfigurationView.getjTextFieldJ3().getText().isEmpty() && !quatreJoueursConfigurationView.getjTextFieldJ4().getText().isEmpty()) {
                AudioPlayer.player.stop(quatreJoueursConfigurationView.getAudioDataStream());
                Partie partie = null;
                if (quatreJoueursConfigurationView.getjComboBox().getSelectedItem().equals("Humain vs Humain vs Humain vs Humain"))
                    partie = new Partie(Mode.HHHH, quatreJoueursConfigurationView.getjTextFieldJ1().getText(), quatreJoueursConfigurationView.getjTextFieldJ2().getText()
                            , quatreJoueursConfigurationView.getjTextFieldJ3().getText(), quatreJoueursConfigurationView.getjTextFieldJ4().getText()
                            , null
                            , null
                            , null
                            , null
                            ,true);
                else if (quatreJoueursConfigurationView.getjComboBox().getSelectedItem().equals("Humain vs Humain vs Humain vs IA"))
                    partie = new Partie(Mode.HHHI, quatreJoueursConfigurationView.getjTextFieldJ1().getText(), quatreJoueursConfigurationView.getjTextFieldJ2().getText()
                            , quatreJoueursConfigurationView.getjTextFieldJ3().getText(), quatreJoueursConfigurationView.getjTextFieldJ4().getText()
                            , null
                            , null
                            , null
                            , Difficulte.valueOf(quatreJoueursConfigurationView.getjComboBoxDifficultyJ4().getSelectedItem().toString().toUpperCase())
                            ,true);
                else if (quatreJoueursConfigurationView.getjComboBox().getSelectedItem().equals("Humain vs Humain vs IA vs IA"))
                    partie = new Partie(Mode.HHII, quatreJoueursConfigurationView.getjTextFieldJ1().getText(), quatreJoueursConfigurationView.getjTextFieldJ2().getText()
                            , quatreJoueursConfigurationView.getjTextFieldJ3().getText(), quatreJoueursConfigurationView.getjTextFieldJ4().getText()
                            , null
                            , null
                            , Difficulte.valueOf(quatreJoueursConfigurationView.getjComboBoxDifficultyJ3().getSelectedItem().toString().toUpperCase())
                            , Difficulte.valueOf(quatreJoueursConfigurationView.getjComboBoxDifficultyJ4().getSelectedItem().toString().toUpperCase())
                            ,true);
                else if (quatreJoueursConfigurationView.getjComboBox().getSelectedItem().equals("Humain vs IA vs IA vs IA"))
                    partie = new Partie(Mode.HIII, quatreJoueursConfigurationView.getjTextFieldJ1().getText(), quatreJoueursConfigurationView.getjTextFieldJ2().getText()
                            , quatreJoueursConfigurationView.getjTextFieldJ3().getText(), quatreJoueursConfigurationView.getjTextFieldJ4().getText()
                            , null
                            , Difficulte.valueOf(quatreJoueursConfigurationView.getjComboBoxDifficultyJ2().getSelectedItem().toString().toUpperCase())
                            , Difficulte.valueOf(quatreJoueursConfigurationView.getjComboBoxDifficultyJ3().getSelectedItem().toString().toUpperCase())
                            , Difficulte.valueOf(quatreJoueursConfigurationView.getjComboBoxDifficultyJ4().getSelectedItem().toString().toUpperCase())
                            ,true);
                else if (quatreJoueursConfigurationView.getjComboBox().getSelectedItem().equals("IA vs IA vs IA vs IA"))
                    partie = new Partie(Mode.IIII, quatreJoueursConfigurationView.getjTextFieldJ1().getText(), quatreJoueursConfigurationView.getjTextFieldJ2().getText()
                            , quatreJoueursConfigurationView.getjTextFieldJ3().getText(), quatreJoueursConfigurationView.getjTextFieldJ4().getText()
                            , Difficulte.valueOf(quatreJoueursConfigurationView.getjComboBoxDifficultyJ1().getSelectedItem().toString().toUpperCase())
                            , Difficulte.valueOf(quatreJoueursConfigurationView.getjComboBoxDifficultyJ2().getSelectedItem().toString().toUpperCase())
                            , Difficulte.valueOf(quatreJoueursConfigurationView.getjComboBoxDifficultyJ3().getSelectedItem().toString().toUpperCase())
                            , Difficulte.valueOf(quatreJoueursConfigurationView.getjComboBoxDifficultyJ4().getSelectedItem().toString().toUpperCase())
                            ,true);

                if (partie != null) {
                    quatreJoueursConfigurationView.getMainFrame().setContentPane(new PlateauView(quatreJoueursConfigurationView.getMainFrame(), partie));
                    quatreJoueursConfigurationView.getMainFrame().validate();
                }
            }

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
