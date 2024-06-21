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
            String choix = cb.getSelectionModel().getSelectedItem();
            switch (choix){
                case "Medaille Argent":
                this.appli.getFenetreJourna().trieMedailleArgent();break;
            case "Medialle Bronze": this.appli.getFenetreJourna().trieMedailleBronze();break;
            case "Medaille Or": this.appli.getFenetreJourna().trieMedailleOr();break;
            case "Natation 100 brasse M": this.appli.getFenetreJourna().natation();break;
            case "Natation 100 brasse F": this.appli.getFenetreJourna().natation2();break;
            case "Volley-ball F":  this.appli.getFenetreJourna().volleyball();break;
            case "Volley-ball M":this.appli.getFenetreJourna().volleyball2();break;
            case "Handball F": this.appli.getFenetreJourna().handball();break;
            case "Handball M": this.appli.getFenetreJourna().handball2();break;
            case "Athlétisme 110m F": this.appli.getFenetreJourna().ahtletisme();break;
            case "Athlétisme 110m M": this.appli.getFenetreJourna().ahtletisme2();break;
            case "Athlétisme 4x100m relais F": this.appli.getFenetreJourna().ahtletisme3();break;
            case "Athlétisme 4x100m relais M": this.appli.getFenetreJourna().ahtletisme4();break;
            case "Escrime fleuret F": this.appli.getFenetreJourna().escrime();break;
            case "Escrime fleuret M": this.appli.getFenetreJourna().escrime1();break;
            case "Escrime épée M": this.appli.getFenetreJourna().escrime2();break;
            case "Escrime épée F": this.appli.getFenetreJourna().escrime3();break;

    }
}
}