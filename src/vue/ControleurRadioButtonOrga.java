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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


public class ControleurRadioButtonOrga implements EventHandler<ActionEvent>{
    private FenetreAccueil appli;
    public ControleurRadioButtonOrga(FenetreAccueil appli){
        this.appli=appli;
    }

    @Override
    public void handle(ActionEvent event){
        try {
            RadioButton selectedRadioButton = (RadioButton) this.appli.getFenetreOrganisateur().getSelectedToggle();
            String selectedSport = selectedRadioButton.getUserData().toString();
            String choixSport = selectedRadioButton.getText();
            String style = "";
            String sexe = null;
            Epreuve e = null;
            switch (selectedSport) {
                case "Natation":
                if (this.appli.getFenetreOrganisateur().getCmbNatation().getValue() == "Catégorie") {
                    System.out.println("Veuillez sélectionner une catégorie");
                    return;
                }         
                style = this.appli.getFenetreOrganisateur().getCmbNatation().getValue().toString();
                    sexe = style.charAt(style.length() - 1) + "";
                    style = style.substring(0, style.length() - 2);
            
                    break;
                case "Volleyball":
                if (this.appli.getFenetreOrganisateur().getCmbVolleyball().getValue() == "Catégorie") {
                    System.out.println("Veuillez sélectionner une catégorie");
                    return;
                }        
                style = this.appli.getFenetreOrganisateur().getCmbVolleyball().getValue();
                    sexe = style.charAt(style.length() - 1) + "";
                    style = style.substring(0, style.length() - 2);
                    
                    break;
                case "Athlétisme":
                if (this.appli.getFenetreOrganisateur().getCmbAthletisme().getValue() == "Catégorie") {
                    System.out.println("Veuillez sélectionner une catégorie");
                    return;
                }        
                style = this.appli.getFenetreOrganisateur().getCmbAthletisme().getValue();
                    sexe = style.charAt(style.length() - 1) + "";
                    style = style.substring(0, style.length() - 2);
                    
                    break;
                case "Escrime":
                if (this.appli.getFenetreOrganisateur().getCmbEscrime().getValue() == "Catégorie") {
                    System.out.println("Veuillez sélectionner une catégorie");
                    return;
                }    
                style = this.appli.getFenetreOrganisateur().getCmbEscrime().getValue();
                    sexe = style.charAt(style.length() - 1) + "";
                    style = style.substring(0, style.length() - 2);
                    
                    break;
                case "Handball":
                if (this.appli.getFenetreOrganisateur().getCmbHandball().getValue() == "Catégorie") {
                    System.out.println("Veuillez sélectionner une catégorie");
                    return;
                }
                style = this.appli.getFenetreOrganisateur().getCmbHandball().getValue();
                    sexe = style.charAt(style.length() - 1) + "";
                    style = style.substring(0, style.length() - 2);
                    e = this.appli.getBD().avoirEpreuveParNom(style, sexe, FenetreOrganisateur.handball);
                    break;        
            }
            System.out.println(style+" "+sexe);
        }
            catch (SQLException e){
                this.appli.getFenetreAdmin().popUpBaseDeDonnees().showAndWait();   
                 
    }
}
}

    
    

    


