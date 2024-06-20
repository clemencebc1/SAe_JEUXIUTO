package vue;
import java.sql.SQLException;

import bd.*;
import vue.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

public class ControleurModifierDonneesUser implements EventHandler<ActionEvent>{
    private FenetreAccueil appli;
    public ControleurModifierDonneesUser(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(ActionEvent event){
        this.appli.getFenetreAdmin().modifDonneesUser();


    }
}