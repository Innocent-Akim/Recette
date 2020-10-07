/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.autre;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static lib.autre.Cls_connection.Connexion;

/**
 *
 * @author Akim
 */
public class Cls_enregistrer {

    private CallableStatement call;
    private PreparedStatement pst;

    public boolean EnregistreAll(Object ob) throws SQLException {
        boolean bool = false;
        if (ob instanceof Cls_factory) {
            Cls_factory CF = (Cls_factory) ob;
            call = Connexion().prepareCall("Exec PsApprovission ?,?,?,?,?,?,?");
            call.setString(1, CF.getDesignation());
            call.setDouble(2, CF.getQte());
            call.setDouble(3, CF.getPv());
            call.setDouble(4, CF.getPa());
            call.setString(5, CF.getAgence());
            call.setInt(6, CF.getCodepp());
            call.setInt(7, CF.getEtat());
            call.executeUpdate();
            bool = true;
        } else if (ob instanceof Cls_Agence) {
            Cls_Agence Ag = (Cls_Agence) ob;
            call = Connexion().prepareCall("Exec PsSave ?,?,?");
            call.setString(1, Ag.getDescription());
            call.setString(2, Ag.getAdresse());
            call.setInt(3, 0);
            call.executeUpdate();
            bool = true;
        } else if (ob instanceof Cls_produit) {
            Cls_produit prod = (Cls_produit) ob;
            call = Connexion().prepareCall("Exec PsSave ?,?,?");
            call.setString(1, prod.getDesignation());
            call.setString(2, prod.getUnite());
            call.setInt(3, 1);
            call.executeUpdate();
            bool = true;
        } else if (ob instanceof Cls_enter) {
            Cls_enter entete = (Cls_enter) ob;
            call = Connexion().prepareCall("{Call FsEnteteAppro (?)}");
            call.setString(1, entete.getDate());
            call.executeUpdate();
            System.out.println(entete.getDate());
            bool = true;
        } else if (ob instanceof Cls_agent) {
            Cls_agent agent = (Cls_agent) ob;
            call = Connexion().prepareCall("{call PsAgentInsert (?,?,?,?,?,?,?)}");
            call.setString(1, agent.getNom());
            call.setString(2, agent.getPostnom());
            call.setString(3, agent.getPrenom());
            call.setString(4, agent.getPwd());
            call.setString(5, agent.getNomUtilisateur());
            call.setInt(7, agent.getEtat());
            call.setString(6, agent.getGenre());
            call.executeUpdate();
            bool = true;
        }
        return bool;
    }

}
