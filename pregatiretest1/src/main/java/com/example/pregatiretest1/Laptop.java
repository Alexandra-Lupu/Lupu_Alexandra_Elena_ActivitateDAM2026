package com.example.pregatiretest1;

import java.io.Serializable;

public class Laptop implements Serializable {
    private String marca;
    private float pret;
    private int nr_nuclee;
    private boolean touchscreen;

    public Laptop()
    {

    }

    public Laptop(String marca, float pret, int nr_nuclee, boolean touchscreen) {
        this.marca = marca;
        this.pret = pret;
        this.nr_nuclee = nr_nuclee;
        this.touchscreen = touchscreen;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "marca='" + marca +
                ", pret=" + pret +
                ", nr_nuclee=" + nr_nuclee +
                ", touchscreen=" + touchscreen +
                '}';
    }
}
