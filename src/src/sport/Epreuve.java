package sport;
import participant.*;
import autre.*;
import comparateur.ComparePoints;

import java.util.Map.Entry;

import java.util.List;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class Epreuve {
    /**nom de l'epreuve */
    private String nom;
    /** la categorie de l'epreuve */
    private String categorie;
    /** le style de l'epreuve */
    private Sport sport;
    private Integer num;
    private List<Athlete> participants;
    private static List<Epreuve> epreuves; // à chaque nouvelle epreuve on l'ajoute à une liste d'epreuve existente
    public Epreuve(String nom, String cat, Sport sport,Integer num){
        this.nom = nom;
        this.categorie = cat;
        this.sport = sport;
        this.num = num;
        this.participants = new ArrayList<>();
    }

    // getters
    public String getNom(){
        return this.nom;
    }
    public String getCategorie(){
        return this.categorie;
    }
    public Integer getNum() {
        return num;
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
        this.participants.addAll(equipe.getLesAthletes());
    }

    public static void setEpreuves(List<Epreuve> epreuves) {
        Epreuve.epreuves = epreuves;
    }

    /** fournit un classement des pays
     * @return une liste de pays placés selon leurs résultats
     */
    public List<?> classementMedaille(Comparator<Athlete> comp, Tri tri){
        List<Athlete> athletes = new ArrayList<>(this.participants);
        Collections.sort(athletes, comp);
        if (tri == Tri.PAYS){
            List<Pays> pays =new ArrayList<>();
            for (Athlete athlete : this.participants){
                if (!(pays.contains(athlete.getPays()))){
                    pays.add(athlete.getPays());
                }
            }
            return pays;
        }
        else if (tri == Tri.EQUIPE){
            List<Equipe> equipe =new ArrayList<>();
            for (Athlete athlete : this.participants){
                if (!(equipe.contains(athlete.getEquipe()))){
                    equipe.add(athlete.getEquipe());
                }
            }
            return equipe;
        }
        else if (tri==Tri.EPREUVE){
            return this.classementPoints();
        }
        return athletes;
    }
    public List<Map<Athlete, Double>> classementPoints(){
        List<Map<Athlete, Double>> classement = new ArrayList<>();
            for (Athlete a : this.participants){
                Map<Athlete, Double> points = new HashMap<>();
                points.put(a, this.sport.calculeRes(a));
                classement.add(points);
            }
            Comparator<Map<Athlete,Double>> comparateur = new ComparePoints();
            Collections.sort(classement, comparateur);
            return classement;
    }

    @Override
    public String toString() {
        return "L'epreuve est " + nom + ", categorie " + categorie + ", sport " + sport
                + ", participants " + participants;
    }
    
}