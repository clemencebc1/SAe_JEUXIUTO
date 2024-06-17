package vue;
import java.sql.SQLException;

import bd.*;
import vue.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;


public class ControleBouton implements EventHandler<ActionEvent>{
    
    private FenetreAccueil appli;
    
    public ControleBouton(FenetreAccueil appli) throws ClassNotFoundException{
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) (event.getSource());
        if (button.getText().contains("Connexion")){
            String id = this.appli.getFenetreCo().getTfId().getText();
            String pwd = this.appli.getFenetreCo().getPwd().getText();
            try {
                System.out.println("ok");
                if (this.appli.getConnexionMySQL().isConnecte(id, pwd)){
                    AthleteBD athleteBD = new AthleteBD();
                    athleteBD.Athlete(this.appli.getConnexionMySQL());
                    if (athleteBD.estAdmin(id, pwd)){
                        this.appli.popUpConnexion().showAndWait();
                        
                    }
                    else if (athleteBD.estJourna(id, pwd)){
                        this.appli.afficheFenetreJournaliste();
                        this.appli.popUpConnexion().showAndWait();
                    }
                    else if (athleteBD.estOrga(id, pwd)){
                        this.appli.popUpConnexion().showAndWait();

                    }
                }
                else {this.appli.popUpErreurConnexion().showAndWait();}

        }
    catch (SQLException e){
        System.err.println("Problème avec la base de données"+e.getMessage());
    }
}
    else if (button.getText().contains("Déconnexion")){
            this.appli.seConnecter();
    }
    
    }
}