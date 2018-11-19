package mps.google.com.ecom.entities;

import java.io.Serializable;

/**
 * Created by famille on 3/12/2018.
 */

public class Produit implements Serializable{
    private int idProduit;
    private String nom_produit;
    private String type_produit;
    private Double prix;
    private int idCatalogue;
    private String img;

    public Produit(int idProduit, String nom_produit, String type_produit, Double prix, int idCatalogue, String img) {
        this.idProduit = idProduit;
        this.nom_produit = nom_produit;
        this.type_produit = type_produit;
        this.prix = prix;
        this.idCatalogue = idCatalogue;
        this.img = img;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getType_produit() {
        return type_produit;
    }

    public void setType_produit(String type_produit) {
        this.type_produit = type_produit;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getIdCatalogue() {
        return idCatalogue;
    }

    public void setIdCatalogue(int idCatalogue) {
        this.idCatalogue = idCatalogue;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
