package vue;
import java.sql.SQLException;

import bd.*;
import vue.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

public class ControleurModifDonnees implements EventHandler<ActionEvent>{
    private FenetreAccueil appli;
    public ControleurModifDonnees(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(ActionEvent event){
        this.appli.getFenetreAdmin().modifierDonnees();
        Button button = (Button) (event.getSource());
        button.setStyle("-fx-background-color: black; -fx-background-radius: 1em; -fx-text-fill: #ffffff");
        this.appli.getFenetreAdmin().etatInitialBoutonSupp();
        this.appli.getFenetreAdmin().etatInitialBoutonSaisir();


    }
}