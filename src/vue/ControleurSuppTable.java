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

public class ControleurSuppTable implements EventHandler<MouseEvent>{
    private FenetreAccueil appli;
    public ControleurSuppTable(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(MouseEvent event){
          Optional<ButtonType> reponse = this.appli.getFenetreAdmin().popUpSuppression().showAndWait(); // on lance la fenêtre popup et on attends la réponse
        // si la réponse est oui
            if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)){
            TableView<Athlete> tv = this.appli.getFenetreAdmin().getTableView();
            try {
                this.appli.getBD().effacerAthlete(tv.getSelectionModel().getSelectedItem().getNum());
                tv.getItems().remove(tv.getSelectionModel().getSelectedItem());
            }
            catch (SQLException e){
                this.appli.getFenetreAdmin().popUpBaseDeDonnees().showAndWait();
            }
            }

    }
}