package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.w3c.dom.events.MouseEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PanneauRapports {

    VBox vbox = new VBox();


    private ComboBox<Visiteur> cbVisiteurs;
    private ComboBox<Mois> cbMois;
    private ComboBox<Integer> cbAnnee;
    /*private ComboBox<Praticien> cbPraticien;*/

    private ObservableList<Visiteur> observableListVisiteur;
    private ObservableList<Mois> observableListMois;
    private ObservableList<Integer> observableListAnnee;
    private ObservableList<RapportVisite> observableListRapportVisite;


    private List<Visiteur> visiteurs;
    private List<Praticien> praticiens;
    private List<RapportVisite> rapportVisites;

    private TableView<RapportVisite> tableView;


    public VBox PanneauRapports() throws ConnexionException {

        tableView = new TableView<>();

        cbVisiteurs = new ComboBox<>();
        visiteurs = ModeleGsbRv.getVisiteur();
        observableListVisiteur = FXCollections.observableArrayList(visiteurs);
        cbVisiteurs.setItems(observableListVisiteur);

        cbMois = new ComboBox<>();
        observableListMois = FXCollections.observableArrayList(Mois.values());
        cbMois.setItems(observableListMois);

        /*LocalDate aujourdhui = LocalDate.now();
        int anneeCourante = aujourdhui.getYear();*/

        List<Integer> anneeCourante = new ArrayList<>();
        LocalDate aujourdhui = LocalDate.now();
        for (int i = 0; i <= 5; i++) {
            anneeCourante.add(aujourdhui.minusYears(i).getYear());
        }

        cbAnnee = new ComboBox<>();
        observableListAnnee = FXCollections.observableArrayList(anneeCourante);
        cbAnnee.setItems(observableListAnnee);

        Button bValider = new Button("Valider");

        bValider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    rafraichir();
                } catch (ConnexionException e) {
                    e.printStackTrace();
                }
            }
        });

        /*String matricule = cbVisiteurs.getValue().getMatricule();
        int mois = cbMois.getValue().ordinal();
        mois = mois + 1;
        int annee = cbAnnee.getValue();

        rapportVisites = ModeleGsbRv.getRapportVisite(matricule, mois, annee);
        observableListRapportVisite = FXCollections.observableArrayList(rapportVisites);*/

        TableColumn<RapportVisite, Integer> colNumero = new TableColumn<RapportVisite, Integer>("Numero");
        TableColumn<RapportVisite,String> colNom = new TableColumn<RapportVisite,String>("Nom");
        TableColumn<RapportVisite,String> colVille = new TableColumn<RapportVisite,String>("Ville");
        TableColumn<RapportVisite,LocalDate> colDateVisite = new TableColumn<RapportVisite, LocalDate>("Visite");
        TableColumn<RapportVisite,LocalDate> colDateRedaction = new TableColumn<RapportVisite, LocalDate>("Redaction");

        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tableView.getColumns().add(colNumero);

       colNom.setCellValueFactory(
               new Callback<TableColumn.CellDataFeatures<RapportVisite, String>, ObservableValue<String>>() {
                   @Override
                   public ObservableValue<String> call(TableColumn.CellDataFeatures<RapportVisite, String> param) {
                       String nom = param.getValue().getLePratiecien().getNom();
                       return new SimpleStringProperty(nom);
                   }
               }
       );
        tableView.getColumns().add(colNom);

        colVille.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<RapportVisite, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<RapportVisite, String> param) {
                        String ville = param.getValue().getLePratiecien().getVille();
                        return new SimpleStringProperty(ville);
                    }
                }
        );
        tableView.getColumns().add(colVille);

        colDateVisite.setCellValueFactory(new PropertyValueFactory<>("dateVisite"));
        colDateVisite.setCellFactory(
                colonne -> {
                    return new TableCell<RapportVisite,LocalDate>(){
                        @Override
                        protected void updateItem (LocalDate item, boolean empty){
                            super.updateItem(item, empty);

                            if (empty){
                                setText("");
                            }
                            else{
                                DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                                setText(item.format(formateur));
                            }
                        }
                    };
                }
        );
        tableView.getColumns().add(colDateVisite);

        colDateRedaction.setCellValueFactory(new PropertyValueFactory<>("dateRedaction"));
        colDateRedaction.setCellFactory(
                colonne -> {
                    return new TableCell<RapportVisite,LocalDate>(){
                        @Override
                        protected void updateItem (LocalDate item, boolean empty){
                            super.updateItem(item, empty);

                            if (empty){
                                setText("");
                            }
                            else{
                                DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                                setText(item.format(formateur));
                            }
                        }
                    };
                }
        );
        tableView.getColumns().add(colDateRedaction);

        tableView.setRowFactory(

                ligne -> {
                    return new TableRow<RapportVisite>(){
                        @Override
                        protected void updateItem(RapportVisite item, boolean empty){
                            super.updateItem(item, empty);

                            if(item != null){
                                if(item.isLu()){
                                    setStyle("-fx-background-color: gold");
                                }
                                else{
                                    setStyle( "-fx-background-color: cyan");
                                }
                            }
                        }
                    };
                }
        );

        tableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2){
                try {
                    int indiceRapport = tableView.getSelectionModel().getSelectedIndex();
                    String matricule = cbVisiteurs.getValue().getMatricule();
                    RapportVisite numeroRap = tableView.getSelectionModel().getSelectedItem();
                    ModeleGsbRv.setRapportVisiteLu(matricule, numeroRap.getNumero());

                    this.rafraichir();
                } catch (ConnexionException e) {
                    e.printStackTrace();
                }
            }
        });

        tableView.setItems(observableListRapportVisite);
        //this.rafraichir();

        HBox hbox1= new HBox(cbVisiteurs,cbMois,cbAnnee);
        HBox hbox2= new HBox(bValider);

        Label textRapports = new Label();
        textRapports.setText("Rapports");
        vbox = new VBox(textRapports,hbox1,hbox2,tableView);
        vbox.setStyle("-fx-background-color: white");

        return vbox;
    }
    public void rafraichir() throws ConnexionException {
        String matricule = cbVisiteurs.getValue().getMatricule();
        int mois = cbMois.getValue().ordinal();
        mois = mois + 1;
        int annee = cbAnnee.getValue();

        rapportVisites = ModeleGsbRv.getRapportVisite(matricule, mois, annee);
        observableListRapportVisite = FXCollections.observableArrayList(rapportVisites);
        tableView.setItems(observableListRapportVisite);

        /*for (RapportVisite unRapportVisite : rapportVisites){
            System.out.println(unRapportVisite);
        }*/
    }

}
