package vue;
import java.sql.SQLException;

import bd.*;
import vue.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControleurTableauxChoix implements EventHandler<ActionEvent>{
    private FenetreAccueil appli;
    public ControleurTableauxChoix(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(ActionEvent event){
        ComboBox<String> cb = this.appli.getFenetreJourna().getComboboxClassement();



    }
}