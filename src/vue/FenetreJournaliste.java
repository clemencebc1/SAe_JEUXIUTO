package vue;
import vue.*;
import participant.*;

import java.util.Map;

import sport.*;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
// import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;


// javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d bin/ src/vue/*.java
// java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -cp bin vue.FenetreAccueil


public class FenetreJournaliste extends BorderPane{
    private Button connexion;
    private FenetreAccueil appli;
    private ComboBox<String> comboboxClassement;
    private Articles articles;
    private TableView<Pays> tableview;
    private Button classement;
    private Button natation;
    private Button escrime;
    private Button athletisme;
    private Button handball;
    private Button volleyball;
    private Button info;

    

    public FenetreJournaliste(Button btn,FenetreAccueil appli){
        super();
        this.connexion = btn;
        ImageView infoImage = new ImageView(new Image("file:./img/info.png",15,15,false,false));
        this.info = new Button("infos",infoImage);
        this.info.setOnAction(new ControleurInfoPage(appli));
        this.appli = appli;
        this.comboboxClassement = new ComboBox<>();
        this.classement =  new Button("Classement");
        this.natation = new Button("Natation");
        this.escrime = new Button("Escrime");
        this.athletisme = new Button("Athlétisme"); 
        this.handball= new Button("Handball"); 
        this.volleyball = new Button("Volley-Ball");
        this.tableview = new TableView<>();
        TableColumn<Pays, String> pays = new TableColumn<>("Pays"); // initialise le tableau
            TableColumn<Pays, String> medailleOr = new TableColumn<>("Medaille Or");
            TableColumn<Pays, String> medailleArgent = new TableColumn<>("Medaille Argent");
            TableColumn<Pays, String> medailleBronze = new TableColumn<>("Medaille Bronze");
            pays.setCellValueFactory(new PropertyValueFactory<>("nom"));
            medailleOr.setCellValueFactory(new PropertyValueFactory<>("nbOr"));
            medailleArgent.setCellValueFactory(new PropertyValueFactory<>("nbArgent"));
            medailleBronze.setCellValueFactory(new PropertyValueFactory<>("nbBronze"));
            tableview.getColumns().add(pays);
            tableview.getColumns().add(medailleOr);
            tableview.getColumns().add(medailleArgent);
            tableview.getColumns().add(medailleBronze);
            
        this.articles = new Articles(appli);
        this.articles.creeArticleSport();

        this.ajouteTop();
        this.ajouteImage();
        this.ajouteBottom();
        this.ajouteCenter();
    }

