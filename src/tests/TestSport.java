import org.junit.*;
import static org.junit.Assert.assertEquals;
import autre.*;
import comparateur.*;
import participant.*;
import sport.*;

public class TestSport {

    Pays france = new Pays("France");
    Sport natation = new Natation("Natation", "Eau");
    Epreuve epreuveNatation = new Epreuve("100m brasse hommes", "hommes", "individuel", natation,1);
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
        Athlete marchand = new Athlete("Marchand", "Léon", "M", 10, 20, 40, france,1);
        epreuveNatation.ajoutParticipants(marchand);
        assertEquals(natation.calculeRes(marchand),8000., 3);
        Athlete manaudou = new Athlete("Manaudou", "Florent", "M", 20, 10, 50, france,2);
        equipeFrance.ajouterAthlete(manaudou);
        equipeFrance.ajouterAthlete(marchand);
        epreuveNatation.ajoutParticipants(equipeFrance);
        assertEquals(natation.calculeRes(equipeFrance),18000., 3);
    }

    Sport athletisme = new Athletisme("Athletisme", "sol");
    Epreuve epreuveAthletisme = new Epreuve("110m haies", "femmes", "individuel", athletisme,2);

    @Test
    public void testAthletismeCalculeRes(){
        Athlete sambaMayela = new Athlete("Samba-Mayela", "Cyréna", "F", 20, 40, 30, france,3);
        epreuveAthletisme.ajoutParticipants(sambaMayela);
        assertEquals(athletisme.calculeRes(sambaMayela),90., 3);
    }

    Sport escrime = new Escrime("Escrime", "intérieur");
    Epreuve epreuveEscrime = new Epreuve("épée", "hommes", "individuel", escrime,4);

    @Test 
    public void testEscrimeCalculeRes(){
        Athlete cannone = new Athlete("Cannone", "Romain", "M", 40, 40, 40, france,4);
        Athlete lefort = new Athlete("Lefort", "Enzo", "M", 30, 50, 40, france,5);
        equipeFrance.ajouterAthlete(lefort);
        equipeFrance.ajouterAthlete(cannone);
        epreuveEscrime.ajoutParticipants(equipeFrance);
        assertEquals(escrime.calculeRes(equipeFrance),240., 3);
        }

    Sport volleyBall = new VolleyBall("Volley-ball", "intérieur");
    Epreuve epreuveVolleyBall = new Epreuve("Volley-ball", "hommes", "équipe", volleyBall,5);

    @Test 
    public void testVolleyBallCalculeRes(){
        Athlete boyer = new Athlete("Boyer", "Stephen", "M", 60, 40, 30, france,6);
        epreuveVolleyBall.ajoutParticipants(boyer);
        assertEquals(volleyBall.calculeRes(boyer),130.,3);
    }

    Sport handball = new Handball("Handball", "intérieur");
    Epreuve epreuveHandball = new Epreuve("Handball", "hommes", "équipe", handball,6);

    @Test 
    public void testHandballCalculRes(){
        Athlete descat =  new Athlete("descat", "hugo", "M", 40, 70, 50, france,7);
        epreuveHandball.ajoutParticipants(descat);
        assertEquals(handball.calculeRes(descat),160.,3);
    }
} 