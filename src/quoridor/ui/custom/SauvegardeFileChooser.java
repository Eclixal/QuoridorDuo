package quoridor.ui.custom;

import quoridor.Partie;
import quoridor.ui.view.PlateauView;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SauvegardeFileChooser extends JFileChooser {

    private Container parent;
    private quoridor.ui.view.Menu menu;

    public SauvegardeFileChooser(quoridor.ui.view.Menu menu){
        super(new File("./"));
        this.parent = menu.getjPanel();
        this.menu = menu;
        setApproveButtonText("Charger une sauvegarde");
    }

    @Override
    public void approveSelection(){
        super.approveSelection();
        parent.remove(SauvegardeFileChooser.this);

        menu.getMainFrame().setContentPane(new PlateauView(menu.getMainFrame(), new Partie(getSelectedFile().getAbsolutePath())));
        menu.getMainFrame().validate();
    }

    @Override
    public void cancelSelection(){
        super.cancelSelection();
        parent.remove(SauvegardeFileChooser.this);

        menu.getNouvelle().setVisible(true);
        menu.getCharger().setVisible(true);

        parent.revalidate();
        parent.repaint();
    }

    @Override
    public void show(){
        rescanCurrentDirectory();

        menu.getNouvelle().setVisible(false);
        menu.getCharger().setVisible(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);
        constraints.gridx = 0;
        constraints.gridy = 1;
        parent.add(this, constraints);
        revalidate();
        repaint();
    }

    @Override
    public Dimension getMaximumSize(){
        return new Dimension(500,300);
    }
}
