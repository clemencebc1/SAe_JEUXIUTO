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
                if (this.appli.getConnexionMySQL().isConnecte(id, pwd)){
                
                    this.appli.getBD().Athlete(this.appli.getConnexionMySQL());
                    if (this.appli.getBD().estAdmin(id, pwd)){
                        this.appli.afficheFenetreAdmin();
                        this.appli.popUpConnexion().showAndWait();
                        
                    }
                    else if (this.appli.getBD().estJourna(id, pwd)){
                        this.appli.afficheFenetreJournaliste();
                        this.appli.popUpConnexion().showAndWait();
                    }
                    else if (this.appli.getBD().estOrga(id, pwd)){
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
    else if (button.getText().contains("Inscription")){
        try {
            this.appli.getBD().Athlete(this.appli.getConnexionMySQL());
            this.appli.getBD().insererUser(new Utilisateur(this.appli.getFenetreInscription().getTfId().getText(), this.appli.getFenetreInscription().getTfId().getText()));
            this.appli.popUpConnexion().showAndWait();
            this.appli.afficheFenetreJournaliste();
            button.setText("S'inscire");

        }
        catch (SQLException e){
            this.appli.getFenetreInscription().popUpIdPresent().showAndWait();

        }

    }
    else {
        button.setText("Inscription");
        this.appli.inscrire();
    }
    
    }
}