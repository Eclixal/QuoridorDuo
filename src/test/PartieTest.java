package test;

import org.junit.Test;
import quoridor.Mode;
import quoridor.Partie;
import static org.junit.Assert.*;


public class PartieTest {

    @Test()
    public void test() {
        Partie partie = new Partie(Mode.HH, "Test1", "Test2", null, null);
        assertEquals(Mode.HH, partie.getMode());
        assertEquals("Test1", partie.getJoueurs().get(0).getNom());
        assertEquals("Test2", partie.getJoueurs().get(1).getNom());
    }

}
