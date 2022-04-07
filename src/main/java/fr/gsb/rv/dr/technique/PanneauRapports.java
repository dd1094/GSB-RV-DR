package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.List;

public class PanneauRapports {

    VBox vbox = new VBox();


    /*private ComboBox<Mois> cbMois;
    private ComboBox<Integer> cbAnnee;
    private ComboBox<Praticien> cbPraticien;*/

    private ObservableList<Visiteur> observableListVisiteur;
    private ObservableList<Mois> observableListMois;
    private ObservableList<Integer> observableListAnnee;
    private ObservableList<Praticien> observableListPraticiens;


    private List<Visiteur> visiteurs;
    private List<Praticien> praticiens;


    public VBox PanneauRapports() throws ConnexionException {

        ComboBox<Visiteur> cbVisiteurs = new ComboBox<>();
        visiteurs = ModeleGsb Rv.getVisiteur();
        observableListVisiteur = FXCollections.observableArrayList(visiteurs);
        cbVisiteurs.setItems(observableListVisiteur);

        ComboBox<Mois> cbMois = new ComboBox<>();
        observableListMois = FXCollections.observableArrayList(Mois.values());
        cbMois.setItems(observableListMois);

        LocalDate aujourdhui = LocalDate.now();
        int anneeCourante = aujourdhui.getYear();

        ComboBox<Integer> cbAnnee = new ComboBox<>();
        observableListAnnee = FXCollections.observableArrayList(anneeCourante);
        cbAnnee.setItems(observableListAnnee);

        Button bValider = new Button("Valider");

        bValider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rafraichir();
            }
        });

        HBox hbox1= new HBox(cbVisiteurs,cbMois,cbAnnee);
        HBox hbox2= new HBox(bValider);

        Label textRapports = new Label();
        textRapports.setText("Rapports");
        vbox = new VBox(textRapports,hbox1,hbox2);
        vbox.setStyle("-fx-background-color: white");

        return vbox;
    }
    public void rafraichir(){
        Visiteur selectedVisiteur =  ;
    }

}
