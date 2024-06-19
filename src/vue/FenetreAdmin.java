package vue;
import vue.*;
import participant.*;

import java.sql.SQLException;
import java.util.Optional;

import javax.swing.text.LabelView;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
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
    private TableView<Athlete> tableview;
    private Button saisir;
    private Button supprimer;
    private Button modifier;

    public FenetreAdmin(Button btn,FenetreAccueil appli){
        super();
        this.connexion = btn;
        this.appli = appli;
        this.saisir = new Button("Saisir données");
        this.supprimer= new Button("Supprimer des données");
        this.modifier = new Button("Modifier des données");
        this.tableview = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom");
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> sexe = new TableColumn<>("Sexe");
            TableColumn<Athlete, String> pays = new TableColumn<>("Pays");
            TableColumn<Athlete, String> force = new TableColumn<>("Force");
            TableColumn<Athlete, String> endurance = new TableColumn<>("Endurance");
            TableColumn<Athlete, String> agilite = new TableColumn<>("Agilite");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
            pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
            force.setCellValueFactory(new PropertyValueFactory<>("force"));
            endurance.setCellValueFactory(new PropertyValueFactory<>("endurance"));
            agilite.setCellValueFactory(new PropertyValueFactory<>("agilite"));
            tableview.getColumns().add(nom);
            tableview.getColumns().add(prenom);
            tableview.getColumns().add(sexe);
            tableview.getColumns().add(pays);
            tableview.getColumns().add(force);
            tableview.getColumns().add(endurance);
            tableview.getColumns().add(agilite);
    

        this.ajouteTop();
        this.ajouteImage();
        this.ajouteBottom();
        
    }

    // ajoute les images
    public void ajouteImage(){
        ImageView imageJO = new ImageView(new Image("file:./img/LogoJO.jpeg"));
        this.setRight(imageJO);
    }

    // ajoute la naviguation 
    public void ajouteTop(){
        HBox HTop = new HBox();
        saisir.setOnAction(new ControleurSaisirDonnees(this.appli));
        supprimer.setOnAction(new ControleurSupprimerDonnees(appli));
        modifier.setOnAction(new ControleurModifDonnees(appli));
        
        this.etatInitialBoutonSaisir();
        this.etatInitialBoutonModifier();
        this.etatInitialBoutonSupp();
        
        HTop.getChildren().addAll(saisir,supprimer,modifier);

        HBox.setMargin(saisir, new Insets(10)); 
        HBox.setMargin(supprimer, new Insets(10));
        HBox.setMargin(modifier, new Insets(10));
   

        HTop.setBackground(new Background(new BackgroundFill(Color.GREEN, null,null)));

        this.setTop(HTop);
    
    }
    public void etatInitialBoutonSaisir(){
        this.saisir.setStyle("-fx-background-radius: 1em;");
    }
    public void etatInitialBoutonSupp(){
        this.supprimer.setStyle("-fx-background-radius: 1em;");
    }
    public void etatInitialBoutonModifier(){
        this.modifier.setStyle("-fx-background-radius: 1em;");
    }
    // choix de saisie des données
    public void saisirDonnees(){
        HBox hbSaisirDonnees = new HBox();
        Button athlete = new Button("Ajouter un athlète");
        athlete.setOnAction(new ControleurSaisirDonneesAthlete(this.appli));
        Button epreuve = new Button("Ajouter une epreuve");
        hbSaisirDonnees.getChildren().addAll(athlete, epreuve);
        HBox.setMargin(athlete, new Insets(10)); 
        HBox.setMargin(epreuve, new Insets(10));
        hbSaisirDonnees.setAlignment(Pos.CENTER);
        this.setCenter(hbSaisirDonnees);


    }
    public void modifierDonnees(){
        HBox hbModifDonnees = new HBox();
        Button athlete = new Button("Modifier un athlète");
        athlete.setOnAction(new ControleurModifierDonneesAthlete(this.appli));

        Button user = new Button("Modifier un utilisateur");
        hbModifDonnees.getChildren().addAll(athlete, user);

        HBox.setMargin(athlete, new Insets(10)); 
        HBox.setMargin(user, new Insets(10));
        hbModifDonnees.setAlignment(Pos.CENTER);
        this.setCenter(hbModifDonnees);
        this.tableview.setOnMouseClicked(new ControleurModifTable(appli));


    }

    public void modifDonneesAthlete(){
        BorderPane bpSaisieAthlete = new BorderPane(); 
        VBox vbSaisirDonnees = new VBox();

        HBox hnom = new HBox();
        this.tnom = new TextField();
        this.tnom.setPromptText("Nom athlete");
        hnom.getChildren().addAll(tnom);
        HBox.setMargin(tnom, new Insets(10));

        HBox hprenom = new HBox();
        this.tprenom = new TextField();
        this.tprenom.setPromptText("Prenom athlete");
        hprenom.getChildren().addAll(tprenom);
        HBox.setMargin(tprenom, new Insets(10));

        HBox hsexe = new HBox();
        this.tsexe = new TextField();
        this.tsexe.setPromptText("Sexe athlete");
        hsexe.getChildren().addAll(tsexe);
        HBox.setMargin(tsexe, new Insets(10));


        HBox hpays = new HBox();
        this.tpays = new TextField();
        this.tpays.setPromptText("Pays athlete");
        hpays.getChildren().addAll(tpays); 
        HBox.setMargin(tpays, new Insets(10));


        HBox hforce = new HBox();
        this.tforce = new TextField();
        this.tforce.setPromptText("Force athlete");
        hforce.getChildren().addAll(tforce);
        tforce.focusedProperty().addListener(new ControleurFieldNB(tforce));
        HBox.setMargin(tforce, new Insets(10));


        HBox hendurance = new HBox();
        this.tendurance = new TextField();
        this.tendurance.setPromptText("Endurance athlete");
        tendurance.focusedProperty().addListener(new ControleurFieldNB(tendurance));
        hendurance.getChildren().addAll(tendurance);
        HBox.setMargin(tendurance, new Insets(10));


        HBox hagilite = new HBox();
        this.tagilite = new TextField();
        this.tagilite.setPromptText("Agilite athlete");
        tagilite.focusedProperty().addListener(new ControleurFieldNB(tagilite));
        hagilite.getChildren().addAll(tagilite);
        HBox.setMargin(tagilite, new Insets(10));


        HBox hequipe = new HBox();
        this.tequipe = new TextField();
        this.tequipe.setPromptText("Equipe athlete");
        hequipe.getChildren().addAll(tequipe);
        HBox.setMargin(tequipe, new Insets(10));

        Button modifier = new Button("Modifier");
        modifier.setOnAction(new ControleurModifTableBouton(this.appli));

        vbSaisirDonnees.getChildren().addAll(hnom,hprenom,hsexe,hpays,hforce,hendurance,hagilite,hequipe,modifier);
        vbSaisirDonnees.setAlignment(Pos.CENTER);
        
        bpSaisieAthlete.setBottom(vbSaisirDonnees);
        bpSaisieAthlete.setCenter(tableview);
        try {
            if (!(this.appli.getBD().maxNumAthlete()==-1)){
                int indice = 1;
                while (!(indice ==this.appli.getBD().maxNumAthlete())){
                    Athlete a = this.appli.getBD().getAthlete(indice);
                    if (!(a==null)){
                        this.tableview.getItems().add(a);
                    }
                    indice++;
                }
        }
        this.tableview.setEditable(true);
        BorderPane.setMargin(tableview, new Insets(15));
    }
    catch (SQLException e){
        this.popUpBaseDeDonnees().showAndWait();
        System.err.println(e.getMessage());

    }
        bpSaisieAthlete.setCenter(tableview);
        this.setCenter(bpSaisieAthlete);
        
    }


