package vue;
import vue.*;

import java.net.ConnectException;

import bd.ConnexionMySQL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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


public class FenetreConnexion extends BorderPane{
    private Button connexion;
    private Button inscription;
    private TextField tfId;
    private PasswordField pwd;
    private FenetreAccueil appli;


    public FenetreConnexion(Button btn, Button inscri, FenetreAccueil appli){
        super();
        this.connexion = btn;
        this.inscription = inscri;
        this.tfId = new TextField();
        this.pwd = new PasswordField();
        this.appli = appli;
        this.ajouteCenter();
        this.ajouteImage();
        this.modifieBorderPane();
    }

    /** ajout de l'image
     */
    public void ajouteImage(){
        ImageView imageJO = new ImageView(new Image("file:./img/logo-paris-2024.jpg"));
        this.setRight(imageJO);
    }


    public void ajouteCenter(){
        VBox vbCentre = new VBox();
        Label labelId = new Label("Identifiant");
        Label labelPasswd = new Label("Mot de passe");
        ImageView infoImage = new ImageView(new Image("file:./img/info.png",15,15,false,false));
        Button info = new Button("", infoImage);
        
        info.setOnAction(new ControleurInfo(this.appli));

        vbCentre.getChildren().addAll(labelId,this.tfId,labelPasswd,this.pwd,this.connexion,this.inscription,info);
        VBox.setMargin(info, new Insets(10));
        this.setCenter(vbCentre);
        vbCentre.setAlignment(Pos.CENTER);
        VBox.setMargin(labelPasswd, new Insets(15));
        VBox.setMargin(this.connexion, new Insets(15));
        VBox.setMargin(tfId, new Insets(15));
        VBox.setMargin(pwd, new Insets(15));
        VBox.setMargin(this.inscription, new Insets(20));
        BorderPane.setMargin(vbCentre, new Insets(20));
        this.tfId.setPromptText("Votre identifiant");
        this.pwd.setPromptText("Votre mot de passe");
        this.tfId.focusedProperty().addListener(new ControleurId(this.tfId));
        vbCentre.setStyle("-fx-background-radius: 1em; -fx-background-color: whitesmoke");
        
    }

    public Alert popUpInfo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Votre identifiant de connexion à l'application \n Jeux IUT'Olympiques ne contenant aucun caractère spécial, et votre mot de passe. \n Si vous avez oublié, veuillez contacter un administrateur.", ButtonType.OK);
        alert.setTitle("Informations");
        alert.setHeaderText("Informations identifiant et mot de passe");
        return alert;
    }

    public void modifieBorderPane(){
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null,null)));

    }

    public FenetreConnexion fenetre(){
        return this;
    }
    public TextField getTfId() {
        return tfId;
    }

    public PasswordField getPwd() {
        return pwd;
    }
}