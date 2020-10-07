/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import com.jfoenix.controls.JFXButton;
import gui.Interface_settingsController;
import static gui.Interface_settingsController.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lib.autre.Cls_autrer;
import lib.autre.Cls_produit;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class DialogueAjouterarticleController implements Initializable {

    @FXML
    private TextField Tdfdesignation;
    @FXML
    private TextField Tfdunite;
    @FXML
    private JFXButton BtnAjouter;
    lib.autre.Cls_produit list;
    lib.autre.Cls_autrer aute;
    gui.Interface_settingsController settings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        settings = new Interface_settingsController();
        aute = new Cls_autrer();
        Tdfdesignation.setText(designation);
        Tfdunite.setText(unite);
        BtnAjouter.setText(nombtn);
    }

    @FXML
    private void AllsourceEnregistrer(ActionEvent event) throws SQLException, Exception {
        System.out.append("" + code1);
        if (aute.SetValeur("UPDATE TListProduit SET designation='" + Tdfdesignation.getText() + "', unite='" + Tfdunite.getText() + "' WHERE code=" + code1 + "")) {
            Cls_autrer.alerteInformation("Notification", "Modification reussi !!!");
            Tdfdesignation.setText(null);
            Tfdunite.setText(null);
            new Cls_autrer().chargerTable("SELECT Code,Designation,Unite FROM TListProduit WHERE bool=1", tbArticle1, 0);
        }
    }

    @FXML
    private void Alltraitement(ActionEvent event) {
    }

}
