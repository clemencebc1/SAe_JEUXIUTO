package fxml;
import java.lang.ModuleLayer.Controller;

import javafx.application.Application;
import javafx.application.Platform;
// import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


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
import java.net.URL;

// javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.fxml -d bin/ src/fxml/*.java
// java --module-path /usr/share/openjfx/lib/ --add-modules javafx.fxml -cp bin fxml.fenetre


public class fenetre extends Application{

    @Override
    public void init(){
    
    }
    @Override
    public void start (Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("file:.src/fxml/FenetreAccueil.fxml"));

        primaryStage.setTitle("JO");
        primaryStage.setScene(new Scene(root, 300,275));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}