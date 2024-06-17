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
    

    public FenetreJournaliste(Button btn){
        super();
        this.connexion = btn;
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
        HBox hbChoix = new HBox();
        Button classement = new Button("Classement");
        Button natation = new Button("Natation");
        classement.setStyle("-fx-background-radius: 1em;");
        natation.setStyle("-fx-background-radius: 1em;");


        hbChoix.getChildren().addAll(classement,natation);
        HBox.setMargin(natation, new Insets(10)); 
        HBox.setMargin(classement, new Insets(10));
        hbChoix.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, null,null)));

        this.setTop(hbChoix);
    
    }
    public void ajouteCenter(){
        HBox hbRecherche = new HBox();
        TextField tfRecherche = new TextField();
        Button boutonRecherche = new Button("Rechercher");
        hbRecherche.getChildren().addAll(tfRecherche, boutonRecherche);


    }

    public void ajouteBottom(){
        this.setBottom(this.connexion);
        BorderPane.setAlignment(connexion, Pos.CENTER);
    }

}