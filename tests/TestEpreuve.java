package tests;
import org.junit.*;

import sport.*;
import participant.*;

import static org.junit.Assert.assertEquals;

// compiler : javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d bin/ -cp jar/junit-jupiter-api-5.3.2.jar:jar/junit-jupiter-engine-5.3.2.jar:jar/junit-platform-console-standalone-1.3.2.jar src/sport/*.java src/participant/*.java src/autre/*.java src/comparateur/*.java ./tests/TestEpreuve.java 
// executer :  java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -javaagent:jar/jacocoagent.jar=destfile=jacoco.exec -cp bin/src/sport:bin/src/tests:bin/src/participant:jar/junit-jupiter-api-5.3.2.jar:jar/junit-jupiter-engine-5.3.2.jar:jar/junit-platform-console-standalone-1.3.2.jar org.junit.platform.console.ConsoleLauncher --scan-class-path --class-path bin



public class TestEpreuve {
    Sport natation = new Natation("Natation", "Eau");
    Epreuve e = new Epreuve("Nom", "Cat",natation,1);
    

    @Test 
    public void testGetNom(){
        assertEquals(e.getNom(), "Nom");
    }
    @Test
    public void testGetCat(){
        assertEquals(e.getCategorie(), "Cat");
    }
    @Test 
    public void testGetNum(){
        Integer num = 1;
        assertEquals(e.getNum(),num);
    }
}