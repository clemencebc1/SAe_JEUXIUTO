import java.util.List;
import java.util.ArrayList;


public class Epreuve {
    /**nom de l'epreuve */
    private String nom;
    /** la categorie de l'epreuve */
    private String categorie;
    /** le style de l'epreuve */
    private String style;
    private Sport sport;
    private List<Athlete> participants;
    private static List<Epreuve> epreuves;
    public Epreuve(String nom, String cat, String style){
        this.nom = nom;
        this.categorie = cat;
        this.style = style;
        this.participants = new ArrayList<>();
    }
    public String getNom(){
        return this.nom;
    }
    public String getCategorie(){
        return this.categorie;
    }
    public String getStyle(){
        return this.style;
    }
    public List<Athlete> getParticipants(){
        return this.participants;
    }
    public static void ajoutEpreuve(Epreuve e){
        Epreuve.epreuves.add(e);
    }
    public List<Epreuve> getEpreuves(){
        return Epreuve.epreuves;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public Sport getSport() {
        return sport;
    }
    public void setSport(Sport sport) {
        this.sport = sport;
    }
    public void ajoutParticipants(Athlete p) {
        this.participants.add(p);
    }
    public static void setEpreuves(List<Epreuve> epreuves) {
        Epreuve.epreuves = epreuves;
    }
}