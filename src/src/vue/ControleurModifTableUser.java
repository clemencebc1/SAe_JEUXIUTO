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

public class ControleurModifTableUser implements EventHandler<MouseEvent>{
    private FenetreAccueil appli;
    public ControleurModifTableUser(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(MouseEvent event){
            TableView<Utilisateur> tv = this.appli.getFenetreAdmin().gTableViewUSer();
                Utilisateur u = tv.getSelectionModel().getSelectedItem(); // la ligne selectionnée
                FenetreAdmin fenetre = this.appli.getFenetreAdmin();
                fenetre.getUserRole().setText(u.getRole());
                

    }
}