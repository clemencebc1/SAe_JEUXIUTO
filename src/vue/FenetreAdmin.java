package vue;
import vue.*;

import javax.swing.text.LabelView;

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


    public void ajouteImage(){
        ImageView imageJO = new ImageView(new Image("file:./img/LogoJO.jpeg"));
        this.setRight(imageJO);
    }


    public void ajouteTop(){
        HBox HTop = new HBox();
        Button saisir = new Button("Saisir données");
        saisir.setOnAction(new ControleurSaisirDonnees(this.appli));
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
        athlete.setOnAction(new ControleurSaisirDonneesAthlete(this.appli));
        Button epreuve = new Button("ajoutez une epreuve");
        hbSaisirDonnees.getChildren().addAll(athlete, epreuve);
        hbSaisirDonnees.setMargin(athlete, new Insets(10)); 
        hbSaisirDonnees.setMargin(epreuve, new Insets(10));
        hbSaisirDonnees.setAlignment(Pos.CENTER);
        this.setCenter(hbSaisirDonnees);


    }

    public void saisirDonneesAthlete(){
        VBox vbSaisirDonnees = new VBox();

        HBox hnom = new HBox();
        Label lnom = new Label("Nom");
        TextField nom = new TextField();
        hnom.getChildren().addAll(lnom,nom);
        hnom.setMargin(lnom, new Insets(10)); 
        hnom.setMargin(nom, new Insets(10));

        HBox hprenom = new HBox();
        Label lprenom = new Label("Prénom");
        TextField prenom = new TextField();
        hprenom.getChildren().addAll(lprenom,prenom);
        hprenom.setMargin(lprenom, new Insets(10)); 
        hprenom.setMargin(prenom, new Insets(10));

        HBox hsexe = new HBox();
        Label lsexe = new Label("Sexe");
        TextField sexe = new TextField();
        hsexe.getChildren().addAll(lsexe,sexe);
        hsexe.setMargin(lsexe, new Insets(10)); 
        hsexe.setMargin(sexe, new Insets(10));


        HBox hpays = new HBox();
        Label lpays = new Label("Pays");
        TextField pays = new TextField();
        hpays.getChildren().addAll(lpays,pays);
        hpays.setMargin(lpays, new Insets(10)); 
        hpays.setMargin(pays, new Insets(10));


        HBox hforce = new HBox();
        Label lforce = new Label("Force");
        TextField force = new TextField();
        hforce.getChildren().addAll(lforce,force);
        force.focusedProperty().addListener(new ControleurFieldNB(force));
        hforce.setMargin(lforce, new Insets(10)); 
        hforce.setMargin(force, new Insets(10));


        HBox hendurance = new HBox();

        Label lendurance = new Label("Endurance");
        TextField endurance = new TextField();
        endurance.focusedProperty().addListener(new ControleurFieldNB(endurance));
        hendurance.getChildren().addAll(lendurance,endurance);
        hendurance.setMargin(lendurance, new Insets(10)); 
        hendurance.setMargin(endurance, new Insets(10));


        HBox hagilite = new HBox();
        Label lagilite = new Label("Agilité");
        TextField agilite = new TextField();
        agilite.focusedProperty().addListener(new ControleurFieldNB(agilite));
        hagilite.getChildren().addAll(lagilite,agilite);
        hagilite.setMargin(lagilite, new Insets(10)); 
        hagilite.setMargin(agilite, new Insets(10));


        HBox hequipe = new HBox();
        Label lequipe = new Label("Equipe");
        TextField equipe = new TextField();
        hequipe.getChildren().addAll(lnom,nom);
        hequipe.setMargin(lequipe, new Insets(10)); 
        hequipe.setMargin(equipe, new Insets(10));

        Button creer = new Button("Créer");
        

        vbSaisirDonnees.getChildren().addAll(hnom,hprenom,hsexe,hpays,hforce,hendurance,hagilite,hequipe,creer);
        vbSaisirDonnees.setAlignment(Pos.CENTER);
        this.setCenter(vbSaisirDonnees);


    }



    public void ajouteBottom(){
        this.setBottom(this.connexion);
        BorderPane.setAlignment(connexion, Pos.CENTER);
    }

}