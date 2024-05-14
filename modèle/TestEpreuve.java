import org.junit.*;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.ArrayList;


public class TestEpreuve {
    Sport natation = new Natation("Natation", "Eau");
    Epreuve epreuveNatation = new Epreuve("100m brasse hommes", "hommes", "individuel", natation);
    @Test 
    public void testGetNom(){
        assertEquals(epreuveNatation.getNom(), "100m brasse hommes");
    }
    @Test 
    public void testGetStyle(){
        assertEquals(epreuveNatation.getStyle(),"individuel");
    }
    @Test 
    public void testGetParticipants(){
        assertEquals(epreuveNatation.getCategorie(), "hommes");
    }
}