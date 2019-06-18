package quoridor.ui.custom;

import javax.swing.*;
import java.awt.*;

public class CustomFileChooser extends JFileChooser {

    private Container parent;

    public CustomFileChooser(Container parent){
        super();
        this.parent = parent;
        setApproveButtonText("Ouvrir");
    }

    @Override
    public void approveSelection(){
        super.approveSelection();
        System.out.println(getSelectedFile().getAbsolutePath());
        parent.remove(CustomFileChooser.this);
        parent.repaint();
    }

    @Override
    public void cancelSelection(){
        super.cancelSelection();
        System.out.println("Annuler");
        parent.remove(CustomFileChooser.this);
        parent.repaint();
    }

    @Override
    public void show(){
        rescanCurrentDirectory();

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
