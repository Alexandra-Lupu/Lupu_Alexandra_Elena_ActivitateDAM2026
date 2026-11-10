package com.example.simularetest2;

import java.io.Serializable;

public class Telefon implements Serializable {
    private String model; //edittext
    private int ram; //spinner
    private int memorie_interna; //radiogroup
    private boolean amprenta; //checkbox

    public Telefon()
    {

    }
    public Telefon(String model, int ram, int memorie_interna, boolean amprenta) {
        this.model = model;
        this.ram = ram;
        this.memorie_interna = memorie_interna;
        this.amprenta = amprenta;
    }

    @Override
    public String toString() {
        return "Telefon{" +
                "model='" + model + '\'' +
                ", ram=" + ram +
                ", memorie_interna=" + memorie_interna +
                ", amprenta=" + amprenta +
                '}';
    }

    public void setMemorie_interna(int memorie_interna) {
        this.memorie_interna = memorie_interna;
    }

    public int getMemorie_interna() {
        return memorie_interna;
    }
}
