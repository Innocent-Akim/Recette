/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import appsoft.DeclancheurController;
import static appsoft.DeclancheurController.Ecran1;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import static gui.InterfaceApprovisionnementController.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import lib.autre.Cls_autrer;
import static lib.autre.Cls_autrer.onTableClick;
import lib.autre.Cls_connection;
import static lib.autre.Cls_connection.Connexion;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Infarce_appro_DetailController implements Initializable {

    Cls_autrer autre;
    appsoft.DeclancheurController decla;

    @FXML
    private TableView<String> tabAppro;
    @FXML
    private JFXButton Btnajout;
    public static TableView<String> tb;
    @FXML
    private TextField TfdSearch;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        autre = new Cls_autrer();

        init();
    }

    void init() {
        try {
            autre.chargerTable("PsAfAppro", tabAppro, 0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        tb = tabAppro;
    }

    @FXML
    private void traitementBtn(ActionEvent event) throws IOException {
        if (event.getSource() == Btnajout) {
            dialogue(DeclancheurController.Ecran1, "InterfaceApprovisionnement.fxml");
        }
    }

    public void dialogue(StackPane stp, String fichierFXML) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fichierFXML));
        JFXDialogLayout dl = new JFXDialogLayout();
        dl.setBody(root);
        JFXDialog dialog = new JFXDialog(stp, dl, JFXDialog.DialogTransition.CENTER);
        dialog.show(stp);
    }

    @FXML
    private void SercherAll(KeyEvent event) {
        try {
            autre.chargerTable("PsApprovisionnement'" + TfdSearch.getText() + "'", tabAppro, 0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    Statement stm;
    ResultSet rs;

    @FXML
    private void MenuModifier(ActionEvent event) throws IOException {
        try {
            stm = Cls_connection.Connexion().createStatement();
            rs = stm.executeQuery("SELECT * FROM VsApprovisionnement WHERE NUMERO='" + onTableClick(tb).get(0) + "'");
            if (rs.next()) {
                dialogue(DeclancheurController.Ecran1, "InterfaceApprovisionnement.fxml");
                TfdArticle1.setText(rs.getString("ARTICLE"));
                TfdPA1.setText(rs.getString("PA"));
                TfdQte1.setText(rs.getString("QUANTITE"));
                TfdPv1.setText(rs.getString("PV"));
                CbxAgence1.setValue(rs.getString("AGENCE"));
                BtnAjouter1.setText("Modifier");
                etat = rs.getInt("NUMERO");
                codeAppro1.setText(rs.getString("ENTETEAPPRO"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Infarce_appro_DetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void MenuSuppression(ActionEvent event) {
        try {
            stm =Connexion().createStatement();
            stm.executeUpdate("DELETE TDetailApp WHERE CODE='" + onTableClick(tb).get(0) + "'");
            init();
            Cls_autrer.alerteInformation("Information", "Suppression reussi");
        } catch (SQLException ex) {
            Logger.getLogger(Infarce_appro_DetailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void MenuAnnuler(ActionEvent event) throws IOException {
//        dialogue(Ecran1, "InterfaceApprovisionnement.fxml");
    }

}
