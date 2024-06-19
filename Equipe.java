import java.util.ArrayList;
import java.util.List;

public class Equipe implements Participant{
    private String nom;
    private List<Athlete> lesAthletes; 

    public Equipe(String nom) {
        this.nom = nom;
        lesAthletes = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }
    
    public void ajouterAthlete(Athlete athlete) {
        this.lesAthletes.add(athlete);
    }

    @Override
    public double participer(Epreuve e){
        double res = 0;
        for (Athlete athlete : lesAthletes) {
           res = res + athlete.participer();
        }
        
        e.ajoutParticipants(this);
        return res = 0;
    }
    
}