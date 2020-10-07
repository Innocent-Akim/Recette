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
public class Cls_factory {

    private String agence;
    private int Codepp;
    private String Designation;
    private double qte;
    private double pv;
    private double pa;
    private int Etat;

    public Cls_factory(String agence, int Codepp, String Designation, double qte, double pv, double pa, int Etat) {
        this.agence = agence;
        this.Codepp = Codepp;
        this.Designation = Designation;
        this.qte = qte;
        this.pv = pv;
        this.pa = pa;
        this.Etat = Etat;
    }

    public int getEtat() {
        return Etat;
    }

    public void setEtat(int Etat) {
        this.Etat = Etat;
    }

    public Cls_factory(String agence, int Codepp, String Designation, double qte, double pv, double pa) {
        this.agence = agence;
        this.Codepp = Codepp;
        this.Designation = Designation;
        this.qte = qte;
        this.pv = pv;
        this.pa = pa;
    }

    public Cls_factory() {
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getPv() {
        return pv;
    }

    public void setPv(double pv) {
        this.pv = pv;
    }

    public double getPa() {
        return pa;
    }

    public void setPa(double pa) {
        this.pa = pa;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public int getCodepp() {
        return Codepp;
    }

    public void setCodepp(int Codepp) {
        this.Codepp = Codepp;
    }

}
