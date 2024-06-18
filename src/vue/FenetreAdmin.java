package vue;
import vue.*;
import javafx.application.Application;
import javafx.application.Platform;
// import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


// javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d bin/ src/vue/*.java
// java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -cp bin vue.FenetreAccueil


public class FenetreAdmin extends BorderPane{
    private Button connexion;
    private FenetreAccueil appli;

    public FenetreAdmin(Button btn,FenetreAccueil appli){
        super();
        this.connexion = btn;
        this.appli = appli;
        this.ajouteTop();
        this.ajouteImage();
        this.ajouteBottom();
        
    }

    /** ajout de l'image
     */
    public void ajouteImage(){
        ImageView imageJO = new ImageView(new Image("file:./img/LogoJO.jpeg"));
        this.setRight(imageJO);
    }


    public void ajouteTop(){
        HBox HTop = new HBox();
        Button saisir = new Button("Saisir données");
        saisir.setOnAction(new ControleurSaisirDonnees(HTop));
        Button supprimer = new Button("Supprimer des données");
        Button modifier = new Button("Modifier des données");
        
        saisir.setStyle("-fx-background-radius: 1em;");
        supprimer.setStyle("-fx-background-radius: 1em;");
        modifier.setStyle("-fx-background-radius: 1em;");
        
        HTop.getChildren().addAll(saisir,supprimer,modifier);

        HTop.setMargin(saisir, new Insets(10)); 
        HTop.setMargin(supprimer, new Insets(10));
        HTop.setMargin(modifier, new Insets(10));
   

        HTop.setBackground(new Background(new BackgroundFill(Color.GREEN, null,null)));

        this.setTop(HTop);
    
    }
    public void saisirDonnees(){
        HBox hbSaisirDonnees = new HBox();
        Button athlete = new Button("ajoutez un athlète");
        Button epreuve = new Button("ajoutez une epreuve");
        hbSaisirDonnees.getChildren().addAll(athlete, epreuve);
        hbSaisirDonnees.setMargin(athlete, new Insets(10)); 
        hbSaisirDonnees.setMargin(epreuve, new Insets(10));
        this.setCenter(hbSaisirDonnees);


    }

    public void ajouteBottom(){
        this.setBottom(this.connexion);
        BorderPane.setAlignment(connexion, Pos.CENTER);
    }

}