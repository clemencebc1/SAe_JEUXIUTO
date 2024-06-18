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


public class FenetreJournaliste extends BorderPane{
    private Button connexion;
    private FenetreAccueil appli;
    

    public FenetreJournaliste(Button btn,FenetreAccueil appli){
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
        ImageView imageJO = new ImageView(new Image("file:./img/LogoJO.jpeg",150,100,false,false));
        this.setRight(imageJO);
    }


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
    public void recherche(){
        HBox hbRecherche = new HBox();
        TextField tfRecherche = new TextField();
        Button boutonRecherche = new Button("Rechercher");
        hbRecherche.getChildren().addAll(tfRecherche, boutonRecherche);
        this.setCenter(hbRecherche);
        HBox.setMargin(tfRecherche, new Insets(10));
        HBox.setMargin(boutonRecherche, new Insets(10));
        hbRecherche.setAlignment(Pos.TOP_CENTER);
        tfRecherche.focusedProperty().addListener(new ControleurRecherche(tfRecherche));
        tfRecherche.setPromptText("Pays, athlète...");


    }

    public void ajouteBottom(){
        this.setBottom(this.connexion);
        BorderPane.setAlignment(connexion, Pos.CENTER);
        BorderPane.setMargin(connexion, new Insets(10));
    }

}