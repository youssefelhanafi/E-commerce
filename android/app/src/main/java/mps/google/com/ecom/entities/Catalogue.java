package mps.google.com.ecom.entities;

import java.io.Serializable;

/**
 * Created by famille on 3/11/2018.
 */

public class Catalogue implements Serializable{
    private int idCat;
    private String nomCat;
    private String dateCat;
    private String imgCat;

    public Catalogue(int idCat, String nomCat, String dateCat, String imgCat) {
        this.idCat = idCat;
        this.nomCat = nomCat;
        this.dateCat = dateCat;
        this.imgCat = imgCat;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public String getDateCat() {
        return dateCat;
    }

    public void setDateCat(String dateCat) {
        this.dateCat = dateCat;
    }

    public String getImgCat() {
        return imgCat;
    }

    public void setImgCat(String imgCat) {
        this.imgCat = imgCat;
    }

    @Override
    public String toString() {
        return "Catalogue{" +
                "idCat=" + idCat +
                ", nomCat='" + nomCat + '\'' +
                ", dateCat='" + dateCat + '\'' +
                ", imgCat='" + imgCat + '\'' +
                '}';
    }
}
