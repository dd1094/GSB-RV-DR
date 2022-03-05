package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {
        public static Visiteur seConnecter( String matricule, String mdp) throws ConnexionException{

            Connection connexion = ConnexionBD.getConnexion() ;

            String requete = "select vis_nom, vis_prenom " +
                    "from Visiteur " +
                    "where vis_matricule = ?" +
                    "and vis_mdp = ?" ;

            try {
                PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
                requetePreparee.setString( 1 , matricule );
                requetePreparee.setString( 2 , mdp );
                ResultSet resultat = requetePreparee.executeQuery() ;
                if( resultat.next() ){
                    Visiteur visiteur = new Visiteur() ;
                    visiteur.setMatricule( matricule );
                    visiteur.setNom( resultat.getString( "vis_nom" ) ) ;
                    visiteur.setPrenom( resultat.getString( "vis_prenom" ) );
                    requetePreparee.close() ;
                    return visiteur ;
                }
                else {
                    return null ;
                }
            }
            catch( Exception e ){
                return null ;
            }
        }

        public static List<Praticien> getPraticiensHesistants() throws ConnexionException{
            Connection connexion = ConnexionBD.getConnexion();
            List<Praticien> listePraticiens = new ArrayList<>();

            String requete = "SELECT vis_matricule, pra_coefnotoriete, pra_ville, R.pra_num, pra_nom, rap_num, rap_date_visite, rap_bilan, rap_coefconfiance, rap_date_saisie, rap_motif,pra_nom, pra_prenom, R.pra_num \n" +
                    "from RapportVisite as R \n" +
                    "INNER JOIN Praticien as P \n" +
                    "on R.pra_num = P.pra_num\n" +
                    "where rap_date_visite in(\n" +
                    "                        select max(rap_date_visite)    \n" +
                    "                        from RapportVisite \n" +
                    "                        group by pra_num\n" +
                    "                        )" +
                    "and rap_coefconfiance < 5;";


            try {
                PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
                ResultSet resultat = requetePreparee.executeQuery() ;
                while (resultat.next()){
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
            }

            catch( Exception e ){
                return null ;
            }
        }
    }

