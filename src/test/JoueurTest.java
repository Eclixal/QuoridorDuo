package test;

import org.junit.Test;
import quoridor.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class JoueurTest {

    @Test()
    public void testJoueurHumain() {
        Pion pion = new Pion("ROUGE", new Coordonnee(2,1, -1, -1));
        Plateau plateau = new Plateau(9, new Partie(Mode.HH));

        Joueur joueur = new Humain("Joueur1", 1, "ROUGE", null, pion, plateau);

        assertEquals("Joueur1", joueur.getNom());
        assertEquals(pion.getCouleur(), joueur.getCouleur());
        assertEquals(1, joueur.getNumero());
        assertEquals("ROUGE", joueur.getCouleur());
        assertEquals(pion, joueur.getPion());
        assertNotNull(joueur.getBarrieres());
    }

    @Test()
    public void testJoueurIA() {
        Pion pion = new Pion("BLEU", new Coordonnee(2,1, -1, -1));
        Plateau plateau = new Plateau(9, new Partie(Mode.HH));

        IA joueur = new IA("IA1", 2, "BLEU", null, pion, plateau, Difficulte.IMPOSSIBLE);

        assertEquals("IA1", joueur.getNom());
        assertEquals(2, joueur.getNumero());
        assertEquals(pion.getCouleur(), joueur.getCouleur());
        assertEquals("BLEU", joueur.getCouleur());
        assertEquals(Difficulte.IMPOSSIBLE, joueur.getDifficulte());
        assertEquals(pion, joueur.getPion());
        assertNotNull(joueur.getBarrieres());
    }

}
