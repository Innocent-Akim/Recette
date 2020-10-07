/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.autre;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 *
 * @author Akim
 */
public class Cls_preference1 {

    private static final Preferences pref = Preferences.userNodeForPackage(Cls_preference1.class);

    public static boolean RedKey(String url, String db, String login, String pwd) {
        boolean bool = false;
        pref.put("url", "jdbc:sqlserver://" + url + ";databaseName=" + db + "");
        pref.put("login", login);
        pref.put("pwd", pwd);
        try {
            bool = true;
            pref.exportNode(new FileOutputStream("configuration.xml"));
        } catch (IOException | BackingStoreException ex) {
            bool = false;
            System.out.println(ex.getMessage());
        }
        return bool;
    }

    public static String localhost() {
        String url = pref.get("url", "url_");
        return url;
    }

    public static String pwd1() {
        String pwd = pref.get("pwd", "pwd_");
        return pwd;
    }

    public static String login1() {
        String login = pref.get("login", "login_");
        return login;
    }

    public static boolean RedKeyExterne(String key, String Chemin) {
        boolean bool = false;
        try {
            pref.put(key, Chemin);
            pref.exportNode(new FileOutputStream("parametrer.xml"));
            bool = true;
        } catch (IOException | BackingStoreException e) {
            bool = false;
        }

        return bool;
    }

    public static String url_Barcode() {
        return pref.get("codebar", "root");
    }

    public static String url_print() {
        return pref.get("imprimerroot-", "");
    }

    public static String url_backup() {
        return pref.get("backup", "backup_");
    }

    public static float taux() {
        return pref.getFloat("taux", 1);
    }

    public static String nomBase() {
        return pref.get("DB", "DB_");
    }

    public static String TimeBackup() {
        return pref.get("heure", "heure");
    }

    public static String cheminDynamyc() {
        return pref.get("accespoint", "accespoint");
    }

}
