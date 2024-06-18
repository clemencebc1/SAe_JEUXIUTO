package vue;
import java.sql.SQLException;

import javax.sound.sampled.Control;

import bd.*;
import vue.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.paint.Color;
// import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

// java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -cp bin vue.FenetreAccueil


public class FenetreAccueil extends Application {
    private Scene scene;
    private FenetreAdmin fenetreAdmin;
    private Button boutonConnexion;
    private Button inscri;
    private FenetreConnexion fenetreCo;
    private FenetreJournaliste fenetreJourna;
    private FenetreInscription fenetreInscription;
    private ConnexionMySQL connexionMySQL;
    private AthleteBD bd;
    private Pane root;

    @Override
    public void init(){

        this.boutonConnexion = new Button("Connexion");
        this.inscri = new Button("S'inscrire");
        this.boutonConnexion.setStyle("-fx-text-fill: #000000;"+"-fx-background-radius: 1em;");
        this.inscri.setStyle("-fx-text-fill: #000000;"+"-fx-background-radius: 1em;");
        this.root = new Pane();
        this.boutonConnexion.setStyle("-fx-background-color: black; -fx-background-radius: 1em; -fx-text-fill: #ffffff");
        this.bd = new AthleteBD();
        try {
            this.connexionMySQL = new ConnexionMySQL();
            this.connexionMySQL.connecter("servinfo-maria","DBlobjois","lobjois","lobjois");
        }catch (ClassNotFoundException ex){
            System.err.println("Driver MySQL non trouvé!!!");
            System.exit(1);
        }
        catch(SQLException e){
            System.err.println("Connexion impossible");
        }
    
    }
    @Override
    public void start(Stage stage) throws Exception{
        this.boutonConnexion.setOnAction(new ControleBouton(this));
        this.inscri.setOnAction(new ControleBouton(this));
        this.scene = new Scene(this.root);
        this.seConnecter();
        stage.setTitle("Fenetre Accueil JO");
        stage.setScene(scene);
        stage.show();
    }

    public void afficheFenetreJournaliste(){
        this.fenetreJourna = new FenetreJournaliste(boutonConnexion, this);
        this.root= this.fenetreJourna;
        this.boutonConnexion.setText("Déconnexion");

        this.scene.setRoot(root);
    }
    public void seConnecter(){
        this.fenetreCo = new FenetreConnexion(boutonConnexion, inscri);
       this.root = this.fenetreCo;
       this.boutonConnexion.setText("Connexion");

       this.scene.setRoot(root);
    }

    public void inscrire(){
        this.fenetreInscription = new FenetreInscription(inscri,this);
        this.root = this.fenetreInscription;
        this.scene.setRoot(root);
        
    }
    public void afficheFenetreAdmin(){
        this.fenetreAdmin = new FenetreAdmin(boutonConnexion, this);
        this.root= this.fenetreAdmin;
        this.boutonConnexion.setText("Déconnexion");

        this.scene.setRoot(root);
    }

    public Alert popUpErreurConnexion(){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Erreur de connexion, votre login ou password est invalide", ButtonType.OK);
        alert.setTitle("Attention");
        return alert;
    }

    public Alert popUpConnexion(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Vous êtes connecté", ButtonType.OK);
        alert.setTitle("Connecté");
        return alert;
    }


        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    

    public FenetreConnexion getFenetreCo() {
        return fenetreCo;
        }
    public FenetreJournaliste getFenetreJourna() {
        return fenetreJourna;
        }
    public ConnexionMySQL getConnexionMySQL() {
        return connexionMySQL;
    }
    public FenetreInscription getFenetreInscription(){
        return this.fenetreInscription;
    }
    public AthleteBD getBD(){
        return this.bd;
    }
    public Button getButtonInscri(){
        return this.inscri;
    }
    
}
