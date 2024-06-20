package participant;
import sport.*;

public class Athlete implements Participant{
    private String nom;
    private String prenom;
    private String sexe;
    private int force;
    private int agilite;
    private int endurance;
    private Pays pays;
    private Equipe equipe;
    private Integer num;
    public Athlete(String nom, String prenom, String sexe, int force, int agilite, int endurance, Pays pays, Integer num){
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurance;
        this.pays = pays;
        this.equipe = null;
        this.num = num;
    }
    public Athlete(String nom, String prenom, String sexe, int force, int agilite, int endurance, Pays pays, Equipe equipe,Integer num){
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurance;
        this.pays = pays;
        this.equipe = equipe;
        this.num = num;
    }
    public Athlete(String nom, String prenom, String sexe, int force, int agilite, int endurance, Pays pays,Equipe equipe){
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurance;
        this.pays = pays;
        this.equipe = equipe;
        this.num = null;
    }
    public Equipe getEquipe(){
        return this.equipe;
    }
    public Pays getPays(){
        return this.pays;
    }
    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public String getSexe(){
        return this.sexe;
    }
    public Integer getNum() {
        return num;
    }

    public int getForce(){
        return this.force;
    }

    public int getAgilite(){
        return this.agilite;
    }

    public int getEndurance(){
        return this.endurance;
    }
    /**
     * @param epreuve une epreuve 
     */
    @Override
    public double participer(Epreuve e){
        e.ajoutParticipants(this);
        return e.getSport().calculeRes(this);

    }
    @Override
    public String toString() {
        String res =  "Athlete de nom " + nom + ", prenom " + prenom + ", sexe " + sexe + ", force " + force + ", agilite "
                + agilite + ", endurance " + endurance + ", pays " + pays;
        if (equipe != null){
            res += ", son equipe est "+this.equipe;
            return res;
        }
        return res+" n'a pas d'equipe";

    }

}