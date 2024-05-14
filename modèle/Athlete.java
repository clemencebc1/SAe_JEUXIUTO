public class Athlete implements Participant{
    private String nom;
    private String prenom;
    private String sexe;
    private int force;
    private int agilite;
    private int endurance;
    private Pays pays;
    public Athlete(String nom, String prenom, String sexe, int force, int agilite, int endurance, Pays pays){
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurance;
        this.pays = pays;
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
        return e.getSport().calculeRes(e);

    }
}