package quoridor.ui.listener;

import quoridor.ui.view.DeuxJoueursConfigurationView;
import quoridor.ui.view.QuatreJoueursConfigurationView;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

/**
  * Gère quand activer et désactiver les combobox de difficultées des IA
  */
public class JComboListener implements ItemListener {

    private DeuxJoueursConfigurationView deuxJoueursConfigurationView;

    private QuatreJoueursConfigurationView quatreJoueursConfigurationView;

/**
  * Initialise avec le menu du mode deux joueurs
  * @param deuxJoueursConfigurationView la frame du menu du mode deux joueurs
  */
    public JComboListener(DeuxJoueursConfigurationView deuxJoueursConfigurationView) {
        this.deuxJoueursConfigurationView = deuxJoueursConfigurationView;
    }

    /**
      * Initialise avec le menu du mode quatre joueurs
      * @param quatreJoueursConfigurationView la frame du menu du mode quatre joueurs
      */
    public JComboListener(QuatreJoueursConfigurationView quatreJoueursConfigurationView) {
        this.quatreJoueursConfigurationView = quatreJoueursConfigurationView;
    }

  /**
    * Gère quand la combobox de choix du nombre d'IA change
    * @param e l'événement
    */
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (deuxJoueursConfigurationView != null)
                deuxJoueursConfigurationView.changeMode(e.getItem());
            if (quatreJoueursConfigurationView != null)
                quatreJoueursConfigurationView.changeMode(e.getItem());
        }
    }
}
