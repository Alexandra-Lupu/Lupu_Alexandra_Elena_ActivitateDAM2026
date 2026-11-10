package com.example.simularetest;

import java.io.Serializable;

public class Album implements Serializable {
    private String titlu;
    private int an;
    private float pret;
    private String gen;
    private boolean vinil;
    private float rating;

    public Album(String titlu, int an, float pret, String gen, boolean vinil, float rating) {
        this.titlu = titlu;
        this.an = an;
        this.pret = pret;
        this.gen = gen;
        this.vinil = vinil;
        this.rating = rating;
    }

    public Album()
    {

    }

    @Override
    public String toString() {
        return "Album{" +
                "titlu='" + titlu + '\'' +
                ", an=" + an +
                ", pret=" + pret +
                ", gen='" + gen + '\'' +
                ", vinil=" + vinil +
                ", rating=" + rating +
                '}';
    }
}
