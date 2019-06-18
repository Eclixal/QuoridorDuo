package quoridor.ui.listener;

import quoridor.ui.custom.CustomFileChooser;
import quoridor.ui.view.Menu;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MenuListener extends MouseAdapter {

    private Menu menu;

    public MenuListener(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        playSound("sons/main_gui_click.wav");
        if (e.getSource() == menu.getNouvelle()) {
            menu.repaint();
        } else if (e.getSource() == menu.getCharger()) {
            CustomFileChooser chooser = new CustomFileChooser(menu.getjPanel());
            chooser.show();

        } else if (e.getSource() == menu.getQuitter())
            System.exit(0);
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
