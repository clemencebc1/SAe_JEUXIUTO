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
    

    @Override
    public boolean equals(Object o){
        if (o == null){return false;}
        if (!(o instanceof Pays)){return false;}
        if (o == this){return true;}
        Pays tmp = (Pays) o;
        return this.nom.equals(tmp.nom) && this.nbArgent == tmp.nbArgent && this.nbBronze == tmp.nbBronze && this.nbOr == tmp.nbOr;
    }

    @Override
    public int hashCode(){
        return this.nom.hashCode() + this.nbArgent + this.nbBronze + this.nbOr +13;
    }

    public int getNbArgent() {
        return nbArgent;
    }

    public List<Athlete> getLesAthletes() {
        return lesAthletes;
    }

}