package quoridor.ui.custom;

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
        System.out.println(getSelectedFile().getAbsolutePath());
        parent.remove(SauvegardeFileChooser.this);

        menu.getNouvelle().setVisible(true);
        menu.getCharger().setVisible(true);

        parent.revalidate();
        parent.repaint();
    }

    @Override
    public void cancelSelection(){
        super.cancelSelection();
        System.out.println("Annuler");
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
