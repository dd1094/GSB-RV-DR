package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.gsb.HelloApplication;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Session;
import fr.gsb.rv.dr.technique.VueConnexion;
import javafx.application.Application;
import javafx.application.Platform;
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
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import fr.gsb.rv.dr.gsb.HelloApplication;

public class PanneauPraticiens {

    public final int CRITERE_COEF_CONFIANCE = 1;
    public final int CRITERE_COEF_NOTOTRIETE = 2;
    public final int CRITERE_COEF_VISITE = 3;

    private int critereTri = CRITERE_COEF_CONFIANCE;

    private RadioButton rbCoefConfiance;
    private RadioButton rbCoefNotoriete;
    private RadioButton rbDateVisite;

    VBox vbox = new VBox();
    ToggleGroup groupeBouton = new ToggleGroup();

    public VBox PanneauPraticiens() {

        //Label textPraticien = new Label();
        //textPraticien.setText("Praticien");

        vbox.setStyle("-fx-background-color: white");

        ToggleGroup groupeBouton = new ToggleGroup();
        RadioButton rbCoefConfiance = new RadioButton("Coef Confiance");
        RadioButton rbCoefNotoriete = new RadioButton("Coef Notoriété");
        RadioButton rbDateVisite = new RadioButton("Coef date de visite");

        rbCoefConfiance.setToggleGroup(groupeBouton);
        rbCoefNotoriete.setToggleGroup(groupeBouton);
        rbDateVisite.setToggleGroup(groupeBouton);

        rbCoefConfiance.setSelected(true);

        vbox = new VBox(rbCoefConfiance,rbCoefNotoriete,rbDateVisite);
        return vbox;
    }

    public void rafraichir(){}

    public void getCritereTri( int critereTri){}
}
