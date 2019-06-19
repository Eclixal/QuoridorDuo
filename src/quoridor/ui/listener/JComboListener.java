package quoridor.ui.listener;

import quoridor.ui.view.DeuxJoueursConfigurationView;
import quoridor.ui.view.QuatreJoueursConfigurationView;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class JComboListener implements ItemListener {

    private DeuxJoueursConfigurationView deuxJoueursConfigurationView;

    private QuatreJoueursConfigurationView quatreJoueursConfigurationView;

    public JComboListener(DeuxJoueursConfigurationView deuxJoueursConfigurationView) {
        this.deuxJoueursConfigurationView = deuxJoueursConfigurationView;
    }

    public JComboListener(QuatreJoueursConfigurationView quatreJoueursConfigurationView) {
        this.quatreJoueursConfigurationView = quatreJoueursConfigurationView;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (deuxJoueursConfigurationView != null)
                deuxJoueursConfigurationView.changeMode(e.getItem());
            if (quatreJoueursConfigurationView != null)
                quatreJoueursConfigurationView.changeMode(e.getItem());
        }
    }
}
