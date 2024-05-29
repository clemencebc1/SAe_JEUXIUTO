import org.junit.*;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;


public class TestEpreuve {
    Sport natation = new Natation("Natation", "Eau");
    Pays france = new Pays("France");
    Pays angleterre = new Pays("Angleterre");
    Pays espagne = new Pays("Espagne");

    Equipe equipeFrance = new Equipe("France");
    Epreuve epreuveNatation = new Epreuve("100m brasse hommes", "hommes", "individuel", natation);
    Athlete marchand = new Athlete("Marchand", "Léon", "M", 10, 20, 40, france);
    Athlete peaty = new Athlete("Peaty", "Adam", "M", 15, 22, 35, angleterre);

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
        france.setNbOr(3);
        angleterre.setNbOr(5);
        espagne.setNbOr(2);
        epreuveNatation.ajoutParticipants(marchand);
        epreuveNatation.ajoutParticipants(peaty);
        List<Pays> listeP = Arrays.asList(france, angleterre);
        assertEquals(epreuveNatation.classement(comp1, Tri.PAYS), listeP);

    }
}