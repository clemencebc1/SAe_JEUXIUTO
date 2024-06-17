package participant;
import sport.*;

import java.util.ArrayList;
import java.util.List;

public class Equipe implements Participant{
    private String nom;
    private int num; 
    private List<Athlete> lesAthletes; 

    public Equipe(String nom, int num) {
        this.nom = nom;
        this.num = num;
        lesAthletes = new ArrayList<>();
    }

    // getters
    public String getNom() {
        return nom;
    }
    public List<Athlete> getLesAthletes() {
        return this.lesAthletes;
    }
    public int getNum() {
        return num;
    }
    
    //setters
    public void ajouterAthlete(Athlete athlete) {
        this.lesAthletes.add(athlete);
    }

    @Override
    public double participer(Epreuve e){
        e.ajoutParticipants(this);
        return e.getSport().calculeRes(this);
    }
    @Override
    public String toString(){
        return this.nom+"";
    }

    
}