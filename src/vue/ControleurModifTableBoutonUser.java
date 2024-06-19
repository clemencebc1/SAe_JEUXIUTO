package vue;
import java.sql.SQLException;
import java.util.Optional;

import bd.*;
import vue.*;
import participant.*;
import javafx.event.ActionEvent ;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ControleurModifTableBoutonUser implements EventHandler<ActionEvent>{
    private FenetreAccueil appli;
    public ControleurModifTableBoutonUser(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(ActionEvent event){
            TableView<Utilisateur> tv = this.appli.getFenetreAdmin().gTableViewUSer();
            try {
                
                Utilisateur u = tv.getSelectionModel().getSelectedItem();
                Utilisateur nouveauUser = new Utilisateur(u.getNom(), u.getMdp(),this.appli.getFenetreAdmin().getUserRole().getText());
                this.appli.getBD().majUser(nouveauUser);
                Optional<ButtonType> reponse = this.appli.getFenetreAdmin().popUpModifier().showAndWait(); // on lance la fenêtre popup et on attends la réponse
        // si la réponse est oui
                if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)){
                    tv.getItems().add(nouveauUser);
                    tv.getItems().remove(u);}
                
            }
            catch (SQLException e){
                this.appli.getFenetreAdmin().popUpBaseDeDonnees().showAndWait();
            }
    }
}