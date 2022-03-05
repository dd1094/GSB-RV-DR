package fr.gsb.rv.dr.gsb;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.*;
import fr.gsb.rv.dr.utilitaires.*;
import fr.gsb.rv.dr.technique.PanneauRapports;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import static javafx.application.Application.launch;

/*public class HelloApplication extends Application  {
    private VBox vbox1;
    private Button button1;

    private VBox vbox2;
    private Button button2;

    Visiteur visiteur;
    @Override
    public void start(Stage primaryStage) throws IOException, ConnexionException {

        // set title for the stage
        primaryStage.setTitle("GSB-RV-DR");

        // create a menu
        Menu menuFichier = new Menu("Fichier");
        Menu menuRapports= new Menu("Rapports");
        Menu menuPraticiens = new Menu("Praticiens");
        menuRapports.setDisable(true);
        menuPraticiens.setDisable(true);

        // create menuitems
        MenuItem itemSeConnecter = new MenuItem("Se Connecter");
        MenuItem itemSeDeconnecter = new MenuItem("Se Deconnecter");
        itemSeDeconnecter.setDisable(true);
        MenuItem itemQuitter = new MenuItem("Quitter");
        MenuItem itemConsulter = new MenuItem("Consulter");
        MenuItem itemHesitants = new MenuItem("Hésitants");
        // add menu items to menu
        menuFichier.getItems().addAll( itemSeConnecter , itemSeDeconnecter , itemQuitter );
        menuRapports.getItems().addAll( itemConsulter );
        menuPraticiens.getItems().addAll( itemHesitants );

        // create a menubar
        MenuBar barreMenus = new MenuBar();

        // add menu to menubar
        barreMenus.getMenus().addAll(menuFichier,menuRapports,menuPraticiens);

        /*MenuBar barreMenus = new MenuBar();
        Menu menuFichier = new Menu("Fichier");
        Menu menuRapports = new Menu("Rapports");
        Menu menuPraticiens = new Menu("Praticiens");
        menuRapports.setDisable(true);
        menuPraticiens.setDisable(true);

        MenuItem itemSeConnecter = new MenuItem("Se Connecter");
        MenuItem itemSeDeconnecter = new MenuItem("Se Deconnecter");
        itemSeDeconnecter.setDisable(true);
        MenuItem itemQuitter = new MenuItem("Quitter");
        MenuItem itemConsulter = new MenuItem("Consulter");
        MenuItem itemHesitants = new MenuItem("Hésitants");

        menuFichier.getItems().addAll( itemSeConnecter , itemSeDeconnecter , itemQuitter );
        menuRapports.getItems().addAll( itemConsulter );
        menuPraticiens.getItems().addAll( itemHesitants );
        SeparatorMenuItem sep = new SeparatorMenuItem();
        menuFichier.getItems().add( 2 , sep );
        barreMenus.getMenus().addAll( menuFichier , menuRapports , menuPraticiens );

        itemSeConnecter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //session.ouvrir(new Visiteur("OB001", "BOUAICHI", "Oumayma"));
                        //primaryStage.setTitle(Session.getSession().getLeVisiteur().getPrenom());
                        VueConnexion vue = new VueConnexion();
                        Optional<Pair<String, String>> response = vue.showAndWait();
                        if (response.isPresent()){
                            try {
                                visiteur = ModeleGsbRv.seConnecter(response.get().getKey(), response.get().getValue());
                                //visiteur = ModeleGsbRv.seConnecter("a131","azerty");
                                if (visiteur != null){
                                    Session.ouvrir(new Visiteur(visiteur.getMatricule(), visiteur.getNom(), visiteur.getPrenom()));
                                    System.out.println("Connecté!!");
                                    menuRapports.setDisable(false);
                                    menuPraticiens.setDisable(false);
                                    itemSeDeconnecter.setDisable(false);
                                    itemSeConnecter.setDisable(true);
                                }
                                else {
                                    Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
                                    alertQuitter.setTitle("Identifiants incorectes");
                                    alertQuitter.setHeaderText("Echec connexion");
                                    alertQuitter.setContentText("Matricule ou mot de passe incorectes");
                                    alertQuitter.getButtonTypes().setAll( ButtonType.OK);

                                    Optional<ButtonType> responses = alertQuitter.showAndWait();
                                }
                            } catch (ConnexionException e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
        );

        itemSeDeconnecter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        menuRapports.setDisable(true);
                        menuPraticiens.setDisable(true);
                        itemSeDeconnecter.setDisable(true);
                        itemSeConnecter.setDisable(false);
                        primaryStage.setTitle("GSB-RV-DR");
                        Session.fermer();
                    }
                }
        );

        itemQuitter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
                        alertQuitter.setTitle("Quitter");
                        alertQuitter.setHeaderText("Demande de confirmation");
                        alertQuitter.setContentText("Voulez-vous quitter l'application ?");
                        ButtonType btnOui = new ButtonType("Oui");
                        ButtonType btnNon = new ButtonType("Non");
                        alertQuitter.getButtonTypes().setAll( btnOui , btnNon );

                        Optional<ButtonType> response = alertQuitter.showAndWait();
                        if(response.get() == btnOui){
                            Platform.exit();
                            Session.fermer();
                        }
                    }
                }
        );
        itemConsulter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("[Rapport] " + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
                    }
                }
        );
        itemHesitants.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("[Praticiens] " + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
                    }
                }
        );

        /*BorderPane root = new BorderPane();
        root.setTop(barreMenus);
        Scene scene = new Scene( root , 600 , 500 );
        primaryStage.setTitle("GSB-RV-DR");
        primaryStage.setScene(scene);
        primaryStage.show();

        Label textP1 = new Label();
        textP1.setText("Bonjour");

        Label textP2 = new Label();
        textP2.setText("Bonjour");

        VBox vb = new VBox(barreMenus);

        // create a scene
        Scene sc = new Scene(vb, 500, 300);

        // set the scene
        primaryStage.setScene(sc);

        primaryStage.show();
    }



    public static void main(String[] args) throws ConnexionException {
        launch();
        /*try {
            ConnexionBD.getConnexion();
        } catch (Exception e) {
           System.out.println(e);
        }
       try {
            ModeleGsbRv.seConnecter("a131", "azerty");
        }
       catch (Exception e){
           System.out.println(e);
        }
    }
}*/

