package test;

import org.junit.Test;
import quoridor.Plateau;

import static org.junit.Assert.assertEquals;

public class PlateauTest {

    @Test()
    public void testPlateau() {
        Plateau plateau = new Plateau(9, null);
        assertEquals(9, plateau.getTaille());
    }

}
