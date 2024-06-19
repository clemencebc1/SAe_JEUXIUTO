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
            try {
                Equipe equipe = null;
                Athlete a = tv.getSelectionModel().getSelectedItem();
                FenetreAdmin fenetre = this.appli.getFenetreAdmin();
                fenetre.getTnom().setText(a.getNom());
                fenetre.getTprenom().setText(a.getPrenom());
                fenetre.getTpays().setText(a.getPays().getNom());
                fenetre.getTsexe().setText(a.getSexe());
                if (this.appli.getBD().avoirEquipeParNom(this.appli.getFenetreAdmin().getTequipe().getText()) != null){
                    equipe = this.appli.getBD().avoirEquipeParNom(this.appli.getFenetreAdmin().getTequipe().getText());
                }
                Pays pays = this.appli.getBD().avoirPaysParNom(this.appli.getFenetreAdmin().getTpays().getText());
                
                tv.getItems().remove(tv.getSelectionModel().getSelectedItem());
                Athlete nouveauAthlete = new Athlete(this.appli.getFenetreAdmin().getTnom().getText(), this.appli.getFenetreAdmin().getTprenom().getText(),this.appli.getFenetreAdmin().getTsexe().getText(),this.appli.getFenetreAdmin().getTforce(),this.appli.getFenetreAdmin().getTagilite(),this.appli.getFenetreAdmin().getTendurance(),pays,equipe);
                this.appli.getBD().majAthlete(nouveauAthlete);
                tv.getItems().add(nouveauAthlete);
            }
            catch (SQLException e){
                this.appli.getFenetreAdmin().popUpBaseDeDonnees().showAndWait();
            }
            

    }
}