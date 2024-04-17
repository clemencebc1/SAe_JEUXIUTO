import org.junit.*;
import static org.junit.Assert.assertEquals;


public class TestSport {
    @Test 
    public void testGetNom(){
        Sport natation = new Natation("Natation", "Eau");
        assertEquals(natation.getNom(), "Natation");
    }

    @Test 
    public void testGetMilieu(){
        Sport natation = new Natation("Natation", "Eau");
        assertEquals(natation.getMilieu(),"Eau");
    }
    @Test 
    public void testCalculeRes(){
        Sport natation = new Natation("Natation", "Eau");
        Epreuve epreuveNatation = new Epreuve("100m brasse hommes", "hommes", "individuel");
        Participant France = new Equipe("France");
        assertEquals(natation.calculeRes(), 0);
    }
}