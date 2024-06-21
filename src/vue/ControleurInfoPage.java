package vue;
import java.sql.SQLException;

import bd.*;
import vue.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

public class ControleurInfoPage implements EventHandler<ActionEvent>{
    private FenetreAccueil appli;
    public ControleurInfoPage(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(ActionEvent event){
        this.appli.popUpInfo().showAndWait();


    }
}