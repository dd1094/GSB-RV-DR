package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.gsb.HelloApplication;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Session;
import fr.gsb.rv.dr.technique.VueConnexion;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.scene.control.cell.PropertyValueFactory;
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

        //vbox.setStyle("-fx-background-color: white");

        ToggleGroup groupeBouton = new ToggleGroup();
        RadioButton rbCoefConfiance = new RadioButton("Coef Confiance");
        RadioButton rbCoefNotoriete = new RadioButton("Coef Notoriété");
        RadioButton rbDateVisite = new RadioButton("Coef date de visite");

        /*GridPane gridPane = new GridPane();
        gridPane.setHgap(gridPane , a);*/

        GridPane.setHalignment(rbCoefConfiance, HPos.RIGHT);

        rbCoefConfiance.setToggleGroup(groupeBouton);
        rbCoefNotoriete.setToggleGroup(groupeBouton);
        rbDateVisite.setToggleGroup(groupeBouton);

        rbCoefConfiance.setSelected(true);


        TableView <Praticien> tableView= new TableView();
        TableColumn<Praticien, Integer> colNumero = new TableColumn<Praticien, Integer>("Numero");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));

        TableColumn<Praticien,String> colNom = new TableColumn<Praticien, String>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Praticien,String> colVille = new TableColumn<Praticien, String>("Ville");
        colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));





        try {
            List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesistants();
            for (Praticien unPraticien : praticiens){
                Praticien praticien = new Praticien(unPraticien.getNumero(), unPraticien.getNom(), unPraticien.getVille());
                tableView.getItems().add(praticien);
            }
        } catch (ConnexionException e) {
            e.printStackTrace();
        }
        rbCoefConfiance.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                    }
                }
        );

        Label lbl  = new Label();

        tableView.getColumns().add(colNumero);
        tableView.getColumns().add(colNom);
        tableView.getColumns().add(colVille);


        //vbox = new VBox(rbCoefConfiance,rbCoefNotoriete,rbDateVisite);
        HBox hbox1= new HBox(rbCoefConfiance,rbCoefNotoriete,rbDateVisite);
        vbox.setStyle("-fx-background-color: white");
        vbox= new VBox(hbox1,tableView,lbl);
        return vbox;
    }

    public void rafraichir() throws ConnexionException {
        List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesistants();
        ObservableList<Praticien> e = FXCollections.observableArrayList();
    }

    public void getCritereTri( int critereTri){}
}
