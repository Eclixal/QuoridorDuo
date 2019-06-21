package quoridor.ui.view;

import quoridor.Plateau;
import quoridor.Partie;
import quoridor.Joueur;
import quoridor.ui.MainFrame;
import quoridor.ui.custom.JButtonMenu;
import quoridor.ui.listener.PlateauListener;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.Color;
import java.util.TimerTask;

public class PlateauView extends JPanel {

    private ArrayList<Joueur> joueurs;
    private Plateau plateau;
    private int tour;
    private JTable table;
    private PlateauListener listener;

    private JButtonMenu jButtonMenu;
    private JLabel joueur;
    private JLabel barriere;

    private JLabel errorMessage;

    private boolean pause;

    private MainFrame mainFrame;

    public PlateauView(MainFrame mainFrame, Partie partie) {
        this.mainFrame = mainFrame;
        this.plateau = partie.getPlateau();
        this.joueurs = partie.getJoueurs();
        this.tour = partie.getStartTour();

        this.setBackground(Color.decode("#b4e9e2"));

        this.pause = false;

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(30,20,30,20);

        this.jButtonMenu = new JButtonMenu("Pause");
        this.jButtonMenu.setMargin(new Insets(20,30,20,30));
        this.jButtonMenu.setFont(new Font("Courier New", Font.BOLD, 19));
        this.jButtonMenu.setBackground(Color.decode("#309286"));
        this.jButtonMenu.setForeground(Color.decode("#ebefd0"));
        this.jButtonMenu.setBorderPainted(false);
        this.jButtonMenu.setFocusPainted(false);
        this.jButtonMenu.setHoverBackgroundColor(Color.decode("#59a59b"));
        this.jButtonMenu.setPressedBackgroundColor(Color.decode("#64afa5"));
        this.jButtonMenu.addMouseListener(new PlateauListener(this, this.joueurs.get(tour)));

        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;

        this.add(this.jButtonMenu, constraints);

        constraints.anchor = GridBagConstraints.NORTHEAST;

        this.errorMessage = new JLabel();
        this.errorMessage.setFont(new Font("Courier New", Font.BOLD, ((int)(this.errorMessage.getFont().getSize()*1.5))));
        this.errorMessage.setForeground(Color.decode("#309286"));

        constraints.gridy = 0;
        constraints.gridx = 1;

        this.add(this.errorMessage, constraints);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        jPanel.setBackground(Color.decode("#b4e9e2"));
        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.insets = new Insets(10,0,10,0);

        this.joueur = new JLabel("Tour : " + this.joueurs.get(tour).getNom(), new ImageIcon(new ImageIcon("../images/Rond_" + this.joueurs.get(tour).getCouleur().toLowerCase() + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)), SwingConstants.CENTER);
        this.joueur.setFont(new Font("Courier New", Font.BOLD, ((int)(this.joueur.getFont().getSize()*1.8))));
        this.joueur.setIconTextGap(20);
        this.joueur.setForeground(Color.decode("#309286"));
        this.joueur.setHorizontalAlignment(SwingConstants.LEFT);
        this.joueur.setVerticalAlignment(SwingConstants.CENTER);
        this.joueur.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        constraints1.anchor = GridBagConstraints.WEST;
        constraints1.gridy = 0;
        constraints1.gridx = 0;

        jPanel.add(this.joueur, constraints1);

        this.barriere = new JLabel("Il vous reste " + this.joueurs.get(tour).getBarrieres().stream().filter(barriere1 -> barriere1.getCoordonnee().getX1() == -1).count() + " barrière" + (this.joueurs.get(tour).getBarrieres().stream().filter(barriere1 -> barriere1.getCoordonnee().getX1() == -1).count() > 1 ? "s" :""));
        this.barriere.setFont(new Font("Courier New", Font.BOLD, ((int)(this.barriere.getFont().getSize()*1.8))));
        this.barriere.setForeground(Color.decode("#309286"));

        constraints1.gridy = 1;
        constraints1.gridx = 0;

        jPanel.add(this.barriere, constraints1);

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridy = 1;
        constraints.gridx = 0;
        this.add(jPanel, constraints);

        PlateauModel model = new PlateauModel(this.plateau.getDAMIER());

        this.table = new JTable(model);
        this.table.setFocusable(false);

        launch();

        this.table.setRowSelectionAllowed(false);
        this.table.setRowHeight(50);



        for(int i = 0;i < this.plateau.getTaille();i++){
            if(i%2 == 0){
                TableColumn column = this.table.getColumnModel().getColumn(i);
                column.setMinWidth(50);
                column.setMaxWidth(50);
            }
            else{
                table.setRowHeight(i,10);
                TableColumn column = this.table.getColumnModel().getColumn(i);
                column.setMinWidth(10);
                column.setMaxWidth(10);
            }
        }
        Color color = new Color(81, 164, 233, 255);
        this.table.setGridColor(color);

        constraints.gridy = 1;
        constraints.gridx = 1;

        this.add(this.table, constraints);
    }

