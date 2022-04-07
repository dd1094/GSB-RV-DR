package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.gsb.HelloApplication;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Session;
import fr.gsb.rv.dr.technique.VueConnexion;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
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

    private List<Praticien> praticiens ;
    private ObservableList<Praticien> observableListPraticiens ;

    VBox vbox = new VBox();
    ToggleGroup groupeBouton = new ToggleGroup();
    private TableView<Praticien> tableView = new TableView<>();

    public VBox PanneauPraticiens() {



        //Label textPraticien = new Label();
        //textPraticien.setText("Praticien");

        //vbox.setStyle("-fx-background-color: white");

        ToggleGroup groupeBouton = new ToggleGroup();
        RadioButton rbCoefConfiance = new RadioButton("Confiance");
        RadioButton rbCoefNotoriete = new RadioButton("Notoriété");
        RadioButton rbDateVisite = new RadioButton("Date Visite");

        /*GridPane gridPane = new GridPane();
        gridPane.setHgap(gridPane , a);*/

        rbCoefConfiance.setOnAction(ActionEvent -> {
            this.setCritereTri(CRITERE_COEF_CONFIANCE);
            try {
                this.rafraichir();
            } catch (ConnexionException e) {
                e.printStackTrace();
            }
        });

        GridPane.setHalignment(rbCoefConfiance, HPos.RIGHT);

        rbCoefConfiance.setToggleGroup(groupeBouton);
        rbCoefNotoriete.setToggleGroup(groupeBouton);
        rbDateVisite.setToggleGroup(groupeBouton);

        rbCoefConfiance.setSelected(true);


        try {

        praticiens = ModeleGsbRv.getPraticiensHesistants();
        observableListPraticiens = FXCollections.observableArrayList(praticiens);

        TableColumn<Praticien, Integer> colNumero = new TableColumn<Praticien, Integer>("Numero");
        TableColumn<Praticien,String> colNom = new TableColumn<Praticien, String>("Nom");
        TableColumn<Praticien,String> colVille = new TableColumn<Praticien, String>("Ville");

        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tableView.getColumns().add(colNumero);

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableView.getColumns().add(colNom);

        colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
        tableView.getColumns().add(colVille);
        //Collections.sort(praticiens, new ComparateurCoefNotoriete());

        tableView.setItems(observableListPraticiens);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.rafraichir();

        } catch (ConnexionException e) {
            e.printStackTrace();
        }

        /*try {
            List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesistants();
            //Collections.sort(praticiens, new ComparateurCoefNotoriete());
            for (Praticien unPraticien : praticiens){
                Praticien praticien = new Praticien(unPraticien.getNumero(), unPraticien.getNom(), unPraticien.getVille());
                tableView.getItems().add(praticien);
            }

        } catch (ConnexionException e) {
            e.printStackTrace();
        }*/
        rbCoefConfiance.setOnAction(ActionEvent -> {
            this.setCritereTri(CRITERE_COEF_CONFIANCE);
            try {
                this.rafraichir();
            } catch (ConnexionException e) {
                e.printStackTrace();
            }
        });

        rbCoefNotoriete.setOnAction(ActionEvent -> {
            this.setCritereTri(CRITERE_COEF_NOTOTRIETE);
            try {
                this.rafraichir();
            } catch (ConnexionException e) {
                e.printStackTrace();
            }
        });

        rbDateVisite.setOnAction(ActionEvent -> {
            this.setCritereTri(CRITERE_COEF_VISITE);
            try {
                this.rafraichir();
            } catch (ConnexionException e) {
                e.printStackTrace();
            }
        });

        Collections.sort(praticiens, new ComparateurCoefConfiance());

        Label lbl  = new Label();
        //vbox = new VBox(rbCoefConfiance,rbCoefNotoriete,rbDateVisite);
        HBox hbox1= new HBox(rbCoefConfiance,rbCoefNotoriete,rbDateVisite);

        vbox.setStyle("-fx-background-color: white");
        vbox= new VBox(hbox1,tableView,lbl);
        return vbox;
    }


    public void rafraichir() throws ConnexionException {
        praticiens = ModeleGsbRv.getPraticiensHesistants();
        observableListPraticiens = FXCollections.observableArrayList(praticiens);

        if(this.getCritereTri() == CRITERE_COEF_CONFIANCE){
            observableListPraticiens.sort(new ComparateurCoefConfiance());
            tableView.setItems(observableListPraticiens);
        }
        else if (this.getCritereTri() == CRITERE_COEF_NOTOTRIETE){
            observableListPraticiens.sort(new ComparateurCoefNotoriete());
            tableView.setItems(observableListPraticiens);
        }
        else if (this.getCritereTri() == CRITERE_COEF_VISITE){
            observableListPraticiens.sort(new ComparateurDateVisite());
            tableView.setItems(observableListPraticiens);
        }

        /*praticiens = ModeleGsbRv.getPraticiensHesistants();
        observableListPraticiens = FXCollections.observableArrayList();
        observableListPraticiens.sort(new ComparateurCoefNotoriete());
        tableView.setItems(observableListPraticiens);*/
    }
    public int getCritereTri() {
        return critereTri;
    }

    public void setCritereTri(int critereTri) {
        this.critereTri = critereTri;
    }
}
