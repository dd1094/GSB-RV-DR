package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class VueRapportVisite {

    Dialog<RapportVisite> dialog = new Dialog<>();

    public VueRapportVisite(RapportVisite rapportVisite){

        DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");

        dialog.setTitle("Rapport de visite");
        dialog.setHeaderText("Détails du rapport de visite");

        //TextField matricule = new TextField();

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets( 20, 150, 10, 10));


        Text numero  = new Text(String.valueOf(rapportVisite.getNumero()));
        //String matricule = Integer.toString(numero);
        grid.add(new Label("Numéro du rapport de Visite :"), 0, 0);
        grid.add(numero, 1, 0);

        Text nom  = new Text(rapportVisite.getLePratiecien().getNom());
        grid.add(new Label("Nom du praticien :"), 0, 1);
        grid.add(nom, 1, 1);

        Text ville  = new Text(rapportVisite.getLePratiecien().getVille());
        grid.add(new Label("Ville du praticien"), 0, 2);
        grid.add(ville, 1, 2);

        Text dateVisite = new Text(String.valueOf(rapportVisite.getDateVisite()));
        grid.add(new Label("Date de la visite : "), 0, 3);
        grid.add(dateVisite, 1, 3);

        Text dateRedaction = new Text(String.valueOf(rapportVisite.getDateRedaction()));
        grid.add(new Label("Date de rédaction : "), 0, 4);
        grid.add(dateRedaction, 1, 4);

        Text bilan = new Text(String.valueOf(rapportVisite.getBilan()));
        grid.add(new Label("Bilan : "), 0, 5);
        grid.add(bilan, 1, 5);

        Text motif = new Text(String.valueOf(rapportVisite.getMotif()));
        grid.add(new Label("Motif : "), 0, 6);
        grid.add(motif, 1, 6);

        dialog.getDialogPane().getButtonTypes().add(loginButtonType);

        /*grid.add(new Label(), 1, 0);
        grid.add(mdp, 1, 1);*/

        dialog.getDialogPane().setContent(grid);
    }
}
