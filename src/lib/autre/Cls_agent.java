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
public class Cls_agent {

    private String Nom;
    private String Postnom;
    private String Prenom;
    private String Pwd;
    private String NomUtilisateur;
    private String Genre;
    private int Etat;

    public Cls_agent(String Nom, String Postnom, String Prenom, String Pwd, String NomUtilisateur, String Genre, int Etat) {
        this.Nom = Nom;
        this.Postnom = Postnom;
        this.Prenom = Prenom;
        this.Pwd = Pwd;
        this.NomUtilisateur = NomUtilisateur;
        this.Genre = Genre;
        this.Etat = Etat;
    }
    
    

    /**
     * @return the Nom
     */
    public String getNom() {
        return Nom;
    }

    /**
     * @param Nom the Nom to set
     */
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    /**
     * @return the Postnom
     */
    public String getPostnom() {
        return Postnom;
    }

    /**
     * @param Postnom the Postnom to set
     */
    public void setPostnom(String Postnom) {
        this.Postnom = Postnom;
    }

    /**
     * @return the Prenom
     */
    public String getPrenom() {
        return Prenom;
    }

    /**
     * @param Prenom the Prenom to set
     */
    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    /**
     * @return the Pwd
     */
    public String getPwd() {
        return Pwd;
    }

    /**
     * @param Pwd the Pwd to set
     */
    public void setPwd(String Pwd) {
        this.Pwd = Pwd;
    }

    /**
     * @return the NomUtilisateur
     */
    public String getNomUtilisateur() {
        return NomUtilisateur;
    }

    /**
     * @param NomUtilisateur the NomUtilisateur to set
     */
    public void setNomUtilisateur(String NomUtilisateur) {
        this.NomUtilisateur = NomUtilisateur;
    }

    /**
     * @return the Genre
     */
    public String getGenre() {
        return Genre;
    }

    /**
     * @param Genre the Genre to set
     */
    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    /**
     * @return the Etat
     */
    public int getEtat() {
        return Etat;
    }

    /**
     * @param Etat the Etat to set
     */
    public void setEtat(int Etat) {
        this.Etat = Etat;
    }
}
