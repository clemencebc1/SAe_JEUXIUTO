package participant;
import sport.*;

import java.util.ArrayList;
import java.util.List;

public class Equipe implements Participant{
    private String nom;
    private Integer num;
    private List<Athlete> lesAthletes; 

    public Equipe(String nom) {
        this.nom = nom;
        this.num = null;
        lesAthletes = new ArrayList<>();
    }
    public Equipe(String nom, Integer num){
        this.nom = nom;
        this.num = num;
    }

    // getters
    public Integer getNum(){
        return this.num;
    }
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
        e.ajoutParticipants(this);
        return e.getSport().calculeRes(this);
    }
    @Override
    public String toString(){
        return this.nom+"";
    }

    
}