package fr.gsb.rv.dr.entites;

import java.time.LocalDate;

public class RapportVisite {
    private int numero;
    private LocalDate dateVisite;
    private LocalDate dateRedaction;
    private String bilan;
    private String motif;
    private int coefConfiance;
    private boolean lu;

    public RapportVisite(int numero, LocalDate dateVisite, LocalDate dateRedaction, String bilan, String motif, int coefConfiance, boolean lu){
        this.numero = numero;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.bilan = bilan;
        this.motif = motif;
        this.coefConfiance = coefConfiance;
        this.lu = lu;
    }
    public RapportVisite(){}

    public int getNumero(){
        return numero;
    }
    public LocalDate getDateVisite(){
        return dateVisite;
    }
    public LocalDate getDateRedaction(){
        return dateRedaction;
    }
    public String getBilan(){
        return bilan;
    }
    public String getMotif(){
        return motif;
    }
    public int getCoefConfiance(){
        return coefConfiance;
    }
    public boolean getLu(){
        return lu;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDateVisite(LocalDate dateVisite) {
        this.dateVisite = dateVisite;
    }

    public void setDateRedaction(LocalDate dateRedaction) {
        this.dateRedaction = dateRedaction;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public void setCoefConfiance(int coefConfiance) {
        this.coefConfiance = coefConfiance;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }
}
