package vue;
import java.sql.SQLException;
import java.util.Optional;

import bd.*;
import vue.*;
import participant.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ControleurModifTable implements EventHandler<MouseEvent>{
    private FenetreAccueil appli;
    public ControleurModifTable(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(MouseEvent event){
            TableView<Athlete> tv = this.appli.getFenetreAdmin().getTableView();
                Athlete a = tv.getSelectionModel().getSelectedItem();
                FenetreAdmin fenetre = this.appli.getFenetreAdmin();
                fenetre.getTnom().setText(a.getNom());
                fenetre.getTprenom().setText(a.getPrenom());
                fenetre.getTpays().setText(a.getPays().getNom());
                fenetre.getTsexe().setText(a.getSexe());
                
        

            

    }
}