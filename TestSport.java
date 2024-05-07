import org.junit.*;
import static org.junit.Assert.assertEquals;


public class TestSport {

    Pays france = new Pays("France");
    Sport natation = new Natation("Natation", "Eau");
    Epreuve epreuveNatation = new Epreuve("100m brasse hommes", "hommes", "individuel", natation);
    Equipe equipeFrance = new Equipe("France");


    @Test 
    public void testGetNom(){
        assertEquals(natation.getNom(), "Natation");
    }

    @Test 
    public void testGetMilieu(){
        assertEquals(natation.getMilieu(),"Eau");
    }
    @Test 
    public void testNatationCalculeRes(){
        assertEquals(natation.calculeRes(epreuveNatation), 0);
        Athlete marchand = new Athlete("Marchand", "Léon", "M", 10, 20, 40, france);
        epreuveNatation.ajoutParticipants(marchand);
        assertEquals(natation.calculeRes(epreuveNatation),8000);
        Athlete manaudou = new Athlete("Manaudou", "Florent", "M", 20, 10, 50, france);
        equipeFrance.ajouterAthlete(manaudou);
        epreuveNatation.ajoutParticipants(equipeFrance);
        assertEquals(natation.calculeRes(epreuveNatation),18000);
    }

    Sport athletisme = new Athletisme("Athletisme", "sol");
    Epreuve epreuveAthletisme = new Epreuve("110m haies", "femmes", "individuel", athletisme);

    @Test
    public void testAthletismeCalculeRes(){
        Athlete sambaMayela = new Athlete("Samba-Mayela", "Cyréna", "F", 20, 40, 30, france);
        epreuveAthletisme.ajoutParticipants(sambaMayela);
        assertEquals(athletisme.calculeRes(epreuveAthletisme),90);
    }

    Sport escrime = new Escrime("Escrime", "intérieur");
    Epreuve epreuveEscrime = new Epreuve("épée", "hommes", "individuel", escrime);

    @Test 
    public void testEscrimeCalculeRes(){
        Athlete cannone = new Athlete("Cannone", "Romain", "M", 40, 40, 40, france);
        Athlete lefort = new Athlete("Lefort", "Enzo", "M", 30, 50, 40, france);
        assertEquals(escrime.calculeRes(epreuveEscrime),0);
        equipeFrance.ajouterAthlete(lefort);
        equipeFrance.ajouterAthlete(cannone);
        epreuveEscrime.ajoutParticipants(equipeFrance);
        assertEquals(escrime.calculeRes(epreuveEscrime),3180);
        }
} 