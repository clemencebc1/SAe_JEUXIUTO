package sport;
import participant.*;

import java.util.List;
import java.util.ArrayList;

public class VolleyBall extends Sport {
    public VolleyBall(String nom, String milieu){
        super(nom, milieu,4);
        
    }
    @Override
    public <T extends Participant> double calculeRes(T participant){
        double points = 0;
        if (participant instanceof Athlete){
            Athlete athlete = (Athlete) participant;
            return athlete.getForce()+athlete.getAgilite()+athlete.getEndurance();
        }
        else if (participant instanceof Equipe){
            Equipe equipe = (Equipe) participant;
            for (Athlete a : equipe.getLesAthletes()){
                points += a.getForce()+a.getAgilite()+a.getEndurance();
        }
        }
        return points;
    }
}