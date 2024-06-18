package vue;

import java.sql.SQLException;
import bd.*;
import vue.*;
import participant.*;
import sport.*;
import autre.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import participant.Athlete;
import participant.Equipe;
import participant.Pays;

public class ControleurCreerAthlete implements EventHandler<ActionEvent> {
    private FenetreAccueil appli;

    public ControleurCreerAthlete(FenetreAccueil appli) {
        this.appli = appli;
        
    }
    // on recupere les donnes du textfield, on maj la base de donnees et le tableau
    @Override
    public void handle(ActionEvent event){
        try{
            Button button = (Button) (event.getSource());
            Equipe equipe = null;
            System.out.println(equipe);
            this.appli.getBD().Athlete(this.appli.getConnexionMySQL());
            System.out.println(equipe);
            if (this.appli.getBD().avoirEquipeParNom(this.appli.getFenetreAdmin().getTequipe().getText()) != null){
                System.out.println(equipe);
                equipe = this.appli.getBD().avoirEquipeParNom(this.appli.getFenetreAdmin().getTequipe().getText());
                System.out.println(equipe);
            }
            Pays pays = this.appli.getBD().avoirPaysParNom(this.appli.getFenetreAdmin().getTpays().getText());
            System.out.println(equipe);
            String nom = this.appli.getFenetreAdmin().getTnom().getText();
            String prenom = this.appli.getFenetreAdmin().getTprenom().getText();
            String sexe = this.appli.getFenetreAdmin().getTsexe().getText();
            Integer force = Integer.parseInt(this.appli.getFenetreAdmin().getTforce().getText());
            Integer agilite = Integer.parseInt(this.appli.getFenetreAdmin().getTagilite().getText());
            Integer endurance = Integer.parseInt(this.appli.getFenetreAdmin().getTendurance().getText());
            Athlete nouveauAthlete = new Athlete(nom, prenom,sexe,force,agilite,endurance,pays,equipe);
            this.appli.getBD().insererAthlete(nouveauAthlete);
            /**
            this.appli.getFenetreAdmin().getTableView().getItems.add(nouveauAthlete);
 
             
             */

            }
        catch(SQLException e){
            System.err.println(" Erreur");
        }
        catch (NumberFormatException e){
            System.err.println("nb");
            this.appli.getFenetreAdmin().popUpNombreException();
        }
 
    }
}