// saisie des données pour un athlete
    public void saisirDonneesAthlete(){
        BorderPane bpSaisieAthlete = new BorderPane(); 
        VBox vbSaisirDonnees = new VBox();

        HBox hnom = new HBox();
        this.tnom = new TextField();
        this.tnom.setPromptText("Nom athlete");
        hnom.getChildren().addAll(tnom);
        HBox.setMargin(tnom, new Insets(10));

        HBox hprenom = new HBox();
        this.tprenom = new TextField();
        this.tprenom.setPromptText("Prenom athlete");
        hprenom.getChildren().addAll(tprenom);
        HBox.setMargin(tprenom, new Insets(10));

        HBox hsexe = new HBox();
        this.tsexe = new TextField();
        this.tsexe.setPromptText("Sexe athlete");
        hsexe.getChildren().addAll(tsexe);
        HBox.setMargin(tsexe, new Insets(10));


        HBox hpays = new HBox();
        this.tpays = new TextField();
        this.tpays.setPromptText("Pays athlete");
        hpays.getChildren().addAll(tpays); 
        HBox.setMargin(tpays, new Insets(10));


        HBox hforce = new HBox();
        this.tforce = new TextField();
        this.tforce.setPromptText("Force athlete");
        hforce.getChildren().addAll(tforce);
        tforce.focusedProperty().addListener(new ControleurFieldNB(tforce));
        HBox.setMargin(tforce, new Insets(10));


        HBox hendurance = new HBox();
        this.tendurance = new TextField();
        this.tendurance.setPromptText("Endurance athlete");
        tendurance.focusedProperty().addListener(new ControleurFieldNB(tendurance));
        hendurance.getChildren().addAll(tendurance);
        HBox.setMargin(tendurance, new Insets(10));


        HBox hagilite = new HBox();
        this.tagilite = new TextField();
        this.tagilite.setPromptText("Agilite athlete");
        tagilite.focusedProperty().addListener(new ControleurFieldNB(tagilite));
        hagilite.getChildren().addAll(tagilite);
        HBox.setMargin(tagilite, new Insets(10));


        HBox hequipe = new HBox();
        this.tequipe = new TextField();
        this.tequipe.setPromptText("Equipe athlete");
        hequipe.getChildren().addAll(tequipe);
        HBox.setMargin(tequipe, new Insets(10));

        Button creer = new Button("Créer");
        creer.setOnAction(new ControleurCreerAthlete(this.appli));

        vbSaisirDonnees.getChildren().addAll(hnom,hprenom,hsexe,hpays,hforce,hendurance,hagilite,hequipe,creer);
        vbSaisirDonnees.setAlignment(Pos.CENTER);
        
        bpSaisieAthlete.setBottom(vbSaisirDonnees);
        bpSaisieAthlete.setCenter(tableview);
        try {
            if (!(this.appli.getBD().maxNumAthlete()==-1)){
                int indice = 1;
                while (!(indice ==this.appli.getBD().maxNumAthlete())){
                    Athlete a = this.appli.getBD().getAthlete(indice);
                    if (!(a==null)){
                        this.tableview.getItems().add(a);
                    }
                    indice++;
                }
        }
        this.tableview.setEditable(true);
        BorderPane.setMargin(tableview, new Insets(15));
    }
    catch (SQLException e){
        this.popUpBaseDeDonnees().showAndWait();
        System.err.println(e.getMessage());

    }
        bpSaisieAthlete.setCenter(tableview);
        this.setCenter(bpSaisieAthlete);

    }

    public void supprimerDonnees(){
        try {
            if (!(this.appli.getBD().maxNumAthlete()==-1)){
                int indice = 1;
                while (!(indice ==this.appli.getBD().maxNumAthlete())){
                    Athlete a = this.appli.getBD().getAthlete(indice);
                    if (!(a==null)){
                        this.tableview.getItems().add(a);
                    }
                    indice++;
                }
        }
        this.tableview.setEditable(true);
        this.tableview.setOnMouseClicked(new ControleurSuppTable(appli));
    }
    catch (SQLException e){
        this.popUpBaseDeDonnees().showAndWait();
        System.err.println(e.getMessage());

    }
    this.setCenter(tableview);

    }
    public Alert popUpModifier(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Voulez-vous modifier l'ahtlète ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        alert.setHeaderText("Modificiation");
        return alert;

    }
    
    public Alert popUpBaseDeDonnees(){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Une erreur avec la base de données est survenue", ButtonType.OK);
        alert.setTitle("Attention");
        alert.setHeaderText("Erreur affichage tableau");
        return alert;

    }
    public Alert popUpNombreException(){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Veuillez entrer un nombre", ButtonType.OK);
        alert.setTitle("Attention");
        alert.setHeaderText("Erreur");
        return alert;}

    public Alert popUpSuppression(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Voulez-vous supprimer cette ligne ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        alert.setHeaderText("Suppression");
        return alert;
    }

    public Alert popUpInsertionReussie(){
        Alert alert =  new Alert(Alert.AlertType.INFORMATION,"Votre insertion est prise en compte", ButtonType.OK);
        alert.setTitle("Insérée");
        alert.setHeaderText("Pris en compte");
        return alert;
    }
    public Alert popUpExisteDeja(){
        Alert alert =  new Alert(Alert.AlertType.CONFIRMATION,"Votre insertion existe déjà, \nvoulez-vous la remplacer?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Existe déjà");
        alert.setHeaderText("Remplacer");
        return alert;
    }
    public Alert popUpNonChangé(){
        Alert alert =  new Alert(Alert.AlertType.INFORMATION,"Votre insertion n'a pas été prise en compte", ButtonType.OK);
        alert.setTitle("Insertion");
        alert.setHeaderText("Non prise en compte");
        return alert;
    }
    /*
    public TableView<Athlete> getTableView(){
        return this.tableview;
    }*/

    public void ajouteBottom(){
        this.setBottom(this.connexion);
        BorderPane.setAlignment(connexion, Pos.CENTER);
    }

// getters et setters
    public Button getConnexion() {
        return connexion;
    }
    public TableView<Athlete> getTableView(){
        return this.tableview;
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
