package tests;
import org.junit.*;

import sport.*;
import participant.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

// compiler : javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d bin/ -cp jar/junit-jupiter-api-5.3.2.jar:jar/junit-jupiter-engine-5.3.2.jar:jar/junit-platform-console-standalone-1.3.2.jar src/sport/*.java src/participant/*.java src/autre/*.java src/comparateur/*.java ./tests/TestEpreuve.java 
// executer :  java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -javaagent:jar/jacocoagent.jar=destfile=jacoco.exec -cp bin/src/sport:bin/src/tests:bin/src/participant:jar/junit-jupiter-api-5.3.2.jar:jar/junit-jupiter-engine-5.3.2.jar:jar/junit-platform-console-standalone-1.3.2.jar org.junit.platform.console.ConsoleLauncher --scan-class-path --class-path bin



public class TestParticipant {
    Pays france = new Pays("France");
    Athlete cannone = new Athlete("Cannone", "Romain", "M", 40, 40, 40, france,4);
    Athlete lefort = new Athlete("Lefort", "Enzo", "M", 30, 50, 40, france,5);
    @Test
    public void testGetForce(){
        assertEquals(cannone.getForce(),40);
        assertEquals(lefort.getForce(),30);
    }
    @Test
    public void testGetAgilite(){
        assertEquals(cannone.getAgilite(),40);
        assertEquals(lefort.getAgilite(),50);
    }
    @Test
    public void testGetEndurance(){
        assertEquals(cannone.getEndurance(),40);
        assertEquals(lefort.getEndurance(),40);
    }
    Equipe equipeFrance = new Equipe("France");
    Sport natation = new Natation("Natation", "Eau");
    Epreuve epreuveNatation = new Epreuve("100m brasse hommes", "hommes", natation, 1);
    @Test
    public void testEquipe(){
        equipeFrance.ajouterAthlete(cannone);
        equipeFrance.ajouterAthlete(lefort);
        assertEquals(equipeFrance.getNom(), "France");
        assertEquals(equipeFrance.participer(epreuveNatation), 124000,2);

    }
    @Test
    public void testGetSexe(){
        String m = "M";
        assertEquals(cannone.getSexe(),m);
        assertEquals(lefort.getSexe(),m);
    }
    @Test
    public void testGetNum(){
        Integer i1 = 4;
        Integer i2 = 5;
        assertEquals(cannone.getNum(),i1);
        assertEquals(lefort.getNum(),i2);
    }
    @Test 
    public void testGetNom(){String nom = "Cannone";
        assertEquals(cannone.getNom(), nom);
    }
    @Test 
    public void testGetPreom(){
        assertEquals(lefort.getPrenom(),"Enzo");
    }
    


}