package com.example.laborator4;

import android.os.Parcel;
import android.os.Parcelable;

// import java.io.Serializable;

// Lab5: "implements Serializable" = obiectul poate fi "impachetat" si pus intr-un Bundle,
// ca sa poata fi trimis de la o activitate la alta

// Lab6: am schimbat din "implements Serializable" in "implements Parcelable"
// Parcelable face acelasi lucru (impacheteaza obiectul), dar e specific Android si mai rapid
public class Lacat implements Parcelable {
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

    // Lab6 - Parcelable, constructor care recreeaza obiectul din Parcel
    // atributele se citesc in aceeasi ordine in care au fost scrise in writeToParcel
    protected Lacat(Parcel in) {
        marca = in.readString();
        culoare = in.readString();
        rezistentApa = in.readByte() != 0;   // boolean-ul a fost scris ca byte (1 sau 0)
        tip = in.readString();
        pret = in.readInt();
        cuCifru = in.readByte() != 0;
        nivelSiguranta = in.readFloat();
    }

    // Lab6 - Parcelable: scrie atributele in Parcel, impachetarea obiectului
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(culoare);
        dest.writeByte((byte) (rezistentApa ? 1 : 0));   // boolean -> byte
        dest.writeString(tip);
        dest.writeInt(pret);
        dest.writeByte((byte) (cuCifru ? 1 : 0));
        dest.writeFloat(nivelSiguranta);
    }

    // Lab6
    @Override
    public int describeContents() {
        return 0;
    }

    // Lab6 - Android creeaza obiectul la primire
    public static final Creator<Lacat> CREATOR = new Creator<Lacat>() {
        @Override
        public Lacat createFromParcel(Parcel in) {
            return new Lacat(in);   // foloseste constructorul cu Parcel de mai sus
        }

        @Override
        public Lacat[] newArray(int size) {
            return new Lacat[size];
        }
    };

    // Lab6 - getteri: ii folosim in adapter (afisare) si la modificare (completam formularul)
    public String getMarca() {
        return marca;
    }

    public String getCuloare() {
        return culoare;
    }

    public boolean isRezistentApa() {
        return rezistentApa;
    }

    public String getTip() {
        return tip;
    }

    public int getPret() {
        return pret;
    }

    public boolean isCuCifru() {
        return cuCifru;
    }

    public float getNivelSiguranta() {
        return nivelSiguranta;
    }

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