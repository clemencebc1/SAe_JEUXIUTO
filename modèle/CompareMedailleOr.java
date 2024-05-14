import java.util.Comparator;

public class CompareMedailleOr implements Comparator<Athlete>{
    @Override
    public int compare(Athlete a1, Athlete a2){
        return a1.getPays().getNbOr()-a2.getPays().getNbOr();
    }
}