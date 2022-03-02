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

    VBox vbox = new VBox();

    public VBox PanneauPraticiens() {

        Label textPraticien = new Label();
        textPraticien.setText("Praticien");
        vbox = new VBox(textPraticien);
        vbox.setStyle("-fx-background-color: white");

        return vbox;
    }
}
