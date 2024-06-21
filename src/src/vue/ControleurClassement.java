package vue;
import java.sql.SQLException;

import bd.*;
import vue.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

public class ControleurClassement implements EventHandler<ActionEvent>{
    private FenetreAccueil appli;
    public ControleurClassement(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(ActionEvent event){

        Button button = (Button) (event.getSource());
        this.appli.getFenetreJourna().initialiseBoutonAthletisme();
        this.appli.getFenetreJourna().initialiseBoutonClassement();
        this.appli.getFenetreJourna().initialiseBoutonEscrime();
        this.appli.getFenetreJourna().initialiseBoutonHandball();
        this.appli.getFenetreJourna().initialiseBoutonNatation();
        this.appli.getFenetreJourna().initialiseBoutonVolleyball();
        button.setStyle("-fx-background-color: black; -fx-background-radius: 1em; -fx-text-fill: #ffffff");
        switch (button.getText()) {
            case "Classement":
            this.appli.getFenetreJourna().trieMedailleOr();
                break;
            case "Natation":
            this.appli.getFenetreJourna().natation();break;
            
            case "Volley-Ball":
                this.appli.getFenetreJourna().volleyball();break;
            case "Handball":
            this.appli.getFenetreJourna().handball();break;
            case "Athlétisme":
            this.appli.getFenetreJourna().volleyball();break;
            default:
            this.appli.getFenetreJourna().escrime();
                break;
        }



    }
}