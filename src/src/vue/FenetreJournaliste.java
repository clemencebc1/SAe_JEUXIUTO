package vue;
import vue.*;
import participant.*;

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

    

    public FenetreJournaliste(Button btn,FenetreAccueil appli){
        super();
        this.connexion = btn;
        this.appli = appli;
        this.comboboxClassement = new ComboBox<>();
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
    }

    /** ajout de l'image
     */
    public void ajouteImage(){
        ImageView imageJO = new ImageView(new Image("file:./img/LogoJO.jpeg",150,100,false,false));
        this.setRight(imageJO);

    }

/*ajoute les choix de pages*/
    public void ajouteTop(){
        HBox hbChoix = new HBox();
        Button classement = new Button("Classement");
        Button natation = new Button("Natation");
        Button escrime = new Button("Escrime");
        Button athletisme = new Button("Athlétisme");
        Button handball = new Button("Handball");
        Button volleyball = new Button("Volley-Ball");
        classement.setOnAction(new ControleurClassement(this.appli));

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
    /*ajoute les choix de recherche pour le classement*/
    public void recherche(){
        HBox hbRecherche = new HBox();
        TextField tfRecherche = new TextField();
        Button boutonRecherche = new Button("Rechercher");
        hbRecherche.getChildren().addAll(tfRecherche, boutonRecherche);
        this.setCenter(hbRecherche);
        HBox.setMargin(tfRecherche, new Insets(10));
        HBox.setMargin(boutonRecherche, new Insets(10));
        hbRecherche.setAlignment(Pos.TOP_CENTER);
        tfRecherche.setPromptText("Pays, athlète...");

    }
    /*creer un tableau avec le classement*/
    public void classement(){
        this.comboboxClassement.getItems().addAll("Medaille Or", "Medaille Argent", "Medaille Bronze");
        VBox vbClassement = new VBox();
        this.comboboxClassement.setOnAction(new ControleurChoixClassement(appli));;
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

    // arrange le tableau selon medaille argent
    public void trieMedailleArgent(){
        VBox vbClassement = new VBox();
        this.tableview.getItems().clear();
        try {
            List<Pays> listeClassement = this.appli.getBD().classementArgent();
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
    // arrange tableau selon medaille bronze
    public void trieMedailleBronze(){
        VBox vbClassement = new VBox();
        this.tableview.getItems().clear();
        try {
            List<Pays> listeClassement = this.appli.getBD().classementBronze();
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
        return alert;
    }


    public void ajouteBottom(){
        this.setBottom(this.connexion);
        BorderPane.setAlignment(connexion, Pos.CENTER);
        BorderPane.setMargin(connexion, new Insets(10));
    }

}
