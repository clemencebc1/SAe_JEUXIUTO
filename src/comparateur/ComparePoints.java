package comparateur;
import participant.*;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Map;

public class ComparePoints implements Comparator<Map<Athlete, Double>>{
    @Override
    public int compare(Map<Athlete, Double> m1, Map<Athlete, Double> m2){
        Double score1 = null;
        Double score2 = null;
        for (Entry<Athlete, Double> entry: m1.entrySet()){
            score1 = entry.getValue();
        }
        for (Entry<Athlete, Double> entry: m2.entrySet()){
            score2 = entry.getValue();
        }
        return score1.compareTo(score2);
    }
}