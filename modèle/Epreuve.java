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
    private static List<Epreuve> epreuves; // à chaque nouvelle epreuve on l'ajoute à une liste d'epreuve existente
    public Epreuve(String nom, String cat, String style, Sport sport){
        this.nom = nom;
        this.categorie = cat;
        this.style = style;
        this.participants = new ArrayList<>();
    }

    // getters
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
    public static List<Epreuve> getEpreuves(){
        return Epreuve.epreuves;
    }
    public Sport getSport() {
        return sport;
    }
    
    // setters
    public static void ajoutEpreuve(Epreuve e){
        Epreuve.epreuves.add(e);
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
    public void setSport(Sport sport) {
        this.sport = sport;
    }

    /** ajoute un particpant (athlete) à la liste d'athlete de l'epreuve
     * @param athlete un athlete
     */
    public void ajoutParticipants(Athlete athlete) {
        this.participants.add(athlete);
    }

    /** ajoute des participants (athletes) à la liste d'athlete de l'epreuve
     * @param equipe une equipe d'athlete
     */
    public void ajoutParticipants(Equipe equipe){
        for (Athlete athlete : equipe.getLesAthletes()){
            this.participants.add(athlete);
        }
    }
    public static void setEpreuves(List<Epreuve> epreuves) {
        Epreuve.epreuves = epreuves;
    }

    /** fournit un classement des pays
     * @return une liste de pays placés selon leurs résultats
     */
    public List<Pays> classement(){
        List<Athlete> athletes = new ArrayList<>(this.participants);
        return new ArrayList<>();

    }
}