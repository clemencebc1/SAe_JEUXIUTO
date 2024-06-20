package bd;

public class Utilisateur{
    private String nom;
    private String mdp;
    private String role;

    public Utilisateur(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
        this.role = "journaliste";
    }

    public Utilisateur(String nom, String mdp, String role) {
        this.nom = nom;
        this.mdp = mdp;
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    

    
}