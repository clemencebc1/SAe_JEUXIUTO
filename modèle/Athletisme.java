import java.util.List;
import java.util.ArrayList;

public class Athletisme extends Sport {
    public Athletisme(String nom, String milieu){
        super(nom, milieu);
        
    }
    @Override
    public int calculeRes(Epreuve e){
        List<Athlete> participants = e.getParticipants();
        int points = 0;
        for (Athlete participant : participants){
            points += participant.getForce()+participant.getAgilite()+participant.getEndurance();
        }
        return points;
    }
}