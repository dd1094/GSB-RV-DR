package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import javafx.geometry.Insets;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class VueRapportVisite {

    Dialog<RapportVisite> dialog = new Dialog<>();

    public VueRapportVisite(RapportVisite rapportVisite){
        dialog.setTitle("Rapport de visite");
        dialog.setHeaderText("Entrez vos identifiants");

        TextField matricule = new TextField();
        PasswordField mdp = new PasswordField();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new TextField(rapportVisite.getNumero()), 0, 0);
        grid.add(matricule, 1, 0);
        grid.add(new Label("Mot de passe"), 0, 1);
        grid.add(mdp, 1, 1);

        dialog.getDialogPane().setContent(grid);
    }
}
