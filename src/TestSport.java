import org.junit.*;
import static org.junit.Assert.assertEquals;


public class TestSport {

    Sport natation = new Natation("Natation", "Eau");
    Epreuve epreuveNatation = new Epreuve("100m brasse hommes", "hommes", "individuel");
    Equipe france = new Equipe("France");

    @Test 
    public void testGetNom(){
        assertEquals(natation.getNom(), "Natation");
    }

    @Test 
    public void testGetMilieu(){
        assertEquals(natation.getMilieu(),"Eau");
    }
    @Test 
    public void testCalculeRes(){
        assertEquals(natation.calculeRes(epreuveNatation), 0);
        Athlete marchand = new Athlete("Marchand", "Léon", "M", 10, 20, 40);
        epreuveNatation.ajoutParticipants(marchand);
        assertEquals(natation.calculeRes(epreuveNatation),8000);
        Athlete manaudou = new Athlete("Manaudou", "Florent", "M", 20, 10, 50);
        france.ajouterAthlete(manaudou);
        epreuveNatation.ajoutParticipants(france);
        assertEquals(natation.calculeRes(epreuveNatation),18000);
    }
}