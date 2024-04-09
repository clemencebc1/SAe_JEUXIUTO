import org.junit.*;
import static org.junit.Assert.assertEquals;


public class TestSport {
    @Test 
    public void testGetNom(){
        Sport sport = new Sport("Nom", "Milieu");
        assertEquals(sport.getNom(), "Nom");
    }

    @Test 
    public void testGetMilieu(){
        Sport sport = new Sport("Nom", "Milieu");
        assertEquals(sport.getMilieu(),"Milieu");
    }
}