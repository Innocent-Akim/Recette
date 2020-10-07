/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.autre;

/**
 *
 * @author Akim
 */
public class Cls_produit {

    private String designation;
    private String unite;

    public Cls_produit(String designation, String unite) {
        this.designation = designation;
        this.unite = unite;
    }

    public Cls_produit() {
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

}
