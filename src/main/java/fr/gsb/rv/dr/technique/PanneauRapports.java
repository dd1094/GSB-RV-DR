package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.gsb.HelloApplication;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Session;
import fr.gsb.rv.dr.technique.VueConnexion;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import fr.gsb.rv.dr.gsb.HelloApplication;

public class PanneauRapports {

    VBox vbox = new VBox();

    private ComboBox<Visiteur> cbVisiteurs;
    private ComboBox<Integer> cbMois;
    private ComboBox<Integer> cbAnnee;

    private ObservableList<Visiteur> observableListVisiteur;
    private ObservableList<Mois> observableListMois;

    private List<Visiteur> visiteurs;
    private Mois[] mois;

    private enum mois;

    public VBox PanneauRapports() throws ConnexionException {

        visiteurs = ModeleGsbRv.getVisiteur();
        observableListVisiteur = FXCollections.observableArrayList(visiteurs);
        cbVisiteurs.setItems(observableListVisiteur);

        mois = Mois.values();
        observableListMois = FXCollections.observableArrayList(mois);



        Label textRapports = new Label();
        textRapports.setText("Rapports");
        vbox = new VBox(textRapports);
        vbox.setStyle("-fx-background-color: white");

        return vbox;
    }
    public void rafraichir(){}

}
