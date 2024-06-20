package participant;
import java.util.List;
import java.util.ArrayList;

public class Pays{

    private String nom;
    private int nbOr;

    private int nbArgent;

    private int nbBronze;
    private Integer num;

    private List<Athlete> lesAthletes;

    public Pays(String nom){
        this.nom = nom;
        this.num = null;
        this.lesAthletes = new ArrayList<>();
        this.nbOr = 0;
        this.nbArgent = 0;
        this.nbBronze = 0;
    }

    public Pays(String nom, Integer num, int nbOr, int nbArgent, int nbBronze) {
        this.nom = nom;
        this.num = num;
        this.nbOr = nbOr;
        this.nbArgent = nbArgent;
        this.nbBronze = nbBronze;
    }
    

    public Pays(String nom, Integer num) {
        this.nom = nom;
        this.num = num;
        this.nbOr = 0;
        this.nbArgent = 0;
        this.nbBronze = 0;
    }

    //getters

    public String getNom(){
        return this.nom;
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


    //setters
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

    public Integer getNum() {
        return num;
    }

}