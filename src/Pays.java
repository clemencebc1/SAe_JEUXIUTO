import java.util.List;
import java.util.ArrayList;

public class Pays{

    private String nom;

    private int nbOr;

    private int nbArgent;

    private int nbBronze;

    private List<Athlete> lesAthletes;

    public Pays(String nom){
        this.nom = nom;
        this.lesAthletes = new ArrayList<>();
    }

    public String getNom(){
        return this.nom;
    }

    public int getNbOr(){
        return this.nbOr;
    }

    public int getnbArgent(){
        return this.nbArgent;
    }

    public int getNbBronze(){
        return this.nbBronze;
    }

}