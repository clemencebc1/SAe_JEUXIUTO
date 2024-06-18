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

    private TextField tnom;
    private TextField tprenom;
    private TextField tsexe ;
    private TextField tpays;
    private TextField tforce;
    private TextField tendurance;
    private TextField tagilite;
    private TextField tequipe;

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
        this.tnom = new TextField();
        hnom.getChildren().addAll(lnom,tnom);
        hnom.setMargin(lnom, new Insets(10)); 
        hnom.setMargin(tnom, new Insets(10));

        HBox hprenom = new HBox();
        Label lprenom = new Label("Prénom");
        this.tprenom = new TextField();
        hprenom.getChildren().addAll(lprenom,tprenom);
        hprenom.setMargin(lprenom, new Insets(10)); 
        hprenom.setMargin(tprenom, new Insets(10));

        HBox hsexe = new HBox();
        Label lsexe = new Label("Sexe");
        this.tsexe = new TextField();
        hsexe.getChildren().addAll(lsexe,tsexe);
        hsexe.setMargin(lsexe, new Insets(10)); 
        hsexe.setMargin(tsexe, new Insets(10));


        HBox hpays = new HBox();
        Label lpays = new Label("Pays");
        this.tpays = new TextField();
        hpays.getChildren().addAll(lpays,tpays);
        hpays.setMargin(lpays, new Insets(10)); 
        hpays.setMargin(tpays, new Insets(10));


        HBox hforce = new HBox();
        Label lforce = new Label("Force");
        this.tforce = new TextField();
        hforce.getChildren().addAll(lforce,tforce);
        tforce.focusedProperty().addListener(new ControleurFieldNB(tforce));
        hforce.setMargin(lforce, new Insets(10)); 
        hforce.setMargin(tforce, new Insets(10));


        HBox hendurance = new HBox();

        Label lendurance = new Label("Endurance");
        this.tendurance = new TextField();
        tendurance.focusedProperty().addListener(new ControleurFieldNB(tendurance));
        hendurance.getChildren().addAll(lendurance,tendurance);
        hendurance.setMargin(lendurance, new Insets(10)); 
        hendurance.setMargin(tendurance, new Insets(10));


        HBox hagilite = new HBox();
        Label lagilite = new Label("Agilité");
        this.tagilite = new TextField();
        tagilite.focusedProperty().addListener(new ControleurFieldNB(tagilite));
        hagilite.getChildren().addAll(lagilite,tagilite);
        hagilite.setMargin(lagilite, new Insets(10)); 
        hagilite.setMargin(tagilite, new Insets(10));


        HBox hequipe = new HBox();
        Label lequipe = new Label("Equipe");
        this.tequipe = new TextField();
        hequipe.getChildren().addAll(lnom,tequipe);
        hequipe.setMargin(lequipe, new Insets(10)); 
        hequipe.setMargin(tequipe, new Insets(10));

        Button creer = new Button("Créer");
        creer.setOnAction(new ControleurCreerAthlete(this.appli));

        vbSaisirDonnees.getChildren().addAll(hnom,hprenom,hsexe,hpays,hforce,hendurance,hagilite,hequipe,creer);
        vbSaisirDonnees.setAlignment(Pos.CENTER);
        this.setCenter(vbSaisirDonnees);


    }
    

    public void ajouteBottom(){
        this.setBottom(this.connexion);
        BorderPane.setAlignment(connexion, Pos.CENTER);
    }


    public Button getConnexion() {
        return connexion;
    }


    public void setConnexion(Button connexion) {
        this.connexion = connexion;
    }


    public FenetreAccueil getAppli() {
        return appli;
    }


    public void setAppli(FenetreAccueil appli) {
        this.appli = appli;
    }


    public TextField getTnom() {
        return tnom;
    }


    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }


    public TextField getTprenom() {
        return tprenom;
    }


    public void setTprenom(TextField tprenom) {
        this.tprenom = tprenom;
    }


    public TextField getTsexe() {
        return tsexe;
    }


    public void setTsexe(TextField tsexe) {
        this.tsexe = tsexe;
    }


    public TextField getTpays() {
        return tpays;
    }


    public void setTpays(TextField tpays) {
        this.tpays = tpays;
    }


    public Integer getTforce() {
        return Integer.parseInt(tforce.getText());
    }


    public void setTforce(TextField tforce) {
        this.tforce = tforce;
    }


    public Integer getTendurance() {
        return Integer.parseInt(tendurance.getText());
    }


    public void setTendurance(TextField tendurance) {
        this.tendurance = tendurance;
    }


    public Integer getTagilite() {
        return Integer.parseInt(tagilite.getText());
    }


    public void setTagilite(TextField tagilite) {
        this.tagilite = tagilite;
    }


    public TextField getTequipe() {
        return tequipe;
    }


    public void setTequipe(TextField tequipe) {
        this.tequipe = tequipe;
    }

}