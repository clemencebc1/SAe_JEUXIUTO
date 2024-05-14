import java.util.List;
import java.util.ArrayList;

public class Pays{

    private String nom;
    private int nbOr;
    private int nbArgent;
    private int nbBronze;
    private List<Athlete> lesAthletes;
    public enum Medaille{OR,ARGENT,BRONZE};
    public Pays(String nom){
        this.nom = nom;
        this.lesAthletes = new ArrayList<>();
    }
    // getters
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
    // setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setNbOr(int nbOr) {
        this.nbOr = nbOr;
    }
    public void setNbArgent(int nbArgent) {
        this.nbArgent = nbArgent;
    }
    public void setNbBronze(int nbBronze) {
        this.nbBronze = nbBronze;
    }
    public void setLesAthletes(List<Athlete> lesAthletes) {
        this.lesAthletes = lesAthletes;
    }

    public void ajoutMedailles(Medaille medal){
        switch (medal) {
            case OR:this.nbOr += 1;break;
            case ARGENT:this.nbArgent += 1;break;
            case BRONZE:this.nbBronze += 1;break;
            default:
                break;
        }
    }

    public void ajoutAthletes(Athlete athlete){
        this.lesAthletes.add(athlete);
    }


}