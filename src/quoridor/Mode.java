package quoridor;

/**
  * Enumération listant les différents modes de jeu
  */
public enum Mode {

    HH, //humain vs humain
    HI, //humain vs ia
    II, //ia vs ia
    HHHH, //humain vs humain vs humain vs humain
    HHHI, //humain vs humain vs humain vs ia
    HHII, //humain vs humain vs ia vs ia
    HIII, //humain vs ia vs ia vs ia
    IIII; //ia vs ia vs ia vs ia

}
