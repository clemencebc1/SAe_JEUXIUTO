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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Arrays;
import java.util.ArrayList;



// javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d bin/ src/vue/*.java
// java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -cp bin vue.FenetreAccueil


public class Articles extends ArrayList<BorderPane>{
    private FenetreAccueil appli;

    public Articles(FenetreAccueil appli){
        this.appli = appli;

    }

    public void creeArticleSport(){
        // sport natation
        BorderPane bpNatation1 = new BorderPane();
        ImageView imageNatation1 = new ImageView(new Image("file:./img/natation1.jpg",175,125,false,false));
        Text titreNatation1 = new Text("Championnats de France de natation 2024 |\n"+" Florent Manaudou et Maxime Grousset en forme dès les séries du 100 m nage libre");
        titreNatation1.setFont(Font.font(" Arial" ,FontWeight.BOLD , 15));
        Text contenu = new Text("Dès les séries du 100 m nage libre hommes aux Championnats de France de natation 2024,\n"+" Florent Manaudou et Maxime Grousset ont réussi de très bons chronos, prometteur avant la finale prévue ce soir à 18h56,\n"+" mais également pour le relais du 4x100 m nage libre.\n"+" Aux Jeux Olympiques de Tokyo 2020, les Français avaient terminé 6e de la finale, remportés par les États-Unis d'Amérique.");
        VBox vbNatation1 = new VBox();
        vbNatation1.getChildren().addAll(titreNatation1,contenu);
        bpNatation1.setCenter(vbNatation1);
        bpNatation1.setLeft(imageNatation1);
        BorderPane.setMargin(imageNatation1, new Insets(10));
        VBox.setMargin(titreNatation1, new Insets(15));
        VBox.setMargin(contenu, new Insets(15));
        this.add(bpNatation1);
        
        vbNatation1.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null,null)));

        BorderPane bpNatation2 = new BorderPane();
        ImageView imageNatation2 = new ImageView(new Image("file:./img/natation1.jpg", 175,125, false,false));
        bpNatation2.setLeft(imageNatation1);
        Text titreNatation2 = new Text("Championnats de France de natation 2024 |\n"+" Florent Manaudou et Maxime Grousset en forme dès les séries du 100 m nage libre");
        titreNatation2.setFont(Font.font(" Arial" ,FontWeight.BOLD , 15));
        Text contenu1 = new Text("Dès les séries du 100 m nage libre hommes aux Championnats de France de natation 2024,\n"+" Florent Manaudou et Maxime Grousset ont réussi de très bons chronos, prometteur avant la finale prévue ce soir à 18h56,\n"+" mais également pour le relais du 4x100 m nage libre.\n"+" Aux Jeux Olympiques de Tokyo 2020, les Français avaient terminé 6e de la finale, remportés par les États-Unis d'Amérique.");
        VBox vbNatation2 = new VBox();
        vbNatation2.getChildren().addAll(titreNatation2,contenu1);
        bpNatation2.setCenter(vbNatation2);
        this.add(bpNatation2);

        vbNatation2.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null,null)));

    }
    
}