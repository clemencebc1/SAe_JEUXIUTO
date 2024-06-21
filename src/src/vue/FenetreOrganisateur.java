package vue;

import vue.*;
import bd.AthleteBD;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sport.Athletisme;
import sport.Epreuve;
import sport.Escrime;
import sport.Handball;
import sport.Natation;
import sport.Sport;
import sport.VolleyBall;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FenetreOrganisateur extends BorderPane {
    private Button lancerE;
    private FenetreAccueil appli;
    private Button deco;
    private ToggleGroup group;
    private ComboBox<String> cmbNatation;
    private ComboBox<String> cmbAthletisme;
    private ComboBox<String> cmbEscrime;
    private ComboBox<String> cmbHandball;
    private ComboBox<String> cmbVolleyball;
    private static int numEpreuve = 0;
    private static Integer idSport;
    private static String milieu;
    public final static Sport natation =new Natation("Natation", "eau") ;
    public final static Sport athletisme =new Athletisme("Athlétisme", "piste") ;
    public final static Sport escrime =new Escrime("Escrime", "salle") ;
    public final static Sport handball =new Handball("Handball", "salle") ;
    public final static Sport volleyball =new VolleyBall("Volley-Ball", "salle") ;





    public FenetreOrganisateur(Button btn, FenetreAccueil appli) {
        super();
        this.lancerE = new Button("C'est parti !");
        this.deco =btn;
        this.appli = appli;
        this.group = new ToggleGroup();
        this.ajouteTop();
        this.ajouteImage();
        this.ajouteBottom();
        this.ajouteCentre();
    }

    /** ajout de l'image */
    public void ajouteImage() {
        ImageView imageJO = new ImageView(new Image("file:./imgSae/LogoJO.jpeg", 150, 100, false, false));
        this.setRight(imageJO);
    }

    public void ajouteTop() {
        Image coche = new Image("file:./imgSae/coche.png", 30, 30, false, false);
        ImageView imageView = new ImageView(coche);
        HBox hbChoix = new HBox();
        Label lancer = new Label("Lancer Épreuve");
    

        lancer.setStyle("-fx-background-radius: 1em;");
        hbChoix.getChildren().addAll(lancer);
        HBox.setMargin(lancer, new Insets(10));
        hbChoix.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, null, null)));
        hbChoix.setAlignment(Pos.CENTER);
        this.setTop(hbChoix);
    }

    public void ajouteCentre() {
        RadioButton button1 = new RadioButton();
        button1.setToggleGroup(group);
        button1.setText("Natation");
        button1.setSelected(true);

        RadioButton button2 = new RadioButton();
        button2.setToggleGroup(group);
        button2.setText("Athlétisme");


        RadioButton button3 = new RadioButton();
        button3.setToggleGroup(group);
        button3.setText("Escrime");


        RadioButton button4 = new RadioButton();
        button4.setToggleGroup(group);
        button4.setText("Handball");


        RadioButton button5 = new RadioButton();
        button5.setToggleGroup(group);
        button5.setText("Volleyball");

        VBox vbCentre = new VBox();
        vbCentre.setBackground(new Background(new BackgroundFill(Color.rgb(255, 230, 230), null, null)));
        VBox hbTitre = new VBox();
        hbTitre.setBackground(new Background(new BackgroundFill(Color.rgb(255, 230, 230), null, null)));
        hbTitre.getChildren().addAll(new Text("LES ÉPREUVES"), new Text("Sélectionnez le sport et son épreuve"));
        hbTitre.setAlignment(Pos.CENTER);
        hbTitre.setPadding(new Insets(10));
        vbCentre.getChildren().addAll(hbTitre);
        this.setCenter(vbCentre);

        cmbNatation = new ComboBox<>();
        cmbNatation.getItems().addAll("Natation 100 brasse M", "Natation 100 brasse F","Natation relais libre F","Natation relais libre M");
        cmbNatation.setValue("Catégorie");
        cmbNatation.setValue(cmbNatation.getItems().get(0));
        cmbNatation.setPrefWidth(150);

        cmbAthletisme = new ComboBox<>();
        cmbAthletisme.getItems().addAll("Athlétisme 110m F", "Athlétisme 110m M","Athlétisme 4x100m relais F","Athlétisme 4x100m relais M");
        cmbAthletisme.setValue("Catégorie");
        cmbAthletisme.setValue(cmbAthletisme.getItems().get(0));
        
        cmbEscrime = new ComboBox<>();
        cmbEscrime.getItems().addAll("Escrime fleuret F", "Escrime fleuret M","Escrime épée M" , "Escrime épée F");
        cmbEscrime.setValue("Catégorie");
        cmbEscrime.setValue(cmbEscrime.getItems().get(0));
        cmbEscrime.setPrefWidth(150);

        cmbHandball = new ComboBox<>();
        cmbHandball.getItems().addAll("Handball F", "Handball M");
        cmbHandball.setValue("Catégorie");
        cmbHandball.setValue(cmbHandball.getItems().get(0));
        cmbHandball.setPrefWidth(150);


        cmbVolleyball = new ComboBox<>();
        cmbVolleyball.getItems().addAll("Volley-ball F", "Volley-ball M");
        cmbVolleyball.setValue("Catégorie");
        cmbVolleyball.setValue(cmbVolleyball.getItems().get(0));
        cmbVolleyball.setPrefWidth(150);

        GridPane grid = new GridPane();
        this.setBackground(new Background(new BackgroundFill(Color.rgb(255, 230, 230), null, null)));
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: LAVENDERBLUSH; -fx-background-radius: 6em;");
        grid.add(button1, 0, 0);
        grid.add(button2, 0, 1);
        grid.add(button3, 0, 2);
        grid.add(button4, 0, 3);
        grid.add(button5, 0, 4);

        vbCentre.getChildren().addAll(grid, new Text("Pour lancer l'épreuve, cliquez sur le bouton ci-dessous"),this.lancerE);
        vbCentre.setAlignment(Pos.CENTER);
        VBox.setMargin(this.lancerE, new Insets(25));
        grid.add(cmbNatation, 2, 0);
        grid.add(cmbAthletisme, 2, 1);
        grid.add(cmbEscrime, 2, 2);
        grid.add(cmbHandball, 2, 3);
        grid.add(cmbVolleyball, 2, 4);
        this.lancerE.setOnAction(new ControleurLancer(appli));
    }
    public Alert popUpBaseDeDonnees(){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Une erreur avec la base de données est survenue", ButtonType.OK);
        alert.setTitle("Attention");
        alert.setHeaderText("1.1 Erreur affichage tableau");
        return alert;

    }
    public Alert popUpPasDeParticipant(){
        Alert alert = new Alert(Alert.AlertType.WARNING,"Il n'y a pas de participants à cette épreuve", ButtonType.OK);
        alert.setTitle("Attention");
        alert.setHeaderText("1.2 Pas de participants");
        return alert;

    }

    public Alert popUpEpreuveLancee(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"L'epreuve a été lancé et enregistré", ButtonType.OK);
        alert.setTitle("Epreuve lancée");
        return alert;
    }

    public void ajouteBottom() {
        this.setBottom(this.deco);
        BorderPane.setAlignment(deco, Pos.CENTER);
        BorderPane.setMargin(deco, new Insets(10));
    }

        //getters
    public ToggleGroup getGroup(){
        return this.group;
    }


    public FenetreAccueil getAppli() {
        return appli;
    }



    public ComboBox<String> getCmbNatation() {
        return cmbNatation;
    }

    public ComboBox<String> getCmbAthletisme() {
        return cmbAthletisme;
    }

    public ComboBox<String> getCmbEscrime() {
        return cmbEscrime;
    }

    public ComboBox<String> getCmbHandball() {
        return cmbHandball;
    }

    public ComboBox<String> getCmbVolleyball() {
        return cmbVolleyball;
    }

    public static int getNumEpreuve() {
        return numEpreuve;
    }

    public static Integer getIdSport() {
        return idSport;
    }

    public static String getMilieu() {
        return milieu;
    }
    

}