    /** ajout de l'image
     */
    public void ajouteImage(){
        ImageView imageJO = new ImageView(new Image("file:./img/LogoJO.jpeg",150,100,false,false));
        this.setRight(imageJO);

    }
    public void ajouteCenter(){
        this.setCenter(info);
        BorderPane.setAlignment(info, Pos.TOP_CENTER);
        BorderPane.setMargin(info, new Insets(15));
    }

/*ajoute les choix de pages*/
    public void ajouteTop(){
        HBox hbChoix = new HBox();
      
        classement.setOnAction(new ControleurClassement(this.appli));
        natation.setOnAction(new ControleurClassement(this.appli));
        escrime.setOnAction(new ControleurClassement(this.appli));
        athletisme.setOnAction(new ControleurClassement(this.appli));
        handball.setOnAction(new ControleurClassement(this.appli));
        volleyball.setOnAction(new ControleurClassement(this.appli));
        

        classement.setStyle("-fx-background-radius: 1em;");
        natation.setStyle("-fx-background-radius: 1em;");
        escrime.setStyle("-fx-background-radius: 1em;");
        athletisme.setStyle("-fx-background-radius: 1em;");
        handball.setStyle("-fx-background-radius: 1em;");
        volleyball.setStyle("-fx-background-radius: 1em;");

        hbChoix.getChildren().addAll(classement,natation,escrime,athletisme,handball,volleyball);
        HBox.setMargin(natation, new Insets(10)); 
        HBox.setMargin(classement, new Insets(10));
        HBox.setMargin(escrime, new Insets(10));
        HBox.setMargin(athletisme, new Insets(10));
        HBox.setMargin(handball, new Insets(10));
        HBox.setMargin(volleyball, new Insets(10));
        hbChoix.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, null,null)));
        hbChoix.setAlignment(Pos.CENTER);

        this.setTop(hbChoix);
    
    }



    /*creer un tableau avec le classement*/
    public void classement(){
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        this.comboboxClassement.getItems().addAll("Medaille Or", "Medaille Argent", "Medaille Bronze");
        VBox vbClassement = new VBox();
        this.comboboxClassement.setOnAction(new ControleurChoixClassement(appli));
        comboboxClassement.getSelectionModel().selectFirst();

        try { List<Pays> listeClassement = this.appli.getBD().classement();
            for (Pays p : listeClassement){
                tableview.getItems().add(p);
            }

            vbClassement.getChildren().addAll(comboboxClassement,tableview);
            vbClassement.setAlignment(Pos.CENTER);
            VBox.setMargin(tableview, new Insets(10));
            VBox.setMargin(comboboxClassement, new Insets(10));
            this.setCenter(vbClassement);
        }
        catch (SQLException e){
            this.popUpErreurClassement(e).showAndWait();

        }

    }

    public void natation(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Natation 100 brasse M", "Natation 100 brasse F");
        comboboxClassement.getSelectionModel().selectFirst();

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            tableview2.getColumns().add(score);
            try { 
                List<Map<Athlete, Double>> points = this.appli.getBD().avoirEpreuveParNom("Natation 100 brasse", "M", FenetreOrganisateur.natation).classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }

                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);

    }
    public void natation2(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Natation 100 brasse M", "Natation 100 brasse F");
        this.comboboxClassement.setValue("Natation 100 brasse F");

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            tableview2.getColumns().add(score);
            try { 
                List<Map<Athlete, Double>> points = this.appli.getBD().avoirEpreuveParNom("Natation 100 brasse", "F", FenetreOrganisateur.natation).classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }

                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);

    }

    public void volleyball(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Volley-ball F", "Volley-ball M");
        comboboxClassement.getSelectionModel().selectFirst();


        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Volley-ball", "F", FenetreOrganisateur.volleyball);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }

                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);

    }

    public void volleyball2(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Volley-ball F", "Volley-ball M");
        this.comboboxClassement.setValue("Volley-Ball M");

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Volley-ball", "M", FenetreOrganisateur.volleyball);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }

                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);

    }
    public void handball(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.setOnAction(null);
        comboboxClassement.getItems().addAll("Handball F", "Handball M");
        comboboxClassement.getSelectionModel().selectFirst();

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Handball", "F", FenetreOrganisateur.handball);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }

                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);

    }
    public void handball2(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Handball F", "Handball M");
        this.comboboxClassement.setValue(this.comboboxClassement.getItems().get(1));

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Handball", "M", FenetreOrganisateur.handball);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }

                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);

    }

    public void ahtletisme(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Athlétisme 110m F", "Athlétisme 110m M","Athlétisme 4x100m relais F","Athlétisme 4x100m relais M");
        comboboxClassement.getSelectionModel().selectFirst();

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Athlétisme 110m", "F", FenetreOrganisateur.athletisme);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }

                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);

    }

    public void ahtletisme2(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Athlétisme 110m F", "Athlétisme 110m M","Athlétisme 4x100m relais F","Athlétisme 4x100m relais M");
        this.comboboxClassement.setValue(this.comboboxClassement.getItems().get(1));

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Athlétisme 110m", "M", FenetreOrganisateur.athletisme);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }

                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);

    }
    public void ahtletisme3(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Athlétisme 110m F", "Athlétisme 110m M","Athlétisme 4x100m relais F","Athlétisme 4x100m relais M");
        this.comboboxClassement.setValue(this.comboboxClassement.getItems().get(2));

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Athlétisme 4x100m relais", "F", FenetreOrganisateur.athletisme);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }

                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);

    }
    public void ahtletisme4(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Athlétisme 110m F", "Athlétisme 110m M","Athlétisme 4x100m relais F","Athlétisme 4x100m relais M");
        this.comboboxClassement.setValue(this.comboboxClassement.getItems().get(3));

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Athlétisme 4x100m relais", "M", FenetreOrganisateur.athletisme);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }

                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);

    }
    public void escrime(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Escrime fleuret F", "Escrime fleuret M","Escrime épée M" , "Escrime épée F");
        comboboxClassement.getSelectionModel().selectFirst();

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Escrime fleuret", "F", FenetreOrganisateur.escrime);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }
                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);
    }
    public void escrime1(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Escrime fleuret F", "Escrime fleuret M","Escrime épée M" , "Escrime épée F");
        this.comboboxClassement.setValue(this.comboboxClassement.getItems().get(1));

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Escrime fleuret", "M", FenetreOrganisateur.escrime);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }
                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);
    }
    public void escrime2(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Escrime fleuret F", "Escrime fleuret M","Escrime épée M" , "Escrime épée F");
        this.comboboxClassement.setValue(this.comboboxClassement.getItems().get(2));

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Escrime épée", "M", FenetreOrganisateur.escrime);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }
                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);
    }

    public void escrime3(){
        VBox vbEpreuve = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        comboboxClassement.getItems().addAll("Escrime fleuret F", "Escrime fleuret M","Escrime épée M" , "Escrime épée F");
        this.comboboxClassement.setValue(this.comboboxClassement.getItems().get(3));

        vbEpreuve.getChildren().addAll(comboboxClassement);

        TableView<Athlete> tableview2 = new TableView<>();
        TableColumn<Athlete, String> nom = new TableColumn<>("Nom"); // initialise le tableau
            TableColumn<Athlete, String> prenom = new TableColumn<>("Prenom");
            TableColumn<Athlete, String> equipe = new TableColumn<>("Equipe");
            TableColumn<Athlete, Double> score = new TableColumn<>("score");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            score.setCellValueFactory(new PropertyValueFactory<>("score"));
            tableview2.getColumns().add(nom);
            tableview2.getColumns().add(prenom);
            tableview2.getColumns().add(equipe);
            try { 
                Epreuve e = this.appli.getBD().avoirEpreuveParNom("Escrime épée", "F", FenetreOrganisateur.escrime);
                for (Athlete at : this.appli.getBD().athleteEpreuve(e)){
                    e.ajoutParticipants(at);
                }
                List<Map<Athlete, Double>> points = e.classementPoints();
                for (Map<Athlete, Double> map : points){
                    for (Athlete a : map.keySet()){
                        tableview2.getItems().add(a);
                    }
                }
                vbEpreuve.getChildren().add(tableview2);
            }
            catch (SQLException e){
                this.popUpErreurClassement(e).showAndWait();
            }
        this.setCenter(vbEpreuve);
    }
    
    // arrange le tableau selon medaille argent
    public void trieMedailleArgent(){
        VBox vbClassement = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        this.comboboxClassement.getItems().addAll("Medaille Or", "Medaille Argent", "Medaille Bronze");
        this.comboboxClassement.setValue(this.comboboxClassement.getItems().get(1));
        this.tableview.getItems().clear();
        Text info = new Text("Vous trouverez le classement des pays en fonction de leurs médailles (or, argent, bronze)");
        try {
            List<Pays> listeClassement = this.appli.getBD().classementArgent();
            for (Pays p : listeClassement){
                tableview.getItems().add(p);
            }
            vbClassement.getChildren().addAll(info,comboboxClassement,tableview);
            vbClassement.setAlignment(Pos.CENTER);
            VBox.setMargin(tableview, new Insets(10));
            VBox.setMargin(comboboxClassement, new Insets(10));
            this.setCenter(vbClassement);

        }
        catch (SQLException e){
            this.popUpErreurClassement(e).showAndWait();
        }

    }
    // arrange tableau selon medaille bronze
    public void trieMedailleBronze(){
        VBox vbClassement = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        this.comboboxClassement.getItems().addAll("Medaille Or", "Medaille Argent", "Medaille Bronze");
        this.comboboxClassement.setValue(this.comboboxClassement.getItems().get(2));
        this.tableview.getItems().clear();
        Text info = new Text("Vous trouverez le classement des pays en fonction de leurs médailles (or, argent, bronze)");
        try {
            List<Pays> listeClassement = this.appli.getBD().classementBronze();
            for (Pays p : listeClassement){
                tableview.getItems().add(p);
            }
            vbClassement.getChildren().addAll(info,comboboxClassement,tableview);
            vbClassement.setAlignment(Pos.CENTER);
            VBox.setMargin(tableview, new Insets(10));
            VBox.setMargin(comboboxClassement, new Insets(10));
            this.setCenter(vbClassement);

        }
        catch (SQLException e){
            this.popUpErreurClassement(e).showAndWait();
        }

    }
    public void trieMedailleOr(){
        VBox vbClassement = new VBox();
        comboboxClassement.getItems().removeAll(comboboxClassement.getItems());
        this.comboboxClassement.getItems().addAll("Medaille Or", "Medaille Argent", "Medaille Bronze");
        this.comboboxClassement.setValue(this.comboboxClassement.getItems().get(0));
        this.tableview.getItems().clear();
        Text info = new Text("Vous trouverez le classement des pays en fonction de leurs médailles (or, argent, bronze)");
        try {
            List<Pays> listeClassement = this.appli.getBD().classement();
            for (Pays p : listeClassement){
                tableview.getItems().add(p);
            }
            vbClassement.getChildren().addAll(info,comboboxClassement,tableview);
            vbClassement.setAlignment(Pos.CENTER);
            VBox.setMargin(tableview, new Insets(10));
            VBox.setMargin(comboboxClassement, new Insets(10));
            this.setCenter(vbClassement);

        }
        catch (SQLException e){
            this.popUpErreurClassement(e).showAndWait();
        }

    }


    /*ajout d'articles en lien avec les actualites JO*/
    public void ajoutArticles(){
        VBox vb = new VBox();
        vb.getChildren().addAll(this.articles.get(0), this.articles.get(1));
        this.setCenter(vb);
        VBox.setMargin(this.articles.get(0), new Insets(15));
    }
    
    public Alert popUpErreurClassement(SQLException e){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Erreur dans la base de données\n"+e.getMessage(), ButtonType.OK);
        alert.setTitle("Attention");
        alert.setHeaderText("1.1 Erreur base de données");
        return alert;
    }


    public void ajouteBottom(){
        this.setBottom(this.connexion);
        BorderPane.setAlignment(connexion, Pos.CENTER);
        BorderPane.setMargin(connexion, new Insets(10));
    }
    public void initialiseBoutonClassement(){
        this.classement.setStyle("-fx-background-radius: 1em;");
    }
    public void initialiseBoutonNatation(){
        this.natation.setStyle("-fx-background-radius: 1em;");
    }
    public void initialiseBoutonEscrime(){
        this.escrime.setStyle("-fx-background-radius: 1em;");
    }
    public void initialiseBoutonAthletisme(){
        this.athletisme.setStyle("-fx-background-radius: 1em;");
    }
    public void initialiseBoutonHandball(){
        this.handball.setStyle("-fx-background-radius: 1em;");
    }
    public void initialiseBoutonVolleyball(){
        this.volleyball.setStyle("-fx-background-radius: 1em;");
    }

    public ComboBox<String> getComboboxClassement() {
        return comboboxClassement;
    }

    public Articles getArticles() {
        return articles;
    }

    public TableView<Pays> getTableview() {
        return tableview;
    }

    public Button getClassement() {
        return classement;
    }

    public Button getNatation() {
        return natation;
    }

    public Button getEscrime() {
        return escrime;
    }

    public Button getAthletisme() {
        return athletisme;
    }

    public Button getHandball() {
        return handball;
    }

    public Button getVolleyball() {
        return volleyball;
    }
    

}
