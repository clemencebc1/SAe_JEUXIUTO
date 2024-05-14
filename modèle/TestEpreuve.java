import org.junit.*;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;


public class TestEpreuve {
    Sport natation = new Natation("Natation", "Eau");
    Pays france = new Pays("France");
    Equipe equipeFrance = new Equipe("France");
    Epreuve epreuveNatation = new Epreuve("100m brasse hommes", "hommes", "individuel", natation);
    Athlete marchand = new Athlete("Marchand", "Léon", "M", 10, 20, 40, france);
    Pays angleterre = new Pays("Angleterre");

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
    @Test 
    public void testClassement(){
        Comparator<Athlete> comp1 = new CompareMedailleOr();
        epreuveNatation.ajoutParticipants(marchand);

    }
}