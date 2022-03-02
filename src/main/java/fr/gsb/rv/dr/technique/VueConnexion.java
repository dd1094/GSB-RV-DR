package fr.gsb.rv.dr.technique;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;
import javafx.scene.control.ButtonType.*;
import javafx.scene.control.ButtonBar.ButtonData;

import java.sql.PreparedStatement;
import java.util.Optional;


public class VueConnexion {
    Dialog<Pair<String, String>> dialog = new Dialog<>();

    public VueConnexion() {
        dialog.setTitle("Connexion");
        dialog.setHeaderText("Entrez vos identifiants");
        
        TextField matricule = new TextField();
        PasswordField mdp = new PasswordField();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Matricule"), 0, 0);
        grid.add(matricule, 1, 0);
        grid.add(new Label("Mot de passe"), 0, 1);
        grid.add(mdp, 1, 1);

        dialog.getDialogPane().setContent(grid);

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, cancelButtonType);


        dialog.setResultConverter(
                new Callback<ButtonType, Pair<String, String>>() {
                    @Override
                    public Pair<String, String> call(ButtonType typeBouton) {
                        if (typeBouton == loginButtonType) {
                            Pair<String, String> pair = new Pair<String, String>(matricule.getText(), mdp.getText());
                            return pair;
                        }
                        return null;
                    }
                });


    }


    public Optional<Pair<String, String>> showAndWait() {
        Optional<Pair<String, String>> reponse = dialog.showAndWait();
        return reponse;
    }


}

