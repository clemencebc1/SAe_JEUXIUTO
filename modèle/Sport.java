import java.util.List;
import java.util.ArrayList;

public abstract class Sport {
    /** le sport possède un nom */
    private String nom;
    /** le sport possède un milieu */
    private String milieu;
    private List<Epreuve> epreuves;
    /**constructeur de Sport mettant à jour les attributs d'un sport */
    public Sport(String nom, String milieu){
        this.nom = nom;
        this.milieu = milieu;
    }
    /**
     * @return le nom d'un sport
     */
    public String getNom(){
        return this.nom;
    }
    /**
     * @return le milieu d'un sport
     */
    public String getMilieu(){
        return this.milieu;
    }
    /** calcule le nombre de points que possède une équipe ou un athlète */
    public abstract <T extends Participant> double calculeRes(T participant);
}