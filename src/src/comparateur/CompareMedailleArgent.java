package comparateur;
import participant.*;

import java.util.Comparator;

public class CompareMedailleArgent implements Comparator<Athlete>{
    @Override
    public int compare(Athlete a1, Athlete a2){
        return a1.getPays().getNbArgent()-a2.getPays().getNbArgent();
    }
}