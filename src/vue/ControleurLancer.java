package vue;
import java.sql.SQLException;

import bd.*;
import vue.*;
import sport.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

public class ControleurLancer implements EventHandler<ActionEvent>{
    private FenetreAccueil appli;
    public ControleurLancer(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(ActionEvent event){
        try {
        RadioButton r = (RadioButton) this.appli.getFenetreOrganisateur().getGroup().getSelectedToggle();
        String choixSport = r.getText();
        String style = "";
        String sexe = null;
        Epreuve e = null;

        switch (choixSport){
            case "Natation": style = this.appli.getFenetreOrganisateur().getCmbNatation().getValue();
            sexe = style.charAt(style.length()-1)+"";
            style = style.substring(0, style.length()-2);
            e = this.appli.getBD().avoirEpreuveParNom(style, sexe, FenetreOrganisateur.natation);
            break;
            case "Volleyball": style = this.appli.getFenetreOrganisateur().getCmbVolleyball().getValue();
            sexe = style.charAt(style.length()-1)+"";
            style = style.substring(0, style.length()-2);
            e = this.appli.getBD().avoirEpreuveParNom(style, sexe, FenetreOrganisateur.volleyball);break;
            case "Athlétisme": style = this.appli.getFenetreOrganisateur().getCmbAthletisme().getValue();
            sexe = style.charAt(style.length()-1)+"";
            style = style.substring(0, style.length()-2);
            e = this.appli.getBD().avoirEpreuveParNom(style, sexe, FenetreOrganisateur.athletisme);break;
            case "Escrime": style = this.appli.getFenetreOrganisateur().getCmbEscrime().getValue();
            sexe = style.charAt(style.length()-1)+"";
            style = style.substring(0, style.length()-2);
            e = this.appli.getBD().avoirEpreuveParNom(style, sexe, FenetreOrganisateur.escrime);break;
            case "Handball": style = this.appli.getFenetreOrganisateur().getCmbHandball().getValue();
            sexe = style.charAt(style.length()-1)+"";
            style = style.substring(0, style.length()-2);
            e = this.appli.getBD().avoirEpreuveParNom(style, sexe, FenetreOrganisateur.handball);
        }
        this.appli.getBD().lancerEpreuve(e);
        this.appli.getFenetreOrganisateur().popUpEpreuveLancee().showAndWait();
    }
    catch (SQLException e){
        System.err.println(e.getMessage());
        this.appli.getFenetreOrganisateur().popUpBaseDeDonnees().showAndWait();
    }
    catch (PasDeParticipantException e){
        this.appli.getFenetreOrganisateur().popUpPasDeParticipant().showAndWait();
    }


    }
}