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
        this.appli.getBD().insererAthlete(new Athlete(this.appli.getFenetreAdmin().getTnom().getText(), this.appli.getFenetreAdmin().getTprenom().getText(),this.appli.getFenetreAdmin().getTsexe().getText(),this.appli.getFenetreAdmin().getTforce(),this.appli.getFenetreAdmin().getTagilite(),this.appli.getFenetreAdmin().getTendurance(),pays,equipe));
        }
        catch(SQLException e){
            System.out.println(" Erreur");
        }
       

        
    }
}


