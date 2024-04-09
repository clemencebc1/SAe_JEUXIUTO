import java.util.List;
import java.util.ArrayList;


public class Epreuve {
    /**nom de l'epreuve */
    private String nom;
    /** la categorie de l'epreuve */
    private String categorie;
    /** le style de l'epreuve */
    private String style;
    private List<Participant> participants;
    public Epreuve(String nom, String cat, String style){
        this.nom = nom;
        this.categorie = cat;
        this.style = style;
        this.participants = new ArrayList<>();
    }
    public String getNom(){
        return "";
    }
    public String getCategorie(){
        return "";
    }
    public String getStyle(){
        return "";
    }
    public List<Athlete> getParticipants(){
        return null;
    }
}