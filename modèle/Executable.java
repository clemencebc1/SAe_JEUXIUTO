import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

public class Executable{
    public static void main (String [] args){
        Sport natation = new Natation("Natation", "Eau");
        Pays france = new Pays("France");
        Pays angleterre = new Pays("Angleterre");
        Pays espagne = new Pays("Espagne");
    
        Equipe equipeFrance = new Equipe("France");
        Epreuve epreuveNatation = new Epreuve("100m brasse hommes", "hommes", "individuel", natation);
        Athlete marchand = new Athlete("Marchand", "Léon", "M", 10, 20, 40, france);
        Athlete peaty = new Athlete("Peaty", "Adam", "M", 15, 22, 35, angleterre);
        System.out.println(epreuveNatation.getNom());
        // créaton d'un classement
        Comparator<Athlete> comp1 = new CompareMedailleOr();
        france.setNbOr(3);
        angleterre.setNbOr(5);
        espagne.setNbOr(2);
        epreuveNatation.ajoutParticipants(marchand);
        epreuveNatation.ajoutParticipants(peaty);
        List<Pays> listeP = Arrays.asList(france, angleterre);
        System.out.println(epreuveNatation.classement(comp1, Tri.PAYS));

        System.out.println(natation.calculeRes(marchand)+" points pour "+marchand.getNom());


        // calcul différent en fonction du sport
        Sport athletisme = new Athletisme("Athletisme", "sol");
        Epreuve epreuveAthletisme = new Epreuve("110m haies", "femmes", "individuel", athletisme);
        Athlete sambaMayela = new Athlete("Samba-Mayela", "Cyréna", "F", 20, 40, 30, france);
        epreuveAthletisme.ajoutParticipants(sambaMayela);
        System.out.println(athletisme.calculeRes(sambaMayela)+" points pour "+sambaMayela.getNom());


    }
}