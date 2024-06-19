import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreOrganisateur extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Top buttons
        HBox topButtons = new HBox(10);
        topButtons.setPadding(new Insets(20));
        topButtons.setStyle("-fx-background-color: #C4002F;");
        
        Button launchButton = new Button("Lancer l'épreuve");
        launchButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        
        topButtons.getChildren().addAll(launchButton);

        // Center content
        VBox centerContent = new VBox(20);
        centerContent.setAlignment(javafx.geometry.Pos.CENTER);
        centerContent.setPadding(new Insets(20));
        centerContent.setStyle("-fx-background-color: #FFD1D1;");
        
        Label titleLabel = new Label("LES ÉPREUVES");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(25);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(75);
        grid.getColumnConstraints().addAll(col1, col2);
        
        // Row 1
        Label natationLabel = new Label("NATATION");
        natationLabel.setStyle("-fx-font-weight: bold;");
        ComboBox<String> natationComboBox = new ComboBox<>(FXCollections.observableArrayList("Catégorie","100m brasse H", "100m brasse F"));
        grid.add(natationLabel, 0, 0);
        grid.add(natationComboBox, 1, 0);
        
        // Row 2
        Label athletismeLabel = new Label("ATHLÉTISME");
        athletismeLabel.setStyle("-fx-font-weight: bold;");
        ComboBox<String> athletismeComboBox = new ComboBox<>(FXCollections.observableArrayList("Catégorie","110m haies F", "110m haies H"));
        grid.add(athletismeLabel, 0, 1);
        grid.add(athletismeComboBox, 1, 1);
        
        // Row 3
        Label handballLabel = new Label("HANDBALL");
        handballLabel.setStyle("-fx-font-weight: bold;");
        ComboBox<String> handballComboBox = new ComboBox<>(FXCollections.observableArrayList("Catégorie","Hommes", "Femmes"));
        grid.add(handballLabel, 0, 2);
        grid.add(handballComboBox, 1, 2);
        
        // Row 4
        Label volleyballLabel = new Label("VOLLEY-BALL");
        volleyballLabel.setStyle("-fx-font-weight: bold;");
        ComboBox<String> volleyballComboBox = new ComboBox<>(FXCollections.observableArrayList("Catégorie","Femmes", "Hommes"));
        grid.add(volleyballLabel, 0, 3);
        grid.add(volleyballComboBox, 1, 3);
        
        // Row 5
        Label escrimeLabel = new Label("ESCRIME");
        escrimeLabel.setStyle("-fx-font-weight: bold;");
        ComboBox<String> escrimeComboBox = new ComboBox<>(FXCollections.observableArrayList("Catégorie","Fleuret F", "Fleuret H"));
        grid.add(escrimeLabel, 0, 4);
        grid.add(escrimeComboBox, 1, 4);

        centerContent.getChildren().addAll(titleLabel, grid);

        // Right image
        


        // Main layout
        BorderPane root = new BorderPane();
        root.setTop(topButtons);
        root.setCenter(centerContent);

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Jeux IUT'Olympiques");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
