package fr.gsb.rv.dr.entites;

import java.time.LocalDate;
import java.util.Date;

public class Praticien {
    private int numero;
    private String nom;
    private String ville;
    private double coefNotoriete;
    private LocalDate dateDerniereVisite;
    private int dernierCoefConfiance;

    public Praticien(int numero, String nom, String ville, double coefNotoriete, LocalDate dateDerniereVisite, int dernierCoefConfiance){
        this.numero = numero;
        this.nom = nom;
        this.ville = ville;
        this.coefNotoriete = coefNotoriete;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    public Praticien(int numero, String nom, String ville){
        this.numero = numero;
        this.nom = nom;
        this.ville = ville;;
    }
    public Praticien(){}

    public String getNom(){
        return nom;
    }

    public String getVille(){
        return ville;
    }

    public int getNumero(){
        return numero;
    }

    public int getDernierCoefConfiance(){
        return dernierCoefConfiance;
    }

    public double getCoefNotoriete(){
        return coefNotoriete;
    }

    public LocalDate getDateDerniereVisite(){
        return dateDerniereVisite;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCoefNotoriete(double coefNotoriete) {
        this.coefNotoriete = coefNotoriete;
    }

    public void setDateDerniereVisite(LocalDate dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }
    public void setDernierCoefConfiance(int dernierCoefConfiance) {
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    @Override
    public String toString() {
        return "Visiteur{" +
                "numero = '" + numero+ '\'' +
                ", nom = '" + nom + '\'' +
                ", ville = '" + ville+ '\'' +
                ", coefNotoriete = '" + coefNotoriete+ '\''+
                ", coefConfiance = '" + dernierCoefConfiance+ '\''+
                ", date de dernière visiste = '" + dateDerniereVisite+ '\''+
                '}';
    }


}
