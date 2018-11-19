package mps.google.com.ecom.entities;

/**
 * Created by famille on 4/30/2018.
 */

public class LigneCmd {
    private Produit produit;
    private int qte;

    public LigneCmd(Produit produit, int qte) {
        this.produit = produit;
        this.qte = qte;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
