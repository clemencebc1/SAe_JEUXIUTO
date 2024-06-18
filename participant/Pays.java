package participant;
import java.util.List;
import java.util.ArrayList;

public class Pays{

    private String nom;

    private int nbOr;

    private int nbArgent;

    private int nbBronze;

    private int num;

    private List<Athlete> lesAthletes;

    public Pays(String nom, int num){
        this.nom = nom;
        this.num = num;
        this.lesAthletes = new ArrayList<>();
    }

    public String getNom(){
        return this.nom;
    }

    public int getNum() {
        return num;
    }

    public int getNbOr(){
        return this.nbOr;
    }

    public int getNbArgent(){
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

    public List<Athlete> getLesAthletes() {
        return lesAthletes;
    }

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
    @Override
    public String toString(){
        return this.nom + " avec "+ this.nbOr +" médailles d'or, "+this.nbArgent+" médailles d'argent et "+this.nbBronze+" médailles de bronze";
    }

}