public class HelloApplication extends Application {



    Visiteur visiteur;

    private Stage stage;
    private Scene scene;
    private BorderPane borderPane;



    @Override
    public void start(Stage primaryStage) throws Exception {

        /*stage = primaryStage;
        stage.setTitle("Switching Scenes");

        //createPaneTwo vbox2 = new createPaneTwo();
        //createPaneThree vbox3 = new createPaneThree();
        PanneauAccueil panneauAccueil = new PanneauAccueil();
        PanneauRapports panneauRapports = new PanneauRapports();
        PanneauPraticiens panneauPraticiens = new PanneauPraticiens();

        panneauRapports.PanneauRapports();

        VBox vbox1 = new VBox(panneauAccueil.PanneauAccueil());
        VBox vbox2 = new VBox(panneauRapports.PanneauRapports());
        VBox vbox3 = new VBox(panneauPraticiens.PanneauPraticiens());


        BorderPane borderPane = new BorderPane();

        MenuBar barreMenus = new MenuBar();
        Menu menuFichier = new Menu("Fichier");
        Menu menuRapports = new Menu("Rapports");
        Menu menuPraticiens = new Menu("Praticiens");
        menuRapports.setDisable(true);
        menuPraticiens.setDisable(true);

        MenuItem itemSeConnecter = new MenuItem("Se Connecter");
        MenuItem itemSeDeconnecter = new MenuItem("Se Deconnecter");
        itemSeDeconnecter.setDisable(true);
        MenuItem itemQuitter = new MenuItem("Quitter");
        MenuItem itemConsulter = new MenuItem("Consulter");
        MenuItem itemHesitants = new MenuItem("Hésitants");

        menuFichier.getItems().addAll( itemSeConnecter , itemSeDeconnecter , itemQuitter );
        menuRapports.getItems().addAll( itemConsulter );
        menuPraticiens.getItems().addAll( itemHesitants );
        SeparatorMenuItem sep = new SeparatorMenuItem();
        menuFichier.getItems().add( 2 , sep );
        barreMenus.getMenus().addAll( menuFichier , menuRapports , menuPraticiens );

        itemSeConnecter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //session.ouvrir(new Visiteur("OB001", "BOUAICHI", "Oumayma"));
                        //primaryStage.setTitle(Session.getSession().getLeVisiteur().getPrenom());
                        VueConnexion vue = new VueConnexion();
                        Optional<Pair<String, String>> response = vue.showAndWait();
                        if (response.isPresent()){
                            try {
                                visiteur = ModeleGsbRv.seConnecter(response.get().getKey(), response.get().getValue());
                                //visiteur = ModeleGsbRv.seConnecter("a131","azerty");
                                if (visiteur != null){
                                    Session.ouvrir(new Visiteur(visiteur.getMatricule(), visiteur.getNom(), visiteur.getPrenom()));
                                    System.out.println("Connecté!!");
                                    menuRapports.setDisable(false);
                                    menuPraticiens.setDisable(false);
                                    itemSeDeconnecter.setDisable(false);
                                    itemSeConnecter.setDisable(true);
                                    //vbox1.setVisible(false);
                                }
                                else {
                                    Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
                                    alertQuitter.setTitle("Identifiants incorectes");
                                    alertQuitter.setHeaderText("Echec connexion");
                                    alertQuitter.setContentText("Matricule ou mot de passe incorectes");
                                    alertQuitter.getButtonTypes().setAll( ButtonType.OK);

                                    Optional<ButtonType> responses = alertQuitter.showAndWait();
                                }
                            } catch (ConnexionException e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
        );

        itemSeDeconnecter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        menuRapports.setDisable(true);
                        menuPraticiens.setDisable(true);
                        itemSeDeconnecter.setDisable(true);
                        itemSeConnecter.setDisable(false);
                        primaryStage.setTitle("GSB-RV-DR");
                        //switchPanes(vbox1);
                        vbox1.toFront();
                        Session.fermer();
                    }
                }
        );

        itemQuitter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
                        alertQuitter.setTitle("Quitter");
                        alertQuitter.setHeaderText("Demande de confirmation");
                        alertQuitter.setContentText("Voulez-vous quitter l'application ?");
                        ButtonType btnOui = new ButtonType("Oui");
                        ButtonType btnNon = new ButtonType("Non");
                        alertQuitter.getButtonTypes().setAll( btnOui , btnNon );

                        Optional<ButtonType> response = alertQuitter.showAndWait();
                        if(response.get() == btnOui){
                            Platform.exit();
                            Session.fermer();
                        }
                    }
                }
        );
        itemConsulter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        vbox1.toBack();
                        vbox3.toBack();
                        vbox2.toFront();
                    }
                }
        );
        itemHesitants.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        vbox1.toBack();
                        vbox2.toBack();
                        vbox3.toFront();
                    }
                }
        );




        borderPane.setTop(new VBox(barreMenus));
        Label textAccueil = new Label();
        textAccueil.setText("Accueil");
        //itemSeConnecter.setOnAction(e -> switchPanes(vbox2.createPaneTwo()));
        //itemSeDeconnecter.setOnAction(e -> switchPanes(vbox3.createPaneThree()));
        //itemQuitter.setOnAction(e -> switchPanes(vbox1));



        //borderPane.setCenter(vbox3.createPaneThree());
        //borderPane.setCenter(new VBox(vbox2.createPaneTwo()));
        //borderPane.setCenter(vbox1);
        StackPane stackpane = new StackPane(vbox1,vbox3,vbox2);
        borderPane.setCenter(new StackPane(stackpane));

        vbox2.toBack();
        vbox1.toFront();

        scene = new Scene(borderPane, 800, 500);
        stage.setScene(scene);

        stage.show();

        */

    }

