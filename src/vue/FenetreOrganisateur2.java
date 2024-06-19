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
import javafx.scene.control.Button;
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
import sport.Epreuve;
import sport.Sport;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FenetreOrganisateur2 extends BorderPane {
    private Button lancerE;
    private FenetreAccueil appli;
    private ToggleGroup group;
    private ComboBox<String> cmbNatation;
    private ComboBox<String> cmbAthletisme;
    private ComboBox<String> cmbEscrime;
    private ComboBox<String> cmbHandball;
    private ComboBox<String> cmbVolleyball;
    private static int numEpreuve = 0;
    private static Integer idSport;
    private static String milieu;

    public FenetreOrganisateur2(Button btn, FenetreAccueil appli) {
        super();
        this.lancerE = btn;
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
        Button lancer = new Button("Lancer Épreuve", imageView);

        lancer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lancerEpreuve();
            }
        });

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
        button1.setUserData("Natation");
        button1.setSelected(true);

        RadioButton button2 = new RadioButton();
        button2.setToggleGroup(group);
        button2.setUserData("Athlétisme");

        RadioButton button3 = new RadioButton();
        button3.setToggleGroup(group);
        button3.setUserData("Escrime");

        RadioButton button4 = new RadioButton();
        button4.setToggleGroup(group);
        button4.setUserData("Handball");

        RadioButton button5 = new RadioButton();
        button5.setToggleGroup(group);
        button5.setUserData("Volleyball");

        VBox vbCentre = new VBox();
        vbCentre.setBackground(new Background(new BackgroundFill(Color.rgb(255, 230, 230), null, null)));
        HBox hbTitre = new HBox();
        hbTitre.setBackground(new Background(new BackgroundFill(Color.rgb(255, 230, 230), null, null)));
        hbTitre.getChildren().add(new Text("LES ÉPREUVES"));
        hbTitre.setAlignment(Pos.CENTER);
        hbTitre.setPadding(new Insets(10));
        vbCentre.getChildren().addAll(hbTitre);
        this.setCenter(vbCentre);

        cmbNatation = new ComboBox<>();
        cmbNatation.getItems().addAll("100m brasse H", "100m brasse F","4x100m nage libre F","4x100m nage libre H");
        cmbNatation.setValue("Catégorie");
        cmbNatation.setPrefWidth(150);

        cmbAthletisme = new ComboBox<>();
        cmbAthletisme.getItems().addAll("110m haies F", "110m haies H","4x100m relais F","4x100m relais H");
        cmbAthletisme.setValue("Catégorie");
        
        cmbEscrime = new ComboBox<>();
        cmbEscrime.getItems().addAll("Fleuret F", "Fleuret H");
        cmbEscrime.setValue("Catégorie");
        cmbEscrime.setPrefWidth(150);

        cmbHandball = new ComboBox<>();
        cmbHandball.getItems().addAll("Femmes", "Hommes");
        cmbHandball.setValue("Catégorie");
        cmbHandball.setPrefWidth(150);


        cmbVolleyball = new ComboBox<>();
        cmbVolleyball.getItems().addAll("Femmes", "Hommes");
        cmbVolleyball.setValue("Catégorie");
        cmbVolleyball.setPrefWidth(150);

        GridPane grid = new GridPane();
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(255, 230, 230), null, null)));
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
        grid.add(button1, 0, 0);
        grid.add(button2, 0, 1);
        grid.add(button3, 0, 2);
        grid.add(button4, 0, 3);
        grid.add(button5, 0, 4);
        grid.add(new Label("NATATION"), 1, 0);
        grid.add(new Label("ATHLÉTISME"), 1, 1);
        grid.add(new Label("ESCRIME"), 1, 2);
        grid.add(new Label("HANDBALL"), 1, 3);
        grid.add(new Label("VOLLEYBALL"), 1, 4);

        vbCentre.getChildren().addAll(grid);
        grid.add(cmbNatation, 2, 0);
        grid.add(cmbAthletisme, 2, 1);
        grid.add(cmbEscrime, 2, 2);
        grid.add(cmbHandball, 2, 3);
        grid.add(cmbVolleyball, 2, 4);
    }

    private void lancerEpreuve() {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String selectedSport = selectedRadioButton.getUserData().toString();
        String selectedCategory = "";

        switch (selectedSport) {
            case "Natation":
            if (cmbNatation.getValue() == "Catégorie") {
                System.out.println("Veuillez sélectionner une catégorie");
                return;
            }
                selectedCategory = cmbNatation.getValue();
                milieu = "eau";
                idSport = 1;
                break;
            case "Athlétisme":
            if (cmbAthletisme.getValue() == "Catégorie") {
                System.out.println("Veuillez sélectionner une catégorie");
                return;
            }
                selectedCategory = cmbAthletisme.getValue();
                milieu = "piste";
                idSport = 2;
                break;

            case "Escrime":
            if (cmbEscrime.getValue() == "Catégorie") {
                System.out.println("Veuillez sélectionner une catégorie");
                return;
            }
                selectedCategory = cmbEscrime.getValue();
                milieu = "salle";
                idSport = 3;
                break;
            case "Handball":
            if (cmbHandball.getValue() == "Catégorie") {
                System.out.println("Veuillez sélectionner une catégorie");
                return;
            }
                selectedCategory = cmbHandball.getValue();
                milieu = "salle";
                idSport = 5;
                break;
            case "Volleyball":
            if (cmbVolleyball.getValue() == "Catégorie") {
                System.out.println("Veuillez sélectionner une catégorie");
                milieu = "salle";
                idSport = 4;
                return;
            }
                selectedCategory = cmbVolleyball.getValue();
                char lettre = selectedCategory.charAt(selectedCategory.length()-1);
                break;
        }
        
    }

    public void ajouteBottom() {
        this.setBottom(this.lancerE);
        BorderPane.setAlignment(lancerE, Pos.CENTER);
        BorderPane.setMargin(lancerE, new Insets(10));
    }
}

