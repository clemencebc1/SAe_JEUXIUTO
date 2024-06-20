package comparateur;
import participant.*;
import java.util.Comparator;

// compare selon le nb de médaille d'or
public class CompareMedailleOr implements Comparator<Athlete>{
    @Override
    public int compare(Athlete a1, Athlete a2){
        return a1.getPays().getNbOr()-a2.getPays().getNbOr();
    }
}