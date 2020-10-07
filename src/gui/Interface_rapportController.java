/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import lib.autre.Cls_autrer;
import static lib.autre.Cls_autrer.datebut;
import static lib.autre.Cls_autrer.onTableClick;
import static lib.autre.Cls_imprimer._impresion;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_rapportController implements Initializable {

    @FXML
    private ComboBox<String> CbxAgence;
    @FXML
    private TableView<String> tab;
    Cls_autrer autre;
    @FXML
    private JFXButton btnPrint;
    @FXML
    private Label code;
    @FXML
    private Label debut1;
    @FXML
    private Label fin1;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker datefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();
        dateDebut.setValue(LocalDate.now());
        datefin.setValue(LocalDate.now());
    }

    void init() {
        try {
            autre = new Cls_autrer();
            autre.chargerTable("PsAfAppro", tab, 0);
            autre.charger_ComboBox(CbxAgence, "Select Description From TAgence");
        } catch (SQLException ex) {
            Logger.getLogger(Interface_rapportController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Interface_rapportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ChargeTab(ActionEvent event) {
        try {
            autre.chargerTable("PsRapport '" + CbxAgence.getValue() + "'", tab, 0);
        } catch (Exception ex) {
            Logger.getLogger(Interface_rapportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    int x = 0;

    @FXML
    private void Allprint(ActionEvent event) throws SQLException {
//        if (event.getSource() == btnPrint) {
//            if (!(code.getText().equals("0") || CbxAgence.getValue().equals("") || debut1.getText().equals("") || fin1.getText().equals(""))) {
//                _impresion("PsPrint " + code.getText() + ",'" + CbxAgence.getValue() + "','" + debut1.getText() + "','" + fin1.getText() + "'", autre.Str("Select url x From turl") + "report1.jrxml", 3);
//            } else {
//                Cls_autrer.alerteInformation("Notification", "Selectionner dans le tableau");
//            }
//        }
    }

    @FXML
    private void clicTb(MouseEvent event) {
        code.setText(onTableClick(tab).get(8));
        fin1.setText(onTableClick(tab).get(6));
        debut1.setText(onTableClick(tab).get(6));
    }

    @FXML
    private void imprimerTous(ActionEvent event) throws SQLException {
        _impresion("SELECT * FROM RAPPORTOK", autre.Str("Select url x From turl") + "rapport_print.jrxml", 3);
    }

    @FXML
    private void imprimerDateAgence(ActionEvent event) throws SQLException {
        if (!(dateDebut.getValue().equals("") || datefin.getValue().equals("") || CbxAgence.getValue().equals(""))) {
            _impresion("SELECT * FROM RAPPORTOK WHERE (AGENCE ='" + CbxAgence.getValue() + "') AND DATE BETWEEN '" + datebut(dateDebut) + "' AND '" + datebut(datefin) + "'", autre.Str("Select url x From turl") + "rapport_print.jrxml", 3);
        } else {
            Cls_autrer.alerteErreur("Notification", "Completer les champ()s svp !!!");
        }
    }

//    private void imprimerdateAppro(ActionEvent event) throws SQLException {
//        if (!datebut(dateDebut).equals("")) {
//            _impresion("SELECT * FROM RAPPORTOK WHERE DATE='" + datebut(dateDebut), autre.Str("Select url x From turl") + "rapport_print.jrxml", 3);
//        } else {
//            Cls_autrer.alerteErreur("Notification", "Completer les champ()s svp !!!");
//        }
//    }
    @FXML
    private void PrintAllAgence(ActionEvent event) throws SQLException {
        if (!CbxAgence.getValue().equals("")) {
            _impresion("SELECT * FROM RAPPORTOK WHERE AGENCE='" + CbxAgence.getValue() + "'", autre.Str("Select url x From turl") + "rapport_print.jrxml", 3);
        } else {
            Cls_autrer.alerteErreur("Notification", "Completer les champ()s svp !!!");
        }
    }
}
