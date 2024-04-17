import org.junit.*;
import static org.junit.Assert.assertEquals;


public class TestEpreuve {
    @Test 
    public void testGetNom(){
        Epreuve e = new Epreuve("Nom", "Cat", "Style");
        assertEquals(e.getNom(), "Nom");
    }
    @Test 
    public void testGetStyle(){
        Epreuve e = new Epreuve("Nom", "cat", "Style");
        assertEquals(e.getStyle(),"Style");
    }
}