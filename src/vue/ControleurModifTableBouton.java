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

public class ControleurModifTableBouton implements EventHandler<ActionEvent>{
    private FenetreAccueil appli;
    public ControleurModifTableBouton(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(ActionEvent event){
            TableView<Athlete> tv = this.appli.getFenetreAdmin().getTableView();
            try {
                Equipe equipe = null;
                if (this.appli.getBD().avoirEquipeParNom(this.appli.getFenetreAdmin().getTequipe().getText()) != null){
                    equipe = this.appli.getBD().avoirEquipeParNom(this.appli.getFenetreAdmin().getTequipe().getText());
                }
                Pays pays = this.appli.getBD().avoirPaysParNom(this.appli.getFenetreAdmin().getTpays().getText());
                
                Athlete a = tv.getSelectionModel().getSelectedItem();
                Athlete nouveauAthlete = new Athlete(this.appli.getFenetreAdmin().getTnom().getText(), this.appli.getFenetreAdmin().getTprenom().getText(),this.appli.getFenetreAdmin().getTsexe().getText(),this.appli.getFenetreAdmin().getTforce(),this.appli.getFenetreAdmin().getTagilite(),this.appli.getFenetreAdmin().getTendurance(),pays,equipe);
                this.appli.getBD().majAthlete(nouveauAthlete);
                Optional<ButtonType> reponse = this.appli.getFenetreAdmin().popUpModifier().showAndWait(); // on lance la fenêtre popup et on attends la réponse
        // si la réponse est oui
                if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)){
                    tv.getItems().add(nouveauAthlete);
                    tv.getItems().remove(a);}
                
            }
            catch (SQLException e){
                this.appli.getFenetreAdmin().popUpBaseDeDonnees().showAndWait();
            }
            catch (NumberFormatException e){
                System.err.println("erreur");
            }
            

    }
}
                