    public void changerJoueur(){
        this.revalidate();
        this.repaint();

        if (!pause) {
            if(!(this.joueurs.get(tour).isIA())){
                this.table.removeMouseListener(this.listener);
            }

            if(this.joueurs.get(tour).getFin().getX1() == this.joueurs.get(tour).getPion().getCoordonnee().getX1() || this.joueurs.get(tour).getFin().getY1() == this.joueurs.get(tour).getPion().getCoordonnee().getY1()){
                this.finJeu();
            } else{
                if(this.tour < this.joueurs.size()-1){
                    this.tour++;
                    this.joueur.setText("Tour : " + this.joueurs.get(tour).getNom());
                    this.joueur.setIcon(new ImageIcon(new ImageIcon("../images/Rond_" + this.joueurs.get(tour).getCouleur().toLowerCase() + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
                    this.barriere.setText("Il vous reste " + this.joueurs.get(tour).getBarrieres().stream().filter(barriere1 -> barriere1.getCoordonnee().getX1() == -1).count() + " barrière" + (this.joueurs.get(tour).getBarrieres().stream().filter(barriere1 -> barriere1.getCoordonnee().getX1() == -1).count() > 1 ? "s" :" "));
                }
                else{
                    this.tour = 0;
                    this.joueur.setText("Tour : " + this.joueurs.get(tour).getNom());
                    this.joueur.setIcon(new ImageIcon(new ImageIcon("../images/Rond_" + this.joueurs.get(tour).getCouleur().toLowerCase() + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
                    this.barriere.setText("Il vous reste " + this.joueurs.get(tour).getBarrieres().stream().filter(barriere1 -> barriere1.getCoordonnee().getX1() == -1).count() + " barrière" + (this.joueurs.get(tour).getBarrieres().stream().filter(barriere1 -> barriere1.getCoordonnee().getX1() == -1).count() > 1 ? "s" :" "));
                }
                if(this.joueurs.get(tour).isIA()){
                    java.util.Timer timer = new java.util.Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            tourIA();
                        }
                    }, 250);
                }
                else{
                    this.listener = new PlateauListener(this, this.joueurs.get(tour));
                    this.table.addMouseListener(this.listener);
                }
            }
        }
    }

    public void tourIA(){
        this.joueurs.get(tour).jeu(true, -1, -1);
        this.changerJoueur();
    }

    public JTable getTable() {
        return table;
    }

    public JButtonMenu getjButtonMenu() {
        return jButtonMenu;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public int getTour() {
        return tour;
    }

    public void launch() {
        if (this.joueurs.get(tour).isIA()) {
            this.joueurs.get(tour).jeu(true, -1, -1);
            this.changerJoueur();
        } else {
            this.listener = new PlateauListener(this, this.joueurs.get(tour));
            this.table.addMouseListener(this.listener);
        }
    }

    public JLabel getErrorMessage() {
        return errorMessage;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
        if (!pause)
            launch();
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void finJeu() {
        getMainFrame().setContentPane(new FinDePartie(mainFrame, this.joueurs.get(tour).getNom()));
        getMainFrame().validate();
    }
}
