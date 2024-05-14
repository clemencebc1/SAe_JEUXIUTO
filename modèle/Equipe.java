import java.util.ArrayList;
import java.util.List;

public class Equipe implements Participant{
    private String nom;
    private List<Athlete> lesAthletes; 

    public Equipe(String nom) {
        this.nom = nom;
        lesAthletes = new ArrayList<>();
    }

    // getters
    public String getNom() {
        return nom;
    }
    public List<Athlete> getLesAthletes() {
        return this.lesAthletes;
    }
    
    //setters
    public void ajouterAthlete(Athlete athlete) {
        this.lesAthletes.add(athlete);
    }

    @Override
    public double participer(Epreuve e){
        double res = 0;
        for (Athlete athlete : lesAthletes) {
           res = res + athlete.participer(e);
           e.ajoutParticipants(athlete);
        }
    
        return res = 0;
    }

    
}