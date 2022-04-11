package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {
    public static Visiteur seConnecter(String matricule, String mdp) throws ConnexionException {

        Connection connexion = ConnexionBD.getConnexion();

        String requete = "SELECT vis_nom, vis_prenom "
                + "FROM Travailler as t "
                + "INNER JOIN ( SELECT tra_role, vis_matricule, MAX(jjmmaa) as jjmmaa FROM Travailler GROUP BY vis_matricule) as u "
                + "INNER JOIN Visiteur as v "
                + "ON t.vis_matricule = u.vis_matricule "
                + "AND t.jjmmaa = u.jjmmaa "
                + "AND t.vis_matricule = v.vis_matricule "
                + "WHERE t.tra_role = 'Délégué' AND v.vis_matricule = ? AND v.vis_mdp = ? ";

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            requetePreparee.setString(1, matricule);
            requetePreparee.setString(2, mdp);
            ResultSet resultat = requetePreparee.executeQuery();
            if (resultat.next()) {
                Visiteur visiteur = new Visiteur();
                visiteur.setMatricule(matricule);
                visiteur.setNom(resultat.getString("vis_nom"));
                visiteur.setPrenom(resultat.getString("vis_prenom"));
                requetePreparee.close();
                return visiteur;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Praticien> getPraticiensHesistants() throws ConnexionException {
        Connection connexion = ConnexionBD.getConnexion();
        List<Praticien> listePraticiens = new ArrayList<>();

        String requete = "SELECT vis_matricule, pra_coefnotoriete, pra_ville, R.pra_num, pra_nom, rap_num, rap_date_visite, rap_bilan, rap_coefconfiance, rap_date_saisie, rap_motif,pra_nom, pra_prenom, R.pra_num \n" +
                "from RapportVisite as R \n" +
                "INNER JOIN Praticien as P \n" +
                "on R.pra_num = P.pra_num\n" +
                "where rap_date_visite in(\n" +
                "    select max(rap_date_visite)    \n" +
                "    from RapportVisite \n" +
                "    group by pra_num\n" +
                "    )" +
                "and rap_coefconfiance < 5;";


        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            ResultSet resultat = requetePreparee.executeQuery();
            while (resultat.next()) {
                Praticien praticiens = new Praticien();
                praticiens.setNumero(resultat.getInt("R.pra_num"));
                praticiens.setNom(resultat.getString("pra_nom"));
                praticiens.setVille(resultat.getString("pra_ville"));
                praticiens.setCoefNotoriete(resultat.getDouble("pra_coefnotoriete"));
                praticiens.setDateDerniereVisite(resultat.getDate("rap_date_visite").toLocalDate());
                praticiens.setDernierCoefConfiance(resultat.getInt("rap_coefconfiance"));
                listePraticiens.add(praticiens);
            }
            return listePraticiens;
        } catch (Exception e) {
            return null;
        }

    }

    public static List<Visiteur> getVisiteur() throws ConnexionException {
        Connection connexion = ConnexionBD.getConnexion();
        List<Visiteur> ListeVisiteur = new ArrayList<>();

        String requete = "select * from Visiteur";

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            ResultSet resultat = requetePreparee.executeQuery();
            while (resultat.next()) {
                Visiteur visiteur = new Visiteur();
                visiteur.setMatricule(resultat.getString("vis_matricule"));
                visiteur.setNom(resultat.getString("vis_nom"));
                visiteur.setPrenom(resultat.getString("vis_prenom"));
                ListeVisiteur.add(visiteur);
            }
            return ListeVisiteur;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<RapportVisite> getRapportVisite( String matricule, int mois, int annee) throws ConnexionException {
        Connection connexion = ConnexionBD.getConnexion();
        List<RapportVisite> ListeRapportVisite = new ArrayList<>();
        List<Praticien> ListePraticien = new ArrayList<>();

        String requete = "select * from RapportVisite as r\n" +
                "INNER join Praticien as p\n" +
                "on r.pra_num = p.pra_num \n" +
                "where vis_matricule = ?\n" +
                "and month(rap_date_visite) = ?\n" +
                "and year(rap_date_visite) = ? \n" +
                "order by rap_date_visite";

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            requetePreparee.setString(1, matricule);
            requetePreparee.setInt(2, mois);
            requetePreparee.setInt(3, annee);
            ResultSet resultat = requetePreparee.executeQuery();
            while (resultat.next()) {

                Praticien praticiens = new Praticien();
                praticiens.setNumero(resultat.getInt("R.pra_num"));
                praticiens.setNom(resultat.getString("pra_nom"));
                praticiens.setVille(resultat.getString("pra_ville"));
                praticiens.setCoefNotoriete(resultat.getDouble("pra_coefnotoriete"));
                praticiens.setDateDerniereVisite(resultat.getDate("rap_date_visite").toLocalDate());
                praticiens.setDernierCoefConfiance(resultat.getInt("rap_coefconfiance"));
                ListePraticien.add(praticiens);

                RapportVisite rapportVisite = new RapportVisite();
                rapportVisite.setLePratiecien(praticiens);
                rapportVisite.setNumero(resultat.getInt("rap_num"));
                rapportVisite.setDateVisite(resultat.getDate("rap_date_visite").toLocalDate());
                rapportVisite.setDateRedaction(resultat.getDate("rap_date_saisie").toLocalDate());
                rapportVisite.setBilan(resultat.getString("rap_bilan"));
                rapportVisite.setMotif(resultat.getString("rap_motif"));
                rapportVisite.setCoefConfiance(resultat.getInt("rap_coefconfiance"));
                rapportVisite.setLu(resultat.getBoolean("rap_lu"));
                ListeRapportVisite.add(rapportVisite);


            }
            return ListeRapportVisite;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public static void setRapportVisiteLu(String matricule, int numRapport) throws ConnexionException {
        Connection connexion = ConnexionBD.getConnexion();

        String requete="UPDATE RapportVisite\n" +
                "SET rap_lu = 1\n" +
                "WHERE vis_matricule = ?\n" +
                "and rap_num = ?";
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            requetePreparee.setString(1, matricule);
            requetePreparee.setInt(2, numRapport);
            ResultSet resultat = requetePreparee.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void setRapportVisite() {
    }
}

