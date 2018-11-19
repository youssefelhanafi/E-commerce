package mps.google.com.ecom.entities;

/**
 * Created by famille on 5/1/2018.
 */

public class Commande {
    private int idCommande;
    private String adresse_livraison;
    private String date_commande;
    private int idLivreur;
    private int idClient;
    private int Etat;

    public Commande(int idCommande, String adresse_livraison, String date_commande, int idLivreur, int idClient, int etat) {
        this.idCommande = idCommande;
        this.adresse_livraison = adresse_livraison;
        this.date_commande = date_commande;
        this.idLivreur = idLivreur;
        this.idClient = idClient;
        Etat = etat;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public int getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(int idLivreur) {
        this.idLivreur = idLivreur;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getEtat() {
        return Etat;
    }

    public void setEtat(int etat) {
        Etat = etat;
    }
}
