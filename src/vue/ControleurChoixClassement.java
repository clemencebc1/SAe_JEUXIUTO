package vue;
import java.sql.SQLException;
import java.util.Optional;

import bd.*;
import vue.*;
import participant.*;
import javafx.event.ActionEvent ;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ControleurChoixClassement implements EventHandler<ActionEvent>{
    private FenetreAccueil appli;
    public ControleurChoixClassement(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(ActionEvent event){
            ComboBox<String> cb = (ComboBox<String>) event.getSource();
        if (cb.getSelectionModel().getSelectedItem()=="Medaille Argent"){
            this.appli.getFenetreJourna().trieMedailleArgent();
        }
        else if (cb.getSelectionModel().getSelectedItem()=="Medaille Bronze"){
            this.appli.getFenetreJourna().trieMedailleBronze();
        }
        else {
            this.appli.getFenetreJourna().classement();
        }


            
        

            

    }
}