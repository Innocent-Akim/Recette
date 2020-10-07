/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.autre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static lib.autre.Cls_preference1.*;

/**
 *
 * @author Akim
 */
public class Cls_connection {

    private static Connection cnx;

    private Cls_connection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnx = DriverManager.getConnection(localhost(), login1(), pwd1());
        } catch (SQLException | ClassNotFoundException ex) {
            Cls_autrer.alerteErreur("ERROR", ex.getMessage());
        }
    }

    public static Connection Connexion() {
        if (cnx == null) {
            new Cls_connection();
            System.out.println("Connection n'exister pas !!!");
        }
        return cnx;

    }

}
