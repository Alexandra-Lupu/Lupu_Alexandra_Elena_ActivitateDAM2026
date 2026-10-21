package com.example.laborator4;

import java.io.Serializable;

//  "implements Serializable" = obiectul poate fi "impachetat" si pus intr-un Bundle,
// ca sa poata fi trimis de la o activitate la alta
public class Lacat implements Serializable {
    private String marca;
    private String culoare;
    private boolean rezistentApa;
    private String tip;
    private int pret;
    private boolean cuCifru;
    private float nivelSiguranta;

    public Lacat()
    {

    }

    public Lacat(String marca, String culoare, boolean rezistentApa, String tip, int pret,
                 boolean cuCifru, float nivelSiguranta) {
        this.marca = marca;
        this.culoare = culoare;
        this.rezistentApa = rezistentApa;
        this.tip = tip;
        this.pret = pret;
        this.cuCifru = cuCifru;
        this.nivelSiguranta = nivelSiguranta;
    }

    // toString() transforma obiectul intr-un text, ca sa-l putem afisa in Toast
    @Override
    public String toString() {
        return "Lacat{" +
                "marca='" + marca + '\'' +
                ", culoare='" + culoare + '\'' +
                ", rezistentApa=" + rezistentApa +
                ", tip='" + tip + '\'' +
                ", pret=" + pret +
                ", cuCifru=" + cuCifru +
                ", nivelSiguranta=" + nivelSiguranta +
                '}';
    }
}
