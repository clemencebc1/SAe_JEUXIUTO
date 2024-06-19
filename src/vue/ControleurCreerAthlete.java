package vue;

import java.sql.SQLException;
import java.util.Optional;

import bd.*;
import vue.*;
import participant.*;
import sport.*;
import autre.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        this.appli.getBD().Athlete(this.appli.getConnexionMySQL());
        if (this.appli.getBD().avoirEquipeParNom(this.appli.getFenetreAdmin().getTequipe().getText()) != null){
            equipe = this.appli.getBD().avoirEquipeParNom(this.appli.getFenetreAdmin().getTequipe().getText());
        }
        Pays pays = this.appli.getBD().avoirPaysParNom(this.appli.getFenetreAdmin().getTpays().getText());
        Athlete a = new Athlete(this.appli.getFenetreAdmin().getTnom().getText(), this.appli.getFenetreAdmin().getTprenom().getText(),this.appli.getFenetreAdmin().getTsexe().getText(),this.appli.getFenetreAdmin().getTforce(),this.appli.getFenetreAdmin().getTagilite(),this.appli.getFenetreAdmin().getTendurance(),pays,equipe);
 
        int nb = this.appli.getBD().insererAthlete(a)-2;
        if (nb==0){
            Optional<ButtonType> reponse = this.appli.getFenetreAdmin().popUpExisteDeja().showAndWait(); // on lance la fenêtre popup et on attends la réponse
        // si la réponse est oui
            if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)){
                this.appli.getBD().majAthlete(a);
                this.appli.getFenetreAdmin().popUpInsertionReussie().showAndWait();
            }
            else {
                this.appli.getFenetreAdmin().popUpNonChangé().showAndWait();
            }

        }
        else {
            this.appli.getFenetreAdmin().popUpInsertionReussie().showAndWait();


        }
        }
        catch(SQLException e){
            System.out.println(" Erreur"+e.getMessage());
        }
       

        
    }
}