    /*private VBox createPaneOne() {

        button1 = new Button("Click to show Pane 2");
        button1.setOnAction(e -> switchPanes(vbox2));
        vbox1 = new VBox(button1);
        vbox1.setStyle("-fx-background-color: green");

        return vbox1;
    }*/

    /*private VBox createPaneTwo() {

        button2 = new Button("Click to show Pane 1");
        button2.setOnAction(e -> switchPanes(vbox1));
        vbox2 = new VBox(button2);
        vbox2.setStyle("-fx-background-color: yellow");

        return vbox2;
    }*/


    // Switch Layout Panes in Center of BorderPane
    public void switchPanes(Pane pane) {
        borderPane.setCenter(pane);
    }

    public static void main(String[] args) throws ConnexionException {
        //Application.launch(args);s
        List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesistants();

        /*if (praticiens == null){
            System.out.println("Null");
        }

        for (Praticien unPraticien : praticiens){
            System.out.println(unPraticien.toString());
        }*/

        /*Collections.sort(praticiens, new ComparateurCoefConfiance());

        for (Praticien unPraticien : praticiens){
            System.out.println(unPraticien);
        }*/

        /*Collections.sort(praticiens ,new ComparateurCoefNotoriete().reversed());

        for (Praticien unPraticien : praticiens){
            System.out.println(unPraticien);
        }*/
        Collections.sort(praticiens ,new ComparateurDateVisite());

        for (Praticien unPraticien : praticiens){
            System.out.println(unPraticien);
        }
    }

}