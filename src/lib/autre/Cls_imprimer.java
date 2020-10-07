package lib.autre;

import static lib.autre.Cls_autrer.alerteInformation;
import static lib.autre.Cls_connection.Connexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Akm
 */
public class Cls_imprimer {

    /**
     * *
     *
     * @param requete
     * @param url
     * @param position
     */
    public static void _impresion(String requete, String url, int position) {
        try {
            JasperDesign load = JRXmlLoader.load(url);
            JRDesignQuery jrdesign = new JRDesignQuery();
            jrdesign.setText(requete);
            load.setQuery(jrdesign);
            JasperReport jasper = JasperCompileManager.compileReport(load);
            JasperPrint print = JasperFillManager.fillReport(jasper, null, Connexion());
            switch (position) {
                case 1:
                    JasperPrintManager.printReport(print, false);
                    break;
                case 2:
                    JasperPrintManager.printReport(print, true);
                    break;
                case 3:
                    JasperViewer.viewReport(print, false);
                    break;
                case 4:
                    JasperViewer.viewReport(print, true);
                    break;
            }
        } catch (JRException ex) {
            alerteInformation("ERROR", ex.getMessage());
        }
    }

}
