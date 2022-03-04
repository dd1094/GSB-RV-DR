package fr.gsb.rv.dr.entites;

import java.time.LocalDate;

public class Praticien {
    private String numero;
    private String nom;
    private String ville;
    private double coefNotoriete;
    private LocalDate dateDerniereVisite;
    private int dernierCoefConfiance;

    public Praticien(String numero, String nom, String ville, double coefNotoriete, LocalDate dateDerniereVisite, int dernierCoefConfiance){
        this.numero = numero;
        this.nom = nom;
        this.ville = ville;
        this.coefNotoriete = coefNotoriete;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
    }
    public Praticien(){}

    public String getNom(){
        return nom;
    }
}
